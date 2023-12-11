package com.example.ciclodevida;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Activity5 extends AppCompatActivity {
    private Button btnRetornar;
    private String datoRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        btnRetornar = findViewById(R.id.btnLlamarActivity1);
        btnRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datoRespuesta = "La actividad 5 envía de vuelta este mensaje a la Actividad 1";
                Intent intent = new Intent();
                intent.putExtra("Mensaje_retornado", datoRespuesta);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}