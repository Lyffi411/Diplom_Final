package com.example.diplom_final.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.R;
import com.example.diplom_final.model.ShoulderExerciseItem;

import java.util.List;

public class ShoulderExerciseAdapter extends RecyclerView.Adapter<ShoulderExerciseAdapter.ShoulderExerciseViewHolder> {
    private List<ShoulderExerciseItem> exercises;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ShoulderExerciseItem exercise);
    }

    public ShoulderExerciseAdapter(List<ShoulderExerciseItem> exercises, OnItemClickListener listener) {
        this.exercises = exercises;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ShoulderExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chest_exercise, parent, false);
        return new ShoulderExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoulderExerciseViewHolder holder, int position) {
        holder.bind(exercises.get(position));
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void updateList(List<ShoulderExerciseItem> newList) {
        exercises = newList;
        notifyDataSetChanged();
    }

    class ShoulderExerciseViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewExercise;
        private TextView textViewTitle;
        private TextView textViewDescription;

        public ShoulderExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewExercise = itemView.findViewById(R.id.imageViewChestExercise);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(exercises.get(position));
                }
            });
        }

        public void bind(ShoulderExerciseItem exercise) {
            imageViewExercise.setImageResource(exercise.getImageResId());
            textViewTitle.setText(exercise.getTitle());
            textViewDescription.setText(exercise.getDescription());
        }
    }
} 