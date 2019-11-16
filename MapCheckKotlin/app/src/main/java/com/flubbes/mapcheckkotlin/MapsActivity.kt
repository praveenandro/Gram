package com.flubbes.mapcheckkotlin

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.location.Location
import android.os.AsyncTask
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.ui.PlacePicker

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener,
    LocationListener {


    private lateinit var mMap: GoogleMap
    internal lateinit var source_location: EditText
    internal lateinit var destination_location: EditText
    internal var TAGG = "LocationSelect"
    internal var AUTOCOMPLETE_SOURCE = 1
    internal var AUTOCOMPLETE_DESTINATITON = 2

    internal var mGoogleApiClient: GoogleApiClient? = null
    internal lateinit var mLastLocation: Location
    internal var source_location_marker: Marker? = null
    internal var destination_location_marker: Marker? = null
    internal var nearby_cab: Marker? = null
    internal lateinit var mLocationRequest: LocationRequest


    internal lateinit var mapFragment: SupportMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        source_location = findViewById(R.id.source_location) as EditText
        destination_location = findViewById(R.id.destination_location) as EditText


        // source_location.setOnFocusChangeListener { v, hasFocus ->  }

        source_location.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
            if (b) {
                try {
                    /*Intent intent =
                               new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(activity);
                        startActivityForResult(intent, AUTOCOMPLETE_SOURCE);*/
                    val builder = PlacePicker.IntentBuilder()
                    startActivityForResult(builder.build(this), AUTOCOMPLETE_SOURCE)
                } catch (e: GooglePlayServicesRepairableException) {
                    // TODO: Handle the error.
                    Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_LONG).show()
                } catch (e: GooglePlayServicesNotAvailableException) {
                    // TODO: Handle the error.
                    Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_LONG).show()

                }

            }
        }


        source_location.setOnClickListener {
            try {

                val builder = PlacePicker.IntentBuilder()
                startActivityForResult(builder.build(this), AUTOCOMPLETE_SOURCE)
            } catch (e: GooglePlayServicesRepairableException) {
                // TODO: Handle the error.
                Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_LONG).show()
            } catch (e: GooglePlayServicesNotAvailableException) {
                // TODO: Handle the error.
                Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_LONG).show()

            }
        }


        destination_location.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
            //                Toast.makeText(getApplicationContext(), "Destination", Toast.LENGTH_SHORT).show();
            if (b) {
                try {
                    //                        Intent intent =
                    //                                new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(activity);
                    //                        startActivityForResult(intent, AUTOCOMPLETE_DESTINATITON);
                    //lCabSelection.setVisibility(View.VISIBLE);
                    val builder = PlacePicker.IntentBuilder()
                    startActivityForResult(builder.build(this), AUTOCOMPLETE_DESTINATITON)
                } catch (e: GooglePlayServicesRepairableException) {
                    // TODO: Handle the error.
                    //                        Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                } catch (e: GooglePlayServicesNotAvailableException) {
                    // TODO: Handle the error.
                    //                        Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();

                }

            }
        }


        destination_location.setOnClickListener {
            try {
                //                    Intent intent =
                //                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(activity);
                //                    startActivityForResult(intent, AUTOCOMPLETE_DESTINATITON);
                val builder = PlacePicker.IntentBuilder()
                startActivityForResult(builder.build(this), AUTOCOMPLETE_DESTINATITON)
            } catch (e: GooglePlayServicesRepairableException) {
                // TODO: Handle the error.
                //                    Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
            } catch (e: GooglePlayServicesNotAvailableException) {
                // TODO: Handle the error.
                //                    Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();

            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_SOURCE) {
            if (resultCode == Activity.RESULT_OK) {
                val place = PlacePicker.getPlace(this, data!!)
                Log.i("log", "Place: " + place.name)
                source_location.setText(place.name)

                if (source_location_marker != null) {
                    source_location_marker!!.remove()
                }

                val latLng = place.latLng
                val markerOptions = MarkerOptions()
                markerOptions.position(latLng)
                markerOptions.title("Source")
                //                BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.car);
                //                Bitmap b = bitmapDrawable.getBitmap();
                //                Bitmap smallCar = Bitmap.createScaledBitmap(b, 150, 81, false);

                //                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallCar));
                //                markerOptions.rotation(location.getBearing());
                source_location_marker = mMap.addMarker(markerOptions)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))


                //--> setNearbyCabsOnMap(latLng);


            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(applicationContext,"else source",Toast.LENGTH_SHORT).show();
            }//            else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
            //                Status status = PlaceAutocomplete.getStatus(this, data);
            //                // TODO: Handle the error.
            //                Log.i(TAG, status.getStatusMessage());
            //
            //            }
        } else if (requestCode == AUTOCOMPLETE_DESTINATITON) {
            if (resultCode == Activity.RESULT_OK) {
                //                Place place = PlaceAutocomplete.getPlace(this, data);
                val place = PlacePicker.getPlace(this, data!!)
                Log.i("log", "Place: " + place.name)
                destination_location.setText(place.name)

                if (destination_location_marker != null) {
                    destination_location_marker!!.remove()
                }

                val latLng = place.latLng
                val markerOptions = MarkerOptions()
                markerOptions.position(latLng)
                markerOptions.title("Destination")
                //                BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.car);
                //                Bitmap b = bitmapDrawable.getBitmap();
                //                Bitmap smallCar = Bitmap.createScaledBitmap(b, 150, 81, false);

                //                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallCar));
                //                markerOptions.rotation(location.getBearing());
                destination_location_marker = mMap.addMarker(markerOptions)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))

                val url =
                    getDirectionsUrl(
                        source_location_marker!!.getPosition(),
                        destination_location_marker!!.getPosition()
                    )
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

            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(applicationContext,"else source",Toast.LENGTH_SHORT).show();
            }//            else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
            //                Status status = PlaceAutocomplete.getStatus(getApplicationContext(), data);
            //                // TODO: Handle the error.
            //                Log.i(TAG, status.getStatusMessage());
            //
            //            }
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

    private fun getDirectionsUrl(origin: LatLng, dest: LatLng): String {
        val API = "AIzaSyDE9SFlVD8m7dztCGGAiy0JlnTFJGlBaUY"

        // Origin of route
        val str_origin = "origin=" + origin.latitude + "," + origin.longitude

        // Destination of route
        val str_dest = "destination=" + dest.latitude + "," + dest.longitude

        // Sensor enabled
        val sensor = "sensor=false"

        // Building the parameters to the web service
        val parameters = "$str_origin&$str_dest&$sensor"

        // Output format
        val output = "json"
        Toast.makeText(applicationContext, parameters, Toast.LENGTH_LONG).show()
        // Building the url to the web service
        /*Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        startActivity(i);*/
        return "https://maps.googleapis.com/maps/api/directions/$output?$parameters&key=$API"
    }

    /**
     * A method to download json data from url
     */
    @Throws(IOException::class)
    private fun downloadUrl(strUrl: String): String {
        var data = ""
        var iStream: InputStream? = null
        var urlConnection: HttpURLConnection? = null
        try {
            val url = URL(strUrl)

            // Creating an http connection to communicate with url
            urlConnection = url.openConnection() as HttpURLConnection

            // Connecting to url
            urlConnection.connect()

            // Reading data from url
            iStream = urlConnection.inputStream

            val br = BufferedReader(InputStreamReader(iStream!!))

            val sb = StringBuffer()

            var line = ""
         /*   while ((line = br.readLine()) != null) {

            }*/
            sb.append(line)
            data = sb.toString()

            // Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();

            br.close()

        } catch (e: Exception) {
            //            Log.d("Exception while downloading url", e.toString());
        } finally {
            iStream!!.close()
            urlConnection!!.disconnect()
        }
        return data
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {

    }

    // Fetches data from url passed
    private inner class DownloadTask : AsyncTask<String, Void, String>() {

        // Downloading data in non-ui thread
        override fun doInBackground(vararg url: String): String {

            // For storing data from web service
            var data = ""

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0])

                //  Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
            } catch (e: Exception) {
                Log.d("Background Task", e.toString())
            }

            return data
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        override fun onPostExecute(result: String) {
            super.onPostExecute(result)

            val parserTask = ParserTask()

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result)
        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    private inner class ParserTask : AsyncTask<String, Int, List<List<HashMap<String, String>>>>() {

        // Parsing the data in non-ui thread
        override fun doInBackground(vararg jsonData: String): List<List<HashMap<String, String>>>? {

            val jObject: JSONObject
            var routes: List<List<HashMap<String, String>>>? = null

            try {
                jObject = JSONObject(jsonData[0])
                val parser = DirectionsJSONParser()

                // Starts parsing data
                routes = parser.parse(jObject)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return routes
        }

        // Executes in UI thread, after the parsing process
        override fun onPostExecute(result: List<List<HashMap<String, String>>>) {
            var points: ArrayList<LatLng>? = null
            var lineOptions: PolylineOptions? = null
            val markerOptions = MarkerOptions()
            var distance = ""
            var duration = ""
            //Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();
            if (result.size < 1) {
                Toast.makeText(applicationContext, "No Points", Toast.LENGTH_SHORT).show()
                return
            }

            // Traversing through all the routes
            for (i in result.indices) {
                points = ArrayList()
                lineOptions = PolylineOptions()

                // Fetching i-th route
                val path = result[i]

                // Fetching all the points in i-th route
                for (j in path.indices) {
                    val point = path[j]


                    if (j == 0) {    // Get distance from the list
                        distance = point["distance"] as String
                        // Toast.makeText(getApplicationContext(), ""+distance, Toast.LENGTH_SHORT).show();
                        continue
                    } else if (j == 1) { // Get duration from the list
                        duration = point["duration"] as String
                          Toast.makeText(getApplicationContext(), ""+duration, Toast.LENGTH_SHORT).show();
                        continue
                    }

                    val lat = java.lang.Double.parseDouble(point["lat"])
                    val lng = java.lang.Double.parseDouble(point["lng"])
                    val position = LatLng(lat, lng)

                    points.add(position)
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points)
                lineOptions.width(5f)
                lineOptions.color(Color.BLUE)

                /*    PolylineOptions rectOptions = new PolylineOptions()
                        .addAll(points)
                        .width(5)
                        .color(Color.RED);*/


            }
            mMap.addPolyline(lineOptions)
            // tRideduration.setText("$duration mins")
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


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mMap.uiSettings.isRotateGesturesEnabled = false
        mMap.isTrafficEnabled = true

        //        mMap.getUiSettings().setScrollGesturesEnabled(false);
        //        mMap.getUiSettings().setMyLocationButtonEnabled(false);


        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                buildGoogleApiClient()
                mMap.isMyLocationEnabled = true
            }
        } else {
            buildGoogleApiClient()
            mMap.isMyLocationEnabled = true
        }
    }

    @Synchronized
    protected fun buildGoogleApiClient() {
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()
        mGoogleApiClient!!.connect()
    }

    override fun onConnected(bundle: Bundle?) {

        mLocationRequest = LocationRequest()
        mLocationRequest.interval = 1000
        mLocationRequest.fastestInterval = 1000
        mLocationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this@MapsActivity)
        }
        mMap.isTrafficEnabled = true
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18f))
        //        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0), 18.0f));
        mMap.isMyLocationEnabled = false

    }

    override fun onConnectionSuspended(i: Int) {

    }

    override fun onLocationChanged(location: Location) {

        mLastLocation = location

        val latLng = LatLng(location.latitude, location.longitude)

/*        if (!isSourceSet) {
            try {
                mMap.animateCamera(CameraUpdateFactory.zoomTo(18f))

                val geocoder = Geocoder(this, Locale.getDefault())
                val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                val cityName = addresses[0].getAddressLine(0)
                val stateName = addresses[0].getAddressLine(1)
                source_location.setText("$cityName, $stateName")

                if (source_location_marker != null) {
                    source_location_marker!!.remove()
                }

                val markerOptions = MarkerOptions()
                markerOptions.position(latLng)
                markerOptions.title("Source")
                source_location_marker = mMap.addMarker(markerOptions)

                val cameraPosition = CameraPosition.Builder()
                    .target(latLng)      // Sets the center of the map to Mountain View
                    .zoom(mMap.cameraPosition.zoom)                   // Sets the zoom
                    .bearing(location.bearing)                // Sets the orientation of the camera to east
                    .tilt(90f)                   // Sets the tilt of the camera to 30 degrees
                    .build()                   // Creates a CameraPosition from the builder
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

                //  setNearbyCabsOnMap(latLng);

                isSourceSet = true
            } catch (e: Exception) {
                //                Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        }

        if (tripStarted) {

            val cameraPosition = CameraPosition.Builder()
                .target(latLng)      // Sets the center of the map to Mountain View
                .zoom(mMap.cameraPosition.zoom)                   // Sets the zoom
                .bearing(location.bearing)                // Sets the orientation of the camera to east
                .tilt(90f)                   // Sets the tilt of the camera to 30 degrees
                .build()                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        }

    }*/

        /*   @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }*/


        val MY_PERMISSIONS_REQUEST_LOCATION = 99

        fun checkLocationPermission(): Boolean {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                // Asking user if explanation is needed
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                ) {

                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                    //Prompt the user once explanation has been shown
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_LOCATION
                    )


                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_LOCATION
                    )
                }
                return false
            } else {
                return true
            }
        }

        fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>, grantResults: IntArray
        ) {
            when (requestCode) {
                MY_PERMISSIONS_REQUEST_LOCATION -> {
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        // permission was granted. Do the
                        // contacts-related task you need to do.
                        if (ContextCompat.checkSelfPermission(
                                this,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {

                            if (mGoogleApiClient == null) {
                                buildGoogleApiClient()
                            }
                            mMap.isMyLocationEnabled = true
                        }

                    } else {

                        // Permission denied, Disable the functionality that depends on this permission.
                        Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show()
                    }
                    return
                }
            }// other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }
}
