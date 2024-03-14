package com.pmdm.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDeDatos extends SQLiteOpenHelper {
    private String sentenciaCreate = "CREATE TABLE compuestos compuesto VARCHAR (20) PRIMARY KEY NOT NULL, formula   VARCHAR (5)  NOT NULL)";

    //constructor
    public BaseDeDatos(@Nullable Context context, @Nullable String name,
                       @Nullable SQLiteDatabase.CursorFactory factory,
                       int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //operaciones de creacion de la bd
        sqLiteDatabase.execSQL(sentenciaCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //ToDo: operaciones de actualización de la bd
    }
}