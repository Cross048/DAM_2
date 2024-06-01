package com.pmdm.proyectofinal.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pmdm.proyectofinal.R;
import com.pmdm.proyectofinal.usuarios.Mascota;
import com.pmdm.proyectofinal.usuarios.UsuariosDBHelper;

import java.util.List;

public class MascotaAdapter extends ArrayAdapter<Mascota> {
    private Context mContext;
    private List<Mascota> mMascotaList;

    public MascotaAdapter(Context context, List<Mascota> mascotaList, UsuariosDBHelper dbHelper) {
        super(context, 0, mascotaList);
        mContext = context;
        mMascotaList = mascotaList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(mContext).inflate(R.layout.item_mascota, parent, false);
        }

        Mascota currentMascota = mMascotaList.get(position);

        // Obtener referencias de las vistas del layout item_mascota.xml
        ImageView imageViewDog = listItemView.findViewById(R.id.imageViewDog);
        TextView textViewDogName = listItemView.findViewById(R.id.textViewDogName);
        TextView textViewDogBreed = listItemView.findViewById(R.id.textViewDogBreed);
        TextView textViewOwnerName = listItemView.findViewById(R.id.textViewOwnerName);

        // Establecer los valores de las vistas con los datos de la mascota actual
        switch (currentMascota.getRaza()) {
            case 0:
                imageViewDog.setImageResource(R.drawable.pic_shiba);
                break;
            case 1:
                imageViewDog.setImageResource(R.drawable.pic_husky);
                break;
            case 2:
                imageViewDog.setImageResource(R.drawable.pic_american);
                break;
            case 3:
                imageViewDog.setImageResource(R.drawable.pic_golden);
                break;
            default:
                imageViewDog.setImageResource(R.drawable.logo); // Imagen por defecto si la raza no coincide
                break;
        }

        textViewDogName.setText(currentMascota.getNombre());

        // Obtener la cadena correspondiente al ID de recurso de la raza
        String[] razas = mContext.getResources().getStringArray(R.array.raza_mascota);
        textViewDogBreed.setText(razas[currentMascota.getRaza()]);

        // Establecer el nombre del propietario en el TextView
        textViewOwnerName.setText(currentMascota.getPropietario());

        // Configurar el clic en el elemento de la lista
        listItemView.setOnClickListener(v -> {
            showPetDetailsDialog(currentMascota);
        });

        return listItemView;
    }

    private void showPetDetailsDialog(Mascota mascota) {
        // Crear un dialog para mostrar los detalles de la mascota
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        String[] razas = mContext.getResources().getStringArray(R.array.raza_mascota);
        builder.setTitle("Detalles de la Mascota");
        builder.setMessage("Nombre: " + mascota.getNombre() + "\n" +
                "Raza: " + razas[mascota.getRaza()] + "\n" +
                "Propietario: " + mascota.getPropietario());
        builder.setPositiveButton("Enviar Correo", (dialog, which) -> {
            if (mascota.getPropietario() != null) {
                sendEmailToOwner(mascota.getPropietario());
            } else {
                Toast.makeText(mContext, "No se pudo obtener el correo electrónico del propietario", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cerrar", null);
        builder.show();
    }

    private void sendEmailToOwner(String email) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { email });
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Interesado en pasear su mascota");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Buenos días, estoy interesado en pasear a su mascota con mucho gusto.");

        try {
            mContext.startActivity(Intent.createChooser(emailIntent, "Enviar correo..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(mContext, "No hay clientes de correo instalados.", Toast.LENGTH_SHORT).show();
        }
    }
}
