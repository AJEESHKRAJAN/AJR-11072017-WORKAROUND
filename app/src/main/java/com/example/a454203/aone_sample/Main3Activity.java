package com.example.a454203.aone_sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity3_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = false;
        int id = item.getItemId();


        switch (id) {
            case R.id.action_showToast:
                onShowToast(item);
                handled = true;
                break;
            case R.id.action_activity2Close:
                onCloseActivity2(item);
                handled = true;
                break;
            default:
                handled = super.onOptionsItemSelected(item);
        }

        return handled;
    }


    public void onShowToast(MenuItem item) {
        Toast.makeText(this, "This is a toast on Activity 3", Toast.LENGTH_SHORT).show();

    }

    public void onCloseActivity2(MenuItem item) {
        finish();
    }
}
