package com.example.diplom_final.ui.Normativi;

public class NormativItem {
    private final String type;
    private final String description;
    private final int imageResource;
    private final int navigationId;

    public NormativItem(String type, String description, int imageResource, int navigationId) {
        this.type = type;
        this.description = description;
        this.imageResource = imageResource;
        this.navigationId = navigationId;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getNavigationId() {
        return navigationId;
    }
} 