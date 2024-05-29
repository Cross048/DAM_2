package com.pmdm.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pmdm.proyectofinal.usuarios.Usuario;
import com.pmdm.proyectofinal.usuarios.UsuariosDBHelper;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_REGISTER = 1;
    private EditText etUsuario;
    private EditText etPassword;
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
        btnIniciar = findViewById(R.id.btnIniciar);
        tvRegistrarse = findViewById(R.id.tvRegistrarse);

        dbHelper = new UsuariosDBHelper(this);
        userList = dbHelper.getAllUsers();

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsuario.getText().toString();
                String password = etPassword.getText().toString();

                if (!username.isEmpty() && !password.isEmpty()) {
                    Usuario usuario = findUser(username);
                    if (usuario != null && usuario.getPassword().equals(password)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("username", usuario.getUsername());
                        intent.putExtra("firstName", usuario.getNombre());
                        intent.putExtra("lastName", usuario.getApellido());
                        intent.putExtra("userType", usuario.getType());
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_REGISTER) {
            if (resultCode == RESULT_OK) {
                userList = dbHelper.getAllUsers();
            } else {
                // ERROR
            }
        }
    }
}
