package com.example.jswin.designproject1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ShowPicCaptureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_pic_capture);

        // Construct the image into a bitmap to show in ImageView

        // Get data picture
        Bundle extras = getIntent().getExtras();
        // Crash occurs, this fixed it because sometimes
        // null was sent -> This checks for that
        assert extras != null;
        byte[] myData = extras.getByteArray("picture");

        // Crash fix -> Double checks for a null
        if(myData != null){
            ImageView picTaken = findViewById(R.id.pictureTaken);

            // Get byte[]data to bitmap
            Bitmap decodeBitmap = BitmapFactory.decodeByteArray(myData, 0, myData.length);
            // Issue, the picture taken by the user is rotated 90 degrees
            // so it needs to be fixed

            picTaken.setImageBitmap(decodeBitmap);
        }

    }

}
