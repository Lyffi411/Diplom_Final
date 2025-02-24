package com.example.diplom_final.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.R;
import com.example.diplom_final.models.ArmsExerciseItem;

import java.util.List;

public class ArmsExerciseAdapter extends RecyclerView.Adapter<ArmsExerciseAdapter.ArmsExerciseViewHolder> {
    private List<ArmsExerciseItem> exercises;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ArmsExerciseItem item);
    }

    public ArmsExerciseAdapter(List<ArmsExerciseItem> exercises, OnItemClickListener listener) {
        this.exercises = exercises;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArmsExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise, parent, false);
        return new ArmsExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArmsExerciseViewHolder holder, int position) {
        ArmsExerciseItem currentItem = exercises.get(position);
        holder.bind(currentItem);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void updateList(List<ArmsExerciseItem> newList) {
        exercises = newList;
        notifyDataSetChanged();
    }

    class ArmsExerciseViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titleTextView;
        private TextView descriptionTextView;

        public ArmsExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.exerciseImageView);
            titleTextView = itemView.findViewById(R.id.exerciseTitleTextView);
            descriptionTextView = itemView.findViewById(R.id.exerciseDescriptionTextView);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(exercises.get(position));
                }
            });
        }

        public void bind(ArmsExerciseItem item) {
            imageView.setImageResource(item.getImageResource());
            titleTextView.setText(item.getTitle());
            descriptionTextView.setText(item.getDescription());
        }
    }
} 