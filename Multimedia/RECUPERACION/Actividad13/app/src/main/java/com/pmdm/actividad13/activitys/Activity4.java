package com.pmdm.actividad13.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.pmdm.actividad13.R;
import com.pmdm.actividad13.retornos.ActivityB;

public class Activity4 extends AppCompatActivity {
    private static final int CODIGO_LLAMADA_ACTB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity04);

        findViewById(R.id.rojo).setOnClickListener(this::onColorClick);
        findViewById(R.id.amarillo).setOnClickListener(this::onColorClick);
        findViewById(R.id.naranja).setOnClickListener(this::onColorClick);
        findViewById(R.id.verde).setOnClickListener(this::onColorClick);
        findViewById(R.id.azul).setOnClickListener(this::onColorClick);
        findViewById(R.id.morado).setOnClickListener(this::onColorClick);
        findViewById(R.id.white).setOnClickListener(this::onColorClick);
        findViewById(R.id.azulito).setOnClickListener(this::onColorClick);
        findViewById(R.id.musgoso).setOnClickListener(this::onColorClick);
    }

    // Determina el color basado en el View clickeado
    private void onColorClick(View view) {
        int color = 0;

        if (view.getId() == R.id.rojo) {
            color = ContextCompat.getColor(this, R.color.rojo);
        } else if (view.getId() == R.id.amarillo) {
            color = ContextCompat.getColor(this, R.color.amarillo);
        } else if (view.getId() == R.id.naranja) {
            color = ContextCompat.getColor(this, R.color.naranja);
        } else if (view.getId() == R.id.verde) {
            color = ContextCompat.getColor(this, R.color.verde);
        } else if (view.getId() == R.id.azul) {
            color = ContextCompat.getColor(this, R.color.azul);
        } else if (view.getId() == R.id.morado) {
            color = ContextCompat.getColor(this, R.color.morado);
        } else if (view.getId() == R.id.white) {
            color = ContextCompat.getColor(this, R.color.white);
        } else if (view.getId() == R.id.azulito) {
            color = ContextCompat.getColor(this, R.color.azulito);
        } else if (view.getId() == R.id.musgoso) {
            color = ContextCompat.getColor(this, R.color.musgoso);
        }

        if (color != 0) {
            enviarColor(color, view.getId());
        }
    }

    // Método para enviar el color a la siguiente Activity
    private void enviarColor(int color, int viewId) {
        Intent intent = new Intent(this, ActivityB.class);
        intent.putExtra("color", color);
        intent.putExtra("viewId", viewId);
        startActivityForResult(intent, CODIGO_LLAMADA_ACTB);
    }

    // Método para borrar el color
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_LLAMADA_ACTB && resultCode == RESULT_OK) {
            boolean changeColorToTransparent = data.getBooleanExtra("changeColorToTransparent", false);
            if (changeColorToTransparent) {
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
