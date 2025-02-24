package com.example.diplom_final.ui.Ypragnenia;

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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.R;
import com.example.diplom_final.databinding.FragmentAbsExerciseDetailBinding;

import java.util.ArrayList;
import java.util.List;

public class AbsExerciseDetailFragment extends Fragment {
    private FragmentAbsExerciseDetailBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAbsExerciseDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            String title = getArguments().getString("exerciseTitle");
            String description = getArguments().getString("exerciseDescription");
            int imageResId = getArguments().getInt("exerciseImage");
            String technique = getArguments().getString("technique");
            String videoUrl = getArguments().getString("videoUrl");
            String sets = getArguments().getString("sets");
            String contraindications = getArguments().getString("contraindications");
            ArrayList<String> alternatives = getArguments().getStringArrayList("alternatives");

            binding.textViewExerciseTitle.setText(title);
            binding.textViewExerciseDescription.setText(description);
            binding.imageViewExercise.setImageResource(imageResId);
            binding.textViewTechnique.setText(technique);
            binding.textViewSets.setText(sets);
            binding.textViewContraindications.setText(contraindications);

            if (alternatives != null && !alternatives.isEmpty()) {
                binding.textViewAlternatives.setText(TextUtils.join("\n• ", alternatives));
            }

            binding.buttonWatchVideo.setOnClickListener(v -> {
                if (videoUrl != null && !videoUrl.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
                    startActivity(intent);
                }
            });
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
} 