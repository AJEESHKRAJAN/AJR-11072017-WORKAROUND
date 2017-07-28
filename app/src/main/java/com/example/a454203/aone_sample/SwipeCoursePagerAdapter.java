package com.example.a454203.aone_sample;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
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
        Bundle bundleArguments = new Bundle();
        bundleArguments.putString(SwipeNavigationCourseFragment.COURSE_TITLE, mCourseTitles[position]);
        bundleArguments.putString(SwipeNavigationCourseFragment.COURSE_DESCRIPTION, mCourseDescription[position]);
        bundleArguments.putInt(SwipeNavigationCourseFragment.TOP_CARD, translateTopCardIndex(position));
        bundleArguments.putInt(SwipeNavigationCourseFragment.COURSE_TYPE_LOGO, R.drawable.ps_android_logo);

        SwipeNavigationCourseFragment swpCourseFrag = new SwipeNavigationCourseFragment();
        swpCourseFrag.setArguments(bundleArguments);
        return swpCourseFrag;

    }

    private int translateTopCardIndex(int position) {
        int resourceId = 0;
        switch (position) {
            case 0:
                resourceId = R.drawable.ps_top_card_01;
                break;
            case 1:
                resourceId = R.drawable.ps_top_card_02;
                break;
            case 2:
                resourceId = R.drawable.ps_top_card_03;
                break;
            case 3:
                resourceId = R.drawable.ps_top_card_04;
                break;
            case 4:
                resourceId = R.drawable.ps_top_card_05;
                break;
            case 5:
                resourceId = R.drawable.ps_top_card_06;
                break;
            case 6:
                resourceId = R.drawable.ps_top_card_07;
                break;
            default:
                break;
        }
        return resourceId;
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
