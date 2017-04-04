package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import java.util.Locale;

public class WriteReviewActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputName, inputReview;
    ImageView star1, star2, star3, star4, star5;
    Button saveBtn;

    private Film film;

    private DAOFactory factory;
    private ArrayList<Visitor> visitors = new ArrayList<>();
    private int score = 0;

    private String language;
    private SharedPreferences languagepref;

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

        languagepref = getSharedPreferences("language", MODE_PRIVATE);
        language = languagepref.getString("languageToLoad", Locale.getDefault().getDisplayLanguage());
    }

    @Override
    public void onResume() {
        super.onResume();
        String oldLanguage = language;

        language = languagepref.getString("languageToLoad", Locale.getDefault().getDisplayLanguage());


        if (!oldLanguage.equals(language)) {
            finish();
            startActivity(getIntent());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.action_lang);

        Log.i("Taal", Locale.getDefault().toString());
        if (Locale.getDefault().toString().equalsIgnoreCase("en_us")) {
            item.setIcon(R.drawable.united_states);
        }
        if (Locale.getDefault().toString().equalsIgnoreCase("en_gb")) {
            item.setIcon(R.drawable.united_kingdom);
        }
        if (Locale.getDefault().toString().equalsIgnoreCase("nl_nl")) {
            item.setIcon(R.drawable.netherlands);
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_lang:
                PopupMenu popup = new PopupMenu(this, findViewById(R.id.action_lang));
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_lang, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_EN_US:
                                Log.i("MenuItemSelected", "ENGELS US");
                                Locale locale = new Locale("en", "US");
                                Locale.setDefault(locale);
                                Configuration config = getBaseContext().getResources().getConfiguration();
                                config.locale = locale;
                                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

                                Intent intent = getIntent();
                                intent.addFlags( Intent.FLAG_ACTIVITY_NO_ANIMATION );
                                intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                                startActivity(intent);
                                return true;

                            case R.id.action_NL:
                                Log.i("MenuItemSelected", "NEDERLANDS");
                                Locale locale2 = new Locale("nl", "NL");
                                Locale.setDefault(locale2);
                                Configuration config2 = getBaseContext().getResources().getConfiguration();
                                config2.locale = locale2;
                                getBaseContext().getResources().updateConfiguration(config2, getBaseContext().getResources().getDisplayMetrics());

                                Intent intent2 = getIntent();
                                intent2.addFlags( Intent.FLAG_ACTIVITY_NO_ANIMATION );
                                intent2.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                                startActivity(intent2);
                                return true;

                            case R.id.action_EN_UK:
                                Log.i("MenuItemSelected", "ENGELS");
                                Locale locale3 = new Locale("en", "GB");
                                Locale.setDefault(locale3);
                                Configuration config3 = getBaseContext().getResources().getConfiguration();
                                config3.locale = locale3;
                                getBaseContext().getResources().updateConfiguration(config3, getBaseContext().getResources().getDisplayMetrics());

                                Intent intent3 = getIntent();
                                intent3.addFlags( Intent.FLAG_ACTIVITY_NO_ANIMATION );
                                intent3.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                                startActivity(intent3);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();
                return true;
            case R.id.action_review:
                Intent intent = new Intent(getApplicationContext(), ReviewsActivity.class);
                intent.putExtra("FILM", film);
                Log.i("FilmDetailAgendaActvty", film.toString());
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
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
