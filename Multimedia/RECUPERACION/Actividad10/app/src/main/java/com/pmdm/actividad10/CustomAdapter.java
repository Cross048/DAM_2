package com.pmdm.actividad10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private int mResource;
    private List<String> mObjects;

    public CustomAdapter(Context context, int resource, List<String> objects) {
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

        String item = mObjects.get(position);

        TextView titleTextView = convertView.findViewById(R.id.titulo);
        TextView messageTextView = convertView.findViewById(R.id.cuerpo);
        TextView footerTextView = convertView.findViewById(R.id.footer);

        // Dividir la cadena en título, mensaje y pie de página
        String[] parts = item.split("\\|");
        String title = parts[0];
        String message = parts[1];
        String footer = parts[2];

        // Establecer los valores en los TextViews
        titleTextView.setText(title);
        messageTextView.setText(message);
        footerTextView.setText(footer);

        return convertView;
    }
}
