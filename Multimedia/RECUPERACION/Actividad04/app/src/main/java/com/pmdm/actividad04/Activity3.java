package com.pmdm.actividad04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Activity3 extends AppCompatActivity {
    private static final int CODIGO_LLAMADA_ACT1 = 0;
    private static final int RESULT_MAIN = 1;
    private static final int RESULT_FINISH = 2;
    private View rojo;
    private View amarillo;
    private View naranja;
    private View verde;
    private View azul;
    private View morado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity03);

        rojo = findViewById(R.id.rojo);
        amarillo = findViewById(R.id.amarillo);
        naranja = findViewById(R.id.naranja);
        verde = findViewById(R.id.verde);
        azul = findViewById(R.id.azul);
        morado = findViewById(R.id.morado);

        // Recoge el color del View seleccionado
        rojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity3.this, R.color.rojo);
                enviarColor(color);
            }
        });

        amarillo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity3.this, R.color.amarillo);
                enviarColor(color);
            }
        });

        naranja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity3.this, R.color.naranja);
                enviarColor(color);
            }
        });

        verde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity3.this, R.color.verde);
                enviarColor(color);
            }
        });

        azul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity3.this, R.color.azul);
                enviarColor(color);
            }
        });

        morado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity3.this, R.color.morado);
                enviarColor(color);
            }
        });
    }

    // Mandamos color a la siguiente Activity
    private void enviarColor(int color) {
        Intent intent = new Intent(this, ActivityC.class);
        intent.putExtra("color", color);
        startActivityForResult(intent, CODIGO_LLAMADA_ACT1);
    }

    // Según el resultado hace algo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_LLAMADA_ACT1) {
            if (resultCode == RESULT_MAIN) {
                // Vuelve al MainActivity
                finish();
            } else if (resultCode == RESULT_FINISH) {
                // Devuelve el resultado y cierra la App
                setResult(RESULT_OK);
                finish();
            }
        }
    }
}