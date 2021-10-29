package com.example.sqliteandroidstudiojava;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqliteandroidstudiojava.databse.DbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoginActivity extends AppCompatActivity {

    FloatingActionButton fb, google, mail;
    EditText txtEmail, txtPass;
    Button btnIngresar;
    TextView registro;
    float v = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        txtEmail = findViewById( R.id.txtEmail );
        txtPass = findViewById( R.id.txtPass );
        btnIngresar = findViewById( R.id.btn_ingresar );
        registro = findViewById( R.id.txt_registro );



        fb = findViewById( R.id.btn_Facebook );
        google = findViewById( R.id.btn_Google );
        mail = findViewById( R.id.btn_Mail );


        //agregamos unas animaciones
        fb.setTranslationY( 300 );
        google.setTranslationY( 300 );
        mail.setTranslationY( 300 );

        fb.setAlpha( v );
        google.setAlpha( v );
        mail.setAlpha( v );

        fb.animate().translationY( 0 ).alpha( 1 ).setDuration( 1000 ).setStartDelay( 400 ).start();
        google.animate().translationY( 0 ).alpha( 1 ).setDuration( 1000 ).setStartDelay( 600 ).start();
        mail.animate().translationY( 0 ).alpha( 1 ).setDuration( 1000 ).setStartDelay( 800 ).start();



        String pass= txtPass.getText().toString();
        String email = txtEmail.getText().toString();


        DbHelper dataBase = new DbHelper( this );
        btnIngresar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String pass= txtPass.getText().toString();

                if (txtEmail.equals( "" ) || txtPass.equals("")) {

                    Toast.makeText( LoginActivity.this, "Favor de llenar todos los campos", Toast.LENGTH_SHORT ).show();

                }else {
                    Boolean checkUser = dataBase.checkUserPass( email, pass );
                    if (checkUser == true) {
                        Toast.makeText( LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT ).show();
                        Intent intent = new Intent( LoginActivity.this, ReadActivity.class );
                        startActivity( intent );

                    }
                }
            }
        } );

        registro.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity( intent2 );
            }
        } );
    }
}