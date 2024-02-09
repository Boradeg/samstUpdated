package com.example.samst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.samst.Dashboard.DashboardScreen;
import com.example.samst.databinding.ActivitySignUpScreenBinding;

import java.util.Objects;


public class SignUpScreen extends AppCompatActivity {

    private ActivitySignUpScreenBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize View Binding
        binding = ActivitySignUpScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Hide ActionBar
        Objects.requireNonNull(getSupportActionBar()).hide();
        // Access views directly using the generated binding class
        binding.signUpButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpScreen.this, DashboardScreen.class));
                finish();
            }
        });

        binding.textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpScreen.this, SignInScreen.class));
                finish();
            }
        });
    }
}
