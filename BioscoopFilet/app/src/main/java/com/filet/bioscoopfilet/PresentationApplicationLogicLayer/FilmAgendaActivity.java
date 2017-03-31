package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.filet.bioscoopfilet.DomainModel.Cinema;
import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.DomainModel.Show;
import com.filet.bioscoopfilet.DomainModel.Theater;
import com.filet.bioscoopfilet.R;

import java.util.ArrayList;
import java.util.Date;

public class FilmAgendaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<Show> shows = new ArrayList<>();

    ListView showList;

    private ShowAdapter showAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_agenda);

        //Setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.films);
        setSupportActionBar(myToolbar);

        //Setting adapter
        showAdapter = new ShowAdapter(this, shows);

        //FOR DEMO
        Cinema cinema = new Cinema(1, null, null, null, null, null);

        Film film = new Film(4, cinema, "Deadpool", "2D OV",
                "EN","30/03/2017","Comic", 90, 16,  "Wade Wilson is een speciale agent van de overheid die, nadat hij onderworpen is aan een behandeling die hem bovenmenselijke genezende krachten heeft gegeven, een alter-ego in de vorm van 'Deadpool' aanneemt. Gewapend met zijn nieuwe vaardigheden en een verwrongen gevoel voor humor jaagt hij op de man die bijna zijn hele leven verwoest heeft.", "",
                "7.5","",  "https://image.tmdb.org/t/p/w500/inVq3FRqcYIRl2la8iZikYYxFNR.jpg",  "");

        Theater theater = new Theater(cinema, 100);
        shows.add(new Show(film,theater, new Date(2017, 04, 04, 17, 50),"0011111100000000000000000000001110010011000011000100000000011111000000000000000000000000000000000000"));


        //Declaration of ListView
        showList = (ListView) findViewById(R.id.filmListView);
        showList.setOnItemClickListener(this);
        showList.setAdapter(showAdapter);

        //Must go in listener
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
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
