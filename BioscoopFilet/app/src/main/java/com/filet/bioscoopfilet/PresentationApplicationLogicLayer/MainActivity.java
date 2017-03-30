package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.filet.bioscoopfilet.DomainModel.Actor;
import com.filet.bioscoopfilet.DomainModel.Cinema;
import com.filet.bioscoopfilet.DomainModel.Feedback;
import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.DomainModel.Review;
import com.filet.bioscoopfilet.DomainModel.Show;
import com.filet.bioscoopfilet.DomainModel.Theater;
import com.filet.bioscoopfilet.DomainModel.Ticket;
import com.filet.bioscoopfilet.DomainModel.Visitor;
import com.filet.bioscoopfilet.Persistancy.ActorDAO;
import com.filet.bioscoopfilet.Persistancy.CinemaDAO;
import com.filet.bioscoopfilet.Persistancy.DAOFactory;
import com.filet.bioscoopfilet.Persistancy.FeedbackDAO;
import com.filet.bioscoopfilet.Persistancy.FilmDAO;
import com.filet.bioscoopfilet.Persistancy.ReviewDAO;
import com.filet.bioscoopfilet.Persistancy.SQLiteDAOFactory;
import com.filet.bioscoopfilet.Persistancy.ShowDAO;
import com.filet.bioscoopfilet.Persistancy.TheaterDAO;
import com.filet.bioscoopfilet.Persistancy.TicketDAO;
import com.filet.bioscoopfilet.Persistancy.VisitorDAO;
import com.filet.bioscoopfilet.R;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private DAOFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        factory = new SQLiteDAOFactory(getApplicationContext());

        testCinemaDAO();
        testFilmDAO();
        testVisitorData();
        testTicketData();
        testReviewData();
        testFeedbackData();
        testShowData();
        testTheaterData();
        testActorDAO();
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


    //Buttons
    public void informationButton(View v) {
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
    }

    public void filmButton(View v) {
        Intent intent = new Intent(this, FilmOverviewActivity.class);
        startActivity(intent);
    }

    public void myButton(View v) {
        Intent intent = new Intent(this, MyFiletActivity.class);
        startActivity(intent);
    }

    public void agendaButton(View v) {
        Intent intent = new Intent(this, FilmAgendaActivity.class);
        startActivity(intent);
    }

    public void testFeedbackData() {

        Visitor v = new Visitor(2, "Jaap", "Jo");
        FeedbackDAO feedbackDAO = factory.createFeedbackDAO();
        feedbackDAO.insertData(new Feedback(v, "Goeie App!"));
        feedbackDAO.selectData();
    }

    public void testReviewData() {
        ReviewDAO reviewDAO = factory.createReviewDAO();
        reviewDAO.insertData(new Review(new Film(2, new Cinema(2, "Filet", "Breda", "Lovensdijkstraat 1",
                "5000XX", "013-51201230"), "Harry Potter", "Version", "language", "23-03-2017", "Horror", 113, 12,
                "Description description...,", "www.imdb.url", "9.9", "www.trailer.url", "www.poster.url", "Director Niels"), new Visitor("Niels", "van Dam"), 5, "Geweldige film"));
        reviewDAO.selectData();
    }

    public void testTheaterData() {
        TheaterDAO theaterDAO = factory.createTheaterDAO();
        theaterDAO.insertData(new Theater( new Cinema(2, "Filet", "Breda", "Lovensdijkstraat 1",
                "5000XX", "013-51201230"), 150));
        theaterDAO.selectData();
    }

    public void testVisitorData() {
        VisitorDAO visitorDAO = factory.createVisitorDAO();
        visitorDAO.insertData(new Visitor("Tommy", "Heunks"));
    }

    public void testShowData() {
        ShowDAO showDAO = factory.createShowDAO();
        showDAO.insertData(new Show(new Film(2, new Cinema(2, "Filet", "Breda", "Lovensdijkstraat 1",
                "5000XX", "013-51201230"), "Harry Potter", "Version", "language", "23-03-2017", "Horror", 113, 12,
                "Description description...,", "www.imdb.url", "9.9", "www.trailer.url", "www.poster.url", "Director Niels"), new Theater(2, new Cinema(2, "Filet", "Breda", "Lovensdijkstraat 1",
                "5000XX", "013-51201230"), 150), new Date(04, 04, 1994, 10, 10), new Boolean[]{true, false, true,
                false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true,
                false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true,
                false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true,
                false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true,
                false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true,
                false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true,
                false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false}));
//        showDAO.selectData();
    }

    public void testTicketData() {
        Show s = new Show(1, new Film(2, new Cinema(2, "Filet", "Breda", "Lovensdijkstraat 1",
                "5000XX", "013-51201230"), "Harry Potter", "Version", "language", "23-03-2017", "Horror", 113, 12,
                "Description description...,", "www.imdb.url", "9.9", "www.trailer.url", "www.poster.url", "Director Niels"), new Theater(2, new Cinema(2, "Filet", "Breda", "Lovensdijkstraat 1",
                "5000XX", "013-51201230"), 150), new Date(04, 04, 1994, 10, 10), new Boolean[]{true, false, true,
                false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true,
                false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true,
                false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true,
                false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true,
                false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true,
                false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true,
                false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false});
        TicketDAO ticketDAO = factory.createTicketDAO();
        ticketDAO.insertData(new Ticket("f32f2eGW", new Visitor(2, "Niels", "nee"), s, 32)); //NEED A METHOD TO GENERATE RANDOM QRCODES
        ticketDAO.selectData();
    }

    public void testFilmDAO() {
        FilmDAO filmDAO = factory.createFilmDAO();
        filmDAO.insertData(new Film(new Cinema(2, "Filet", "Breda", "Lovensdijkstraat 1",
                "5000XX", "013-51201230"), "Harry Potter", "Version", "language", "23-03-2017", "Horror", 113, 12,
                "Description description...,", "www.imdb.url", "9.9", "www.trailer.url", "www.poster.url", "Director Niels"));
//        filmDAO.selectData();
    }

    public void testActorDAO() {
        ActorDAO actorDAO = factory.createActorDAO();
        actorDAO.insertData(new Actor("Geurtje", "Acteurtje"));
        actorDAO.selectData();
    }

    public void testCinemaDAO() {
        CinemaDAO cinemaDAO = factory.createCinemaDAO();
        cinemaDAO.insertData(new Cinema("Filet", "Breda", "Lovensdijkstraat 1",
                "5000XX", "013-51201230"));
//        cinemaDAO.selectData();
    }
}
