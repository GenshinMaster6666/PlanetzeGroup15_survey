package com.b07group15.plantze;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class ConsumptionPrompt1Fragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_collection_consumption_prompt1, container, false);

        Button nextButton = view.findViewById(R.id.button2);
        nextButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_consumptionPrompt1Fragment_to_consumptionPrompt2Fragment);
        });

        Button backButton = view.findViewById(R.id.button1);
        backButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });

        return view;
    }
}