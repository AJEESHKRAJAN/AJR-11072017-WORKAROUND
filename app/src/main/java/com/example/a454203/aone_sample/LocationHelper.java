package com.example.a454203.aone_sample;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by 454203 on 1/16/2018.
 */

public class LocationHelper implements LocationListener {

    private long threadId = Thread.currentThread().getId();
    @Override
    public void onLocationChanged(Location location) {

        Log.d("AppThreadingMech-LocHlp", String.format("Provider :%s [running on thread %d] - Location: %.6f/%.6f",
                location.getProvider(), threadId, location.getLatitude(), location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        String statusMessage = "unknown";
        switch (status) {
            case LocationProvider.OUT_OF_SERVICE:
                statusMessage = "Out of service";
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                statusMessage = "Temporarily unavailable";
                break;
            case LocationProvider.AVAILABLE:
                statusMessage = "Available";
                break;
        }
        Log.d("AppThreadingMech-LocHlp", String.format("Provider:%s Status changed [running on thread %d] - new status:%s", provider, threadId, statusMessage));
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("AppThreadingMech-LocHlp", String.format("Provider:%s [running on thread %d] ENABLED", provider, threadId));
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("AppThreadingMech-LocHlp", String.format("Provider:%s [running on thread %d] DISABLED", provider, threadId));
    }
}
