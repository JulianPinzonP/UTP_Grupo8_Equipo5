package com.LUIS.ProyectoAndroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBSQLiteHelper extends SQLiteOpenHelper {
    public MyDBSQLiteHelper(Context context, String nomDB, SQLiteDatabase.CursorFactory factory, int version){
        super(context,nomDB,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE registro(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, fecha TEXT, telefono TEXT, correo TEXT, contra TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int verDBAnterior, int verDBNueva) {

        db.execSQL("DROP TABLE IF EXISTS registro");
        db.execSQL("CREATE TABLE registro(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, fecha TEXT, telefono TEXT, correo TEXT, contra TEXT)");
    }
}