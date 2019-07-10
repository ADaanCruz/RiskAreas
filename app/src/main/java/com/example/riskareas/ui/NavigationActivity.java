package com.example.riskareas.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.riskareas.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;

import java.util.Objects;

public class NavigationActivity extends AppCompatActivity {

    private Fragment fragment = new AreaFragment();
    private Bundle bundle = new Bundle();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new AreaFragment();
                    break;
                case R.id.navigation_dashboard:
                    fragment = new MunicipalityFragment();
                    break;
                case R.id.navigation_notifications:
                    fragment = null;
                    break;
            }
            if (fragment != null) {
                loadBundle(fragment);
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_container,
                        fragment).commit();
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Objects.requireNonNull(getSupportActionBar()).hide();

        loadBundle(fragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_container,
                fragment).commit();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(NavigationActivity.this, MainActivity.class));
    }

    private void loadBundle(Fragment fragment) {
        bundle.putBoolean("admin", getIntent().getBooleanExtra("admin", false));
        fragment.setArguments(bundle);
    }
}