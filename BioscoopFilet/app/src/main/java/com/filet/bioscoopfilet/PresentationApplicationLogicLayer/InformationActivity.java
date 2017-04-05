package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.textservice.TextServicesManager;
import android.widget.TextView;

import com.filet.bioscoopfilet.R;

import java.util.Locale;

public class InformationActivity extends AppCompatActivity {

    TextView locationAddress;
    TextView locationCity;
    TextView locationZipCode;

    TextView parkingAddress;
    TextView parkingCity;
    TextView parkingZipCode;

    private String language;
    private SharedPreferences languagepref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        //Setting up the toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.information);
        setSupportActionBar(myToolbar);

        //Declaration of location TextViews
        locationAddress = (TextView) findViewById(R.id.locationAddress);
        locationCity = (TextView) findViewById(R.id.locationCity);
        locationZipCode = (TextView) findViewById(R.id.locationZipCode);

        //Declaration of parking TextViews
        parkingAddress = (TextView) findViewById(R.id.parkingAddress);
        parkingCity = (TextView) findViewById(R.id.parkingCity);
        parkingZipCode = (TextView) findViewById(R.id.parkingZipCode);

        languagepref = getSharedPreferences("language", MODE_PRIVATE);
        language = languagepref.getString("languageToLoad", Locale.getDefault().toString());
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
                                Log.i("MenuItemSelected", "ENGELS");
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

    public void locationButton(View v){
        String address = locationAddress.getText().toString();
        String city = locationCity.getText().toString();

        Intent lIntent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.co.in/maps?q=" + address + "," + city));
        startActivity(lIntent);

    }

    public void parkingButton(View v){
        String address = parkingAddress.getText().toString();
        String city = parkingCity.getText().toString();

        Intent lIntent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q=" + address  + "+" + city));
        startActivity(lIntent);

    }


    public void ptInformationButton(View v){
        Intent ptIntent = new Intent(this, PTActivity.class);
        startActivity(ptIntent);

    }

    public void feedbackButton(View v){
        Intent fIntent = new Intent(this, FeedbackActivity.class);
        startActivity(fIntent);
    }

    public void contactButton(View v){
        Intent fIntent = new Intent(this, ContactActivity.class);
        startActivity(fIntent);
    }
}
