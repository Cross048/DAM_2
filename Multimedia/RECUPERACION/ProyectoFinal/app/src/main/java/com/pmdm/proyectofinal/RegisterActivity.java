package com.pmdm.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pmdm.proyectofinal.usuarios.Usuario;
import com.pmdm.proyectofinal.usuarios.UsuariosDBHelper;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsuario;
    private EditText etPassword;
    private EditText etNombre;
    private EditText etApellidos;
    private Spinner spinnerType;
    private Button btnRegistrar;
    private UsuariosDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        spinnerType = findViewById(R.id.spinnerType);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        dbHelper = new UsuariosDBHelper(this);

        String[] typeOptions = getResources().getStringArray(R.array.type_options);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, typeOptions);
        spinnerType.setAdapter(adapter);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsuario.getText().toString();
                String password = etPassword.getText().toString();
                String nombre = etNombre.getText().toString();
                String apellido = etApellidos.getText().toString();

                int spinnerIndex = spinnerType.getSelectedItemPosition();
                int type = spinnerIndex == 0 ? 0 : 1;
                int profilePic = 0; // Valor por defecto

                if (!username.isEmpty() && !password.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty()) {
                    Usuario nuevoUsuario = new Usuario(username, password, nombre, apellido, type, profilePic);

                    boolean isInserted = dbHelper.addUser(
                            nuevoUsuario.getUsername(),
                            nuevoUsuario.getPassword(),
                            nuevoUsuario.getNombre(),
                            nuevoUsuario.getApellido(),
                            nuevoUsuario.getType(),
                            nuevoUsuario.getProfilePic()
                    );

                    if (isInserted) {
                        Toast.makeText(RegisterActivity.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("username", username);
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right); // Cambiar la transición al retroceder
    }
}
