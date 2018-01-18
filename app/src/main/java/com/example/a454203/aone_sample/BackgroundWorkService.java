package com.example.a454203.aone_sample;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;

import java.io.FileOutputStream;
import java.util.Locale;

public class BackgroundWorkService extends Service {
    FileHelper fileHelper;
    Handler mHandler;
    HandlerThread mHandlerThread;
    String logName;

    public BackgroundWorkService() {
        fileHelper = new FileHelper();
        logName = "BKG-SVC-LOG";
    }

    @Override
    public void onCreate() {
        LogHelper.LogThreadId(logName, "Logging - On Create  - Service");

        mHandlerThread = new HandlerThread("BackgroundWorkServiceHandler");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                LogHelper.LogThreadId(logName, "Logging - Handle Message  - Passing to doWork() method");
                Intent intent = (Intent) msg.obj;
                int startId = msg.arg1;
                doWork(intent);
                boolean isStopped = stopSelfResult(startId);
                LogHelper.LogThreadId(logName, String.format(Locale.US,"Stop Self Result for Start id: %d returned %b",
                        startId, isStopped));
            }
        };
    }

    @Override
    public void onDestroy() {
        LogHelper.LogThreadId(logName, "Logging - Service Handlers Destroyed");
        mHandlerThread.quit();
        mHandlerThread = null;
        mHandler = null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogHelper.LogThreadId(logName, "Logging - From background service");

        Message message = mHandler.obtainMessage();
        message.obj = intent;
        message.arg1 = startId;
        message.sendToTarget();
        return 0;
    }

    private void doWork(Intent intent) {
        LogHelper.LogThreadId(logName, "Logging - Method inside Service - doWork()");

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
