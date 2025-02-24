package com.example.diplom_final.ui.Ypragnenia;
import java.util.List;

public class ChestExerciseItem {
    private String title;
    private String description;
    private int imageResourceId;
    private String technique;
    private String videoUrl;
    private String sets;
    private String contraindications;
    private List<String> alternatives;

    public ChestExerciseItem(String title, String description, int imageResourceId,
                            String technique, String videoUrl, String sets,
                            String contraindications, List<String> alternatives) {
        this.title = title;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.technique = technique;
        this.videoUrl = videoUrl;
        this.sets = sets;
        this.contraindications = contraindications;
        this.alternatives = alternatives;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getImageResourceId() { return imageResourceId; }
    public String getTechnique() { return technique; }
    public String getVideoUrl() { return videoUrl; }
    public String getSets() { return sets; }
    public String getContraindications() { return contraindications; }
    public List<String> getAlternatives() { return alternatives; }
} 