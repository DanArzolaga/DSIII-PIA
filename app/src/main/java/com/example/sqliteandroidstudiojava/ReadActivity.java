package com.example.sqliteandroidstudiojava;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.sqliteandroidstudiojava.adaptadores.AnimeAdapter;
import com.example.sqliteandroidstudiojava.databse.DbHelper;
import com.example.sqliteandroidstudiojava.entidades.Anime;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadActivity extends AppCompatActivity{

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.item_list)
    RecyclerView mRecyclerView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    AnimeAdapter mAnimeAdapter;

    GridLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_read );

        ButterKnife.bind( this );
        Recycler();

        mFab.setOnClickListener( view -> {
            Intent intent = new Intent( ReadActivity.this, AddActivity.class );
            startActivity( intent );
        } );

    }

    public void Recycler() {

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mLayoutManager = new GridLayoutManager( this, 2 );
        } else {
            mLayoutManager = new GridLayoutManager( this, 1 );
        }

        mRecyclerView.setLayoutManager( mLayoutManager );
        mAnimeAdapter = new AnimeAdapter( new ArrayList<>() );

        Content();
    }

    private void Content() {

        DbHelper mDatabase = new DbHelper( this );
        List<Anime> mAnime = mDatabase.listAnime();


        if (mAnime.size() > 0) {
            mAnimeAdapter = new AnimeAdapter( mAnime );
        } else {
            ArrayList<Anime> animeEmpty = new ArrayList<>();
            mAnimeAdapter.addItems( animeEmpty );
        }

        mRecyclerView.setAdapter( mAnimeAdapter );

    }
}
