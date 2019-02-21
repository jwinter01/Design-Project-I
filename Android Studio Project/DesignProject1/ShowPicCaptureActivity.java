package com.example.jswin.designproject1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import java.io.FileNotFoundException;

public class ShowPicCaptureActivity extends AppCompatActivity {

    // For finding if the message is a Story or a Chat
    String userID;
    String chatStory;

    String Uid;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pic_capture);


        /*
        NOTE: THIS WILL LOOK FOR THE IMAGE INSTALLED ON THE DEVICE (DEFAULT NAME IS imageToSend)
        AND IT WILL SAVE IT TO THE BITMAP HERE
         */
        try {
            bitmap = BitmapFactory.decodeStream(getApplication().openFileInput("imageToSend"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            finish();
            return;
        }

        // Set the image to the bitmap
        ImageView mImage = findViewById(R.id.pictureTaken);
        mImage.setImageBitmap(bitmap);

        Uid = FirebaseAuth.getInstance().getUid();

        Button mySend = findViewById(R.id.findSender);
        mySend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), choose_pal_activity.class);
                startActivity(intent);
                return;
            }

        });
    }

}