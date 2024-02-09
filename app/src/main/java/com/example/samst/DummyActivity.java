package com.example.samst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;

import com.example.samst.Dashboard.DashboardScreen;
import com.example.samst.Registration.RegisterTabActivity;
import com.example.samst.SacWorld.SacWorldActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class DummyActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        Objects.requireNonNull(getSupportActionBar()).hide();
        drawerLayout = findViewById(R.id.dlayout_dummy);

        // Set up the toolbar and navigation drawer
        setDrawable();
        addDrawerLayoutAndMenu();

    }
    private void addDrawerLayoutAndMenu() {
        // Initialize drawerLayout before using it

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Set up NavigationView listener
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.menu_SAKWorld) {
                startActivity(new Intent(DummyActivity.this, SacWorldActivity.class));
                // Handle SAKWorld click
                return true;

            } else if (itemId == R.id.menu_Registration) {
                startActivity(new Intent(DummyActivity.this, RegisterTabActivity.class));
                // Handle Registration click
                return true;
            } else if (itemId == R.id.menu_Home) {
                startActivity(new Intent(DummyActivity.this, DashboardScreen.class));
                // Handle Logout click
                return true;


            } else if (itemId == R.id.menu_logout) {
                startActivity(new Intent(DummyActivity.this, SignInScreen.class));
                // Handle Logout click
                return true;

            } else {
                return false;
            }
        });


        // Add DrawerListener after initializing drawerLayout
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            int textColor = Color.parseColor("#FDFDFD");
            actionBar.setTitle("Dashboard");
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(Html.fromHtml("<font color='" + textColor + "'>" + "Dashboard" + "</font>"));
        }

    }



    private void setDrawable() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toggle.setDrawerIndicatorEnabled(false); // Disable default toggle icon
        Drawable customToggleIcon = ContextCompat.getDrawable(this, R.drawable.add_profile);
        toggle.setHomeAsUpIndicator(customToggleIcon); // Set custom toggle icon
;
    }
    // Add this method to handle the toggle button click event
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}