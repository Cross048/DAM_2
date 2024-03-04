package com.cristian.repasoexamen2;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerCursos;
    private Spinner spinnerCiclos;
    private final int CICLOS_SPINNER = 2;
    private EditText editNombre;
    private ListView listAlumnos;
    private ArrayAdapter<String> alumnosAdapter;
    private ArrayList<String> listaAlumnos;
    private ArrayList<Integer> listaIconos; // Declara listaIconos aquí

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar referencias
        spinnerCursos = findViewById(R.id.spinnerCursos);
        spinnerCiclos = findViewById(R.id.spinnerCiclos);
        editNombre = findViewById(R.id.editNombre);
        Button btnGuardar = findViewById(R.id.btnGuardar);
        listAlumnos = findViewById(R.id.listAlumnos);

        // Configuración del adaptador de alumnos
        listaAlumnos = new ArrayList<>();
        listaIconos = new ArrayList<>(); // Inicializa listaIconos aquí
        alumnosAdapter = new ArrayAdapter<String>(
                this, R.layout.list_item_layout, R.id.textView, listaAlumnos);
        listAlumnos.setAdapter(alumnosAdapter);

        // Registro de la lista como un view que puede tener un menú contextual
        registerForContextMenu(listAlumnos);

        // Manejo de la selección en el Spinner spinnerCursos
        spinnerCursos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el índice de la opción seleccionada
                int selectedItemIndex = spinnerCursos.getSelectedItemPosition();

                // Si se selecciona la opción deseada, hacer visible el Spinner spinnerCiclos
                spinnerCiclos.setVisibility(selectedItemIndex == CICLOS_SPINNER ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No se realiza ninguna acción si no se selecciona nada
            }
        });

        // Manejo del clic en el botón btnGuardar
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el nombre ingresado
                String nombre = editNombre.getText().toString().trim();

                // Verificar si se ingresó un nombre
                if (!nombre.isEmpty()) {
                    // Obtener el curso seleccionado en el spinnerCursos
                    String cursoSeleccionado = spinnerCursos.getSelectedItem().toString();

                    // Obtener el ciclo seleccionado en el spinnerCiclos si está visible
                    String cicloSeleccionado = "";
                    if (spinnerCiclos.getVisibility() == View.VISIBLE) {
                        cicloSeleccionado = spinnerCiclos.getSelectedItem().toString();
                    }

                    // Determinar el icono según el curso seleccionado
                    int icono = cursoSeleccionado.equals(getString(R.string.curso_eso)) ?
                            R.drawable.icono_eso : R.drawable.icono_resto;

                    // Crear el texto completo a mostrar en el ListView
                    String itemLista = nombre + " - " + cursoSeleccionado;
                    if (!cicloSeleccionado.isEmpty()) {
                        itemLista += " - " + cicloSeleccionado;
                    }

                    // Agregar el texto completo a la lista de alumnos
                    listaAlumnos.add(itemLista);

                    // Agregar el icono correspondiente a la lista de iconos
                    listaIconos.add(icono);

                    // Notificar al adaptador que se ha agregado un nuevo elemento
                    alumnosAdapter.notifyDataSetChanged();

                    // Limpiar el EditText
                    editNombre.setText("");
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        // Verificar si el elemento seleccionado es el de eliminar
        if (item.getItemId() == R.id.action_eliminar) {
            // Lógica para eliminar
            listaAlumnos.remove(position);
            listaIconos.remove(position); // También elimina el icono correspondiente
            alumnosAdapter.notifyDataSetChanged();
            return true;
        }

        // Verificar si el elemento seleccionado es el de otras opciones
        if (item.getItemId() == R.id.action_otras) {
            // Lógica para otras cosas
            return true;
        }

        // Si no coincide con ninguno de los casos anteriores, llamar al método de la superclase
        return super.onContextItemSelected(item);
    }
}