package com.example.samst;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.bumptech.glide.Glide;

public class DashboardScreen extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private int currentEventId = 9;
    private DrawerLayout drawerLayout;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_screen);

        // Find the DrawerLayout in the layout XML and assign it to the drawerLayout variable
        drawerLayout = findViewById(R.id.drawerLayout);

        // Set up the toolbar and navigation drawer
        setDrawable();
        addDrawerLayoutAndMenu();

        // Initialize RecyclerView and fetch data
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        fetchData();
    }


    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sidhman.in/skmarati/new/admin/admin-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        fetchEventData(apiService);
    }

    private void fetchEventData(ApiService apiService) {
        Call<EventResponse> call = apiService.getEventDetails(currentEventId);

        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if (response.isSuccessful()) {
                    EventResponse eventResponse = response.body();
                    if (eventResponse != null) {
                        updateRecyclerView(eventResponse);
                        currentEventId++;
                        fetchEventData(apiService);
                    }
                } else {
                    // Handle error, show a message, etc.
                    System.out.println("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                // Handle failure, show a message, etc.
                t.printStackTrace();
            }
        });
    }

    private void updateRecyclerView(EventResponse eventResponse) {
        List<EventResponse> updatedList = new ArrayList<>(adapter.getEvents());
        updatedList.add(eventResponse);
        adapter.updateEvents(updatedList);


    }

    private void addDrawerLayoutAndMenu() {
        // Initialize drawerLayout before using it
        drawerLayout = findViewById(R.id.drawerLayout);

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
                startActivity(new Intent(DashboardScreen.this, SacWorldActivity.class));
                // Handle SAKWorld click
                return true;

            } else if (itemId == R.id.menu_Registration) {
                startActivity(new Intent(DashboardScreen.this, RegisterTabActivity.class));
                // Handle Registration click
                return true;
            } else if (itemId == R.id.menu_Home) {
                startActivity(new Intent(DashboardScreen.this, DashboardScreen.class));
                // Handle Logout click
                return true;


        } else if (itemId == R.id.menu_logout) {
                startActivity(new Intent(DashboardScreen.this, SignInScreen.class));
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

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
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






