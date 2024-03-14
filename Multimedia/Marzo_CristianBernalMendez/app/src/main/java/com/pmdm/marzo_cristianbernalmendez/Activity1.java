package com.pmdm.marzo_cristianbernalmendez;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity1 extends AppCompatActivity {
    private ListView listCompuestos;
    private ArrayAdapter<String> listaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        listCompuestos= findViewById(R.id.listCompuestos);

        listaAdapter = new ArrayAdapter<String>(
                this, R.layout.list_item_layout, R.id.textView,
                getResources().getStringArray(R.array.compuestos));

        listCompuestos.setAdapter(listaAdapter);

        listCompuestos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Activity1.this, Activity2.class);
                String seleccion = parent.getItemAtPosition(position).toString();
                String compuesto = seleccion;
                intent.putExtra("mensaje", compuesto);
                startActivity(intent);
            }
        });

        // He intentado la base de datos y no paraba de darme fallos, lo que usé arrays al final

        /*
        BaseDeDatos cargarBD = new BaseDeDatos(this, "BDCompuestos", null, 1);
        SQLiteDatabase db = cargarBD.getReadableDatabase();

        String[] datosARecuperar={"compuesto", "formula"};
        Cursor c = db.query("compuestos", datosARecuperar, null,null,null,null,null,null);
        if (c.moveToFirst()) { //significa que se ha recuperado algo en la consulta
            //recorremos el cursor hasta que no haya más registros
            do {
                String compuesto = c.getString(0);
                listaCompuestos.add(compuesto);
                String formula = c.getString(1);
                listaFormulas.add(formula);
            } while (c.moveToNext());
        }
        */
    }
}
