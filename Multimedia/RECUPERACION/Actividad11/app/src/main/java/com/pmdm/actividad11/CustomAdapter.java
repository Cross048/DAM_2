package com.pmdm.actividad11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<ElementoLista> {

    private Context mContext;
    private int mResource;
    private List<ElementoLista> mObjects;

    public CustomAdapter(Context context, int resource, List<ElementoLista> objects) {
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

        // Obtener los valores de ElementoLista
        String title = item.getTitulo();
        String message = item.getCuerpo();
        String footer = item.getFooter();

        // Establecer los valores en los TextViews
        titleTextView.setText(title);
        messageTextView.setText(message);
        footerTextView.setText(footer);

        return convertView;
    }
}
