package com.example.a454203.aone_sample;

import android.content.Context;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ajesh on 18-01-2018.
 */

class FileHelper {
    private String logName = "FileHelper - Log";

    FileOutputStream openOutStream(Context context, String fileName) {
        FileOutputStream fileOutputStream = null;
        try {
            LogHelper.LogThreadId(logName, "File - Open - Initiated");
            fileOutputStream = context.openFileOutput(fileName, MODE_PRIVATE);
        } catch (Exception ex) {
            LogHelper.LogThreadId(logName, "File - Open - Exception");
            ex.printStackTrace();
            Log.getStackTraceString(ex);
        }
        return fileOutputStream;
    }

    void closeOutStream(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                LogHelper.LogThreadId(logName, "File - Close - Initiated");
                fileOutputStream.close();
            } catch (IOException e) {
                LogHelper.LogThreadId(logName, "File - close - Exception");
                e.printStackTrace();
                Log.getStackTraceString(e);
            }
        }
    }

    void simpleWrite(FileOutputStream fileOutputStream, String s) {
        try {
            fileOutputStream.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            Log.getStackTraceString(e);
        }
    }


    void slowWrite(FileOutputStream fileOutputStream, String writeText) {

        try {
            LogHelper.LogThreadId(logName, "File - Slow Write - Started");
            fileOutputStream.write(writeText.getBytes());
            Thread.sleep(1500, 0);
        } catch (IOException e) {
            LogHelper.LogThreadId(logName, "File - Slow Write - Exception");
            e.printStackTrace();
        } catch (InterruptedException e) {
            LogHelper.LogThreadId(logName, "File - Slow Write - Exception");
            e.printStackTrace();
        }
    }

}
