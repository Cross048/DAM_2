package com.pmdm.actividad04.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.pmdm.actividad04.retornos.ActivityA;
import com.pmdm.actividad04.R;

public class Activity1 extends AppCompatActivity {
    private View rojo;
    private View amarillo;
    private View verde;
    private View azul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity01);

        rojo = findViewById(R.id.rojo);
        amarillo = findViewById(R.id.amarillo);
        verde = findViewById(R.id.verde);
        azul = findViewById(R.id.azul);

        // Recoge el color del View seleccionado
        rojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity1.this, R.color.rojo);
                enviarColor(color);
            }
        });

        amarillo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity1.this, R.color.amarillo);
                enviarColor(color);
            }
        });

        verde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity1.this, R.color.verde);
                enviarColor(color);
            }
        });

        azul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ContextCompat.getColor(Activity1.this, R.color.azul);
                enviarColor(color);
            }
        });
    }

    // Abre la siguiente Activity mandándole el color del View seleccionado
    private void enviarColor(int color) {
        Intent intent = new Intent(this, ActivityA.class);
        intent.putExtra("color", color);
        startActivity(intent);
    }
}