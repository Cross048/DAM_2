package com.pmdm.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int CODIGO_LLAMADA_SEARCH = 1;
    private static final int CODIGO_LLAMADA_PROFILE = 2;
    private static final int RESULT_MAIN = 1;
    private ListView listMain;
    private List<Mascota> mascotas;
    private MascotaAdapter adapter;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        listMain = findViewById(R.id.listMain);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Crear la lista de mascotas y añadir algunos datos de ejemplo
        mascotas = new ArrayList<>();
        mascotas.add(new Mascota("NombrePerro1", "RazaPerro1", "Dueño1"));
        mascotas.add(new Mascota("NombrePerro2", "RazaPerro2", "Dueño2"));
        mascotas.add(new Mascota("NombrePerro3", "RazaPerro3", "Dueño3"));

        // Crear un adaptador personalizado y asignarlo a la lista
        adapter = new MascotaAdapter(this, mascotas);
        listMain.setAdapter(adapter);

        // Establecer la opción predeterminada en el BottomNavigationView
        bottomNavigationView.setSelectedItemId(R.id.action_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    // Manejar la opción Home
                    return true;
                } else if (item.getItemId() == R.id.action_search) {
                    // Manejar la opción Search
                    return true;
                } else if (item.getItemId() == R.id.action_profile) {
                    // Manejar la opción Profile
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra("firstName", getIntent().getStringExtra("firstName"));
                    intent.putExtra("lastName", getIntent().getStringExtra("lastName"));
                    intent.putExtra("userType", getIntent().getIntExtra("userType", -1));
                    startActivityForResult(intent, CODIGO_LLAMADA_PROFILE);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Cambiar la transición
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_LLAMADA_PROFILE) {
            if (resultCode == RESULT_MAIN) {
                // Establecer la opción predeterminada en el BottomNavigationView
                bottomNavigationView.setSelectedItemId(R.id.action_home);
            }
        }
    }
}
