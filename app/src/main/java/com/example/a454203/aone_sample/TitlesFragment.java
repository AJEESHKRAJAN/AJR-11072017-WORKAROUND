package com.example.a454203.aone_sample;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

/**
 * Created by HP on 24-07-2017.
 */

public class TitlesFragment extends ListFragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] titles = getResources().getStringArray(R.array.arrFragmentCourseTitle);

        ArrayAdapter<String> titileAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titles);


    }
}
