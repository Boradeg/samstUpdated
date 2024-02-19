package com.example.samst.SacWorld;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.samst.Dashboard.DashboardScreen;
import com.example.samst.R;
import com.example.samst.Registration.RegisterTabActivity;
import com.example.samst.SignInScreen;
import com.example.samst.databinding.ActivitySacWorldBinding;
import com.example.samst.stateCountryCityApi.ApiClient;
import com.example.samst.stateCountryCityApi.ApiService;
import com.example.samst.stateCountryCityApi.CityData;
import com.example.samst.stateCountryCityApi.CountryData;
import com.example.samst.stateCountryCityApi.CountryRequestBody;
import com.example.samst.stateCountryCityApi.CountryResponse;
import com.example.samst.stateCountryCityApi.Data;
import com.example.samst.stateCountryCityApi.ShowStatesResponse;
import com.example.samst.stateCountryCityApi.State;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SacWorldActivity extends AppCompatActivity {
    private static final int REQUEST_PICK_IMAGE = 1;
    private static final int REQUEST_CAPTURE_IMAGE = 2;
    private static final int PERMISSION_REQUEST_CODE = 100;
    private CircleImageView imageView;
    private AutoCompleteTextView state1;
    private DrawerLayout drawerLayout;
    private RadioButton radioYourself, radioSomeoneElse;

    private ActivitySacWorldBinding binding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySacWorldBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addDrawerLayoutAndMenu();
        imageView = findViewById(R.id.image_sak_world);
        imageView.setOnClickListener(v -> showOptions());
        radioYourself = findViewById(R.id.id_yourself);
        radioSomeoneElse = findViewById(R.id.id_someone_else);
        // state1 = findViewById(R.id.state2);
        String[] service_buisness = getResources().getStringArray(R.array.Service_buisness_detail);
        ArrayAdapter<String> arrayAdapter_language = new ArrayAdapter<>(SacWorldActivity.this, R.layout.drop_down_layout, service_buisness);
        String[] gender = getResources().getStringArray(R.array.Gender);
        ArrayAdapter<String> arrayAdapter_gender = new ArrayAdapter<>(SacWorldActivity.this, R.layout.drop_down_layout, gender);
        String[] ration_card = getResources().getStringArray(R.array.Ration_card);
        ArrayAdapter<String> arrayAdapter_ration_card = new ArrayAdapter<>(SacWorldActivity.this, R.layout.drop_down_layout, ration_card);
        String[] blood_group = getResources().getStringArray(R.array.Blood_Group);
        ArrayAdapter<String> arrayAdapter_blood_group = new ArrayAdapter<>(SacWorldActivity.this, R.layout.drop_down_layout, blood_group);
        binding.serviceDetail2.setAdapter(arrayAdapter_language);
        //binding.city2.setAdapter(arrayAdapter_gender);
        String[] country_name = getResources().getStringArray(R.array.country);
        ArrayAdapter<String> arrayAdapter_country_name = new ArrayAdapter<>(SacWorldActivity.this, R.layout.drop_down_layout, country_name);
        binding.rationCard2.setAdapter(arrayAdapter_ration_card);
        binding.country2.setAdapter(arrayAdapter_country_name);
        binding.gender2.setAdapter(arrayAdapter_gender);
        binding.bloodGroup2.setAdapter(arrayAdapter_blood_group);

        popularCitySpinner2("India","Maharashtra");
       // populateCountrySpinner();
        populateStateSpinner("India");

        //binding.state2.setAdapter(arrayAdapter_gender);

        radioSomeoneElse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Deselect the first button when the second button is clicked
                radioYourself.setChecked(false);
            }
        });
        radioYourself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Deselect the first button when the second button is clicked
                radioSomeoneElse.setChecked(false);
            }
        });
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDropDownField();
                if (validateFields()) {
                    // Proceed with further actions
                    showToast("Validation successful!");
                } else {
                    showToast("Validation failed. Please check the fields.");
                }
            }
        });


        final AutoCompleteTextView dateOfBirthTextView = findViewById(R.id.dateOfBirth2);
        dateOfBirthTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(dateOfBirthTextView);
            }
        });
    }

    private void validateDropDownField() {
        int[] autoCompleteTextViewIds = {R.id.dateOfBirth2, R.id.gender2, R.id.blood_group2,
                R.id.country2, R.id.state2
                , R.id.city2, R.id.rationCard2, R.id.service_detail2
        }; // Add more if needed
        int[] textInputLayoutIds = {R.id.layout_dateOfBirth2, R.id.layout_gender2, R.id.layout_blood_group2,
                R.id.layout_country2, R.id.layout_state2
                , R.id.layout_city2, R.id.layout_rationCard2, R.id.layout_service_detail2

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

    private void popularCitySpinner2(String country, String state) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://countriesnow.space/api/v0.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<CityData> call = apiService.getCities(country, state);
        call.enqueue(new Callback<CityData>() {
            @Override
            public void onResponse(Call<CityData> call, Response<CityData> response) {
                if (response.isSuccessful()) {
                    CityData cityData = response.body();
                    if (cityData != null) {
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                SacWorldActivity.this,
                                R.layout.drop_down_layout,
                                cityData.getData()
                        );
                        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.city2.setAdapter(adapter);
                    }
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<CityData> call, Throwable t) {
                Toast.makeText(SacWorldActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void populateStateSpinner(String country) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://countriesnow.space/api/v0.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        CountryRequestBody requestBody = new CountryRequestBody(country);
        Call<ShowStatesResponse> call = apiService.getStates(requestBody);

        call.enqueue(new Callback<ShowStatesResponse>() {
            @Override
            public void onResponse(Call<ShowStatesResponse> call, Response<ShowStatesResponse> response) {
                if (response.isSuccessful()) {
                    ShowStatesResponse showStatesResponse = response.body();
                    if (showStatesResponse != null) {
                        Data data = showStatesResponse.getData();
                        List<State> states = data.getStates();
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                SacWorldActivity.this,
                                R.layout.drop_down_layout
                        );
                        for (State state : states) {
                            adapter.add(state.getName());
                        }
                        // adapter.setDropDownViewResource(R.layout.drop_down_layout);
                        binding.state2.setAdapter(adapter);
                    }
                } else {
                    Toast.makeText(SacWorldActivity.this, "Failed to fetch states data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ShowStatesResponse> call, Throwable t) {
                Toast.makeText(SacWorldActivity.this, "Error occurred while fetching states data", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void populateCountrySpinner() {
        ApiService apiService = ApiClient.getApiService();
        Call<CountryResponse> countryCall = apiService.getCountries();
        countryCall.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                if (response.isSuccessful()) {
                    CountryResponse countryResponse = response.body();
                    if (countryResponse != null) {
                        List<CountryData> countries = countryResponse.getData();
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                SacWorldActivity.this,
                                R.layout.drop_down_layout
                        );
                        for (CountryData countryData : countries) {
                            adapter.add(countryData.getCountry());
                        }
                      //  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.country2.setAdapter(adapter);
                    }
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                // Handle failure
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
        String voterIdPattern = "^[a-zA-Z0-9]+$";
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

    private void addDrawerLayoutAndMenu() {
        // Initialize drawerLayout before using it
        drawerLayout = findViewById(R.id.dLayout3);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Set up NavigationView listener
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.menu_SAKWorld) {
                startActivity(new Intent(SacWorldActivity.this, SacWorldActivity.class));
                // Handle SAKWorld click
                return true;

            } else if (itemId == R.id.menu_Registration) {
                startActivity(new Intent(SacWorldActivity.this, RegisterTabActivity.class));
                // Handle Registration click
                return true;
            } else if (itemId == R.id.menu_Home) {
                startActivity(new Intent(SacWorldActivity.this, DashboardScreen.class));
                // Handle Home click
                return true;
            } else if (itemId == R.id.menu_logout) {
                startActivity(new Intent(SacWorldActivity.this, SignInScreen.class));
                // Handle Logout click
                return true;

            } else {
                return false;
            }
        });

        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            int textColor = Color.parseColor("#FDFDFD");
            actionBar.setTitle("SAK WORLD");
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(Html.fromHtml("<font color='" + textColor + "'>" + "SAK WORLD" + "</font>"));
        }
    }

    private void showOptions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Image Source");
        builder.setItems(new CharSequence[]{"Gallery", "Camera"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        openGallery();
                        break;
                    case 1:
                        openCamera();
                        break;
                }
            }
        });
        builder.show();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PICK_IMAGE);
    }

    private void openCamera() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CAPTURE_IMAGE);
        } else {
            Toast.makeText(this, "No camera app found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_PICK_IMAGE && data != null) {
                Uri selectedImageUri = data.getData();
                imageView.setImageURI(selectedImageUri);
            } else if (requestCode == REQUEST_CAPTURE_IMAGE && data != null) {
                Bundle extras = data.getExtras();
                if (extras != null && extras.containsKey("data")) {
                    // Get the captured image and set it to the ImageView
                    imageView.setImageBitmap((android.graphics.Bitmap) extras.get("data"));
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
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
