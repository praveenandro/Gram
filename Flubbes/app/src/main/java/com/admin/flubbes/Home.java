package com.admin.flubbes;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Geocoder;
import android.location.Location;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;


import com.google.android.gms.location.LocationListener;


public class Home extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, NavigationView.OnNavigationItemSelectedListener {

    TextView navt1, navt2, tRidefare, tRideduration, tDailyRides, tRentals, tOutstations;
    SharedPreferences spnum;
    double latitude, longitude;
    String phone;
    private AddCustomersTask mAddCustomersTask = null;
    // private View mHome_progress;
    private String AddUserURL = "http://flubbes.com/Apis/fetch_userdetails.php";
    private Context conx;
    String TAG = "Home", jName, jPhone, jOwnRefCode, jWallet, jEmail;

    //--mapactivity---
    RelativeLayout rl, rlmaplayout;
    int flag = 0;
    boolean isSourceSet = false, tripStarted = false;
    ;
    EditText source_location, destination_location;
    String TAGG = "LocationSelect";
    int AUTOCOMPLETE_SOURCE = 1, AUTOCOMPLETE_DESTINATITON = 2;
    GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker source_location_marker, destination_location_marker;
    Marker nearby_cab;
    LocationRequest mLocationRequest;
    //ArrayList<LatLng> markerPoints;
    Button btnBookNow;
    FrameLayout frame2, frame3, frame4;
    ImageView img1, img2, img3, img4;
    ImageView cab;
    RelativeLayout driver_info;
    LinearLayout ll_call, ll_share, ll_cancel, lCabSelection;
    String driver_name, cab_no, cab_id, otp, fare, driver_phone, ride_id;
    TextView cab_no_a, cab_no_b, ride_otp, ride_driver_name, ride_fare;
    TextView daily, rental;
    String PREFS_NAME = "auth_info";
    ProgressDialog progressDialog;
    Timer timer;
    Handler handler;
    ImageView micro, mini, prime, auto;

    SupportMapFragment mapFragment;
    //---------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // View view = inflater.inflate(R.layout.homee, null, false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

       /* NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navt1 = (TextView) headerView.findViewById(R.id.navtext1);*/
       /* navt2 = (TextView) headerView.findViewById(R.id.navtext2);
        frame2 = (FrameLayout) findViewById(R.id.frame2);
        frame3 = (FrameLayout) findViewById(R.id.frame3);
        frame4 = (FrameLayout) findViewById(R.id.frame4);
        tRidefare = (TextView) findViewById(R.id.ride_fare);
        tRideduration = (TextView) findViewById(R.id.idtextduration);

        micro = (ImageView) findViewById(R.id.micro);
        mini = (ImageView) findViewById(R.id.mini);
        prime = (ImageView) findViewById(R.id.prime);
        auto = (ImageView) findViewById(R.id.auto);
        rlmaplayout = (RelativeLayout) findViewById(R.id.maplayout);

       *//* SharedPreferences sp = getSharedPreferences("User", MODE_PRIVATE);
        spnum = getSharedPreferences("Userphone", MODE_PRIVATE);*//*
        conx = this;
        rl = (RelativeLayout) findViewById(R.id.maplayout);*/


        //mHome_progress = findViewById(R.id.home_progress);
       // getDetails();


        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowTitleEnabled(false);

      //  frame2.setVisibility(View.VISIBLE);


