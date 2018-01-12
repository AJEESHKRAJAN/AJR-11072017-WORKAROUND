package com.example.a454203.aone_sample;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class AppModelBasicOther extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_model_basic_other);

        InitializeListeners();
    }

    public void InitializeListeners() {
        findViewById(R.id.btnMonitorAirplaneModeActivity).setOnClickListener(view -> processAirplaneActivityMode((Button) view));
        findViewById(R.id.btnMonitorAirplaneModeApp).setOnClickListener(view -> processAirplaneAppMode((Button) view));
        findViewById(R.id.btnStopMonitorAirplaneModeApp).setOnClickListener(view -> processAirplaneStopAppMode((Button) view));
    }


    private void processAirplaneActivityMode(Button button) {
        AirplaneModeReceiver receiver = new AirplaneModeReceiver();
        receiver.setLabel("Activity");

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(receiver, intentFilter);

        Log.d("appmodel", "Registered to monitor Airplane Mode - Activity Context");
    }

    private void processAirplaneAppMode(Button view) {
        AirplaneModeReceiver receiver = new AirplaneModeReceiver();
        receiver.setLabel("App");

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        Context theContext = getApplicationContext();

        theContext.registerReceiver(receiver, intentFilter);
        Log.d("appmodel", "Registered to monitor Airplane Mode - App Context");
    }

    private void processAirplaneStopAppMode(Button view) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
