package com.example.samst.Registration;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.samst.R;
import com.example.samst.databinding.Fragment2Binding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Fragment2 extends Fragment {

    private Fragment2Binding binding; // Declare binding variable

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout using view binding
        binding = Fragment2Binding.inflate(inflater, container, false);
        View rootView = binding.getRoot(); // Get the root view from the binding

        Button buttonInFragment2 = rootView.findViewById(R.id.submitButton);

        // Check if buttonInFragment2 is null to avoid NullPointerException
        if (buttonInFragment2 != null) {
            binding.submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validateFields();
                    Toast.makeText(getActivity(), "currentIdAge", Toast.LENGTH_SHORT).show();

                    // Add your onClick logic here for Fragment2's button
                }
            });
        }

        return rootView;
    }
    private void validateFieldOfText(String fieldValue, TextInputEditText textInputLayout, String errorMessage) {
        if (TextUtils.isEmpty(fieldValue)) {
            textInputLayout.setError(errorMessage);
        } else {
            textInputLayout.setError(null); // Clear error
        }
    }

    private boolean validateFields() {
        String guardianName = binding.guardianName.getText().toString().trim();
        String guardianFatherName = binding.guardianFatherName.getText().toString().trim();
        String guardianSurname = binding.guardianSurname.getText().toString().trim();
        String guardiaEmail = binding.guardiaEmail.getText().toString().trim();
        String address = binding.address.getText().toString().trim();
        String pincode = binding.pincode.getText().toString().trim();
        String mobileNumber = binding.mobileNumber.getText().toString().trim();
        String numberOfDaughters = binding.numberOfDaughters.getText().toString().trim();
        String numberOfSon = binding.numberOfSon.getText().toString().trim();
        String post = binding.post.getText().toString().trim();
        String monthlyIncome = binding.monthlyIncome.getText().toString().trim();
        String eduDetail = binding.eduDetail.getText().toString().trim();
        String materalUncleName = binding.materalUncleName.getText().toString().trim();


        String mobileNumberPattern = "^[6-9][0-9]{9}$";
        String emailPattern = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        Map<View, String> errorMap = new HashMap<>();
        validateFieldOfText(post, binding.post, "Post is required.");
        validateFieldOfText(materalUncleName, binding.materalUncleName, "Materal Uncle Name is required.");
        validateFieldOfText(address, binding.address, "Address is required.");
        validateFieldOfText(pincode, binding.pincode, "Pincode is required.");
        validateFieldOfText(eduDetail, binding.eduDetail, "Education Detail is required.");
        validateFieldOfText(numberOfDaughters, binding.numberOfDaughters, "Number Of Sons is required.");
        validateFieldOfText(numberOfSon, binding.numberOfSon, "Number Of Sons is required.");
        validateFieldOfText(monthlyIncome, binding.monthlyIncome, "Monthly Income is required.");

        // Validate Mobile Number
        if (TextUtils.isEmpty(mobileNumber)) {
            errorMap.put(binding.mobileNumber, "Mobile Number is required.");
        } else if (!mobileNumber.matches(mobileNumberPattern)) {
            errorMap.put(binding.mobileNumber, "Invalid Mobile Number.");
        } else {
            binding.mobileNumber.setError(null); // Clear error
        }

        // Validate Email
        if (TextUtils.isEmpty(guardiaEmail)) {
            errorMap.put(binding.guardiaEmail, "Email is required.");
        } else if (!guardiaEmail.matches(emailPattern)) {
            errorMap.put(binding.guardiaEmail, "Invalid Email Address.");
        } else {
            binding.guardiaEmail.setError(null); // Clear error
        }

        //Map<TextInputLayout, String> errorMap = new HashMap<>();
        if (TextUtils.isEmpty(guardianName)) {
            errorMap.put(binding.guardianName, "First Name is required.");
        } else {
            binding.guardianName.setError(null); // Clear error
        }

        if (TextUtils.isEmpty(guardianFatherName)) {
            errorMap.put(binding.guardianFatherName, "Name is required.");
        } else if (!isValidName(guardianFatherName)) {
            errorMap.put(binding.guardianFatherName, "Name should not contain numbers.");
        } else {
            binding.guardianFatherName.setError(null); // Clear error
        }


        if (TextUtils.isEmpty(guardianSurname)) {
            errorMap.put(binding.guardianSurname, "Surname is required.");
        } else if (!isValidName(guardianSurname)) {
            errorMap.put(binding.guardianSurname, "Surname should not contain numbers.");
        } else {
            binding.guardianSurname.setError(null); // Clear error
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
