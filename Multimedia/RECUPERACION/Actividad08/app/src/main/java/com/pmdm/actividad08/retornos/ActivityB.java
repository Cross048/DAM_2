package com.pmdm.actividad08.retornos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pmdm.actividad08.R;

public class ActivityB extends AppCompatActivity {
    private View pickedColorView;
    private RadioButton rbtnSi;
    private RadioButton rbtnNo;
    private Button btnEntendido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity06);

        pickedColorView = findViewById(R.id.vPickedColor);
        rbtnSi = findViewById(R.id.rbtnSi);
        rbtnNo = findViewById(R.id.rbtnNo);
        btnEntendido = findViewById(R.id.btnEntendido);

        // Recibe el color de la Activity anterior y la establece de color de fondo
        int color = getIntent().getIntExtra("color", Color.WHITE);
        pickedColorView.setBackgroundColor(color);

        btnEntendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                if (rbtnNo.isChecked()) {
                    // Indicamos cambiar a transparente
                    returnIntent.putExtra("changeColorToTransparent", true);
                    returnIntent.putExtra("viewId", getIntent().getIntExtra("viewId", -1));
                } else if (rbtnSi.isChecked()){
                    // No hay necesidad de cambiar
                    returnIntent.putExtra("changeColorToTransparent", false);
                    Toast.makeText(getApplicationContext(), "Me alegro que te guste :)", Toast.LENGTH_SHORT).show();
                }
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }

}