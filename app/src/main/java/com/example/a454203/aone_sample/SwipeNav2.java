package com.example.a454203.aone_sample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class SwipeNav2 extends AppCompatActivity implements ListView.OnItemClickListener{

    SwipeCoursePagerAdapter mSwipeCoursePagerAdapter;
    ViewPager mViewPager;

    NavigationDrawerSoleHelper mNavigationDrawerSoleHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_nav2);
//        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);
//
//        if (mToolbar != null) {
//            setSupportActionBar(mToolbar);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
        mViewPager = (ViewPager) findViewById(R.id.container);
        //mViewPager.setAdapter(mSectionsPagerAdapter);
        mSwipeCoursePagerAdapter = new SwipeCoursePagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mSwipeCoursePagerAdapter);

        mNavigationDrawerSoleHelper =  new NavigationDrawerSoleHelper();
        mNavigationDrawerSoleHelper.init(this,this);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_swipe_navigation_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mSwipeCoursePagerAdapter.setCourseLib(position);
        mNavigationDrawerSoleHelper.handleSelect(position);
    }
}
