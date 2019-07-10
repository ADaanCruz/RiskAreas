package com.example.riskareas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.riskareas.R;

import java.util.Objects;

public class CreateEditAreaActivity extends AppCompatActivity {

    private TextView titulo;
    private Spinner igecem;
    private Button agregar;
    private Button guardar;
    private boolean add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_area);
        Objects.requireNonNull(getSupportActionBar()).hide();

        titulo = findViewById(R.id.createA_title);
        igecem = findViewById(R.id.createA_igecem);
        agregar = findViewById(R.id.createA_add);
        guardar = findViewById(R.id.createA_save);

        add = getIntent().getBooleanExtra("add",true);
        if (add) {
            agregar.setVisibility(View.VISIBLE);
            guardar.setVisibility(View.GONE);
        } else {
            titulo.setText(R.string.edit_area);
            agregar.setVisibility(View.GONE);
            guardar.setVisibility(View.VISIBLE);
        }

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.igecem,
                android.R.layout.simple_spinner_item
        );
        igecem.setAdapter(adapter);
    }
}
