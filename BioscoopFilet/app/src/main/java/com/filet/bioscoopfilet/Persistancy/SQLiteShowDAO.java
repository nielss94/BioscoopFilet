package com.filet.bioscoopfilet.Persistancy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.DomainModel.Show;
import com.filet.bioscoopfilet.DomainModel.Theater;

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
    private ArrayList<Film> films = new ArrayList<>();
    private ArrayList<Theater> theaters = new ArrayList<>();

    public SQLiteShowDAO(Context context)
    {
        this.context = context;

        db = new DBConnect(context,null,null);
    }

    @Override
    public ArrayList<Show> selectData() {

        try{
            SQLiteDatabase readable = db.getReadableDatabase();

            String query = "SELECT * FROM " + db.getDB_TABLE_SHOW_NAME();
            Cursor cursor = readable.rawQuery(query, null);

            FilmDAO filmDAO = new SQLiteFilmDAO(context);
            films = filmDAO.selectData();

            TheaterDAO theaterDAO = new SQLiteTheaterDAO(context);
            theaters = theaterDAO.selectData();

            cursor.moveToFirst();
            while(cursor.moveToNext() ) {
                Film f = null;
                Theater t = null;
                Show s = null;
                for (int i = 0; i < films.size(); i++) {
                    if (films.get(i).getFilmID() == cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_SHOW_FILMID()))) {
                        f = films.get(i);
                    }
                }
                for (int i = 0; i < theaters.size(); i++) {
                    if (theaters.get(i).getTheaterID() == cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_SHOW_THEATERID()))) {
                        t = theaters.get(i);
                    }
                }
                s = new Show(cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_SHOWID())),
                        f,
                        t,
                        new Date(cursor.getString(cursor.getColumnIndex(db.getCOLUMN_SHOW_TIME())))); //Test the time!!
                String seats = cursor.getString(cursor.getColumnIndex(db.getCOLUMN_SHOW_SEATS()));
                ArrayList<Boolean> seatsList = new ArrayList<>();
                for (int i = 0; i < seats.length(); i++) {
                    if(seats.charAt(i) == 1)
                    {
                        seatsList.add(true);
                    }
                    else{
                        seatsList.add(false);
                    }
                }
                s.setSeats(seatsList.toArray(new Boolean[seatsList.size()]));
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


            values.put(db.getCOLUMN_SHOW_FILMID(),show.getFilm().getFilmID());
            values.put(db.getCOLUMN_SHOW_THEATERID(),show.getTheater().getTheaterID());
            //Test this!!
            values.put(db.getCOLUMN_SHOW_TIME(),show.getTime().toString());

            String seatsAsText = "";
            for (int i = 0; i < show.getSeats().length; i++) {
                if(show.getSeats()[i] == true)
                {
                    seatsAsText = seatsAsText + "1";
                }
                else{
                    seatsAsText = seatsAsText + "0";
                }
            }
            values.put(db.getCOLUMN_SHOW_SEATS(),seatsAsText);

            writable.insert(db.getDB_TABLE_SHOW_NAME(), null, values);
        }catch(SQLiteException e)
        {
            Log.i(TAG,e.getMessage());
        }

    }
}
