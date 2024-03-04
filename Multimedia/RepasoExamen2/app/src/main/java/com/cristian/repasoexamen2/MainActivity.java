package com.cristian.repasoexamen2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static Spinner spinnerCursos;
    private static Spinner spinnerCiclos;
    private static final int CICLOS_SPINNER = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Spinners
        spinnerCursos = findViewById(R.id.spinnerCursos);
        spinnerCiclos = findViewById(R.id.spinnerCiclos);

        // Adapter spinnerCursos
        ArrayAdapter<CharSequence> adapterCursos = ArrayAdapter.createFromResource(
                this,
                R.array.cursos,
                android.R.layout.simple_spinner_item
        );
        adapterCursos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCursos.setAdapter(adapterCursos);

        // Adapter spinnerCiclos
        ArrayAdapter<CharSequence> adapterCiclos = ArrayAdapter.createFromResource(
                this,
                R.array.ciclos,
                android.R.layout.simple_spinner_item
        );
        adapterCiclos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCiclos.setAdapter(adapterCiclos);

        // Manejo de la selección en el Spinner spinnerCursos
        spinnerCursos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtiene el índice de la opción seleccionada
                int selectedItemIndex = spinnerCursos.getSelectedItemPosition();

                // Si se selecciona la opción deseada, hace visible el Spinner spinnerCiclos
                if (selectedItemIndex == CICLOS_SPINNER) {
                    spinnerCiclos.setVisibility(View.VISIBLE);
                } else {
                    // Si no se selecciona la opción deseada, oculta el Spinner spinnerCiclos
                    spinnerCiclos.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No se realiza ninguna acción si no se selecciona nada
            }
        });
    }
}
