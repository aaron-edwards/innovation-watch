package com.ioof.innovation.ioofsuperfit;

import android.content.Intent;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class EmailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        String messageBody = "";

        Bundle remoteInput = RemoteInput.getResultsFromIntent(getIntent());
        if (remoteInput != null) {
            messageBody = remoteInput.getCharSequence("email.message").toString();
        }

        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.email_address)});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "What is up IOOF!");
        emailIntent.putExtra(Intent.EXTRA_TEXT, messageBody);

        startActivity(emailIntent);

        finish();
    }
}
