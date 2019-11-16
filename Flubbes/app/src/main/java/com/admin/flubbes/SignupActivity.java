package com.admin.flubbes;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {

    TextView tTitle, tSubTitle1, tSubTitle2;
    Typeface tf, tf2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tSubTitle1 = (TextView) findViewById(R.id.idSubTitle1);
        tSubTitle2 = (TextView) findViewById(R.id.idSubTitle2);

        tf = Typeface.createFromAsset(getAssets(), "grand.otf");
        tf2 = Typeface.createFromAsset(getAssets(), "ignacious.ttf");
        tSubTitle1.setTypeface(tf);
        tSubTitle2.setTypeface(tf);


    }

    public void numget(View view) {

        Intent i = new Intent(SignupActivity.this, NumGet.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
