package com.pmdm.actividad04.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.pmdm.actividad04.retornos.ActivityD;
import com.pmdm.actividad04.R;

public class Activity4 extends AppCompatActivity {
    private static final int REQUEST_CODE_COLOR_CHANGED = 1;
    private View rojo;
    private View amarillo;
    private View naranja;
    private View verde;
    private View azul;
    private View morado;
    private View white;
    private View azulito;
    private View musgoso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity04);

        rojo = findViewById(R.id.rojo);
        amarillo = findViewById(R.id.amarillo);
        naranja = findViewById(R.id.naranja);
        verde = findViewById(R.id.verde);
        azul = findViewById(R.id.azul);
        morado = findViewById(R.id.morado);
        white = findViewById(R.id.white);
        azulito = findViewById(R.id.azulito);
        musgoso = findViewById(R.id.musgoso);

        // Recoge el color del View seleccionado
        rojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity4.this, R.color.rojo);
                enviarColor(color, R.id.rojo);
            }
        });

        amarillo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity4.this, R.color.amarillo);
                enviarColor(color, R.id.amarillo);
            }
        });

        naranja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity4.this, R.color.naranja);
                enviarColor(color, R.id.naranja);
            }
        });

        verde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity4.this, R.color.verde);
                enviarColor(color, R.id.verde);
            }
        });

        azul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity4.this, R.color.azul);
                enviarColor(color, R.id.azul);
            }
        });

        morado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity4.this, R.color.morado);
                enviarColor(color, R.id.morado);
            }
        });

        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity4.this, R.color.white);
                enviarColor(color, R.id.white);
            }
        });

        azulito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity4.this, R.color.azulito);
                enviarColor(color, R.id.azulito);
            }
        });

        musgoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity4.this, R.color.musgoso);
                enviarColor(color, R.id.musgoso);
            }
        });
    }

    // Mandamos color a la siguiente Activity
    private void enviarColor(int color, int viewId) {
        Intent intent = new Intent(this, ActivityD.class); // Asegúrate de que el nombre de la clase sea correcto
        intent.putExtra("color", color);
        intent.putExtra("viewId", viewId); // Enviamos el ID del View seleccionado
        startActivityForResult(intent, REQUEST_CODE_COLOR_CHANGED);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_COLOR_CHANGED && resultCode == RESULT_OK && data != null) {
            boolean changeToTransparent = data.getBooleanExtra("changeColorToTransparent", false);
            if (changeToTransparent) {
                int viewId = data.getIntExtra("viewId", -1);
                if (viewId != -1) {
                    View view = findViewById(viewId);
                    if (view != null) {
                        view.setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        }
    }
}