     /*   tDailyRides = (TextView) toolbar.findViewById(R.id.idDailyRides);
        tRentals = (TextView) toolbar.findViewById(R.id.idRentals);
        tOutstations = (TextView) toolbar.findViewById(R.id.idOutstations);
        tDailyRides.setTextColor(Color.parseColor("#000000"));

        micro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                micro.setImageResource(R.drawable.micro_color);
                mini.setImageResource(R.drawable.mini);
                prime.setImageResource(R.drawable.prime);
                auto.setImageResource(R.drawable.rickshaw);
            }
        });
        mini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   mini.setBackgroundResource(R.drawable.mini_color);
                mini.setImageResource(R.drawable.mini_color);
                micro.setImageResource(R.drawable.micro);

                prime.setImageResource(R.drawable.prime);
                auto.setImageResource(R.drawable.rickshaw);
            }
        });
        prime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prime.setImageResource(R.drawable.primecolor);
                micro.setImageResource(R.drawable.micro);
                mini.setImageResource(R.drawable.mini);

                auto.setImageResource(R.drawable.rickshaw);
            }
        });
        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auto.setImageResource(R.drawable.rickshaw_color);
                micro.setImageResource(R.drawable.micro);
                mini.setImageResource(R.drawable.mini);
                prime.setImageResource(R.drawable.prime);

            }
        });
        tDailyRides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tDailyRides.setBackgroundResource(R.drawable.layoutbg);
                tDailyRides.setTextColor(Color.parseColor("#000000"));
                tRentals.setBackgroundResource(0);
                tOutstations.setBackgroundResource(0);
                frame2.setVisibility(View.VISIBLE);
                frame3.setVisibility(View.INVISIBLE);
                frame4.setVisibility(View.INVISIBLE);

            }
        });

        tRentals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tRentals.setBackgroundResource(R.drawable.layoutbg);
                tRentals.setTextColor(Color.parseColor("#000000"));
                tDailyRides.setBackgroundResource(0);
                tOutstations.setBackgroundResource(0);
                frame2.setVisibility(View.INVISIBLE);
                frame3.setVisibility(View.VISIBLE);
                frame4.setVisibility(View.INVISIBLE);
            }
        });

        tOutstations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tOutstations.setBackgroundResource(R.drawable.layoutbg);
                tOutstations.setTextColor(Color.parseColor("#000000"));
                tDailyRides.setBackgroundResource(0);
                tRentals.setBackgroundResource(0);
                frame2.setVisibility(View.INVISIBLE);
                frame4.setVisibility(View.VISIBLE);
                frame3.setVisibility(View.INVISIBLE);
            }
        });*/

       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //  NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(Home.this);*/

//-----mapactivity----


        handler = new Handler();

       // driver_info = (RelativeLayout) findViewById(R.id.driver_details);

        // driver_info.setVisibility(View.GONE);

       /* BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Booking...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgress(0);
        progressDialog.setCancelable(false);

        ll_call = (LinearLayout) findViewById(R.id.ll_call);
        ll_share = (LinearLayout) findViewById(R.id.ll_share);
        ll_cancel = (LinearLayout) findViewById(R.id.ll_cancel);

        cab_no_a = (TextView) findViewById(R.id.cab_no_a);
        cab_no_b = (TextView) findViewById(R.id.cab_no_b);
        ride_driver_name = (TextView) findViewById(R.id.driver_name);
        ride_otp = (TextView) findViewById(R.id.ride_otp);
        // ride_fare = (TextView) findViewById(R.id.ride_fare);

        btnBookNow = (Button) findViewById(R.id.btnBookNow);
        // btnBookNow.setVisibility(View.GONE);


        cab = (ImageView) findViewById(R.id.cab);
        cab.setVisibility(View.GONE);

        timer = new Timer();

        // markerPoints = new ArrayList<LatLng>();*/

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(threadPolicy);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        source_location = (EditText) findViewById(R.id.source_location);
        destination_location = (EditText) findViewById(R.id.destination_location);


