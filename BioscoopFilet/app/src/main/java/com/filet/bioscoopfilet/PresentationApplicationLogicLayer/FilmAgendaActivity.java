package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.filet.bioscoopfilet.DomainModel.Cinema;
import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.DomainModel.Show;
import com.filet.bioscoopfilet.DomainModel.Theater;
import com.filet.bioscoopfilet.Persistancy.DAOFactory;
import com.filet.bioscoopfilet.Persistancy.SQLiteDAOFactory;
import com.filet.bioscoopfilet.Persistancy.ShowDAO;
import com.filet.bioscoopfilet.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FilmAgendaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private final String TAG = getClass().getSimpleName();

    private ArrayList<Show> shows = new ArrayList<>();
    private ArrayList<Show> selectedShows = new ArrayList<>();

    private Spinner spinner;
    ListView showList;
    private DAOFactory factory;
    private ShowAdapter showAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_agenda);

        //Setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.films);
        setSupportActionBar(myToolbar);

        //Get the spinner
        spinner = (Spinner) findViewById(R.id.showSpinner);

        //Set the factory
        factory = new SQLiteDAOFactory(getApplicationContext());

        //Setting adapter
        showAdapter = new ShowAdapter(this, selectedShows);

        ShowDAO showDAO = factory.createShowDAO();
        shows = showDAO.selectData();


        //Declaration of ListView
        showList = (ListView) findViewById(R.id.filmListView);
        showList.setOnItemClickListener(this);
        showList.setAdapter(showAdapter);

        //Must go in listener
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedShows.clear();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                for (int i = 0; i < shows.size(); i++) {
                    Log.i(TAG, spinner.getSelectedItem().toString() + " and " + sdf.format(shows.get(i).getTime()));

                    if(spinner.getSelectedItem().toString().equals(sdf.format(shows.get(i).getTime())))
                    {
                        selectedShows.add(shows.get(i));
                    }
                }
                showAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Show show = shows.get(position);

        Intent intent = new Intent(getApplicationContext(), SelectedFilmDetailActivity.class);
        intent.putExtra("SHOW", show);
        startActivity(intent);
    }
}
