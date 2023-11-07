package com.example.ciclodevida;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity5 extends AppCompatActivity {
    private Button btnRetornar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        btnRetornar = findViewById(R.id.btnLlamarActivity1);
        btnRetornar.setOnClickListener(){ // TODO: rellenar paréntesis
            @Override
            public void onClick(View view) {
                String datoRespuesta = "La actividad 5 envía de vuelta este mensaje a la Act 1";
                Intent intent = new Intent();
                intent.putExtra("Mensaje_retornado", datoRespuesta);
                setResult(RESULT_OK, intent);
            }

        };
    }
}