      /*  btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();

                try {

                    String api_url = "https://nearcabs.000webhostapp.com/api/book_cab.php";

                    double src_lat = source_location_marker.getPosition().latitude;
                    double src_lng = source_location_marker.getPosition().longitude;

                    double dest_lat = destination_location_marker.getPosition().latitude;
                    double dest_lng = destination_location_marker.getPosition().longitude;

                       *//* SharedPreferences sharedPreferences= getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

                        String user_id=sharedPreferences.getString("id", null);*//*

                    String book_now_request = "user_id=" + URLEncoder.encode("1", "UTF-8") + "&src_lat=" + URLEncoder.encode(String.valueOf(src_lat), "UTF-8") + "&src_lng=" + URLEncoder.encode(String.valueOf(src_lng), "UTF-8") + "&dest_lat=" + URLEncoder.encode(String.valueOf(dest_lat), "UTF-8") + "&dest_lng=" + URLEncoder.encode(String.valueOf(dest_lng), "UTF-8");

                    JSONObject response_data = call_api(api_url, book_now_request);
                    if (response_data.getString("status").equals("1")) {
                        if (nearby_cab != null) {
                            nearby_cab.remove();
                        }

//                        MarkerOptions markerOptions1=new MarkerOptions();
                        JSONObject book_cab_response_data = response_data.getJSONObject("data");

                        ride_id = book_cab_response_data.getString("ride_id");

                        cab_no = book_cab_response_data.getString("cab_no");
                        cab_id = book_cab_response_data.getString("cab_id");


                        driver_name = book_cab_response_data.getString("driver_name");


                        driver_phone = book_cab_response_data.getString("driver_phone");

                        otp = "OTP : " + book_cab_response_data.getString("otp");


                        fare = "Rs. " + book_cab_response_data.getString("fare");

                        cab_no_a.setText(cab_no.split(" ")[0]);
                        cab_no_b.setText(cab_no.split(" ")[1]);

                        ride_driver_name.setText(driver_name);
                        ride_otp.setText(otp);

                        ride_fare.setText(fare);


                        ll_call.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:" + driver_phone));

                                if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                startActivity(callIntent);
                            }
                        });


                        ll_share.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                                sharingIntent.setType("text/plain");
                                String shareBody = driver_name + " (" + driver_phone + ") is on the way in Cab number " + cab_no + ". You are paying " + fare + " for this ride. Share " + otp + " with the driver to start the ride.";
                                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Near Cabs Booking");
                                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                            }
                        });


                        ll_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    String cancel_api_url = "https://nearcabs.000webhostapp.com/api/cancel_book_cab.php";
                                    String cancel_book_now_request = "ride_id=" + URLEncoder.encode(ride_id, "UTF-8") + "&cab_id=" + URLEncoder.encode(cab_id, "UTF-8");

                                    JSONObject cancel_response_data = call_api(cancel_api_url, cancel_book_now_request);
                                    if (cancel_response_data.getString("status").equals("1")) {
                                        Toast.makeText(getApplicationContext(), "Booking Cancelled", Toast.LENGTH_SHORT).show();
                                        driver_info.setVisibility(View.GONE);
                                        btnBookNow.setVisibility(View.VISIBLE);
                                    }
                                } catch (Exception e) {
//                                 Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                        btnBookNow.setVisibility(View.GONE);
                        driver_info.setVisibility(View.VISIBLE);

                        progressDialog.hide();

//                        updateNearbyCabPosition();

                        handler.postDelayed(runnable, 0);


                    } else {
                        Toast.makeText(getApplicationContext(), "No cabs nearby", Toast.LENGTH_LONG).show();
                    }


                } catch (Exception e) {
//                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }


            }
        });*/

//        Toast.makeText(getApplicationContext(), this.toString(), Toast.LENGTH_LONG).show();
        final Activity activity = this;

        source_location.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    try {
                       /*Intent intent =
                               new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(activity);
                        startActivityForResult(intent, AUTOCOMPLETE_SOURCE);*/
                        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                        startActivityForResult(builder.build(activity), AUTOCOMPLETE_SOURCE);
                    } catch (GooglePlayServicesRepairableException e) {
                        // TODO: Handle the error.
                        Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    } catch (GooglePlayServicesNotAvailableException e) {
                        // TODO: Handle the error.
                        Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();

                    }
                }
            }
        });


        source_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    Intent intent =
//                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(activity);
//                    startActivityForResult(intent, AUTOCOMPLETE_SOURCE);
                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                    startActivityForResult(builder.build(activity), AUTOCOMPLETE_SOURCE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                    Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                    Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();

                }
            }
        });


        destination_location.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
//                Toast.makeText(getApplicationContext(), "Destination", Toast.LENGTH_SHORT).show();
                if (b) {
                    try {
//                        Intent intent =
//                                new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(activity);
//                        startActivityForResult(intent, AUTOCOMPLETE_DESTINATITON);
                        //lCabSelection.setVisibility(View.VISIBLE);
                        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                        startActivityForResult(builder.build(activity), AUTOCOMPLETE_DESTINATITON);
                    } catch (GooglePlayServicesRepairableException e) {
                        // TODO: Handle the error.
//                        Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    } catch (GooglePlayServicesNotAvailableException e) {
                        // TODO: Handle the error.
//                        Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();

                    }
                }
            }
        });


        destination_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    Intent intent =
