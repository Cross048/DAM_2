package com.pmdm.proyectofinal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pmdm.proyectofinal.R;

public class AvatarSelectionAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private int[] mAvatars;

    public AvatarSelectionAdapter(Context context, int[] avatars, String[] avatarNames) {
        super(context, R.layout.item_avatar_selection, avatarNames);
        mContext = context;
        mAvatars = avatars;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(mContext).inflate(R.layout.item_avatar_selection, parent, false);
        }

        // Obtener referencias de las vistas del layout item_avatar_selection.xml
        ImageView avatarImage = listItemView.findViewById(R.id.avatarImage);
        TextView avatarName = listItemView.findViewById(R.id.avatarName);

        // Establecer las imágenes y nombres de avatar en las vistas
        avatarImage.setImageResource(mAvatars[position]);
        avatarName.setText(getItem(position));

        return listItemView;
    }
}
