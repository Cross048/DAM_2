package com.pmdm.actividad18;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ResultadosAdapter extends ArrayAdapter<Resultado> {

    private Context mContext;
    private int mResource;

    public ResultadosAdapter(Context context, int resource, List<Resultado> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        }

        Resultado resultado = getItem(position);

        TextView tvPais = listItem.findViewById(R.id.tvPais);
        TextView tvInterprete = listItem.findViewById(R.id.tvInterprete);
        TextView tvCancion = listItem.findViewById(R.id.tvCancion);
        Button btnVotosJurado = listItem.findViewById(R.id.btnVotosJurado);
        Button btnVotosAudiencia = listItem.findViewById(R.id.btnVotosAudiencia);
        Button btnTotalVotos = listItem.findViewById(R.id.btnTotalVotos);

        tvPais.setText(resultado.getPais());
        tvInterprete.setText(resultado.getInterprete());
        tvCancion.setText(resultado.getCancion());
        btnVotosJurado.setText(String.valueOf(resultado.getVotosJurado()));
        btnVotosAudiencia.setText(String.valueOf(resultado.getVotosAudiencia()));
        btnTotalVotos.setText(String.valueOf(resultado.getVotosJurado() + resultado.getVotosAudiencia()));

        return listItem;
    }
}
