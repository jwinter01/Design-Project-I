package com.example.jswin.designproject1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Not sure why it says this class is never used
// This is the function that will be called in the switch case in
// the main activitiy
public class ChatFrag extends Fragment {

    public static ChatFrag newInstance(){
        ChatFrag fragment = new ChatFrag();
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
