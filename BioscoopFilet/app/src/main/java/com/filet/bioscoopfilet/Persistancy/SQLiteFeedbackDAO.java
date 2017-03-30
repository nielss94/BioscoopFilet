package com.filet.bioscoopfilet.Persistancy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.filet.bioscoopfilet.DomainModel.Feedback;
import com.filet.bioscoopfilet.DomainModel.Visitor;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public class SQLiteFeedbackDAO implements FeedbackDAO {

    private final String TAG = this.getClass().getSimpleName();

    private DBConnect db;
    private Context context;

    private ArrayList<Feedback> feedbacks = new ArrayList<>();
    private ArrayList<Visitor> visitors = new ArrayList<>();

    public SQLiteFeedbackDAO(Context context)
    {
        this.context = context;

        db = new DBConnect(context, null, null);
    }

    @Override
    public ArrayList<Feedback> selectData() {

        try {
            SQLiteDatabase readable = db.getReadableDatabase();

            String query = "SELECT * FROM " + db.getDB_TABLE_FEEDBACK_NAME();
            Cursor cursor = readable.rawQuery(query, null);

            cursor.moveToFirst();

            VisitorDAO visitorDAO = new SQLiteVisitorDAO(context);

            visitors = visitorDAO.selectData();

            while(cursor.moveToNext() ) {
                Feedback f = null;
                Visitor v = null;
                for (int i = 0; i < visitors.size(); i++) {
                    Log.i(TAG, visitors.get(i).getVisitorID() + " &&& " + cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_FEEDBACK_VISITORID())));
                    if(visitors.get(i).getVisitorID() == cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_FEEDBACK_VISITORID())))
                    {
                        v = visitors.get(i);


                    }
                }
                f = new Feedback(cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_FEEDBACKID())), v,
                        cursor.getString(cursor.getColumnIndex(db.getCOLUMN_FEEDBACK_DESCRIPTION())));

                Log.i(TAG, f.toString());
                Log.i(TAG, "--------------------------------------------");

                feedbacks.add(f);
            }

            db.close();
        } catch (SQLiteException e)
        {
            Log.i(TAG, e.getMessage());
        }

        return feedbacks;
    }

    @Override
    public void insertData(Feedback feedback) {

        try{
            SQLiteDatabase writable = db.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(db.getCOLUMN_FEEDBACK_VISITORID(),feedback.getVisitor().getVisitorID());
            values.put(db.getCOLUMN_FEEDBACK_DESCRIPTION(),feedback.getDescription());

            writable.insert(db.getDB_TABLE_FEEDBACK_NAME(), null, values);
        }catch(SQLiteException e)
        {
            Log.i(TAG, e.getMessage());
        }

    }
}
