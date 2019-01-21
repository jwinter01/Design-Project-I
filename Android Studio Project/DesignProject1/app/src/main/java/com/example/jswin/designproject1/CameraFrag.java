package com.example.jswin.designproject1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

import static com.example.jswin.designproject1.R.id.surfaceView;

// add extends Fragment hit enter to auto import above
// Implements a callback for the surface holder
public class CameraFrag extends Fragment implements SurfaceHolder.Callback{

    // Variable to request work with the camera
    final int CAMERA_REQUEST_CODE = 1;
    // Camera is the camera
    Camera camera;
    // This receives/holds the picture taken by user
    Camera.PictureCallback picCallBack;
    // mySurfaceView is a reference to the SV in res/layout
    SurfaceView mySurfaceView;
    SurfaceHolder mySurfaceHolder;


    public static CameraFrag newInstance(){
        CameraFrag fragment = new CameraFrag();
        return fragment;
    }

    @Override
    // This was auto generated from Android Studio (onCreateView then press enter)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // View find view by id
        View view = inflater.inflate(R.layout.frag_camera, container, false);
        mySurfaceView = view.findViewById(surfaceView);
        // The holder is what controls the surface view
        // to add something to the surface view
        mySurfaceHolder = mySurfaceView.getHolder();

        // User can give permission inside the app
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            // Request user to authorize app to access camera
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
        }else{
            mySurfaceHolder.addCallback(this);
            mySurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        //mySurfaceHolder.addCallback(this);
        //mySurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        // Call the logout button
        Button myLogout = view.findViewById(R.id.logout);
        // Call the capture button (take a picture button)
        Button myCapture = view.findViewById(R.id.capture);



        myLogout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                LogOut();
            }
        });

        myCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                // Code to work with Capture (take picture)
                takePic();
            }
        });

        // Receives/holds the picture taken from the user
        picCallBack = new Camera.PictureCallback(){
            // To show to user, or to send to another user (Functionality added later)
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Intent intent = new Intent(getActivity(), ShowPicCaptureActivity.class);
                // To move variables between activities
                // byte[] data will be passed which is the picture taken
                intent.putExtra("picture", data);
                // Start the activity and return
                // ERROR FOUND AT THIS LOCATION
                startActivity(intent);
                return;
            }
        };


        return view; // Return the view
    }

    // Method to capture image (take picture)
    private void takePic() {
        // Shutterspeed and raw will be null, picCallBack see comments above
        camera.takePicture(null, null, picCallBack);
    }


    // The parameters for the camera and what to do with it
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        // Set camera variable to access the camera
        camera = camera.open();

        // The camera parameters:
        Camera.Parameters param;
        param = camera.getParameters();
        // Must be set to 90* so the screen is oriented vertically
        camera.setDisplayOrientation(90);
        // Set FPS to 30 -> Most phones support 30fps
        param.setPreviewFrameRate(30);
        // This function will make the camera focus
        // at all times on center object in camera view
        param.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        // Sets the camera to the param parameters
        camera.setParameters(param);

        // Error with above function required a try catch
        try {
            camera.setPreviewDisplay(mySurfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Give permissions to Android 6 and Up
        camera.startPreview();
    }

    /*
    Leave the surfaceChanged and surfaceDestroyed
    This file wont run without them -> look into this later
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    // Called whenever if ActivityCompat code is ran -> Success/Not Success
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            // There is only 1 request but the case will be left here in case
            // I want to add more functionality to the code on the camera view
            // by requesting more permissions
            case CAMERA_REQUEST_CODE:{
                // If result of request is positive and user has granted app with the camera permissions
                if(grantResults.length>0&& grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    mySurfaceHolder.addCallback(this);
                    mySurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
                }else{
                    // Make some toast
                    Toast.makeText(getContext(), "Camera permissions required for application to function properly", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }
    // For Logout button
    private void LogOut() {
        // Signs the user out
        // This works too not sure if this is better mAuth.signOut()
        FirebaseAuth.getInstance().signOut();
        // Move the user to the splinter screen login
        Intent intent = new Intent(getContext(), SplinterScreenActivity.class);
        // Clear everything ontop of activity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return;
    }
}


