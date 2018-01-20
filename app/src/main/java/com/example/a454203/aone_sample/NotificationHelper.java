package com.example.a454203.aone_sample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;

import java.util.Locale;

import static java.lang.String.format;

/**
 * Created by ajesh on 20-01-2018.
 */

class NotificationHelper {

    private static String logName = "NTFC-LOC-HLPR";

    private static int LOCATION_NOTIFICATION_ID = 1;


    static void displayNotification(Context context, Location location) {

        String mapUri = String.format(Locale.US, "https://maps.google.com/maps?q=%.6f,%.6f",
                location.getLatitude(),location.getLongitude());

        Uri uri = Uri.parse(mapUri);

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,mapIntent,0);

        String message = formatNotification(location.getProvider(), location.getLatitude(), location.getLongitude());

        Notification.Builder notificationBuilder = new Notification.Builder(context);
        notificationBuilder.setContentTitle("Current Location")
                .setContentText(message)
                .setSmallIcon(R.drawable.smile_self)
                .setContentIntent(pendingIntent);

        Notification notification = notificationBuilder.getNotification();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            notificationManager.notify(LOCATION_NOTIFICATION_ID,notification);
        }
    }

    static void removeNotification(Context context){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(LOCATION_NOTIFICATION_ID);
    }

    private static String formatNotification(String provider, double lattitude, double longitude) {
        String locValue = String.format(Locale.US, "%.6f/%.6f Provider :%s", lattitude, longitude, provider);
        LogHelper.LogThreadId(logName, locValue);
        return locValue;
    }
}
