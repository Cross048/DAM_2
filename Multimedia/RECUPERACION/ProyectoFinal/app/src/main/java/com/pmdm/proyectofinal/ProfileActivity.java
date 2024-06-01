package com.pmdm.proyectofinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pmdm.proyectofinal.usuarios.Usuario;
import com.pmdm.proyectofinal.usuarios.UsuariosDBHelper;

public class ProfileActivity extends AppCompatActivity {
    private static final int RESULT_MAIN = 1;
    private String username;
    private int profile_pic;
    private ImageView imgPerfil;
    private TextView tvNombre;
    private TextView tvApellidos;
    private TextView tvType;
    private BottomNavigationView bottomNavigationView;
    private UsuariosDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setSupportActionBar(findViewById(R.id.toolbar));

        imgPerfil = findViewById(R.id.imgPerfil);
        tvNombre = findViewById(R.id.tvNombre);
        tvApellidos = findViewById(R.id.tvApellidos);
        tvType = findViewById(R.id.tvType);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        dbHelper = new UsuariosDBHelper(this);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        String nombre = intent.getStringExtra("nombre");
        String apellido = intent.getStringExtra("apellido");
        int type = intent.getIntExtra("type", -1);
        profile_pic = intent.getIntExtra("profile_pic", 0);

        tvNombre.setText(nombre);
        tvApellidos.setText(apellido);
        tvType.setText(getUserTypeString(type));
        setProfileImage(profile_pic);

        imgPerfil.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showAvatarSelectionDialog();
                return true;
            }
        });

        // Establecer la opción predeterminada en el BottomNavigationView
        bottomNavigationView.setSelectedItemId(R.id.action_profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    setResult(RESULT_MAIN);
                    finish();
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right); // Cambiar la transición
                    return true;
                } else if (item.getItemId() == R.id.action_search) {
                    return true;
                } else if (item.getItemId() == R.id.action_profile) {
                }
                return false;
            }
        });
    }

    private void setProfileImage(int profilePic) {
        switch (profilePic) {
            case 0:
                imgPerfil.setImageResource(R.drawable.pic_boy);
                break;
            case 1:
                imgPerfil.setImageResource(R.drawable.pic_girl);
                break;
            case 2:
                imgPerfil.setImageResource(R.drawable.pic_man);
                break;
            case 3:
                imgPerfil.setImageResource(R.drawable.pic_woman);
                break;
            default:
                imgPerfil.setImageResource(R.drawable.pic_boy); // Imagen por defecto
                break;
        }
    }

    private void showAvatarSelectionDialog() {
        // Crear un nuevo AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccione un avatar");

        // Configurar el diálogo para mostrar los avatares y manejar la selección
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_avatar_selection, null);
        builder.setView(dialogView);

        // Obtener referencias a las ImageView dentro del diálogo
        ImageView imageViewAvatar1 = dialogView.findViewById(R.id.imageViewAvatar1);
        ImageView imageViewAvatar2 = dialogView.findViewById(R.id.imageViewAvatar2);
        ImageView imageViewAvatar3 = dialogView.findViewById(R.id.imageViewAvatar3);
        ImageView imageViewAvatar4 = dialogView.findViewById(R.id.imageViewAvatar4);

        // Crear el diálogo
        AlertDialog dialog = builder.create();

        // Configurar un OnClickListener para cada ImageView
        imageViewAvatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAvatar(0); // Actualizar el avatar del usuario a 0
                dialog.dismiss();
            }
        });

        imageViewAvatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAvatar(1); // Actualizar el avatar del usuario a 1
                dialog.dismiss();
            }
        });

        imageViewAvatar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAvatar(2); // Actualizar el avatar del usuario a 2
                dialog.dismiss();
            }
        });

        imageViewAvatar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAvatar(3); // Actualizar el avatar del usuario a 3
                dialog.dismiss();
            }
        });

        // Configurar un OnClickListener para manejar la selección del avatar
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Mostrar el diálogo
        dialog.show();
    }

    private void updateAvatar(int newAvatar) {
        // Actualizar el avatar del usuario en la base de datos
        dbHelper.updateProfilePic(username, newAvatar);
        // Actualizar la imagen del perfil en la actividad
        profile_pic = newAvatar;
        setProfileImage(profile_pic);
    }

    private String getUserTypeString(int userType) {
        switch (userType) {
            case 0:
                return "Paseador";
            case 1:
                return "Cliente";
            default:
                return "Admin";
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Usuario user = dbHelper.getUserByUsername(username);
        if (user != null) {
            profile_pic = user.getProfilePic();
            setProfileImage(profile_pic);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_MAIN);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right); // Cambiar la transición
    }
}
