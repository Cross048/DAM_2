package com.example.cambiodemonedas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    TextView txtCambio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        txtCambio = findViewById(R.id.txtdinerocambiado);
        Intent intent = getIntent();
        String cambio = intent.getStringExtra("mensaje");
        txtCambio.setText(cambio);


    }
}