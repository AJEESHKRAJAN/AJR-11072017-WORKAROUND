package com.example.a454203.aone_sample;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    public final static String COURSE_HAS_REFERENCES_LIST = "course has references list";


    private final static int COURSE_ACTION_NOT_SET = -1;
    private final static boolean COURSE_HAS_REFERENCES_LIST_NOT_SET = false;

    String mCourseTitle;
    String mCourseDescription;
    int mTopCardResourceId;
    int mCourseTypeLogoResourceId;
    boolean mCourseHasRefUrls;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(mCourseHasRefUrls)
            inflater.inflate(R.menu.course_refs, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        boolean handled = true;
        int courseActionResourceId = COURSE_ACTION_NOT_SET;

        switch (item.getItemId()) {
            case R.id.action_view:
                courseActionResourceId = R.string.title_action_view;
                break;
            case R.id.action_contents:
                courseActionResourceId = R.string.title_action_contents;
                break;
            case R.id.action_description:
                courseActionResourceId = R.string.title_action_description;
                break;
            case R.id.action_assessment:
                courseActionResourceId = R.string.title_action_assessment;
                break;
            case R.id.action_exercises:
                courseActionResourceId = R.string.title_action_exercises;
                break;
            case R.id.action_refs:
                courseActionResourceId = R.string.title_action_refs;
                break;
            default:
                handled = super.onOptionsItemSelected(item);
        }

//        if (courseActionResourceId != COURSE_ACTION_NOT_SET)
//            showActionActivity(courseActionResourceId);

        return handled;
    }

//    private void showActionActivity(int courseActionResourceId) {
//        Intent intent = new Intent(getActivity(), SwipeCourseActionsActivityClass.class);
//        intent.putExtra(SwipeCourseActionsActivityClass.COURSE_ACTION, getString(courseActionResourceId));
//        intent.putExtra(SwipeCourseActionsActivityClass.COURSE_TITLE, mCourseTitle);
//        intent.putExtra(SwipeCourseActionsActivityClass.TOP_CARD, mTopCardResourceId);
//
//        startActivity(intent);
//    }
}
