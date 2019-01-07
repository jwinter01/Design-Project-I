package com.example.jswin.designproject1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// add extends Fragment hit enter to auto import above
public class CameraFrag extends Fragment {

    public static CameraFrag newInstance(){
        CameraFrag fragment = new CameraFrag();
        return fragment;
    }

    @Override
    // This was auto generated from Android Studio (onCreateView then press enter)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // View find view by id
        View view = inflater.inflate(R.layout.frag_chat, container, false);
        return view; // Return the view
    }
}


