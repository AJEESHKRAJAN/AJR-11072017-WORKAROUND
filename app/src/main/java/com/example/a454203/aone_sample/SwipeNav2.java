package com.example.a454203.aone_sample;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

public class SwipeNav2 extends AppCompatActivity implements ListView.OnItemClickListener {

    SwipeCoursePagerAdapter mSwipeCoursePagerAdapter;
    ViewPager mViewPager;

    NavigationDrawerSoleHelper mNavigationDrawerSoleHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_swipe_nav2);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // experiment with the ActionBar
       // ActionBar actionBar = getActionBar();
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mViewPager = (ViewPager) findViewById(R.id.container);
        //mViewPager.setAdapter(mSectionsPagerAdapter);
        mSwipeCoursePagerAdapter = new SwipeCoursePagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mSwipeCoursePagerAdapter);

        mNavigationDrawerSoleHelper = new NavigationDrawerSoleHelper();
        mNavigationDrawerSoleHelper.init(this, this , actionBar);


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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mSwipeCoursePagerAdapter.setCourseLib(position);
        mNavigationDrawerSoleHelper.handleSelect(position);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mNavigationDrawerSoleHelper.syncState();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mNavigationDrawerSoleHelper.handleOnPrepareOptionsMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mNavigationDrawerSoleHelper.handleOnOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        mNavigationDrawerSoleHelper.syncState();
        super.onConfigurationChanged(newConfig);
    }
}
