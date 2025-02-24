package com.example.diplom_final.ui.Ypragnenia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.R;

import java.util.ArrayList;
import java.util.List;

public class LegExerciseAdapter extends RecyclerView.Adapter<LegExerciseAdapter.LegExerciseViewHolder> {
    private List<LegExerciseItem> items;
    private List<LegExerciseItem> filteredItems;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public LegExerciseAdapter(List<LegExerciseItem> items, OnItemClickListener listener) {
        this.items = items;
        this.filteredItems = new ArrayList<>(items);
        this.listener = listener;
    }

    @NonNull
    @Override
    public LegExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_leg_exercise, parent, false);
        return new LegExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LegExerciseViewHolder holder, int position) {
        LegExerciseItem currentItem = filteredItems.get(position);
        holder.imageView.setImageResource(currentItem.getImageResourceId());
        holder.titleTextView.setText(currentItem.getTitle());
        holder.descriptionTextView.setText(currentItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    public void filter(String text) {
        filteredItems.clear();
        if (text.isEmpty()) {
            filteredItems.addAll(items);
        } else {
            text = text.toLowerCase();
            for (LegExerciseItem item : items) {
                if (item.getTitle().toLowerCase().contains(text) ||
                    item.getDescription().toLowerCase().contains(text)) {
                    filteredItems.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public List<LegExerciseItem> getFilteredItems() {
        return filteredItems;
    }

    class LegExerciseViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;

        LegExerciseViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewLegExercise);
            titleTextView = itemView.findViewById(R.id.textViewTitle);
            descriptionTextView = itemView.findViewById(R.id.textViewDescription);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position);
                }
            });
        }
    }
} 