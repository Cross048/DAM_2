package com.pmdm.actividad15.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pmdm.actividad15.ElementoLista;
import com.pmdm.actividad15.R;

import java.util.List;

public class CustomAdapter2 extends ArrayAdapter<ElementoLista> {

    private Context mContext;
    private int mResource;
    private List<ElementoLista> mObjects;

    public CustomAdapter2(Context context, int resource, List<ElementoLista> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mObjects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        }

        ElementoLista item = mObjects.get(position);

        TextView titleTextView = convertView.findViewById(R.id.titulo);
        ImageView iconImageView = convertView.findViewById(R.id.imageView);

        String title = item.getTitulo();
        int imagen = item.getImagen();

        titleTextView.setText(title);
        switch (imagen) {
            case 0:
                iconImageView.setImageResource(R.drawable.icon6_sin);
                break;
            case 1:
                iconImageView.setImageResource(R.drawable.icon6_con);
                break;
            default:
                break;
        }

        return convertView;
    }
}
