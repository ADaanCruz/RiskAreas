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

import com.example.riskareas.ListMunicipalitiesAdapter;
import com.example.riskareas.R;
import com.example.riskareas.db.SQLite;
import com.example.riskareas.model.Municipality;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class MunicipalityFragment extends Fragment {

    private String all = "all";
    private String strIgecem = "igecem";
    private String search = strIgecem;
    private ArrayList<Municipality> lista = new ArrayList<>();
    private boolean admin;

    private SQLite sqLite;
    private TextView informacion;
    private ProgressBar progreso;

    private ToggleButton buscar;
    private TextView txtRango;
    private Spinner rango;
    private EditText igecem;
    private FloatingActionButton nuevo;
    private ListView municipios;

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
        nuevo = view.findViewById(R.id.municipalities_actionButton);

        informacion = view.findViewById(R.id.municipalities_information);
        progreso = view.findViewById(R.id.municipalities_progress);
        municipios = view.findViewById(R.id.municipalities_list);

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
        Cursor cursor = sqLite.getMunicipalities();
        lista = sqLite.getMunicipalities(cursor);
        sqLite.close();

        if (lista.size() == 0) {
            progress("not_found");
        } else {
            municipios.setAdapter(new ListMunicipalitiesAdapter(view.getContext(), lista, admin));
        }

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

        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateEditMunicipalityActivity.class);
                intent.putExtra("add", true);
                startActivity(intent);
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
