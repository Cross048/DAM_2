package com.example.a2023_cristianbernalmendez;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    private static final int CODIGO_LLAMADA_ACT1 = 0;
    private RadioButton radBtnCalcularIMC;
    private RadioButton radBtnInfo;
    private Button btnCalcularIMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radBtnCalcularIMC = findViewById(R.id.radBtnCalcularIMC);
        radBtnInfo = findViewById(R.id.radBtnInfo);
        btnCalcularIMC = findViewById(R.id.btnCalcularIMC);

        btnCalcularIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnCalcularIMC) {
                    if (radBtnCalcularIMC.isChecked()) {
                        Intent intent = new Intent(MainActivity.this, Activity1.class);
                        startActivityForResult(intent, CODIGO_LLAMADA_ACT1);
                    } else if (radBtnInfo.isChecked()) {
                        Intent intent = new Intent(MainActivity.this, Activity2.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_LLAMADA_ACT1) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }
}