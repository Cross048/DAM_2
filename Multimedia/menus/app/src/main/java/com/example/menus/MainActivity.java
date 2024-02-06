package com.example.menus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AuxiliarActivity {
    TextView textViewHolaMundo;
    ImageView imageViewVaca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewHolaMundo = findViewById(R.id.txtView_hola_mundo);
        imageViewVaca = findViewById(R.id.img_vaca);
        // Vamos a asociar el menu contextual a la text view
        registerForContextMenu(textViewHolaMundo);
        registerForContextMenu(imageViewVaca);

    }

/*    // Inflar mi archivo de xml para el menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_opciones, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // CREACIONE DEL LISTENER PARA EL MENU
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opc_item_1:
                Toast.makeText(this, "Has elegido el 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_item_1_1:
                Toast.makeText(this, "Has elegido el 1 SUB 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_item_1_2:
                Toast.makeText(this, "Has elegido el 1 SUB 2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_item_2:
                Toast.makeText(this, "Has elegido el 2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_item_3:
                Toast.makeText(this, "Has elegido el 3", Toast.LENGTH_SHORT).show();
                return true;

        }


        return super.onOptionsItemSelected(item);
    }*/
    // Inflar mi archivo de xml para el menu de opciones contextual

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        if(v.getId() == R.id.txtView_hola_mundo){
            inflater.inflate(R.menu.menu_contextual,menu);
        }else{
            inflater.inflate(R.menu.menu_contextual_vaca,menu);
        }
    }

    // CREACIONE DEL LISTENER PARA EL MENU
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opc_item_1_contextual:
                Toast.makeText(this, "Has elegido el contextual 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_item_2_contextual:
                Toast.makeText(this, "Has elegido el contextual 2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_item_1_contextual_vaca:
                Toast.makeText(this, "Has elegido el contextual 1 vaca", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_item_2_contextual_vaca:
                Toast.makeText(this, "Has elegido el contextual 2 vaca", Toast.LENGTH_SHORT).show();
                return true;

        }
        return super.onContextItemSelected(item);
    }

    public void onClickBoton(View view) {
        Intent intent = new Intent();
        intent.setClass(this,Second_Activity.class);
        startActivity(intent);
    }
}
