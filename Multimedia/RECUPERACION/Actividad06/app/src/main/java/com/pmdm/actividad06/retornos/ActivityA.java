package com.pmdm.actividad06.retornos;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.pmdm.actividad06.R;

public class ActivityA extends AppCompatActivity {
    private static final int RESULT_MAIN = 1;
    private static final int RESULT_FINISH = 2;
    private Button btnVolver;
    private Button btnVolverInicio;
    private Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity05);

        // Inicializa los botones
        btnVolver = findViewById(R.id.btnVolver);
        btnVolverInicio = findViewById(R.id.btnVolverInicio);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        // Recibe el color de la Activity anterior y la establece de color de fondo
        int color = getIntent().getIntExtra("color", Color.WHITE);
        View view = findViewById(android.R.id.content);
        view.setBackgroundColor(color);

        // Configura la visibilidad de los botones
        configureButtonVisibility(getIntent().getIntExtra("tipoVista", 0));

        // Configura los clics de los botones
        configureButtonClicks();
    }

    // Método para configurar la visibilidad de los botones
    private void configureButtonVisibility(int tipoVista) {
        switch (tipoVista) {
            case 1:
                btnVolverInicio.setVisibility(View.GONE);
                btnFinalizar.setVisibility(View.GONE);
                break;
            case 2:
                btnFinalizar.setVisibility(View.GONE);
                break;
            case 3:
                // No es necesario modificar la visibilidad
                break;
            default:
                btnVolver.setVisibility(View.GONE);
                btnVolverInicio.setVisibility(View.GONE);
                btnFinalizar.setVisibility(View.GONE);
                break;
        }
    }

    // Método para configurar los clics de los botones
    private void configureButtonClicks() {
        // Volvemos a la Activity anterior terminando la actual
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Finaliza la Activity y la manda al MainActivity
        btnVolverInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_MAIN);
                finish();
            }
        });

        // Finaliza la Activity y la manda al MainActivity, finalizando esta también
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoConfirmacion();
            }
        });
    }

    // Método para mostrar un cuadro de diálogo de confirmación
    private void mostrarDialogoConfirmacion() {
        // Inflar el diseño personalizado para el cuadro de diálogo
        View dialogView = LayoutInflater.from(this).inflate(R.layout.context_menu, null);

        // Obtener referencias a los elementos de la vista
        ImageView icono = dialogView.findViewById(R.id.icon);
        TextView tituloDialogo = dialogView.findViewById(R.id.titulo);
        TextView mensajeDialogo = dialogView.findViewById(R.id.mensaje);

        // Configurar los valores del cuadro de diálogo de confirmación
        icono.setImageResource(R.drawable.icon);
        tituloDialogo.setText("CIERRE DE APP");
        mensajeDialogo.setText("La App se va a cerrar\n¿Está seguro?");

        // Crear el cuadro de diálogo de confirmación
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setPositiveButton("OK", (dialog, which) -> {
                    setResult(RESULT_FINISH);
                    finish();
                })
                .setNegativeButton("NO", (dialog, which) -> dialog.dismiss())
                .show();
    }
}