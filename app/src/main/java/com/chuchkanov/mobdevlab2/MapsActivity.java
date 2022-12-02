package com.chuchkanov.mobdevlab2;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.chuchkanov.mobdevlab2.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private static final int REQUEST_CODE_ACCESS_COARSE_LOCATION=1;
    private static boolean ACCESS_COARSE_LOCATION_GRANTED =false;
    private static final int REQUEST_CODE_ACCESS_FINE_LOCATION=1;
    private static boolean ACCESS_FINE_LOCATION_GRANTED =false;

    String address="Київ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bndl = getIntent().getExtras();
        address=bndl.getString("name");
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        int hasACCESS_COARSE_LOCATIONPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if(hasACCESS_COARSE_LOCATIONPermission == PackageManager.PERMISSION_GRANTED){
            ACCESS_COARSE_LOCATION_GRANTED = true;
        }
        else{
            // вызываем диалоговое окно для установки разрешений
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_ACCESS_COARSE_LOCATION);
        }


        int hasACCESS_FINE_LOCATIONPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if(hasACCESS_FINE_LOCATIONPermission == PackageManager.PERMISSION_GRANTED){
            ACCESS_FINE_LOCATION_GRANTED = true;
        }
        else{
            // вызываем диалоговое окно для установки разрешений
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_ACCESS_FINE_LOCATION);
        }
        if (ACCESS_COARSE_LOCATION_GRANTED&&ACCESS_FINE_LOCATION_GRANTED){

            mapFragment.getMapAsync(this);
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;





        LatLng myPosition=new LatLng(-34,151);;
        Geocoder geocoder = new Geocoder(this);
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocationName(address, 1);
            if(addresses.size() > 0) {
                double latitude= addresses.get(0).getLatitude();
                double longitude= addresses.get(0).getLongitude();
                myPosition=new LatLng(latitude,longitude);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                addresses = geocoder.getFromLocationName("Київ", 1);
                if(addresses.size() > 0) {
                    double latitude= addresses.get(0).getLatitude();
                    double longitude= addresses.get(0).getLongitude();
                    myPosition=new LatLng(latitude,longitude);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(myPosition).title(address));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition,12));




    }

}