package com.pmdm.actividad18;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "eurovision2021.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Resultado> getAllResultados() {
        List<Resultado> resultados = new ArrayList<>();

        String query = "SELECT * FROM resultados";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String pais = cursor.getString(cursor.getColumnIndex("pais"));
                String interprete = cursor.getString(cursor.getColumnIndex("interprete"));
                String cancion = cursor.getString(cursor.getColumnIndex("cancion"));
                int votosJurado = cursor.getInt(cursor.getColumnIndex("votosJurado"));
                int votosAudiencia = cursor.getInt(cursor.getColumnIndex("votosAudiencia"));

                resultados.add(new Resultado(pais, interprete, cancion, votosJurado, votosAudiencia));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return resultados;
    }

    public List<String> getAllPaises() {
        List<String> paises = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT pais FROM resultados", null);
        if (cursor.moveToFirst()) {
            do {
                String pais = cursor.getString(cursor.getColumnIndex("pais"));
                paises.add(pais);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return paises;
    }

    public List<Resultado> getResultadosPorPais(String nombrePais) {
        List<Resultado> resultados = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM resultados WHERE pais = ?", new String[]{nombrePais});
        if (cursor.moveToFirst()) {
            do {
                String pais = cursor.getString(cursor.getColumnIndex("pais"));
                String interprete = cursor.getString(cursor.getColumnIndex("interprete"));
                String cancion = cursor.getString(cursor.getColumnIndex("cancion"));
                int votosJurado = cursor.getInt(cursor.getColumnIndex("votosJurado"));
                int votosAudiencia = cursor.getInt(cursor.getColumnIndex("votosAudiencia"));

                Resultado resultado = new Resultado(pais, interprete, cancion, votosJurado, votosAudiencia);
                resultados.add(resultado);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return resultados;
    }
}
