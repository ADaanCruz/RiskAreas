package com.example.riskareas.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.riskareas.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class NavigationActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Fragment fragment = new AreaFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    selectedFragment = fragment;
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_container,
                            selectedFragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    selectedFragment = new MunicipalityFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_container,
                            selectedFragment).commit();
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Objects.requireNonNull(getSupportActionBar()).hide();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        mTextMessage.setVisibility(View.GONE);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(NavigationActivity.this, MainActivity.class));
    }
}
