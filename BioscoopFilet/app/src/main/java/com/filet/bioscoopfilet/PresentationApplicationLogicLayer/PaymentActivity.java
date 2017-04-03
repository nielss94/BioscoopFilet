package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.DomainModel.Show;
import com.filet.bioscoopfilet.DomainModel.Ticket;
import com.filet.bioscoopfilet.Persistancy.DAOFactory;
import com.filet.bioscoopfilet.Persistancy.SQLiteDAOFactory;
import com.filet.bioscoopfilet.Persistancy.TicketDAO;
import com.filet.bioscoopfilet.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    TextView payTitle;
    TextView payVersion;
    TextView payDateTime;
    TextView payTotalPrice;

    private ArrayList<Ticket> tickets;
    private DAOFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        factory = new SQLiteDAOFactory(getApplicationContext());

        //Setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.pay_tickets);
        setSupportActionBar(myToolbar);

        //Getting film given by activities before
        tickets = (ArrayList<Ticket>) getIntent().getSerializableExtra("tickets");
        Show show = tickets.get(0).getShow();
        Film film = show.getFilm();

        Log.i(TAG,tickets.size()+" tickets to be ordered.");

        //Get the total price
        Double totalPrice = getIntent().getDoubleExtra("totalPrice",0.00);

        //Declaration of TextViews
        payTitle = (TextView) findViewById(R.id.payTitle);
        payVersion = (TextView) findViewById(R.id.payVersion);
        payDateTime = (TextView) findViewById(R.id.payDateTime);
        payTotalPrice = (TextView) findViewById(R.id.payTotalPrice);


        //Filling TextViews with film info
        payTitle.setText(film.getTitle());
        payVersion.setText(film.getVersion());
        payTotalPrice.setText("€"+totalPrice+"0");

        //Filling TextView with show info
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd/MM/yy - HH:mm");
        payDateTime.setText(format.format(show.getTime()));
    }

    public void payTickets(View v)
    {
        TicketDAO ticketDAO = factory.createTicketDAO();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        switch(v.getTag().toString())
        {
            case "iDEAL":
                Toast.makeText(PaymentActivity.this,"Tickets are bought with iDEAL and added to My Filet",Toast.LENGTH_LONG).show();
                for (int i = 0; i < tickets.size(); i++) {
                    ticketDAO.insertData(tickets.get(i));
                }
                finish();
                startActivity(intent);
                break;
            case "Creditcard":
                Toast.makeText(PaymentActivity.this,"Tickets are bought with CreditCard and added to My Filet",Toast.LENGTH_LONG).show();
                for (int i = 0; i < tickets.size(); i++) {
                    ticketDAO.insertData(tickets.get(i));
                }
                finish();
                startActivity(intent);
                break;
            case "PayPal":
                Toast.makeText(PaymentActivity.this,"Tickets are bought with PayPal and added to My Filet",Toast.LENGTH_LONG).show();
                for (int i = 0; i < tickets.size(); i++) {
                    ticketDAO.insertData(tickets.get(i));
                }
                finish();
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
