package com.example.samst.Dashboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samst.R;
import com.example.samst.Registration.RegisterTabActivity;
import com.example.samst.SacWorld.SacWorldActivity;
import com.example.samst.SignInScreen;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//
import retrofit2.Call;
import retrofit2.Response;

public class DashboardScreen extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private int currentEventId = 9;
    private DrawerLayout drawerLayout;
    private Button allButton;

    private Button prevButton;
    private Button latestButton;
    private Button upcomingButton;
    private Button liveButton;
    private ProgressBar pBar;


    @SuppressLint({"NonConstantResourceId", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_screen);

        // Find the DrawerLayout in the layout XML and assign it to the drawerLayout variable
        drawerLayout = findViewById(R.id.drawerLayout);
        pBar = findViewById(R.id.pBar);

        allButton = findViewById(R.id.allButton);
        prevButton = findViewById(R.id.prevButton);
        latestButton = findViewById(R.id.latestButton);
        upcomingButton = findViewById(R.id.upcomingButton);
        liveButton = findViewById(R.id.liveButton);
        recyclerView = findViewById(R.id.recyclerView);


                recyclerView.setLayoutManager(new LinearLayoutManager(DashboardScreen.this));
                adapter = new EventAdapter(new ArrayList<>(),DashboardScreen.this);
                recyclerView.setAdapter(adapter);
                // Run function after 1 second delay
                fetchData();




        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetButtonColors();
                allButton.setBackgroundColor(getResources().getColor(R.color.Red));
                Toast.makeText(DashboardScreen.this, "All button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButtonColors();
                prevButton.setBackgroundColor(getResources().getColor(R.color.Red));
                Toast.makeText(DashboardScreen.this, "Previous button clicked", Toast.LENGTH_SHORT).show();


            }
        });

//
        liveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetButtonColors();
                liveButton.setBackgroundColor(getResources().getColor(R.color.Red));
                Toast.makeText(DashboardScreen.this, "Live button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        latestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetButtonColors();
                latestButton.setBackgroundColor(getResources().getColor(R.color.Red));
                Toast.makeText(DashboardScreen.this, "Latest button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        upcomingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButtonColors();
                upcomingButton.setBackgroundColor(getResources().getColor(R.color.Red));
                Toast.makeText(DashboardScreen.this, "Upcoming button clicked", Toast.LENGTH_SHORT).show();
            }
        });
        // Set up the toolbar and navigation drawer
        setDrawable();
        addDrawerLayoutAndMenu();
    }

    private void resetButtonColors() {
        allButton.setBackgroundColor(getResources().getColor(R.color.button_bg_white));
        prevButton.setBackgroundColor(getResources().getColor(R.color.button_bg_white));
        liveButton.setBackgroundColor(getResources().getColor(R.color.button_bg_white));
        latestButton.setBackgroundColor(getResources().getColor(R.color.button_bg_white));
        upcomingButton.setBackgroundColor(getResources().getColor(R.color.button_bg_white));
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
            actionBar.setLogo(R.drawable.logo);

            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(Html.fromHtml("<font color='" + textColor + "'>" + "Dashboard" + "</font>"));
        }

    }
    private void setDrawable() {
        Drawable customToggleIcon = ContextCompat.getDrawable(this, R.drawable.add_profile);

        customToggleIcon.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN); // Change PorterDuff mode

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(customToggleIcon); // Set custom toggle icon
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        // Retrieve the SearchView and configure it
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        // Configure the SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission
                Toast.makeText(DashboardScreen.this, query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle search query text change
                return false;
            }
        });

        return true;
    }


}






