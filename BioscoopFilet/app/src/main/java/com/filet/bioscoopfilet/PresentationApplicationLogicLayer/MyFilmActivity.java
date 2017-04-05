package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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
import com.filet.bioscoopfilet.DomainModel.Ticket;
import com.filet.bioscoopfilet.Persistancy.DAOFactory;
import com.filet.bioscoopfilet.Persistancy.FilmDAO;
import com.filet.bioscoopfilet.Persistancy.SQLiteDAOFactory;
import com.filet.bioscoopfilet.Persistancy.TicketDAO;
import com.filet.bioscoopfilet.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class MyFilmActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DAOFactory factory;
    private final String TAG = getClass().getSimpleName();

    private ArrayList<Film> allFilms = new ArrayList<>();
    private ArrayList<Film> myFilms = new ArrayList<>();
    private ArrayList<Ticket> tickets = new ArrayList<>();
    ListView filmList;
    private FilmAdapter filmAdapter;

    private String language;
    private SharedPreferences languagepref;

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
        allFilms = filmDAO.selectData();
        //Get show information from DB
        TicketDAO ticketDAO = factory.createTicketDAO();
        tickets = ticketDAO.selectData();

        //Check if the film is a film that the user has watched
        for (int i = 0; i < allFilms.size(); i++) {
            for (int j = 0; j < tickets.size(); j++) {
                if (allFilms.get(i).getFilmAPIID() == tickets.get(j).getShow().getFilmAPIID()) {
                    if (!myFilms.contains(allFilms.get(i))) {
                        myFilms.add(allFilms.get(i));
                    }
                }
            }
        }

        Cinema cinema = new Cinema(1, null, null, null, null, null);

        //Setting adapter
        filmAdapter = new FilmAdapter(this, myFilms);

        Log.i(TAG, allFilms.size() + "");

        //Declaration of ListView
        filmList = (ListView) findViewById(R.id.filmListView);
        filmList.setOnItemClickListener(this);
        filmList.setAdapter(filmAdapter);

        //Stop loading animation
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);

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
        inflater.inflate(R.menu.menu_order, menu);

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
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
                                intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
                                intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent3);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();
                return true;
            case R.id.action_sort:
                PopupMenu popup2 = new PopupMenu(this, findViewById(R.id.action_lang));
                MenuInflater inflater2 = popup2.getMenuInflater();
                inflater2.inflate(R.menu.menu_orderlist, popup2.getMenu());
                popup2.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_sort_score_desc:
                                Collections.sort(myFilms, Film.DESCENDING_SCORE);
                                filmAdapter.notifyDataSetChanged();
                                return true;

                            case R.id.action_sort_score_asc:
                                Collections.sort(myFilms, Film.ASCENDING_SCORE);
                                filmAdapter.notifyDataSetChanged();
                                return true;

                            case R.id.action_sort_age_desc:
                                Collections.sort(myFilms, Film.DESCENDING_AGE);
                                filmAdapter.notifyDataSetChanged();
                                return true;

                            case R.id.action_sort_age_asc:
                                Collections.sort(myFilms, Film.ASCENDING_AGE);
                                filmAdapter.notifyDataSetChanged();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup2.show();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Film film = myFilms.get(position);

        Intent intent = new Intent(getApplicationContext(), MyFilmDetailActivity.class);
        intent.putExtra("FILM", film);
        startActivity(intent);
    }
}
