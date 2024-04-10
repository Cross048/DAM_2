package com.pmdm.actividad05.retornos;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.pmdm.actividad05.R;

public class ActivityA extends AppCompatActivity {
    private Button btnVolver;
    private Button btnVolverInicio;
    private Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity05);

        btnVolver = findViewById(R.id.btnVolver);
        btnVolverInicio = findViewById(R.id.btnVolverInicio);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        // Recibe el color de la Activity anterior y la establece de color de fondo
        int color = getIntent().getIntExtra("color", Color.WHITE);
        View view = findViewById(android.R.id.content);
        view.setBackgroundColor(color);

        // Configura la visibilidad de los botones
        configureButtonVisibility(getIntent().getIntExtra("tipoVista", 0));

        // Volvemos a la Activity anterior terminando la actual
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });
    }

    // Método para configurar la visibilidad de los botones
    private void configureButtonVisibility(int tipoVista) {
        switch (tipoVista) {
            case 1:
                btnVolverInicio.setVisibility(View.GONE);
                btnFinalizar.setVisibility(View.GONE);
                break;
            case 2:
                btnFinalizar.setVisibility(View.GONE);
                break;
            case 3:
                // No es necesario modificar la visibilidad
                break;
            default:
                btnVolver.setVisibility(View.GONE);
                btnVolverInicio.setVisibility(View.GONE);
                btnFinalizar.setVisibility(View.GONE);
                break;
        }
    }
}