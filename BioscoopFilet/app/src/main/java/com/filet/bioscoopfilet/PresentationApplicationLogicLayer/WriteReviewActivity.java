package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.DomainModel.Review;
import com.filet.bioscoopfilet.DomainModel.Visitor;
import com.filet.bioscoopfilet.Persistancy.DAOFactory;
import com.filet.bioscoopfilet.Persistancy.ReviewDAO;
import com.filet.bioscoopfilet.Persistancy.SQLiteDAOFactory;
import com.filet.bioscoopfilet.Persistancy.VisitorDAO;
import com.filet.bioscoopfilet.R;

import java.util.ArrayList;

public class WriteReviewActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputName, inputReview;
    ImageView star1, star2, star3, star4, star5;
    Button saveBtn;

    private Film film;

    private DAOFactory factory;
    private ArrayList<Visitor> visitors = new ArrayList<>();
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        //initialise factory
        factory = new SQLiteDAOFactory(getApplicationContext());

        //Setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.film_detail);
        setSupportActionBar(myToolbar);

        //Get intent extras
        film = (Film) getIntent().getSerializableExtra("FILM");
        Log.i("WriteReviewTOAST", film.toString());

        //initialise xml elements
        inputName = (EditText) findViewById(R.id.reviewName);
        inputReview = (EditText) findViewById(R.id.review);
        star1 = (ImageView) findViewById(R.id.reviewStar1);
        star2 = (ImageView) findViewById(R.id.reviewStar2);
        star3 = (ImageView) findViewById(R.id.reviewStar3);
        star4 = (ImageView) findViewById(R.id.reviewStar4);
        star5 = (ImageView) findViewById(R.id.reviewStar5);
        saveBtn = (Button) findViewById(R.id.save_review_btn);

        //add listeners to stars
        star1.setOnClickListener(this);
        star2.setOnClickListener(this);
        star3.setOnClickListener(this);
        star4.setOnClickListener(this);
        star5.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
    }

    public void saveReview() {
        String name = inputName.getText().toString().trim();
        String review = inputReview.getText().toString().trim();

        VisitorDAO visitorDAO = factory.createVisitorDAO();
        visitors = visitorDAO.selectData();
        Visitor visitor = visitors.get(0);


        if(score == 0) {
            Toast.makeText(this, "Please give a rating.", Toast.LENGTH_SHORT).show();
        } else if (name.equals("")) {
            Toast.makeText(this, "Please enter a name.", Toast.LENGTH_SHORT).show();
        } else if (review.equals("")) {
            Toast.makeText(this, "Please enter a review.", Toast.LENGTH_SHORT).show();
        }
        else {
            ReviewDAO reviewDAO = factory.createReviewDAO();
            reviewDAO.insertData(new Review(film.getFilmAPIID(), visitor, score, review));
            reviewDAO.selectData();

            Intent intent = new Intent(getApplicationContext(), ReviewsActivity.class);
            intent.putExtra("FILM", film);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reviewStar1:
                star1.setImageResource(R.drawable.filled_star);
                star2.setImageResource(R.drawable.empty_star);
                star3.setImageResource(R.drawable.empty_star);
                star4.setImageResource(R.drawable.empty_star);
                star5.setImageResource(R.drawable.empty_star);
                score = 1;
                break;
            case R.id.reviewStar2:
                star1.setImageResource(R.drawable.filled_star);
                star2.setImageResource(R.drawable.filled_star);
                star3.setImageResource(R.drawable.empty_star);
                star4.setImageResource(R.drawable.empty_star);
                star5.setImageResource(R.drawable.empty_star);
                score = 2;
                break;
            case R.id.reviewStar3:
                star1.setImageResource(R.drawable.filled_star);
                star2.setImageResource(R.drawable.filled_star);
                star3.setImageResource(R.drawable.filled_star);
                star4.setImageResource(R.drawable.empty_star);
                star5.setImageResource(R.drawable.empty_star);
                score = 3;
                break;
            case R.id.reviewStar4:
                star1.setImageResource(R.drawable.filled_star);
                star2.setImageResource(R.drawable.filled_star);
                star3.setImageResource(R.drawable.filled_star);
                star4.setImageResource(R.drawable.filled_star);
                star5.setImageResource(R.drawable.empty_star);
                score = 4;
                break;
            case R.id.reviewStar5:
                star1.setImageResource(R.drawable.filled_star);
                star2.setImageResource(R.drawable.filled_star);
                star3.setImageResource(R.drawable.filled_star);
                star4.setImageResource(R.drawable.filled_star);
                star5.setImageResource(R.drawable.filled_star);
                score = 5;
                break;
            case R.id.save_review_btn:
                saveReview();
                break;
        }
    }
}
