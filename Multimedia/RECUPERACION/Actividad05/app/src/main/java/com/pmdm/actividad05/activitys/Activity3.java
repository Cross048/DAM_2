package com.pmdm.actividad05.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.pmdm.actividad05.R;
import com.pmdm.actividad05.retornos.ActivityC;

public class Activity3 extends AppCompatActivity {
    private static final int CODIGO_LLAMADA_ACT1 = 0;
    private static final int RESULT_MAIN = 1;
    private static final int RESULT_FINISH = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity03);

        findViewById(R.id.rojo).setOnClickListener(this::onColorClick);
        findViewById(R.id.amarillo).setOnClickListener(this::onColorClick);
        findViewById(R.id.naranja).setOnClickListener(this::onColorClick);
        findViewById(R.id.verde).setOnClickListener(this::onColorClick);
        findViewById(R.id.azul).setOnClickListener(this::onColorClick);
        findViewById(R.id.morado).setOnClickListener(this::onColorClick);
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
        }

        if (color != 0) {
            enviarColor(color);
        }
    }

    private void enviarColor(int color) {
        Intent intent = new Intent(this, ActivityC.class);
        intent.putExtra("color", color);
        intent.putExtra("tipoVista", 3);
        startActivityForResult(intent, CODIGO_LLAMADA_ACT1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_LLAMADA_ACT1 && resultCode == RESULT_OK) {
            finish();
        }
    }
}