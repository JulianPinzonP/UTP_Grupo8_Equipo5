package com.LUIS.ProyectoAndroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private MyDBSQLiteHelper admin;
    private SQLiteDatabase db;
    private Cursor filas;

    public void goToActivityProducto(View view) {
        Intent newIntent = new Intent(this, ProductoActivity.class);
        newIntent.putExtra("msg", "Hola");
        newIntent.putExtra("year", 2021);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(newIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toast.makeText(this, "Método onCreate()", Toast.LENGTH_LONG).show();
        lv = findViewById(R.id.listaperso);

        admin = new MyDBSQLiteHelper(this, vars.nomDB , null, vars.version);
        Bundle extras = getIntent().getExtras();
        String corr = extras.getString("correo");
        ArrayList<String> listado = new ArrayList<String>();

        String nomTabla = extras.getString("nomTabla");

        //Convertir la primera letra a mayusculas
        String tabla = nomTabla.substring(0,1).toUpperCase() + nomTabla.substring(1);

        //Cambiar título de la actividad
        //setTitle(tabla);

        db = admin.getReadableDatabase();
        if (nomTabla.equals("registro")) {
            filas = db.rawQuery("SELECT * FROM registro WHERE correo='" + corr + "'", null);
            while (filas.moveToNext()) {
                listado.add("Nombre: " + filas.getString(1) + "\n\nApellido: " + filas.getString(2) + "\n\nFecha de nacimiento: " +
                        filas.getString(3) + "\n\nTelefono: " + filas.getString(4));
            }
            db.close();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, listado);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast.makeText(getApplicationContext(), " - "+lv.getItemAtPosition(pos), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id == R.id.mnu_producto) {
            Intent newIntent = new Intent(this, ProductoActivity.class);
            newIntent.putExtra("msg", "Hola MinTIC");
            newIntent.putExtra("year", 2021);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(newIntent);
        }
        else if(id == R.id.mnu_categoria) {
            Intent newIntent = new Intent(this, CategoriaActivity.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(newIntent);
        }

        return super.onOptionsItemSelected(menuItem);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Información")
                    .setMessage("¿Desea cerrar sesion?")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent newIntent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(newIntent);
                            finish();
                        }
                    }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}