package com.pmdm.actividad15;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.pmdm.actividad15.activitys.Activity3;

public class MainActivity extends AppCompatActivity {
    private EditText txtNombre;
    private CheckBox chkRecordar;
    private Button btnOk;
    private SharedPreferences sharedPreferences;
    private static final int CODIGO_LLAMADA_SELECT = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        txtNombre = findViewById(R.id.txtNombre);
        chkRecordar = findViewById(R.id.chkRecordar);
        btnOk = findViewById(R.id.btnOk);

        // Obtener SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Si hay un nombre guardado, establecerlo como hint en txtNombre
        String nombreGuardado = sharedPreferences.getString("nombre", "");
        if (!nombreGuardado.isEmpty()) {
            txtNombre.setHint(nombreGuardado);
        }

        // Configurar el OnClickListener para el botón Guardar
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNombre();

                startActivityForResult(new Intent(MainActivity.this, ActivitySelect.class), CODIGO_LLAMADA_SELECT);
            }
        });
    }

    private void guardarNombre() {
        String nombre = txtNombre.getText().toString().trim();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (chkRecordar.isChecked()) {
            // Si el CheckBox está marcado, guardar el nombre en SharedPreferences
            editor.putString("nombre", nombre);
            editor.apply();
        } else {
            // Si el CheckBox no está marcado, borrar el nombre de SharedPreferences
            editor.remove("nombre");
            editor.apply();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Espera el resultado de que hay que cerrar la App
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_LLAMADA_SELECT) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }
}

