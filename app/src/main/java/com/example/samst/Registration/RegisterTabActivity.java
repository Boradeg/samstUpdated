package com.example.samst.Registration;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.samst.Dashboard.DashboardScreen;
import com.example.samst.R;
import com.example.samst.SacWorld.SacWorldActivity;
import com.example.samst.SignInScreen;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class RegisterTabActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tab);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        //getSupportActionBar().hide();
        addDrawerLayoutAndMenu();

        // Set up ViewPager and TabLayout
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    public void switchToFragment2() {
        viewPager.setCurrentItem(1);
    }

    // ViewPagerAdapter class
    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final String[] tabTitles = new String[]{"Personal Detail", "Guardian Detail"};

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Fragment1();
                case 1:
                    return new Fragment2();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Return the tab title at the given position
            return tabTitles[position];
        }
    }
    private void addDrawerLayoutAndMenu() {
        // Initialize drawerLayout before using it
        drawerLayout = findViewById(R.id.dlay_register);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Set up NavigationView listener
        NavigationView navigationView = findViewById(R.id.navigationView2);
        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.menu_SAKWorld) {
                startActivity(new Intent(RegisterTabActivity.this, SacWorldActivity.class));
                // Handle SAKWorld click
                return true;

            } else if (itemId == R.id.menu_Registration) {
                startActivity(new Intent(RegisterTabActivity.this, RegisterTabActivity.class));
                // Handle Registration click
                return true;

            } else if (itemId == R.id.menu_logout) {
                startActivity(new Intent(RegisterTabActivity.this, SignInScreen.class));
                // Handle Logout click
                return true;
            } else if (itemId == R.id.menu_Home) {
                startActivity(new Intent(RegisterTabActivity.this, DashboardScreen.class));
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
            actionBar.setTitle("Registraion");
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(Html.fromHtml("<font color='" + textColor + "'>" + "Registraion" + "</font>"));
        }


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
}



