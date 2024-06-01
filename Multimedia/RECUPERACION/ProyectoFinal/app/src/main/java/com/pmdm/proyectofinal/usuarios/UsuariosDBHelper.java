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
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER = "username";
    private static final String COLUMN_PASS = "password";
    private static final String COLUMN_NAME = "nombre";
    private static final String COLUMN_SURNAME = "apellido";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_PROFILE_PIC = "profile_pic";

    public UsuariosDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public boolean addUser(String username, String password, String nombre, String apellido, int type, int profilePic) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER, username);
        values.put(COLUMN_PASS, password);
        values.put(COLUMN_NAME, nombre);
        values.put(COLUMN_SURNAME, apellido);
        values.put(COLUMN_TYPE, type);
        values.put(COLUMN_PROFILE_PIC, profilePic);

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
                String nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String apellido = cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME));
                int type = cursor.getInt(cursor.getColumnIndex(COLUMN_TYPE));
                int profilePic = cursor.getInt(cursor.getColumnIndex(COLUMN_PROFILE_PIC));
                Usuario usuario = new Usuario(username, password, nombre, apellido, type, profilePic);
                userList.add(usuario);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }

    public Usuario getUserByUsername(String username) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_USER + " = ?", new String[]{username}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASS));
            String nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String apellido = cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME));
            int type = cursor.getInt(cursor.getColumnIndex(COLUMN_TYPE));
            int profilePic = cursor.getInt(cursor.getColumnIndex(COLUMN_PROFILE_PIC));
            cursor.close();
            db.close();
            return new Usuario(username, password, nombre, apellido, type, profilePic);
        } else {
            db.close();
            return null;
        }
    }

    public void updateProfilePic(String username, int profilePic) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PROFILE_PIC, profilePic);
        db.update(TABLE_USERS, values, COLUMN_USER + " = ?", new String[]{username});
        db.close();
    }

    public List<Usuario> getAllUsersWithType(int type) {
        List<Usuario> userList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {COLUMN_USER, COLUMN_PASS, COLUMN_NAME, COLUMN_SURNAME, COLUMN_TYPE, COLUMN_PROFILE_PIC};
        String selection = COLUMN_TYPE + " = ?";
        String[] selectionArgs = {String.valueOf(type)};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String username = cursor.getString(cursor.getColumnIndex(COLUMN_USER));
                String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASS));
                String nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String apellido = cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME));
                int userType = cursor.getInt(cursor.getColumnIndex(COLUMN_TYPE));
                int profilePic = cursor.getInt(cursor.getColumnIndex(COLUMN_PROFILE_PIC));
                Usuario usuario = new Usuario(username, password, nombre, apellido, userType, profilePic);
                userList.add(usuario);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }
}
