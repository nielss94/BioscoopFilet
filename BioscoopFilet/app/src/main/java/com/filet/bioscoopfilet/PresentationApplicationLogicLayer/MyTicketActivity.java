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
import com.filet.bioscoopfilet.DomainModel.Show;
import com.filet.bioscoopfilet.DomainModel.Theater;
import com.filet.bioscoopfilet.DomainModel.Ticket;
import com.filet.bioscoopfilet.DomainModel.Visitor;
import com.filet.bioscoopfilet.R;

import java.util.ArrayList;
import java.util.Date;

public class MyTicketActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TicketAdapter adapter;
    public ArrayList<Ticket> tickets = new ArrayList<>();
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.my_tickets);
        setSupportActionBar(myToolbar);

        Visitor v = new Visitor(1, "Niels", "van Dam");
        Cinema c = new Cinema(1, "Filet", "Breda", "Lovensdijkstraat", "5000XX", "013-57123010");
        Film f = new Film(1, c, "Hiro Potter and saving the cheerleader", "Version", "language", "23-03-2017", "Horror", 113, 12,
                "Description description...,", "www.imdb.url", "9.9", "www.trailer.url", "http://static.fjcdn.com/pictures/Hiro_ee085f_349000.jpg", "Director Niels");
        Theater th = new Theater(1, c, 150);
        Show s = new Show(1, f, th, new Date("03/27/2017"));

        adapter = new TicketAdapter(getApplicationContext(), tickets);
        ListView listview = (ListView) findViewById(R.id.ticketsListview);

        Ticket t = new Ticket("f123fdoinj123kf", v, s, 32);
        tickets.add(t);
        t = new Ticket("124kjio3rhtofld", v, s, 32);
        tickets.add(t);
        t = new Ticket("2r1dmnwiun23jf", v, s, 32);
        tickets.add(t);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Ticket ticket = tickets.get(position);

        Intent intent = new Intent(getApplicationContext(), MyTicketDetailActivity.class);
        intent.putExtra("ticket", ticket);
        startActivity(intent);
    }
}
