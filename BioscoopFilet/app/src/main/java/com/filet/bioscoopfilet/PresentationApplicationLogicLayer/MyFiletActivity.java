package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.filet.bioscoopfilet.R;

public class MyFiletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_filet);
    }

    public void myTicketsButton(View v) {
        Intent intent = new Intent(this, MyTicketActivity.class);
        startActivity(intent);
    }

//    public void myFilmsButton(View v) {
//        Intent intent = new Intent(this, MyFilmsActivity.class);
//        startActivity(intent);
//    }
}
