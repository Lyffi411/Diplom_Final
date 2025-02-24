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

public class ExerciseResultAdapter extends ListAdapter<ExerciseResult, ExerciseResultAdapter.ResultViewHolder> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());

    public ExerciseResultAdapter() {
        super(new DiffUtil.ItemCallback<ExerciseResult>() {
            @Override
            public boolean areItemsTheSame(@NonNull ExerciseResult oldItem, @NonNull ExerciseResult newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull ExerciseResult oldItem, @NonNull ExerciseResult newItem) {
                return oldItem.result == newItem.result && oldItem.timestamp == newItem.timestamp;
            }
        });
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
        String exerciseName = getExerciseName(result.exerciseType);
        String dateStr = dateFormat.format(new Date(result.timestamp));
        
        holder.exerciseNameView.setText(exerciseName);
        holder.resultView.setText(String.format("%.1f кг", result.result));
        holder.dateView.setText(dateStr);
    }

    private String getExerciseName(String type) {
        switch (type) {
            case "bench_press": return "Жим лежа";
            case "deadlift": return "Становая тяга";
            case "squat": return "Присед";
            default: return "Неизвестное упражнение";
        }
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