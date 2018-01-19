package com.example.a454203.aone_sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.Locale;

public class BroadcastReceiverBatteryStatusActivity extends Activity {
    BatteryStatusBroadcastReceiver mBatteryStatusBroadcastReceiver;
    BatteryStatusNestedActivityClass mBatteryStatusNestedActivityClass;
    String LogName = "BTRY-STS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver_battery_status);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.battery_status_menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBatteryStatusNestedActivityClass = new BatteryStatusNestedActivityClass();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBatteryStatusNestedActivityClass, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBatteryStatusNestedActivityClass);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = true;
        switch (item.getItemId()) {
            case R.id.batteryStatusStart:
                handleBatteryStatusStartLogging(item);
                break;
            case R.id.batteryStatusStop:
                handleBatteryStatusStopLogging(item);
                break;
            default:
                handled = super.onOptionsItemSelected(item);
                break;
        }
        return handled;
    }

    private void handleBatteryStatusStartLogging(MenuItem item) {
        if (mBatteryStatusBroadcastReceiver == null) {
            LogHelper.LogThreadId(LogName, "A new battery status being monitored by a Broadcast Receiver.");
            mBatteryStatusBroadcastReceiver = new BatteryStatusBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            registerReceiver(mBatteryStatusBroadcastReceiver, intentFilter);
        }
    }

    private void handleBatteryStatusStopLogging(MenuItem item) {
        if (mBatteryStatusBroadcastReceiver != null) {
            LogHelper.LogThreadId(LogName, "Broadcast receiver for monitoring battery status has been stopped.");
            unregisterReceiver(mBatteryStatusBroadcastReceiver);
            mBatteryStatusBroadcastReceiver = null;
        }
    }

    private void setPowerDisplay(boolean onAC, int powerLevel) {
        int displayResource;
        if (powerLevel > 90) {
            displayResource = onAC ? R.drawable.battery_charging_100 :
                    R.drawable.battery_discharging_100;
        } else if (powerLevel > 65) {
            displayResource = onAC ? R.drawable.battery_charging_075 :
                    R.drawable.battery_discharging_075;
        } else if (powerLevel > 40) {
            displayResource = onAC ? R.drawable.battery_charging_050 :
                    R.drawable.battery_discharging_050;
        } else {
            displayResource = onAC ? R.drawable.battery_charging_025 :
                    R.drawable.battery_discharging_025;
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(displayResource);
    }

    public class BatteryStatusNestedActivityClass extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            LogHelper.LogThreadId(LogName, "Broadcast receiver - Called from nested class.");
            int batteryPlugged = intent.getIntExtra("plugged", -1);
            boolean onAC = batteryPlugged == BatteryManager.BATTERY_PLUGGED_AC;
            LogHelper.LogThreadId(LogName, "Broadcast receiver -- onAc " + onAC);
            int level = intent.getIntExtra("level", -1);
            int maxValue = intent.getIntExtra("scale", -1);
            int chargedPct = (level * 100) / maxValue;
            LogHelper.LogThreadId(LogName, String.format(Locale.US, "Level: %d, maxValue: %d, ChargedPct: %d ", level,maxValue,chargedPct));
            setPowerDisplay(onAC, chargedPct);
        }
    }
}
