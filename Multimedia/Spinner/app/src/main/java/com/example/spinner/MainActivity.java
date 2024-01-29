package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private Spinner spProvincia;
    private Spinner spLocalidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //escuchador del spinner
        spProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String planeta = spProvincia.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        String[] arrayPlanetas= {"Mercurio", "Venus", "Tierra", "Marte", "Júpiter",
                "Saturno","Urano","Neptuno"};

        //creamos el elemento adaptador
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, arrayPlanetas);
        //asignamos al Spinner el adaptador con los datos ya cargados
        spPlanetas.setAdapter(adaptador);

    }
}