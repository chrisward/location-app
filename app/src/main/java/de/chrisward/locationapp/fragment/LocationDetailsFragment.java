package de.chrisward.locationapp.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.Locale;
import java.util.Objects;

import de.chrisward.locationapp.R;

public class LocationDetailsFragment extends Fragment implements OnMapReadyCallback, LocationListener {
    private MapView mapView;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private LocationManager locationManager;

    private TextView gpsAccuracyLabel;
    private TextView gpsAltitudeLabel;
    private TextView gpsLatitudeLabel;
    private TextView gpsLongitudeLabel;
    private TextView networkAccuracyLabel;
    private TextView networkAltitudeLabel;
    private TextView networkLatitudeLabel;
    private TextView networkLongitudeLabel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_location_details, container, false);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mapView = result.findViewById(R.id.map);
        mapView.onCreate(mapViewBundle);

        mapView.getMapAsync(this);


        locationManager = (LocationManager) Objects.requireNonNull(getActivity()).getSystemService(Context.LOCATION_SERVICE);

        gpsAccuracyLabel = result.findViewById(R.id.gpsAccuracy);
        gpsLongitudeLabel = result.findViewById(R.id.gpsLongitude);
        gpsLatitudeLabel = result.findViewById(R.id.gpsLatitude);
        gpsAltitudeLabel = result.findViewById(R.id.gpsAltitude);

        networkAccuracyLabel = result.findViewById(R.id.networkAccuracy);
        networkLongitudeLabel = result.findViewById(R.id.networkLongitude);
        networkLatitudeLabel = result.findViewById(R.id.networkLatitude);
        networkAltitudeLabel = result.findViewById(R.id.networkAltitude);

        return result;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (getContext() == null) {
            return;
        }

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

        googleMap.setMyLocationEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
            populateGpsValues(location);
        } else if (location.getProvider().equals(LocationManager.NETWORK_PROVIDER)) {
            populateNetworkValues(location);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String    s) {

    }

    private void populateNetworkValues(Location location) {
        if (location == null) {
            return;
        }

        networkAccuracyLabel.setText(
                String.format(Locale.getDefault(), "%f metres", location.getAccuracy())
        );

        networkAltitudeLabel.setText(
                String.format(Locale.getDefault(), "%f metres", location.getAltitude())
        );

        networkLatitudeLabel.setText(
                String.format(Locale.getDefault(), "%f", location.getLatitude())
        );

        networkLongitudeLabel.setText(
                String.format(Locale.getDefault(), "%f", location.getLongitude())
        );
    }

    private void populateGpsValues(Location location) {
        if (location == null) {
            return;
        }

        gpsAccuracyLabel.setText(
                String.format(Locale.getDefault(), "%f metres", location.getAccuracy())
        );

        gpsAltitudeLabel.setText(
                String.format(Locale.getDefault(), "%f metres", location.getAltitude())
        );

        gpsLatitudeLabel.setText(
                String.format(Locale.getDefault(), "%f", location.getLatitude())
        );

        gpsLongitudeLabel.setText(
                String.format(Locale.getDefault(), "%f", location.getLongitude())
        );
    }
}
