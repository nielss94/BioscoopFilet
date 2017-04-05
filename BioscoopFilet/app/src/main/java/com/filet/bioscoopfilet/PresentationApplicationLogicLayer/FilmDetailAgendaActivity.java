package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.filet.bioscoopfilet.DomainModel.Actor;
import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class FilmDetailAgendaActivity extends AppCompatActivity implements TrailerApiConnector.TrailerAvailable, 
        DirectorApiConnector.DirectorAvailable, ActorApiConnector.ActorAvailable, RuntimeApiConnector.RuntimeAvailable{

    TextView title;
    TextView version;
    TextView length;
    TextView age;
    TextView imdbScore;
    TextView director;
    TextView actors;
    TextView description;
    TextView release;

    ImageView poster;

    Film film;

    private String language;
    private SharedPreferences languagepref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail_agenda);

        //Getting film given by FilmOverviewActivity
        film = (Film) getIntent().getSerializableExtra("FILM");

        TrailerApiConnector getTrailer = new TrailerApiConnector(this);
        getTrailer.execute("https://api.themoviedb.org/3/movie/"+film.getFilmAPIID()+"/videos?api_key=863618e1d5c5f5cc4e34a37c49b8338e&language=en_US");

        DirectorApiConnector getDirector = new DirectorApiConnector(this);
        getDirector.execute("https://api.themoviedb.org/3/movie/"+film.getFilmAPIID()+"/credits?api_key=863618e1d5c5f5cc4e34a37c49b8338e");

        ActorApiConnector getActor = new ActorApiConnector(this);
        getActor.execute("https://api.themoviedb.org/3/movie/"+film.getFilmAPIID()+"/credits?api_key=863618e1d5c5f5cc4e34a37c49b8338e");

        RuntimeApiConnector getRuntime = new RuntimeApiConnector(this);
        getRuntime.execute("https://api.themoviedb.org/3/movie/"+film.getFilmAPIID()+"?api_key=863618e1d5c5f5cc4e34a37c49b8338e&language=nl");

        //Setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.film_detail);
        setSupportActionBar(myToolbar);

        //Declaration of TextViews
        title = (TextView) findViewById(R.id.movieTitleDetailId);
        version = (TextView) findViewById(R.id.movieVersionDetailId);
        length  = (TextView) findViewById(R.id.movieLengthDetailId);
        age = (TextView) findViewById(R.id.movieAgeDetailId);
        imdbScore = (TextView) findViewById(R.id.movieIMDBDetailId);
        director = (TextView) findViewById(R.id.movieDirectorDetailId);
        actors = (TextView) findViewById(R.id.movieActorsDetailId);
        description = (TextView) findViewById(R.id.movieDescriptionDetailId);
        release = (TextView) findViewById(R.id.movieReleaseDetailId);

        //Declaration of ImageView
        poster = (ImageView) findViewById(R.id.posterImageDetailedId);

        //Setting film info in TextViews
        title.setText(film.getTitle());
        version.setText(getResources().getString(R.string.version)  + " " + film.getVersion());
        release.setText(getResources().getString(R.string.release) + " " + film.getReleaseDate());
//        length.setText("");

        if (film.getAge() == 99){
            age.setText(getResources().getString(R.string.not_yet_rated));
        }
        else{
            age.setText(getResources().getString(R.string.age) + " " + film.getAge());
        }

        imdbScore.setText(getResources().getString(R.string.imdb) + " " + film.getIMDBScore());
//        director.setText("");
//        actors.setText("");
        description.setText(film.getDescription());

        //Setting poster image in ImageView
        Picasso.with(this).load(film.getPosterURL()).into(poster);

        languagepref = getSharedPreferences("language", MODE_PRIVATE);
        language = languagepref.getString("languageToLoad", Locale.getDefault().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        String oldLanguage = language;

        language = languagepref.getString("languageToLoad", Locale.getDefault().toString());


        if (!oldLanguage.equals(language)) {
            finish();
            startActivity(getIntent());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_review, menu);

        MenuItem item = menu.findItem(R.id.action_lang);

        Log.i("Taal", Locale.getDefault().toString());
        if (Locale.getDefault().toString().equalsIgnoreCase("en_us")) {
            item.setIcon(R.drawable.united_states);
        }
        else if (Locale.getDefault().toString().equalsIgnoreCase("en_gb")) {
            item.setIcon(R.drawable.united_kingdom);
        }
        else if (Locale.getDefault().toString().equalsIgnoreCase("nl_nl")) {
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

    public void openAgenda(View v)
    {
        Intent intent = new Intent(getApplicationContext(),FilmAgendaActivity.class);
        startActivity(intent);
    }


    @Override
    public void trailerAvailable(String trailerURL) {
        film.setTrailerURL(trailerURL);
    }

    @Override
    public void directorAvailable(String directorString) {
        film.setDirector(directorString);
        director.setText("Director: " + directorString);
    }

    @Override
    public void actorAvailable(ArrayList<Actor> actorsList) {
        film.setActors(actorsList);
        actors.setText(getResources().getString(R.string.actors) + " " + actorsList.get(0).getFirstName()
                + ", " + actorsList.get(1).getFirstName() + ", " + actorsList.get(2).getFirstName());
    }

    @Override
    public void runtimeAvailable(String runtime) {
        film.setLength(Integer.parseInt(runtime));
        length.setText(runtime + " " + getResources().getString(R.string.minutes));
    }

    public void trailerButton(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(film.getTrailerURL()));
        startActivity(intent);
    }
}
