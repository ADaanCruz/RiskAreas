package com.example.riskareas.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.example.riskareas.R;

import java.util.Objects;

public class AreaFragment extends Fragment {

    private String user = "";
    private String risks = "risks";
    private String municipalitites = "municipalities";
    private String search = risks;

    private ToggleButton buscar;
    private Spinner riesgo;
    private EditText igecem;

    public AreaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getString("user");
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_area, container, false);
        // Inflate the layout for this fragment
        buscar = view.findViewById(R.id.areas_button_search);
        riesgo = view.findViewById(R.id.areas_risk);
        igecem = view.findViewById(R.id.areas_igecem);

        show(search);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                Objects.requireNonNull(view.getContext()),
                R.array.risk,
                android.R.layout.simple_spinner_item
        );
        riesgo.setAdapter(adapter);

        buscar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    search = risks;
                } else {
                    search = municipalitites;
                }
                show(search);
            }
        });

        return view;
    }

    private void show(String search) {
        if (search.equals(risks)) {
            riesgo.setVisibility(View.VISIBLE);
            igecem.setVisibility(View.GONE);
        } else if (search.equals(municipalitites)){
            riesgo.setVisibility(View.GONE);
            igecem.setVisibility(View.VISIBLE);
        }
    }
}
