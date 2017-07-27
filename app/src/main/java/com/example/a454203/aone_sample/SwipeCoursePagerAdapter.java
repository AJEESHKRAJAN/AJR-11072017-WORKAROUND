package com.example.a454203.aone_sample;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by HP on 27-07-2017.
 */

public class SwipeCoursePagerAdapter extends FragmentPagerAdapter {
    String[] mCourseTitles;
    String[] mCourseShortTitles;
    String[] mCourseDescription;


    public SwipeCoursePagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        Resources resources = context.getResources();
        mCourseTitles = resources.getStringArray(R.array.arrFragmentCourseTitle);
        mCourseShortTitles = resources.getStringArray(R.array.arrFragmentCourseTitlesShort);
        mCourseDescription = resources.getStringArray(R.array.arrFragmentCourseDesc);
    }

    @Override
    public Fragment getItem(int position) {
        SwipeNavigationCourseFragment swpCourseFrag = new SwipeNavigationCourseFragment();

       // return swpCourseFrag;
        return null;
    }

    @Override
    public int getCount() {
        return mCourseTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCourseShortTitles[position];
    }
}
