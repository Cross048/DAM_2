package com.example.ciclodevida;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Activity3 extends AppCompatActivity {
    private TextView tvRecepcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        // Recupera el dato enviado desde MainActivity med. el intent
        Intent intent = getIntent();
        // Extraer el dato
        String datoRecibido = intent.getStringExtra("mensaje");
        // Visualizar el dato en la TextView
        TextView tvRecepcion = findViewById(R.id.tvMensajeRecibido);
        tvRecepcion.setText(datoRecibido);
    }
}