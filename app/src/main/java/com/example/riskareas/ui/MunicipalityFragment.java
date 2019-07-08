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
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.riskareas.R;

import java.util.Objects;

public class MunicipalityFragment extends Fragment {

    private String all = "all";
    private String strIgecem = "igecem";
    private String search = strIgecem;

    private ToggleButton buscar;
    private TextView txtRango;
    private Spinner rango;
    private EditText igecem;


    public MunicipalityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_municipality, container, false);
        // Inflate the layout for this fragment
        buscar = view.findViewById(R.id.municipalities_button_search);
        rango = view.findViewById(R.id.municipalities_range);
        igecem = view.findViewById(R.id.municipalities_igecem);
        txtRango = view.findViewById(R.id.municipalities_order_by);

        show(search);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                Objects.requireNonNull(view.getContext()),
                R.array.alphabet,
                android.R.layout.simple_spinner_item
        );
        rango.setAdapter(adapter);

        buscar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    search = strIgecem;
                } else {
                    search = all;
                }
                show(search);
            }
        });

        return view;
    }

    private void show(String search) {
        if (search.equals(all)) {
            txtRango.setVisibility(View.VISIBLE);
            rango.setVisibility(View.VISIBLE);
            igecem.setVisibility(View.GONE);
        } else if (search.equals(strIgecem)){
            txtRango.setVisibility(View.GONE);
            rango.setVisibility(View.GONE);
            igecem.setVisibility(View.VISIBLE);
        }
    }
}
