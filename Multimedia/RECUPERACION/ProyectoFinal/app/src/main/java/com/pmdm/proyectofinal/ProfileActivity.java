package com.pmdm.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    private static final int RESULT_MAIN = 1;
    private ImageView imgPerfil;
    private TextView tvNombre;
    private TextView tvApellidos;
    private TextView tvType;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setSupportActionBar(findViewById(R.id.toolbar));

        imgPerfil = findViewById(R.id.imgPerfil);
        tvNombre = findViewById(R.id.tvNombre);
        tvApellidos = findViewById(R.id.tvApellidos);
        tvType = findViewById(R.id.tvType);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Obtener los datos del Intent
        Intent intent = getIntent();
        String firstNameStr = intent.getStringExtra("firstName");
        String lastNameStr = intent.getStringExtra("lastName");
        int userTypeInt = intent.getIntExtra("userType", -1);

        // Actualizar las vistas con los datos del usuario
        tvNombre.setText(firstNameStr);
        tvApellidos.setText(lastNameStr);
        tvType.setText(getUserTypeString(userTypeInt));

        // Aquí puedes cargar una imagen de perfil si tienes una URL o un recurso específico
        // profileImage.setImageResource(R.drawable.ic_profile_placeholder);

        // Establecer la opción predeterminada en el BottomNavigationView
        bottomNavigationView.setSelectedItemId(R.id.action_profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    // Manejar la opción Home
                    setResult(RESULT_MAIN);
                    finish();
                    finish();
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Cambiar la transición
                    return true;
                } else if (item.getItemId() == R.id.action_search) {
                    // Manejar la opción Search
                    return true;
                } else if (item.getItemId() == R.id.action_profile) {
                    // Manejar la opción Profile
                }
                return false;
            }
        });
    }

    private String getUserTypeString(int userType) {
        switch (userType) {
            case 0:
                return "Paseador";
            case 1:
                return "Cliente";
            default:
                return "Admin";
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right); // Cambiar la transición al retroceder
    }
}