package com.example.ciclodevida;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int CODIGO_LLAMADA_ACT5 = 0;
    private Button buttonActivity2;
    private Button buttonActivity3;
    private Button buttonActivity4;
    private Button buttonActivity5;
    private String datoAEnviar;
    private String datoRespuesta;
    private TextView tvRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "App iniciada con éxito", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "Ejecutando onCreate");

        buttonActivity2 = findViewById(R.id.btnLlamarActivity2);
        buttonActivity3 = findViewById(R.id.btnLlamarActivity3);
        buttonActivity4 = findViewById(R.id.btnLlamarActivity4);
        buttonActivity5 = findViewById(R.id.btnLlamarActivity5);
    }

    public void onClickCambio(View view) {
        if (view.getId() == R.id.btnLlamarActivity2) {
            Intent intent = new Intent(this, Activity2.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btnLlamarActivity3) {
            Intent intent = new Intent(this, Activity3.class);
            datoAEnviar = "Activity 1 envia mensaje a Activity 3";
            intent.putExtra("mensaje", datoAEnviar);
            startActivity(intent);
        } else if (view.getId() == R.id.btnLlamarActivity4) {
            Intent intent = new Intent(this, Activity4.class);
            datoAEnviar = "Activity 1 envia bundle a Activity 4";
            Bundle bundle = new Bundle();
            bundle.putString("mensaje", datoAEnviar);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (view.getId() == R.id.btnLlamarActivity5) {
            Intent intent = new Intent(this, Activity5.class);
            startActivityForResult(intent, CODIGO_LLAMADA_ACT5);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Vemos qué Activity nos está contestando
        if (requestCode == CODIGO_LLAMADA_ACT5) {
            // Testeamos el codigo del resultado
            if (resultCode == RESULT_OK) {
                // Operaciones si la actividad llamada finaliza según lo previsto
                datoRespuesta = data.getStringExtra("Mensaje_retornado");
                Toast.makeText(this, "Todo ok", Toast.LENGTH_SHORT).show();
                tvRespuesta = findViewById(R.id.tvRespuesta);
                tvRespuesta.setText(datoRespuesta);
            } else {
                // Algo fue mal :(
            }
        }
    }

    /* Mensajes de estado (comprobaciones)
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
    */
}