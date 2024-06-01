package com.pmdm.proyectofinal;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.pmdm.proyectofinal.usuarios.Usuario;
import com.pmdm.proyectofinal.usuarios.UsuariosDBHelper;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_REGISTER = 1;
    private static final String PREFS_NAME = "LoginPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_REMEMBER_ME = "rememberMe";

    private EditText etUsuario;
    private EditText etPassword;
    private CheckBox chkRecuerdame;
    private Button btnIniciar;
    private TextView tvRegistrarse;
    private UsuariosDBHelper dbHelper;
    private List<Usuario> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        chkRecuerdame = findViewById(R.id.chkRecuerdame);
        btnIniciar = findViewById(R.id.btnIniciar);
        tvRegistrarse = findViewById(R.id.tvRegistrarse);

        dbHelper = new UsuariosDBHelper(this);
        userList = dbHelper.getAllUsers();

        // Recuperar preferencias
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean rememberMe = settings.getBoolean(KEY_REMEMBER_ME, false);
        if (rememberMe) {
            String savedUsername = settings.getString(KEY_USERNAME, "");
            etUsuario.setText(savedUsername);
            chkRecuerdame.setChecked(true);
        }

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsuario.getText().toString();
                String password = etPassword.getText().toString();

                if (!username.isEmpty() && !password.isEmpty()) {
                    Usuario usuario = findUser(username);
                    if (usuario != null && usuario.getPassword().equals(password)) {
                        // Guardar preferencias
                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        if (chkRecuerdame.isChecked()) {
                            editor.putString(KEY_USERNAME, username);
                            editor.putBoolean(KEY_REMEMBER_ME, true);
                        } else {
                            editor.remove(KEY_USERNAME);
                            editor.putBoolean(KEY_REMEMBER_ME, false);
                        }
                        editor.apply();

                        // Enviar notificación
                        sendLoginNotification(username);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("username", usuario.getUsername());
                        intent.putExtra("nombre", usuario.getNombre());
                        intent.putExtra("apellido", usuario.getApellido());
                        intent.putExtra("type", usuario.getType());
                        intent.putExtra("profile_pic", usuario.getProfilePic());
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Cambiar la transición
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, REQUEST_CODE_REGISTER);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Cambiar la transición
            }
        });
    }

    private Usuario findUser(String username) {
        for (Usuario usuario : userList) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }

    private void sendLoginNotification(String username) {
        createNotificationChannel();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.ic_user)
                .setContentTitle("Inicio de sesión exitoso")
                .setContentText("Ha iniciado sesión el usuario " + username)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(123, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel Name";
            String description = "Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel_id", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_REGISTER) {
            if (resultCode == RESULT_OK) {
                userList = dbHelper.getAllUsers();
                String username = data.getStringExtra("username");
                if (username != null) {
                    etUsuario.setText(username);
                }
            } else {
                // ERROR
            }
        }
    }
}
