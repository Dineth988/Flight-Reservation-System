package com.example.flight_reservation_system;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder> {
    private List<Document> documents;

    public DocumentAdapter(List<Document> documents) {
        this.documents = documents;
    }

    @NonNull
    @Override
    public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_document, parent, false);
        return new DocumentViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull DocumentViewHolder holder, int position) {
        Document document = documents.get(position);
        holder.documentName.setText(document.getName());
        Glide.with(holder.itemView.getContext())
                .load(document.getThumbnailPath())
                .into(holder.documentThumbnail);
    }

    @Override
    public int getItemCount() {
        return documents.size();
    }

    static class DocumentViewHolder extends RecyclerView.ViewHolder {
        ImageView documentThumbnail;
        TextView documentName;

        public DocumentViewHolder(@NonNull View itemView) {
            super(itemView);
            documentThumbnail = itemView.findViewById(R.id.documentThumbnail);
            documentName = itemView.findViewById(R.id.documentName);
        }
    }
}