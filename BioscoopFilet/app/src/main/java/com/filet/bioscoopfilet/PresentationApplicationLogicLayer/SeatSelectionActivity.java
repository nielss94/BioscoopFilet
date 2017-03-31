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
import com.filet.bioscoopfilet.DomainModel.Ticket;
import com.filet.bioscoopfilet.DomainModel.Visitor;
import com.filet.bioscoopfilet.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class SeatSelectionActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private int amountOfTickets;
    private Double totalPrice;
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
        show = (Show) getIntent().getSerializableExtra("SHOW");
        amountOfTickets = getIntent().getIntExtra("amountOfTickets",0);
        totalPrice = getIntent().getDoubleExtra("totalPrice", 0.00);


        seatsSelected = new int[amountOfTickets];

        selectAvailableSeats();
    }

    //DEMO BUTTON
    public void paymentButton(View v) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        Random r = new Random();
        Intent intent = new Intent(getApplicationContext(),PaymentActivity.class);
        for (int i = 0; i < amountOfTickets; i++) {
            int randomNumber = r.nextInt(99999);
            Ticket t = new Ticket(randomNumber+"",new Visitor(1, "Tommy", "Heunks"), show, seatsSelected[i]);
            tickets.add(t);
        }
        intent.putExtra("tickets",tickets);
        intent.putExtra("totalPrice",totalPrice);

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
            if(show.getSeats().charAt(i) == '1')
            {
                seats.get(i).setImageResource(R.drawable.ic_taken);
                freeSeats = 0;
            }
            else if(show.getSeats().charAt(i) == '0')
            {
                freeSeats++;

                Log.i(TAG,"Nummer: " + i);

                if(freeSeats >= amountOfTickets && seatsFound == false)
                {
                    for (int j = 0; j < amountOfTickets; j++) {
                        seatsSelected[j] = (i - j);
                        seats.get(i - j).setImageResource(R.drawable.ic_selected);

                    }
                    seatsFound = true;
                }
                else
                {
                    seats.get(i).setImageResource(R.drawable.ic_available);
                }
            }
        }
    }
}
