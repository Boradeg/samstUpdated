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
// WebViewActivity.java
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

public class DummyActivity extends AppCompatActivity {

    private static final String YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v=y3YrbBrfy0E";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        Objects.requireNonNull(getSupportActionBar()).hide();

        StatusBarColorHelper.changeStatusBarColor(DummyActivity.this, R.color.button_bg_white);

        // Get the YouTube link from the intent
      //  String youtubeLink = getIntent().getStringExtra("youtubeLink");

        // Initialize the WebView
        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        // Load the YouTube link in the WebView
        webView.loadUrl(YOUTUBE_BASE_URL);
    }
}

