package com.example.sqliteandroidstudiojava.databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqliteandroidstudiojava.entidades.Anime;
import com.example.sqliteandroidstudiojava.entidades.User;

import java.util.ArrayList;
import java.util.List;


public class DbHelper extends SQLiteOpenHelper {

    private static final int    DATABASE_VERSION =	3;
    private static final String DATABASE_NAME = "ANIME_DB";

    //tabla de anime
    private final static String TABLE_ANIME ="ANIME";
    private static final String COLUMN_ID_ANIME = "ID";
    private static final String COLUMN_NAME_ANIME = "NAME";
    private static final String COLUMN_YEAR = "YEAR";
    private static final String COLUMN_EPISODE = "EPISODE";
    private static final String COLUMN_GENRE = "GENRE";
    private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    private static final String COLUMN_URL = "URL";


    //tabla de usuarios
    private final static String TABLE_USER ="USUARIO";
    private static final String COLUMN_ID_USER = "ID_USER";
    private static final String COLUMN_NAME_USER = "NAME";
    private static final String COLUMN_EMAIL = "EMAIL";
    private static final String COLUMN_PASSWORD = "PASSWORD";



    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ANIME_TABLE = "CREATE	TABLE " + TABLE_ANIME +
                "(" + COLUMN_ID_ANIME + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME_ANIME+ " TEXT,"
                + COLUMN_YEAR+ " INTEGER,"
                + COLUMN_EPISODE+ " INTEGER,"
                + COLUMN_GENRE+ " INTEGER,"
                + COLUMN_DESCRIPTION+ " TEXT,"
                + COLUMN_URL+ " TEXT);";
        db.execSQL(CREATE_ANIME_TABLE);

        String CREATE_USER_TABLE = "CREATE	TABLE " + TABLE_USER +
                "(" + COLUMN_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME_USER+ " TEXT,"
                + COLUMN_EMAIL+ " TEXT,"
                + COLUMN_PASSWORD+ " TEXT);";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANIME);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public List<Anime> listAnime(){
        String sql = "select * from " + TABLE_ANIME;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Anime> listAnime = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int year = cursor.getInt(2);
                int episode = cursor.getInt(3);
                String genre = cursor.getString(4);
                String desc = cursor.getString(5);
                String url = cursor.getString(6);
                listAnime.add(new Anime(id,name,year,episode,genre,desc,url));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return listAnime;
    }

    public  Anime getAnime(int id){
        String sql = "select * from " + TABLE_ANIME+" where "+COLUMN_ID_ANIME+"	= ?";
        SQLiteDatabase db = this.getReadableDatabase();

        Anime mAnime = new Anime();

        Cursor cursor=db.rawQuery(sql, new String[] { String.valueOf(id)});
        if(cursor.moveToFirst()){
            do{
                mAnime.setName(cursor.getString(1));
                mAnime.setYear(cursor.getInt(2));
                mAnime.setEpisode(cursor.getInt(3));
                mAnime.setGenre( cursor.getString( 4 ) );
                mAnime.setDescription(cursor.getString(5));
                mAnime.setUrl( cursor.getString(6));

            }while (cursor.moveToNext());
        }
        cursor.close();
        return mAnime;
    }
    public void newAnime(Anime mAnime){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_ANIME, mAnime.getName());
        values.put(COLUMN_YEAR, mAnime.getYear());
        values.put(COLUMN_EPISODE, mAnime.getEpisode());
        values.put(COLUMN_GENRE, mAnime.getGenre());
        values.put(COLUMN_DESCRIPTION, mAnime.getDescription());
        values.put(COLUMN_URL, mAnime.getUrl());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_ANIME, null, values);
    }

    public  void updateAnime(Anime mAnime, int id){
        ContentValues values= new ContentValues();
        values.put(COLUMN_NAME_ANIME, mAnime.getName());
        values.put(COLUMN_YEAR, mAnime.getYear());
        values.put(COLUMN_EPISODE, mAnime.getEpisode());
        values.put(COLUMN_GENRE, mAnime.getGenre());
        values.put(COLUMN_DESCRIPTION, mAnime.getDescription());
        values.put(COLUMN_URL, mAnime.getUrl());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_ANIME, values, COLUMN_ID_ANIME+"="+ id,null);
    }

    public void deleteAnime(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ANIME, COLUMN_ID_ANIME+ "= ?", new String[] { String.valueOf(id)});
    }


    //METODOS PARA LA TABLA USER
    public boolean checkEmailUser(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "Select * from " + TABLE_USER+ " where "+ COLUMN_EMAIL+ " = ?", new String[]{email} );
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkUserPass(String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "Select * from " + TABLE_USER+ " where "+ COLUMN_EMAIL+ " = ?"+ "and "+ COLUMN_PASSWORD+ " = ?", new String[]{email, pass} );
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }


    public void newUser(User mUser){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_USER, mUser.getNameUser());
        values.put(COLUMN_EMAIL, mUser.getEmail());
        values.put(COLUMN_PASSWORD, mUser.getPassword());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USER, null, values);
    }

    public  void updateUser(User mUser, int id){
        ContentValues values= new ContentValues();
        values.put(COLUMN_NAME_USER, mUser.getNameUser());
        values.put(COLUMN_EMAIL, mUser.getEmail());
        values.put(COLUMN_PASSWORD, mUser.getPassword());

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_USER, values, COLUMN_ID_USER+"="+ id,null);
    }

    public void deleteUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, COLUMN_ID_USER+ "= ?", new String[] { String.valueOf(id)});
    }

}
