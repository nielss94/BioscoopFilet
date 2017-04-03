package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.filet.bioscoopfilet.DomainModel.Actor;
import com.filet.bioscoopfilet.DomainModel.Cinema;
import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.R;

import java.util.ArrayList;

public class FilmOverviewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, FilmApiConnector.FilmsAvailable {

    private ArrayList<Film> films = new ArrayList<>();

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

        //Setting adapter
        filmAdapter = new FilmAdapter(this, films);

        //Declaration of ListView
        filmList = (ListView) findViewById(R.id.filmListView);
        filmList.setOnItemClickListener(this);
        filmList.setAdapter(filmAdapter);

        String[] urls = new String[]{"https://api.themoviedb.org/3/movie/upcoming?api_key=863618e1d5c5f5cc4e34a37c49b8338e&language=nl"};
        FilmApiConnector getFilms = new FilmApiConnector(this);
        getFilms.execute(urls);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Film film = films.get(position);

        Intent intent = new Intent(getApplicationContext(), FilmDetailAgendaActivity.class);
        intent.putExtra("FILM", film);
        startActivity(intent);
    }

    @Override
    public void filmsAvailable(ArrayList<Film> result) {

        findViewById(R.id.loadingPanel).setVisibility(View.GONE);

        //Clear current products
        films.clear();

        //Put new products in ArrayList
        for (Film f: result) {
            films.add(f);
        }

        //Notify the changes
        filmAdapter.notifyDataSetChanged();
    }

}
