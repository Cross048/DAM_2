package com.pmdm.actividad17;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText etNombre;
    private TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        dbHelper = new DatabaseHelper(this);
        etNombre = findViewById(R.id.etNombre);
        Button btnConsulta = findViewById(R.id.btnConsulta);
        tvEmail = findViewById(R.id.tvEmail);

        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarEmail();
            }
        });
    }

    private void consultarEmail() {
        String nombre = etNombre.getText().toString();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT email FROM personas WHERE nombre = ?", new String[]{nombre});
        if (cursor.moveToFirst()) {
            String email = cursor.getString(0);
            tvEmail.setText(email);
        } else {
            tvEmail.setText("No se encontró el email.");
        }
        cursor.close();
    }
}
