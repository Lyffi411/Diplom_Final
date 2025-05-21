package com.example.diplom_final.ui.Stata.Stytis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.diplom_final.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class ExerciseResultAdapter extends ListAdapter<ExerciseResult, ExerciseResultAdapter.ResultViewHolder> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());

    private static final DiffUtil.ItemCallback<ExerciseResult> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ExerciseResult>() {
                @Override
                public boolean areItemsTheSame(@NonNull ExerciseResult oldItem, @NonNull ExerciseResult newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull ExerciseResult oldItem, @NonNull ExerciseResult newItem) {
                    // Сравниваем все значащие поля
                    return oldItem.getResult() == newItem.getResult() && // Используем getResult()
                           oldItem.getTimestamp() == newItem.getTimestamp() &&
                           Objects.equals(oldItem.getExerciseType(), newItem.getExerciseType()) &&
                           oldItem.getWeight() == newItem.getWeight() && // Добавим сравнение новых полей
                           oldItem.getReps() == newItem.getReps();
                }
            };

    public ExerciseResultAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise_result, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        ExerciseResult result = getItem(position);
        if (result != null) {
            holder.exerciseNameView.setText(getExerciseName(result.getExerciseType()));
            holder.resultView.setText(String.format(Locale.getDefault(), "%.1f кг", result.getResult()));
            holder.dateView.setText(formatTimestamp(result.getTimestamp()));
            
            // Отображаем вес и повторения, если они есть (для функции "Лучший подход")
            if (result.getWeight() > 0 || result.getReps() > 0) {
                String weightRepsText = String.format(Locale.getDefault(), "Подход: %.1f кг x %d", result.getWeight(), result.getReps());
                holder.resultView.setText(String.format(Locale.getDefault(), "1ПМ: %.1f кг (Подход: %.1f кг x %d)", result.getResult(), result.getWeight(), result.getReps()));
            }
        }
    }

    private String getExerciseName(String type) {
        if (type == null) return "Неизвестно";
        switch (type) {
            case "bench_press": return "Жим лежа";
            case "deadlift": return "Становая тяга";
            case "squat": return "Присед";
            // Добавьте другие упражнения, если они есть
            default: return type; // Возвращаем как есть, если не найдено
        }
    }

    private String formatTimestamp(long timestamp) {
        Date date = new Date(timestamp);
        return dateFormat.format(date);
    }

    public static class ResultViewHolder extends RecyclerView.ViewHolder {
        TextView exerciseNameView;
        TextView resultView;
        TextView dateView;

        public ResultViewHolder(View itemView) {
            super(itemView);
            exerciseNameView = itemView.findViewById(R.id.exerciseNameView);
            resultView = itemView.findViewById(R.id.resultView);
            dateView = itemView.findViewById(R.id.dateView);
        }
    }
} 