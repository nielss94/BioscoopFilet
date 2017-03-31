package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.DomainModel.Show;
import com.filet.bioscoopfilet.R;

public class PaymentActivity extends AppCompatActivity {

    TextView payTitle;
    TextView payVersion;
    TextView payDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //Setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.pay_tickets);
        setSupportActionBar(myToolbar);

        //Getting film given by activities before
        Show show = (Show) getIntent().getSerializableExtra("SHOW");
        Film film =  show.getFilm();

        //Declaration of TextViews
        payTitle = (TextView) findViewById(R.id.payTitle);
        payVersion = (TextView) findViewById(R.id.payVersion);
        payDateTime = (TextView) findViewById(R.id.payDateTime);

        //Filling TextViews with film info
        payTitle.setText(film.getTitle());
        payVersion.setText(film.getVersion());

        //Filling TextView with show info
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd/MM/yy - HH:mm");
        payDateTime.setText(format.format(show.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
