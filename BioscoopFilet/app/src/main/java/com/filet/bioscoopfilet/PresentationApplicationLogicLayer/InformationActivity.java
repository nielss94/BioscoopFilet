package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.textservice.TextServicesManager;
import android.widget.TextView;

import com.filet.bioscoopfilet.R;

public class InformationActivity extends AppCompatActivity {

    TextView locationAddress;
    TextView locationCity;
    TextView locationZipCode;

    TextView parkingAddress;
    TextView parkingCity;
    TextView parkingZipCode;

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
                PopupMenu popup = new PopupMenu(this, findViewById(R.id.action_lang));
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_lang, popup.getMenu());
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