//                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(activity);
//                    startActivityForResult(intent, AUTOCOMPLETE_DESTINATITON);
                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                    startActivityForResult(builder.build(activity), AUTOCOMPLETE_DESTINATITON);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
//                    Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
//                    Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();

                }
            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


// ----------mapactivity-------------


        //      return view;
    }


    private void getDetails() {
        mAddCustomersTask = new AddCustomersTask();
        mAddCustomersTask.execute();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }*/

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.drides) {

            return true;
        }
        if (id == R.id.rentals) {
            return true;
        }
        if (id == R.id.outstation) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    int prev_id;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.nav_orders) {
            prev_id = R.id.nav_orders;
            rl.setVisibility(View.INVISIBLE);          //   mMap.setVisibility(View.INVISIBLE);
            fragment = new OrderFragment();
        } else if (id == R.id.nav_account) {
            prev_id = R.id.nav_account;
            // mMapView.setVisibility(View.INVISIBLE);
            rl.setVisibility(View.INVISIBLE);
            fragment = new AccFragment();
        } else if (id == R.id.nav_ewallet) {
            prev_id = R.id.nav_ewallet;
           /* mMapView.setVisibility(View.INVISIBLE);

            fragment = new WalletFrag();*/
            Intent i = new Intent(Home.this, WalletFragment.class);
            startActivity(i);
        } else if (id == R.id.nav_sell) {
      /*      prev_id = R.id.nav_sell;
            mMapView.setVisibility(View.INVISIBLE);

            fragment = new SellFrag();*/
            Intent i = new Intent(Home.this, SellFrag.class);
            startActivity(i);
        } else if (id == R.id.nav_deals) {
            prev_id = R.id.nav_deals;
            // mMapView.setVisibility(View.INVISIBLE);

            /*fragment = new DealFrag();*/

            Intent o = new Intent(Home.this, NotificationActivity.class);
            startActivity(o);
        }else if (id == R.id.rides) {
            prev_id = R.id.rides;
            // mMapView.setVisibility(View.INVISIBLE);

            /*fragment = new DealFrag();*/

            Intent o = new Intent(Home.this, RideDetails.class);
            startActivity(o);
        }

        else if (id == R.id.nav_logout) {
            prev_id = R.id.nav_logout;
            //   mMapView.setVisibility(View.INVISIBLE);
            AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
            builder.setTitle("LOGOUT");
            builder.setMessage("Are You Sure ?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences sp = getSharedPreferences("Userphone", MODE_PRIVATE);
                    SharedPreferences.Editor ed = sp.edit();
                    ed.clear();
                    ed.apply();
                    Intent i = new Intent(Home.this, Splash.class);
                    startActivity(i);
                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

            //fragment = new logout();
        } else if (id == R.id.nav_refer) {
            prev_id = R.id.nav_refer;
           /* mMapView.setVisibility(View.INVISIBLE);

            fragment = new DealFrag();*/

            Intent i = new Intent(Home.this, Refer.class);
            startActivity(i);
        } else if (id == R.id.nav_home) {
            // prev_id =  R.id.nav_home;

            //  mMapView.setVisibility(View.VISIBLE);
//            Intent i = new Intent(Home.this, MapsActivity.class);
//            startActivity(i);
            rl.setVisibility(View.VISIBLE);
            for (Fragment fragment1 : getSupportFragmentManager().getFragments()) {
                if (fragment instanceof Fragment) {
                    continue;
                } else {
                    getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
                    mapFragment.onResume();
                }
            }

        }


        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.contentframe, fragment);

            //    mMapView.onResume();

            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class AddCustomersTask extends AsyncTask<String, Void, String> {
        String name, user, pass;

        @Override
        protected void onPreExecute() {
            phone = spnum.getString("phone", "");
            // Toast.makeText(getApplicationContext(),phone,Toast.LENGTH_SHORT).show();
            //  showProgress(true);
          /*  name = nam.getText().toString().trim();
            user = unam.getText().toString().trim();
            pass = pwd.getText().toString().trim();*/
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO: attempt authentication against a network service.
            String resp = "";
            try {

                // for GET Method
                String URL = "";

                URL = AddUserURL + "?phone=" + phone;

                URL = URL.replace(" ", "%20");
                HttpClient Client = new DefaultHttpClient();
                HttpGet httpget = new HttpGet(URL);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                resp = Client.execute(httpget, responseHandler).trim();

            } catch (Exception e) {
                resp = e.toString();
            }

            // TODO: register the new account here.
            return resp;
        }

        @Override
        protected void onPostExecute(final String result) {

            try {
                if (result.startsWith("Error")) {
                    Toast.makeText(conx, "ph" + result, Toast.LENGTH_LONG).show();
                } else {
                    boolean isJsArr = false;
                    try {
                        JSONArray jsArray1 = new JSONArray(result);
                        isJsArr = true;
                    } catch (Exception e) {
                        isJsArr = false;
                    }

                    if (isJsArr) {
                        JSONArray jsArray = new JSONArray(result);
                        JSONObject jsObj;
                        int len = jsArray.length();

                        for (int i = 0; i < len; i++) {
                            jsObj = jsArray.getJSONObject(i);
                            jName = jsObj.getString("user_name");
                            jPhone = jsObj.getString("phone");
                            jOwnRefCode = jsObj.getString("ownref_code");
                            jWallet = jsObj.getString("wallet");
                            jEmail = jsObj.getString("email");
                            Log.e(TAG, "name: " + jName);
                            Log.e(TAG, "phone: " + jPhone);
                            Log.e(TAG, "ownrefcode: " + jOwnRefCode);
                            Log.e(TAG, "wallet: " + jWallet);
                            Log.e(TAG, "email: " + jEmail);
                        }
                        navt1.setText(jName);
                        navt2.setText(jEmail);

                    } else {
                        Toast.makeText(conx, result, Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "Error is " + e.toString());
            }

            //    showProgress(false);

        }

        @Override
        protected void onCancelled() {
            mAddCustomersTask = null;
            //showProgress(false);
        }
    }

   /* private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_cabs:


                    return true;
                case R.id.navigation_trends:

                    return true;
                case R.id.navigation_food:

                    return true;
                case R.id.navigation_groccery:

                    return true;

            }
            return false;
        }
    };*/

   /* public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            MarkerOptions markerOptions1;
            try {

                String api_url = "https://nearcabs.000webhostapp.com/api/get_cab_location.php";

                String get_cab_location_request = "cab_id=" + URLEncoder.encode(cab_id, "UTF-8") + "&ride_id=" + URLEncoder.encode(ride_id, "UTF-8");

                JSONObject response_data = call_api(api_url, get_cab_location_request);

//                Toast.makeText(getApplicationContext(), response_data.toString(), Toast.LENGTH_LONG).show();

                if (response_data.getString("status").equals("1")) {
                    if (nearby_cab != null) {
                        nearby_cab.remove();
                    }

                    markerOptions1 = new MarkerOptions();
                    JSONObject get_cab_position_response_data = response_data.getJSONObject("data");


                    LatLng nearby_cab_position = new LatLng(Double.parseDouble(get_cab_position_response_data.getString("cab_lat")), Double.parseDouble(get_cab_position_response_data.getString("cab_lng")));
                    markerOptions1.position(nearby_cab_position);

                    BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.car);
                    Bitmap b = bitmapDrawable.getBitmap();
                    Bitmap smallCar = Bitmap.createScaledBitmap(b, 60, 72, false);
                    markerOptions1.icon(BitmapDescriptorFactory.fromBitmap(smallCar));
                    markerOptions1.rotation(Float.parseFloat(get_cab_position_response_data.getString("cab_bearing")));

                    nearby_cab = mMap.addMarker(markerOptions1);

                    handler.postDelayed(this, 10000);
                } else if (response_data.getString("status").equals("2")) {
                    ll_cancel.setClickable(false);
                    handler.removeCallbacksAndMessages(runnable);
                    if (nearby_cab != null) {
                        nearby_cab.remove();
                    }

                    tripStarted = true;
                    cab.setVisibility(View.VISIBLE);
                } else if (response_data.getString("status").equals("3")) {
                    ll_cancel.setClickable(false);
                    handler.removeCallbacksAndMessages(runnable);
                } else {
                    handler.postDelayed(this, 10000);
                }

            } catch (Exception e) {
                handler.postDelayed(this, 10000);
            }


        }
    };*/

   /* public JSONObject call_api(String api_url, String request_data) {
        try {
            URL url = new URL(api_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(request_data);
            writer.flush();
            writer.close();
            os.close();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String response = "";
            while ((line = bufferedReader.readLine()) != null) {
                response += line;
            }

            Log.d("API response", response);

            JSONObject response_data = new JSONObject(response);
            return response_data;

        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }

        return null;
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AUTOCOMPLETE_SOURCE) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                Log.i(TAG, "Place: " + place.getName());
                source_location.setText(place.getName());

                if (source_location_marker != null) {
                    source_location_marker.remove();
                }

                LatLng latLng = place.getLatLng();
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Source");
//                BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.car);
//                Bitmap b = bitmapDrawable.getBitmap();
//                Bitmap smallCar = Bitmap.createScaledBitmap(b, 150, 81, false);

//                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallCar));
//                markerOptions.rotation(location.getBearing());
                source_location_marker = mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));


                //--> setNearbyCabsOnMap(latLng);


            }
//            else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
//                Status status = PlaceAutocomplete.getStatus(this, data);
//                // TODO: Handle the error.
//                Log.i(TAG, status.getStatusMessage());
//
//            }
            else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        } else if (requestCode == AUTOCOMPLETE_DESTINATITON) {
            if (resultCode == RESULT_OK) {
//                Place place = PlaceAutocomplete.getPlace(this, data);
                Place place = PlacePicker.getPlace(this, data);
                Log.i(TAG, "Place: " + place.getName());
                destination_location.setText(place.getName());

                if (destination_location_marker != null) {
                    destination_location_marker.remove();
                }

                LatLng latLng = place.getLatLng();
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Destination");
//                BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.car);
//                Bitmap b = bitmapDrawable.getBitmap();
//                Bitmap smallCar = Bitmap.createScaledBitmap(b, 150, 81, false);

//                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallCar));
//                markerOptions.rotation(location.getBearing());
                destination_location_marker = mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));

                String url = getDirectionsUrl(source_location_marker.getPosition(), destination_location_marker.getPosition());
                //  Toast.makeText(getApplicationContext(),String.valueOf(source_location_marker.getPosition()+"/nfgf"+String.valueOf(destination_location_marker.getPosition())),Toast.LENGTH_SHORT).show();
              /*  Home.DownloadTask downloadTask = new DownloadTask();

                // Start downloading json data from Google Directions API
                downloadTask.execute(url);*/
//                lCabSelection.setVisibility(View.GONE);
                // btnBookNow.setVisibility(View.VISIBLE);
                   /* if (!source_location.getText().toString().equals("") && !destination_location.getText().toString().equals("")) {



                        btnBookNow.setVisibility(View.VISIBLE);
                    }
                    else {
                        btnBookNow.setVisibility(View.GONE);
                    }*/

            }
//            else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
//                Status status = PlaceAutocomplete.getStatus(getApplicationContext(), data);
//                // TODO: Handle the error.
//                Log.i(TAG, status.getStatusMessage());
//
//            }
            else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }


    /*public void setNearbyCabsOnMap(LatLng latLng) {
        try {
            String api_url = "https://nearcabs.000webhostapp.com/api/get_near_cabs.php";

            double user_lat = latLng.latitude;
            double user_lng = latLng.longitude;

            String pickup_location_cabs_request = "user_lat=" + URLEncoder.encode(String.valueOf(user_lat), "UTF-8") + "&user_lng=" + URLEncoder.encode(String.valueOf(user_lng), "UTF-8");

            JSONObject response_data = call_api(api_url, pickup_location_cabs_request);
            if (response_data.getString("status").equals("1")) {
                if (nearby_cab != null) {
                    nearby_cab.remove();
                }

                MarkerOptions markerOptions1 = new MarkerOptions();
                JSONObject nearby_cab_position_data = response_data.getJSONObject("data");

//              cab_no=nearby_cab_position_data.getString("cab_no");
//              cab_no_a.setText(cab_no.split(" ")[0]);
//              cab_no_b.setText(cab_no.split(" ")[1]);
//
//              driver_name=nearby_cab_position_data.getString("driver_name");
//              ride_driver_name.setText(driver_name);
//
//              driver_phone=nearby_cab_position_data.getString("driver_phone");


                LatLng nearby_cab_position = new LatLng(Double.parseDouble(nearby_cab_position_data.getString("cab_lat")), Double.parseDouble(nearby_cab_position_data.getString("cab_lng")));
                markerOptions1.position(nearby_cab_position);

                BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.car);
                Bitmap b = bitmapDrawable.getBitmap();
                Bitmap smallCar = Bitmap.createScaledBitmap(b, 60, 72, false);
                markerOptions1.icon(BitmapDescriptorFactory.fromBitmap(smallCar));
                markerOptions1.rotation(mLastLocation.getBearing());

                nearby_cab = mMap.addMarker(markerOptions1);
            } else {
                Toast.makeText(getApplicationContext(), "No cabs nearby", Toast.LENGTH_LONG).show();
            }


        } catch (Exception e) {
//          Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
    }*/


    private String getDirectionsUrl(LatLng origin, LatLng dest) {
        String API = "AIzaSyByDyuf-tKW4k1ZAvOXlcG60ReoeZzd7Vw";

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";
        Toast.makeText(getApplicationContext(), parameters, Toast.LENGTH_LONG).show();
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters+ "&key=" + API;
       /*Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        startActivity(i);*/
        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            // Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();

            br.close();

        } catch (Exception e) {
//            Log.d("Exception while downloading url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);

                //  Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();
            String distance = "";
            String duration = "";
//Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();
            if (result.size() < 1) {
                Toast.makeText(getApplicationContext(), "No Points", Toast.LENGTH_SHORT).show();
                return;
            }

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);


                    if (j == 0) {    // Get distance from the list
                        distance = (String) point.get("distance");
                       // Toast.makeText(getApplicationContext(), ""+distance, Toast.LENGTH_SHORT).show();
                        continue;
                    } else if (j == 1) { // Get duration from the list
                        duration = (String) point.get("duration");
                      //  Toast.makeText(getApplicationContext(), ""+duration, Toast.LENGTH_SHORT).show();
                        continue;
                    }

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(5);
                lineOptions.color(Color.BLUE);

            /*    PolylineOptions rectOptions = new PolylineOptions()
                        .addAll(points)
                        .width(5)
                        .color(Color.RED);*/


            }
            mMap.addPolyline(lineOptions);
            tRideduration.setText(duration + " mins");
            //	tvDistanceDuration.setText("Distance:"+distance + ", Duration:"+duration);

            // Drawing polyline in the Google Map for the i-th route
            //mMap.addPolyline(lineOptions);
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.setTrafficEnabled(true);

