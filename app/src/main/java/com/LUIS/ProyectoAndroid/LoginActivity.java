package com.LUIS.ProyectoAndroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private TextView t1,t2;
    private ImageView iv1;
    private EditText et1,et2;
    private MyDBSQLiteHelper admin;
    private SQLiteDatabase db;
    private Cursor filas;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Ocultar ActionBar
        getSupportActionBar().hide();

        admin = new MyDBSQLiteHelper(this, vars.nomDB , null, vars.version);

        t1 = findViewById(R.id.textView);
        t1.setText("AnimalHealt");
        t1.setTextSize(25);
        t2 = findViewById(R.id.textView3);
        et1= findViewById(R.id.editTextTextPersonName);
        et2= findViewById(R.id.editTextTextPassword);
        String texto= "Recordar Contraseña";
        t2.setText(Html.fromHtml(texto));
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = et1.getText().toString();
                db = admin.getReadableDatabase();
                filas = db.rawQuery("SELECT * FROM registro WHERE correo='" + correo + "'", null);
                if ((filas.moveToFirst())) {
                    if (filas.getString(5).equals(correo)) {
                        Toast.makeText(LoginActivity.this, "Su contraseña es: "+ filas.getString(6), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        iv1 = findViewById(R.id.imageView12);
    }

    public void iniciarSesion(View view){
        String correo = et1.getText().toString();
        String pass = et2.getText().toString();

        if(!correo.equals("") && !pass.equals("")) {
            db = admin.getReadableDatabase();
            filas = db.rawQuery("SELECT * FROM registro WHERE correo='" + correo + "'", null);
            if ((filas.moveToFirst())) {
                if(filas.getString(6).equals(pass)) {
                    Intent newIntent = new Intent(this, MainActivity.class);
                    newIntent.putExtra("nomTabla", "registro");
                    newIntent.putExtra("correo", correo);
                    startActivity(newIntent);
                }
                else {
                    Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                    et2.setText("");
                }
            }
        }
        else {
            Toast.makeText(LoginActivity.this, "Ingrese sus datos completos", Toast.LENGTH_SHORT).show();
            et1.requestFocus();
        }
    }

    public void registrarse(View view){
        Intent newIntent= new Intent(this,RegistroActivity.class);
        startActivity(newIntent);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Información")
                    .setMessage("¿Desea cerrar la aplicación?")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LoginActivity.this.finish();
                        }
                    }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}