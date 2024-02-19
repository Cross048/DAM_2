package com.example.notificaciones;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtn(View view) {
        switch (view.getId()) {
            case R.id.btn_mensaje1:
                dialogo_mensaje();
                break;
        }
    }

    private void dialogo_mensaje() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        // ventana.setIcon(android.drawable.###)

    }

    private void dialogo_ventana_1boton() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setMessage("Esto es un mensaje de aviso de bla bla")
                .setTitle("Atención!")
                // Imagen que queramos cargar
                // .setIcon(R.drawable.#)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //TODO: Operaciones correspondientes
                        dialog.cancel();
                    }
                })
                .show();
    }
}