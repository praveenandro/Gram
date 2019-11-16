package com.admin.flubbes;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    ImageView im;
    private static int SPLASH_TIME_OUT = 3000;

    private static final String CHANNEL_ID = "Flubbes";
    private static final String CHANNEL_NAME = "Flubbes";
    private static final String CHANNEL_DESC = "Flubbes Notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        im = (ImageView) findViewById(R.id.imageView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        displayNotification();

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                SharedPreferences sp = getSharedPreferences("Userphone", MODE_PRIVATE);
                if (sp.getString("phone", "").isEmpty()) {
                    Intent i = new Intent(Splash.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(Splash.this, Home.class);
                    startActivity(i);
                }


              /*  Fragment fragment = new Homee();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();*/

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

       /* Intent i = new Intent(Splash.this,Home.class);
        startActivity(i);*/
    }

    private void displayNotification() {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Flubbes.!")
                .setContentText("Hey Customer Enjoy Your RIDES WITH Flubbes!.... Earn Money through your reference... Have a Fabulous Journey")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat mNotificationMgr = NotificationManagerCompat.from(this);
        mNotificationMgr.notify(1, mBuilder.build());
    }

 /*   private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }*/
}
