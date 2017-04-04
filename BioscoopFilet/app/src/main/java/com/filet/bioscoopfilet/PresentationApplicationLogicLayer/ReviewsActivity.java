package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.DomainModel.Review;
import com.filet.bioscoopfilet.Persistancy.DAOFactory;
import com.filet.bioscoopfilet.Persistancy.ReviewDAO;
import com.filet.bioscoopfilet.Persistancy.SQLiteDAOFactory;
import com.filet.bioscoopfilet.R;

import java.util.ArrayList;

public class ReviewsActivity extends AppCompatActivity {

    private DAOFactory factory;

    Button button;
    private Film film;
    private ArrayList<Review> allReviews = new ArrayList<>();
    private ArrayList<Review> selectedReviews = new ArrayList<>();
    private ReviewAdapter adapter;
    private ListView reviewListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        //Initialise factory & get data
        factory = new SQLiteDAOFactory(getApplicationContext());
        ReviewDAO reviewDAO = factory.createReviewDAO();
        allReviews = reviewDAO.selectData();

        //Get intent extras
        film = (Film) getIntent().getSerializableExtra("FILM");
        if (film != null) {
            Log.i("ReviewsActivityTEST", film.toString());
        }

        for (Review r : allReviews ) {
            if (r.getFilmAPIID() == film.getFilmAPIID()) {
                selectedReviews.add(r);
            }
        }

        //Initialise adapter
        adapter = new ReviewAdapter(getApplicationContext(), selectedReviews);
        reviewListView = (ListView) findViewById(R.id.reviewListView);
        reviewListView.setAdapter(adapter);

        //Setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.film_detail);
        setSupportActionBar(myToolbar);

        //initialise button
        button = (Button) findViewById(R.id.write_review_btn);
    }

    public void writeReviewButton(View v) {
        Intent intent = new Intent(this, WriteReviewActivity.class);
        intent.putExtra("FILM", film);
        startActivity(intent);
        finish();
    }
}
