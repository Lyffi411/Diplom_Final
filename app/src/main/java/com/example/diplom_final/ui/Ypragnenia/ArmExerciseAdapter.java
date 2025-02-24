package com.example.diplom_final.ui.Ypragnenia;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.ui.Ypragnenia.ArmExerciseItem;
import com.google.android.material.card.MaterialCardView;
import com.example.diplom_final.R;
import java.util.List;

public class ArmExerciseAdapter extends RecyclerView.Adapter<ArmExerciseAdapter.ArmExerciseViewHolder> {
    private List<ArmExerciseItem> exerciseList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ArmExerciseItem item);
    }

    public ArmExerciseAdapter(List<ArmExerciseItem> exerciseList, OnItemClickListener listener) {
        this.exerciseList = exerciseList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArmExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise, parent, false);
        return new ArmExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArmExerciseViewHolder holder, int position) {
        ArmExerciseItem exercise = exerciseList.get(position);
        holder.bind(exercise, listener);
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public void filterList(List<ArmExerciseItem> filteredList) {
        exerciseList = filteredList;
        notifyDataSetChanged();
    }

    static class ArmExerciseViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private ImageView imageView;
        private MaterialCardView cardView;

        public ArmExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.exerciseTitleTextView);
            descriptionTextView = itemView.findViewById(R.id.exerciseDescriptionTextView);
            imageView = itemView.findViewById(R.id.exerciseImageView);
            cardView = itemView.findViewById(R.id.exerciseCardView);
        }

        public void bind(ArmExerciseItem exercise, OnItemClickListener listener) {
            titleTextView.setText(exercise.getTitle());
            descriptionTextView.setText(exercise.getDescription());
            imageView.setImageResource(exercise.getImageResourceId());
            
            cardView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(exercise);
                }
            });
        }
    }
} 