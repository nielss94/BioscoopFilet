package com.filet.bioscoopfilet.Persistancy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.filet.bioscoopfilet.DomainModel.Cinema;
import com.filet.bioscoopfilet.DomainModel.Theater;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public class SQLiteTheaterDAO implements TheaterDAO {

    private final String TAG = this.getClass().getSimpleName();
    private DBConnect db;
    private Context context;
    private ArrayList<Theater> theaters = new ArrayList<>();
    private ArrayList<Cinema> cinemas = new ArrayList<>();

    public SQLiteTheaterDAO(Context context) {
        this.context = context;

        db = new DBConnect(context, null, null);
    }

    @Override
    public ArrayList<Theater> selectData() {
        try {

            SQLiteDatabase readable = db.getReadableDatabase();

            String query = "SELECT * FROM " + db.getDB_TABLE_THEATER_NAME();
            Cursor cursor = readable.rawQuery(query, null);

            cursor.moveToFirst();

            CinemaDAO cinemaDAO = new SQLiteCinemaDAO(context);
            cinemas = cinemaDAO.selectData();

            while (cursor.moveToNext()) {
                Cinema c = null;
                Theater t = null;
                for (int i = 0; i < cinemas.size(); i++) {
                    if (cinemas.get(i).getCinemaID() == cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_THEATER_CINEMAID()))) {
                        c = cinemas.get(i);
                    }
                }
                t = new Theater(cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_THEATERID())), c,
                        cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_THEATER_NUMBER_OF_SEATS())));

                Log.i(TAG, t.toString());
                Log.i(TAG, "--------------------------------------------");

                theaters.add(t);
            }
            db.close();

        } catch (SQLiteException e) {
            Log.i(TAG, e.getMessage());
        }
        return theaters;
    }

    @Override
    public void insertData(Theater theater) {
        try {
            SQLiteDatabase writable = db.getReadableDatabase();

            ContentValues values = new ContentValues();

            values.put(db.getCOLUMN_THEATER_CINEMAID(), theater.getCinema().getCinemaID());
            values.put(db.getCOLUMN_THEATER_NUMBER_OF_SEATS(), theater.getNumberOfSeats());

            writable.insert(db.getDB_TABLE_THEATER_NAME(), null, values);
        } catch (SQLiteException e) {
            Log.i(TAG, e.getMessage());
        }
    }
}
