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
import com.example.sqliteandroidstudiojava.entidades.User;

public class SignupActivity extends AppCompatActivity {

    EditText txtPass, txtNombre, txtEmail;
    Button btnRegistro;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup );

        txtEmail = findViewById( R.id.txtEmail1 );
        txtNombre = findViewById( R.id.txtNombre );
        txtPass = findViewById( R.id.txtPass1 );
        btnRegistro = findViewById( R.id.btn_registro );
        login = findViewById( R.id.txt_login );

        DbHelper dataBase = new DbHelper(this );

        btnRegistro.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtNombre.getText().toString();
                String email = txtEmail.getText().toString();
                String pass= txtPass.getText().toString();

                if (txtNombre.equals( "" ) || txtEmail.equals( "" ) || txtPass.equals("")) {

                    Toast.makeText( SignupActivity.this, "Favor de llenar todos los campos", Toast.LENGTH_SHORT ).show();

                } else {
                    if (pass.length() >= 6) {
                        Boolean checkUser = dataBase.checkEmailUser( email);
                        try {
                            if (checkUser == false) {
                                User mNewUser = new User( name, email, pass);
                                dataBase.newUser( mNewUser );

                                Toast.makeText( SignupActivity.this, "Registro exitoso", Toast.LENGTH_SHORT ).show();

                                Intent intent = new Intent( SignupActivity.this, ReadActivity.class );
                                startActivity( intent );

                            } else {
                                Toast.makeText( SignupActivity.this, "Este usuario ya existe", Toast.LENGTH_SHORT ).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText( SignupActivity.this, "No se pudo realizar el registro, " + e.getMessage(), Toast.LENGTH_SHORT ).show();
                        }
                    } else {
                        Toast.makeText( SignupActivity.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT ).show();
                    }
                }
            }
        } );

        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent2 = new Intent(SignupActivity.this, LoginActivity.class );
               startActivity( intent2 );
            }
        } );
    }
}