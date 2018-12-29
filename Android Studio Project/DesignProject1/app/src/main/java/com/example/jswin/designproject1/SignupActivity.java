package com.example.jswin.designproject1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class SignupActivity extends AppCompatActivity {

    private Button mSignUp;
    private EditText mEmail, mPassword, mName;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // If this user is not NULL, go to other page
                if(user!=null){
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // ERASES EVERYTHING ON TOP OF THIS ACTIVITY
                    startActivity(intent);
                    finish();
                    return;
                }

            }
        };

        // Variables needed for firebase
        mAuth = FirebaseAuth.getInstance();
        mSignUp = findViewById(R.id.Signup);
        mName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = mName.getText().toString();
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    // Task is returned so
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Notice if login is not successful otherwise user wont know
                        if (!task.isSuccessful()){
                            Toast.makeText(getApplication(), "ERROR: Sign In Failed", Toast.LENGTH_SHORT).show();
                        }else{
                            // Signup is Successful
                            String userId = mAuth.getCurrentUser().getUid();
                            // Database reference
                            DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
                            Map userInfo = new HashMap<>();
                            userInfo.put("email",email);
                            userInfo.put("name",name);
                            userInfo.put("profileImageUrl", "default"); // Profile picture? Save for later

                            currentUserDb.updateChildren(userInfo);

                        }
                    }
                });

            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop(){
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}
