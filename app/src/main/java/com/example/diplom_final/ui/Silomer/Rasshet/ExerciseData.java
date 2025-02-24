package com.example.diplom_final.ui.Silomer.Rasshet;

public class ExerciseData {
    private float weight;
    private int reps;
    private long timestamp;

    // Пустой конструктор нужен для Firebase
    public ExerciseData() {
    }

    public ExerciseData(float weight, int reps) {
        this.weight = weight;
        this.reps = reps;
        this.timestamp = System.currentTimeMillis();
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
} 
