package com.example.a454203.aone_sample;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.DatePicker;

import java.io.FileOutputStream;

public class BackgroundWorkServiceActivity extends Activity {

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
        Intent intent =  new Intent(this,BackgroundWorkIntentService.class);
        intent.putExtra("filename",fileName1);
        intent.putExtra("MAX_WRITES",MAX_WRITES);
        intent.putExtra("writeText",writeTextA);

        startService(intent);
    }

    private void btnStartMonitoringOnClick(Button button) {
        LogHelper.LogThreadId(backgroundWorkLogName, "Logging - Background Work Monitoring service Initiated - Start");

        Intent intent = new Intent(this,BackgroundWorkMonitoringService.class);
        intent.setAction(BackgroundWorkMonitoringService.START_ACTION);
        startService(intent);

    }

    private void btnStopMonitoringOnClick(Button button) {
        LogHelper.LogThreadId(backgroundWorkLogName, "Logging - Background Work Monitoring service Initiated - Stop");

        Intent intent = new Intent(this,BackgroundWorkMonitoringService.class);
        intent.setAction(BackgroundWorkMonitoringService.STOP_ACTION);
        startService(intent);
    }
}
