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
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.samst.databinding.ActivityRegisterBinding;
import com.example.samst.databinding.ActivitySacWorldBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
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
       // String[] blood_group = getResources().getStringArray(R.array.Blood_Group);
        String[] gender = getResources().getStringArray(R.array.Gender);
        ArrayAdapter<String> arrayAdapter_language = new ArrayAdapter<>(SacWorldActivity.this, R.layout.drop_down_layout, languages);
        ArrayAdapter<String> arrayAdapter_blood_group = new ArrayAdapter<>(SacWorldActivity.this, R.layout.drop_down_layout,languages);
        ArrayAdapter<String> arrayAdapter_gender = new ArrayAdapter<>(SacWorldActivity.this, R.layout.drop_down_layout,gender);
        binding.serviceDetail2.setAdapter(arrayAdapter_gender);
       binding.city2.setAdapter(arrayAdapter_gender);
       binding.rationCard2.setAdapter(arrayAdapter_gender);
       binding.city2.setAdapter(arrayAdapter_gender);
       binding.country2.setAdapter(arrayAdapter_gender);
       binding.gender2.setAdapter(arrayAdapter_gender);
       binding.bloodGroup2.setAdapter(arrayAdapter_gender);
       binding.state2.setAdapter(arrayAdapter_gender);
//        CountryCodePicker countryCodePicker = findViewById(R.id.countryPicker);
//
//        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
//            @Override
//            public void onCountrySelected() {
//                String selectedCountryCode = countryCodePicker.getSelectedCountryCode();
//                String selectedCountryName = countryCodePicker.getSelectedCountryName();
//                // Handle the selected country code and name
//            }
//        });
       // binding.bloodGroup2.setAdapter(arrayAdapter_blood_group);
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

        //AutoCompleteTextView dateOfBirthEditText = findViewById(R.id.dateOfBirth2);
