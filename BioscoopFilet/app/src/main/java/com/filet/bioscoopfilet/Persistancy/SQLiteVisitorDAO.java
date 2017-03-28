package com.filet.bioscoopfilet.Persistancy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

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
        try{
            SQLiteDatabase readable = db.getReadableDatabase();

            String query = "SELECT * FROM " + db.getDB_TABLE_VISITOR_NAME();
            Cursor cursor = readable.rawQuery(query, null);

            cursor.moveToFirst();
            while(cursor.moveToNext() ) {
                Visitor v = new Visitor(cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_VISITORID())),
                        cursor.getString(cursor.getColumnIndex(db.getCOLUMN_VISITOR_FIRSTNAME())),
                        cursor.getString(cursor.getColumnIndex(db.getCOLUMN_VISITOR_LASTNAME())));

                Log.i(TAG, v.toString());
                Log.i(TAG, "--------------------------------------------");

                visitors.add(v);
            }

            db.close();
        }catch(SQLiteException e)
        {
            Log.i(TAG,e.getMessage());
        }
        return visitors;
    }

    @Override
    public void insertData(Visitor visitor) {
        try{
            SQLiteDatabase writable = db.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(db.getCOLUMN_VISITOR_FIRSTNAME(),visitor.getFirstName());
            values.put(db.getCOLUMN_VISITOR_LASTNAME(),visitor.getLastName());

            writable.insert(db.getDB_TABLE_VISITOR_NAME(), null, values);
        }catch(SQLiteException e)
        {
            Log.i(TAG, e.getMessage());
        }
    }
}
