package com.example.riskareas.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Sql extends SQLiteOpenHelper {

    private static final String database = "risk_areas";
    private static final int VERSION = 1;
    private final String table_municipalities =
            "CREATE TABLE municipalities (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "name TEXT NOT NULL," +
                    "significance TEXT NOT NULL," +
                    "header TEXT NOT NULL," +
                    "area TEXT NOT NULL," +
                    "clime TEXT NOT NULL," +
                    "altitude TEXT NOT NULL," +
                    "latitude TEXT NOT NULL," +
                    "longitude TEXT NOT NULL" +
            ")";
    private final String table_areas =
            "CREATE TABLE areas (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "id_municipality INTEGER NOT NULL," +
                    "disaster TEXT NOT NULL" +
            ")";

    public Sql(@Nullable Context context) {
        super(context, database, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table_municipalities);
        db.execSQL(table_areas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion>oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS areas");
            db.execSQL(table_areas);
            db.execSQL("DROP TABLE IF EXISTS municipalities");
            db.execSQL(table_municipalities);
        }
    }
}
