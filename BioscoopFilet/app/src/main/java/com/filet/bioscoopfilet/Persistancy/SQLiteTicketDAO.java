package com.filet.bioscoopfilet.Persistancy;

import android.content.Context;

import com.filet.bioscoopfilet.DomainModel.Theater;
import com.filet.bioscoopfilet.DomainModel.Ticket;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public class SQLiteTicketDAO implements TicketDAO {

    private final String TAG = this.getClass().getSimpleName();
    private DBConnect db;
    private Context context;
    private ArrayList<Ticket> tickets = new ArrayList<>();

    public SQLiteTicketDAO(Context context)
    {
        this.context = context;

        db = new DBConnect(context,null,null,1);
    }
    @Override
    public ArrayList<Ticket> selectData() {
        return tickets;
    }

    @Override
    public void insertData(Ticket ticket) {

    }
}
