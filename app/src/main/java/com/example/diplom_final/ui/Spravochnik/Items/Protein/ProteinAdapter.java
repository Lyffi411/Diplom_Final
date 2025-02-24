package com.example.diplom_final.ui.Spravochnik.Items.Protein;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ProteinAdapter extends RecyclerView.Adapter<ProteinAdapter.ViewHolder> {
    private final List<ProteinItem> allItems;
    private List<ProteinItem> filteredItems;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int navigationId);
    }

    public ProteinAdapter(List<ProteinItem> items, OnItemClickListener listener) {
        this.allItems = items;
        this.filteredItems = new ArrayList<>(items);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_protein, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProteinItem item = filteredItems.get(position);
        
        Glide.with(holder.imageView.getContext())
                .load(item.getImageResource())
                .into(holder.imageView);
                
        holder.titleTextView.setText(item.getTitle());
        holder.descriptionTextView.setText(item.getDescription());
        
        holder.itemView.setOnClickListener(v -> 
            listener.onItemClick(item.getNavigationId())
        );
    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }

    public void filter(String query) {
        filteredItems.clear();
        if (query.isEmpty()) {
            filteredItems.addAll(allItems);
        } else {
            String lowerCaseQuery = query.toLowerCase().trim();
            for (ProteinItem item : allItems) {
                if (item.getTitle().toLowerCase().contains(lowerCaseQuery) ||
                    item.getDescription().toLowerCase().contains(lowerCaseQuery)) {
                    filteredItems.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public List<ProteinItem> getFilteredItems() {
        return filteredItems;
    }
} 