package com.example.diplom_final.ui.Ypragnenia;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;


public class ButtExerciseAdapter extends RecyclerView.Adapter<ButtExerciseAdapter.ButtExerciseViewHolder> {
    private List<ButtExerciseItem> exerciseList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ButtExerciseItem exercise);
    }

    public ButtExerciseAdapter(List<ButtExerciseItem> exerciseList, OnItemClickListener listener) {
        this.exerciseList = exerciseList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ButtExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise, parent, false);
        return new ButtExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ButtExerciseViewHolder holder, int position) {
        ButtExerciseItem exercise = exerciseList.get(position);
        holder.bind(exercise, listener);
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public void filterList(List<ButtExerciseItem> filteredList) {
        exerciseList = filteredList;
        notifyDataSetChanged();
    }

    static class ButtExerciseViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private ImageView imageView;
        private MaterialCardView cardView;

        public ButtExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.exerciseTitleTextView);
            descriptionTextView = itemView.findViewById(R.id.exerciseDescriptionTextView);
            imageView = itemView.findViewById(R.id.exerciseImageView);
            cardView = itemView.findViewById(R.id.exerciseCardView);
        }

        public void bind(ButtExerciseItem exercise, OnItemClickListener listener) {
            titleTextView.setText(exercise.getTitle());
            descriptionTextView.setText(exercise.getDescription());
            imageView.setImageResource(exercise.getImageResourceId());
            
            cardView.setOnClickListener(v -> listener.onItemClick(exercise));
        }
    }
} 