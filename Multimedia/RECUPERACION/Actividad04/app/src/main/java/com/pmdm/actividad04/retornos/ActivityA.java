package com.pmdm.actividad04.retornos;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.pmdm.actividad04.R;

public class ActivityA extends AppCompatActivity {
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity05);

        btnVolver = findViewById(R.id.btnVolver);

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
    }
}