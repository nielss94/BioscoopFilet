package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.filet.bioscoopfilet.R;

public class FeedbackActivity extends AppCompatActivity {

    EditText name;
    EditText feedback;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.feedback);
        setSupportActionBar(myToolbar);

        name = (EditText) findViewById(R.id.feedbackName);
        feedback = (EditText) findViewById(R.id.feedback);
        sendBtn = (Button) findViewById(R.id.sendFeedback);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void sendEmail() {
        String subjectName = name.getText().toString();
        String text = feedback.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO)
                .setData(new Uri.Builder().scheme("mailto").build())
                .putExtra(Intent.EXTRA_EMAIL, new String[]{ "Filet <wit.jesse.de@gmail.com>" })
                .putExtra(Intent.EXTRA_SUBJECT, subjectName + ": Feedback")
                .putExtra(Intent.EXTRA_TEXT, text + "");

        ComponentName emailApp = intent.resolveActivity(getPackageManager());
        ComponentName unsupportedAction = ComponentName.unflattenFromString("com.android.fallback/.Fallback");
        if (emailApp != null && !emailApp.equals(unsupportedAction))
            try {
                // Needed to customise the chooser dialog title since it might default to "Share with"
                // Note that the chooser will still be skipped if only one app is matched
                Intent chooser = Intent.createChooser(intent, "Send email with");
                startActivity(chooser);
                return;
            }
            catch (ActivityNotFoundException ignored) {
            }

        Toast
                .makeText(this, "Couldn't find an email app and account", Toast.LENGTH_LONG)
                .show();
    }
}
