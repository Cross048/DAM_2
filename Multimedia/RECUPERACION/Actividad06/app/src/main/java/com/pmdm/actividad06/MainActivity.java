package com.pmdm.actividad06;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pmdm.actividad06.activitys.Activity1;
import com.pmdm.actividad06.activitys.Activity2;
import com.pmdm.actividad06.activitys.Activity3;
import com.pmdm.actividad06.activitys.Activity4;

public class MainActivity extends AppCompatActivity {
    private static final int CODIGO_LLAMADA_ACT3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar la barra de acción
        setSupportActionBar(findViewById(R.id.toolbar));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        int id = item.getItemId();

        if (id == R.id.navPantalla1) {
            intent = new Intent(MainActivity.this, Activity1.class);
            return true;
        } else if (id == R.id.navPantalla2) {
            intent = new Intent(MainActivity.this, Activity2.class);
            return true;
        } else if (id == R.id.navPantalla3) {
            intent = new Intent(MainActivity.this, Activity3.class);
            return true;
        } else if (id == R.id.navPantalla4) {
            intent = new Intent(MainActivity.this, Activity4.class);
            return true;
        } else if (id == R.id.navFinalizar) {
            // Finalizar
            finish();
            return true;
        } else if (id == R.id.navAcercaDe) {
            // Acerca de
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    // Espera el resultado de que hay que cerrar la App
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_LLAMADA_ACT3) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }
}
