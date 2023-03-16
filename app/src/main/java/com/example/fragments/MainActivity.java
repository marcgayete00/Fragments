package com.example.fragments;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    //private MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_maps:
                        Map mapFragment = new Map();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mapFragment).commit();
                        return true;
                    case R.id.menu_weather:
                        Weather weatherFragment = new Weather(/*mainActivity*/);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, weatherFragment).commit();
                        // Acción al seleccionar el elemento Weather
                        return true;
                    default:
                        return false;
                }
            }
        });

    }
}