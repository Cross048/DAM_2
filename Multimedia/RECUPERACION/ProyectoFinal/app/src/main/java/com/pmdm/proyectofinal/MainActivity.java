package com.pmdm.proyectofinal;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listMain;
    private List<Mascota> mascotas;
    private MascotaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        listMain = findViewById(R.id.listMain);

        // Crear la lista de mascotas y añadir algunos datos de ejemplo
        mascotas = new ArrayList<>();
        mascotas.add(new Mascota("NombrePerro1", "RazaPerro1", "Dueño1"));
        mascotas.add(new Mascota("NombrePerro2", "RazaPerro2", "Dueño2"));
        mascotas.add(new Mascota("NombrePerro3", "RazaPerro3", "Dueño3"));

        // Crear un adaptador personalizado y asignarlo a la lista
        adapter = new MascotaAdapter(this, mascotas);
        listMain.setAdapter(adapter);
    }
}
