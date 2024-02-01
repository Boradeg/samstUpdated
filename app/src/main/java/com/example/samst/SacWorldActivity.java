package com.example.samst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.samst.databinding.ActivityRegisterBinding;
import com.example.samst.databinding.ActivitySacWorldBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Objects;

public class SacWorldActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActivitySacWorldBinding binding;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySacWorldBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addDrawerLayoutAndMenu();
        String[] languages = getResources().getStringArray(R.array.Language);
        String[] blood_group = getResources().getStringArray(R.array.Blood_Group);
        String[] gender = getResources().getStringArray(R.array.Gender);
        ArrayAdapter<String> arrayAdapter_language = new ArrayAdapter<>(SacWorldActivity.this, R.layout.drop_down_layout, languages);
        ArrayAdapter<String> arrayAdapter_blood_group = new ArrayAdapter<>(SacWorldActivity.this, R.layout.drop_down_layout,blood_group);
        ArrayAdapter<String> arrayAdapter_gender = new ArrayAdapter<>(SacWorldActivity.this, R.layout.drop_down_layout,gender);
        binding.gender2.setAdapter(arrayAdapter_gender);
        binding.bloodGroup2.setAdapter(arrayAdapter_blood_group);
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    // Proceed with further actions
                    showToast("Validation successful!");
                } else {
                    showToast("Validation failed. Please check the fields.");
                }
            }
        });

        TextInputEditText dateOfBirthEditText = findViewById(R.id.dateOfBirth2);
        dateOfBirthEditText.setOnTouchListener((v, event) -> {
            // Check if the touch event is on the right drawable
            if (event.getAction() == MotionEvent.ACTION_UP) {
                int drawableRight = 2; // Index of the right drawable
                Rect bounds = dateOfBirthEditText.getCompoundDrawables()[drawableRight].getBounds();

                // Check if the touch event is within the bounds of the right drawable
                if (event.getRawX() >= (v.getRight() - bounds.width())) {
                    showDatePicker();
                    return true; // Consume the touch event
                }
            }
            return false; // Allow other touch events to be handled
        });


    }

    private boolean validateFields() {
        String firstNameFamilyHead2 = binding.firstNameFamilyHead2.getText().toString().trim();
        String surnameFamilyHead2 = binding.surnameFamilyHead2.getText().toString().trim();
        String parentName2 = binding.parentName2.getText().toString().trim();
        String dateOfBirth2 = binding.dateOfBirth2.getText().toString().trim();
        String gender2 = binding.gender2.getText().toString().trim();
        String bloodGroup2 = binding.bloodGroup2.getText().toString().trim();
        String address2 = binding.address2.getText().toString().trim();
        String country2 = binding.country2.getText().toString().trim();
        String state2 = binding.state2.getText().toString().trim();
        String city2 = binding.city2.getText().toString().trim();
        String village2 = binding.village2.getText().toString().trim();
        String serviceDetails = binding.serviceDetails.getText().toString().trim();
        String designation2 = binding.designation2.getText().toString().trim();
        String email2 = binding.email2.getText().toString().trim();
        String number2 = binding.number2.getText().toString().trim();
        String adharNumber2 = binding.adharNumber2.getText().toString().trim();
        String voterId2 = binding.voterId2.getText().toString().trim();
        String rationCardNumber2 = binding.rationCardNumber2.getText().toString().trim();
        String rationCard = binding.rationCard.getText().toString().trim();
        String annualFamilyIncome2 = binding.annualFamilyIncome2.getText().toString().trim();
        String  pincode2= binding.pincode2.getText().toString().trim();

        String  pincode4= binding.pincode2.getText().toString().trim();

        String  pincode3= binding.pincode2.getText().toString().trim();


        if (TextUtils.isEmpty(annualFamilyIncome2)) {
            binding.annualFamilyIncome2.setError("Income is required.");
            return false;
        } else {
            binding.annualFamilyIncome2.setError(null); // Clear error
        }
        if (TextUtils.isEmpty(firstNameFamilyHead2)) {
            binding.firstNameFamilyHead2.setError("Name is required.");
            return false;
        } else {
            binding.firstNameFamilyHead2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(surnameFamilyHead2)) {
            binding.surnameFamilyHead2.setError("Surname is required.");
            return false;
        } else {
            binding.surnameFamilyHead2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(parentName2)) {
            binding.parentName2.setError("Parent Name is required.");
            return false;
        } else {
            binding.parentName2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(dateOfBirth2)) {
            binding.dateOfBirth2.setError("Date of Birth is required.");
            return false;
        } else {
            binding.dateOfBirth2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(gender2)) {
            binding.gender2.setError("Gender is required.");
            return false;
        } else {
            binding.gender2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(bloodGroup2)) {
            binding.bloodGroup2.setError("Blood Group is required.");
            return false;
        } else {
            binding.bloodGroup2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(address2)) {
            binding.address2.setError("Address is required.");
            return false;
        } else {
            binding.address2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(country2)) {
            binding.country2.setError("Country is required.");
            return false;
        } else {
            binding.country2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(state2)) {
            binding.state2.setError("State is required.");
            return false;
        } else {
            binding.state2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(city2)) {
            binding.city2.setError("City is required.");
            return false;
        } else {
            binding.city2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(village2)) {
            binding.village2.setError("Village is required.");
            return false;
        } else {
            binding.village2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(serviceDetails)) {
            binding.serviceDetails.setError("Service Details are required.");
            return false;
        } else {
            binding.serviceDetails.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(designation2)) {
            binding.designation2.setError("Designation is required.");
            return false;
        } else {
            binding.designation2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(email2)) {
            binding.email2.setError("Email is required.");
            return false;
        } else {
            binding.email2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(number2)) {
            binding.number2.setError("Number is required.");
            return false;
        } else {
            binding.number2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(adharNumber2)) {
            binding.adharNumber2.setError("Aadhar Number is required.");
            return false;
        } else {
            binding.adharNumber2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(voterId2)) {
            binding.voterId2.setError("Voter ID is required.");
            return false;
        } else {
            binding.voterId2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(rationCard)) {
            binding.rationCard.setError("Ration Card is required.");
            return false;
        } else {
            binding.rationCard.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(rationCardNumber2)) {
            binding.rationCardNumber2.setError("Ration Card Number is required.");
            return false;
        } else {
            binding.rationCardNumber2.setError(null); // Clear error
        }

        // Add additional validation rules as needed

        return true;
    }

    private boolean isValidPin(String enteredPin) {
        return !TextUtils.isEmpty(enteredPin) && TextUtils.isDigitsOnly(enteredPin) && enteredPin.length() == 6;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void showDatePicker() {
        // Your existing DatePickerDialog code
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, monthOfYear, dayOfMonth) -> {
                    String selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, selectedYear);
                    Toast.makeText(this, selectedDate, Toast.LENGTH_SHORT).show();
                    // Add your logic here after selecting the date
                    // For example, update the TextInputEditText with the selected date
                    updateDateOfBirth(selectedDate);
                },
                year,
                month,
                day
        );
        datePickerDialog.show();
    }

    private void updateDateOfBirth(String selectedDate) {
        TextInputEditText dateOfBirthEditText = findViewById(R.id.dateOfBirth2);
        dateOfBirthEditText.setText(selectedDate);
    }
    private void addDrawerLayoutAndMenu() {
        // Initialize drawerLayout before using it
        drawerLayout = findViewById(R.id.dLayout3);

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
                startActivity(new Intent(SacWorldActivity.this, SacWorldActivity.class));
                // Handle SAKWorld click
                return true;

            } else if (itemId == R.id.menu_Registration) {
                startActivity(new Intent(SacWorldActivity.this, RegisterActivity.class));
                // Handle Registration click
                return true;

            } else if (itemId == R.id.menu_logout) {
                startActivity(new Intent(SacWorldActivity.this, SignInScreen.class));
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
            actionBar.setTitle("Form");
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(Html.fromHtml("<font color='" + textColor + "'>" + "Dashboard" + "</font>"));
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