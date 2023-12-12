package com.example.a2023_cristianbernalmendez;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity1 extends AppCompatActivity {
    private EditText txtAltura;
    private String stringAltura;
    private float altura;
    private EditText txtPeso;
    private String stringPeso;
    private float peso;
    private float IMC;
    private Button btnCalcular;
    private Button btnFinApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        txtAltura = findViewById(R.id.txtAltura);
        txtPeso = findViewById(R.id.txtPeso);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnFinApp = findViewById(R.id.btnFinApp);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnCalcular) {
                    stringAltura = txtAltura.getText().toString();
                    stringPeso = txtPeso.getText().toString();
                    if (stringAltura.isEmpty()) {
                        Toast.makeText(Activity1.this, "Introduce una altura", Toast.LENGTH_SHORT).show();
                    } else if (stringPeso.isEmpty()) {
                        Toast.makeText(Activity1.this, "Introduce un peso", Toast.LENGTH_SHORT).show();
                    } else {
                        altura = Float.parseFloat(stringAltura);
                        peso = Float.parseFloat(stringPeso);

                        IMC = peso / (altura * altura); // Fórmula IMC

                        Intent intent = new Intent(Activity1.this, Activity3.class);
                        String resultado = String.valueOf(IMC);
                        intent.putExtra("mensaje", resultado);
                        startActivity(intent);
                    }
                }
            }
        });

        btnFinApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnFinApp) {
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}