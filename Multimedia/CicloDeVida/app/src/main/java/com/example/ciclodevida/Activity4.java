package com.example.ciclodevida;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity4 extends AppCompatActivity {
    private TextView tvBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        // Recupera el dato enviado desde MainActivity med. el intent
        Intent intent = getIntent();
        // Extraer el dato
        Bundle bundle = intent.getExtras();
        // Recuperar el dato con el método adecuado
        String datoRecibido = bundle.getString("mensaje");
        // Visualizar el dato en la TextView
        TextView tvBundle = findViewById(R.id.tvBundleRecibido);
        tvBundle.setText(datoRecibido);
    }
}