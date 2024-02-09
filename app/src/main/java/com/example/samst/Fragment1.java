package com.example.samst;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.samst.databinding.Fragment1Binding;
import com.example.samst.databinding.Fragment2Binding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Fragment1 extends Fragment {

    private EditText idAge;
    private Fragment1Binding binding2; // Declare binding variable

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout using view binding
        binding2 = Fragment1Binding.inflate(inflater, container, false);
        View rootView = binding2.getRoot(); // Get the root view from the binding
        setDropDownValues();
        // Check if rootView is not null before accessing its children views
        if (rootView != null) {
            idAge = rootView.findViewById(R.id.id_age);
            // Initialize more EditText fields similarly

            Button buttonInFragment1 = rootView.findViewById(R.id.submitButton);
//
            buttonInFragment1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validateDropDownField(rootView);
                    validateFields();

                    // Switch to Fragment2 when the button is clicked
//                RegisterTabActivity activity = (RegisterTabActivity) getActivity();
//                if (activity != null) {
//                    activity.switchToFragment2();
//                }
                }
            });
        }

        return rootView;
    }


    private void setDropDownValues() {
        String[] Looking_for = getResources().getStringArray(R.array.Looking_for);
        String[] Gender = getResources().getStringArray(R.array.Gender);
        String[] Blood_Group = getResources().getStringArray(R.array.Blood_Group);
        String[] Service_buisness_detail = getResources().getStringArray(R.array.Service_buisness_detail);
        String[] Height_feet = getResources().getStringArray(R.array.Height_feet);
        String[] Height_inch = getResources().getStringArray(R.array.Height_feet);
        String[] Complexion = getResources().getStringArray(R.array.Complexion);
        String[] Spectacles = getResources().getStringArray(R.array.Spectacles);
        String[] lens = getResources().getStringArray(R.array.lens);
        String[] service_type = getResources().getStringArray(R.array.service_type);
        String[] charan = getResources().getStringArray(R.array.charan);
        String[] nadi = getResources().getStringArray(R.array.Nadi);
        String[] Believe_horoscope = getResources().getStringArray(R.array.Believe_horoscope);
        String[] Mangal = getResources().getStringArray(R.array.Mangal);
        ArrayAdapter<String> arrayAdapter_gender = new ArrayAdapter<>(requireContext(), R.layout.drop_down_layout,Gender);
        binding2.idGender.setAdapter(arrayAdapter_gender);

        ArrayAdapter<String> arrayAdapter_looking_for = new ArrayAdapter<>(requireContext(), R.layout.drop_down_layout,Looking_for);
        binding2.idLookingFor.setAdapter(arrayAdapter_looking_for);

        ArrayAdapter<String> arrayAdapter_blood_group = new ArrayAdapter<>(requireContext(), R.layout.drop_down_layout,Blood_Group);
        binding2.idBloodGroup.setAdapter(arrayAdapter_blood_group);

        ArrayAdapter<String> arrayAdapter_service_type = new ArrayAdapter<>(requireContext(), R.layout.drop_down_layout,service_type);
        binding2.idServiceType.setAdapter(arrayAdapter_service_type);

        ArrayAdapter<String> arrayAdapter_height_feet = new ArrayAdapter<>(requireContext(), R.layout.drop_down_layout, Height_feet);
        binding2.idHeightInFeet.setAdapter(arrayAdapter_height_feet);

        ArrayAdapter<String> arrayAdapter_height_inch = new ArrayAdapter<>(requireContext(), R.layout.drop_down_layout, Height_inch);
        binding2.idHeightInInch.setAdapter(arrayAdapter_height_inch);

        ArrayAdapter<String> arrayAdapter_complexion = new ArrayAdapter<>(requireContext(), R.layout.drop_down_layout,Complexion);
        binding2.idComplexion.setAdapter(arrayAdapter_complexion);

        ArrayAdapter<String> arrayAdapter_spectacles = new ArrayAdapter<>(requireContext(), R.layout.drop_down_layout, Spectacles);
        binding2.idSpectacles.setAdapter(arrayAdapter_spectacles);

        ArrayAdapter<String> arrayAdapter_lens = new ArrayAdapter<>(requireContext(), R.layout.drop_down_layout, lens);
        binding2.idLens.setAdapter(arrayAdapter_lens);

        ArrayAdapter<String> arrayAdapter_charan = new ArrayAdapter<>(requireContext(), R.layout.drop_down_layout, charan);
        binding2.idCharan.setAdapter(arrayAdapter_charan);

        ArrayAdapter<String> arrayAdapter_nadi = new ArrayAdapter<>(requireContext(), R.layout.drop_down_layout,nadi);
        binding2.idNadi.setAdapter(arrayAdapter_nadi);

        ArrayAdapter<String> arrayAdapter_believe_horoscope = new ArrayAdapter<>(requireContext(), R.layout.drop_down_layout, Believe_horoscope);
        binding2.idBelieveHoroscope.setAdapter(arrayAdapter_believe_horoscope);

        ArrayAdapter<String> arrayAdapter_mangal = new ArrayAdapter<>(requireContext(), R.layout.drop_down_layout, Mangal);
        binding2.idMangal.setAdapter(arrayAdapter_mangal);


    }

    private void validateDropDownField(View rootView) {
        int[] autoCompleteTextViewIds = {R.id.id_gender, R.id.id_dateOfBirth, R.id.id_lookingFor, R.id.id_country, R.id.id_city, R.id.id_state, R.id.id_blood_group, R.id.id_gender, R.id.id_service_detail, R.id.id_height_in_feet, R.id.id_height_in_inch, R.id.id_complexion, R.id.id_spectacles, R.id.id_lens, R.id.id_service_type, R.id.id_nakshtra, R.id.id_charan, R.id.id_yoni, R.id.id_rashi, R.id.id_nadi, R.id.id_gotra, R.id.id_believe_horoscope, R.id.id_mangal, R.id.id_swami};

        int[] textInputLayoutIds = {R.id.genderLayout, R.id.date_of_birth_layout, R.id.looking_for_layout, R.id.id_country_layout, R.id.city_layout, R.id.state_layout, R.id.blood_group_layout, R.id.genderLayout, R.id.id_service_detail_layout, R.id.id_height_in_feet_layout, R.id.id_height_in_inch_layout, R.id.id_complexion_layout, R.id.id_spectacles_layout, R.id.id_lens_layout, R.id.id_service_type_layout, R.id.id_nakshtra_layout, R.id.id_charan_layout, R.id.id_yoni_layout, R.id.id_rashi_layout, R.id.id_nadi_layout, R.id.id_gotra_layout, R.id.id_believe_horoscope_layout, R.id.id_mangal_layout, R.id.id_swami_layout};

        for (int i = 0; i < autoCompleteTextViewIds.length; i++) {
            AutoCompleteTextView autoCompleteTextView = rootView.findViewById(autoCompleteTextViewIds[i]);
            TextInputLayout textInputLayout = rootView.findViewById(textInputLayoutIds[i]);

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
        String idFname = binding2.idFname.getText().toString().trim();
        String idFathername = binding2.idFathername.getText().toString().trim();
        String idSurname = binding2.idSurname.getText().toString().trim();
        String idLookingFor = binding2.idLookingFor.getText().toString().trim();
        String idEmail = binding2.idEmail.getText().toString().trim();
        String idNumber = binding2.idNumber.getText().toString().trim();
        String idCountry = binding2.idCountry.getText().toString().trim();
        String idState = binding2.idState.getText().toString().trim();
        String idCity = binding2.idCity.getText().toString().trim();
        String idVillage = Objects.requireNonNull(binding2.idVillage.getText()).toString().trim();
        String idDateOfBirth = binding2.idDateOfBirth.getText().toString().trim();
        String idGender = binding2.idGender.getText().toString().trim();
        String idBloodGroup = binding2.idBloodGroup.getText().toString().trim();
        String idAge = binding2.idAge.getText().toString().trim();
        String idServiceDetail = binding2.idServiceDetail.getText().toString().trim();
        String idDesignation = binding2.idDesignation.getText().toString().trim();
        String idBirthPlace = binding2.idBirthPlace.getText().toString().trim();
        String idBirthTime = binding2.idBirthTime.getText().toString().trim();
        String idHeightInFeet = binding2.idHeightInFeet.getText().toString().trim();
        String idHeightInInch = binding2.idHeightInInch.getText().toString().trim();
        String idComplexion = binding2.idComplexion.getText().toString().trim();
        String idSpectacles = binding2.idSpectacles.getText().toString().trim();
        String idLens = binding2.idLens.getText().toString().trim();
        String idServiceType = binding2.idServiceType.getText().toString().trim();
        String idNakshtra = binding2.idNakshtra.getText().toString().trim();
        String idCharan = binding2.idCharan.getText().toString().trim();
        String idYoni = binding2.idYoni.getText().toString().trim();
        String idRashi = binding2.idRashi.getText().toString().trim();
        String idNadi = binding2.idNadi.getText().toString().trim();
        String idGotra = binding2.idGotra.getText().toString().trim();
        String idSwami = binding2.idSwami.getText().toString().trim();
        String idBelieveHoroscope = binding2.idBelieveHoroscope.getText().toString().trim();
        String idEducation = binding2.idEducation.getText().toString().trim();
        String idMangal = binding2.idMangal.getText().toString().trim();
        String idExpectation = binding2.idExpectation.getText().toString().trim();

        String mobileNumberPattern = "^[6-9][0-9]{9}$";
        String emailPattern = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        Map<View, String> errorMap = new HashMap<>();
        validateFieldOfText(idExpectation, binding2.idExpectation, "Expectation is required.");
        validateFieldOfText(idBirthTime, binding2.idBirthTime, "Birth Time is required.");
        validateFieldOfText(idAge, binding2.idAge, "Age is required.");
        validateFieldOfText(idBirthPlace, binding2.idBirthPlace, "Birth Place is required.");
        validateFieldOfText(idEducation, binding2.idEducation, "Education Place is required.");
        validateFieldOfText(idVillage, binding2.idVillage, "Village is required.");
        validateFieldOfText(idDesignation, binding2.idDesignation, "Designation is required.");
        validateFieldOfText(idEducation, binding2.idEducation, "Education is required.");

        // Validate Mobile Number
        if (TextUtils.isEmpty(idNumber)) {
            errorMap.put(binding2.idNumber, "Mobile Number is required.");
        } else if (!idNumber.matches(mobileNumberPattern)) {
            errorMap.put(binding2.idNumber, "Invalid Mobile Number.");
        } else {
            binding2.idNumber.setError(null); // Clear error
        }

        // Validate Email
        if (TextUtils.isEmpty(idEmail)) {
            errorMap.put(binding2.idEmail, "Email is required.");
        } else if (!idEmail.matches(emailPattern)) {
            errorMap.put(binding2.idEmail, "Invalid Email Address.");
        } else {
            binding2.idEmail.setError(null); // Clear error
        }

        //Map<TextInputLayout, String> errorMap = new HashMap<>();
        if (TextUtils.isEmpty(idFname)) {
            errorMap.put(binding2.idFname, "First Name is required.");
        } else {
            binding2.idFname.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(idFathername)) {
            errorMap.put(binding2.idFathername, "Name is required.");
        } else if (!isValidName(idFathername)) {
            errorMap.put(binding2.idFathername, "Name should not contain numbers.");
        } else {
            binding2.idFathername.setError(null); // Clear error
        }


        if (TextUtils.isEmpty(idSurname)) {
            errorMap.put(binding2.idSurname, "Surname is required.");
        } else if (!isValidName(idSurname)) {
            errorMap.put(binding2.idSurname, "Surname should not contain numbers.");
        } else {
            binding2.idSurname.setError(null); // Clear error
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


}
