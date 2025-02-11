package com.example.flight_reservation_system;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.flight_reservation_system.Fragment.DocumentFragment;
import com.example.flight_reservation_system.Fragment.ExploreFragment;
import com.example.flight_reservation_system.Fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainWindowActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Fragment exploreFragment = new ExploreFragment();
    private Fragment documentFragment = new DocumentFragment();
    private Fragment profileFragment = new ProfileFragment();
    private Fragment activeFragment = exploreFragment;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);

        bottomNavigationView = findViewById(R.id.bottom_nav);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container, profileFragment, "3").hide(profileFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_container, documentFragment, "2").hide(documentFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_container, exploreFragment, "1").commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            if (item.getItemId() == R.id.nav_explore) {
                transaction.hide(activeFragment).show(exploreFragment).commit();
                activeFragment = exploreFragment;
            } else if (item.getItemId() == R.id.nav_saved) {
                transaction.hide(activeFragment).show(documentFragment).commit();
                activeFragment = documentFragment;
            } else if (item.getItemId() == R.id.nav_profile) {
                transaction.hide(activeFragment).show(profileFragment).commit();
                activeFragment = profileFragment;
            }
            return true;
        });
    }
}