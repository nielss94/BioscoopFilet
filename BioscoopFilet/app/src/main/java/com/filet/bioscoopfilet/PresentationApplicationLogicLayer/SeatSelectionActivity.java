package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.filet.bioscoopfilet.DomainModel.Cinema;
import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.DomainModel.Show;
import com.filet.bioscoopfilet.DomainModel.Theater;
import com.filet.bioscoopfilet.R;

import java.util.ArrayList;
import java.util.Date;

public class SeatSelectionActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private int amountOfTickets;
    private Show show;

    private GridLayout seatsList;
    int[] seatsSelected;
    private ArrayList<ImageView> seats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        //Setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.select_seats);
        setSupportActionBar(myToolbar);

        //Find the GridLayout seatsList
        seatsList = (GridLayout)findViewById(R.id.seatsList);

        //Fill seats with all the childs from the GridLayout
        for (int i = 0; i < seatsList.getChildCount(); i++) {
            seats.add((ImageView)seatsList.getChildAt(i));
        }

        //Test show and tickets
        show = new Show(1, new Film(2, new Cinema(2, "Filet", "Breda", "Lovensdijkstraat 1",
                "5000XX", "013-51201230"), "Harry Potter", "Version", "language", "23-03-2017", "Horror", 113, 12,
                "Description description...,", "www.imdb.url", "9.9", "www.trailer.url", "www.poster.url", "Director Niels"), new Theater(2, new Cinema(2, "Filet", "Breda", "Lovensdijkstraat 1",
                "5000XX", "013-51201230"), 150), new Date(04, 04, 1994, 10, 10), "1010100010001001011101001001010100100010010101001001000100010000100101001010101000100010010111010010");
        amountOfTickets = 3;

        seatsSelected = new int[amountOfTickets];

        selectAvailableSeats();
    }

    //DEMO BUTTON
    public void paymentButton(View v) {
        Show show = (Show) getIntent().getSerializableExtra("SHOW");

        Intent intent = new Intent(getApplicationContext(),PaymentActivity.class);
        intent.putExtra("SHOW", show);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void selectAvailableSeats()
    {
        int freeSeats = 0;
        boolean seatsFound = false;
        for (int i = 0; i < show.getSeats().length(); i++) {
            Log.i(TAG,show.getSeats().charAt(i)+"");
            if(show.getSeats().charAt(i) == '1')
            {
                seats.get(i).setBackgroundColor(getResources().getColor(R.color.taken));
                freeSeats = 0;
            }
            else if(show.getSeats().charAt(i) == '0')
            {
                freeSeats++;

                if(freeSeats >= amountOfTickets && seatsFound == false)
                {
                    int seatNumber = i;
                    for (int j = 0; j < amountOfTickets; j++) {
                        seatsSelected[j] = (i - j);
                        seats.get(i - j).setBackgroundColor(getResources().getColor(R.color.selected));
                    }
                    seatsFound = true;
                }
                else {
                    seats.get(i).setBackgroundColor(getResources().getColor(R.color.free));
                }
            }
        }
    }
}
