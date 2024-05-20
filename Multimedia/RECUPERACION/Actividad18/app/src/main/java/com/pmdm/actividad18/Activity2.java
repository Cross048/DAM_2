package com.pmdm.actividad18;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Activity2 extends AppCompatActivity {
    private ListView listPais;
    private DatabaseHelper databaseHelper;
    private ArrayAdapter<Resultado> adapter;
    private List<Resultado> resultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        setSupportActionBar(findViewById(R.id.toolbar));

        listPais = findViewById(R.id.listPais);

        databaseHelper = new DatabaseHelper(this);

        List<Resultado> resultados = databaseHelper.getAllResultados();

        if (resultados.size() > 0) {
            ResultadosAdapter adapter = new ResultadosAdapter(Activity2.this, R.layout.item_resultado2, resultados);
            listPais.setAdapter(adapter);
        } else {
            Toast.makeText(Activity2.this, "No hay datos disponibles", Toast.LENGTH_SHORT).show();
        }
    }
}