//        binding.dateOfBirth2.setOnTouchListener((v, event) -> {
//            // Check if the touch event is on the right drawable
//            if (event.getAction() == MotionEvent.ACTION_UP) {
//                int drawableRight = 2; // Index of the right drawable
//                Rect bounds = binding.getCompoundDrawables()[drawableRight].getBounds();
//
//                // Check if the touch event is within the bounds of the right drawable
//                if (event.getRawX() >= (v.getRight() - bounds.width())) {
//                    showDatePicker();
//                    return true; // Consume the touch event
//                }
//            }
//            return false; // Allow other touch events to be handled
//        });
        // Find the AutoCompleteTextView for Date of Birth
        final AutoCompleteTextView dateOfBirthTextView = findViewById(R.id.dateOfBirth2);

        // Set a click listener on the AutoCompleteTextView to open DatePickerDialog
        dateOfBirthTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(dateOfBirthTextView);
            }
        });


    }

    private void showDatePickerDialog(final AutoCompleteTextView autoCompleteTextView) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String formattedDate = String.format("%02d/%02d/%04d", day, month + 1, year);
                        autoCompleteTextView.setText(formattedDate);
                    }
                },
                year, month, day);

        datePickerDialog.show();
    }
    private boolean isValidName(String name) {
        // Customize this method based on your definition of a valid name
        // For example, you can use a regular expression to check for the presence of numbers
        return !name.matches(".*\\d.*");
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
        String serviceDetails = binding.serviceDetail2.getText().toString().trim();
        String designation2 = binding.designation2.getText().toString().trim();
        String email2 = binding.email2.getText().toString().trim();
        String number2 = binding.number2.getText().toString().trim();
        String adharNumber2 = binding.adharNumber2.getText().toString().trim();
        String voterId2 = binding.voterId2.getText().toString().trim();
        String rationCardNumber2 = binding.rationCardNumber2.getText().toString().trim();
        String rationCard = binding.rationCard2.getText().toString().trim();
        String annualFamilyIncome2 = binding.annualFamilyIncome2.getText().toString().trim();
        String pincode2 = binding.pincode2.getText().toString().trim();
        String adharNumberPattern = "^[2-9]{1}[0-9]{11}$";
        String voterIdPattern = "^[a-zA-Z]{3}[0-9]{7}$";
        String rationCardNumberPattern = "^[a-zA-Z0-9]+$";

        String pincodePattern = "^[1-9][0-9]{5}$";

        String mobileNumberPattern = "^[6-9][0-9]{9}$";
        String emailPattern = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        Map<View, String> errorMap = new HashMap<>();
        // Validate Aadhar Number
        if (TextUtils.isEmpty(adharNumber2)) {
            errorMap.put(binding.adharNumber2, "Aadhar Number is required.");
        } else if (!adharNumber2.matches(adharNumberPattern)) {
            errorMap.put(binding.adharNumber2, "Invalid Aadhar Number.");
        } else {
            binding.adharNumber2.setError(null); // Clear error
        }
        // Validate Pincode
        if (TextUtils.isEmpty(pincode2)) {
            errorMap.put(binding.pincode2, "Pincode is required.");
        } else if (!pincode2.matches(pincodePattern)) {
            errorMap.put(binding.pincode2, "Invalid Pincode.");
        } else {
            binding.pincode2.setError(null); // Clear error
        }

        // Validate Income
        if (TextUtils.isEmpty(annualFamilyIncome2)) {
            errorMap.put(binding.annualFamilyIncome2, "Income is required.");
        } else {
            try {
                double incomeValue = Double.parseDouble(annualFamilyIncome2);
                if (incomeValue <= 0) {
                    errorMap.put(binding.annualFamilyIncome2, "Income should be greater than 0.");
                } else {
                    binding.annualFamilyIncome2.setError(null); // Clear error
                }
            } catch (NumberFormatException e) {
                errorMap.put(binding.annualFamilyIncome2, "Invalid Income value.");
            }
        }

        // Validate Voter ID
        if (TextUtils.isEmpty(voterId2)) {
            errorMap.put(binding.voterId2, "Voter ID is required.");
        } else if (!voterId2.matches(voterIdPattern)) {
            errorMap.put(binding.voterId2, "Invalid Voter ID.");
        } else {
            binding.voterId2.setError(null); // Clear error
        }

        // Validate Ration Card Number
        if (TextUtils.isEmpty(rationCardNumber2)) {
            errorMap.put(binding.rationCardNumber2, "Ration Card Number is required.");
        } else if (!rationCardNumber2.matches(rationCardNumberPattern)) {
            errorMap.put(binding.rationCardNumber2, "Invalid Ration Card Number.");
        } else {
            binding.rationCardNumber2.setError(null); // Clear error
        }

        // Validate Mobile Number
        if (TextUtils.isEmpty(number2)) {
            errorMap.put(binding.number2, "Mobile Number is required.");
        } else if (!number2.matches(mobileNumberPattern)) {
            errorMap.put(binding.number2, "Invalid Mobile Number.");
        } else {
            binding.number2.setError(null); // Clear error
        }

        // Validate Email
        if (TextUtils.isEmpty(email2)) {
            errorMap.put(binding.email2, "Email is required.");
        } else if (!email2.matches(emailPattern)) {
            errorMap.put(binding.email2, "Invalid Email Address.");
        } else {
            binding.email2.setError(null); // Clear error
        }

        //Map<TextInputLayout, String> errorMap = new HashMap<>();
        if (TextUtils.isEmpty(annualFamilyIncome2)) {
            errorMap.put(binding.annualFamilyIncome2, "Income is required.");
        } else {
            binding.annualFamilyIncome2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(firstNameFamilyHead2)) {
            errorMap.put(binding.firstNameFamilyHead2, "Name is required.");
        } else if (!isValidName(firstNameFamilyHead2)) {
            errorMap.put(binding.firstNameFamilyHead2, "Name should not contain numbers.");
        } else {
            binding.firstNameFamilyHead2.setError(null); // Clear error
        }


        if (TextUtils.isEmpty(surnameFamilyHead2)) {
            errorMap.put(binding.surnameFamilyHead2, "Surname is required.");
        } else if (!isValidName(surnameFamilyHead2)) {
            errorMap.put(binding.surnameFamilyHead2, "Surname should not contain numbers.");
        } else {
            binding.surnameFamilyHead2.setError(null); // Clear error
        }
        if (TextUtils.isEmpty(parentName2)) {
            errorMap.put(binding.parentName2, "Parent Name is required.");
        } else if (!isValidName(parentName2)) {
            errorMap.put(binding.parentName2, "Parent Name should not contain numbers.");
        } else {
            binding.parentName2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(dateOfBirth2)) {
            errorMap.put(binding.dateOfBirth2, "Date of Birth is required.");
        } else {
            binding.dateOfBirth2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(gender2)) {
            errorMap.put(binding.gender2, "Gender is required.");
        } else {
            binding.gender2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(bloodGroup2)) {
            errorMap.put(binding.bloodGroup2, "Blood Group is required.");
        } else {
            binding.bloodGroup2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(address2)) {
            errorMap.put(binding.address2, "Address is required.");
        } else {
            binding.address2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(country2)) {
            errorMap.put(binding.country2, "Country is required.");
        } else {
            binding.country2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(state2)) {
            errorMap.put(binding.state2, "State is required.");
        } else {
            binding.state2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(city2)) {
            errorMap.put(binding.city2, "City is required.");
        } else {
            binding.city2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(village2)) {
            errorMap.put(binding.village2, "Village is required.");
        } else {
            binding.village2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(serviceDetails)) {
            errorMap.put(binding.serviceDetail2, "Service Details are required.");
        } else {
            binding.serviceDetail2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(designation2)) {
            errorMap.put(binding.designation2, "Designation is required.");
        } else {
            binding.designation2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(email2)) {
            errorMap.put(binding.email2, "Email is required.");
        } else {
            binding.email2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(number2)) {
            errorMap.put(binding.number2, "Number is required.");
        } else {
            binding.number2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(adharNumber2)) {
            errorMap.put(binding.adharNumber2, "Aadhar Number is required.");
        } else {
            binding.adharNumber2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(voterId2)) {
            errorMap.put(binding.voterId2, "Voter ID is required.");
        } else {
            binding.voterId2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(rationCard)) {
            errorMap.put(binding.rationCard2, "Ration Card is required.");
        } else {
            binding.rationCard2.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(rationCardNumber2)) {
            errorMap.put(binding.rationCardNumber2, "Ration Card Number is required.");
        } else {
            binding.rationCardNumber2.setError(null); // Clear error
        }

        // Validate Pincode
        if (TextUtils.isEmpty(pincode2)) {
            errorMap.put(binding.pincode2, "Pincode is required.");
        } else {
            binding.pincode2.setError(null); // Clear error
        }

// Show errors for all empty fields
        for (Map.Entry<View, String> entry : errorMap.entrySet()) {
            setErrorForView(entry.getKey(), entry.getValue());
        }
        // If there are errors, return false; otherwise, return true
        return errorMap.isEmpty();
    }
    private void setErrorForView(View view, String errorMessage) {
        if (view instanceof TextInputLayout) {
            ((TextInputLayout) view).setError(errorMessage);
        } else if (view instanceof TextInputEditText) {
            ((TextInputEditText) view).setError(errorMessage);
        } else {
            // Handle other types of views if needed
            // For non-text input views, consider providing a more suitable way to display errors
        }
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
        AutoCompleteTextView dateOfBirthEditText = findViewById(R.id.dateOfBirth2);
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
            } else if (itemId == R.id.menu_Home) {
                startActivity(new Intent(SacWorldActivity.this, DashboardScreen.class));
                // Handle Logout click
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