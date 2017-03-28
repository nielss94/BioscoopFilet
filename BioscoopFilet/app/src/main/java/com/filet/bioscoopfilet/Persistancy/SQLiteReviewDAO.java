//package com.filet.bioscoopfilet.Persistancy;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.util.Log;
//
//import com.filet.bioscoopfilet.DomainModel.Feedback;
//import com.filet.bioscoopfilet.DomainModel.Review;
//
//import java.util.ArrayList;
//
///**
// * Created by Niels on 3/28/2017.
// */
//
//public class SQLiteReviewDAO implements ReviewDAO {
//
//    private final String TAG = this.getClass().getSimpleName();
//
//    private DBConnect db;
//    private Context context;
//
//    private ArrayList<Review> reviews = new ArrayList<>();
//
//    public SQLiteReviewDAO(Context context)
//    {
//        this.context = context;
//
//        db = new DBConnect(context, null, null,1);
//
//    }
//
//    @Override
//    public ArrayList<Review> selectData() {
//
//        SQLiteDatabase readable = db.getReadableDatabase();
//
//        String query = "SELECT * FROM " + db.get();
//        Cursor cursor = readable.rawQuery(query, null);
//
//        cursor.moveToFirst();
//        while(cursor.moveToNext() ) {
//            Feedback f = new Feedback(cursor.getInt(cursor.getColumnIndex(db.getColumnFeedbackid())),
//                    cursor.getInt(cursor.getColumnIndex(db.getColumnFeedbackVisitorid())),
//                    cursor.getString(cursor.getColumnIndex(db.getColumnFeedbackDescription())));
//
//            Log.i(TAG, f.toString());
//            Log.i(TAG, "--------------------------------------------");
//
//            reviews.add(f);
//        }
//
//        db.close();
//        return reviews;
//    }
//
//    @Override
//    public void insertData(Review review) {
//
//        SQLiteDatabase writable = db.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//
//        values.put(db.getColumnFeedbackVisitorid(),review.getVisitorID());
//        values.put(db.getColumnFeedbackDescription(),review.getDescription());
//
//        writable.insert(db.getDbTableFeedbackName(), null, values);
//    }
//
//}
