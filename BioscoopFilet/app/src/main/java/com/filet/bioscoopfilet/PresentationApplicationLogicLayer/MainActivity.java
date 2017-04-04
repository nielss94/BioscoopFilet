package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

public class MainActivity extends AppCompatActivity implements FilmApiConnector.FilmsAvailable,
        AgeApiConnector.AgeAvailable, GenreApiConnector.GenreAvailable {


    private final String TAG = getClass().getSimpleName();
    private DAOFactory factory;

    private String language;
    private SharedPreferences languagepref;
    private ArrayList<Film> films = new ArrayList<>();
    private FilmDAO filmDAO;
    private CinemaDAO cinemaDAO;
    private FilmApiConnector getFilms;
    private int getFilmsCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        languagepref = getSharedPreferences("language", MODE_PRIVATE);
        language = languagepref.getString("languageToLoad", Locale.getDefault().toString());

        factory = new SQLiteDAOFactory(getApplicationContext());
        filmDAO = factory.createFilmDAO();
        cinemaDAO = factory.createCinemaDAO();




//        testCinemaDAO();
//        testVisitorData();
//        testFeedbackData();
        String[] urls = new String[]{"https://api.themoviedb.org/3/movie/upcoming?api_key=863618e1d5c5f5cc4e34a37c49b8338e&language=nl"};
        getFilms = new FilmApiConnector(this);
        getFilms.execute(urls);
//        testTheaterData();

        findViewById(R.id.LinearLayout).setVisibility(View.GONE);
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
        inflater.inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.action_lang);

        Log.i("Taal", Locale.getDefault().toString());
        if (Locale.getDefault().toString().equalsIgnoreCase("en_us")) {
            item.setIcon(R.drawable.united_states);
        }
        if (Locale.getDefault().toString().equalsIgnoreCase("en_gb")) {
            item.setIcon(R.drawable.united_kingdom);
        }
        if (Locale.getDefault().toString().equalsIgnoreCase("nl_nl")) {
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

                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                finish();
                                startActivity(intent);
                                return true;

                            case R.id.action_NL:
                                Log.i("MenuItemSelected", "NEDERLANDS");
                                Locale locale2 = new Locale("nl", "NL");
                                Locale.setDefault(locale2);
                                Configuration config2 = getBaseContext().getResources().getConfiguration();
                                config2.locale = locale2;
                                getBaseContext().getResources().updateConfiguration(config2, getBaseContext().getResources().getDisplayMetrics());

                                Intent intent2 = new Intent(getBaseContext(), MainActivity.class);
                                finish();
                                startActivity(intent2);
                                return true;

                            case R.id.action_EN_UK:
                                Log.i("MenuItemSelected", "ENGELS");
                                Locale locale3 = new Locale("en", "GB");
                                Locale.setDefault(locale3);
                                Configuration config3 = getBaseContext().getResources().getConfiguration();
                                config3.locale = locale3;
                                getBaseContext().getResources().updateConfiguration(config3, getBaseContext().getResources().getDisplayMetrics());

                                Intent intent3 = new Intent(getBaseContext(), MainActivity.class);
                                finish();
                                startActivity(intent3);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
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

        reviewDAO.insertData(new Review(f.getFilmAPIID(), v, 5, "Geweldige film"));
        reviewDAO.selectData();
    }

    public void testTheaterData() {
        TheaterDAO theaterDAO = factory.createTheaterDAO();
        CinemaDAO cinemaDAO = factory.createCinemaDAO();

        Cinema c = cinemaDAO.selectData().get(0);
        theaterDAO.insertData(new Theater(c, 100));
        theaterDAO.selectData();
    }

    public void testVisitorData() {
        VisitorDAO visitorDAO = factory.createVisitorDAO();
        visitorDAO.insertData(new Visitor("Niels", "van Dam"));
    }

    public void testShowData() {
        ShowDAO showDAO = factory.createShowDAO();
        FilmDAO filmDAO = factory.createFilmDAO();
        TheaterDAO theaterDAO = factory.createTheaterDAO();

        Theater t = theaterDAO.selectData().get(0);
        Film f = filmDAO.selectData().get(0);
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 6, 17, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 6, 19, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 6, 21, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 7, 14, 30), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 7, 16, 30), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 7, 18, 30), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 8, 12, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 8, 14, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 8, 16, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 9, 9, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 9, 11, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 10, 9, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 10, 11, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 11, 15, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 11, 17, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 12, 15, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 12, 17, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));

        f = filmDAO.selectData().get(1);
        t = theaterDAO.selectData().get(1);
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 6, 17, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 6, 19, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 6, 21, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 7, 14, 30), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 7, 16, 30), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 7, 18, 30), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 8, 12, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 8, 14, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 8, 16, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 9, 9, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 9, 11, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 10, 9, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 10, 11, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 11, 15, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 11, 17, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 12, 15, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 12, 17, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));

        f = filmDAO.selectData().get(2);
        t = theaterDAO.selectData().get(2);
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 6, 17, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 6, 19, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 6, 21, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 7, 14, 30), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 7, 16, 30), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 7, 18, 30), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 8, 12, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 8, 14, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 8, 16, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 9, 9, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 9, 11, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 10, 9, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 10, 11, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 11, 15, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 11, 17, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 12, 15, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 12, 17, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));

        f = filmDAO.selectData().get(3);
        t = theaterDAO.selectData().get(1);
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 6, 12, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 6, 14, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 6, 16, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 7, 20, 30), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 7, 22, 30), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 8, 18, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 8, 20, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        showDAO.insertData(new Show(f.getFilmAPIID(), t, new Date(117, 3, 8, 22, 10), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
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
            if (tickets.get(i).getQrCode() == t.getQrCode()) {
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

        filmDAO.insertData(new Film(12345, c, "Bob de Bouwer: De Film", "Version", "Nederlands", "12-01-2018", "Komedie", 93, 6,
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

    @Override
    public void filmsAvailable(ArrayList<Film> result) {

        //Clear current products
        this.films.clear();
        filmDAO.deleteData();
        //Fill film Array with films from API
        this.films = result;

        for (Film f : result) {
            AgeApiConnector getAge = new AgeApiConnector(this);
            getAge.execute("https://api.themoviedb.org/3/movie/" + f.getFilmAPIID()
                    + "/release_dates?api_key=863618e1d5c5f5cc4e34a37c49b8338e");

            GenreApiConnector getGenre = new GenreApiConnector(this);
            getGenre.execute("https://api.themoviedb.org/3/movie/" + f.getFilmAPIID()
                    + "?api_key=863618e1d5c5f5cc4e34a37c49b8338e&language=en-US");
        }
    }

    @Override
    public void ageAvailable(String age, Integer id) {
        for (int i = 0; i < films.size(); i++) {

            Integer ageFinal;

            if (id == films.get(i).getFilmAPIID()) {

                if (age.equals("PG-13")) {
                    ageFinal = 12;
                    films.get(i).setAge(ageFinal);
                }
                if (age.equals("PG")) {
                    ageFinal = 3;
                    films.get(i).setAge(ageFinal);
                }
                if (age.equals("R")) {
                    ageFinal = 16;
                    films.get(i).setAge(ageFinal);
                }
                if (age.equals("")) {
                    ageFinal = 0;
                    films.get(i).setAge(ageFinal);
                }
            }
        }
    }

    @Override
    public void genreAvailable(String genre, Integer id) {
        getFilmsCounter++;
        Log.i(TAG,getFilmsCounter+"");
        for (int i = 0; i < films.size(); i++) {

            if (id == films.get(i).getFilmAPIID()) {
                films.get(i).setGenre(genre);
                films.get(i).setCinema(cinemaDAO.selectData().get(0));
                filmDAO.insertData(films.get(i));

            }
        }
        if(getFilmsCounter >= 19) {
            Log.i(TAG,"Hello?");


//        testFilmDAO();
//            testShowData();
//        testTicketData();
//        testReviewData();
//        testActorDAO();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            findViewById(R.id.splashScreen).setVisibility(View.GONE);
            findViewById(R.id.LinearLayout).setVisibility(View.VISIBLE);
        }
    }
}
