package com.example.a454203.aone_sample;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.Locale;

public class BackgroundWorkMonitoringService extends Service {
    FileHelper fileHelper;
    HandlerThread mHandlerThread;
    String logName;
    LocationListener mLocationListener = null;

    public static String START_ACTION = "com.example.a454203.aone_sample.START_ACTION";
    public static String STOP_ACTION = "com.example.a454203.aone_sample.STOP_ACTION";

    public BackgroundWorkMonitoringService() {
        fileHelper = new FileHelper();
        logName = "BKG--MONTRG-SVC-LOG";
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        LogHelper.LogThreadId(logName, "Logging - On Create  - Service");

        mHandlerThread = new HandlerThread("BackgroundWorkServiceHandler");
        mHandlerThread.start();
    }

    @Override
    public void onDestroy() {
        LogHelper.LogThreadId(logName, "Logging - Service Handlers Destroyed");
        mHandlerThread.quit();
        mHandlerThread = null;
        NotificationHelper.removeNotification(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();

        if (START_ACTION.equalsIgnoreCase(action)) {
            StartMonitoring();
        } else if (STOP_ACTION.equalsIgnoreCase(action)) {
            StopMonitoring();
            stopSelf();
        }

        return START_REDELIVER_INTENT;
    }


    @SuppressLint("MissingPermission")
    public void StartMonitoring() {
        if (mLocationListener == null) {
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            //PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
            mLocationListener = new LocationHelper(this);



//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
//
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
            //locationManager.
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
                    mLocationListener, mHandlerThread.getLooper());
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
                    mLocationListener, mHandlerThread.getLooper());
        }

    }


    public void StopMonitoring() {
        if (mLocationListener != null) {
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            if (locationManager != null) {
                locationManager.removeUpdates(mLocationListener);
                mLocationListener = null;
            }
        }
    }

}


