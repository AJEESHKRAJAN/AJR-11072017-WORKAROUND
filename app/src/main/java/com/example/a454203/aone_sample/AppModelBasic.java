package com.example.a454203.aone_sample;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;

public class AppModelBasic extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_model_basic);

        findViewById(R.id.btnLaunchAppBasicOther).setOnClickListener(view -> showOtherActivityOnClick((Button) view));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void showOtherActivityOnClick(Button button) {
        Intent intent =  new Intent(this,AppModelBasicOther.class);
        startActivity(intent);
    }
}
