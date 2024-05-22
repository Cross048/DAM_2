package com.pmdm.proyectofinal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsuario;
    private EditText etPassword;
    private Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // ID elementos
        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnIniciar = findViewById(R.id.btnIniciar);

        // Botón para iniciar sesión
        btnIniciar.setOnClickListener(v -> {
            String username = etUsuario.getText().toString();
            String password = etPassword.getText().toString();

            // Iniciar sesión
        });
    }
}
