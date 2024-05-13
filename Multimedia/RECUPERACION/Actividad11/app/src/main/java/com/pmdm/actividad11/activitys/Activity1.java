package com.pmdm.actividad11.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.pmdm.actividad11.R;
import com.pmdm.actividad11.retornos.ActivityA;

public class Activity1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity01);

        // Asignación del mismo OnClickListener a todos los Views
        findViewById(R.id.rojo).setOnClickListener(this::onColorClick);
        findViewById(R.id.amarillo).setOnClickListener(this::onColorClick);
        findViewById(R.id.verde).setOnClickListener(this::onColorClick);
        findViewById(R.id.azul).setOnClickListener(this::onColorClick);
    }

    private void onColorClick(View view) {
        int color = 0;

        // Determina el color basado en el View clickeado
        if (view.getId() == R.id.rojo) {
            color = ContextCompat.getColor(this, R.color.rojo);
        } else if (view.getId() == R.id.amarillo) {
            color = ContextCompat.getColor(this, R.color.amarillo);
        } else if (view.getId() == R.id.verde) {
            color = ContextCompat.getColor(this, R.color.verde);
        } else if (view.getId() == R.id.azul) {
            color = ContextCompat.getColor(this, R.color.azul);
        }

        // Si se encontró un color, envía el color a la siguiente Activity
        if (color != 0) {
            enviarColor(color);
        }
    }

    // Abre la siguiente Activity mandándole el color del View seleccionado
    private void enviarColor(int color) {
        Intent intent = new Intent(this, ActivityA.class);
        intent.putExtra("color", color);
        // 1 para solo el primer botón, 2 para los dos primeros, 3 para todos
        intent.putExtra("tipoVista", 1);
        startActivity(intent);
    }
}
