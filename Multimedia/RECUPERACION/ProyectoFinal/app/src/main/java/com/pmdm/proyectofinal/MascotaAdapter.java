package com.pmdm.proyectofinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MascotaAdapter extends ArrayAdapter<Mascota> {
    private Context mContext;
    private List<Mascota> mMascotaList;

    public MascotaAdapter(Context context, List<Mascota> mascotaList) {
        super(context, 0, mascotaList);
        mContext = context;
        mMascotaList = mascotaList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(mContext).inflate(R.layout.item_mascota, parent, false);
        }

        Mascota currentMascota = mMascotaList.get(position);

        // Obtener referencias de las vistas del layout item_mascota.xml
        ImageView imageViewDog = listItemView.findViewById(R.id.imageViewDog);
        TextView textViewDogName = listItemView.findViewById(R.id.textViewDogName);
        TextView textViewDogBreed = listItemView.findViewById(R.id.textViewDogBreed);
        TextView textViewOwnerName = listItemView.findViewById(R.id.textViewOwnerName);

        // Establecer los valores de las vistas con los datos de la mascota actual
        imageViewDog.setImageResource(R.drawable.logo); // Reemplazar por la imagen real de la mascota si tienes
        textViewDogName.setText(currentMascota.getNombre());
        textViewDogBreed.setText(currentMascota.getRaza());
        textViewOwnerName.setText(currentMascota.getNombreDueno());

        return listItemView;
    }
}
