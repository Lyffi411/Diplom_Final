package com.example.diplom_final.ui.Spravochnik.Items.Protein;

public class ProteinItem {
    private final String title;
    private final String description;
    private final int imageResource;
    private final int navigationId;

    public ProteinItem(String title, String description, int imageResource, int navigationId) {
        this.title = title;
        this.description = description;
        this.imageResource = imageResource;
        this.navigationId = navigationId;
    }

    public String getTitle() {
        return title;
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