package com.example.a454203.aone_sample;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by HP on 19-07-2017.
 */

public class PhotoHelper {
    private static final String LOG_TAG = "PHOTO HELPER";

    public static Uri generateTimeStampPhotoFileUri() {
        Uri photoFileUri;
        String timestamp;
        String photoFileName = "";

        File outputDir = getPhotoDirectory();

        if (outputDir != null) {
            timestamp = new SimpleDateFormat("yyyyMMDD_HHmmss", Locale.ENGLISH).format(new Date());
            photoFileName = "Img_" + timestamp + ".jpg";
        }

        File photoFile = new File(outputDir, photoFileName);

        photoFileUri = Uri.fromFile(photoFile);

        return photoFileUri;
    }

    public static File getPhotoDirectory() {
        File outputDir = null;

        String externalStorageState = Environment.getExternalStorageState();
        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
            File pictureDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

            outputDir = new File(pictureDir, "AVD_Cam_Pics");
            if (!outputDir.exists()) {
                if (!outputDir.mkdirs()) {
                    Log.e(LOG_TAG, "Failed to create directory: " + outputDir.getAbsolutePath());
                }
            }
        }
        return outputDir;
    }

    public static void addPhotoToMediaStoreAndDisplayThumbnail(String pathName, Activity activity, ImageView imageView) {
        final ImageView thumbnailImageView = imageView;
        final Activity thumbnailImageActivity = activity;

        String[] fileToScan = {pathName};

        MediaScannerConnection.scanFile(thumbnailImageActivity, fileToScan, null, (filePath, uri) -> {

            long id = ContentUris.parseId(uri);
            ContentResolver contentResolver = thumbnailImageActivity.getContentResolver();

            final Bitmap thumbnail = MediaStore.Images.Thumbnails.getThumbnail(contentResolver, id,
                    MediaStore.Images.Thumbnails.MINI_KIND, null);

            thumbnailImageActivity.runOnUiThread(() -> {
                thumbnailImageView.setImageBitmap(thumbnail);
            });
        });

    }
}
