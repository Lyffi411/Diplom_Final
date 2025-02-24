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

public class ChestExerciseAdapter extends RecyclerView.Adapter<ChestExerciseAdapter.ChestExerciseViewHolder> {
    private List<ChestExerciseItem> items;
    private List<ChestExerciseItem> filteredItems;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public ChestExerciseAdapter(List<ChestExerciseItem> items, OnItemClickListener listener) {
        this.items = items;
        this.filteredItems = new ArrayList<>(items);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChestExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chest_exercise, parent, false);
        return new ChestExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChestExerciseViewHolder holder, int position) {
        ChestExerciseItem currentItem = filteredItems.get(position);
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
            for (ChestExerciseItem item : items) {
                if (item.getTitle().toLowerCase().contains(text) ||
                    item.getDescription().toLowerCase().contains(text)) {
                    filteredItems.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public List<ChestExerciseItem> getFilteredItems() {
        return filteredItems;
    }

    class ChestExerciseViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;

        ChestExerciseViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewChestExercise);
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