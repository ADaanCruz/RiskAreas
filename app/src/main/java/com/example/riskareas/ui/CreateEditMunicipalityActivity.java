package com.example.riskareas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.riskareas.R;
import com.example.riskareas.Validations;
import com.example.riskareas.db.SQLite;
import com.example.riskareas.model.Municipality;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class CreateEditMunicipalityActivity extends AppCompatActivity {

    private TextView titulo;
    private Button agregar;
    private Button guardar;
    private boolean add;

    private Validations validations;

    private EditText igecem;
    private EditText name;
    private EditText significance;
    private EditText header;
    private EditText area;
    private Spinner climes;
    private EditText altitude;
    private EditText latitude;
    private EditText longitude;

    private EditText[] fields;

    private SQLite sqLite;
    private Municipality municipio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_municipality);
        Objects.requireNonNull(getSupportActionBar()).hide();

        titulo = findViewById(R.id.createM_title);
        igecem = findViewById(R.id.createM_igecem);
        name = findViewById(R.id.createM_name);
        significance = findViewById(R.id.createM_significance);
        header = findViewById(R.id.createM_header);
        area = findViewById(R.id.createM_area);
        climes = findViewById(R.id.createM_clime);
        altitude = findViewById(R.id.createM_altitude);
        latitude = findViewById(R.id.createM_latitude);
        longitude = findViewById(R.id.createM_longitude);
        agregar = findViewById(R.id.createM_add);
        guardar = findViewById(R.id.createM_save);

        fields = new EditText[8];
        fields[0] = igecem;
        fields[1] = name;
        fields[2] = significance;
        fields[3] = header;
        fields[4] = area;
        fields[5] = altitude;
        fields[6] = latitude;
        fields[7] = longitude;

//        validations = new Validations(this, fields);

        sqLite = new SQLite(this);

        add = getIntent().getBooleanExtra("add",true);
        if (add) {
            agregar.setVisibility(View.VISIBLE);
            guardar.setVisibility(View.GONE);
        } else {
            titulo.setText(R.string.edit_municipality);
            agregar.setVisibility(View.GONE);
            guardar.setVisibility(View.VISIBLE);
        }

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.climes,
                android.R.layout.simple_spinner_item
        );
        climes.setAdapter(adapter);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLite.open();
                int id = Integer.parseInt(igecem.getText().toString());
                String nombre = name.getText().toString();
                String significado = significance.getText().toString();
                String cabezera = header.getText().toString();
                String superficie = area.getText().toString();
                String clima = climes.getSelectedItem().toString();
                String altitud = altitude.getText().toString();
                String latitud = latitude.getText().toString();
                String longitud = longitude.getText().toString();

                municipio = new Municipality(
                        id,
                        nombre,
                        significado,
                        cabezera,
                        superficie,
                        clima,
                        altitud,
                        latitud,
                        longitud
                );

                if (sqLite.insert(municipio)) {
                    finish();
                } else {
                    Snackbar.make(v,
                            "Error al agregar el municipio.",
                            Snackbar.LENGTH_LONG
                    ).setAction("Action", null).show();
                }
                sqLite.close();
            }
        });

    }
}
