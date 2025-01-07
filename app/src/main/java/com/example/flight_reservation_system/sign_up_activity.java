package com.example.flight_reservation_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class sign_up_activity extends AppCompatActivity {

    DatabaseHelper db;
    EditText editTextName,editTextEmail,editTextPassword,editTextUsername;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = new DatabaseHelper(this);

        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        editTextUsername = (EditText)findViewById(R.id.editTextUsername);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        signUp();
    }
    public void signUp(){
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInsertd = db.addUsers(editTextName.getText().toString(),editTextEmail.getText().toString(),
                             editTextUsername.getText().toString(),editTextPassword.getText().toString());
                if(isInsertd=true){
                    Toast.makeText(sign_up_activity.this,"User Registered",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(sign_up_activity.this,"User Not Registered",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void onCancel(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}