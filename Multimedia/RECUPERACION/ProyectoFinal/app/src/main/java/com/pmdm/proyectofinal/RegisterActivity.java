package com.pmdm.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pmdm.proyectofinal.usuarios.Usuario;
import com.pmdm.proyectofinal.usuarios.UsuariosDBHelper;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsuario;
    private EditText etPassword;
    private UsuariosDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        Button btnRegistrar = findViewById(R.id.btnRegistrar);

        dbHelper = new UsuariosDBHelper(this);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsuario.getText().toString();
                String password = etPassword.getText().toString();

                if (!username.isEmpty() && !password.isEmpty()) {
                    // Crear un objeto Usuario con los datos proporcionados
                    Usuario nuevoUsuario = new Usuario(username, password);

                    // Añadir el usuario a la base de datos
                    boolean isInserted = dbHelper.addUser(nuevoUsuario.getUsername(), nuevoUsuario.getPassword());

                    if (isInserted) {
                        Toast.makeText(RegisterActivity.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                        Intent resultIntent = new Intent();
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
}
