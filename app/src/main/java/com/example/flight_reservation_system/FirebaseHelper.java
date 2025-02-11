package com.example.flight_reservation_system;

import android.util.Log;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class FirebaseHelper {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public void saveUser(String name, String email, String username, String password) {
        String userId = auth.getCurrentUser().getUid(); // Unique ID from Firebase Auth

        Map<String, Object> user = new HashMap<>();
        user.put("_id", userId); // Firestore automatically generates an ID, but you can use UID
        user.put("name", name);
        user.put("email", email);
        user.put("username", username);
        user.put("password", password); // ⚠️ Store hashed passwords for security

        db.collection("users").document(userId)
                .set(user)
                .addOnSuccessListener(aVoid -> Log.d("Firebase", "User added"))
                .addOnFailureListener(e -> Log.e("Firebase", "Error", e));
    }

}
