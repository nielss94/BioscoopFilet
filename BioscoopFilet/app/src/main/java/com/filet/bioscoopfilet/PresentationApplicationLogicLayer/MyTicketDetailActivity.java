package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.filet.bioscoopfilet.DomainModel.Ticket;
import com.filet.bioscoopfilet.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MyTicketDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket_detail);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.my_ticket_details);
        setSupportActionBar(myToolbar);

        //get intent extras
        Ticket ticket = (Ticket) getIntent().getSerializableExtra("ticket");

        //initialise xml elements
        TextView title = (TextView) findViewById(R.id.ticketDetailFilmTitle);
        TextView amount = (TextView) findViewById(R.id.ticketDetailTicketAmount);
        TextView seats = (TextView) findViewById(R.id.ticketDetailSeats);
        ImageView qrCode = (ImageView) findViewById(R.id.ticketDetailQRCode);

        //fill xml elements with intent extras
        title.setText(ticket.getShow().getFilm().getTitle());
        amount.setText(getString(R.string.amount) + " " + ticket.getShow().getFilm().getLength() + " min.");
        seats.setText(getString(R.string.seats) + " " + ticket.getSeat());

        //generate qrCode
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(ticket.getQrCode()+"", BarcodeFormat.QR_CODE, 800, 800);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
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
}
