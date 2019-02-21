package com.example.jswin.designproject1;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jswin.designproject1.RecyclerViewFollow.FollowPalsObject;
import com.example.jswin.designproject1.RecyclerViewFollow.FollowRCAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class FindPalsActivity extends AppCompatActivity {

    private RecyclerView myRecyclerPals;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutmanager;

    EditText myInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pals);

        myInput = findViewById(R.id.palEmailInput);
        Button mySearchPals = findViewById(R.id.searchPals);

        // Sets up the Recycler View of Pals
        myRecyclerPals = findViewById(R.id.recyclerPals);
        myRecyclerPals.setNestedScrollingEnabled(false);
        myRecyclerPals.setHasFixedSize(false);
        myLayoutmanager = new LinearLayoutManager(getApplication());
        myRecyclerPals.setLayoutManager(myLayoutmanager);

        mySearchPals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // Clear the search query
               clearDb();
                listenForData();

            }
        });

        // Adapters 10/15
        myAdapter = new FollowRCAdapter(getDataSet(), getApplication());
        myRecyclerPals.setAdapter(myAdapter);

    }


    // Searching for names and stuff
    private void listenForData() {
        DatabaseReference usersDb = FirebaseDatabase.getInstance().getReference().child("users");
        // \uf8ff end of string
        Query query = usersDb.orderByChild("email").startAt(myInput.getText().toString()).endAt(myInput.getText().toString() + "\uf8ff");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // Make sure it's empty
                String email = "";
                // User sent through this listener (Give each UID of each user returned)
                String uid = dataSnapshot.getRef().getKey();
                // A catch - This will return null sometimes
                if(dataSnapshot.child("email").getValue() != null){
                    email = dataSnapshot.child("email").getValue().toString();
                }
                // If email is different from our firebase get instance
                if(!email.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                    // Create new object of type Pals object
                    FollowPalsObject obj = new FollowPalsObject(email, uid);
                    // Results are our array list that will contain all users that are in recycler view
                    results.add(obj);
                    // Data has changed
                    myAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    // Clears the query
    private void clearDb() {
        int size = this.results.size();
        this.results.clear();
        myAdapter.notifyItemRangeChanged(0, size);

    }

    // Results from pals from database
    private ArrayList<FollowPalsObject> results = new ArrayList<>();
    private ArrayList<FollowPalsObject> getDataSet() {
        listenForData();
        return results;
    }
}
