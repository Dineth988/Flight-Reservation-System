package com.example.flight_reservation_system;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CarRentalCollapsedView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_car_rental_collapsed_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etCollapsedPickup = findViewById(R.id.etCollapsedPickup);
        ImageView btnEdit = findViewById(R.id.btnEdit);

        String pickupLocation = getIntent().getStringExtra("pickup_location");
        if (pickupLocation != null) {
            etCollapsedPickup.setText(pickupLocation);
        }

        btnEdit.setOnClickListener(view -> {
            Intent intent = new Intent(CarRentalCollapsedView.this, CarRental.class);
            startActivity(intent);
        });
    }
}