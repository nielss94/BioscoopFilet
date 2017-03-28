package com.filet.bioscoopfilet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.filet.bioscoopfilet.DomainModel.Feedback;
import com.filet.bioscoopfilet.Persistancy.DAOFactory;
import com.filet.bioscoopfilet.Persistancy.FeedbackDAO;
import com.filet.bioscoopfilet.Persistancy.SQLiteDAOFactory;

public class MainActivity extends AppCompatActivity {

    private DAOFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        factory = new SQLiteDAOFactory(getApplicationContext());

        FeedbackDAO feedbackDAO = factory.createFeedbackDAO();
        feedbackDAO.insertData(new Feedback(1,1,"Goeie app!"));
        feedbackDAO.selectData();
    }
}
