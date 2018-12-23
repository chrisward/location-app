package de.chrisward.locationapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import de.chrisward.locationapp.fragment.LocationDetailsFragment;
import de.chrisward.locationapp.fragment.RequestPermissionFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (hasLocationPermission()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content,
                            new LocationDetailsFragment())
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content,
                            new RequestPermissionFragment())
                    .commit();
        }
    }

    private boolean hasLocationPermission() {
        return (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        );
    }


}
