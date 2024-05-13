package com.pmdm.actividad11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.pmdm.actividad11.activitys.Activity1;
import com.pmdm.actividad11.activitys.Activity2;
import com.pmdm.actividad11.activitys.Activity3;
import com.pmdm.actividad11.activitys.Activity4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int CODIGO_LLAMADA_ACT3 = 0;
    private ListView lista1;
    private ListView lista2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        lista1 = findViewById(R.id.lista1);
        lista2 = findViewById(R.id.lista2);

        String[] lista1array = getResources().getStringArray(R.array.lista1);
        List<ElementoLista> elementosLista = new ArrayList<>();

        for (String elemento : lista1array) {
            String[] partes = elemento.split("\\|");
            if (partes.length == 3) {
                String titulo = partes[0];
                String cuerpo = partes[1];
                String footer = partes[2];

                ElementoLista elementoLista = new ElementoLista(titulo, cuerpo, footer);
                elementosLista.add(elementoLista);
            }
        }

        CustomAdapter adapter = new CustomAdapter(
                this, R.layout.list_item, elementosLista
        );
        lista1.setAdapter(adapter);

        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, Activity1.class));
                        break;
                    case 1:
                        mostrarLista2(lista1, lista2);
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, Activity4.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void mostrarLista2(ListView lista1, ListView lista2) {
        lista1.setVisibility(View.GONE);
        lista2.setVisibility(View.VISIBLE);

        String[] lista2array = getResources().getStringArray(R.array.lista2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.list_item2,R.id.titulo, lista2array
        );
        lista2.setAdapter(adapter);

        lista2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, Activity2.class));
                        break;
                    case 1:
                        startActivityForResult(new Intent(MainActivity.this, Activity3.class), CODIGO_LLAMADA_ACT3);
                        break;
                    default:
                        break;
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
