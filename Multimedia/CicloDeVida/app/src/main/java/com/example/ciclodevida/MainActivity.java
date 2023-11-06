package com.example.ciclodevida;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Ejecutando onCreate", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "Ejecutando onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Ejecutando onStart", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "Ejecutando onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Ejecutando onStop", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "Ejecutando onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Ejecutando onResume", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "Ejecutando onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Ejecutando onPause", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "Ejecutando onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Ejecutando onRestart", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "Ejecutando onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Ejecutando onDestroy", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "Ejecutando onDestroy");
    }
}