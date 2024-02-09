package com.example.samst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.samst.databinding.ActivityRegisterBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
         addDrawerLayoutAndMenu();
        setDropDownValues();


        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateDropDownField();
                validateFields();
            }
        });

    }

    private void setDropDownValues() {
        String[] gender = getResources().getStringArray(R.array.Gender);
        ArrayAdapter<String> arrayAdapter_gender = new ArrayAdapter<>(RegisterActivity.this, R.layout.drop_down_layout,gender);
        binding.idGender.setAdapter(arrayAdapter_gender);
    }

    private void validateDropDownField() {
        int[] autoCompleteTextViewIds = {R.id.id_gender, R.id.id_dateOfBirth,R.id.id_lookingFor,R.id.id_country,R.id.id_city
                ,R.id.id_state,R.id.id_blood_group,R.id.id_gender,R.id.id_service_detail,
                R.id.id_height_in_feet,R.id.id_height_in_inch,R.id.id_complexion,R.id.id_spectacles,
                R.id.id_lens,R.id.id_service_type,R.id.id_nakshtra,R.id.id_charan,
                R.id.id_yoni,R.id.id_rashi,R.id.id_nadi,R.id.id_gotra,
                R.id.id_believe_horoscope,R.id.id_mangal,R.id.id_swami
        }; // Add more if needed
        int[] textInputLayoutIds = {R.id.genderLayout, R.id.date_of_birth_layout,R.id.looking_for_layout,R.id.id_country_layout,R.id.city_layout
                ,R.id.state_layout,R.id.blood_group_layout,R.id.genderLayout,R.id.id_service_detail_layout,
                R.id.id_height_in_feet_layout,R.id.id_height_in_inch_layout,R.id.id_complexion_layout,R.id.id_spectacles_layout,
                R.id.id_lens_layout,R.id.id_service_type_layout,R.id.id_nakshtra_layout,R.id.id_charan_layout,
                R.id.id_yoni_layout,R.id.id_rashi_layout,R.id.id_nadi_layout,R.id.id_gotra_layout,
                R.id.id_believe_horoscope_layout,R.id.id_mangal_layout,R.id.id_swami_layout


        }; // Add more if needed

        for (int i = 0; i < autoCompleteTextViewIds.length; i++) {
            AutoCompleteTextView autoCompleteTextView = findViewById(autoCompleteTextViewIds[i]);
            TextInputLayout textInputLayout = findViewById(textInputLayoutIds[i]);

            String fieldValue = autoCompleteTextView.getText().toString().trim();
            if (TextUtils.isEmpty(fieldValue)) {
                textInputLayout.setError("This field is required.");
            } else {
                textInputLayout.setError(null); // Clear error
            }
        }
    }
    private void validateFieldOfText(String fieldValue, TextInputEditText textInputLayout, String errorMessage) {
        if (TextUtils.isEmpty(fieldValue)) {
            textInputLayout.setError(errorMessage);
        } else {
            textInputLayout.setError(null); // Clear error
        }
    }
    private boolean validateFields() {
        String idFname = binding.idFname.getText().toString().trim();
        String idFathername = binding.idFathername.getText().toString().trim();
        String idSurname = binding.idSurname.getText().toString().trim();
        String idLookingFor = binding.idLookingFor.getText().toString().trim();
        String idEmail = binding.idEmail.getText().toString().trim();
        String idNumber = binding.idNumber.getText().toString().trim();
        String idCountry = binding.idCountry.getText().toString().trim();
        String idState = binding.idState.getText().toString().trim();
        String idCity = binding.idCity.getText().toString().trim();
        String idVillage = Objects.requireNonNull(binding.idVillage.getText()).toString().trim();
        String idDateOfBirth = binding.idDateOfBirth.getText().toString().trim();
        String idGender = binding.idGender.getText().toString().trim();
        String idBloodGroup = binding.idBloodGroup.getText().toString().trim();
        String idAge = binding.idAge.getText().toString().trim();
        String idServiceDetail = binding.idServiceDetail.getText().toString().trim();
        String idDesignation = binding.idDesignation.getText().toString().trim();
        String idBirthPlace = binding.idBirthPlace.getText().toString().trim();
        String idBirthTime = binding.idBirthTime.getText().toString().trim();
        String idHeightInFeet = binding.idHeightInFeet.getText().toString().trim();
        String idHeightInInch = binding.idHeightInInch.getText().toString().trim();
        String idComplexion = binding.idComplexion.getText().toString().trim();
        String idSpectacles = binding.idSpectacles.getText().toString().trim();
        String idLens = binding.idLens.getText().toString().trim();
        String idServiceType = binding.idServiceType.getText().toString().trim();
        String idNakshtra = binding.idNakshtra.getText().toString().trim();
        String idCharan = binding.idCharan.getText().toString().trim();
        String idYoni = binding.idYoni.getText().toString().trim();
        String idRashi = binding.idRashi.getText().toString().trim();
        String idNadi = binding.idNadi.getText().toString().trim();
        String idGotra = binding.idGotra.getText().toString().trim();
        String idSwami = binding.idSwami.getText().toString().trim();
        String idBelieveHoroscope = binding.idBelieveHoroscope.getText().toString().trim();
        String idEducation = binding.idEducation.getText().toString().trim();
        String idMangal = binding.idMangal.getText().toString().trim();
        String idExpectation = binding.idExpectation.getText().toString().trim();

        String mobileNumberPattern = "^[6-9][0-9]{9}$";
        String emailPattern = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        Map<View, String> errorMap = new HashMap<>();
        validateFieldOfText(idExpectation, binding.idExpectation, "Expectation is required.");
        validateFieldOfText(idBirthTime, binding.idBirthTime, "Birth Time is required.");
        validateFieldOfText(idAge, binding.idAge, "Age is required.");
        validateFieldOfText(idBirthPlace, binding.idBirthPlace, "Birth Place is required.");
        validateFieldOfText(idEducation, binding.idEducation, "Education Place is required.");
        validateFieldOfText(idVillage, binding.idVillage, "Village is required.");
        validateFieldOfText(idDesignation, binding.idDesignation, "Designation is required.");
        validateFieldOfText(idEducation, binding.idEducation, "Education is required.");

        // Validate Mobile Number
        if (TextUtils.isEmpty(idNumber)) {
            errorMap.put(binding.idNumber, "Mobile Number is required.");
        } else if (!idNumber.matches(mobileNumberPattern)) {
            errorMap.put(binding.idNumber, "Invalid Mobile Number.");
        } else {
            binding.idNumber.setError(null); // Clear error
        }

        // Validate Email
        if (TextUtils.isEmpty(idEmail)) {
            errorMap.put(binding.idEmail, "Email is required.");
        } else if (!idEmail.matches(emailPattern)) {
            errorMap.put(binding.idEmail, "Invalid Email Address.");
        } else {
            binding.idEmail.setError(null); // Clear error
        }

        //Map<TextInputLayout, String> errorMap = new HashMap<>();
        if (TextUtils.isEmpty(idFname)) {
            errorMap.put(binding.idFname, "First Name is required.");
        } else {
            binding.idFname.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(idFathername)) {
            errorMap.put(binding.idFathername, "Name is required.");
        } else if (!isValidName(idFathername)) {
            errorMap.put(binding.idFathername, "Name should not contain numbers.");
        } else {
            binding.idFathername.setError(null); // Clear error
        }


        if (TextUtils.isEmpty(idSurname)) {
            errorMap.put(binding.idSurname, "Surname is required.");
        } else if (!isValidName(idSurname)) {
            errorMap.put(binding.idSurname, "Surname should not contain numbers.");
        } else {
            binding.idSurname.setError(null); // Clear error
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
    private boolean isValidName(String name) {
        // Customize this method based on your definition of a valid name
        // For example, you can use a regular expression to check for the presence of numbers
        return !name.matches(".*\\d.*");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void addDrawerLayoutAndMenu() {
        // Initialize drawerLayout before using it
        drawerLayout = findViewById(R.id.dLayout);

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
                startActivity(new Intent(RegisterActivity.this, SacWorldActivity.class));
                // Handle SAKWorld click
                return true;

            } else if (itemId == R.id.menu_Registration) {
                startActivity(new Intent(RegisterActivity.this, RegisterActivity.class));
                // Handle Registration click
                return true;

            } else if (itemId == R.id.menu_logout) {
                startActivity(new Intent(RegisterActivity.this, SignInScreen.class));
                // Handle Logout click
                return true;
            } else if (itemId == R.id.menu_Home) {
                startActivity(new Intent(RegisterActivity.this, DashboardScreen.class));
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
            actionBar.setTitle("Formed");
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