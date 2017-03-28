package com.filet.bioscoopfilet.Persistancy;

import android.content.Context;

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
        return theaters;
    }

    @Override
    public void insertData(Theater theater) {

    }
}
