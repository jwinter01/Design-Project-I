package com.example.jswin.designproject1.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jswin.designproject1.PalInformation;
import com.example.jswin.designproject1.R;
import com.example.jswin.designproject1.RecyclerViewStory.StoryPalsObject;
import com.example.jswin.designproject1.RecyclerViewStory.StoryRCAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StoryFrag extends Fragment {

    private RecyclerView myRecyclerPals;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutmanager;

    public static StoryFrag newInstance(){
        StoryFrag fragment = new StoryFrag();
        return fragment;
    }

    @Override
    // This was auto generated from Android Studio (onCreateView then press enter)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // View find view by id
        View view = inflater.inflate(R.layout.frag_story, container, false);

        // Sets up the Recycler View of Pals
        myRecyclerPals = view.findViewById(R.id.recyclerPals);
        myRecyclerPals.setNestedScrollingEnabled(false);
        myRecyclerPals.setHasFixedSize(false);
        myLayoutmanager = new LinearLayoutManager(getContext());
        myAdapter = new StoryRCAdapter(getDataSet(), getContext());
        myRecyclerPals.setAdapter(myAdapter);

        myRecyclerPals.setLayoutManager(myLayoutmanager);

        Button myRefresh = view.findViewById(R.id.refresh);
        myRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDb();
                listenForData();
            }
        });

        return view; // Return the view
    }

    // Clears the query
    private void clearDb() {
        int size = this.results.size();
        this.results.clear();
        myAdapter.notifyItemRangeChanged(0, size);

    }

    // Results from pals from database
    private ArrayList<StoryPalsObject> results = new ArrayList<>();
    private ArrayList<StoryPalsObject> getDataSet() {
        listenForData();
        return results;
    }

    private void listenForData() {
        for(int i = 0; i < PalInformation.listFollowing.size(); i++){
            DatabaseReference followingStoryDb = FirebaseDatabase.getInstance().getReference().child("users").child(PalInformation.listFollowing.get(i));
            followingStoryDb.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String email = dataSnapshot.child("email").getValue().toString();
                    String uid = dataSnapshot.getRef().getKey();
                    long timestampBeg = 0;
                    long timestampEnd = 0;
                    for(DataSnapshot storySnapshot : dataSnapshot.child("story").getChildren()){
                        if(storySnapshot.child("timestampBeg").getValue() != null){
                            timestampBeg = Long.parseLong(storySnapshot.child("timestampBeg").getValue().toString());
                        }
                        if(storySnapshot.child("timestampEnd").getValue() != null){
                            timestampEnd = Long.parseLong(storySnapshot.child("timestampEnd").getValue().toString());
                        }
                        long timestampCurrent = System.currentTimeMillis();
                        if(timestampCurrent >= timestampBeg && timestampCurrent <= timestampEnd){
                            // Tells the system that this is coming from a given email, user id, and STORY
                            StoryPalsObject obj = new StoryPalsObject(email, uid, "story");
                            if(!results.contains(obj)){
                                results.add(obj);
                                myAdapter.notifyDataSetChanged();
                            }
                        }

                    }
                }

                // Leave it even though not being used at the moment
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}