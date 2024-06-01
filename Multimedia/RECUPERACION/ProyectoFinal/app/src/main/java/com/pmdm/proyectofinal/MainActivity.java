package com.pmdm.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pmdm.proyectofinal.usuarios.Usuario;
import com.pmdm.proyectofinal.usuarios.UsuariosDBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int CODIGO_LLAMADA_SEARCH = 1;
    private static final int CODIGO_LLAMADA_PROFILE = 2;
    private static final int RESULT_MAIN = 1;
    private ListView listMain;
    private BottomNavigationView bottomNavigationView;
    private UsuariosDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        listMain = findViewById(R.id.listMain);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        dbHelper = new UsuariosDBHelper(this);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String nombre = intent.getStringExtra("nombre");
        String apellido = intent.getStringExtra("apellido");
        int type = intent.getIntExtra("type", -1);
        int profile_pic = intent.getIntExtra("profile_pic", 0);

        loadUsersWithSpecificType();

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
                    intent.putExtra("username", username);
                    intent.putExtra("nombre", nombre);
                    intent.putExtra("apellido", apellido);
                    intent.putExtra("type", type);
                    intent.putExtra("profile_pic", profile_pic);
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
                loadUsersWithSpecificType();
            }
        }
    }

    private void loadUsersWithSpecificType() {
        List<Usuario> userList = dbHelper.getAllUsersWithType(0); // Obtener todos los usuarios con type=0

        // Crear un adaptador personalizado para los paseadores y asignarlo a la lista
        PaseadorAdapter paseadorAdapter = new PaseadorAdapter(this, userList);
        listMain.setAdapter(paseadorAdapter);
    }
}
