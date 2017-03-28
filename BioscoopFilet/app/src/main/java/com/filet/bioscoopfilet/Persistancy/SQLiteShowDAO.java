package com.filet.bioscoopfilet.Persistancy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.filet.bioscoopfilet.DomainModel.Feedback;
import com.filet.bioscoopfilet.DomainModel.Show;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public class SQLiteShowDAO implements ShowDAO {

    private final String TAG = this.getClass().getSimpleName();
    private DBConnect db;
    private Context context;
    private ArrayList<Show> shows = new ArrayList<>();

    public SQLiteShowDAO(Context context)
    {
        this.context = context;

        db = new DBConnect(context,null,null,1);
    }

    @Override
    public ArrayList<Show> selectData() {

        return shows;
    }

    @Override
    public void insertData(Show show) {

    }
}
