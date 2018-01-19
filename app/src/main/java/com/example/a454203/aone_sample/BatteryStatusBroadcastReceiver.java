package com.example.a454203.aone_sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BatteryStatusBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        BatteryStatus batteryStatus = new BatteryStatus(intent);
        LogHelper.LogThreadId("BRD-RCR-BTRY-STS", batteryStatus.toString());
    }
}
