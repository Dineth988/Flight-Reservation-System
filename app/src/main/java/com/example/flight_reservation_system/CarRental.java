package com.example.flight_reservation_system;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CarRental extends AppCompatActivity {

    private EditText etPickupLocation;
    private TextView tvPickupDate;
    private TextView tvDropOffDate;
    private List<LocationClass> locationList;
    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_car_rental);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvPickupDate = findViewById(R.id.tvPickupDate);
        tvDropOffDate = findViewById(R.id.tvDropOffDate);

        tvPickupDate.setOnClickListener(view -> showDatePickerDialog(true));
        tvDropOffDate.setOnClickListener(view -> showDatePickerDialog(false));

        etPickupLocation = findViewById(R.id.etPickupLocation);
        Button btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(view -> {
            String pickupLocation = etPickupLocation.getText().toString();
            Intent intent = new Intent(CarRental.this, CarRentalCollapsedView.class);
            intent.putExtra("pickup_location", pickupLocation);
            startActivity(intent);
        });

        etPickupLocation.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(CarRental.this);
            View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
            bottomSheetDialog.setContentView(bottomSheetView);

            RecyclerView recyclerView = bottomSheetView.findViewById(R.id.locationRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(CarRental.this));

            locationList = new ArrayList<>();
            LocationAdapter locationAdapter = new LocationAdapter(CarRental.this, locationList);
            recyclerView.setAdapter(locationAdapter);

            loadLocations(locationAdapter, bottomSheetDialog);
            bottomSheetDialog.show();
        });

        db = FirebaseFirestore.getInstance();
        Log.d("Firebase", "Firestore instance: " + db);
    }

    private void showDatePickerDialog(boolean isPickup) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    if (isPickup) {
                        tvPickupDate.setText(selectedDate);
                    } else {
                        tvDropOffDate.setText(selectedDate);
                    }
                },
                year, month, day
        );

        datePickerDialog.show();
    }

    private void loadLocations(LocationAdapter locationAdapter, BottomSheetDialog bottomSheetDialog) {
        db.collection("locations").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                locationList.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String name = document.getString("name");
                    String address = document.getString("locatedAt");
                    locationList.add(new LocationClass(name, address));
                }
                locationAdapter.notifyDataSetChanged();
            } else {
                Log.e("Firestore", "Error getting documents.", task.getException());
            }
        });
    }
}
