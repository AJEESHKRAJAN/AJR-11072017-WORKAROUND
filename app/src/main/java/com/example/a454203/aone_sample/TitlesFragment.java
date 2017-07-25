package com.example.a454203.aone_sample;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by HP on 24-07-2017.
 */

public class TitlesFragment extends ListFragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] titles = getResources().getStringArray(R.array.arrFragmentCourseTitle);

        ArrayAdapter<String> titleAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titles);
        setListAdapter(titleAdapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        OnCourseSelectChangeListener listener = (OnCourseSelectChangeListener) getActivity();
        listener.OnCourseSelectChanged(position);
    }
}
