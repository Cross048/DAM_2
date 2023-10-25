package com.example.girarsaludo;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button pulsarButton;
    private int rotacion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define el botón según su ID
        Button pulsarButton = findViewById(R.id.pulsador);

        // Función del pulsador
        pulsarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotacion += 45;
                if (rotacion == 360) { rotacion = 0; }

                pulsarButton.setText("¡Hola!");
                pulsarButton.setTextColor(getResources().getColor(R.color.black));
                pulsarButton.setTextSize(40);
                pulsarButton.setRotation(rotacion);
            }
        });
    }
}