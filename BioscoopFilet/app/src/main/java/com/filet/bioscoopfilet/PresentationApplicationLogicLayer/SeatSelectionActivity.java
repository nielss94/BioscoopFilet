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
import android.widget.Toast;

import com.filet.bioscoopfilet.DomainModel.Cinema;
import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.DomainModel.Show;
import com.filet.bioscoopfilet.DomainModel.Theater;
import com.filet.bioscoopfilet.DomainModel.Ticket;
import com.filet.bioscoopfilet.DomainModel.Visitor;
import com.filet.bioscoopfilet.Persistancy.DAOFactory;
import com.filet.bioscoopfilet.Persistancy.SQLiteDAOFactory;
import com.filet.bioscoopfilet.Persistancy.SQLiteTicketDAO;
import com.filet.bioscoopfilet.Persistancy.TicketDAO;
import com.filet.bioscoopfilet.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class SeatSelectionActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private DAOFactory factory;

    private int amountOfTickets;
    private Double totalPrice;
    private Show show;

    private GridLayout seatsList;
    int[] seatsSelected;
    private ArrayList<ImageView> seats = new ArrayList<>();
    private ArrayList<Ticket> tickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        //Setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.select_seats);
        setSupportActionBar(myToolbar);

        //Find the GridLayout seatsList
        seatsList = (GridLayout) findViewById(R.id.seatsList);



        //Fill seats with all the childs from the GridLayout
        for (int i = 0; i < seatsList.getChildCount(); i++) {
            seats.add((ImageView) seatsList.getChildAt(i));
        }
        show = (Show) getIntent().getSerializableExtra("SHOW");
        amountOfTickets = getIntent().getIntExtra("amountOfTickets", 0);
        totalPrice = getIntent().getDoubleExtra("totalPrice", 0.00);

        seatsSelected = new int[amountOfTickets];

        //factory creation
        factory = new SQLiteDAOFactory(getApplicationContext());
        //get tickets from DB
        TicketDAO ticketDAO = factory.createTicketDAO();
        tickets = ticketDAO.selectData();

        for (int i = 0; i < seatsList.getChildCount(); i++){

            seatsList.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < seatsList.getChildCount(); j++) {
                        if(seatsList.getChildAt(j) == v)
                        {
                            selectSeatsOnClick(j);
                        }
                    }
                }
            });
        }

        selectAvailableSeats();
    }
    public void setDefiniteShowSeats()
    {
        String r = "";
        for (int k = 0; k < show.getSeats().length(); k++) {
            innerloop:
            for (int i = 0; i < seatsSelected.length; i++) {
                if (k == seatsSelected[i]) {
                    r = r + "1";
                    break innerloop;
                }else if (i==seatsSelected.length-1){
                    r = r + show.getSeats().charAt(k);
                }
            }
        }

        show.setSeats(r);

    }
    public boolean setShowSeats()
    {
        boolean returnValue = true;
        String r = "";
        for (int k = 0; k < show.getSeats().length(); k++) {
            innerloop:
            for (int i = 0; i < seatsSelected.length; i++) {
                if (k == seatsSelected[i]) {
                    r = r + "1";
                    break innerloop;
                }else if (i==seatsSelected.length-1){
                    r = r + show.getSeats().charAt(k);
                }
            }
        }

        int emptySeats =0;
        for (int i = 0; i < r.length(); i++) {
            if(r.charAt(i) == '0')
            {
                emptySeats += 1;
            }else if(r.charAt(i) == '1')
            {
                if(emptySeats == 1)
                {
                    Log.i(TAG,"empty seats is 1");
                    returnValue = false;
                }
                else
                {
                    emptySeats = 0;
                }
            }
        }
        Log.i(TAG,returnValue+"");
        if(returnValue != false)
        {
            return true;
        }
        return false;
    }


    public void paymentButton(View v) {
        setDefiniteShowSeats();
        Random r = new Random();
        Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
        ArrayList<Ticket> newTickets = new ArrayList<>();
        for (int i = 0; i < amountOfTickets; i++) {
            int randomNumber = r.nextInt(99999);

            for (int j = 0; j < tickets.size(); j++) {
                while(!checkIfQRCodeExists(randomNumber))
                {
                    randomNumber = r.nextInt(99999);
                    Log.i(TAG,"QRCode bestond al, nieuwe nummer is: " + randomNumber);

                }
            }
            Log.i(TAG,randomNumber + "");
            Ticket t = new Ticket(randomNumber, new Visitor(1, "Tommy", "Heunks"), show, seatsSelected[i]);
            newTickets.add(t);
        }

        intent.putExtra("tickets", newTickets);
        intent.putExtra("totalPrice", totalPrice);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean checkIfQRCodeExists(int qrCode)
    {
        for (int i = 0; i < tickets.size(); i++) {
            if(qrCode == tickets.get(i).getQrCode())
            {
                return false;
            }
        }
        return true;
    }

    public void selectSeatsOnClick(int seatNumber)
    {
        int[] previousSeats = seatsSelected;
        for (int i = 0; i < previousSeats.length; i++) {
            Log.i(TAG,"Seat" + i + ": " + previousSeats[i]);
        }
        Log.i(TAG,show.getSeats());
        if(show.getSeats().charAt(seatNumber) != '1') {
            if(amountOfTickets == 1)
            {
                seats.get(seatsSelected[0]).setImageResource(R.drawable.ic_available);
                seatsSelected = new int[amountOfTickets];
                seats.get(seatNumber).setImageResource(R.drawable.ic_selected);
                seatsSelected[0] = seatNumber;

                if(setShowSeats() == false)
                {
                    for (int i = 0; i < seatsSelected.length; i++) {
                        seats.get(seatsSelected[i]).setImageResource(R.drawable.ic_available);
                    }
                    seatsSelected = previousSeats;
                    for (int i = 0; i < seatsSelected.length; i++) {
                        seats.get(seatsSelected[i]).setImageResource(R.drawable.ic_selected);
                    }
                }
            }
            else if (amountOfTickets > seatNumber) {
                if (findSeatsRight(seatNumber)) {
                    for (int i = 0; i < seatsSelected.length; i++) {
                        seats.get(seatsSelected[i]).setImageResource(R.drawable.ic_available);
                    }
                    seatsSelected = new int[amountOfTickets];
                    for (int i = 0; i < amountOfTickets; i++) {
                        seats.get(seatNumber+i).setImageResource(R.drawable.ic_selected);
                        seatsSelected[i] = (seatNumber + i);
                    }

                    if(setShowSeats() == false)
                    {
                        for (int i = 0; i < seatsSelected.length; i++) {
                            seats.get(seatsSelected[i]).setImageResource(R.drawable.ic_available);
                        }
                        seatsSelected = previousSeats;
                        for (int i = 0; i < seatsSelected.length; i++) {
                            seats.get(seatsSelected[i]).setImageResource(R.drawable.ic_selected);
                        }
                    }
                    else
                    {
                        Log.i(TAG, "Found seats on the right.");
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.noSeatAvailable),Toast.LENGTH_SHORT).show();
                }
            } else if ((amountOfTickets + seatNumber) > 99) {
                if (findSeatsLeft(seatNumber)) {
                    for (int i = 0; i < seatsSelected.length; i++) {
                        seats.get(seatsSelected[i]).setImageResource(R.drawable.ic_available);
                    }
                    seatsSelected = new int[amountOfTickets];

                    for (int i = 0; i < amountOfTickets; i++) {
                        seats.get(seatNumber - i).setImageResource(R.drawable.ic_selected);
                        seatsSelected[i] = (seatNumber - i);
                    }
                    if(setShowSeats() == false)
                    {
                        for (int i = 0; i < seatsSelected.length; i++) {
                            seats.get(seatsSelected[i]).setImageResource(R.drawable.ic_available);
                        }
                        seatsSelected = previousSeats;
                        for (int i = 0; i < seatsSelected.length; i++) {
                            seats.get(seatsSelected[i]).setImageResource(R.drawable.ic_selected);
                        }
                    }
                    else {
                        Log.i(TAG, "Found seats on the left.");
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.noSeatAvailable),Toast.LENGTH_SHORT).show();
                }
            } else {
                if (findSeatsRight(seatNumber)) {
                    for (int i = 0; i < seatsSelected.length; i++) {
                        seats.get(seatsSelected[i]).setImageResource(R.drawable.ic_available);
                    }
                    seatsSelected = new int[amountOfTickets];
                    for (int i = 0; i < amountOfTickets; i++) {
                        seats.get(seatNumber+i).setImageResource(R.drawable.ic_selected);
                        seatsSelected[i] = (seatNumber + i);
                    }
                    if(setShowSeats() == false)
                    {
                        for (int i = 0; i < seatsSelected.length; i++) {
                            seats.get(seatsSelected[i]).setImageResource(R.drawable.ic_available);
                        }
                        seatsSelected = previousSeats;
                        for (int i = 0; i < seatsSelected.length; i++) {
                            seats.get(seatsSelected[i]).setImageResource(R.drawable.ic_selected);
                        }
                    }
                    else {
                        Log.i(TAG, "Found seats on the right.");
                    }
                } else if (findSeatsLeft(seatNumber)) {
                    for (int i = 0; i < seatsSelected.length; i++) {
                    seats.get(seatsSelected[i]).setImageResource(R.drawable.ic_available);
                    }
                    seatsSelected = new int[amountOfTickets];

                    for (int i = 0; i < amountOfTickets; i++) {
                        seats.get(seatNumber-i).setImageResource(R.drawable.ic_selected);
                        seatsSelected[i] = (seatNumber - i);
                    }
                    if(setShowSeats() == false)
                    {
                        for (int i = 0; i < seatsSelected.length; i++) {
                            seats.get(seatsSelected[i]).setImageResource(R.drawable.ic_available);
                        }
                        seatsSelected = previousSeats;
                        for (int i = 0; i < seatsSelected.length; i++) {
                            seats.get(seatsSelected[i]).setImageResource(R.drawable.ic_selected);
                        }
                    }
                    else {
                        Log.i(TAG, "Found seats on the left.");
                    }
                } else {
                    Log.i(TAG, "There are no seats available around this seat");
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.noSeatAvailable),Toast.LENGTH_SHORT).show();
                }
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.seatTaken),Toast.LENGTH_SHORT).show();
            Log.i(TAG, "This seat is not available");
        }
        Log.i(TAG, seatNumber+"");
    }


    public boolean findSeatsRight(int seatNumber)
    {
        int freeSeats = 0;
        boolean returnValue = false;
        for (int i = 0; i < amountOfTickets; i++) {
//            Log.i(TAG,show.getSeats().charAt(i + seatNumber)+"");
            if(seatNumber + i <= 99)
            {
                if(show.getSeats().charAt(i + seatNumber) == '1')
                {
                    returnValue = false;
                }
                else if (show.getSeats().charAt(i + seatNumber) == '0')
                {
                    freeSeats+=1;
                }
                if(freeSeats >= amountOfTickets)
                {
                    returnValue= true;
                }
            }
        }
        return returnValue;
    }

    public boolean findSeatsLeft(int seatNumber)
    {
        int freeSeats = 0;
        boolean returnValue = false;
        for (int i = 0; i < amountOfTickets; i++) {
            if(seatNumber - i >= 0)
            {
                if(show.getSeats().charAt(seatNumber - i) == '1')
                {
                    returnValue = false;
                }
                else if (show.getSeats().charAt(seatNumber - i) == '0')
                {
                    freeSeats+= 1;
                }
                if(freeSeats >= amountOfTickets)
                {
                    returnValue = true;
                }
            }

        }
        return returnValue;
    }

    public void selectAvailableSeats() {
        int freeSeats = 0;
        boolean seatsFound = false;
        for (int i = 0; i < show.getSeats().length(); i++) {
            if (show.getSeats().charAt(i) == '1') {
                seats.get(i).setImageResource(R.drawable.ic_taken);
                freeSeats = 0;
            } else if (show.getSeats().charAt(i) == '0') {
                freeSeats++;

                if (freeSeats >= amountOfTickets && seatsFound == false) {
                    for (int j = 0; j < amountOfTickets; j++) {
                        seatsSelected[j] = (i - j);
                        seats.get(i - j).setImageResource(R.drawable.ic_selected);

                    }
                    seatsFound = true;
                } else {
                    seats.get(i).setImageResource(R.drawable.ic_available);
                }
            }
        }
    }
}
