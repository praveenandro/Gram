package com.example.flubbesdriver;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    ImageView im;
    private static int SPLASH_TIME_OUT = 3000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        im = (ImageView) findViewById(R.id.imageView);



       // displayNotification();

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                Intent i = new Intent(Splash.this, NumGet.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);

       /* Intent i = new Intent(Splash.this,Home.class);
        startActivity(i);*/
    }




}
