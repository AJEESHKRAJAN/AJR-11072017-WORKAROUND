package com.example.a454203.aone_sample;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

public class FragmentActivity extends Activity implements OnCourseSelectChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_layout);
    }

    @Override
    public void OnCourseSelectChanged(int courseIndex) {
        FragmentManager fragMgr = getFragmentManager();
        DescriptionFragment descFrag = (DescriptionFragment) fragMgr.findFragmentById(R.id.descriptionFragment);
        descFrag.SetCourse(courseIndex);
    }
}
