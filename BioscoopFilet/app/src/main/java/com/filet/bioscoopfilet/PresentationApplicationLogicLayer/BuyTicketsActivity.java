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

import com.filet.bioscoopfilet.DomainModel.Show;
import com.filet.bioscoopfilet.R;

import java.util.Locale;

public class BuyTicketsActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private Double totalPrice;
    private int amountOfTickets;
    private TextView totalPriceView;

    private String language;
    private SharedPreferences languagepref;
    Show show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_tickets);

        //Setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.buy_tickets);
        setSupportActionBar(myToolbar);

        show = (Show) getIntent().getSerializableExtra("SHOW");

        totalPriceView = (TextView)findViewById(R.id.totalPrice);
        totalPrice = calculatePrice();

        languagepref = getSharedPreferences("language", MODE_PRIVATE);
        language = languagepref.getString("languageToLoad", Locale.getDefault().toString());
    }

    public void seatButton(View v) {

        int availableSeats = findMaxAvailableSeats();

        if (amountOfTickets == 0){
            Toast.makeText(this, getResources().getString(R.string.no_tickets), Toast.LENGTH_SHORT).show();
        }
        else if(amountOfTickets > availableSeats)
        {
            Toast.makeText(this, getResources().getString(R.string.tooManyTickets), Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, SeatSelectionActivity.class);

            intent.putExtra("amountOfTickets", amountOfTickets);
            intent.putExtra("totalPrice", totalPrice);
            intent.putExtra("SHOW", show);

            startActivity(intent);
        }
    }

    public int findMaxAvailableSeats()
    {
        int maxAvailableSeats = 0;
        int counter = 0;
        for (int i = 0; i < show.getSeats().length(); i++) {
            if(show.getSeats().charAt(i) == '0')
            {
                counter++;
            }
            else if(show.getSeats().charAt(i) == '1')
            {
                if(counter > maxAvailableSeats)
                {
                    maxAvailableSeats = counter;
                }
                counter = 0;
            }
        }
        return maxAvailableSeats;
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
        else if (Locale.getDefault().toString().equalsIgnoreCase("en_gb")) {
            item.setIcon(R.drawable.united_kingdom);
        }
        else if (Locale.getDefault().toString().equalsIgnoreCase("nl_nl")) {
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

    public void plusOne(View v)
    {
        int i;
        TextView count;
        if(Locale.getDefault().toString().equals("nl_NL"))
        {
            switch(v.getTag().toString())
            {
                case "Normaal":
                    count = (TextView)findViewById(R.id.count1);
                    i = Integer.parseInt(count.getText().toString());
                    count.setText(i + 1 + "");
                    break;
                case "Tiener":
                    count = (TextView)findViewById(R.id.count2);
                    i = Integer.parseInt(count.getText().toString());
                    count.setText(i + 1 + "");
                    break;
                case "Kind":
                    count = (TextView)findViewById(R.id.count3);
                    i = Integer.parseInt(count.getText().toString());
                    count.setText(i + 1 + "");
                    break;
                case "Senior":
                    count = (TextView)findViewById(R.id.count4);
                    i = Integer.parseInt(count.getText().toString());
                    count.setText(i + 1 + "");
                    break;
                case "Student":
                    count = (TextView)findViewById(R.id.count5);
                    i = Integer.parseInt(count.getText().toString());
                    count.setText(i + 1 + "");
                    break;
                case "3D bril":
                    count = (TextView)findViewById(R.id.countGlasses);
                    i = Integer.parseInt(count.getText().toString());
                    count.setText(i + 1 + "");
                    break;
            }
        }
        else if (Locale.getDefault().toString().equals("en_US") || Locale.getDefault().toString().equals("en_GB"))
        {
            switch(v.getTag().toString())
            {
                case "Normal":
                    count = (TextView)findViewById(R.id.count1);
                    i = Integer.parseInt(count.getText().toString());
                    count.setText(i + 1 + "");
                    break;
                case "Teen":
                    count = (TextView)findViewById(R.id.count2);
                    i = Integer.parseInt(count.getText().toString());
                    count.setText(i + 1 + "");
                    break;
                case "Child":
                    count = (TextView)findViewById(R.id.count3);
                    i = Integer.parseInt(count.getText().toString());
                    count.setText(i + 1 + "");
                    break;
                case "Senior":
                    count = (TextView)findViewById(R.id.count4);
                    i = Integer.parseInt(count.getText().toString());
                    count.setText(i + 1 + "");
                    break;
                case "Student":
                    count = (TextView)findViewById(R.id.count5);
                    i = Integer.parseInt(count.getText().toString());
                    count.setText(i + 1 + "");
                    break;
                case "3D glasses":
                    count = (TextView)findViewById(R.id.countGlasses);
                    i = Integer.parseInt(count.getText().toString());
                    count.setText(i + 1 + "");
                    break;
            }
        }
        totalPrice = calculatePrice();
    }

    public void minOne(View v)
    {
        int i;
        TextView count;
        if (Locale.getDefault().toString().equals("nl_NL")) {
            switch (v.getTag().toString()) {
                case "Normaal":
                    count = (TextView) findViewById(R.id.count1);
                    i = Integer.parseInt(count.getText().toString());
                    if (i > 0) {
                        count.setText((i - 1) + "");
                    }
                    break;
                case "Tiener":
                    count = (TextView) findViewById(R.id.count2);
                    i = Integer.parseInt(count.getText().toString());
                    if (i > 0) {
                        count.setText((i - 1) + "");
                    }
                    break;
                case "Kind":
                    count = (TextView) findViewById(R.id.count3);
                    i = Integer.parseInt(count.getText().toString());
                    if (i > 0) {
                        count.setText((i - 1) + "");
                    }
                    break;
                case "Senior":
                    count = (TextView) findViewById(R.id.count4);
                    i = Integer.parseInt(count.getText().toString());
                    if (i > 0) {
                        count.setText((i - 1) + "");
                    }
                    break;
                case "Student":
                    count = (TextView) findViewById(R.id.count5);
                    i = Integer.parseInt(count.getText().toString());
                    if (i > 0) {
                        count.setText((i - 1) + "");
                    }
                    break;
                case "3D bril":
                    count = (TextView) findViewById(R.id.countGlasses);
                    i = Integer.parseInt(count.getText().toString());
                    if (i > 0) {
                        count.setText((i - 1) + "");
                    }
                    break;
            }
        } else if (Locale.getDefault().toString().equals("en_US") || Locale.getDefault().toString().equals("en_GB"))
        {
            switch(v.getTag().toString())
            {
                case "Normal":
                    count = (TextView)findViewById(R.id.count1);
                    i = Integer.parseInt(count.getText().toString());
                    if (i > 0) {
                        count.setText(i - 1 + "");
                    }
                    break;
                case "Teen":
                    count = (TextView)findViewById(R.id.count2);
                    i = Integer.parseInt(count.getText().toString());
                    if (i > 0) {
                        count.setText(i - 1 + "");
                    }
                    break;
                case "Child":
                    count = (TextView)findViewById(R.id.count3);
                    i = Integer.parseInt(count.getText().toString());
                    if (i > 0) {
                        count.setText(i - 1 + "");
                    }
                    break;
                case "Senior":
                    count = (TextView)findViewById(R.id.count4);
                    i = Integer.parseInt(count.getText().toString());
                    if (i > 0) {
                        count.setText(i - 1 + "");
                    }
                    break;
                case "Student":
                    count = (TextView)findViewById(R.id.count5);
                    i = Integer.parseInt(count.getText().toString());
                    if (i > 0) {
                        count.setText(i - 1 + "");
                    }
                    break;
                case "3D glasses":
                    count = (TextView)findViewById(R.id.countGlasses);
                    i = Integer.parseInt(count.getText().toString());
                    if (i > 0) {
                        count.setText(i - 1 + "");
                    }
                    break;
            }
        }
        totalPrice = calculatePrice();
    }

    public double calculatePrice()
    {
        double price = 0;
        TextView glassesCount = (TextView)findViewById(R.id.countGlasses);
        TextView glassesPrice = (TextView)findViewById(R.id.priceGlasses);
        TextView normalCount = (TextView)findViewById(R.id.count1);
        TextView normalPrice = (TextView)findViewById(R.id.price1);
        TextView teenCount = (TextView)findViewById(R.id.count2);
        TextView teenPrice = (TextView)findViewById(R.id.price2);
        TextView childCount = (TextView)findViewById(R.id.count3);
        TextView childPrice = (TextView)findViewById(R.id.price3);
        TextView seniorCount = (TextView)findViewById(R.id.count4);
        TextView seniorPrice = (TextView)findViewById(R.id.price4);
        TextView studentCount = (TextView)findViewById(R.id.count5);
        TextView studentPrice = (TextView)findViewById(R.id.price5);

        int amountGlasses = Integer.parseInt(glassesCount.getText().toString());
        int amountNormal = Integer.parseInt(normalCount.getText().toString());
        int amountTeen = Integer.parseInt(teenCount.getText().toString());
        int amountChild = Integer.parseInt(childCount.getText().toString());
        int amountSenior = Integer.parseInt(seniorCount.getText().toString());
        int amountStudent = Integer.parseInt(studentCount.getText().toString());

        price += (amountGlasses * Double.parseDouble(glassesPrice.getText().toString().substring(1)));
        price += (amountNormal * Double.parseDouble(normalPrice.getText().toString().substring(1)));
        price += (amountTeen * Double.parseDouble(teenPrice.getText().toString().substring(1)));
        price += (amountChild * Double.parseDouble(childPrice.getText().toString().substring(1)));
        price += (amountSenior * Double.parseDouble(seniorPrice.getText().toString().substring(1)));
        price += (amountStudent * Double.parseDouble(studentPrice.getText().toString().substring(1)));

        amountOfTickets = amountChild + amountNormal +
                            amountSenior + amountStudent + amountTeen;
        totalPriceView.setText("€" + price + "0");
        return price;
    }
}
