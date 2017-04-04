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
import android.widget.AdapterView;
import android.widget.ListView;

import com.filet.bioscoopfilet.DomainModel.Ticket;
import com.filet.bioscoopfilet.Persistancy.DAOFactory;
import com.filet.bioscoopfilet.Persistancy.SQLiteDAOFactory;
import com.filet.bioscoopfilet.Persistancy.TicketDAO;
import com.filet.bioscoopfilet.R;

import java.util.ArrayList;
import java.util.Locale;

public class MyTicketActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private String language;
    private SharedPreferences languagepref;

    private TicketAdapter adapter;
    public ArrayList<Ticket> tickets = new ArrayList<>();
    private final String TAG = getClass().getSimpleName();
    private DAOFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.my_tickets);
        setSupportActionBar(myToolbar);

        factory = new SQLiteDAOFactory(getApplicationContext());
        TicketDAO ticketDAO = factory.createTicketDAO();
        tickets = ticketDAO.selectData();

        for (int i = 0; i < tickets.size(); i++) {
            Log.i(TAG,"-----------------");
            Log.i(TAG,tickets.get(i).toString());
            Log.i(TAG,"-----------------");
        }

/*
        Visitor v = new Visitor(1, "Niels", "van Dam");
        Cinema c = new Cinema(1, "Filet", "Breda", "Lovensdijkstraat", "5000XX", "013-57123010");
        Film f = new Film(1, c, "Hiro Potter and saving the cheerleader", "Version", "language", "23-03-2017", "Horror", 113, 12,
                "Description description...,", "www.imdb.url", "9.9", "www.trailer.url", "http://static.fjcdn.com/pictures/Hiro_ee085f_349000.jpg", "Director Niels");
        Theater th = new Theater(1, c, 150);
        Show s = new Show(1, f, th, new Date("03/27/2017"));
*/
        adapter = new TicketAdapter(getApplicationContext(), tickets);
        ListView listview = (ListView) findViewById(R.id.ticketsListview);
/*
        Ticket t = new Ticket(23411142, v, s, 32);
        tickets.add(t);
        t = new Ticket(23144124, v, s, 32);
        tickets.add(t);
        t = new Ticket(1242151242, v, s, 32);
        tickets.add(t);*/
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);

        languagepref = getSharedPreferences("language",MODE_PRIVATE);
        language = languagepref.getString("languageToLoad", Locale.getDefault().toString());
    }

    @Override
    public void onResume(){
        super.onResume();

        String oldLanguage = language;

        language = languagepref.getString("languageToLoad", Locale.getDefault().toString());

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
        if (Locale.getDefault().toString().equalsIgnoreCase("en_gb")){
            item.setIcon(R.drawable.united_kingdom);
        }
        if (Locale.getDefault().toString().equalsIgnoreCase("nl_nl")){
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
                                Log.i("MenuItemSelected", "ENGELS");
                                Locale locale = new Locale("en", "US");
                                Locale.setDefault(locale);
                                Configuration config = getBaseContext().getResources().getConfiguration();
                                config.locale = locale;
                                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

                                Intent intent = new Intent(getBaseContext(), MyFiletActivity.class);
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

                                Intent intent2 = new Intent(getBaseContext(), MyFiletActivity.class);
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

                                Intent intent3 = new Intent(getBaseContext(), MyFiletActivity.class);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Ticket ticket = tickets.get(position);

        Intent intent = new Intent(getApplicationContext(), MyTicketDetailActivity.class);
        intent.putExtra("ticket", ticket);
        startActivity(intent);
    }
}
