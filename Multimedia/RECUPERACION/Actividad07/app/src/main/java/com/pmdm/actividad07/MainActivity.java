package com.pmdm.actividad07;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.pmdm.actividad07.activitys.Activity1;
import com.pmdm.actividad07.activitys.Activity2;
import com.pmdm.actividad07.activitys.Activity3;
import com.pmdm.actividad07.activitys.Activity4;

public class MainActivity extends AppCompatActivity {
    private static final int CODIGO_LLAMADA_ACT3 = 0;
    private TextView tvBienvenido;
    private TextView tvJugamos;
    private LinearLayout mainBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        mainBody = findViewById(R.id.mainBody);
        tvBienvenido = findViewById(R.id.tvBienvenido);
        tvJugamos = findViewById(R.id.tvJugamos);

        tvBienvenido.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showPopupMenuBienvenido(v);
                return true;
            }
        });

        tvJugamos.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showPopupMenuJugamos(v);
                return true;
            }
        });
    }

    private void showPopupMenuBienvenido(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.menu_popup01);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_4) {
                    mainBody.setBackgroundResource(R.drawable.background01);
                    return true;
                } else if (itemId == R.id.menu_6) {
                    mainBody.setBackgroundResource(R.drawable.background02);
                    return true;
                } else if (itemId == R.id.menu_9) {
                    mainBody.setBackgroundResource(R.drawable.background03);
                    return true;
                } else {
                    return false;
                }
            }
        });
        popupMenu.show();
    }

    private void showPopupMenuJugamos(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.menu_popup02);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_borrar) {
                    mainBody.setBackground(null);
                    return true;
                } else if (itemId == R.id.menu_negro) {
                    mainBody.setBackgroundColor(Color.BLACK);
                    return true;
                } else {
                    return false;
                }
            }
        });
        popupMenu.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.navPantalla1) {
            startActivity(new Intent(MainActivity.this, Activity1.class));
            return true;
        } else if (id == R.id.navPantalla2) {
            startActivity(new Intent(MainActivity.this, Activity2.class));
            return true;
        } else if (id == R.id.navPantalla3) {
            startActivityForResult(new Intent(MainActivity.this, Activity3.class), CODIGO_LLAMADA_ACT3);
            return true;
        } else if (id == R.id.navPantalla4) {
            startActivity(new Intent(MainActivity.this, Activity4.class));
            return true;
        } else if (id == R.id.navFinalizar) {
            mostrarDialogoConfirmacion();
            return true;
        } else if (id == R.id.navAcercaDe) {
            mostrarDialogoAcercaDe();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
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

    private void mostrarDialogoConfirmacion() {
        // Método para mostrar un cuadro de diálogo de confirmación
        View dialogView = LayoutInflater.from(this).inflate(R.layout.context_menu, null);

        ImageView icono = dialogView.findViewById(R.id.icon);
        TextView tituloDialogo = dialogView.findViewById(R.id.titulo);
        TextView mensajeDialogo = dialogView.findViewById(R.id.mensaje);

        icono.setImageResource(R.drawable.icon);
        tituloDialogo.setText("CIERRE DE APP");
        mensajeDialogo.setText("La App se va a cerrar\n¿Está seguro?");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setPositiveButton("OK", (dialog, which) -> {
                    finish();
                })
                .setNegativeButton("NO", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void mostrarDialogoAcercaDe() {
        // Método para mostrar un cuadro de diálogo de Acerca de
        View dialogView = LayoutInflater.from(this).inflate(R.layout.context_menu, null);

        ImageView icono = dialogView.findViewById(R.id.icon);
        TextView tituloDialogo = dialogView.findViewById(R.id.titulo);
        TextView mensajeDialogo = dialogView.findViewById(R.id.mensaje);

        icono.setImageResource(R.drawable.icon);
        tituloDialogo.setText("Cristian Bernal Méndez");
        mensajeDialogo.setText("2º DAM");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
