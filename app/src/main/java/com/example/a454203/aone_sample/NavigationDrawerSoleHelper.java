package com.example.a454203.aone_sample;

//import android.app.ActionBar;
//import android.app.Activity;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by 454203 on 1/9/2018.
 */

public class NavigationDrawerSoleHelper extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    ListView mDrawerListView;
    Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;

    public void init(Activity theActivity, ListView.OnItemClickListener listener , android.support.v7.app.ActionBar theActionBar) {
        mDrawerLayout = (DrawerLayout) theActivity.findViewById(R.id.swipe_drawer_layout);
        mDrawerListView = (ListView) theActivity.findViewById(R.id.left_drawer);

        String[] navigationDrawerOptions =
                theActivity.getResources().getStringArray(R.array.navigation_drawer_options);
        ArrayAdapter<String> navigationDrawerAdapter =
                new ArrayAdapter<String>(theActivity, R.layout.drawer_option_item_layout, navigationDrawerOptions);
        mDrawerListView.setAdapter(navigationDrawerAdapter);
        mDrawerListView.setOnItemClickListener(listener);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        mDrawerListView.setItemChecked(0, true);

        mToolbar = (Toolbar) theActivity.findViewById(R.id.toolbar_for_drawer);

        setupActionBar(theActivity, mToolbar ,  theActionBar);

    }

    private void setupActionBar(Activity theActivity, Toolbar theToolbar,android.support.v7.app.ActionBar theActionBar) {
        final Activity activity = theActivity;

        android.support.v7.app.ActionBar actionBar = theActionBar;
//        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mDrawerToggle = new ActionBarDrawerToggle(theActivity, mDrawerLayout, theToolbar, R.string.open_drawer_message, R.string.close_drawer_message)
        {
            @Override
            public void onDrawerClosed(View drawerView) {
                activity.invalidateOptionsMenu();
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                activity.invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }
        };

    }

    public void handleSelect(int option) {
        mDrawerListView.setItemChecked(option, true);
        mDrawerLayout.closeDrawer(mDrawerListView);
    }

    public void handleOnPrepareOptionsMenu(Menu menu) {
        boolean itemVisible = !mDrawerLayout.isDrawerOpen(mDrawerListView);

        for (int index = 0; index < menu.size(); index++) {
            MenuItem item = menu.getItem(index);
            item.setEnabled(itemVisible);
        }
    }

    public void handleOnOptionsItemSelected(MenuItem item) {
        mDrawerToggle.onOptionsItemSelected(item);
    }

    public void syncState() {
        mDrawerToggle.syncState();
    }

    public void setSelection(int option) {
        mDrawerListView.setItemChecked(option, true);
    }
}
