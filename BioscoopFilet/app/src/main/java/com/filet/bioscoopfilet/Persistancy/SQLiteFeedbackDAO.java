package com.filet.bioscoopfilet.Persistancy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.filet.bioscoopfilet.DomainModel.Feedback;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public class SQLiteFeedbackDAO implements FeedbackDAO {

    private final String TAG = this.getClass().getSimpleName();

    private DBConnect db;
    private Context context;

    private ArrayList<Feedback> feedbacks = new ArrayList<>();

    public SQLiteFeedbackDAO(Context context)
    {
        this.context = context;

        db = new DBConnect(context, null, null,1);
    }

    @Override
    public ArrayList<Feedback> selectData() {

        SQLiteDatabase readable = db.getReadableDatabase();

        String query = "SELECT * FROM " + db.getDB_TABLE_FEEDBACK_NAME();
        Cursor cursor = readable.rawQuery(query, null);

        cursor.moveToFirst();
        while(cursor.moveToNext() ) {
            Feedback f = new Feedback(cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_FEEDBACKID())),
                    cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_FEEDBACK_VISITORID())),
                    cursor.getString(cursor.getColumnIndex(db.getCOLUMN_FEEDBACK_DESCRIPTION())));

            Log.i(TAG, f.toString());
            Log.i(TAG, "--------------------------------------------");

            feedbacks.add(f);
        }

        db.close();
        return feedbacks;
    }

    @Override
    public void insertData(Feedback feedback) {

        SQLiteDatabase writable = db.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(db.getCOLUMN_FEEDBACK_VISITORID(),feedback.getVisitorID());
        values.put(db.getCOLUMN_FEEDBACK_DESCRIPTION(),feedback.getDescription());

        writable.insert(db.getDB_TABLE_FEEDBACK_NAME(), null, values);
    }
}
