package com.pmdm.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pmdm.proyectofinal.usuarios.Usuario;
import com.pmdm.proyectofinal.usuarios.UsuariosDBHelper;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsuario;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etNombre;
    private EditText etApellidos;
    private EditText etNombreMascota;
    private Spinner spinnerType;
    private Spinner spinnerRaza;
    private Button btnRegistrar;
    private UsuariosDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsuario = findViewById(R.id.etUsuario);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        spinnerType = findViewById(R.id.spinnerType);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        etNombreMascota = findViewById(R.id.etNombreMascota);
        spinnerRaza = findViewById(R.id.spinnerRaza);

        dbHelper = new UsuariosDBHelper(this);

        String[] typeOptions = getResources().getStringArray(R.array.type_options);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, typeOptions);
        spinnerType.setAdapter(adapter);

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    etNombreMascota.setVisibility(View.VISIBLE);
                    spinnerRaza.setVisibility(View.VISIBLE);

                    // Cargar el array de raza_mascota en el spinnerRaza
                    String[] razaMascotaOptions = getResources().getStringArray(R.array.raza_mascota);
                    ArrayAdapter<String> razaAdapter = new ArrayAdapter<>(RegisterActivity.this, R.layout.spinner_item, razaMascotaOptions); // Cambio aquí
                    spinnerRaza.setAdapter(razaAdapter);
                } else {
                    etNombreMascota.setVisibility(View.GONE);
                    spinnerRaza.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No hacer nada aquí
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsuario.getText().toString();
                String password = etPassword.getText().toString();
                String nombre = etNombre.getText().toString();
                String apellido = etApellidos.getText().toString();
                final String email = etEmail.getText().toString(); // Obtener el valor del campo email

                int spinnerIndex = spinnerType.getSelectedItemPosition();
                final int type = spinnerIndex == 0 ? 0 : 1;
                int profilePic = 0; // Valor por defecto

                if (!username.isEmpty() && !password.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty()) {
                    Usuario nuevoUsuario = new Usuario(username, password, nombre, apellido, type, profilePic, email);

                    boolean isUserInserted = dbHelper.addUser(
                            nuevoUsuario.getUsername(),
                            nuevoUsuario.getPassword(),
                            nuevoUsuario.getNombre(),
                            nuevoUsuario.getApellido(),
                            nuevoUsuario.getType(),
                            nuevoUsuario.getProfilePic(),
                            nuevoUsuario.getEmail()
                    );

                    if (isUserInserted) {
                        Toast.makeText(RegisterActivity.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();

                        if (type == 1) { // Si el tipo es 1, es decir, es un propietario de mascota
                            final String nombreMascota = etNombreMascota.getText().toString();
                            final int razaMascota = spinnerRaza.getSelectedItemPosition(); // Obtiene la posición seleccionada

                            // Insertar la mascota en la tabla pets
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    boolean isPetInserted = dbHelper.addPet(nombreMascota, razaMascota, username);
                                    if (isPetInserted) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(RegisterActivity.this, "Mascota registrada exitosamente", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    } else {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(RegisterActivity.this, "Error al registrar la mascota", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }
                            }).start();
                        }

                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("username", username);
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right); // Cambiar la transición al retroceder
    }
}
