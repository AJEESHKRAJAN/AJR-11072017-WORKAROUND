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

    FileOutputStream openOutStream(Context context, String fileName) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(fileName, MODE_PRIVATE);
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.getStackTraceString(ex);
        }
        return fileOutputStream;
    }

    void closeOutStream(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
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
            fileOutputStream.write(writeText.getBytes());
            Thread.sleep(1500, 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