//        mMap.getUiSettings().setScrollGesturesEnabled(false);
//        mMap.getUiSettings().setMyLocationButtonEnabled(false);


        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, Home.this);
        }
        mMap.setTrafficEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0), 18.0f));
        mMap.setMyLocationEnabled(false);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        if (!isSourceSet) {
            try {
                mMap.animateCamera(CameraUpdateFactory.zoomTo(18));

                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<android.location.Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                String cityName = addresses.get(0).getAddressLine(0);
                String stateName = addresses.get(0).getAddressLine(1);
                source_location.setText(cityName + ", " + stateName);

                if (source_location_marker != null) {
                    source_location_marker.remove();
                }

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Source");
                source_location_marker = mMap.addMarker(markerOptions);

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(latLng)      // Sets the center of the map to Mountain View
                        .zoom(mMap.getCameraPosition().zoom)                   // Sets the zoom
                        .bearing(location.getBearing())                // Sets the orientation of the camera to east
                        .tilt(90)                   // Sets the tilt of the camera to 30 degrees
                        .build();                   // Creates a CameraPosition from the builder
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                //  setNearbyCabsOnMap(latLng);

                isSourceSet = true;
            } catch (Exception e) {
//                Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        }

        if (tripStarted) {

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng)      // Sets the center of the map to Mountain View
                    .zoom(mMap.getCameraPosition().zoom)                   // Sets the zoom
                    .bearing(location.getBearing())                // Sets the orientation of the camera to east
                    .tilt(90)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }

    }

 /*   @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }*/


    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }
}
