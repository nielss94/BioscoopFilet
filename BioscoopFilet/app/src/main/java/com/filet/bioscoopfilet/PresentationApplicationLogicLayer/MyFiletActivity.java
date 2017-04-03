package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
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

import com.filet.bioscoopfilet.R;

import java.util.Locale;

public class MyFiletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_filet);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.my_films);
        setSupportActionBar(myToolbar);
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
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_EN_US:
                                Log.i("MenuItemSelected", "ENGELS");
                                String languageToLoad  = "en-US";
                                Locale locale = new Locale(languageToLoad);
                                Locale.setDefault(locale);
                                Configuration config = new Configuration();
                                config.locale = locale;
                                getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

                                Intent intent = new Intent(getBaseContext(), MyFiletActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                startActivity(intent);
                                return true;

                            case R.id.action_NL:
                                Log.i("MenuItemSelected", "NEDERLANDS");
                                String languageToLoad2  = "nl";
                                Locale locale2 = new Locale(languageToLoad2);
                                Locale.setDefault(locale2);
                                Configuration config2 = new Configuration();
                                config2.locale = locale2;
                                getBaseContext().getResources().updateConfiguration(config2,getBaseContext().getResources().getDisplayMetrics());

                                Intent intent2 = new Intent(getBaseContext(), MyFiletActivity.class);
                                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                startActivity(intent2);
                                return true;

                            case R.id.action_EN_UK:
                                Log.i("MenuItemSelected", "NEDERLANDS");
                                String languageToLoad3  = "en-GB";
                                Locale locale3 = new Locale(languageToLoad3);
                                Locale.setDefault(locale3);
                                Configuration config3 = new Configuration();
                                config3.locale = locale3;
                                getBaseContext().getResources().updateConfiguration(config3,getBaseContext().getResources().getDisplayMetrics());

                                Intent intent3 = new Intent(getBaseContext(), MyFiletActivity.class);
                                intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

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


    public void myTicketsButton(View v) {
        Intent intent = new Intent(this, MyTicketActivity.class);
        startActivity(intent);
    }

    public void myFilmsButton(View v) {
        Intent intent = new Intent(this, MyFilmActivity.class);
        startActivity(intent);
    }
}
