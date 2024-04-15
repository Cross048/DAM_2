package com.pmdm.actividad05;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.pmdm.actividad05.activitys.Activity1;
import com.pmdm.actividad05.activitys.Activity2;
import com.pmdm.actividad05.activitys.Activity3;
import com.pmdm.actividad05.activitys.Activity4;

public class MainActivity extends AppCompatActivity {
    private static final int CODIGO_LLAMADA_ACT3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialización de botones
        View btn1 = findViewById(R.id.btn1);
        View btn2 = findViewById(R.id.btn2);
        View btn3 = findViewById(R.id.btn3);
        View btn4 = findViewById(R.id.btn4);

        // Registrar los botones para el menú contextual
        registerForContextMenu(btn1);
        registerForContextMenu(btn2);
        registerForContextMenu(btn3);
        registerForContextMenu(btn4);

        // Manejar el clic largo de los botones
        btn1.setOnLongClickListener(v -> {
            mostrarDialogo("PANTALLA 1", "Pantalla con 4 colores\n" +
                    "Cada color lleva a una pantalla con retroceso");
            return true;
        });

        btn2.setOnLongClickListener(v -> {
            mostrarDialogo("PANTALLA 2", "Pantalla con 6 colores\n" +
                    "Cada color lleva a una pantalla con retroceso o vuelta al inicio");
            return true;
        });

        btn3.setOnLongClickListener(v -> {
            mostrarDialogo("PANTALLA 3", "Pantalla con 6 colores y padding\n" +
                    "Cada color lleva a una pantalla con retroceso, vuelta al inicio o fin de app");
            return true;
        });

        btn4.setOnLongClickListener(v -> {
            mostrarDialogo("PANTALLA 4", "Pantalla con 9 colores\n" +
                    "Cada color lleva a una pantalla que permite eliminar dicho color");
            return true;
        });

        // Manejar el clic simple de los botones
        btn1.setOnClickListener(this::handleButtonClick);
        btn2.setOnClickListener(this::handleButtonClick);
        btn3.setOnClickListener(this::handleButtonClick);
        btn4.setOnClickListener(this::handleButtonClick);
    }


    // Mostrar cuadro de diálogo con el mensaje
    private void mostrarDialogo(String titulo, String mensaje) {
        // Inflar el diseño personalizado
        View dialogView = getLayoutInflater().inflate(R.layout.context_menu, null);

        // Obtener referencias a los elementos de la vista
        TextView tituloDialogo = dialogView.findViewById(R.id.titulo);
        TextView mensajeDialogo = dialogView.findViewById(R.id.mensaje);

        // Configurar los valores
        tituloDialogo.setText(titulo);
        mensajeDialogo.setText(mensaje);

        // Crear el cuadro de diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView).show();
    }

    private void handleButtonClick(View view) {
        Intent intent = null;
        boolean isStartActivityForResult = false;

        int id = view.getId();
        if (id == R.id.btn1) {
            intent = new Intent(MainActivity.this, Activity1.class);
        } else if (id == R.id.btn2) {
            intent = new Intent(MainActivity.this, Activity2.class);
        } else if (id == R.id.btn3) {
            intent = new Intent(MainActivity.this, Activity3.class);
            isStartActivityForResult = true;
        } else if (id == R.id.btn4) {
            intent = new Intent(MainActivity.this, Activity4.class);
        }

        if (intent != null) {
            if (isStartActivityForResult) {
                startActivityForResult(intent, CODIGO_LLAMADA_ACT3);
            } else {
                startActivity(intent);
            }
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
