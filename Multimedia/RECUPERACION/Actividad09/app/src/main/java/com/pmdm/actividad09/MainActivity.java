package com.pmdm.actividad09;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.pmdm.actividad09.activitys.Activity1;
import com.pmdm.actividad09.activitys.Activity2;
import com.pmdm.actividad09.activitys.Activity3;
import com.pmdm.actividad09.activitys.Activity4;

public class MainActivity extends AppCompatActivity {
    private static final int CODIGO_LLAMADA_ACT3 = 0;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        lista = findViewById(R.id.lista);

        String[] elementosLista = getResources().getStringArray(R.array.lista1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.textView, elementosLista);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, Activity1.class));
                        break;
                    case 1:
                        mostrarLista2(lista); // Pasar la referencia del ListView como parámetro
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

    private void mostrarLista2(ListView listView) {
        // Segunda lista con Adapter
        String[] options = {"Sin padding", "Con padding"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.textView, options);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, Activity2.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, Activity3.class));
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
