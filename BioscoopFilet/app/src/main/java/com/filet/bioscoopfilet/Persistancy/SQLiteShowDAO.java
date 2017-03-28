package com.filet.bioscoopfilet.Persistancy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.filet.bioscoopfilet.DomainModel.Show;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

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

        try{
            SQLiteDatabase readable = db.getReadableDatabase();

            String query = "SELECT * FROM " + db.getDB_TABLE_SHOW_NAME();
            Cursor cursor = readable.rawQuery(query, null);

            cursor.moveToFirst();
            while(cursor.moveToNext() ) {
                Show s = new Show(cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_SHOWID())),
                        cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_SHOW_FILMID())),
                        cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_SHOW_THEATHERID())),
                        new Date(cursor.getString(cursor.getColumnIndex(db.getCOLUMN_SHOW_TIME())))); //Test the time!!

                Log.i(TAG, s.toString());
                Log.i(TAG, "--------------------------------------------");

                shows.add(s);
            }

            db.close();
        }catch(SQLiteException e)
        {
            Log.i(TAG,e.getMessage());
        }

        return shows;
    }

    @Override
    public void insertData(Show show) {

        try{
            SQLiteDatabase writable = db.getWritableDatabase();

            ContentValues values = new ContentValues();


            values.put(db.getCOLUMN_SHOW_FILMID(),show.getFilmID());
            values.put(db.getCOLUMN_SHOW_THEATHERID(),show.getTheaterID());
            //Test this!!
            values.put(db.getCOLUMN_SHOW_TIME(),show.getTime().toString());

            writable.insert(db.getDB_TABLE_FEEDBACK_NAME(), null, values);
        }catch(SQLiteException e)
        {
            Log.i(TAG,e.getMessage());
        }

    }
}
