package com.example.diplom_final.ui.Stata.Stytis;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "exercise_results")
public class ExerciseResult {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "exercise_type")
    public String exerciseType; // "bench_press", "deadlift", "squat"

    @ColumnInfo(name = "result")
    public double result;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    // Конструктор по умолчанию для Room
    public ExerciseResult() {
    }

    // Конструктор с параметрами
    public ExerciseResult(String exerciseType, double result) {
        this.exerciseType = exerciseType;
        this.result = result;
        this.timestamp = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public double getValue() {
        return result;
    }

    public void setValue(double result) {
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
} 