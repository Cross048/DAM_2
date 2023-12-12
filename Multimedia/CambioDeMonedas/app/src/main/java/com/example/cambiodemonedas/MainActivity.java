package com.example.cambiodemonedas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGrupo;
    private TextView txtCambioMoneda;
    private Button botonCambio;
    private EditText editMonedas;
    private RadioButton rbtaEuros, rbtaPesetas;
    private String stringEditmonedas;
    private double doubleEditmonedas, resultado;
    private double aEuros = 0.006;
    private double aPesetas = 166.386;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCambioMoneda = findViewById(R.id.txtCambiodemoneda);
        radioGrupo = findViewById(R.id.radioGroup);
        editMonedas = findViewById(R.id.editTxtMonedas);
        rbtaEuros = findViewById(R.id.radBtnaEuros);
        rbtaPesetas = findViewById(R.id.radBtnaPesetas);
        botonCambio = findViewById(R.id.btnCambiar);

        botonCambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringEditmonedas = editMonedas.getText().toString();
                if (radioGrupo.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MainActivity.this, "Selecciona el cambio que quieres hacer", Toast.LENGTH_SHORT).show();
                } else if (stringEditmonedas.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Introduce un valor", Toast.LENGTH_SHORT).show();
                } else {
                    doubleEditmonedas = Double.parseDouble(stringEditmonedas);
                    if (rbtaEuros.isChecked()) {
                        resultado = doubleEditmonedas * aEuros;
                        Intent intent = new Intent(MainActivity.this, Activity2.class);
                        String monedasCambiadas = doubleEditmonedas + " pesetas son "+ resultado + " euros";
                        intent.putExtra("mensaje", monedasCambiadas);
                        startActivity(intent);
                    } else if (rbtaPesetas.isChecked()) {
                        resultado = doubleEditmonedas * aPesetas;
                        Intent intent = new Intent(MainActivity.this, Activity2.class);
                        String monedasCambiadas = doubleEditmonedas + " euros son " + resultado + " pesetas";
                        intent.putExtra("mensaje", monedasCambiadas);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
