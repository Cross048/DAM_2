package com.pmdm.proyectofinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pmdm.proyectofinal.usuarios.Usuario;

import java.util.List;

public class PaseadorAdapter extends ArrayAdapter<Usuario> {

    public PaseadorAdapter(@NonNull Context context, @NonNull List<Usuario> paseadores) {
        super(context, 0, paseadores);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_paseador, parent, false);
        }

        Usuario currentPaseador = getItem(position);

        ImageView imgPerfil = listItemView.findViewById(R.id.imgPerfil);
        TextView tvNombre = listItemView.findViewById(R.id.tvNombre);
        TextView tvApellidos = listItemView.findViewById(R.id.tvApellidos);

        if (currentPaseador != null) {
            tvNombre.setText(currentPaseador.getNombre());
            tvApellidos.setText(currentPaseador.getApellido());
            setProfileImage(currentPaseador.getProfilePic(), imgPerfil);
        }

        return listItemView;
    }

    private void setProfileImage(int profilePic, ImageView imgPerfil) {
        switch (profilePic) {
            case 0:
                imgPerfil.setImageResource(R.drawable.pic_boy);
                break;
            case 1:
                imgPerfil.setImageResource(R.drawable.pic_girl);
                break;
            case 2:
                imgPerfil.setImageResource(R.drawable.pic_man);
                break;
            case 3:
                imgPerfil.setImageResource(R.drawable.pic_woman);
                break;
            default:
                imgPerfil.setImageResource(R.drawable.pic_boy); // Imagen por defecto
                break;
        }
    }
}
