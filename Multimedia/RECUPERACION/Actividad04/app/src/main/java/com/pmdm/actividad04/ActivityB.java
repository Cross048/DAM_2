package com.pmdm.actividad04;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityB extends AppCompatActivity {
    private Button btnVolver;
    private Button btnVolverInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity06);

        btnVolver = findViewById(R.id.btnVolver);
        btnVolverInicio = findViewById(R.id.btnVolverInicio);

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
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}