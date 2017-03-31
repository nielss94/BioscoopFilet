package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.R;
import com.squareup.picasso.Picasso;

public class MyFilmDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_film_detail);

        //Setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.film_detail);
        setSupportActionBar(myToolbar);

        //Get intent extras
        Film film = (Film) getIntent().getSerializableExtra("FILM");

        //Initialise xml elements
        ImageView poster = (ImageView) findViewById(R.id.posterImageDetailedId);
        TextView title = (TextView) findViewById(R.id.movieTitleDetailId);
        TextView version = (TextView) findViewById(R.id.movieVersionDetailId);
        TextView releaseDate = (TextView) findViewById(R.id.movieReleaseDetailId);
        TextView length = (TextView) findViewById(R.id.movieLengthDetailId);
        TextView age = (TextView) findViewById(R.id.movieAgeDetailId);
        TextView imdbScore = (TextView) findViewById(R.id.movieIMDBDetailId);
        TextView director = (TextView) findViewById(R.id.movieDirectorDetailId);
        TextView actors = (TextView) findViewById(R.id.movieActorsDetailId);
        TextView description = (TextView) findViewById(R.id.movieDescriptionDetailId);

        //fill xml elements with intent extrastitle.setText(film.getTitle());
        Picasso.with(this).load(film.getPosterURL()).into(poster);
        title.setText(film.getTitle());
        version.setText(getResources().getString(R.string.version)  + " " + film.getVersion());
        releaseDate.setText(getResources().getString(R.string.release) + " " + film.getReleaseDate());
        length.setText(getResources().getString(R.string.length) + " " + film.getLength());
        age.setText(getResources().getString(R.string.age) + " " + film.getAge());
        imdbScore.setText(getResources().getString(R.string.imdb) + " " + film.getIMDBScore());
//        director.setText();
//        actors.setText();
        description.setText(film.getDescription());
    }
}
