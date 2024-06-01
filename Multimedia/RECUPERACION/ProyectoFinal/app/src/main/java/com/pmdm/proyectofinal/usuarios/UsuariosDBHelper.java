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
    private static final String COLUMN_EMAIL = "email";

    public UsuariosDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public boolean addUser(String username, String password, String nombre, String apellido, int type, int profilePic, String email) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER, username);
        values.put(COLUMN_PASS, password);
        values.put(COLUMN_NAME, nombre);
        values.put(COLUMN_SURNAME, apellido);
        values.put(COLUMN_TYPE, type);
        values.put(COLUMN_PROFILE_PIC, profilePic);
        values.put(COLUMN_EMAIL, email); // Agregar el correo electrónico

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
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)); // Obtener el correo electrónico de la base de datos
                Usuario usuario = new Usuario(username, password, nombre, apellido, type, profilePic, email);
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
            String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)); // Obtener el correo electrónico de la base de datos
            cursor.close();
            db.close();
            return new Usuario(username, password, nombre, apellido, type, profilePic, email);
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
        String[] columns = {COLUMN_USER, COLUMN_PASS, COLUMN_NAME, COLUMN_SURNAME, COLUMN_TYPE, COLUMN_PROFILE_PIC, COLUMN_EMAIL};
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
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)); // Obtener el correo electrónico de la base de datos
                Usuario usuario = new Usuario(username, password, nombre, apellido, userType, profilePic, email);
                userList.add(usuario);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }

    public boolean addPet(String nombre, int raza, String propietario) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("raza", raza);
        values.put("propietario", propietario);

        long result = db.insert("pets", null, values);
        db.close();

        return result != -1;
    }

    public Mascota getMascotaByPropietario(String propietario) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"nombre", "raza"};
        String selection = "propietario = ?";
        String[] selectionArgs = {propietario};
        Cursor cursor = db.query("pets", columns, selection, selectionArgs, null, null, null);
        Mascota mascota = null;
        if (cursor != null && cursor.moveToFirst()) {
            String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            int raza = cursor.getInt(cursor.getColumnIndex("raza"));
            mascota = new Mascota(nombre, raza, propietario);
            cursor.close();
        }
        db.close();
        return mascota;
    }

    public List<Mascota> getAllMascotas() {
        List<Mascota> mascotaList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT pets.nombre AS mascota_nombre, pets.raza, users.nombre AS propietario_nombre, users.apellido AS propietario_apellido " +
                "FROM pets " +
                "INNER JOIN users ON pets.propietario = users.username";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String nombreMascota = cursor.getString(cursor.getColumnIndex("mascota_nombre"));
                int raza = cursor.getInt(cursor.getColumnIndex("raza"));
                String propietarioNombre = cursor.getString(cursor.getColumnIndex("propietario_nombre"));
                String propietarioApellido = cursor.getString(cursor.getColumnIndex("propietario_apellido"));

                // Obtener el nombre completo del propietario
                StringBuilder propietarioBuilder = new StringBuilder();
                propietarioBuilder.append(propietarioNombre);
                propietarioBuilder.append(" ");
                propietarioBuilder.append(propietarioApellido);
                String propietario = propietarioBuilder.toString();

                Mascota mascota = new Mascota(nombreMascota, raza, propietario);
                mascotaList.add(mascota);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mascotaList;
    }

    public String getEmailByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String email = "Correo no encontrado"; // Valor por defecto

        try {
            String query = "SELECT email FROM users WHERE username=?";
            cursor = db.rawQuery(query, new String[]{username});

            if (cursor != null && cursor.moveToFirst()) {
                email = cursor.getString(cursor.getColumnIndex("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return email;
    }

    public Usuario getUsuarioByUsername(String username) {
        SQLiteDatabase db = getReadableDatabase();
        Usuario usuario = null;

        Cursor cursor = db.query("users", new String[]{
                        "username", "password", "nombre",
                        "apellido", "email", "type", "profile_pic"},
                "username=?", new String[]{username}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            usuario = new Usuario(
                    cursor.getString(cursor.getColumnIndex("username")),
                    cursor.getString(cursor.getColumnIndex("password")),
                    cursor.getString(cursor.getColumnIndex("nombre")),
                    cursor.getString(cursor.getColumnIndex("apellido")),
                    cursor.getInt(cursor.getColumnIndex("type")),
                    cursor.getInt(cursor.getColumnIndex("profile_pic")),
                    cursor.getString(cursor.getColumnIndex("email"))
            );
            cursor.close();
        }

        db.close();
        return usuario;
    }
}
