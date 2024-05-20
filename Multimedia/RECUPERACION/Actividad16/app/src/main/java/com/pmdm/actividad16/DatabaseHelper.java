package com.pmdm.actividad16;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "alumnos.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void addAlumno(Alumno alumno) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO alumnos (nombre, curso, ciclo) VALUES (?, ?, ?)", new Object[]{alumno.getNombre(), alumno.getCurso(), alumno.getCiclo()});
    }

    public void deleteAlumno(String nombre) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM alumnos WHERE nombre = ?", new String[]{nombre});
    }

    public List<Alumno> getAllAlumnos() {
        List<Alumno> alumnos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM alumnos", null);
        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                String curso = cursor.getString(cursor.getColumnIndex("curso"));
                String ciclo = cursor.getString(cursor.getColumnIndex("ciclo"));
                Alumno alumno = new Alumno(nombre, curso, ciclo);
                alumnos.add(alumno);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return alumnos;
    }
}
