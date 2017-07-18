package com.example.a454203.aone_sample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {

    static final int PROVIDE_INFO_REQUEST_CODE = 1000;
    static final int TAKE_PICTURE_REQUEST_CODE = 1010;
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
        provideInfo.setOnClickListener((view) -> handleMoreInformationButton((Button) view));

        Button pictureButton = (Button) findViewById(R.id.btnPicture);
        provideInfo.setOnClickListener((view) -> handleTakePictureButton((Button) view));

        Button sendButton = (Button) findViewById(R.id.btnSend);
        sendButton.setOnClickListener((view) -> handlePostbackInfoButton((Button) view));
    }

    private void handlePostbackInfoButton(Button view) {
//        Intent intent = new Intent(this, ProvideInformationActivity.class);
//        startActivityForResult(intent, PROVIDE_INFO_REQUEST_CODE);
    }

    private void handleTakePictureButton(Button view) {
    }

    private void handleMoreInformationButton(Button view) {
      /*  Intent intent = new Intent(this, ProvideInformationActivity.class);
        startActivityForResult(intent, PROVIDE_INFO_REQUEST_CODE);*/
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        switch (resultCode) {
            case PROVIDE_INFO_REQUEST_CODE:
                handleProvideInfoResult(resultCode, resultIntent);
                break;
        }
    }

    private void handleProvideInfoResult(int resultCode, Intent resultIntent) {
        if (resultCode == RESULT_OK) {
            String className = "";
            String personNsme = "";
            String personEmail = "";

             /*className = resultIntent.getStringExtra(ProvideInformationActivity.CLASS_NAME_EXTRA);
             personNsme = resultIntent.getStringExtra(ProvideInformationActivity.PERSON_NAME_EXTRA);
             personEmail = resultIntent.getStringExtra(ProvideInformationActivity.PERSON_EMAIL_EXTRA);*/

            TextView textClassNameView = (TextView) findViewById(R.id.txtClassName);
            TextView textPersonNameView = (TextView) findViewById(R.id.txtPersonName);
            TextView textPersonEmailView = (TextView) findViewById(R.id.txtEmail);

            textClassNameView.setText(className);
            textPersonNameView.setText(personNsme);
            textPersonEmailView.setText(personEmail);
        } else {
            Toast.makeText(this,"User cancelled",Toast.LENGTH_LONG).show();
        }
    }
}
