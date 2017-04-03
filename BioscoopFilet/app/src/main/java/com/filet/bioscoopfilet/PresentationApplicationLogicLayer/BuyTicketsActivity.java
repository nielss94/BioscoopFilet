package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.filet.bioscoopfilet.DomainModel.Show;
import com.filet.bioscoopfilet.R;

public class BuyTicketsActivity extends AppCompatActivity {

    private Double totalPrice;
    private int amountOfTickets;
    private TextView totalPriceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_tickets);

        //Setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.buy_tickets);
        setSupportActionBar(myToolbar);

        totalPriceView = (TextView)findViewById(R.id.totalPrice);
        totalPrice = calculatePrice();
    }

    public void seatButton(View v) {
        Intent intent = new Intent(this, SeatSelectionActivity.class);

        Show show = (Show) getIntent().getSerializableExtra("SHOW");

        intent.putExtra("amountOfTickets", amountOfTickets);
        intent.putExtra("totalPrice", totalPrice);
        intent.putExtra("SHOW", show);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void plusOne(View v)
    {
        int i;
        TextView count;
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
        totalPrice = calculatePrice();
    }

    public void minOne(View v)
    {
        int i;
        TextView count;
        switch(v.getTag().toString())
        {
            case "Normaal":
                count = (TextView)findViewById(R.id.count1);
                i = Integer.parseInt(count.getText().toString());
                if(i > 0){
                    count.setText((i - 1) + "");
                }
                break;
            case "Tiener":
                count = (TextView)findViewById(R.id.count2);
                i = Integer.parseInt(count.getText().toString());
                if(i > 0) {
                    count.setText((i - 1) + "");
                }
                break;
            case "Kind":
                count = (TextView)findViewById(R.id.count3);
                i = Integer.parseInt(count.getText().toString());
                if(i > 0)
                {
                    count.setText((i - 1) + "");
                }
                break;
            case "Senior":
                count = (TextView)findViewById(R.id.count4);
                i = Integer.parseInt(count.getText().toString());
                if(i > 0) {
                    count.setText((i - 1) + "");
                }
                break;
            case "Student":
                count = (TextView)findViewById(R.id.count5);
                i = Integer.parseInt(count.getText().toString());
                if(i > 0) {
                    count.setText((i - 1) + "");
                }
                break;
            case "3D bril":
                count = (TextView)findViewById(R.id.countGlasses);
                i = Integer.parseInt(count.getText().toString());
                if(i > 0) {
                    count.setText((i - 1) + "");
                }
                break;
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
