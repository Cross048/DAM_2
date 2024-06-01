package com.pmdm.proyectofinal.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pmdm.proyectofinal.R;
import com.pmdm.proyectofinal.usuarios.Usuario;

import java.util.List;

public class PaseadorAdapter extends ArrayAdapter<Usuario> {

    private Context mContext;
    private List<Usuario> mPaseadores;

    public PaseadorAdapter(@NonNull Context context, @NonNull List<Usuario> paseadores) {
        super(context, 0, paseadores);
        mContext = context;
        mPaseadores = paseadores;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(mContext).inflate(R.layout.item_paseador, parent, false);
        }

        final Usuario currentPaseador = getItem(position);

        ImageView imgPerfil = listItemView.findViewById(R.id.imgPerfil);
        TextView tvNombre = listItemView.findViewById(R.id.tvNombre);
        TextView tvApellidos = listItemView.findViewById(R.id.tvApellidos);

        if (currentPaseador != null) {
            tvNombre.setText(currentPaseador.getNombre());
            tvApellidos.setText(currentPaseador.getApellido());
            setProfileImage(currentPaseador.getProfilePic(), imgPerfil);

            listItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPaseadorDetailsDialog(currentPaseador);
                }
            });
        }

        return listItemView;
    }

    private void setProfileImage(int profilePic, ImageView imgPerfil) {
        // Implementa tu lógica para establecer la imagen de perfil aquí
    }

    private void showPaseadorDetailsDialog(Usuario paseador) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Detalles del Paseador");
        builder.setMessage("Nombre: " + paseador.getNombre() + "\n" +
                "Apellido: " + paseador.getApellido() + "\n" +
                "Correo electrónico: " + paseador.getEmail());
        builder.setPositiveButton("Enviar Correo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendEmail(paseador.getEmail());
            }
        });
        builder.setNegativeButton("Cerrar", null);
        builder.show();
    }

    private void sendEmail(String emailAddress) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Asunto del correo");
        intent.putExtra(Intent.EXTRA_TEXT, "Cuerpo del correo");

        try {
            mContext.startActivity(Intent.createChooser(intent, "Enviar correo..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(mContext, "No hay clientes de correo instalados.", Toast.LENGTH_SHORT).show();
        }
    }
}
