package com.LUIS.ProyectoAndroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView t1,t2;
    ImageView iv1;
    EditText et1,et2;
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

        t1 = findViewById(R.id.textView);
        t1.setText("AnimalHealt");
        t1.setTextSize(25);
        t2 = findViewById(R.id.textView3);
        et1= findViewById(R.id.editTextTextPersonName);
        et2= findViewById(R.id.editTextTextPassword);
  //      String link = "<a href='https://www.google.com/'>google</a>";
  //      t2.setMovementMethod(LinkMovementMethod.getInstance());
        String texto= "Recordar Contraseña";
        t2.setText(Html.fromHtml(texto));
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et1.getText().toString().equals("admin")) {
                    Toast.makeText(LoginActivity.this, "Su contraseña es: admin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        iv1 = findViewById(R.id.imageView);
    }

    public void iniciarSesion(View view){
        if(et1.getText().toString().equals("admin") && et2.getText().toString().equals("admin")){
            Intent newIntent= new Intent(this,MainActivity.class);
            startActivity(newIntent);
            finish();
        }
        else {
            Toast.makeText(LoginActivity.this, "Datos Incorrectos", Toast.LENGTH_SHORT).show();
            et1.requestFocus();
        }
        if(et1.getText().toString().equals("")){
            Toast.makeText(LoginActivity.this, "Por favor, Ingrese el usuario", Toast.LENGTH_SHORT).show();
            et1.requestFocus();
        }
        else if(et2.getText().toString().equals("")){
            Toast.makeText(LoginActivity.this, "Por favor, Ingrese la contraseña", Toast.LENGTH_SHORT).show();
            et2.requestFocus();
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