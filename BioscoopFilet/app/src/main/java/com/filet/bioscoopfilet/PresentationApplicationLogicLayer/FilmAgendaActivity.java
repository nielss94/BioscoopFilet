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

        //FOR DEMO
        /*Cinema cinema = new Cinema(1, null, null, null, null, null);

        Film film = new Film(4, cinema, "Deadpool", "2D OV",
                "EN","30/03/2017","Comic", 90, 16,  "Wade Wilson is een speciale agent van de overheid die, nadat hij onderworpen is aan een behandeling die hem bovenmenselijke genezende krachten heeft gegeven, een alter-ego in de vorm van 'Deadpool' aanneemt. Gewapend met zijn nieuwe vaardigheden en een verwrongen gevoel voor humor jaagt hij op de man die bijna zijn hele leven verwoest heeft.", "",
                "7.5","",  "https://image.tmdb.org/t/p/w500/inVq3FRqcYIRl2la8iZikYYxFNR.jpg",  "");

        Theater theater = new Theater(cinema, 100);
        shows.add(new Show(film,theater, new Date(2017, 04, 04, 17, 50),"0011111100000000000000000000001110010011000011000100000000011111000000000000000000000000000000000000"));
*/
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
