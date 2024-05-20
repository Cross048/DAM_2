package com.pmdm.actividad18;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class Activity1 extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private ArrayAdapter<Resultado> adapter;
    private List<Resultado> resultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        setSupportActionBar(findViewById(R.id.toolbar));

        databaseHelper = new DatabaseHelper(this);

        AutoCompleteTextView txtPais = findViewById(R.id.txtPais);
        Button btnComprobar = findViewById(R.id.btnComprobar);
        ListView listPais = findViewById(R.id.listPais);

        // Obtener todos los países de la base de datos
        List<String> paises = databaseHelper.getAllPaises();
        ArrayAdapter<String> autoCompleteAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, paises);
        txtPais.setAdapter(autoCompleteAdapter);

        // Configurar el listener de clics para el botón Comprobar
        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el nombre del país ingresado
                String nombrePais = txtPais.getText().toString().trim();

                // Buscar el país en la base de datos
                resultados = databaseHelper.getResultadosPorPais(nombrePais);

                // Mostrar los resultados en el ListView
                adapter = new ResultadosAdapter(Activity1.this, R.layout.item_resultado, resultados);
                listPais.setAdapter(adapter);
            }
        });
    }
}

