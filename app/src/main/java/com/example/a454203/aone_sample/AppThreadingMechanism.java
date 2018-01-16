package com.example.a454203.aone_sample;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

public class AppThreadingMechanism extends Activity {

    private static final int MAX_WRITES = 5;

    HandlerThread mHandlerThread;
    Handler mHandler;
    LocationListener mLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_threading_mechanism);
        setListeners();

        StrictMode.enableDefaults();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setListeners() {
        findViewById(R.id.btnWriteToFile).setOnClickListener(view -> btnWriteToFileOnClick((Button) view));
        findViewById(R.id.btnWriteToFileAsyncTask).setOnClickListener(view -> btnWriteToFileAsyncTaskOnClick((Button) view));
        findViewById(R.id.btnSendMessageToHandler).setOnClickListener(view -> btnSendMessageToHandlerOnClick((Button) view));
        findViewById(R.id.btnCallRunnableOnHandler).setOnClickListener(view -> btnCallRunnableOnHandlerOnClick((Button) view));
        findViewById(R.id.btnStartLocationMonitoring).setOnClickListener(view -> btnStartLocationMonitoringOnClick((Button) view));
        findViewById(R.id.btnStopLocationMonitoring).setOnClickListener(view -> btnStopLocationMonitoringOnClick((Button) view));

    }

    private void btnWriteToFileOnClick(Button view) {
        FileOutputStream fileOutputStream = openOutStream("testout.dat");

        for (int i = 0; i < MAX_WRITES; i++) {
            simpleWrite(fileOutputStream, "Hello. This is a test write");
        }
        closeOutStream(fileOutputStream);
    }

    @SuppressLint("StaticFieldLeak")
    private void btnWriteToFileAsyncTaskOnClick(Button view) {
        String writeText = "Async task simple demo - Illustration";

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBarMain);
        initializeProgressBar(progressBar);
        displayStartedMessage();

        new AsyncTask<String, Integer, Void>() {
            @Override
            protected Void doInBackground(String... strings) {
                String submittedText = strings[0];
                FileOutputStream fileOutputStream = openOutStream("testout.dat");

                for (int i = 0; i < MAX_WRITES; i++) {
                    slowWrite(fileOutputStream, submittedText);
                    publishProgress(i);
                }
                closeOutStream(fileOutputStream);
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                progressBar.setProgress(values[0]);
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                displayCompletionMessage();
                cleanProgressBar(progressBar);
            }
        }.execute(writeText);


    }

    private void btnSendMessageToHandlerOnClick(Button view) {
        Handler handler = getMyHandler();
        long threadId = Thread.currentThread().getId();
        String messageToSend = String.format(Locale.ENGLISH, "Message sent from the thread : %d", threadId);

        Message msg = handler.obtainMessage(0, messageToSend);
        msg.sendToTarget();
    }

    private void btnCallRunnableOnHandlerOnClick(Button view) {
        Handler handler = getMyHandler();
        long callingThreadId = Thread.currentThread().getId();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long runningThreadId = Thread.currentThread().getId();
                String messageToDisplay = String.format(Locale.US, "Runnable implementation running on thread %d - called from thread %d",
                        runningThreadId, callingThreadId);
                Log.d("AppThreadMech-RunHdl", messageToDisplay);
            }
        });
    }


    private void btnStartLocationMonitoringOnClick(Button view) {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mLocationListener = new LocationHelper();
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
        HandlerThread handlerThread = getHandlerThread();
        Looper looper = handlerThread.getLooper();

        if (locationManager != null) {
            //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0.0f, mLocationListener, looper);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
        }
    }

    private void btnStopLocationMonitoringOnClick(Button view) {
        stopLocationMonitoring();
    }

    protected FileOutputStream openOutStream(String fileName) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput("testout.dat", MODE_PRIVATE);
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.getStackTraceString(ex);
        }
        return fileOutputStream;
    }

    protected void closeOutStream(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.getStackTraceString(e);
            }
        }
    }

    private void simpleWrite(FileOutputStream fileOutputStream, String s) {
        try {
            fileOutputStream.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            Log.getStackTraceString(e);
        }
    }

    private void initializeProgressBar(ProgressBar progressBar) {
        progressBar.setMax(MAX_WRITES);
        progressBar.setProgress(0);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void displayStartedMessage() {
        Toast.makeText(this, "File writing operation Initiated via Async Task.", Toast.LENGTH_SHORT).show();
    }

    private void slowWrite(FileOutputStream fileOutputStream, String writeText) {

        try {
            fileOutputStream.write(writeText.getBytes());
            Thread.sleep(1500, 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void displayCompletionMessage() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AppThreadingMechanism.this);
        alertDialog.setTitle("Writing to File").setMessage("File operation complete")
                .setIcon(R.drawable.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }

    private void cleanProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private HandlerThread getHandlerThread() {
        if (mHandlerThread == null) {
            mHandlerThread = new HandlerThread("aoneSampleHandler");
            mHandlerThread.start();
        }
        return mHandlerThread;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mHandlerThread != null) {
            stopLocationMonitoring();
            mHandlerThread.quit();
            mHandlerThread = null;
        }
    }

    private void stopLocationMonitoring() {
        if (mLocationListener != null) {
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            if (locationManager != null) {
                locationManager.removeUpdates(mLocationListener);
            }
            mLocationListener = null;
        }
    }

    private Handler getMyHandler() {
        if (mHandler == null) {
            HandlerThread handlerThread = getHandlerThread();
            mHandler = new ThreadingHandler(handlerThread.getLooper());
        }
        return mHandler;
    }
}
