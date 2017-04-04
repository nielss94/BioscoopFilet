package com.filet.bioscoopfilet.Persistancy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.filet.bioscoopfilet.DomainModel.Cinema;
import com.filet.bioscoopfilet.DomainModel.Film;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public class SQLiteFilmDAO implements FilmDAO {

    private final String TAG = this.getClass().getSimpleName();
    private DBConnect db;
    private Context context;
    private ArrayList<Film> films = new ArrayList<>();
    private ArrayList<Cinema> cinemas = new ArrayList<>();

    public SQLiteFilmDAO(Context context) {
        this.context = context;

        db = new DBConnect(context, null, null);
    }

    @Override
    public ArrayList<Film> selectData() {
        try {

            SQLiteDatabase readable = db.getReadableDatabase();

            String query = "SELECT * FROM " + db.getDB_TABLE_FILM_NAME();
            Cursor cursor = readable.rawQuery(query, null);

            CinemaDAO cinemaDAO = new SQLiteCinemaDAO(context);
            cinemas = cinemaDAO.selectData();

//            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Film f = null;
                Cinema c = null;
                for (int i = 0; i < cinemas.size(); i++) {
                    if (cinemas.get(i).getCinemaID() == cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_FILM_CINEMAID()))) {
                        c = cinemas.get(i);
                    }
                    f = new Film(cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_FILMID())),
                            cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_FILMAPIID())),
                            c,
                            cursor.getString(cursor.getColumnIndex(db.getCOLUMN_FILM_TITLE())),
                            cursor.getString(cursor.getColumnIndex(db.getCOLUMN_FILM_VERSION())),
                            cursor.getString(cursor.getColumnIndex(db.getCOLUMN_FILM_LANGUAGE())),
                            cursor.getString(cursor.getColumnIndex(db.getCOLUMN_FILM_RELEASEDATE())),
                            cursor.getString(cursor.getColumnIndex(db.getCOLUMN_FILM_GENRE())),
                            cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_FILM_LENGTH())),
                            cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_FILM_AGE())),
                            cursor.getString(cursor.getColumnIndex(db.getCOLUMN_FILM_DESCRIPTION())),
                            cursor.getString(cursor.getColumnIndex(db.getCOLUMN_FILM_IMDBURL())),
                            cursor.getString(cursor.getColumnIndex(db.getCOLUMN_FILM_IMDBSCORE())),
                            cursor.getString(cursor.getColumnIndex(db.getCOLUMN_FILM_TRAILERURL())),
                            cursor.getString(cursor.getColumnIndex(db.getCOLUMN_FILM_POSTERURL())),
                            cursor.getString(cursor.getColumnIndex(db.getCOLUMN_FILM_DIRECTOR())));
                }

                Log.i(TAG, f.toString());
                Log.i(TAG, "--------------------------------------------");

                films.add(f);
            }
            db.close();

        } catch (SQLiteException e) {
            Log.i(TAG, e.getMessage());
        }
        return films;
    }

    @Override
    public void insertData(Film film) {
        try {
            SQLiteDatabase writable = db.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(db.getCOLUMN_FILMAPIID(), film.getFilmAPIID());
            values.put(db.getCOLUMN_FILM_CINEMAID(), film.getCinema().getCinemaID());
            values.put(db.getCOLUMN_FILM_TITLE(), film.getTitle());
            values.put(db.getCOLUMN_FILM_VERSION(), film.getVersion());
            values.put(db.getCOLUMN_FILM_LANGUAGE(), film.getLanguage());
            values.put(db.getCOLUMN_FILM_RELEASEDATE(), film.getReleaseDate());
            values.put(db.getCOLUMN_FILM_GENRE(), film.getGenre());
            values.put(db.getCOLUMN_FILM_LENGTH(), film.getLength());
            values.put(db.getCOLUMN_FILM_AGE(), film.getAge());
            values.put(db.getCOLUMN_FILM_DESCRIPTION(), film.getDescription());
            values.put(db.getCOLUMN_FILM_IMDBURL(), film.getIMDBUrl());
            values.put(db.getCOLUMN_FILM_IMDBSCORE(), film.getIMDBScore());
            values.put(db.getCOLUMN_FILM_TRAILERURL(), film.getTrailerURL());
            values.put(db.getCOLUMN_FILM_POSTERURL(), film.getPosterURL());
            values.put(db.getCOLUMN_FILM_DIRECTOR(), film.getDirector());

            writable.insert(db.getDB_TABLE_FILM_NAME(), null, values);
        } catch (SQLiteException e) {
            Log.i(TAG, e.getMessage());
        }
    }

    @Override
    public void deleteData() {
        try {
            SQLiteDatabase writable = db.getWritableDatabase();
            writable.execSQL("delete from " + db.getDB_TABLE_FILM_NAME() + ";");
        } catch (SQLiteException e) {
            Log.i(TAG, e.getMessage());
        }
    }
}
