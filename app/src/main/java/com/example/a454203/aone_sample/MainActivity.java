package com.example.a454203.aone_sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        boolean handled;
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        switch (id) {
            case R.id.action_other:
                onClickMenuOther(item);
                handled = true;
                break;
            case R.id.actionExit:
                onClickMenuExit(item);
                handled = true;
                break;
            case R.id.action_activity2:
                onClickActivity2(item);
                handled = true;
                break;
            case R.id.action_activity3:
                onClickActivity3(item);
                handled = true;
                break;
            case R.id.action_activity4:
                onClickActivity4(item);
                handled = true;
                break;
            case R.id.actionFragment:
                onClickActivityFragment(item);
                handled = true;
                break;

            default:
                handled = super.onOptionsItemSelected(item);
        }

        return handled;
    }

    private void onClickMenuOther(MenuItem item) {

        Toast.makeText(this, "Clicked on Other", Toast.LENGTH_LONG).show();
    }


    private void onClickActivity2(MenuItem item) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    private void onClickActivity3(MenuItem item) {
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }

    private void onClickActivity4(MenuItem item) {
        Intent intent = new Intent(this, Main4Activity.class);
        startActivity(intent);
    }

    private void onClickActivityFragment(MenuItem item) {
        Intent intent = new Intent(this, FragmentActivity.class);
        startActivity(intent);
    }

    private void onClickMenuExit(MenuItem item) {
        finish();
    }
}
