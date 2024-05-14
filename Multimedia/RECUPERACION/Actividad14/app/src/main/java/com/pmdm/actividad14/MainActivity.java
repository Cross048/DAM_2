package com.pmdm.actividad14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner spCurso;
    private Spinner spCiclo;
    private TextView tvCiclo;
    private EditText txtNombre;
    private Button btnGuardar;
    private ListView listAlumnos;
    private List<Alumno> alumnosList;
    private AlumnoAdapter alumnosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        // Buscar los elementos por su ID
        spCurso = findViewById(R.id.spCurso);
        spCiclo = findViewById(R.id.spCiclo);
        tvCiclo = findViewById(R.id.tvCiclo);
        txtNombre = findViewById(R.id.txtNombre);
        btnGuardar = findViewById(R.id.btnGuardar);
        listAlumnos = findViewById(R.id.listAlumnos);

        // Adaptador de ListView alumnos
        alumnosList = new ArrayList<>();
        alumnosAdapter = new AlumnoAdapter(this, alumnosList);
        listAlumnos.setAdapter(alumnosAdapter);

        // Adaptador de Spinner curso
        String[] cursos = getResources().getStringArray(R.array.curso);
        ArrayAdapter<String> adapterCursos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cursos);
        adapterCursos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCurso.setAdapter(adapterCursos);

        // Adaptador de Spinner ciclo
        String[] ciclos = getResources().getStringArray(R.array.ciclo);
        ArrayAdapter<String> adapterCiclos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ciclos);
        adapterCiclos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCiclo.setAdapter(adapterCiclos);

        spCurso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2) {
                    // Hacer visible el Spinner de ciclos
                    tvCiclo.setVisibility(View.VISIBLE);
                    spCiclo.setVisibility(View.VISIBLE);
                } else {
                    // Ocultar el Spinner de ciclos
                    tvCiclo.setVisibility(View.GONE);
                    spCiclo.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarAlumno();
            }
        });
    }

    private void guardarAlumno() {
        // Obtener el nombre ingresado en el EditText
        String nombre = txtNombre.getText().toString().trim();

        // Obtener el curso seleccionado en el Spinner
        String curso = spCurso.getSelectedItem().toString();

        // Obtener el ciclo seleccionado en el Spinner de ciclos, si es visible
        String ciclo = "";
        if (tvCiclo.getVisibility() == View.VISIBLE) {
            ciclo = spCiclo.getSelectedItem().toString();
        }

        // Crear un nuevo objeto Alumno con los datos obtenidos
        Alumno nuevoAlumno;
        if (!ciclo.isEmpty()) {
            nuevoAlumno = new Alumno(nombre, curso, ciclo);
        } else {
            nuevoAlumno = new Alumno(nombre, curso);
        }

        // Agregar el nuevo alumno a la lista
        alumnosList.add(nuevoAlumno);

        // Notificar al adaptador que los datos han cambiado
        alumnosAdapter.notifyDataSetChanged();

        // Limpiar el EditText después de guardar
        txtNombre.getText().clear();
    }
}