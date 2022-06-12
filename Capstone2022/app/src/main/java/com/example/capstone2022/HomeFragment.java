package com.example.capstone2022;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.app.Fragment;

import androidx.annotation.NonNull;

import com.example.capstone2022.api.APIConnector;
import com.example.capstone2022.api.corona.CoronaParser;

public class HomeFragment extends Fragment {

    private TextView population;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getContext() == null) return null;

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        population = view.findViewById(R.id.Population);
        population.setText("로딩중...");

        updatePopulation();

        return view;
    }

    public void updatePopulation() {
        if (getContext() == null) return;

        APIConnector.GET("rest/corona", (jsonObject) -> {
            long addDecide = CoronaParser.parseData(jsonObject).getAddDecide();

            population.setText(String.valueOf(addDecide));
            population.invalidate();
            population.requestLayout();
        });
    }

}
