package com.pmdm.actividad07;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.pmdm.actividad07.activitys.Activity1;
import com.pmdm.actividad07.activitys.Activity2;
import com.pmdm.actividad07.activitys.Activity3;
import com.pmdm.actividad07.activitys.Activity4;

public class MainActivity extends AppCompatActivity {
    private static final int CODIGO_LLAMADA_ACT3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        Spinner spinner1 = findViewById(R.id.spinner1);
        Spinner spinner2 = findViewById(R.id.spinner2);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                // Si se selecciona la opción "6 colores"
                if (position == 1) {
                    spinner2.setVisibility(View.VISIBLE); // Hacer spinner2 visible
                } else {
                    spinner2.setVisibility(View.GONE); // Hacer spinner2 invisible para otras opciones
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nada seleccionado
            }
        });

        String[] options = {"Sin padding", "Con padding"};

        // Crear un ArrayAdapter utilizando el diseño simple_spinner_item predeterminado de Android
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);

        // Especificar el diseño a utilizar cuando se despliegan las opciones
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Establecer el adaptador en el Spinner
        spinner2.setAdapter(adapter);

        Button btnAdelante = findViewById(R.id.btnAdelante);
        btnAdelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int opcionSeleccionadaSpinner1 = spinner1.getSelectedItemPosition();
                int opcionSeleccionadaSpinner2 = spinner2.getSelectedItemPosition();

                Class<?> nextActivity = null;

                // Verificar si la opción seleccionada en spinner2 es la segunda opción y
                // la opción seleccionada en spinner1 es la segunda opción
                if (opcionSeleccionadaSpinner2 == 1 && opcionSeleccionadaSpinner1 == 0) {
                    nextActivity = Activity2.class;
                }
                // Verificar si la opción seleccionada en spinner2 es la segunda opción y
                // la opción seleccionada en spinner1 es la tercera opción
                else if (opcionSeleccionadaSpinner2 == 1 && opcionSeleccionadaSpinner1 == 1) {
                    nextActivity = Activity3.class;
                }
                // Si spinner2 no está seleccionado,
                // abrir la actividad basada en la selección de spinner1
                else {
                    switch (opcionSeleccionadaSpinner1) {
                        case 0:
                            nextActivity = Activity1.class;
                            break;
                        case 1:
                            nextActivity = Activity2.class;
                            break;
                        case 2:
                            nextActivity = Activity3.class;
                            break;
                        default:
                            break;
                    }
                }

                if (nextActivity != null) {
                    Intent intent = new Intent(MainActivity.this, nextActivity);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Espera el resultado de que hay que cerrar la App
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_LLAMADA_ACT3) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }
}
