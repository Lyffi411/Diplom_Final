package com.example.diplom_final.ui.Ypragnenia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.diplom_final.R;
import java.util.List;

public class ForearmExerciseAdapter extends RecyclerView.Adapter<ForearmExerciseAdapter.ExerciseViewHolder> {
    private List<ForearmExerciseItem> exercises;
    private OnItemClickListener listener;

    public ForearmExerciseAdapter(List<ForearmExerciseItem> exercises, OnItemClickListener listener) {
        this.exercises = exercises;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        ForearmExerciseItem exercise = exercises.get(position);
        holder.imageView.setImageResource(exercise.getImageResourceId());
        
        switch (position) {
            case 0:
                holder.titleTextView.setText("Подъём груза на вращающейся балке (разгибатели)");
                holder.descriptionTextView.setText("Эффективное упражнение для развития разгибателей предплечья. Укрепляет мышцы, участвующие в разгибании запястья.");
                break;
            case 1:
                holder.titleTextView.setText("Подъём груза на вращающейся балке (сгибатели)");
                holder.descriptionTextView.setText("Изолированное упражнение для сгибателей предплечья. Улучшает силу хвата и развивает мышцы внутренней стороны предплечья.");
                break;
            case 2:
                holder.titleTextView.setText("Разгибание кисти в кроссовере стоя");
                holder.descriptionTextView.setText("Упражнение для изолированной работы разгибателей запястья. Помогает улучшить контроль и силу кисти.");
                break;
            case 3:
                holder.titleTextView.setText("Разгибание кисти в нижнем блоке (локоть под углом 90°)");
                holder.descriptionTextView.setText("Точечная проработка разгибателей запястья с фиксированным положением локтя для максимальной изоляции.");
                break;
            case 4:
                holder.titleTextView.setText("Разгибание кисти с гантелью в упоре");
                holder.descriptionTextView.setText("Базовое упражнение для укрепления разгибателей запястья. Хорошо подходит для начинающих.");
                break;
            case 5:
                holder.titleTextView.setText("Разгибание кисти со штангой");
                holder.descriptionTextView.setText("Классическое упражнение для развития силы и выносливости разгибателей предплечья с использованием штанги.");
                break;
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(exercise);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.exerciseImageView);
            titleTextView = itemView.findViewById(R.id.exerciseTitleTextView);
            descriptionTextView = itemView.findViewById(R.id.exerciseDescriptionTextView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ForearmExerciseItem exercise);
    }
} 