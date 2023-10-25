package com.example.contadorclicks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button pulsarButton;
    private Button finalizarButton;
    private TextView textView;
    private int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define el botón y el textView según su ID
        Button pulsarButton = findViewById(R.id.pulsador);
        Button finalizarButton = findViewById(R.id.finalizar);
        TextView textView = findViewById(R.id.contador);

        // Función del pulsador
        pulsarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Incrementar el contador
                cont++;
                // Actualizar el TextView con el valor del contador
                textView.setText("Has pulsado " + String.valueOf(cont) + " veces");
            }
        });

        // Función de resetear contador
        finalizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Restablecer el contador a 0
                cont = 0;
                // Actualizar el TextView con el valor del contador
                textView.setText("Has pulsado " + String.valueOf(cont) + " veces");
            }
        });

    }
}