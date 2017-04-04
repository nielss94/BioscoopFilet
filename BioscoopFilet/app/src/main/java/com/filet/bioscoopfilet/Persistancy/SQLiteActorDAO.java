package com.filet.bioscoopfilet.Persistancy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.filet.bioscoopfilet.DomainModel.Actor;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public class SQLiteActorDAO implements ActorDAO {

    private final String TAG = this.getClass().getSimpleName();
    private DBConnect db;
    private Context context;
    private ArrayList<Actor> actors = new ArrayList<>();

    public SQLiteActorDAO(Context context) {
        this.context = context;

        db = new DBConnect(context, null, null);
    }

    @Override
    public ArrayList<Actor> selectData() {
        try {

            SQLiteDatabase readable = db.getReadableDatabase();

            String query = "SELECT * FROM " + db.getDB_TABLE_ACTOR_NAME();
            Cursor cursor = readable.rawQuery(query, null);

            cursor.moveToFirst();
            do {
                Actor a = new Actor(cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_ACTORID())),
                        cursor.getString(cursor.getColumnIndex(db.getCOLUMN_ACTOR_FIRSTNAME())),
                        cursor.getString(cursor.getColumnIndex(db.getCOLUMN_ACTOR_LASTNAME())));

                Log.i(TAG, a.toString());
                Log.i(TAG, "--------------------------------------------");

                actors.add(a);
            } while (cursor.moveToNext());
            db.close();

        } catch (SQLiteException e) {
            Log.i(TAG, e.getMessage());
        }
        return actors;
    }

    @Override
    public void insertData(Actor actor) {
        try {
            SQLiteDatabase writable = db.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(db.getCOLUMN_ACTOR_FIRSTNAME(), actor.getFirstName());
            values.put(db.getCOLUMN_ACTOR_LASTNAME(), actor.getLastName());

            writable.insert(db.getDB_TABLE_ACTOR_NAME(), null, values);
        } catch (SQLiteException e) {
            Log.i(TAG, e.getMessage());
        }
    }
}
