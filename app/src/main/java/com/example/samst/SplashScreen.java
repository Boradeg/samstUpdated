package com.example.samst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        // Hide the ActionBar
        getSupportActionBar().hide();

        // Add a delay of 2 seconds
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity after the delay
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish(); // close the current activity
            }
        }, 2000); // 2000 milliseconds = 2 seconds

    }
}