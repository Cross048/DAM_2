package com.pmdm.actividad18;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnVerTodo;
    private ListView listView;
    private LinearLayout linearLayout;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        btnVerTodo = findViewById(R.id.btnVerTodo);
        listView = findViewById(R.id.listView);

        databaseHelper = new DatabaseHelper(this);

        btnVerTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnVerTodo.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);

                List<Resultado> resultados = databaseHelper.getAllResultados();

                if (resultados.size() > 0) {
                    ResultadosAdapter adapter = new ResultadosAdapter(MainActivity.this, R.layout.item_resultado, resultados);
                    listView.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "No hay datos disponibles", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuDatosPais) {
            Intent intent = new Intent(MainActivity.this, Activity1.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menuVotosPais) {
            Intent intent = new Intent(MainActivity.this, Activity2.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
