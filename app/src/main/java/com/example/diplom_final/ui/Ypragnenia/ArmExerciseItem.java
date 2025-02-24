package com.example.diplom_final.ui.Ypragnenia;
import android.os.Bundle;
import java.util.List;

public class ArmExerciseItem {
    private int id;
    private String title;
    private String description;
    private String technique;
    private String sets;
    private String contraindications;
    private List<String> alternatives;
    private String videoUrl;
    private int imageResourceId;
    private Bundle extraData;

    public ArmExerciseItem(int id, String title, String description, String technique,
                          String sets, String contraindications, List<String> alternatives,
                          String videoUrl, int imageResourceId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.technique = technique;
        this.sets = sets;
        this.contraindications = contraindications;
        this.alternatives = alternatives;
        this.videoUrl = videoUrl;
        this.imageResourceId = imageResourceId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTechnique() {
        return technique;
    }

    public String getSets() {
        return sets;
    }

    public String getContraindications() {
        return contraindications;
    }

    public List<String> getAlternatives() {
        return alternatives;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setExtraData(Bundle extraData) {
        this.extraData = extraData;
    }

    public Bundle getExtraData() {
        return extraData;
    }
} 