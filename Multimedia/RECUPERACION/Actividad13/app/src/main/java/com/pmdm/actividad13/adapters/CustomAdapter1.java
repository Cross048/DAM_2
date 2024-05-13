package com.pmdm.actividad13.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pmdm.actividad13.ElementoLista;
import com.pmdm.actividad13.R;

import java.util.List;

public class CustomAdapter1 extends ArrayAdapter<ElementoLista> {

    private Context mContext;
    private int mResource;
    private List<ElementoLista> mObjects;

    public CustomAdapter1(Context context, int resource, List<ElementoLista> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mObjects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
        }

        ElementoLista item = mObjects.get(position);

        TextView titleTextView = convertView.findViewById(R.id.titulo);
        TextView messageTextView = convertView.findViewById(R.id.cuerpo);
        TextView footerTextView = convertView.findViewById(R.id.footer);
        ImageView iconImageView = convertView.findViewById(R.id.imageView);

        String title = item.getTitulo();
        String message = item.getCuerpo();
        String footer = item.getFooter();
        int imagen = item.getImagen();

        titleTextView.setText(title);
        messageTextView.setText(message);
        footerTextView.setText(footer);
        switch (imagen) {
            case 0:
                iconImageView.setImageResource(R.drawable.icon4);
                break;
            case 1:
                iconImageView.setImageResource(R.drawable.icon6_sin);
                break;
            case 2:
                iconImageView.setImageResource(R.drawable.icon9);
                break;
            default:
                break;
        }

        return convertView;
    }
}
