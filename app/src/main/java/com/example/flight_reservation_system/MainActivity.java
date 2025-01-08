package com.example.flight_reservation_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    EditText editTextUsername,editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void onLogin(View view){
        editTextUsername = (EditText)findViewById(R.id.editTextUername2);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword2);

        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if(databaseHelper.verifyUser(username,password)){
            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
            // Navigate to the profile
            Intent intent = new Intent(this, Profile_Activity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Invalid username or password!", Toast.LENGTH_SHORT).show();
        }
    }
    public void onSignUp(View view){
        Intent intent = new Intent(this, sign_up_activity.class);
        startActivity(intent);
    }
}