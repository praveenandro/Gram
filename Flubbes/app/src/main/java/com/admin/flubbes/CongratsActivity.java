package com.admin.flubbes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CongratsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);
    }

    public void continuee(View view) {
        Intent i = new Intent(CongratsActivity.this, PermissionsActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }
}
