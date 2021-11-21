package com.LUIS.ProyectoAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    private ListView lv;
    private MyDBSQLiteHelper admin;
    private SQLiteDatabase db;
    private Cursor filas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        lv = findViewById(R.id.listView);

        admin = new MyDBSQLiteHelper(this, vars.nomDB , null, vars.version);

        ArrayList<String> listado = new ArrayList<String>();

        Bundle extras = getIntent().getExtras();
        String nomTabla = extras.getString("nomTabla");

        //Convertir la primera letra a mayusculas
        String tabla = nomTabla.substring(0,1).toUpperCase() + nomTabla.substring(1);

        //Cambiar título de la actividad
        setTitle(tabla);

        db = admin.getReadableDatabase();
        if (nomTabla.equals("registro")) {
            filas = db.rawQuery("SELECT * FROM registro", null);
            while (filas.moveToNext()) {
                listado.add(filas.getInt(0) + "-" + filas.getString(1) + "\n" + filas.getString(2) + "\n" +
                        filas.getString(3) + "\n" + filas.getString(4) + "\n" + filas.getString(5) + "\n" + filas.getString(6));
            }
            db.close();
        }
        //listado.add("Uno");
        //listado.add("dos");
        //for(int i=3; i<=20; i++) {
        //    listado.add("Elemento " + i);
        //}

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, listado);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast.makeText(getApplicationContext(), "Pos: "+pos+" - "+lv.getItemAtPosition(pos), Toast.LENGTH_SHORT).show();
            }
        });
    }
}