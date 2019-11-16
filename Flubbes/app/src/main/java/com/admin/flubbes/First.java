package com.admin.flubbes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class First extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

       // toolbar.setTitle("Shop");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_cabs:

              /*      SharedPreferences sp = getSharedPreferences("User", MODE_PRIVATE);
                    if(sp.getString("a","").isEmpty()){
                        Intent in=new Intent(First.this,SignupActivity.class);
                        startActivity(in);
                    }else{*/
                        Intent in=new Intent(First.this,Home.class);
                        startActivity(in);
                    /*}*/

                    return true;
                case R.id.navigation_trends:
                    Intent in1=new Intent(First.this,SampleFragment.class);
                    startActivity(in1);
                    return true;
                case R.id.navigation_food:
                    Intent in2=new Intent(First.this,SampleFragment.class);
                    startActivity(in2);
                    return true;
                case R.id.navigation_groccery:
                    Intent in3=new Intent(First.this,SampleFragment.class);
                    startActivity(in3);
                    return true;

            }
            return false;
        }
    };
}