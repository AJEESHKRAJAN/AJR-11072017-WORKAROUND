package com.example.a454203.aone_sample;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by HP on 27-07-2017.
 */

public class SwipeNavigationCourseFragment extends Fragment {

    public static final String COURSE_TITLE = "Course Title";
    public static final String COURSE_DESCRIPTION = "Course Description";
    public static final String TOP_CARD = "Top Card";
    public static final String COURSE_TYPE_LOGO = "Course Type Logo";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View theView = inflater.inflate(R.layout.swipe_navigation_fragment_main_layout, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            String courseTitle = arguments.getString(COURSE_TITLE);
            String courseDesc = arguments.getString(COURSE_DESCRIPTION);
            int topCardResId = arguments.getInt(TOP_CARD);
            int courseTypeLogoResID = arguments.getInt(COURSE_TYPE_LOGO);

            displayValues(theView, courseTitle, courseDesc, topCardResId, courseTypeLogoResID);

        }
        return theView;

    }

    private void displayValues(View theView, String courseTitle,
                               String courseDesc, int topCardResId, int courseTypeLogoResID) {

        TextView courseTitleTextView = (TextView) theView.findViewById(R.id.txtSwpCourseTitle);
        TextView courseDescTextView = (TextView) theView.findViewById(R.id.txtSwpCourseDescription);
        ImageView topCardImageView = (ImageView) theView.findViewById(R.id.imgSwpTopCard);
        ImageView courseTypeLogoImageView = (ImageView) theView.findViewById(R.id.imgSwpCourseTypeLogo);

        courseTitleTextView.setText(courseTitle);
        courseDescTextView.setText(courseDesc);
        topCardImageView.setImageResource(topCardResId);
        courseTypeLogoImageView.setImageResource(courseTypeLogoResID);
    }
}
