package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.filet.bioscoopfilet.DomainModel.Show;
import com.filet.bioscoopfilet.R;

public class BuyTicketsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_tickets);

        //Setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.buy_tickets);
        setSupportActionBar(myToolbar);
    }

    public void seatButton(View v) {
        Show show = (Show) getIntent().getSerializableExtra("SHOW");

        Intent intent = new Intent(getApplicationContext(), SeatSelectionActivity.class);
        intent.putExtra("SHOW", show);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
