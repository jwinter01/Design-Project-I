package com.example.jswin.designproject1;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.*;

public class PalInformation {

    public static ArrayList<String> listFollowing = new ArrayList<>();

    public void startFetching(){
        listFollowing.clear(); // Don't fetch more times than needed
        getUserFollowing();
    }

    private void getUserFollowing() {

        DatabaseReference palsFollowingDb = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("following");
        palsFollowingDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()){
                    String uid = dataSnapshot.getRef().getKey();
                    if (uid != null && !listFollowing.contains(uid)){
                        listFollowing.add(uid); // Add this user
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String uid = dataSnapshot.getRef().getKey();
                    if (uid != null){
                        listFollowing.remove(uid); // Remove this user
                    }
                }

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
