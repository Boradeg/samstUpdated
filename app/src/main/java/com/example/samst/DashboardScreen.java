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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import java.util.List;
import java.util.Objects;

public class DashboardScreen extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_screen);
        addDrawerLayoutAndMenu();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        setDrawable();
        List<EventData> doctorDataList = getEventData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DoctorAdapter doctorAdapter = new DoctorAdapter(doctorDataList);
        recyclerView.setAdapter(doctorAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                startActivity(new Intent(DashboardScreen.this, RegisterActivity.class));
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


    private List<EventData> getEventData() {
        // Replace this with your logic to fetch doctor data
        // Sample data for demonstration
        return List.of(
               new EventData("Melava-6 (part 1)", "https://www.youtube.com/watch?v=VL_ccRojUTI", "Previous"),
                new EventData("Melava-6 (part 2)", "https://www.youtube.com/watch?v=VL_ccRojUTI", "Previous"),
                new EventData("Melava-6 (part 3)", "https://www.youtube.com/watch?v=VL_ccRojUTI", "Previous"),
                new EventData("Melava-6 (part 4)", "https://www.youtube.com/watch?v=VL_ccRojUTI", "Previous"),
                new EventData("Melava-6 (part 5)", "https://www.youtube.com/watch?v=VL_ccRojUTI", "Previous"),
                new EventData("Melava-6 (part 6)", "https://www.youtube.com/watch?v=VL_ccRojUTI", "Previous"),
                new EventData("Melava-6 (part 7)", "https://www.youtube.com/watch?v=VL_ccRojUTI", "Previous"),
                new EventData("Melava-6 (part 1)", "https://www.youtube.com/watch?v=VL_ccRojUTI", "Previous"),
                new EventData("Melava-6 (part 1)", "https://www.youtube.com/watch?v=VL_ccRojUTI", "Previous"),
                new EventData("Melava-6 (part 1)", "https://www.youtube.com/watch?v=VL_ccRojUTI", "Previous"),
                new EventData("Melava-6 (part 2)", "https://www.youtube.com/watch?v=VL_ccRojUTI", "Previous"),
                new EventData("Melava-6 (part 3)", "https://www.youtube.com/watch?v=VL_ccRojUTI", "Previous"),
                new EventData("Melava-6 (part 4)", "https://www.youtube.com/watch?v=VL_ccRojUTI", "Previous"),
                new EventData("Melava-6 (part 5)", "https://www.youtube.com/watch?v=VL_ccRojUTI", "Previous"),
                new EventData("Melava-6 (part 6)", "https://www.youtube.com/watch?v=VL_ccRojUTI", "Previous"),
                new EventData("Melava-6 (part 1)", "https://www.youtube.com/watch?v=VL_ccRojUTI", "Previous"),
                new EventData("Melava-6 (part 2)", "https://www.youtube.com/watch?v=VL_ccRojUTI", "Previous")
        );
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

class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    private final List<EventData> doctorList;

    public DoctorAdapter(List<EventData> doctorList) {
        this.doctorList = doctorList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView doctorNameTextView;
        TextView dateTextView;
        TextView usernameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorNameTextView = itemView.findViewById(R.id.eventName);
            dateTextView = itemView.findViewById(R.id.eventLink);
            usernameTextView = itemView.findViewById(R.id.Btn);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventData currentDoctor = doctorList.get(position);
        holder.doctorNameTextView.setText(currentDoctor.getEventName());
        holder.dateTextView.setText(currentDoctor.getEventlink());
        holder.usernameTextView.setText(currentDoctor.getMode());
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }
}

