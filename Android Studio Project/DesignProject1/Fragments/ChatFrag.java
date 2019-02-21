package com.example.jswin.designproject1.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jswin.designproject1.R;
import com.example.jswin.designproject1.RecyclerViewStory.StoryPalsObject;
import com.example.jswin.designproject1.RecyclerViewStory.StoryRCAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatFrag extends Fragment {


    private RecyclerView myRecyclerPals;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutmanager;

    public static ChatFrag newInstance(){
        ChatFrag fragment = new ChatFrag();
        return fragment;
    }

    @Override
    // This was auto generated from Android Studio (onCreateView then press enter)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // View find view by id
        View view = inflater.inflate(R.layout.frag_chat, container, false);

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

    // Cant copy this from Story must be custom
    private void listenForData(){
        DatabaseReference receivedDb = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("received");
        receivedDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        getUserInfo(snapshot.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getUserInfo(String chatUid) {
        DatabaseReference chatUserDb = FirebaseDatabase.getInstance().getReference().child("users").child(chatUid);
        chatUserDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String email = dataSnapshot.child("email").getValue().toString();
                    String uid = dataSnapshot.getRef().getKey();

                    StoryPalsObject obj = new StoryPalsObject(email, uid, "chat");
                    if(!results.contains(obj)){
                        results.add(obj);
                        myAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}



