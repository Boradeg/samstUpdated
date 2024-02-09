package com.example.samst;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment2, container, false);
        Button buttonInFragment2 = rootView.findViewById(R.id.buttonInFragment2);

        // Check if buttonInFragment2 is null to avoid NullPointerException
        if (buttonInFragment2 != null) {
            buttonInFragment2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Add your onClick logic here for Fragment2's button
                }
            });
        }

        return rootView;
    }
}
