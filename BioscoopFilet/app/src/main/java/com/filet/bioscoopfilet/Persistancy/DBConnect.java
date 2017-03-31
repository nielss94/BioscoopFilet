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

    private static final int DB_VERSION = 13;
    private static final String DB_NAME = "filet.db";

    private Context context;

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
    private final String COLUMN_SHOW_FILMID = "FilmID";
    private final String COLUMN_SHOW_THEATERID = "TheaterID";
    private final String COLUMN_SHOW_TIME = "Time";
    private final String COLUMN_SHOW_SEATS = "SeatsAsText";
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
    //Film
    private final String DB_TABLE_FILM_NAME = "Film";
    private final String COLUMN_FILMID = "FilmID";
    private final String COLUMN_FILM_CINEMAID = "CinemaID";
    private final String COLUMN_FILM_TITLE = "Title";
    private final String COLUMN_FILM_VERSION = "Version";
    private final String COLUMN_FILM_LANGUAGE = "Language";
    private final String COLUMN_FILM_RELEASEDATE = "ReleaseDate";
    private final String COLUMN_FILM_GENRE = "Genre";
    private final String COLUMN_FILM_LENGTH = "Length";
    private final String COLUMN_FILM_AGE = "Age";
    private final String COLUMN_FILM_DESCRIPTION = "Description";
    private final String COLUMN_FILM_IMDBURL = "IMDBUrl";
    private final String COLUMN_FILM_IMDBSCORE = "IMDBScore";
    private final String COLUMN_FILM_TRAILERURL = "TrailerURL";
    private final String COLUMN_FILM_POSTERURL = "PosterURL";
    private final String COLUMN_FILM_DIRECTOR = "Director";
    //Actor
    private final String DB_TABLE_ACTOR_NAME = "Actor";
    private final String COLUMN_ACTORID = "ActorID";
    private final String COLUMN_ACTOR_FIRSTNAME = "FirstName";
    private final String COLUMN_ACTOR_LASTNAME = "LastName";
    //Cinema
    private final String DB_TABLE_CINEMA_NAME = "Cinema";
    private final String COLUMN_CINEMAID = "CinemaID";
    private final String COLUMN_CINEMA_NAME = "Name";
    private final String COLUMN_CINEMA_CITY = "City";
    private final String COLUMN_CINEMA_ADDRESS = "Address";
    private final String COLUMN_CINEMA_ZIPCODE = "ZipCode";
    private final String COLUMN_CINEMA_PHONE = "Phone";

    public DBConnect(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, DB_NAME, factory, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create actor table
        Log.i(TAG, "Creating Actor table");
        String CREATE_ACTOR_TABLE = "CREATE TABLE "+DB_TABLE_ACTOR_NAME +
                "(" +
                COLUMN_ACTORID + " INTEGER PRIMARY KEY," +
                COLUMN_ACTOR_FIRSTNAME + " TEXT," +
                COLUMN_ACTOR_LASTNAME + " TEXT" +
                ")";
        db.execSQL(CREATE_ACTOR_TABLE);

        //Create cinema table
        Log.i(TAG, "Creating Cinema table");
        String CREATE_CINEMA_TABLE = "CREATE TABLE "+DB_TABLE_CINEMA_NAME +
                "(" +
                COLUMN_CINEMAID + " INTEGER PRIMARY KEY," +
                COLUMN_CINEMA_NAME + " TEXT," +
                COLUMN_CINEMA_CITY + " TEXT," +
                COLUMN_CINEMA_ADDRESS + " TEXT," +
                COLUMN_CINEMA_ZIPCODE + " TEXT," +
                COLUMN_CINEMA_PHONE + " TEXT" +
                ")";
        db.execSQL(CREATE_CINEMA_TABLE);

        //Create visitor table
        Log.i(TAG, "Creating Visitor table");
        String CREATE_VISITOR_TABLE = "CREATE TABLE "+DB_TABLE_VISITOR_NAME +
                "(" +
                COLUMN_VISITORID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_VISITOR_FIRSTNAME + " TEXT," +
                COLUMN_VISITOR_LASTNAME + " TEXT" +
                ")";
        db.execSQL(CREATE_VISITOR_TABLE);

        //Create feedback table
        Log.i(TAG, "Creating Feedback table");
        String CREATE_FEEDBACK_TABLE = "CREATE TABLE "+DB_TABLE_FEEDBACK_NAME +
                "(" +
                COLUMN_FEEDBACKID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FEEDBACK_VISITORID + " INTEGER," +
                COLUMN_FEEDBACK_DESCRIPTION + " TEXT," +
                "FOREIGN KEY ("+COLUMN_FEEDBACK_VISITORID+") REFERENCES "+DB_TABLE_VISITOR_NAME+"("+COLUMN_VISITORID+")" +
                ")";
        db.execSQL(CREATE_FEEDBACK_TABLE);

        //Create theater table
        Log.i(TAG, "Creating Theater table");
        String CREATE_THEATER_TABLE = "CREATE TABLE "+DB_TABLE_THEATER_NAME +
                "(" +
                COLUMN_THEATERID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_THEATER_CINEMAID + " INTEGER," +
                COLUMN_THEATER_NUMBER_OF_SEATS + " INTEGER," +
                "FOREIGN KEY ("+COLUMN_THEATER_CINEMAID+") REFERENCES "+DB_TABLE_CINEMA_NAME+"("+COLUMN_CINEMAID+")" +
                ")";
        db.execSQL(CREATE_THEATER_TABLE);

        //Create film table
        Log.i(TAG, "Creating Film table");
        String CREATE_FILM_TABLE = "CREATE TABLE "+DB_TABLE_FILM_NAME +
                "(" +
                COLUMN_FILMID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FILM_CINEMAID + " INTEGER," +
                COLUMN_FILM_TITLE + " TEXT," +
                COLUMN_FILM_VERSION + " TEXT," +
                COLUMN_FILM_LANGUAGE + " TEXT," +
                COLUMN_FILM_RELEASEDATE + " TEXT," +
                COLUMN_FILM_GENRE + " TEXT," +
                COLUMN_FILM_LENGTH + " INTEGER," +
                COLUMN_FILM_AGE + " INTEGER," +
                COLUMN_FILM_DESCRIPTION + " TEXT," +
                COLUMN_FILM_IMDBURL + " TEXT," +
                COLUMN_FILM_IMDBSCORE + " TEXT," +
                COLUMN_FILM_TRAILERURL + " TEXT," +
                COLUMN_FILM_POSTERURL + " TEXT," +
                COLUMN_FILM_DIRECTOR + " TEXT," +
                "FOREIGN KEY ("+COLUMN_THEATER_CINEMAID+") REFERENCES "+DB_TABLE_CINEMA_NAME+"("+COLUMN_CINEMAID+")" +
                ")";
        db.execSQL(CREATE_FILM_TABLE);

        //Create show table
        Log.i(TAG, "Creating Show table");
        String CREATE_SHOW_TABLE = "CREATE TABLE "+DB_TABLE_SHOW_NAME +
                "(" +
                COLUMN_SHOWID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_SHOW_FILMID + " INTEGER," +
                COLUMN_SHOW_THEATERID + " TEXT," +
                COLUMN_SHOW_TIME + " TEXT," +
                COLUMN_SHOW_SEATS + " TEXT," +
                "FOREIGN KEY ("+COLUMN_SHOW_FILMID+") REFERENCES "+DB_TABLE_FILM_NAME+"("+COLUMN_FILMID+")," +
                "FOREIGN KEY ("+COLUMN_SHOW_THEATERID+") REFERENCES "+DB_TABLE_THEATER_NAME+"("+COLUMN_THEATERID+")" +
                ")";
        db.execSQL(CREATE_SHOW_TABLE);

        //Create ticket table
        Log.i(TAG, "Creating Ticket table");
        String CREATE_TICKET_TABLE = "CREATE TABLE "+DB_TABLE_TICKET_NAME +
                "(" +
                COLUMN_TICKET_QRCODE + " INTEGER PRIMARY KEY," +
                COLUMN_TICKET_VISITORID + " INTEGER," +
                COLUMN_TICKET_SHOWID + " INTEGER," +
                COLUMN_TICKET_SEAT + " INTEGER," +
                "FOREIGN KEY ("+COLUMN_TICKET_VISITORID+") REFERENCES "+DB_TABLE_VISITOR_NAME+"("+COLUMN_VISITORID+")" +
                ")";
        db.execSQL(CREATE_TICKET_TABLE);

        //Create review table
        Log.i(TAG, "Creating Review table");
        String CREATE_REVIEW_TABLE = "CREATE TABLE "+DB_TABLE_REVIEW_NAME +
                "(" +
                COLUMN_REVIEWID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_REVIEW_FILMID + " INTEGER," +
                COLUMN_REVIEW_VISITORID + " INTEGER," +
                COLUMN_REVIEW_SCORE + " INTEGER," +
                COLUMN_REVIEW_DESCRIPTION + " TEXT," +
                "FOREIGN KEY ("+COLUMN_REVIEW_FILMID+") REFERENCES "+DB_TABLE_FILM_NAME+"("+COLUMN_FILMID+")," +
                "FOREIGN KEY ("+COLUMN_REVIEW_VISITORID+") REFERENCES "+DB_TABLE_VISITOR_NAME+"("+COLUMN_VISITORID+")" +
                ")";
        db.execSQL(CREATE_REVIEW_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_VISITOR_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_THEATER_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_FILM_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_CINEMA_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_ACTOR_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_FEEDBACK_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_REVIEW_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_TICKET_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_SHOW_NAME);
        onCreate(db);
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

    //Show table getters
    public String getDB_TABLE_SHOW_NAME() {
        return DB_TABLE_SHOW_NAME;
    }

    public String getCOLUMN_SHOW_FILMID() {
        return COLUMN_SHOW_FILMID;
    }

    public String getCOLUMN_SHOWID() {
        return COLUMN_SHOWID;
    }

    public String getCOLUMN_SHOW_THEATERID() {
        return COLUMN_SHOW_THEATERID;
    }

    public String getCOLUMN_SHOW_TIME() {
        return COLUMN_SHOW_TIME;
    }

    public String getCOLUMN_SHOW_SEATS() {
        return COLUMN_SHOW_SEATS;
    }

    //Theater table getters
    public String getDB_TABLE_THEATER_NAME() {
        return DB_TABLE_THEATER_NAME;
    }

    public String getCOLUMN_THEATERID() {
        return COLUMN_THEATERID;
    }

    public String getCOLUMN_THEATER_CINEMAID() {
        return COLUMN_THEATER_CINEMAID;
    }

    public String getCOLUMN_THEATER_NUMBER_OF_SEATS() {
        return COLUMN_THEATER_NUMBER_OF_SEATS;
    }

    //Ticket table getters
    public String getDB_TABLE_TICKET_NAME() {
        return DB_TABLE_TICKET_NAME;
    }

    public String getCOLUMN_TICKET_QRCODE() {
        return COLUMN_TICKET_QRCODE;
    }

    public String getCOLUMN_TICKET_VISITORID() {
        return COLUMN_TICKET_VISITORID;
    }

    public String getCOLUMN_TICKET_SHOWID() {
        return COLUMN_TICKET_SHOWID;
    }

    public String getCOLUMN_TICKET_SEAT() {
        return COLUMN_TICKET_SEAT;
    }

    //Visitor table getters
    public String getDB_TABLE_VISITOR_NAME() {
        return DB_TABLE_VISITOR_NAME;
    }

    public String getCOLUMN_VISITORID() {
        return COLUMN_VISITORID;
    }

    public String getCOLUMN_VISITOR_FIRSTNAME() {
        return COLUMN_VISITOR_FIRSTNAME;
    }

    public String getCOLUMN_VISITOR_LASTNAME() {
        return COLUMN_VISITOR_LASTNAME;
    }

    //Film table getters
    public String getDB_TABLE_FILM_NAME() {
        return DB_TABLE_FILM_NAME;
    }

    public String getCOLUMN_FILMID() {
        return COLUMN_FILMID;
    }

    public String getCOLUMN_FILM_CINEMAID() {
        return COLUMN_FILM_CINEMAID;
    }

    public String getCOLUMN_FILM_TITLE() {
        return COLUMN_FILM_TITLE;
    }

    public String getCOLUMN_FILM_VERSION() {
        return COLUMN_FILM_VERSION;
    }

    public String getCOLUMN_FILM_LANGUAGE() {
        return COLUMN_FILM_LANGUAGE;
    }

    public String getCOLUMN_FILM_RELEASEDATE() {
        return COLUMN_FILM_RELEASEDATE;
    }

    public String getCOLUMN_FILM_GENRE() {
        return COLUMN_FILM_GENRE;
    }

    public String getCOLUMN_FILM_LENGTH() {
        return COLUMN_FILM_LENGTH;
    }

    public String getCOLUMN_FILM_AGE() {
        return COLUMN_FILM_AGE;
    }

    public String getCOLUMN_FILM_DESCRIPTION() {
        return COLUMN_FILM_DESCRIPTION;
    }

    public String getCOLUMN_FILM_IMDBURL() {
        return COLUMN_FILM_IMDBURL;
    }

    public String getCOLUMN_FILM_IMDBSCORE() {
        return COLUMN_FILM_IMDBSCORE;
    }

    public String getCOLUMN_FILM_TRAILERURL() {
        return COLUMN_FILM_TRAILERURL;
    }

    public String getCOLUMN_FILM_POSTERURL() {
        return COLUMN_FILM_POSTERURL;
    }

    public String getCOLUMN_FILM_DIRECTOR() {
        return COLUMN_FILM_DIRECTOR;
    }

    public String getDB_TABLE_ACTOR_NAME() {
        return DB_TABLE_ACTOR_NAME;
    }

    //Actor table getters
    public String getCOLUMN_ACTORID() {
        return COLUMN_ACTORID;
    }

    public String getCOLUMN_ACTOR_FIRSTNAME() {
        return COLUMN_ACTOR_FIRSTNAME;
    }

    public String getCOLUMN_ACTOR_LASTNAME() {
        return COLUMN_ACTOR_LASTNAME;
    }

    //Cinema table getters
    public String getDB_TABLE_CINEMA_NAME() {
        return DB_TABLE_CINEMA_NAME;
    }

    public String getCOLUMN_CINEMAID() {
        return COLUMN_CINEMAID;
    }

    public String getCOLUMN_CINEMA_NAME() {
        return COLUMN_CINEMA_NAME;
    }

    public String getCOLUMN_CINEMA_CITY() {
        return COLUMN_CINEMA_CITY;
    }

    public String getCOLUMN_CINEMA_ADDRESS() {
        return COLUMN_CINEMA_ADDRESS;
    }

    public String getCOLUMN_CINEMA_ZIPCODE() {
        return COLUMN_CINEMA_ZIPCODE;
    }

    public String getCOLUMN_CINEMA_PHONE() {
        return COLUMN_CINEMA_PHONE;
    }
}
