package com.example.jswin.designproject1;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayStoryActivity extends AppCompatActivity {

    // To find if it is a Chat or Story message
    String chatStory;

    String palId;
    private ImageView myImage;
    private boolean started = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_story);

        // Get bundle
        Bundle b = getIntent().getExtras();
        palId = b.getString("PalId");
        chatStory = b.getString("chatStory");


        myImage = findViewById(R.id.image);

        switch(chatStory){
            case "chat":
                listenForChat();
                break;
            case "story":
                listenForData();
        }


        listenForData();

    }

    private void listenForChat() {

        final DatabaseReference chat = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("received").child(palId);
        chat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String imageUrl = "";

                for(DataSnapshot chatSnapshot : dataSnapshot.getChildren()){

                    if(chatSnapshot.child("profileImageUrl").getValue() != null){
                        imageUrl = chatSnapshot.child("profileImageUrl").getValue().toString();
                    }

                    // Add the image url
                    imageUrlList.add(imageUrl);
                    if(!started){ // First time finding an image
                        started = true;
                        // Display images
                        initializeDisplay();
                        }
                        // This removes the images as loaded
                        // User will only be able to see them ONCE!!!
                        chat.child(chatSnapshot.getKey()).removeValue();
                    }

                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    ArrayList<String> imageUrlList = new ArrayList<>();
    private void listenForData() {
            DatabaseReference followingStoryDb = FirebaseDatabase.getInstance().getReference().child("users").child(palId);
            followingStoryDb.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String imageUrl = "";
                    long timestampBeg = 0;
                    long timestampEnd = 0;
                    for(DataSnapshot storySnapshot : dataSnapshot.child("story").getChildren()){
                        if(storySnapshot.child("timestampBeg").getValue() != null){
                            timestampBeg = Long.parseLong(storySnapshot.child("timestampBeg").getValue().toString());
                        }
                        if(storySnapshot.child("timestampEnd").getValue() != null){
                            timestampEnd = Long.parseLong(storySnapshot.child("timestampEnd").getValue().toString());
                        }
                        if(storySnapshot.child("profileImageUrl").getValue() != null){
                            imageUrl = storySnapshot.child("profileImageUrl").getValue().toString();
                        }
                        long timestampCurrent = System.currentTimeMillis();
                        if(timestampCurrent >= timestampBeg && timestampCurrent <= timestampEnd){
                            // Add the image url
                            imageUrlList.add(imageUrl);
                            if(!started){ // First time finding an image
                                started = true;
                                // Display images
                                initializeDisplay();
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
    // Iterator to hold which position in to get correct image
    private int imageIterator = 0;
    private void initializeDisplay() {

        // Glide is a github source code that will autoamtically take an image URL from
        // Firebase and display it much easier with on thinking required

        // This function gets the application, loats the list of URL's, gets the iterator,
        // AND then puts it into my image
        Glide.with(getApplication()).load(imageUrlList.get(imageIterator)).into(myImage);

        // Change image when Clicked
        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();

            }
        });

        // Function that allows us to call function various times
        // in given time frame
        final Handler handler = new Handler();
        // Delay time 10,000 millis = 10 seconds
        // 5,000 millis = 5 seconds
        final int delay = 5000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeImage();
                handler.postDelayed(this,delay); // Go until reaches if
            }
        }, delay);

    }

    // Change image when clicked
    private void changeImage() {
        // If the image list has reached it's end
        // If it has, the activity will kill itself and go back
        // to previous activity

        if(imageIterator == imageUrlList.size()-1){
            finish();
            return;
        }else{ // If it has NOT, then load next image in the list

            imageIterator++;
            Glide.with(getApplication()).load(imageUrlList.get(imageIterator)).into(myImage);


        }
    }

}
