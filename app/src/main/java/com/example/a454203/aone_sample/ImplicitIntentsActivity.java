package com.example.a454203.aone_sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

import java.net.URI;

public class ImplicitIntentsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intents);
        setOnClickListeners();
    }

    public void setOnClickListeners() {
        findViewById(R.id.makeCallButton).setOnClickListener(view -> setUpMakeCall());
        findViewById(R.id.showWebPage).setOnClickListener(view -> setUpShowWebPage());
        findViewById(R.id.browseCourse).setOnClickListener(view -> setUpBrowseCourse());
    }

    public void setUpMakeCall() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:9790793380"));
        startActivity(intent);
    }

    public void setUpShowWebPage() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://pluralsight.com"));
        startActivity(intent);
    }

    public void setUpBrowseCourse() {
//        Intent intent = new Intent("com.example.coursebrowse.action.BROWSE_COURSE");
//        intent.putExtra("course_lib",2);
//        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
