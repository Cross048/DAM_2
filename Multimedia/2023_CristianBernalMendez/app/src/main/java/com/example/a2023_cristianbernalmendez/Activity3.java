package com.example.a2023_cristianbernalmendez;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity3 extends AppCompatActivity {
    private TextView tvResultado;
    private TextView tvTipo;
    private float IMC;
    private static final float SOBREPESO = 25.0F;
    private static final float OBESIDAD = 30.0F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        tvResultado = findViewById(R.id.tvResultado);
        tvTipo = findViewById(R.id.tvTipo);

        Intent intent = getIntent();
        String cambio = intent.getStringExtra("mensaje");
        tvResultado.setText("IMC = " + cambio);

        IMC = Float.parseFloat(cambio);
        if (IMC >= OBESIDAD) {
            tvTipo.setText("OBESIDAD");
        } else if (IMC >= SOBREPESO) {
            tvTipo.setText("SOBREPESO");
        } else {
            tvTipo.setText("NORMAL");
        }
    }
}