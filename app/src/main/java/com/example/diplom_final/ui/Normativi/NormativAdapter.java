package com.example.diplom_final.ui.Normativi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class NormativAdapter extends RecyclerView.Adapter<NormativAdapter.ViewHolder> {
    private final List<NormativItem> allItems;
    private List<NormativItem> filteredItems;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int navigationId);
    }

    public NormativAdapter(List<NormativItem> items, OnItemClickListener listener) {
        this.allItems = items;
        this.filteredItems = new ArrayList<>(items);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_normativ, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NormativItem item = filteredItems.get(position);
        
        Glide.with(holder.imageButton.getContext())
                .load(item.getImageResource())
                .into(holder.imageButton);
                
        holder.titleTextView.setText(item.getType());
        holder.descriptionTextView.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButton;
        TextView titleTextView;
        TextView descriptionTextView;

        ViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.imageButton);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position);
                }
            });
        }
    }

    public void filter(String query) {
        filteredItems.clear();
        if (query.isEmpty()) {
            filteredItems.addAll(allItems);
        } else {
            String lowerCaseQuery = query.toLowerCase().trim();
            for (NormativItem item : allItems) {
                if (item.getType().toLowerCase().contains(lowerCaseQuery)) {
                    filteredItems.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public List<NormativItem> getFilteredItems() {
        return filteredItems;
    }
} 