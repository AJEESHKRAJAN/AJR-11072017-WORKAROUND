package com.example.a454203.aone_sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by 454203 on 1/12/2018.
 */

public class AirplaneModeReceiver extends BroadcastReceiver {

    public String mLabel = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean state = intent.getBooleanExtra("state", false);

        Log.d("AppModel", String.format("Airplane Mode Changed, Label=%s  state=%b", mLabel, state));
    }

    public void setLabel(String value){
        mLabel = value;
    }
}
