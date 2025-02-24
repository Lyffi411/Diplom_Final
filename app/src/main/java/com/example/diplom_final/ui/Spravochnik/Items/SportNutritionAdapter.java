package com.example.diplom_final.ui.Spravochnik.Items;

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

public class SportNutritionAdapter extends RecyclerView.Adapter<SportNutritionAdapter.ViewHolder> {
    private final List<SportNutritionItem> allItems;
    private List<SportNutritionItem> filteredItems;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public SportNutritionAdapter(List<SportNutritionItem> items, OnItemClickListener listener) {
        this.allItems = items;
        this.filteredItems = new ArrayList<>(items);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sport_nutrition, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SportNutritionItem item = filteredItems.get(position);
        
        Glide.with(holder.imageView.getContext())
                .load(item.getImageResource())
                .into(holder.imageView);
                
        holder.titleTextView.setText(item.getTitle());
        holder.descriptionTextView.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;

        ViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
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
            for (SportNutritionItem item : allItems) {
                if (item.getTitle().toLowerCase().contains(lowerCaseQuery) ||
                    item.getDescription().toLowerCase().contains(lowerCaseQuery)) {
                    filteredItems.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public List<SportNutritionItem> getFilteredItems() {
        return filteredItems;
    }
} 