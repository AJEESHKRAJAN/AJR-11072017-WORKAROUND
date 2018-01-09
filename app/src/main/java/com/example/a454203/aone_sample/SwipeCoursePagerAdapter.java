package com.example.a454203.aone_sample;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Toast;

/**
 * Created by HP on 27-07-2017.
 */

public class SwipeCoursePagerAdapter extends FragmentStatePagerAdapter {
    public static final int COURSE_LIB_ANDROID = 0;
    public static final int COURSE_LIB_IOS = 1;
    public static final int COURSE_LIB_WINDOWSPHONE = 2;
    String[] mCourseTitles;
    String[] mCourseShortTitles;
    String[] mCourseDescription;
    int mCourseLogoResourceId;

    Context mContext;

    public SwipeCoursePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        Resources resources = mContext.getResources();
        mCourseTitles = resources.getStringArray(R.array.arrFragmentCourseTitle);
        mCourseShortTitles = resources.getStringArray(R.array.arrFragmentCourseTitlesShort);
        mCourseDescription = resources.getStringArray(R.array.arrFragmentCourseDesc);
    }

    public void setCourseLib(int courseLib) {
        boolean isValid = true;
        Resources resources = mContext.getResources();

        switch (courseLib) {
            case COURSE_LIB_ANDROID:
                mCourseTitles = resources.getStringArray(R.array.android_course_titles);
                mCourseShortTitles = resources.getStringArray(R.array.android_course_titles_short);
                mCourseDescription = resources.getStringArray(R.array.android_course_descriptions);
                mCourseLogoResourceId = R.drawable.ps_android_logo;
                break;
            case COURSE_LIB_IOS:
                mCourseTitles = resources.getStringArray(R.array.ios_course_titles);
                mCourseShortTitles = resources.getStringArray(R.array.ios_course_titles_short);
                mCourseDescription = resources.getStringArray(R.array.ios_course_descriptions);
                mCourseLogoResourceId = R.drawable.ps_ios_logo;
                break;
            case COURSE_LIB_WINDOWSPHONE:
                mCourseTitles = resources.getStringArray(R.array.windows_phone_course_titles);
                mCourseShortTitles = resources.getStringArray(R.array.windows_phone_course_titles_short);
                mCourseDescription = resources.getStringArray(R.array.windows_phone_course_descriptions);
                mCourseLogoResourceId = R.drawable.ps_windows_logo;
                break;
            default:
                Toast.makeText(mContext, "Invalid library name", Toast.LENGTH_LONG).show();
                isValid = false;
                break;
        }

        if(isValid)
            notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundleArguments = new Bundle();
        bundleArguments.putString(SwipeNavigationCourseFragment.COURSE_TITLE, mCourseTitles[position]);
        bundleArguments.putString(SwipeNavigationCourseFragment.COURSE_DESCRIPTION, mCourseDescription[position]);
        bundleArguments.putInt(SwipeNavigationCourseFragment.TOP_CARD, translateTopCardIndex(position));
        bundleArguments.putInt(SwipeNavigationCourseFragment.COURSE_TYPE_LOGO, R.drawable.ps_android_logo);

//         Use page index to simulate some courses have references and some not
        boolean hasRefs = position % 2 == 0;
        bundleArguments.putBoolean(SwipeNavigationCourseFragment.COURSE_HAS_REFERENCES_LIST, hasRefs);

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
