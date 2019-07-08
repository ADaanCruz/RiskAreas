package com.example.riskareas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.riskareas.R;
import com.example.riskareas.Validations;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Validations validations;
    EditText[] fields;

    EditText user, password;
    Button sign_in;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        sign_in = findViewById(R.id.login_sign_in);
        progress = findViewById(R.id.login_progress);
        showProgress(true);

        user = findViewById(R.id.login_user);
        password = findViewById(R.id.login_password);

        fields = new EditText[2];
        fields[0] = user;
        fields[1] = password;

        validations = new Validations(this, fields);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress(true);
                if (validations.areNotEmpty()) {
                    String usuario = user.getText().toString().toLowerCase();
                    String constrasenia = password.getText().toString().toLowerCase();
                    if ((usuario.equals("administrador") && constrasenia.equals("administrador")) ||
                            (usuario.equals("consultor") && constrasenia.equals("consultor"))) {
                        finish();
                        startActivity(new Intent(MainActivity.this, NavigationActivity.class));
                    } else {
                        Snackbar.make(view,
                                "Usuario y/o contraseña incorrectos.",
                                Snackbar.LENGTH_LONG
                        ).setAction("Action", null).show();
                    }
                } else {
                    Snackbar.make(view,
                            "Llena todos los campos requeridos",
                            Snackbar.LENGTH_SHORT
                    ).setAction("Action", null).show();
                }
                showProgress(false);
            }
        });

        showProgress(false);
    }

    public void showProgress(Boolean show) {
        if (show) {
            sign_in.setEnabled(false);
            sign_in.setVisibility(View.GONE);
            progress.setVisibility(View.VISIBLE);
        } else {
            progress.setVisibility(View.GONE);
            sign_in.setVisibility(View.VISIBLE);
            sign_in.setEnabled(true);
        }
    }
}