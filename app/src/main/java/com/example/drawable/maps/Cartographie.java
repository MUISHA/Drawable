package com.example.drawable.maps;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.drawable.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Cartographie extends AppCompatActivity implements LocationListener {
    LocationManager locationManager;
    private static final int REQUEST_CODE = 1234;
    SupportMapFragment mapFragment;
    GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cartographie);
        mapFragment = (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map);
    }
    @SuppressLint("MissingPermission")
    private void LodingMapCarton(){
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
               Cartographie.this.mMap = googleMap;
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                mMap.moveCamera(CameraUpdateFactory.zoomBy(10));
                mMap.setMyLocationEnabled(true);
                mMap.addMarker(new MarkerOptions().position(new LatLng(1.5497,30.2517))
                .title("EMERGENCY SOS"));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        ChekingPermission();
    }
    private void ChekingPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION

                    }, REQUEST_CODE);
            return;
        }
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
        }
        if (locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER))
        {
            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,10000,0,this);
        }
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,10000,0,this);
        }
        LodingMapCarton();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE)
        {
            ChekingPermission();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (locationManager != null)
        {
            locationManager.removeUpdates(this);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
        double lat = location.getLatitude();
        double longi = location.getLongitude();
        if (mMap != null)
        {
            LatLng getlocation = new LatLng(lat , longi);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(getlocation));
        }
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }
    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}