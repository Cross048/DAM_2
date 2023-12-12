package com.example.a2023_cristianbernalmendez;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    private RadioButton radBtnWikipedia;
    private RadioButton radBtnOMS;
    private Button btnOk;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        radBtnWikipedia = findViewById(R.id.radBtnWikipedia);
        radBtnOMS = findViewById(R.id.radBtnOMS);
        btnOk = findViewById(R.id.btnOk);
        btnVolver = findViewById(R.id.btnVolver);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnOk) {
                    if (radBtnWikipedia.isChecked()) {
                        // No terminado
                        Toast.makeText(Activity2.this, "No terminado", Toast.LENGTH_SHORT).show();
                    } else if (radBtnOMS.isChecked()) {
                        // No terminado
                        Toast.makeText(Activity2.this, "No terminado", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}