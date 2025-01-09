package com.example.flight_reservation_system;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.UUID;

public class DocumentStorage extends AppCompatActivity {

    private static final int PICK_DOCUMENT_REQUEST = 1;

    private RecyclerView recyclerView;
    private DocumentAdapter documentAdapter;
    private List<Document> documents;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_storage);

        recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton fab = findViewById(R.id.fab);

        dbHelper = new DatabaseHelper(this);
        documents = dbHelper.getAllDocuments();
        documentAdapter = new DocumentAdapter(documents);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(documentAdapter);

        fab.setOnClickListener(view -> openFilePicker());
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");
        startActivityForResult(intent, PICK_DOCUMENT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_DOCUMENT_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri documentUri = data.getData();
            String documentName = "New Document"; // Placeholder, you can fetch the actual name
            String filePath = documentUri.toString();
            String thumbnailPath = filePath; // Placeholder for a thumbnail generator.

            Document newDocument = new Document(UUID.randomUUID().toString(), documentName, filePath, thumbnailPath);
            dbHelper.insertDocument(newDocument);
            documents.add(newDocument);
            documentAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "No document selected", Toast.LENGTH_SHORT).show();
        }
    }
}
