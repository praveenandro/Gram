package com.jksystems.gram.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;
import com.jksystems.gram.R;
import com.jksystems.gram.fragment.AdminFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ADMINISTRATION");
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        if(savedInstanceState==null) {


            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AdminFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_administration);
        }

    }





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.nav_administration:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AdminFragment()).commit();
                break;
            case R.id.nav_demand_col:
                Intent i = new Intent(MainActivity.this,DemandCollection.class);
                startActivity(i);
                break;

            /*case R.id.nav_user:
              *//*  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UserFragment()).commit();
                break;*//*
                Intent i = new Intent(MainActivity.this,DetailsActivity.class);
                startActivity(i);
                break;

            case R.id.nav_bill:
                Intent ie = new Intent(MainActivity.this,BillActivity.class);
                startActivity(ie);
                break;

            case R.id.nav_message:
              *//*  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MessageFragment()).commit();
                break;*/

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);

        }else{
            super.onBackPressed();
        }

    }
}
