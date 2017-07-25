package com.example.a454203.aone_sample;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by HP on 23-07-2017.
 */

public class DescriptionFragment extends Fragment {

    private String[] mCourseDescription;
    private TextView mCourseDescriptionTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCourseDescription = getResources().getStringArray(R.array.arrFragmentCourseDesc);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);

        mCourseDescriptionTextView = (TextView) view.findViewById(R.id.txtShowCourseDesc);

        return view;
    }

    public void SetCourse(int courseIndex) {
        mCourseDescriptionTextView.setText(mCourseDescription[courseIndex]);
    }
}
