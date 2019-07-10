package com.example.riskareas.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.riskareas.ListAreasAdapter;
import com.example.riskareas.R;
import com.example.riskareas.db.SQLite;
import com.example.riskareas.model.Area;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class AreaFragment extends Fragment {

    private String risks = "risks";
    private String municipalitites = "municipalities";
    private String search = risks;
    private ArrayList<Area> lista = new ArrayList<>();
    private boolean admin;

    private SQLite sqLite;
    private TextView informacion;
    private ProgressBar progreso;

    private ToggleButton buscar;
    private Spinner riesgo;
    private EditText igecem;
    private FloatingActionButton nuevo;
    private ListView areas;

    public AreaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_area, container, false);
        // Inflate the layout for this fragment
        buscar = view.findViewById(R.id.areas_button_search);
        riesgo = view.findViewById(R.id.areas_risk);
        igecem = view.findViewById(R.id.areas_igecem);
        nuevo = view.findViewById(R.id.areas_actionButton);

        informacion = view.findViewById(R.id.areas_information);
        progreso = view.findViewById(R.id.areas_progress);
        areas = view.findViewById(R.id.areas_list);

        progress("search");
        show(search);

        if (getArguments() != null) {
            if (getArguments().getBoolean("admin", false)) {
                admin = true;
                nuevo.show();
            } else {
                admin = false;
                nuevo.hide();
            }
        }

        sqLite = new SQLite(getContext());
        sqLite.open();
        Cursor cursor = sqLite.getAreas();
        lista = sqLite.getAreas(cursor);
        sqLite.close();

        if (lista.size() == 0) {
            progress("not_found");
        } else {
            areas.setAdapter(new ListAreasAdapter(view.getContext(), lista, admin));
        }

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

        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateEditAreaActivity.class);
                intent.putExtra("add", true);
                startActivity(intent);
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

    private void progress(String process) {
        switch (process) {
            case "search":
                informacion.setText(R.string.wait);
                informacion.setVisibility(View.VISIBLE);
                progreso.setVisibility(View.VISIBLE);
                break;
            case "not_found":
                informacion.setText(R.string.not_found);
                informacion.setVisibility(View.VISIBLE);
                progreso.setVisibility(View.GONE);
                break;
            case "found":
                informacion.setVisibility(View.GONE);
                progreso.setVisibility(View.GONE);
        }
    }
}
