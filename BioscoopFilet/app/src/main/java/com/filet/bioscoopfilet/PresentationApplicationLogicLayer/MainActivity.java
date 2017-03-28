package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.filet.bioscoopfilet.DomainModel.Feedback;
import com.filet.bioscoopfilet.DomainModel.Review;
import com.filet.bioscoopfilet.DomainModel.Theater;
import com.filet.bioscoopfilet.DomainModel.Visitor;
import com.filet.bioscoopfilet.Persistancy.DAOFactory;
import com.filet.bioscoopfilet.Persistancy.FeedbackDAO;
import com.filet.bioscoopfilet.Persistancy.ReviewDAO;
import com.filet.bioscoopfilet.Persistancy.TheaterDAO;
import com.filet.bioscoopfilet.Persistancy.VisitorDAO;
import com.filet.bioscoopfilet.R;

public class MainActivity extends AppCompatActivity {

    private DAOFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        testFeedbackData();
        testReviewData();
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
}
