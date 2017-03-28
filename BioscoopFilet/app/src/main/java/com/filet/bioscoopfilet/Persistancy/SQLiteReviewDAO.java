package com.filet.bioscoopfilet.Persistancy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.filet.bioscoopfilet.DomainModel.Review;

import java.util.ArrayList;

/**
 * Created by Niels on 3/28/2017.
 */

public class SQLiteReviewDAO implements ReviewDAO {

    private final String TAG = this.getClass().getSimpleName();
    private DBConnect db;
    private Context context;
    private ArrayList<Review> reviews = new ArrayList<>();

    public SQLiteReviewDAO(Context context) {
        this.context = context;

        db = new DBConnect(context, null, null);
    }

    @Override
    public ArrayList<Review> selectData() {
        try {

            SQLiteDatabase readable = db.getReadableDatabase();

            String query = "SELECT * FROM " + db.getDB_TABLE_REVIEW_NAME();
            Cursor cursor = readable.rawQuery(query, null);

            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Review r = new Review(cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_REVIEWID())),
                        cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_REVIEW_FILMID())),
                        cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_REVIEW_VISITORID())),
                        cursor.getInt(cursor.getColumnIndex(db.getCOLUMN_REVIEW_SCORE())),
                        cursor.getString(cursor.getColumnIndex(db.getCOLUMN_REVIEW_DESCRIPTION())));

                Log.i(TAG, r.toString());
                Log.i(TAG, "--------------------------------------------");

                reviews.add(r);
            }
            db.close();
        } catch (SQLiteException e) {
            Log.i(TAG, e.getMessage());
        }
        return reviews;
    }

    @Override
    public void insertData(Review review) {
        try {
            SQLiteDatabase writable = db.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(db.getCOLUMN_REVIEW_FILMID(), review.getFilmID());
            values.put(db.getCOLUMN_REVIEW_VISITORID(), review.getVisitorID());
            values.put(db.getCOLUMN_REVIEW_SCORE(), review.getScore());
            values.put(db.getCOLUMN_REVIEW_DESCRIPTION(), review.getDescription());

            writable.insert(db.getDB_TABLE_REVIEW_NAME(), null, values);
        } catch (SQLiteException e) {
            Log.i(TAG, e.getMessage());
        }

    }
}
