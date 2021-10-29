package com.example.sqliteandroidstudiojava.adaptadores;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sqliteandroidstudiojava.ReadActivity;
import com.example.sqliteandroidstudiojava.entidades.Anime;
import com.example.sqliteandroidstudiojava.DetailActivity;
import com.example.sqliteandroidstudiojava.EditActivity;
import com.example.sqliteandroidstudiojava.R;
import com.example.sqliteandroidstudiojava.ViewHolder;
import com.example.sqliteandroidstudiojava.databse.DbHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AnimeAdapter extends RecyclerView.Adapter<ViewHolder>{


    private final List<Anime> mAnimeList;

    public AnimeAdapter(List<Anime> animeList) {

        mAnimeList = animeList;
    }

    @Override
    public void onBindViewHolder(com.example.sqliteandroidstudiojava.ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public com.example.sqliteandroidstudiojava.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate( R.layout.anime_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mAnimeList != null & mAnimeList.size() > 0) {
            return mAnimeList.size();
        } else {
            return 0;
        }
    }

    public void addItems(List<Anime> animeList) {
        mAnimeList.addAll(animeList);
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        if (mAnimeList != null & mAnimeList.size() > 0) {
            mAnimeList.remove(position);
        }
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public class ViewHolder extends com.example.sqliteandroidstudiojava.ViewHolder {

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.animeImageView)
        ImageView mAnimeImageView;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.animeCardView)
        CardView mAnimeCardView;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.deleteImageVIew)
        ImageView mDeleteImageVIew;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.editImageView)
        ImageView mEditImageView;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.nameTextView)
        TextView mNameTextView;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.yearTextView)
        TextView mYearEditText;

        DbHelper dataBase;

        public ViewHolder(View itemView) {
            super( itemView );
            ButterKnife.bind( this, itemView );
        }

        protected void clear() {
            mAnimeImageView.setImageDrawable( null );
            mNameTextView.setText( "" );
            mYearEditText.setText( "" );
        }

        public void onBind(int position) {
            super.onBind( position );

            Anime mAnime = mAnimeList.get( position );
            dataBase = new DbHelper( itemView.getContext() );

            if (mAnime.getUrl() != null) {
                Glide.with( itemView.getContext() )
                        .load( mAnime.getUrl() )
                        .into( mAnimeImageView );
            }

            if (mAnime.getName() != null) {
                mNameTextView.setText( mAnime.getName() );
            }

            mYearEditText.setText( String.valueOf( mAnime.getYear() ) );

            mAnimeCardView.setOnClickListener( v -> {
                Intent intent = new Intent( itemView.getContext(), DetailActivity.class );
                intent.putExtra( "id", mAnime.getId() );
                itemView.getContext().startActivity( intent );
            } );

            mEditImageView.setOnClickListener( v -> {
                Intent intent = new Intent( itemView.getContext(), EditActivity.class );
                intent.putExtra( "id", mAnime.getId() );
                itemView.getContext().startActivity( intent );
            } );

            mDeleteImageVIew.setOnClickListener( v -> {
                dataBase.deleteAnime( mAnime.getId() );
                deleteItem( position );
            } );
        }
    }

}