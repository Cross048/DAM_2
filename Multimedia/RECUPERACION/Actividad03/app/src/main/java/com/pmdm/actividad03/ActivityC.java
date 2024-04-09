package com.pmdm.actividad03;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityC extends AppCompatActivity {
    private static final int RESULT_MAIN = 1;
    private static final int RESULT_FINISH = 2;
    private Button btnVolver;
    private Button btnVolverInicio;
    private Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity07);

        btnVolver = findViewById(R.id.btnVolver);
        btnVolverInicio = findViewById(R.id.btnVolverInicio);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        // Recibe el color de la Activity anterior y la establece de color de fondo
        int color = getIntent().getIntExtra("color", Color.WHITE);
        View view = findViewById(android.R.id.content);
        view.setBackgroundColor(color);

        // Volvemos a la Activity anterior terminando la actual
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Finaliza la Activity y la manda al MainActivity
        btnVolverInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_MAIN);
                finish();
            }
        });

        // Finaliza la Activity y la manda al MainActivity, finalizando esta también
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_FINISH);
                finish();
            }
        });
    }
}