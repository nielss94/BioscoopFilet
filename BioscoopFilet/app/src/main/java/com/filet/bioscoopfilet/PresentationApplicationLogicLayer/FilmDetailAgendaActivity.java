package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.filet.bioscoopfilet.DomainModel.Actor;
import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
        age.setText(getResources().getString(R.string.age) + " " + film.getAge());
        imdbScore.setText(getResources().getString(R.string.imdb) + " " + film.getIMDBScore());
//        director.setText("");
//        actors.setText("");
        description.setText(film.getDescription().toString());

        //Setting poster image in ImageView
        Picasso.with(this).load(film.getPosterURL()).into(poster);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
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
