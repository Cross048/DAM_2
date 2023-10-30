package com.example.componentesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Definimos variables
    private ToggleButton toggleButton;
    private Switch switchWiFi;
    private Button buttonConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarVariables(); // Inicializar variables
        asignarListeners(); // Asignar listeners
    }

    private void inicializarVariables() {
        toggleButton = findViewById(R.id.toggle1);
        switchWiFi = findViewById(R.id.swWiFi);
        buttonConfirmar = findViewById(R.id.btnConfirmar);
    }

    private void asignarListeners() {
        toggleButton.setOnClickListener(this);
        switchWiFi.setOnClickListener(this);
        buttonConfirmar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toggle1) {
            if (toggleButton.isChecked()) {
                Toast.makeText(this, "toggle ACTIVADO", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "toggle DESACTIVADO", Toast.LENGTH_SHORT).show();
            }
        } // End toggle
        else if (v.getId() == R.id.swWiFi) {
            if (switchWiFi.isChecked()) {
                Toast.makeText(this, "Wi-Fi ACTIVADO", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Wi-Fi DESACTIVADO", Toast.LENGTH_LONG).show();
            }
        } // End switch
        else if (v.getId() == R.id.btnConfirmar) {
            if (buttonConfirmar.isPressed()) {
                Toast.makeText(this, "Boton PULSADO", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Boton NO PULSADO", Toast.LENGTH_LONG).show();
            }
        } // End button
    }
}