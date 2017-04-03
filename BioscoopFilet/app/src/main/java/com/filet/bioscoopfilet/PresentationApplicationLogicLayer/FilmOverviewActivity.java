package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.Persistancy.DAOFactory;
import com.filet.bioscoopfilet.Persistancy.FilmDAO;
import com.filet.bioscoopfilet.Persistancy.SQLiteDAOFactory;
import com.filet.bioscoopfilet.R;

import java.util.ArrayList;

public class FilmOverviewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<Film> films = new ArrayList<>();

    ListView filmList;
    private FilmAdapter filmAdapter;
    private DAOFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_overview);

        //Setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.films);
        setSupportActionBar(myToolbar);


        //Declaration of ListView
        filmList = (ListView) findViewById(R.id.filmListView);
        filmList.setOnItemClickListener(this);

        //Create factory and get data
        factory = new SQLiteDAOFactory(getApplicationContext());
        FilmDAO filmDAO = factory.createFilmDAO();
        films = filmDAO.selectData();

        //Stop animation
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);

        //Setting adapter
        filmAdapter = new FilmAdapter(this, films);
        filmList.setAdapter(filmAdapter);
        filmAdapter.notifyDataSetChanged();
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
}
