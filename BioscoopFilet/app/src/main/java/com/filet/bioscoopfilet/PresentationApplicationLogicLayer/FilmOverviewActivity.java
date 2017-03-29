package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;

import com.filet.bioscoopfilet.DomainModel.Actor;
import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.R;

import java.util.ArrayList;

public class FilmOverviewActivity extends AppCompatActivity {

    private ArrayList<Film> films = new ArrayList<>();
    private ArrayList<Actor> actors = new ArrayList<>();

    ListView filmList;

    private FilmAdapter filmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_overview);

        //Setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.films);
        setSupportActionBar(myToolbar);

        films.add(new Film(1, 1, "Harry Potter: en de Steen der Wijzen", "2D NL",
                    "NL","30/03/2017", "Fantasy", 90, 12,  "Goeie!", "",
                    "6.5",  "","https://image.tmdb.org/t/p/w500//dCtFvscYcXQKTNvyyaQr2g2UacJ.jpg",  ""));

        films.add(new Film(1, 1, "Mad Max: Fury Road", "2D OV",
                "EN","30/03/2017", "Actie", 90, 16,  "Goeie!", "",
                "9.3",  "","https://image.tmdb.org/t/p/w500//kqjL17yufvn9OVLyXYpvtyrFfak.jpg",  ""));

        films.add(new Film(1, 1, "Star Wars Episode VII: The Force Awakens", "3D OV",
                "NL","30/03/2017","Sci-Fi", 90, 12,  "Goeie!", "",
                "8.5",""  ,"https://image.tmdb.org/t/p/w500/weUSwMdQIa3NaXVzwUoIIcAi85d.jpg",  ""));

        films.add(new Film(1, 1, "Deadpool", "2D OV",
                "EN","30/03/2017","Comic", 90, 16,  "Goeie!", "",
                "7.5","",  "https://image.tmdb.org/t/p/w500/inVq3FRqcYIRl2la8iZikYYxFNR.jpg",  ""));

        films.add(new Film(1, 1, "Logan", "2D OV",
                "EN","30/03/2017","Comic", 90, 16,  "Goeie!", "",
                "8.5","",  "https://image.tmdb.org/t/p/w500/45Y1G5FEgttPAwjTYic6czC9xCn.jpg",  ""));

        films.add(new Film(1, 1, "Minions", "3D NL",
                "NL","30/03/2017","Comedy", 90, 3,  "Goeie!", "",
                "5.5","",  "https://image.tmdb.org/t/p/w500/q0R4crx2SehcEEQEkYObktdeFy.jpg",  ""));

        films.add(new Film(1, 1, "Source Code", "2D OV",
                "EN","30/03/2017", "Action",90, 12,  "Goeie!", "",
                "10", "", "https://image.tmdb.org/t/p/w500/cpl7R5d3qwWvykRRSxdhQ0htstU.jpg",  ""));

        //Setting adapter
        filmAdapter = new FilmAdapter(this, films);

        //Declaration of ListView
        filmList = (ListView) findViewById(R.id.filmListView);
        filmList.setAdapter(filmAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
