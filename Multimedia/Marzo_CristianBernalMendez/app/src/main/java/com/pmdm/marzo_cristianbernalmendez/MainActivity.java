package com.pmdm.marzo_cristianbernalmendez;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ImageView imgInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgInicio = findViewById(R.id.imgInicio);

        // Configurar la imagen
        imgInicio.setImageResource(R.drawable.flaskgrande);
        imgInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.imgInicio) {
                    Intent intent = new Intent(MainActivity.this, Activity1.class);
                    startActivity(intent);
                }
            }
        });
    }
}
