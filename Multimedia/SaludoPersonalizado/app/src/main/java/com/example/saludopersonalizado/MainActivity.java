package com.example.saludopersonalizado;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText nombre;
    private EditText nacimiento;
    private RadioButton sr;
    private RadioButton sra;
    private String genero;
    private CheckBox despedir;
    private boolean confirmarDespedir = false;
    private Button saludar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarVariables(); // Inicializar variables
        asignarListeners(); // Asignar listeners
    }
    private void inicializarVariables() {
        nombre = findViewById(R.id.etNombre);
        nacimiento = findViewById(R.id.etNacimiento);
        sr = findViewById(R.id.btnSr);
        sra = findViewById(R.id.btnSra);
        despedir = findViewById(R.id.cboxDespedida);
        saludar = findViewById(R.id.btnSaludar);
    }
    private void asignarListeners() {
        sr.setOnClickListener((View.OnClickListener) this);
        sra.setOnClickListener((View.OnClickListener) this);
        despedir.setOnClickListener((View.OnClickListener) this);
        saludar.setOnClickListener((View.OnClickListener) this);
    }
    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        // Comprueba si se ha pulsado
        boolean checked = ((RadioButton) view).isChecked();

        // Comprueba que RadioButton ha sido pulsado
        switch(view.getId()) {
            case R.id.btnSr:
                if (checked)
                    genero = "Sr.";
                    break;
            case R.id.btnSra:
                if (checked)
                    genero = "Sra.";
                    break;
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.cboxDespedida) {
            if (despedir.isChecked()) {
                confirmarDespedir = true;
            } else {
                confirmarDespedir = false;
            }
        } // End CheckBox
        if (view.getId() == R.id.btnSaludar) {
            if (saludar.isPressed()) {
                if (confirmarDespedir) {
                    System.out.print("Hasta luego " + genero + " ");
                }
            }
        } // End CheckBox
    }
}