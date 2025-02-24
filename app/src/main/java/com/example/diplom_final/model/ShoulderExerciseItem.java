package com.example.diplom_final.model;

public class ShoulderExerciseItem {
    private String title;
    private String description;
    private int imageResId;

    public ShoulderExerciseItem(String title, String description, int imageResId) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
} 