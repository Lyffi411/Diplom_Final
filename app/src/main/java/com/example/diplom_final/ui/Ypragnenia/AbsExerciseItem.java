package com.example.diplom_final.ui.Ypragnenia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.R;

import java.util.ArrayList;
import java.util.List;

public class AbsExerciseItem {
    private int id;
    private String title;
    private String description;
    private int imageResourceId;
    private Bundle extraData;

    public AbsExerciseItem(String title, String description, int imageResourceId) {
        this.title = title;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public void setId(int id) {
        this.id = id;
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