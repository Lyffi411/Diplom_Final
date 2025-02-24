package com.example.diplom_final.ui.Ypragnenia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.diplom_final.R;
import com.example.diplom_final.databinding.FragmentArmsExerciseDetailBinding;

import java.util.ArrayList;

public class ArmsExerciseDetailFragment extends Fragment {
    private FragmentArmsExerciseDetailBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentArmsExerciseDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            String title = getArguments().getString("exerciseTitle");
            String description = getArguments().getString("exerciseDescription");
            int imageResId = getArguments().getInt("exerciseImage");
            String technique = getArguments().getString("technique");
            String sets = getArguments().getString("sets");
            String contraindications = getArguments().getString("contraindications");
            ArrayList<String> alternatives = getArguments().getStringArrayList("alternatives");
            String videoUrl = getArguments().getString("videoUrl");

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