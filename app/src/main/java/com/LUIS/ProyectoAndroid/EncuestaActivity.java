package com.LUIS.ProyectoAndroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.NavUtils;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class EncuestaActivity extends AppCompatActivity {
    private EditText et1,et2,et3,et4;
    private TextView tv1;
    private SeekBar sk1;
    private String texto = "";
    private Spinner sp1;
    private Switch sw1;
    private CheckBox ch1,ch2,ch3,ch4,ch5,ch6;
    private RadioButton rb1,rb2,rb3;
    private TextInputEditText ti1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        et1 = (EditText) findViewById(R.id.editTextTextPersonName2);
        et2 = (EditText) findViewById(R.id.editTextPhone);
        et3 = (EditText) findViewById(R.id.editTextTextEmailAddress);
        ti1 = (TextInputEditText) findViewById(R.id.direccion);
        et4 = (EditText) findViewById(R.id.editTextDate);
        tv1 = (TextView) findViewById(R.id.textView13);
        sp1 = (Spinner) findViewById(R.id.spinner);
        sw1 = (Switch) findViewById(R.id.switch1);
        ch1 = (CheckBox) findViewById(R.id.checkBox);
        ch2 = (CheckBox) findViewById(R.id.checkBox2);
        ch3 = (CheckBox) findViewById(R.id.checkBox3);
        ch4 = (CheckBox) findViewById(R.id.checkBox4);
        ch5 = (CheckBox) findViewById(R.id.checkBox5);
        ch6 = (CheckBox) findViewById(R.id.checkBox6);
        rb1 = (RadioButton) findViewById(R.id.radioButton);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        sk1 = (SeekBar) findViewById(R.id.seekBar);
        sk1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv1.setText(""+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void obtenerDatos (View view){
        String nom = "Nombre: "+et1.getText().toString();
        String fechaN = "Fecha de nacimiento: "+et4.getText().toString();
        String tel = "Teléfono: "+et2.getText().toString();
        String email = "Correo: "+et3.getText().toString();
        String dic = "Dirección: "+ti1.getText().toString();
        String niv_ing = "Nivel en ingles: "+sp1.getSelectedItem();
        String gus_prog= "¿Te gusta programar?: ";
        if(sw1.isChecked()){
            gus_prog+= "Si";
        }
        else {
            gus_prog +="No";
        }
        String leng = "Lenguajes de programacion: ";
        if(ch1.isChecked()){
            leng+= ch1.getText()+ ", ";
        }
        if(ch2.isChecked()){
            leng+= ch2.getText()+ ", ";
        }
        if(ch3.isChecked()){
            leng+= ch3.getText()+ ", ";
        }
        if(ch4.isChecked()){
            leng+= ch4.getText()+ ", ";
        }
        if(ch5.isChecked()){
            leng+= ch5.getText()+ ", ";
        }
        if(ch6.isChecked()){
            leng+= ch6.getText();
        }
        String tiemp_exp = "Tiempo de experiencia: ";
        if(rb1.isChecked()){
            tiemp_exp+= rb1.getText()+" año";
        }
        if(rb2.isChecked()){
            tiemp_exp+= rb2.getText()+" años";
        }
        if(rb3.isChecked()){
            tiemp_exp+= rb3.getText()+" años";
        }
        String niv_sat = "Nivel de satisfacción: "+tv1.getText();
        new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Datos")
            .setMessage(nom+"\n"+fechaN+"\n"+tel+"\n"+email+"\n"+niv_ing+"\n"+gus_prog+"\n"+leng
                    +"\n"+tiemp_exp+"\n"+niv_sat+"\n"+dic)
            .setPositiveButton("Aceptar",null).show();
    }

    //public void OnBackPressed() {
        //finish();
    //}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}