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
    //Feedback
    private final String DB_TABLE_FEEDBACK_NAME = "Feedback";
    private final String COLUMN_FEEDBACKID = "ID";
    private final String COLUMN_FEEDBACK_VISITORID = "VisitorID";
    private final String COLUMN_FEEDBACK_DESCRIPTION = "Description";
    //Review
    private final String DB_TABLE_REVIEW_NAME = "Review";
    private final String COLUMN_REVIEWID = "ID";
    private final String COLUMN_REVIEW_FILMID = "FilmID";
    private final String COLUMN_REVIEW_VISITORID = "VisitorID";
    private final String COLUMN_REVIEW_SCORE = "Score";
    private final String COLUMN_REVIEW_DESCRIPTION = "Description";
    //Show
    private final String DB_TABLE_SHOW_NAME = "Show";
    private final String COLUMN_SHOWID = "ShowID";
    private final String COLUMN_SHOW_THEATHERID = "TheaterID";
    private final String COLUMN_SHOW_TIME = "Time";
    private final String COLUMN_SEATS = "SeatsAsText";
    //Theater
    private final String DB_TABLE_THEATER_NAME = "Theater";
    private final String COLUMN_THEATERID = "ID";
    private final String COLUMN_THEATER_CINEMAID = "CinemaID";
    private final String COLUMN_THEATER_NUMBER_OF_SEATS = "NumberOfSeats";
    //Ticket
    private final String DB_TABLE_TICKET_NAME = "Ticket";
    private final String COLUMN_TICKET_QRCODE = "QRCode";
    private final String COLUMN_TICKET_VISITORID = "VisitorID";
    private final String COLUMN_TICKET_SHOWID = "ShowID";
    private final String COLUMN_TICKET_SEAT = "Seat";
    //Visitor
    private final String DB_TABLE_VISITOR_NAME = "Visitor";
    private final String COLUMN_VISITORID = "VisitorID";
    private final String COLUMN_VISITOR_FIRSTNAME = "FirstName";
    private final String COLUMN_VISITOR_LASTNAME = "LastName";




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

    //Feedback table getters


    public String getDB_TABLE_FEEDBACK_NAME() {
        return DB_TABLE_FEEDBACK_NAME;
    }

    public String getCOLUMN_FEEDBACKID() {
        return COLUMN_FEEDBACKID;
    }

    public String getCOLUMN_FEEDBACK_VISITORID() {
        return COLUMN_FEEDBACK_VISITORID;
    }

    public String getCOLUMN_FEEDBACK_DESCRIPTION() {
        return COLUMN_FEEDBACK_DESCRIPTION;
    }

    //Review table getters
    public String getDB_TABLE_REVIEW_NAME() {
        return DB_TABLE_REVIEW_NAME;
    }

    public String getCOLUMN_REVIEWID() {
        return COLUMN_REVIEWID;
    }

    public String getCOLUMN_REVIEW_FILMID() {
        return COLUMN_REVIEW_FILMID;
    }

    public String getCOLUMN_REVIEW_VISITORID() {
        return COLUMN_REVIEW_VISITORID;
    }

    public String getCOLUMN_REVIEW_SCORE() {
        return COLUMN_REVIEW_SCORE;
    }

    public String getCOLUMN_REVIEW_DESCRIPTION() {
        return COLUMN_REVIEW_DESCRIPTION;
    }
}
