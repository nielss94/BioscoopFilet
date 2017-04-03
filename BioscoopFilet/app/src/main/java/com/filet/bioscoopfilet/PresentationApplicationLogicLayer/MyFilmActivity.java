package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.filet.bioscoopfilet.DomainModel.Cinema;
import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.Persistancy.DAOFactory;
import com.filet.bioscoopfilet.Persistancy.FilmDAO;
import com.filet.bioscoopfilet.Persistancy.SQLiteDAOFactory;
import com.filet.bioscoopfilet.R;

import java.util.ArrayList;

public class MyFilmActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DAOFactory factory;
    private final String TAG = getClass().getSimpleName();

    private ArrayList<Film> films = new ArrayList<>();
    ListView filmList;
    private FilmAdapter filmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_overview);

        //Setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.my_films);
        setSupportActionBar(myToolbar);

        //Get film information from DB
        factory = new SQLiteDAOFactory(getApplicationContext());
        FilmDAO filmDAO = factory.createFilmDAO();
        films = filmDAO.selectData();

        Cinema cinema = new Cinema(1, null, null, null, null, null);

        //Setting adapter
        filmAdapter = new FilmAdapter(this, films);

        Log.i(TAG, films.size() + "");

        //Declaration of ListView
        filmList = (ListView) findViewById(R.id.filmListView);
        filmList.setOnItemClickListener(this);
        filmList.setAdapter(filmAdapter);

        //Stop loading animation
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_lang:
                PopupMenu popup = new PopupMenu(this, findViewById(R.id.action_lang));
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_lang, popup.getMenu());
                popup.show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Film film = films.get(position);

        Intent intent = new Intent(getApplicationContext(), MyFilmDetailActivity.class);
        intent.putExtra("FILM", film);
        startActivity(intent);
    }
}
