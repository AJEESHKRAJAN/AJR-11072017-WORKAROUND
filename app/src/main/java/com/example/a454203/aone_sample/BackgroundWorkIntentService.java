package com.example.a454203.aone_sample;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import java.io.FileOutputStream;


public class BackgroundWorkIntentService extends IntentService {
    FileHelper fileHelper;
    String logName;

    public BackgroundWorkIntentService()
    {
        super("BackgroundWorkIntentService");
        fileHelper = new FileHelper();
        logName = "BKG-INTNT--SVC-LOG";
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            LogHelper.LogThreadId(logName, "Logging - Intent Service is triggered");
            String fileName = intent.getStringExtra("filename");
            int MAX_WRITES = intent.getIntExtra("MAX_WRITES", 1);
            String writeText = intent.getStringExtra("writeText");

            FileOutputStream fileOutputStream = fileHelper.openOutStream(this, fileName);

            for (int i = 0; i < MAX_WRITES; i++) {
                fileHelper.slowWrite(fileOutputStream, writeText);
            }
            fileHelper.closeOutStream(fileOutputStream);
        }
    }


}
