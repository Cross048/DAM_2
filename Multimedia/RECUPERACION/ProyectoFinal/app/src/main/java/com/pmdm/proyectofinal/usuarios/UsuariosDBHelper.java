package com.pmdm.proyectofinal.usuarios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class UsuariosDBHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "usuarios.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER = "username";
    private static final String COLUMN_PASS = "password";
    private static final String COLUMN_TYPE = "type"; // Nuevo campo para almacenar el tipo de usuario

    public UsuariosDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public boolean addUser(String username, String password, int type) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER, username);
        values.put(COLUMN_PASS, password);
        values.put(COLUMN_TYPE, type);

        long result = db.insert(TABLE_USERS, null, values);
        db.close();

        return result != -1;
    }

    public List<Usuario> getAllUsers() {
        List<Usuario> userList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String username = cursor.getString(cursor.getColumnIndex(COLUMN_USER));
                String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASS));
                int type = cursor.getInt(cursor.getColumnIndex(COLUMN_TYPE));
                Usuario usuario = new Usuario(username, password, type); // Pasar el tipo de usuario al constructor
                userList.add(usuario);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }
}
