package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.filet.bioscoopfilet.DomainModel.Actor;
import com.filet.bioscoopfilet.DomainModel.Feedback;
import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.DomainModel.Review;
import com.filet.bioscoopfilet.DomainModel.Show;
import com.filet.bioscoopfilet.DomainModel.Theater;
import com.filet.bioscoopfilet.DomainModel.Ticket;
import com.filet.bioscoopfilet.DomainModel.Visitor;
import com.filet.bioscoopfilet.Persistancy.ActorDAO;
import com.filet.bioscoopfilet.Persistancy.DAOFactory;
import com.filet.bioscoopfilet.Persistancy.DBConnect;
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

//        testVisitorData();
//        testTicketData();
//        testReviewData();
//        testFeedbackData();
//        testFilmDAO();
//        testShowData();
//        testTheaterData();
        testActorDAO();
//        cleanDatabase();
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
                // User chose the "Settings" item, show the app settings UI...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    public void informationButton(View v) {
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
    }

    public void filmButton(View v) {
        Intent intent = new Intent(this, FilmOverviewActivity.class);
        startActivity(intent);
    }

    public void testFeedbackData() {
        FeedbackDAO feedbackDAO = factory.createFeedbackDAO();
        feedbackDAO.insertData(new Feedback(1, 1, "Goeie App!"));
        feedbackDAO.selectData();
    }

    public void testReviewData() {
        ReviewDAO reviewDAO = factory.createReviewDAO();
        reviewDAO.insertData(new Review(1, 1, 1, 5, "Geweldige film"));
        reviewDAO.selectData();
    }

    public void testTheaterData() {
        TheaterDAO theaterDAO = factory.createTheaterDAO();
        theaterDAO.insertData(new Theater(1, 1, 150));
        theaterDAO.selectData();
    }

    public void testVisitorData() {
        VisitorDAO visitorDAO = factory.createVisitorDAO();
        visitorDAO.insertData(new Visitor(1, "Tommy", "Heunks"));
        visitorDAO.selectData();
    }

    public void testShowData()
    {
        ShowDAO showDAO = factory.createShowDAO();
        showDAO.insertData(new Show(1,3,2,new Date(04,04,1994,10,10),new Boolean[] { true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false,true, false}));

        showDAO.selectData();
    }

    public void testTicketData()
    {
        TicketDAO ticketDAO = factory.createTicketDAO();
        ticketDAO.insertData(new Ticket("fe32f2ef23fef23", 1, 1, 32)); //NEED A METHOD TO GENERATE RANDOM QRCODES
        ticketDAO.selectData();
    }

    public void testFilmDAO() {
        FilmDAO filmDAO = factory.createFilmDAO();
        filmDAO.insertData(new Film(1, 1, "Harry Potter", "En 3D", "English", "05-08-2017", 134, 12, "Coole film, joh", "www.imdb.com/harrypotter", "8.1", "www.trailers.nl/harrypotter", "J.K. Not-Rowling"));
        filmDAO.selectData();
    }

    public void testActorDAO() {
        ActorDAO actorDAO = factory.createActorDAO();
        actorDAO.insertData(new Actor(1, "Geurtje", "Acteurtje"));
        actorDAO.selectData();
    }

    //cleans the database. DOES NOT WORK YET.
    public void cleanDatabase() {
        DBConnect db = new DBConnect(getApplicationContext(), null, null);
        db.cleanDatabase();
    }
}
