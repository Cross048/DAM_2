package com.pmdm.actividad10;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pmdm.actividad10.activitys.Activity1;
import com.pmdm.actividad10.activitys.Activity2;
import com.pmdm.actividad10.activitys.Activity3;
import com.pmdm.actividad10.activitys.Activity4;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int CODIGO_LLAMADA_ACT3 = 0;
    private ListView lista1;
    private ListView lista2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        lista1 = findViewById(R.id.lista1);
        lista2 = findViewById(R.id.lista2);

        String[] lista1array = getResources().getStringArray(R.array.lista1);

        // Utiliza tu adaptador personalizado
        CustomAdapter adapter = new CustomAdapter(
                this, R.layout.list_item, new ArrayList<>(Arrays.asList(lista1array))
        );
        lista1.setAdapter(adapter);

        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, Activity1.class));
                        break;
                    case 1:
                        mostrarLista2(lista1, lista2);
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, Activity4.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void mostrarLista2(ListView lista1, ListView lista2) {
        lista1.setVisibility(View.GONE);
        lista2.setVisibility(View.VISIBLE);

        String[] lista2array = getResources().getStringArray(R.array.lista2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.list_item,R.id.textView, lista2array
        );
        lista2.setAdapter(adapter);

        lista2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, Activity2.class));
                        break;
                    case 1:
                        startActivityForResult(new Intent(MainActivity.this, Activity3.class), CODIGO_LLAMADA_ACT3);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public class CustomAdapter extends ArrayAdapter<String> {

        private Context mContext;
        private int mResource;
        private ArrayList<String> mData;

        public CustomAdapter(Context context, int resource, ArrayList<String> data) {
            super(context, resource, data);
            mContext = context;
            mResource = resource;
            mData = data;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
            }

            // Obtener el elemento actual
            String item = mData.get(position);

            // Obtener la referencia al TextView en el layout personalizado
            TextView textView = convertView.findViewById(R.id.textView);

            // Establecer el texto del TextView
            textView.setText(item);

            // Aquí puedes personalizar más la apariencia del elemento si lo necesitas

            return convertView;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Espera el resultado de que hay que cerrar la App
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_LLAMADA_ACT3) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }
}
