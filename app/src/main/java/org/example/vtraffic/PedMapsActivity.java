package org.example.vtraffic;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class PedMapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, LocationManagerInterface {

    private GoogleMap mMap;
    MarkerOptions myOptions;
    boolean mylocation = true;
    MarkerOptions myOptionsMyLocation;
    Marker myMarker, trfone;
    double oldlati = 0.0;
    double oldlongit = 0.0;
    SmartLocationManager mLocationManager;
    // private GeofencingClient mGeofencingClient;
    private Circle circle;
    boolean cameraState = false;
    private Location newLoc;

//SmartLocationManager mLocationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        // mLocationManager = new SmartLocationManager(getApplicationContext(), this, this, SmartLocationManager.ALL_PROVIDERS, LocationRequest.PRIORITY_HIGH_ACCURACY, 10 * 1000, 1 * 1000, SmartLocationManager.LOCATION_PROVIDER_RESTRICTION_NONE); // init location manager

        mLocationManager = new SmartLocationManager(getApplicationContext(), this, this, SmartLocationManager.ALL_PROVIDERS, LocationRequest.PRIORITY_HIGH_ACCURACY, 10 * 1000, 1 * 1000, SmartLocationManager.LOCATION_PROVIDER_RESTRICTION_NONE); // init location manager
        myOptions = new MarkerOptions();


        myOptionsMyLocation = new MarkerOptions();
        // Initializing

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //mMap.setOnMyLocationChangeListener();


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
        //*****************************
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setOnMarkerClickListener(PedMapsActivity.this);


        //setting color light for pedestrian crossing
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_action_pedlight);
        myOptions.icon(icon);
        myOptions.anchor(0.5f, 0.5f);
        //  myOptions.position(new LatLng(lati,longit));
        // myOptions.rotation(bearing);
        myOptions.flat(true);
        LatLng wellawattaSouthCrossing = new LatLng(6.875462, 79.860918);
        mMap.addMarker(myOptions.position(wellawattaSouthCrossing).title("Wellawatta Junction crossing "));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(wellawattaSouthCrossing, 15));
        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);











        /*BitmapDescriptor icon1 = BitmapDescriptorFactory.fromResource(R.drawable.ic_action_navigation);
        myOptionsMyLocation.icon(icon1);
        myOptionsMyLocation.anchor(0.5f, 0.5f);
        //  myOptions.position(new LatLng(lati,longit));
        // myOptions.rotation(bearing);
        myOptionsMyLocation.flat(true);
        // myOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        myMarker =  mMap.addMarker(myOptionsMyLocation);*/
        // adding circle to map


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(false);
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
// circle for required zone
        String title = marker.getTitle();
        circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(6.875462,79.860918))
                .radius(10)
                .strokeColor(Color.RED)
                .visible(true)

        );

        float[] distance = new float[2];

/*
Location.distanceBetween( mMarker.getPosition().latitude, mMarker.getPosition().longitude,
mCircle.getCenter().latitude, mCircle.getCenter().longitude, distance);
*/

        Location.distanceBetween(newLoc.getLatitude(), newLoc.getLongitude(),
                circle.getCenter().latitude, circle.getCenter().longitude, distance);

        if (distance[0] > circle.getRadius()) {
            Toast.makeText(getBaseContext(), "You are in Outside from the required Distance , distance from center: " + distance[0] + " meters "+" radius: " + circle.getRadius(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "You are in Inside of the zone , distance from center: " + distance[0] + " radius: " + circle.getRadius(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(PedMapsActivity.this, PedestrianRequestActivity.class);
            intent.putExtra("title", title);
            startActivityForResult(intent, 1);
        }
        return false;

    }


    public void locationFetched(Location mLocation, Location oldLocation, String time, String locationProvider) {
        // Toast.makeText(getApplication(), "Lat : " + mLocal.getLatitude() + " Lng : " + mLocal.getLongitude(), Toast.LENGTH_LONG).show();
        // Creating MarkerOptions
        double lati = mLocation.getLatitude();
        double longit = mLocation.getLongitude();


        if (myMarker != null) {
            myMarker.remove();
        }
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_action_pedestrain);
        Location prevLoc = oldLocation;
        if (prevLoc == null) {
            prevLoc = mLocation;
        }
        newLoc = mLocation;
        float bearing = prevLoc.bearingTo(newLoc);
        //IconGenerator iconFactory = new IconGenerator(this);
        myOptionsMyLocation.icon(icon);
        myOptionsMyLocation.anchor(0.5f, 0.5f);
        myOptionsMyLocation.position(new LatLng(lati, longit));
        myOptionsMyLocation.rotation(bearing);
        myOptionsMyLocation.flat(true);
        // myOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        myMarker = mMap.addMarker(myOptionsMyLocation);
        oldlati = lati;
        oldlongit = longit;

        if (cameraState) {

        } else {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati, longit), 15));
            // Zoom in, animating the camera.
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
            // Zoom out to zoom level 10, animating with a duration of 2 seconds.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        }
       /* mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati,longit),15));
        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

         */

        cameraState = true;
        mylocation = false;


        if (mylocation) {

        } else {


        }

        //float[] results = new float[1];
        //  Location.distanceBetween(6.875455, 79.860958, lati, longit, results);
        // float distanceInMeters = results[0];
        // boolean isWithin300m = distanceInMeters < 300;


    }

    protected void onStart() {
        super.onStart();
        mLocationManager.startLocationFetching();
    }

    protected void onStop() {
        super.onStop();
        mLocationManager.abortLocationFetching();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationManager.pauseLocationFetching();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocationManager.startLocationFetching();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            // setUI();
            circle.remove();

            setResult(RESULT_OK, null);
        }
    }
}

