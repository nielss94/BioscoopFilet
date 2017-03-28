package com.filet.bioscoopfilet.Persistancy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Niels on 3/28/2017.
 */

public class DBConnect extends SQLiteOpenHelper {

    private final String TAG = this.getClass().getSimpleName();

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "filet.db";

    //Tables and columns
    private final String DB_TABLE_FEEDBACK_NAME = "Feedback";
    private final String COLUMN_FEEDBACKID = "ID";
    private final String COLUMN_FEEDBACK_VISITORID = "VisitorID";
    private final String COLUMN_FEEDBACK_DESCRIPTION = "Description";

    public DBConnect(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Creating Feedback table");
        String CREATE_FEEDBACK_TABLE = "CREATE TABLE "+DB_TABLE_FEEDBACK_NAME +
                "(" +
                COLUMN_FEEDBACKID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FEEDBACK_VISITORID + " INTEGER," +
                COLUMN_FEEDBACK_DESCRIPTION + " TEXT" +
                ")";
        db.execSQL(CREATE_FEEDBACK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public String getDbTableFeedbackName() {
        return DB_TABLE_FEEDBACK_NAME;
    }

    public String getColumnFeedbackid() {
        return COLUMN_FEEDBACKID;
    }

    public String getColumnFeedbackVisitorid() {
        return COLUMN_FEEDBACK_VISITORID;
    }

    public String getColumnFeedbackDescription() {
        return COLUMN_FEEDBACK_DESCRIPTION;
    }
}
