package com.example.jswin.designproject1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registration);

        // Login and Registration Buttons (See activity_login_registration.xml)
        Button mLogin = findViewById(R.id.Login);
        Button mSignup = findViewById(R.id.Signup);

        // Login Click Listeners listen to Login button clicks
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Goes to the Login Activity
                Intent intent = new Intent(getApplication(), LoginActivity.class);
                startActivity(intent);
                return;
            }
        });

        // Signup Click Listeners listen to Signup button clicks
        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Goes to the Signup Activity
                Intent intent = new Intent(getApplication(), SignupActivity.class);
                startActivity(intent);
                return;
            }
        });

    }
}
