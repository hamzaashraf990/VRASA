package com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.VehicleRepairing_Module;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MechanicMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double lat, longitude;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String location = "";
    String customer_id="";

    double mLatitude,mLongitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        location = getIntent().getStringExtra("location");
        customer_id = getIntent().getStringExtra("customer_id");


        String[] result = location.split(",");
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
        }

        mLatitude = Double.valueOf(result[0]);
        mLongitude = Double.valueOf(result[1]);
    }


    @Override
    protected void onResume() {
        super.onResume();
        final LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                lat = location.getLatitude();
                longitude = location.getLongitude();

                firebaseDatabase = FirebaseDatabase.getInstance();

                databaseReference = firebaseDatabase.getReference("VRASA_Database").child("CustomerRequests").child(customer_id);
                databaseReference.child("PendingRequests").child("Latitude").setValue(lat).toString();
                databaseReference.child("PendingRequests").child("Longtitude").setValue(longitude).toString();
                databaseReference.child("PendingRequests").child("Status").setValue("In Progress");



                LatLng latLng = new LatLng(mLatitude, mLongitude);

                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f));

                Polyline line = mMap.addPolyline(new PolylineOptions()
                        .add(new LatLng(lat, longitude), new LatLng(mLatitude, mLongitude))
                        .width(5)
                        .color(Color.BLUE));



                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseReference = firebaseDatabase.getReference("VRASA_Database").child("Users").child(userID);
                databaseReference.child("Locations").setValue(latLng).toString();


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);


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
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED
                ){
            return;
        }
        mMap.setMyLocationEnabled(true);
    }
}
