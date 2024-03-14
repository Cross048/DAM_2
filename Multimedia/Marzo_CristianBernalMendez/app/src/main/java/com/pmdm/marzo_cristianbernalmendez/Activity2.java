package com.pmdm.marzo_cristianbernalmendez;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Activity2 extends AppCompatActivity {
    private EditText editNombre;
    private Button btnComprobar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        editNombre = findViewById(R.id.editNombre);
        btnComprobar = findViewById(R.id.btnComprobar);

        Intent intent = getIntent();
        String compuesto = intent.getStringExtra("mensaje");
        editNombre.setHint(compuesto);

        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnComprobar) {
                    String formula = editNombre.getText().toString();

                    if (formula == "") {
                        Toast.makeText(Activity2.this, "Campo vacío", Toast.LENGTH_SHORT).show();
                    } else {
                        // ToDo: comprobar si es igual a la fórmula real
                    }
                }
            }
        });
    }
}
