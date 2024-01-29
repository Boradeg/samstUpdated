package com.example.samst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.samst.databinding.ActivityMainBinding;
import com.example.samst.databinding.ActivitySignInScreenBinding;

public class SignInScreen extends AppCompatActivity {
    private ActivitySignInScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Hide ActionBar
        getSupportActionBar().hide();

        // Access views directly using the generated binding class
        binding.signInButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInScreen.this, DashboardScreen.class));
            }
        });

        binding.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInScreen.this,
                        SignUpScreen.class));
            }
        });


    }
}