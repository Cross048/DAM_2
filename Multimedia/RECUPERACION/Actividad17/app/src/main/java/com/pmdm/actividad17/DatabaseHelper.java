package com.pmdm.actividad17;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import android.content.Context;

public class DatabaseHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "personas.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}