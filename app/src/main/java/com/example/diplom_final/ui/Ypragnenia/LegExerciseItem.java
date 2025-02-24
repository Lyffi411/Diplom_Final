package com.example.diplom_final.ui.Ypragnenia;

public class LegExerciseItem {
    private final String title;
    private final String description;
    private final int imageResourceId;

    public LegExerciseItem(String title, String description, int imageResourceId) {
        this.title = title;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getImageResourceId() { return imageResourceId; }
} 