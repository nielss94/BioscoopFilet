package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MyTicketActivity extends AppCompatActivity {

    TicketAdapter adapter;
    public ArrayList<Ticket> tickets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket);
        Visitor v = new Visitor(1, "Niels", "van Dam");
        Cinema c = new Cinema(1, "Filet", "Breda", "Lovensdijkstraat", "5000XX", "013-57123010");
        Film f = new Film(1, c, "Hiro Potterand saving the cheerleader", "Version", "language", "23-03-2017", "Horror", 113, 12,
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
    }
}
