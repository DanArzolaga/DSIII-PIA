package com.example.sqliteandroidstudiojava;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqliteandroidstudiojava.databse.DbHelper;
import com.example.sqliteandroidstudiojava.entidades.Anime;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddActivity extends AppCompatActivity {

    @BindView(R.id.nameEditText)
    EditText mNameEditText;

    @BindView(R.id.yearEditText)
    EditText mScoreEditText;

    @BindView(R.id.episodeEditText)
    EditText mEpisodeEditText;

    @BindView(R.id.descriptionEditText)
    EditText mDescriptionEditText;

    @BindView(R.id.genreEditText)
    EditText mGenreEditText;

    @BindView(R.id.urlEditText)
    EditText mUrlEditText;

    @BindView(R.id.animeButton)
    Button mAnimeButton;

    //para el EditText de genero se convierta en selecctor multiple
    boolean[] genero;
    ArrayList<Integer> listaGenero = new ArrayList<>();
    String [] generoArray = {"Accion", "Aventura", "Comedia", "Drama", "Fantasía", "Ciencia Ficcion", "Sobrenatural", "Terror", "Romance", "Musica", "Deportes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ButterKnife.bind(this);

        DbHelper dataBase = new DbHelper(this);

        genero = new boolean[generoArray.length];

        mGenreEditText.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        AddActivity.this
                );
                //cambiar el titulo
                builder.setTitle( "Seleciona el genero" );

                //cambia el dialog
                builder.setCancelable( false );

                builder.setMultiChoiceItems( generoArray, genero, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        //checa la condicion
                        if(b){
                            //cuando el checkbox este seleccionado
                            //agrega la posicion en listaGenero
                            listaGenero.add(i);
                            Collections.sort(listaGenero);
                        } else{
                            //cuando el checkbox no este seleccionado
                            //quitar la posicion de listaGenero
                            listaGenero.remove( i );
                        }
                    }
                } );

                builder.setPositiveButton( "Seleccionar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();

                        for(int j = 0; j<listaGenero.size(); j++){
                            stringBuilder.append( generoArray[listaGenero.get( j )] );

                            //checa la condicion
                            if(j != listaGenero.size()-1){
                                //cuando el valor de j no se igual a -1 se añade una coma
                                stringBuilder.append( ", " );
                            }
                            //cambiar texto al textview
                            mGenreEditText.setText( stringBuilder.toString() );
                        }
                    }
                } );

                builder.setNegativeButton( "Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //desaparece el dialog
                        dialogInterface.dismiss();
                    }
                } );

                builder.setNeutralButton( "Limpiar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for(int j = 0; j<genero.length; j++){
                            //desaparece la seleccion
                            genero[j] = false;

                            //limpia la lista de genero
                            listaGenero.clear();

                            //limpiar los valores del text view
                            mGenreEditText.setText( "" );
                        }
                    }
                } );

                //muestra el dialog
                builder.show();
            }
        } );

        mAnimeButton.setOnClickListener(v -> {
            String name = mNameEditText.getText().toString();
            int score = Integer.parseInt(mScoreEditText.getText().toString());
            int episode = Integer.parseInt(mEpisodeEditText.getText().toString());
            String genre = mGenreEditText.getText().toString();
            String description = mDescriptionEditText.getText().toString();
            String url = mUrlEditText.getText().toString();

            Anime mNewAnime = new Anime(name, score, episode, genre, description, url);
            dataBase.newAnime(mNewAnime);

            Intent intent=new Intent(this, ReadActivity.class);
            startActivity(intent);
        });

    }
}
