package com.LUIS.ProyectoAndroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddPetsActivity extends AppCompatActivity {
    private TextInputEditText ti1, ti2, ti3;
    private ImageButton btnCamara;
    private ImageView imgView;
    private MyDBSQLiteHelper admin;
    private SQLiteDatabase db;
    private Cursor filas;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private firebaspet base;
    private String bases = "";
    private Bitmap bmp, bmp2;
    private ContentValues cv;
    private TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pets);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        admin = new MyDBSQLiteHelper(this, vars.nomDB , null, vars.version);
        ti1 = findViewById(R.id.nombrepet);
        ti2 = findViewById(R.id.razapet);
        ti3 = findViewById(R.id.edadpet);
        tv1 = findViewById(R.id.textView22);
        btnCamara = findViewById(R.id.btnCamara);
        imgView = findViewById(R.id.imageView12);
        imgView.setImageDrawable(null);
        Bundle extras = getIntent().getExtras();
        String usuario = extras.getString("usuario");
        tv1.setText(usuario);
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara(view);
            }
        });
    }
    public void abrirCamara(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, 1);
        }
    }
    public void deGaleria(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent , 2);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            imgView.setImageBitmap(imgBitmap);
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            InputStream inputStream;
            try {
                inputStream = getContentResolver().openInputStream(selectedImage);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);
                imgView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void OnBackPressed() {
        finish();
    }
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id == android.R.id.home) {
            OnBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void agreg(View view){
        String nombre = ti1.getText().toString();
        String raza = ti2.getText().toString();
        String edad = ti3.getText().toString();
        String usuario = tv1.getText().toString();

        if (!nombre.equals("") && !raza.equals("") && !edad.equals("")&&imgView.getDrawable() != null) {
            base = new firebaspet (nombre, raza, Integer.parseInt(edad));
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference().child("base").child(usuario).push();
            myRef.setValue(base);

            String imgCodificada = "";
            imgView.buildDrawingCache(true);
            bmp = imgView.getDrawingCache(true);

            bmp2 = Bitmap.createScaledBitmap(bmp, imgView.getWidth(), imgView.getHeight(), true);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp2.compress(Bitmap.CompressFormat.PNG, 25, baos);
            byte[] imagen = baos.toByteArray();
            imgCodificada = Base64.encodeToString(imagen, Base64.DEFAULT);

            //Almacenar los datos
            db = admin.getWritableDatabase();
            cv = new ContentValues();
            cv.put("descripcion", nombre);
            cv.put("img", imgCodificada);
            cv.put("usuario", usuario);
            long reg = db.insert("imagenes", null, cv);
            if (reg != -1) {
                Toast.makeText(this, "Registro almacenado", Toast.LENGTH_SHORT).show();
                imgView.setImageBitmap(null);
                imgView.destroyDrawingCache();
                imgView.setImageDrawable(null);
                ti1.setText("");
                ti2.setText("");
                ti3.setText("");
            } else
                Toast.makeText(this, "El registro no se pudo almacenar", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Por favor, ingrese todos los datos", Toast.LENGTH_SHORT).show();
    }
    public void buscar(View view){







    }
}