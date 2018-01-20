package com.example.a454203.aone_sample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.io.FileOutputStream;

public class BackgroundWorkServiceActivity extends Activity implements OnRequestPermissionsResultCallback {

    FileHelper fileHelper = new FileHelper();
    private static final int MAX_WRITES = 5;
    private static String fileName1 = "BkGd_Svc_1.dat";
    private static String writeTextA = "This is for Background Work Service \n";
    String backgroundWorkLogName = "BGW-WRK-SVC-Log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_work_service);
        LogHelper.LogThreadId(backgroundWorkLogName, "Logging - Background Work service Started");

        initializeListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initializeListeners() {
        findViewById(R.id.btnDoLongRunningWork).setOnClickListener(v -> btnDoLongRunningWorkOnClick((Button) v));
        findViewById(R.id.btnStartMonitoring).setOnClickListener(v -> btnStartMonitoringOnClick((Button) v));
        findViewById(R.id.btnStopMonitoring).setOnClickListener(v -> btnStopMonitoringOnClick((Button) v));
    }

    private void btnDoLongRunningWorkOnClick(Button button) {
        LogHelper.LogThreadId(backgroundWorkLogName, "Logging - Long running method Started");

//        Intent intent =  new Intent(this,BackgroundWorkService.class);
        Intent intent = new Intent(this, BackgroundWorkIntentService.class);
        intent.putExtra("filename", fileName1);
        intent.putExtra("MAX_WRITES", MAX_WRITES);
        intent.putExtra("writeText", writeTextA);

        startService(intent);
    }

    private void btnStartMonitoringOnClick(Button button) {
        LogHelper.LogThreadId(backgroundWorkLogName, "Logging - Background Work Monitoring service Initiated - Start");

        boolean isHavingPermission = checkLocationPermission();
        if (!isHavingPermission) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {

            Intent intent = new Intent(this, BackgroundWorkMonitoringService.class);
            intent.setAction(BackgroundWorkMonitoringService.START_ACTION);
            startService(intent);
        }

    }

    private void btnStopMonitoringOnClick(Button button) {
        LogHelper.LogThreadId(backgroundWorkLogName, "Logging - Background Work Monitoring service Initiated - Stop");

        Intent intent = new Intent(this, BackgroundWorkMonitoringService.class);
        intent.setAction(BackgroundWorkMonitoringService.STOP_ACTION);
        startService(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Cannot use Location services. Hence Monitoring doesn't work.", Toast.LENGTH_LONG).show();
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public boolean checkLocationPermission() {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = this.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }
}
