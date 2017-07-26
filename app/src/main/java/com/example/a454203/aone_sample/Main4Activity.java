package com.example.a454203.aone_sample;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {

    private static final int PROVIDE_INFO_REQUEST_CODE = 1000;
    private static final int TAKE_PICTURE_REQUEST_CODE = 1010;
    Uri mPhotoPathUri;
    String mPhotoPathName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        setUpViews();
    }

    private void setUpViews() {
        Button provideInfo = (Button) findViewById(R.id.btnProvInfo);
        provideInfo.setOnClickListener(this);

        Button pictureButton = (Button) findViewById(R.id.btnPicture);
        pictureButton.setOnClickListener(this);

        Button sendButton = (Button) findViewById(R.id.btnSend);
        sendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;

        int id = button.getId();

        switch (id) {
            case R.id.btnProvInfo:
                handleMoreInformationButton(button);
                break;
            case R.id.btnPicture:
                handleTakePictureButton(button);
                break;
            case R.id.btnSend:
                handlePostBackInfoButton(button);
                break;
            default:
                break;
        }

    }

    private void handlePostBackInfoButton(Button button) {
//        Intent intent = new Intent(this, ProvideInformationActivity.class);
//        startActivityForResult(intent, PROVIDE_INFO_REQUEST_CODE);
    }

    private void handleTakePictureButton(Button button) {
        mPhotoPathUri = PhotoHelper.generateTimeStampPhotoFileUri();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoPathUri);
        startActivityForResult(intent, TAKE_PICTURE_REQUEST_CODE);

    }

    private void handleMoreInformationButton(Button button) {
        Intent intent = new Intent(this, ProvideInformationActivity.class);
        startActivityForResult(intent, PROVIDE_INFO_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        switch (requestCode) {
            case PROVIDE_INFO_REQUEST_CODE:
                handleProvideInfoResult(resultCode, resultIntent);
                break;
            case TAKE_PICTURE_REQUEST_CODE:
                handleTakePictureResult(resultCode, resultIntent);
                break;
        }
    }

    private void handleTakePictureResult(int resultCode, Intent resultIntent) {
        if (resultCode == RESULT_OK) {
            ImageView imgView = (ImageView) findViewById(R.id.imgShowImage);
            mPhotoPathName = mPhotoPathUri.getPath();
            PhotoHelper.addPhotoToMediaStoreAndDisplayThumbnail(mPhotoPathName, this, imgView);
        } else {
            mPhotoPathUri = null;
            Toast.makeText(this, "User cancelled", Toast.LENGTH_LONG).show();
        }
    }

    private void handleProvideInfoResult(int resultCode, Intent resultIntent) {
        if (resultCode == RESULT_OK) {
            String className;
            String personName;
            String personEmail;

            className = resultIntent.getStringExtra(ProvideInformationActivity.CLASS_NAME_EXTRA);
            personName = resultIntent.getStringExtra(ProvideInformationActivity.PERSON_NAME_EXTRA);
            personEmail = resultIntent.getStringExtra(ProvideInformationActivity.PERSON_EMAIL_EXTRA);

            TextView textClassNameView = (TextView) findViewById(R.id.txtClassName);
            TextView textPersonNameView = (TextView) findViewById(R.id.txtPersonName);
            TextView textPersonEmailView = (TextView) findViewById(R.id.txtEmail);

            textClassNameView.setText(className);
            textPersonNameView.setText(personName);
            textPersonEmailView.setText(personEmail);
        } else {
            Toast.makeText(this, "User cancelled", Toast.LENGTH_LONG).show();
        }
    }
}
