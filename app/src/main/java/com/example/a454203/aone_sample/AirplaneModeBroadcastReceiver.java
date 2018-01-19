package com.example.a454203.aone_sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AirplaneModeBroadcastReceiver extends BroadcastReceiver {
    String LogName = "BRDCST-RCVR-AIRPLN";

    @Override
    public void onReceive(Context context, Intent intent) {
        LogHelper.LogThreadId(LogName, "Broadcast Receiver for Airplane mode is notified.");

        String action = intent.getAction();
        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equalsIgnoreCase(action)) {
            boolean isAirplaneMode = intent.getBooleanExtra("state", false);

            String serviceAction = isAirplaneMode ? BackgroundWorkMonitoringService.STOP_ACTION
                    : BackgroundWorkMonitoringService.START_ACTION;

            LogHelper.LogThreadId(LogName, "Service action name is :" + serviceAction);

            Intent serviceIntent = new Intent(context, BackgroundWorkMonitoringService.class);
            serviceIntent.setAction(serviceAction);
            context.startService(serviceIntent);
        }
    }
}
