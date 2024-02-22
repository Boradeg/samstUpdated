package com.example.samst;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.samst.Dashboard.DashboardScreen;
import com.example.samst.Registration.Fragment1;
import com.example.samst.Registration.RegisterTabActivity;
import com.example.samst.SacWorld.SacWorldActivity;
import com.example.samst.databinding.ActivitySignUpScreenBinding;
import com.example.samst.stateCountryCityApi.ApiService;
import com.example.samst.stateCountryCityApi.CityData;
import com.example.samst.stateCountryCityApi.CountryRequestBody;
import com.example.samst.stateCountryCityApi.Data;
import com.example.samst.stateCountryCityApi.ShowStatesResponse;
import com.example.samst.stateCountryCityApi.State;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SignUpScreen extends AppCompatActivity {

    private ActivitySignUpScreenBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize View Binding
        binding = ActivitySignUpScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Hide ActionBar
        Objects.requireNonNull(getSupportActionBar()).hide();
        // Access views directly using the generated binding class
        popularCitySpinner2("India","Maharashtra");
        populateStateSpinner("India");
        String[] gender = getResources().getStringArray(R.array.Gender);
        ArrayAdapter<String> arrayAdapter_gender = new ArrayAdapter<>(SignUpScreen.this, R.layout.drop_down_layout,gender);
         binding.gender2.setAdapter(arrayAdapter_gender);
        String[] blood_group = getResources().getStringArray(R.array.Blood_Group);
        ArrayAdapter<String> arrayAdapter_blood_group = new ArrayAdapter<>(SignUpScreen.this, R.layout.drop_down_layout,blood_group);
        binding.idBloodGroupReg.setAdapter(arrayAdapter_blood_group);
        String[] looking_for = getResources().getStringArray(R.array.Looking_for);
        ArrayAdapter<String> arrayAdapter_looking_for = new ArrayAdapter<>(SignUpScreen.this, R.layout.drop_down_layout,looking_for);
        binding.idLookingForReg.setAdapter(arrayAdapter_looking_for);
        String[] cast = getResources().getStringArray(R.array.Cast);
        ArrayAdapter<String> arrayAdapter_cast = new ArrayAdapter<>(SignUpScreen.this, R.layout.drop_down_layout,cast);
        binding.idSelectCastReg.setAdapter(arrayAdapter_cast);
        String[] mariatal_status = getResources().getStringArray(R.array.Marial_status);
        ArrayAdapter<String> arrayAdapter_mariatal_status = new ArrayAdapter<>(SignUpScreen.this, R.layout.drop_down_layout,mariatal_status);
        binding.idSelectCastReg.setAdapter(arrayAdapter_mariatal_status);
        String[] language = getResources().getStringArray(R.array.Language);
        ArrayAdapter<String> arrayAdapter_language = new ArrayAdapter<>(SignUpScreen.this, R.layout.drop_down_layout,language);
        binding.idSelectLanguageReg.setAdapter(arrayAdapter_language);
        binding.dateOfBirth2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(binding.dateOfBirth2);
            }
        });
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpScreen.this, RegisterTabActivity.class));
                finish();
            }
        });
//        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//
//            int textColor = Color.parseColor("#FDFDFD");
//            actionBar.setTitle("Sign Up");
//            actionBar.setLogo(R.drawable.logo);
//
//            actionBar.setDisplayShowTitleEnabled(true);
//            actionBar.setTitle(Html.fromHtml("<font color='" + textColor + "'>" + "Sign Up" + "</font>"));
//        }


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
                                SignUpScreen.this,
                                R.layout.drop_down_layout,
                                cityData.getData()
                        );
                        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.idDistrict.setAdapter(adapter);
                    }
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<CityData> call, Throwable t) {
                Toast.makeText(SignUpScreen.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
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
                                SignUpScreen.this,
                                R.layout.drop_down_layout
                        );
                        for (State state : states) {
                            adapter.add(state.getName());
                        }
                        // adapter.setDropDownViewResource(R.layout.drop_down_layout);
                        binding.idState.setAdapter(adapter);
                    }
                } else {
                    Toast.makeText(SignUpScreen.this, "Failed to fetch states data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ShowStatesResponse> call, Throwable t) {
                Toast.makeText(SignUpScreen.this, "Error occurred while fetching states data", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
