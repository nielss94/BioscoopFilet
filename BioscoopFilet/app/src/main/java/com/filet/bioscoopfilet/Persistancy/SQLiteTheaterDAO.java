package com.filet.bioscoopfilet.Persistancy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.filet.bioscoopfilet.DomainModel.Show;
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

    public SQLiteTheaterDAO(Context context)
    {
        this.context = context;

        db = new DBConnect(context,null,null,1);
    }

    @Override
    public ArrayList<Theater> selectData() {
        try {

            SQLiteDatabase readable = db.getReadableDatabase();

            String query = "SELECT * FROM " +

        } catch (SQLiteException e) {
            Log.i(TAG, e.getMessage());
        }
        return theaters;
    }

    @Override
    public void insertData(Theater theater) {

    }
}
