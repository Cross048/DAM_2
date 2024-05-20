package com.pmdm.actividad16;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AlumnoAdapter extends ArrayAdapter<Alumno> {
    private Context mContext;
    private List<Alumno> mAlumnos;

    public AlumnoAdapter(@NonNull Context context, @NonNull List<Alumno> alumnos) {
        super(context, 0, alumnos);
        mContext = context;
        mAlumnos = alumnos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        }

        Alumno currentAlumno = mAlumnos.get(position);

        ImageView iconoAlumno = listItemView.findViewById(R.id.iconoAlumno);
        if (currentAlumno.getCurso().equals("ESO")) {
            // Cambia el icono si es alumno cursando la ESO
            iconoAlumno.setImageResource(R.drawable.icono_eso);
        } else {
            // Usa de manera predeterminada el "icono_resto"
            iconoAlumno.setImageResource(R.drawable.icono_resto);
        }

        TextView nombreTextView = listItemView.findViewById(R.id.nombreAlumno);
        nombreTextView.setText(currentAlumno.getNombre());

        TextView cursoTextView = listItemView.findViewById(R.id.cursoAlumno);
        cursoTextView.setText(currentAlumno.getCurso());

        TextView cicloTextView = listItemView.findViewById(R.id.cicloAlumno);
        if (currentAlumno.tieneCiclo()) {
            // Hace visible el pie de página con el Ciclo, en caso de tener Ciclo
            cicloTextView.setText(currentAlumno.getCiclo());
            cicloTextView.setVisibility(View.VISIBLE);
        } else {
            // Desaparece el Ciclo en caso de no ser alumno de Ciclo
            cicloTextView.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
