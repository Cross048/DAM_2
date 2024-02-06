package com.example.menus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class AuxiliarActivity extends AppCompatActivity {


    // Inflar mi archivo de xml para el menu de opciones
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
    }


}