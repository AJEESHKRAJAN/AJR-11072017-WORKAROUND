package com.example.a454203.aone_sample;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Created by 454203 on 1/16/2018.
 */

public class ThreadingHandler extends Handler {
    public ThreadingHandler(Looper looper) {
        super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
        String messageText = (String) msg.obj;
        long threadId = Thread.currentThread().getId();
        Log.d("AppThreadMech-Handl", String.format("MyHandler[running on thread %d] - received:%s", threadId, messageText));
    }
}
