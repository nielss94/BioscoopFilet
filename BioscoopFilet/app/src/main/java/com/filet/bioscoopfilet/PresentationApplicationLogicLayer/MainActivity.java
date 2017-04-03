package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private DAOFactory factory;

    private String language;
    private SharedPreferences languagepref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        languagepref = getSharedPreferences("language",MODE_PRIVATE);
        language = languagepref.getString("languageToLoad", Locale.getDefault().getDisplayLanguage());


        factory = new SQLiteDAOFactory(getApplicationContext());

        
//        testCinemaDAO();
//        testFilmDAO();
//        testVisitorData();
//        testFeedbackData();
//        testTheaterData();
//        testShowData();
//        testTicketData();
//        testReviewData();
//        testActorDAO();
    }

    @Override
    public void onResume(){
        super.onResume();

        String oldLanguage = language;

        language = languagepref.getString("languageToLoad", Locale.getDefault().getDisplayLanguage());

        if (!oldLanguage.equals(language)){
            finish();
            startActivity(getIntent());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.action_lang);

        Log.i("Taal", Locale.getDefault().toString());
        if (Locale.getDefault().toString().equalsIgnoreCase("en_us")){
            item.setIcon(R.drawable.united_states);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_lang:
                // User chose the "Settings" item, show the app settings UI...
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
        VisitorDAO visitorDAO = factory.createVisitorDAO();
        Visitor v = visitorDAO.selectData().get(0);

        FeedbackDAO feedbackDAO = factory.createFeedbackDAO();
        feedbackDAO.insertData(new Feedback(v, "Goeie App!"));
        feedbackDAO.selectData();
    }

    public void testReviewData() {
        FilmDAO filmDAO = factory.createFilmDAO();
        ReviewDAO reviewDAO = factory.createReviewDAO();
        VisitorDAO visitorDAO = factory.createVisitorDAO();

        Visitor v = visitorDAO.selectData().get(0);
        Film f = filmDAO.selectData().get(0);

        reviewDAO.insertData(new Review(f, v, 5, "Geweldige film"));
        reviewDAO.selectData();
    }

    public void testTheaterData() {
        TheaterDAO theaterDAO = factory.createTheaterDAO();
        CinemaDAO cinemaDAO = factory.createCinemaDAO();

        Cinema c = cinemaDAO.selectData().get(0);
        theaterDAO.insertData(new Theater(c, 100));
        theaterDAO.insertData(new Theater(c, 100));
        theaterDAO.insertData(new Theater(c, 100));
        theaterDAO.insertData(new Theater(c, 100));
        theaterDAO.selectData();
    }

    public void testVisitorData() {
        VisitorDAO visitorDAO = factory.createVisitorDAO();
        visitorDAO.insertData(new Visitor("Felix", "Boons"));
        visitorDAO.insertData(new Visitor("Jesse", "de Wit"));
        visitorDAO.insertData(new Visitor("Bart", "in t Veld"));
    }

    public void testShowData() {
        ShowDAO showDAO = factory.createShowDAO();
        FilmDAO filmDAO = factory.createFilmDAO();
        TheaterDAO theaterDAO = factory.createTheaterDAO();

        Film f = filmDAO.selectData().get(0);
        Theater t = theaterDAO.selectData().get(0);

        showDAO.insertData(new Show(f, t, new Date(117, 03, 10, 10, 10), "1010100010001001011101001001010100100010010101001001000100010000100101001010101000100010010111010010"));
        showDAO.selectData();
    }

    public void testTicketData() {
        ShowDAO showDAO = factory.createShowDAO();
        VisitorDAO visitorDAO = factory.createVisitorDAO();

        Visitor v = visitorDAO.selectData().get(0);
        Show s = showDAO.selectData().get(0);

        TicketDAO ticketDAO = factory.createTicketDAO();

        Ticket t = new Ticket(new QRCode().getQrCode(), v, s, 65);
        ArrayList<Ticket> tickets = ticketDAO.selectData();

        for (int i = 0; i < tickets.size(); i++) {
            if(tickets.get(i).getQrCode() == t.getQrCode())
            {
                Log.e("Main", "Can't create ticket: the qrcode already exists in the database. Creating new QRCode.");
                tickets.get(i).setQrCode(new QRCode().getQrCode());
                break;
            }
        }
        ticketDAO.insertData(t);
        ticketDAO.selectData();
    }

    public void testFilmDAO() {
        FilmDAO filmDAO = factory.createFilmDAO();
        CinemaDAO cinemaDAO = factory.createCinemaDAO();
        Cinema c = cinemaDAO.selectData().get(0);

        filmDAO.insertData(new Film(12345,c, "Bob de Bouwer: De Film", "Version", "Nederlands", "12-01-2018", "Komedie", 93, 6,
                "Bacon ipsum dolor amet bacon strip steak pork, spare ribs tongue pork chop burgdoggen swine jowl chuck. Beef ribs burgdoggen chicken, pig fatback sausage drumstick leberkas cow tongue shank chuck porchetta rump. Beef ribs shankle cow hamburger, turkey ground round ham hock meatball strip steak kielbasa pancetta picanha flank pork loin. Short loin fatback pork chop jerky hamburger meatloaf. Pork loin shoulder pork chop ribeye filet mignon sausage beef bacon.\n" +
                        "\n" +
                        "Tail landjaeger alcatra kevin doner pastrami. Ribeye filet mignon shankle, pastrami sausage pancetta pork shoulder. Sirloin spare ribs beef, rump tenderloin shoulder chuck pastrami kielbasa flank. Boudin hamburger shank porchetta pork loin landjaeger chicken kielbasa alcatra tenderloin fatback spare ribs. Andouille fatback beef ribs picanha sausage, ham jerky pork alcatra. Bresaola pork belly beef ribs tenderloin t-bone. Tail tenderloin shank, andouille doner ball tip hamburger ham chuck short ribs jerky ham hock alcatra jowl.",
                "www.imdb.url", "7.4", "www.youtube.com",
                "https://s-media-cache-ak0.pinimg.com/236x/25/98/13/259813c534453e8c1597f4ab1d62b284.jpg", "Die Boy Bert"));
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
    }
}
