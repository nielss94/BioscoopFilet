package com.filet.bioscoopfilet.Persistancy;

import android.content.Context;

import com.filet.bioscoopfilet.DomainModel.Ticket;
import com.filet.bioscoopfilet.DomainModel.Visitor;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public class SQLiteVisitorDAO implements VisitorDAO {

    private final String TAG = this.getClass().getSimpleName();
    private DBConnect db;
    private Context context;
    private ArrayList<Visitor> visitors = new ArrayList<>();

    public SQLiteVisitorDAO(Context context)
    {
        this.context = context;

        db = new DBConnect(context,null,null,1);
    }
    @Override
    public ArrayList<Visitor> selectData() {
        return visitors;
    }

    @Override
    public void insertData() {

    }
}
