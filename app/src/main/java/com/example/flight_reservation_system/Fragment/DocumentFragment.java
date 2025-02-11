package com.example.flight_reservation_system.Fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.flight_reservation_system.DatabaseHelper;
import com.example.flight_reservation_system.Document;
import com.example.flight_reservation_system.DocumentAdapter;
import com.example.flight_reservation_system.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.UUID;

public class DocumentFragment extends Fragment {

    private static final int PICK_DOCUMENT_REQUEST = 1;

    private RecyclerView recyclerView;
    private DocumentAdapter documentAdapter;
    private List<Document> documents;
    private DatabaseHelper dbHelper;

    public DocumentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_document, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        recyclerView = view.findViewById(R.id.recyclerViewdocument);
        FloatingActionButton fab = view.findViewById(R.id.fab);

        // Initialize database helper
        dbHelper = new DatabaseHelper(getActivity());

        // Fetch documents
        documents = dbHelper.getAllDocuments();
        documentAdapter = new DocumentAdapter(documents);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(documentAdapter);

        // Floating action button click listener
        fab.setOnClickListener(v -> openFilePicker());
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");
        startActivityForResult(intent, PICK_DOCUMENT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_DOCUMENT_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri documentUri = data.getData();
            String documentName = "New Document"; // Placeholder name
            String filePath = documentUri.toString();

            // Create new document object
            Document newDocument = new Document(UUID.randomUUID().toString(), documentName, filePath, filePath);
            dbHelper.insertDocument(newDocument);

            // Update RecyclerView
            documents.add(newDocument);
            documentAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getActivity(), "No document selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh document list when fragment is resumed
        documents.clear();
        documents.addAll(dbHelper.getAllDocuments());
        documentAdapter.notifyDataSetChanged();
    }
}
