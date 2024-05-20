package com.pmdm.actividad16;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        // ID de elementos
        spCurso = findViewById(R.id.spCurso);
        spCiclo = findViewById(R.id.spCiclo);
        tvCiclo = findViewById(R.id.tvCiclo);
        txtNombre = findViewById(R.id.txtNombre);
        btnGuardar = findViewById(R.id.btnGuardar);
        listAlumnos = findViewById(R.id.listAlumnos);

        // Inicializar DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Cargar alumnos desde la base de datos
        alumnosList = databaseHelper.getAllAlumnos();
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
                // En caso de no seleccionar nada. Método por defecto
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre.getText().toString().trim();
                if (!nombre.isEmpty()) {
                    // Método para guardar el Alumno en la Lista y base de datos
                    guardarAlumno();
                } else {
                    // El usuario no introdujo ningún nombre
                    mostrarDialogoError("Es necesario introducir un nombre");
                }
            }
        });

        listAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Alumno alumnoSeleccionado = alumnosList.get(position);
                mostrarDialogoAlumno(alumnoSeleccionado);
            }
        });

        listAlumnos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Alumno alumnoSeleccionado = alumnosList.get(position);

                // Creamos un AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(alumnoSeleccionado.getNombre());

                // Definimos las opciones del Dialog
                String[] opciones = {"Borrar", "Otros"};
                builder.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                // Opción Borrar
                                mostrarDialogoConfirmacionEliminar(alumnoSeleccionado);
                                break;
                            case 1:
                                // Opción Otros
                                break;
                        }
                    }
                });

                // Crea el AlertDialog y lo muestra al usuario
                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            }
        });
    }

    private void guardarAlumno() {
        // Obtenemos los datos del alumno
        String nombre = txtNombre.getText().toString().trim();
        String curso = spCurso.getSelectedItem().toString();
        String ciclo = "";
        if (tvCiclo.getVisibility() == View.VISIBLE) {
            // Si es visible el TextView Ciclo, entonces obtenemos los datos del Spinner
            ciclo = spCiclo.getSelectedItem().toString();
        }

        // Creamos un nuevo Alumno con los datos obtenidos
        Alumno nuevoAlumno;
        if (!ciclo.isEmpty()) {
            // En caso de tener Ciclo
            nuevoAlumno = new Alumno(nombre, curso, ciclo);
        } else {
            // Si no, usamos el otro constructor
            nuevoAlumno = new Alumno(nombre, curso);
        }

        // Agregamos el nuevo alumno a la lista alumnosList y base de datos
        alumnosList.add(nuevoAlumno);
        alumnosAdapter.notifyDataSetChanged();
        databaseHelper.addAlumno(nuevoAlumno);

        // Mostrar dialogo de confirmación
        mostrarDialogoAlumnoGuardado(nuevoAlumno.getNombre(), nuevoAlumno.getCurso(), nuevoAlumno.getCiclo());

        // Limpia el EditText Nombre después de guardar los datos
        txtNombre.getText().clear();
    }

    private void eliminarAlumno(Alumno alumno) {
        // Eliminar alumno de la lista y base de datos
        alumnosList.remove(alumno);
        alumnosAdapter.notifyDataSetChanged();
        databaseHelper.deleteAlumno(alumno.getNombre());
    }

    private void mostrarDialogoAlumnoGuardado(String nombre, String curso, String ciclo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alumno Guardado");

        // Establece el ícono basado en el curso
        if ("ESO".equals(curso)) {
            builder.setIcon(R.drawable.icono_eso);
        } else {
            builder.setIcon(R.drawable.icono_resto);
        }

        String mensaje = "Nombre: " + nombre + "\nCurso: " + curso;
        if (ciclo != null) {
            mensaje += "\nCiclo: " + ciclo;
        }
        builder.setMessage(mensaje);
        builder.setPositiveButton("OK", null);
        builder.show();
    }


    private void mostrarDialogoError(String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage(mensaje);
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    private void mostrarDialogoAlumno(Alumno alumno) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alumno.getNombre());

        // Establece el ícono basado en el curso
        if ("ESO".equals(alumno.getCurso())) {
            builder.setIcon(R.drawable.icono_eso);
        } else {
            builder.setIcon(R.drawable.icono_resto);
        }

        String mensaje = "Curso: " + alumno.getCurso();
        if (alumno.tieneCiclo()) {
            mensaje += "\nCiclo: " + alumno.getCiclo();
        }
        builder.setMessage(mensaje);
        builder.setPositiveButton("OK", null);
        builder.show();
    }


    private void mostrarDialogoConfirmacionEliminar(final Alumno alumno) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar Alumno");
        builder.setMessage("¿Estás seguro de que deseas eliminar a " + alumno.getNombre() + "?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                eliminarAlumno(alumno);
                Toast.makeText(MainActivity.this, "Alumno eliminado", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
}
