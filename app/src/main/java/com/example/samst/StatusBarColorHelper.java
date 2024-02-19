package com.example.samst;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;

public class StatusBarColorHelper {

    // Method to change the status bar color
    public static void changeStatusBarColor(Activity activity, int colorResId) {
        Window window = activity.getWindow();

        // Check if the Android version is Lollipop or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set the status bar color
            window.setStatusBarColor(activity.getResources().getColor(colorResId));

            // For devices with light status bar icons, change the status bar icon color to dark
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                View decor = window.getDecorView();
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }
}
