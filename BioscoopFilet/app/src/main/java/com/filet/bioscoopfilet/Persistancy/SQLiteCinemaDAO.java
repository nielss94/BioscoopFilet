package com.filet.bioscoopfilet.Persistancy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.filet.bioscoopfilet.DomainModel.Cinema;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public class SQLiteCinemaDAO implements CinemaDAO {

    private final String TAG = this.getClass().getSimpleName();
    private DBConnect db;
    private Context context;
    private ArrayList<Cinema> cinemas = new ArrayList<>();

    public SQLiteCinemaDAO(Context context) {
        this.context = context;

        db = new DBConnect(context, null, null);
    }

    @Override
    public ArrayList<Cinema> selectData() {
        try {

            SQLiteDatabase readable = db.getReadableDatabase();

            String query = "SELECT * FROM " + db.getDB_TABLE_CINEMA_NAME();
            Cursor cursor = readable.rawQuery(query, null);

//            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Cinema c = new Cinema(cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_CINEMAID())),
                        cursor.getString(cursor.getColumnIndex(db.getCOLUMN_CINEMA_NAME())),
                        cursor.getString(cursor.getColumnIndex(db.getCOLUMN_CINEMA_CITY())),
                        cursor.getString(cursor.getColumnIndex(db.getCOLUMN_CINEMA_ADDRESS())),
                        cursor.getString(cursor.getColumnIndex(db.getCOLUMN_CINEMA_ZIPCODE())),
                        cursor.getString(cursor.getColumnIndex(db.getCOLUMN_CINEMA_PHONE())));

                Log.i(TAG, c.toString());
                Log.i(TAG, "--------------------------------------------");

                cinemas.add(c);
            }
            db.close();
        } catch (SQLiteException e) {
            Log.i(TAG, e.getMessage());
        }
        return cinemas;
    }

    @Override
    public void insertData(Cinema cinema) {
        try {
            SQLiteDatabase writable = db.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(db.getCOLUMN_CINEMA_NAME(), cinema.getName());
            values.put(db.getCOLUMN_CINEMA_CITY(), cinema.getCity());
            values.put(db.getCOLUMN_CINEMA_ADDRESS(), cinema.getAddress());
            values.put(db.getCOLUMN_CINEMA_ZIPCODE(), cinema.getZipCode());
            values.put(db.getCOLUMN_CINEMA_PHONE(), cinema.getPhone());

            writable.insert(db.getDB_TABLE_CINEMA_NAME(), null, values);
        } catch (SQLiteException e) {
            Log.i(TAG, e.getMessage());
        }
    }
}
