package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MainActivityForChild extends FragmentActivity implements OnMapReadyCallback {
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    private Button [][]buttons=new Button[3][3];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_details);


        LocationRequest mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(60000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationCallback mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        currentLocation= location;
                        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                        mapFragment.getMapAsync(MainActivityForChild.this);
                        Toast.makeText(getApplicationContext(),location.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();
    }


    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
           // return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location !=null)
                {
                    currentLocation= location;
                    Toast.makeText(getApplicationContext(),currentLocation.getLatitude()
                            +" "+currentLocation.getLongitude(),Toast.LENGTH_SHORT).show();

                    Toast.makeText(getApplicationContext(),"Inside Fetch",Toast.LENGTH_SHORT).show();
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(MainActivityForChild.this);

                }
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(getApplicationContext(),"Inside OnMapReady",Toast.LENGTH_SHORT).show();
       LatLng latLng = new LatLng (currentLocation.getLatitude(),currentLocation.getLongitude());
        MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("i am here.");

        googleMap.addMarker(markerOptions);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED)
                {
                    fetchLastLocation();
                }
                break;
        }

    }


}





