package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.DomainModel.Show;
import com.filet.bioscoopfilet.DomainModel.Ticket;
import com.filet.bioscoopfilet.Persistancy.DAOFactory;
import com.filet.bioscoopfilet.Persistancy.FilmDAO;
import com.filet.bioscoopfilet.Persistancy.SQLiteDAOFactory;
import com.filet.bioscoopfilet.Persistancy.ShowDAO;
import com.filet.bioscoopfilet.Persistancy.TicketDAO;
import com.filet.bioscoopfilet.R;

import java.util.ArrayList;
import java.util.Locale;

public class PaymentActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    TextView payTitle;
    TextView payVersion;
    TextView payDateTime;
    TextView payTotalPrice;

    private ArrayList<Ticket> tickets;
    private DAOFactory factory;
    private Show show;
    private ArrayList<Film> films = new ArrayList<>();
    private Film film;

    private String language;
    private SharedPreferences languagepref;

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
        FilmDAO filmDAO = factory.createFilmDAO();
        films = filmDAO.selectData();
        show = tickets.get(0).getShow();
        for (int i = 0; i < films.size(); i++) {
            if(films.get(i).getFilmAPIID() == show.getFilmAPIID())
            {
                film = films.get(i);
            }
        }

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
        ShowDAO showDAO = factory.createShowDAO();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        switch(v.getTag().toString())
        {
            case "iDEAL":
                Toast.makeText(PaymentActivity.this,"Tickets are bought with iDEAL and added to My Filet",Toast.LENGTH_LONG).show();
                for (int i = 0; i < tickets.size(); i++) {
                    ticketDAO.insertData(tickets.get(i));
                    Log.i(TAG, "Ordering ticket: "+tickets.get(i).toString());
                }
                showDAO.updateData(show);
                finish();
                startActivity(intent);
                break;
            case "Creditcard":
                Toast.makeText(PaymentActivity.this,"Tickets are bought with CreditCard and added to My Filet",Toast.LENGTH_LONG).show();
                for (int i = 0; i < tickets.size(); i++) {
                    ticketDAO.insertData(tickets.get(i));
                }
                showDAO.updateData(show);
                finish();
                startActivity(intent);
                break;
            case "PayPal":
                Toast.makeText(PaymentActivity.this,"Tickets are bought with PayPal and added to My Filet",Toast.LENGTH_LONG).show();
                for (int i = 0; i < tickets.size(); i++) {
                    ticketDAO.insertData(tickets.get(i));
                }
                showDAO.updateData(show);
                finish();
                startActivity(intent);
                break;
        }
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

                                Intent intent = getIntent();
                                intent.addFlags( Intent.FLAG_ACTIVITY_NO_ANIMATION );
                                intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                                startActivity(intent);
                                return true;

                            case R.id.action_NL:
                                Log.i("MenuItemSelected", "NEDERLANDS");
                                Locale locale2 = new Locale("nl", "NL");
                                Locale.setDefault(locale2);
                                Configuration config2 = getBaseContext().getResources().getConfiguration();
                                config2.locale = locale2;
                                getBaseContext().getResources().updateConfiguration(config2, getBaseContext().getResources().getDisplayMetrics());

                                Intent intent2 = getIntent();
                                intent2.addFlags( Intent.FLAG_ACTIVITY_NO_ANIMATION );
                                intent2.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                                startActivity(intent2);
                                return true;

                            case R.id.action_EN_UK:
                                Log.i("MenuItemSelected", "ENGELS");
                                Locale locale3 = new Locale("en", "GB");
                                Locale.setDefault(locale3);
                                Configuration config3 = getBaseContext().getResources().getConfiguration();
                                config3.locale = locale3;
                                getBaseContext().getResources().updateConfiguration(config3, getBaseContext().getResources().getDisplayMetrics());

                                Intent intent3 = getIntent();
                                intent3.addFlags( Intent.FLAG_ACTIVITY_NO_ANIMATION );
                                intent3.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
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
}
