package com.example.flubbesdriver;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double latitude, longitude;
    Switch switchOne;
    TextView tonline,tOffline;
    private Context conx;
    MarkerOptions marker;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
         switchOne = (Switch) findViewById(R.id.switch1);
         tonline=(TextView)findViewById(R.id.idOnline);
        tOffline=(TextView)findViewById(R.id.idOffline);
        conx = this;
        switchOne.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {

                            tOffline.setTextColor(Color.parseColor("#FFFFFF"));
                            tonline.setTextColor(Color.parseColor("#9B9696"));
                          //  Location mLastLocation;

                            mMap.addMarker(marker);

                           // dialog.dismiss();
                        } else {
                            tonline.setTextColor(Color.parseColor("#FFFFFF"));
                            tOffline.setTextColor(Color.parseColor("#9B9696"));
                            mMap.clear();
                            showdialogg();
                        }
                    }
                });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation1);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void showdialogg() {

        dialog = new Dialog(MapsActivity.this);
        dialog.setContentView(R.layout.rest);
        dialog.setCancelable(true);



        //msg.setText(jMessage);
       // msg.setText("Hey Customer Enjoy Your RIDES WITH Flubbes!.... Earn Money through your reference... Have a Fabulous Journey");

        //Picasso.get().load("http://flubbes.com/Apis/images/" + jImgURL).into(img);
       /* Picasso.with(conx).load("http://flubbes.com/Apis/images/refer.png").into(img);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });*/
        dialog.show();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_cabs:



                    return true;
                case R.id.navigation_trends:
showduty();
                    return true;
                case R.id.navigation_food:

                    return true;
                case R.id.navigation_groccery:

                    return true;

            }
            return false;
        }
    };

    private void showduty() {
        dialog = new Dialog(MapsActivity.this);
        dialog.setContentView(R.layout.dutysetting);
        dialog.setCancelable(false);
        Button submit = (Button)dialog.findViewById(R.id.but);
        dialog.setCancelable(true);

submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        dialog.dismiss();
    }
});

        dialog.show();
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

        // Add a marker in Sydney and move the camera
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        //------------
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);

        LocationManager lm = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);
        Location l = null;

        for (int i = 0; i < providers.size(); i++) {
            l = lm.getLastKnownLocation(providers.get(i));
            if (l != null) {
                latitude = l.getLatitude();
                longitude = l.getLongitude();
                //strAdd = getCompleteAddressString(latitude, longitude);
                //   tvAddress.setText("Complete Address : " + strAdd);
                break;
            }
        }

        if (mMap != null) {
            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.cabmarker);
           marker = new MarkerOptions().position(
                    new LatLng(latitude, longitude)) .title("Current Location")
                   .snippet("Thinking of finding some thing...")
                   .icon(icon);

          /*  marker.icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_RED));*/

// Moving Camera to a Location with animation
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(latitude, longitude)).zoom(12)
                    .build();


            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));


        }
        }
}
