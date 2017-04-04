package com.filet.bioscoopfilet.Persistancy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.filet.bioscoopfilet.DomainModel.Show;
import com.filet.bioscoopfilet.DomainModel.Ticket;
import com.filet.bioscoopfilet.DomainModel.Visitor;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public class SQLiteTicketDAO implements TicketDAO {

    private final String TAG = this.getClass().getSimpleName();
    private DBConnect db;
    private Context context;
    private ArrayList<Ticket> tickets = new ArrayList<>();
    private ArrayList<Show> shows = new ArrayList<>();
    private ArrayList<Visitor> visitors = new ArrayList<>();

    public SQLiteTicketDAO(Context context) {
        this.context = context;

        db = new DBConnect(context, null, null);
    }

    @Override
    public ArrayList<Ticket> selectData() {

        try {
            SQLiteDatabase readable = db.getReadableDatabase();

            String query = "SELECT * FROM " + db.getDB_TABLE_TICKET_NAME();
            Cursor cursor = readable.rawQuery(query, null);

//            cursor.moveToFirst();

            ShowDAO showDAO = new SQLiteShowDAO(context);
            shows = showDAO.selectData();
            VisitorDAO visitorDAO = new SQLiteVisitorDAO(context);
            visitors = visitorDAO.selectData();

            while (cursor.moveToNext()) {
                Visitor v = null;
                Show s = null;
                Ticket t = null;
                for (int i = 0; i < visitors.size(); i++) {
                    if (visitors.get(i).getVisitorID() == cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_TICKET_VISITORID()))) {
                        v = visitors.get(i);
                    }
                }
                for (int i = 0; i < shows.size(); i++) {
                    if (shows.get(i).getShowID() == cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_TICKET_SHOWID()))) {
                        s = shows.get(i);
                    }
                }
                t = new Ticket(cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_TICKET_QRCODE())), v, s,
                        cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_TICKET_SEAT())));

                Log.i(TAG, t.toString());
                Log.i(TAG, "--------------------------------------------");

                tickets.add(t);
            }

            db.close();
        } catch (SQLiteException e) {
            Log.i(TAG, e.getMessage());
        }
        return tickets;
    }

    @Override
    public void insertData(Ticket ticket) {
        try {
            SQLiteDatabase writable = db.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(db.getCOLUMN_TICKET_QRCODE(), ticket.getQrCode());
            values.put(db.getCOLUMN_TICKET_VISITORID(), ticket.getVisitor().getVisitorID());
            values.put(db.getCOLUMN_TICKET_SHOWID(), ticket.getShow().getShowID());
            values.put(db.getCOLUMN_TICKET_SEAT(), ticket.getSeat());

            writable.insert(db.getDB_TABLE_TICKET_NAME(), null, values);
        } catch (SQLiteException e) {
            Log.i(TAG, e.getMessage());
        }
    }
}
