package com.pmdm.actividad05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.pmdm.actividad05.activitys.Activity1;
import com.pmdm.actividad05.activitys.Activity2;
import com.pmdm.actividad05.activitys.Activity3;
import com.pmdm.actividad05.activitys.Activity4;

public class MainActivity extends AppCompatActivity {
    private static final int CODIGO_LLAMADA_ACT3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialización de botones
        findViewById(R.id.btn1).setOnClickListener(this::handleButtonClick);
        findViewById(R.id.btn2).setOnClickListener(this::handleButtonClick);
        findViewById(R.id.btn3).setOnClickListener(this::handleButtonClick);
        findViewById(R.id.btn4).setOnClickListener(this::handleButtonClick);
    }

    // Según el botón pulsado, va a un Activity u otro
    private void handleButtonClick(View view) {
        Intent intent = null;
        boolean isStartActivityForResult = false;

        if (view.getId() == R.id.btn1) {
            intent = new Intent(MainActivity.this, Activity1.class);
        } else if (view.getId() == R.id.btn2) {
            intent = new Intent(MainActivity.this, Activity2.class);
        } else if (view.getId() == R.id.btn3) {
            intent = new Intent(MainActivity.this, Activity3.class);
            isStartActivityForResult = true;
        } else if (view.getId() == R.id.btn4) {
            intent = new Intent(MainActivity.this, Activity4.class);
        }

        if (intent != null) {
            if (isStartActivityForResult) {
                startActivityForResult(intent, CODIGO_LLAMADA_ACT3);
            } else {
                startActivity(intent);
            }
        }
    }

    // Espera el resultado de que hay que cerrar la App
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_LLAMADA_ACT3) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }
}