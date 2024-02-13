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


    }

}