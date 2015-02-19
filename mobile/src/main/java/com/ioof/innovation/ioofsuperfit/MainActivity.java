package com.ioof.innovation.ioofsuperfit;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onNotification1Click(View v){
        int notificationId = 01;

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("IOOF Superfit")
                .bigText(getString(R.string.notification_1_text));

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                    .setContentTitle(getString(R.string.app_name))
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setPriority(100)
                    .setStyle(bigTextStyle)
                    .setColor(0xffffffff)
                    .extend(new NotificationCompat.WearableExtender()
                                    .setBackground(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.background))
                                    .addAction(createCallAction())
                                    .addAction(createEmailAction())
                    );

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        notificationManager.notify(notificationId, notificationBuilder.build());

    }

    private NotificationCompat.Action createEmailAction() {
        RemoteInput remoteInput = new RemoteInput.Builder("email.message")
                .setLabel(getString(R.string.message_label))
                .build();

        Intent emailIntent = new Intent(getApplicationContext(), EmailActivity.class);

        PendingIntent emailPendingIntent =
                PendingIntent.getActivity(this, 0, emailIntent, 0);

        return new NotificationCompat.Action.Builder(R.drawable.abc_textfield_activated_mtrl_alpha,
                getString(R.string.email_label), emailPendingIntent)
                .addRemoteInput(remoteInput)
                .build();
    }

    private NotificationCompat.Action createCallAction() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        Uri phoneURI = Uri.parse("tel:" + getString(R.string.phone_number));

        callIntent.setData(phoneURI);
        PendingIntent callPendingIntent =
                PendingIntent.getActivity(this, 0, callIntent, 0);

        return new NotificationCompat.Action.Builder(R.drawable.common_full_open_on_phone,
                getString(R.string.call_label), callPendingIntent)
                .build();
    }

}
