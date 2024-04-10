package com.pmdm.actividad05.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.pmdm.actividad05.R;
import com.pmdm.actividad05.retornos.ActivityB;

public class Activity2 extends AppCompatActivity {
    private static final int CODIGO_LLAMADA_ACT1 = 0;
    private View rojo;
    private View amarillo;
    private View naranja;
    private View verde;
    private View azul;
    private View morado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity02);

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
                int color = ContextCompat.getColor(Activity2.this, R.color.rojo);
                enviarColor(color);
            }
        });

        amarillo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity2.this, R.color.amarillo);
                enviarColor(color);
            }
        });

        naranja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity2.this, R.color.naranja);
                enviarColor(color);
            }
        });

        verde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity2.this, R.color.verde);
                enviarColor(color);
            }
        });

        azul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity2.this, R.color.azul);
                enviarColor(color);
            }
        });

        morado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity2.this, R.color.morado);
                enviarColor(color);
            }
        });
    }

    private void enviarColor(int color) {
        Intent intent = new Intent(this, ActivityB.class);
        intent.putExtra("color", color);
        startActivityForResult(intent, CODIGO_LLAMADA_ACT1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_LLAMADA_ACT1) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }
}