package com.example.jswin.designproject1;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
// The login or signup screen presented to the user on startup
public class SplinterScreenActivity extends AppCompatActivity {


    public static Boolean initialized = false;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Holds all information about the user (IF NULL, NOT LOGGED IN)
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut(); // This will sign the user out right away and force a relog
        if(mAuth.getCurrentUser() != null){
            Intent intent = new Intent(getApplication(), MainActivity.class);
            // FOR SOME REASON THE USER STAYS LOGGED IN
            //Intent intent = new Intent(getApplication(), LoginRegistrationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // ERASES EVERYTHING ON TOP OF THIS ACTIVITY
            startActivity(intent);
            finish();
            return;
        }
        else{
            Intent intent = new Intent(getApplication(), LoginRegistrationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // ERASES EVERYTHING ON TOP OF THIS ACTIVITY
            startActivity(intent);
            finish();
            return;
        }
    }
}
