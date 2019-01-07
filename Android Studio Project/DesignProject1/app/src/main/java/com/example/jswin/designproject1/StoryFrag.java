package com.example.jswin.designproject1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StoryFrag extends Fragment {

    public static StoryFrag newInstance(){
        StoryFrag fragment = new StoryFrag();
        return fragment;
    }

    @Override
    // This was auto generated from Android Studio (onCreateView then press enter)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // View find view by id
        View view = inflater.inflate(R.layout.frag_story, container, false);
        return view; // Return the view
    }
}