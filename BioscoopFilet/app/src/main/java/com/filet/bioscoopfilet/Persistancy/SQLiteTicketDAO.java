package com.filet.bioscoopfilet.Persistancy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.filet.bioscoopfilet.DomainModel.Show;
import com.filet.bioscoopfilet.DomainModel.Theater;
import com.filet.bioscoopfilet.DomainModel.Ticket;

import java.util.ArrayList;
import java.util.Date;

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

        db = new DBConnect(context,null,null);
    }
    @Override
    public ArrayList<Ticket> selectData() {

        try{
            SQLiteDatabase readable = db.getReadableDatabase();

            String query = "SELECT * FROM " + db.getDB_TABLE_TICKET_NAME();
            Cursor cursor = readable.rawQuery(query, null);

            cursor.moveToFirst();
            while(cursor.moveToNext() ) {
                Ticket t = new Ticket(cursor.getString(cursor.getColumnIndex(db.getCOLUMN_TICKET_QRCODE())),
                        cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_TICKET_VISITORID())),
                        cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_TICKET_SHOWID())),
                        cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_TICKET_SEAT())));

                Log.i(TAG, t.toString());
                Log.i(TAG, "--------------------------------------------");

                tickets.add(t);
            }

            db.close();
        }catch(SQLiteException e)
        {
            Log.i(TAG,e.getMessage());
        }
        return tickets;
    }

    @Override
    public void insertData(Ticket ticket) {
        try{
            SQLiteDatabase writable = db.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(db.getCOLUMN_TICKET_QRCODE(),ticket.getQrCode());
            values.put(db.getCOLUMN_TICKET_VISITORID(),ticket.getVisitorID());
            values.put(db.getCOLUMN_TICKET_SHOWID(),ticket.getShowID());
            values.put(db.getCOLUMN_TICKET_SEAT(),ticket.getSeat());

            writable.insert(db.getDB_TABLE_FEEDBACK_NAME(), null, values);
        }catch(SQLiteException e)
        {
            Log.i(TAG, e.getMessage());
        }
    }
}
