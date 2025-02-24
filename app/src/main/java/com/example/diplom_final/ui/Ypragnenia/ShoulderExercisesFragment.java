package com.example.diplom_final.ui.Ypragnenia;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.R;
import com.example.diplom_final.adapters.ShoulderExerciseAdapter;
import com.example.diplom_final.model.ShoulderExerciseItem;
import com.example.diplom_final.databinding.FragmentShoulderExercisesBinding;

import java.util.ArrayList;
import java.util.List;

public class ShoulderExercisesFragment extends Fragment {

    private FragmentShoulderExercisesBinding binding;
    private RecyclerView recyclerView;
    private ShoulderExerciseAdapter adapter;
    private List<ShoulderExerciseItem> allExercises;
    private EditText searchEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentShoulderExercisesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        
        allExercises = getShoulderExercises();
        setupRecyclerView();
        setupSearchView();
        
        return root;
    }

    private void setupSearchView() {
        searchEditText = binding.searchEditText;
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                filterExercises(s.toString());
            }
        });
    }

    private void filterExercises(String query) {
        List<ShoulderExerciseItem> filteredList = new ArrayList<>();
        for (ShoulderExerciseItem exercise : allExercises) {
            if (exercise.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(exercise);
            }
        }
        adapter.updateList(filteredList);
    }

    private void setupRecyclerView() {
        recyclerView = binding.recyclerViewShoulderExercises;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        adapter = new ShoulderExerciseAdapter(allExercises, item -> {
            Bundle bundle = new Bundle();
            bundle.putString("title", item.getTitle());
            Navigation.findNavController(requireView())
                     .navigate(R.id.action_nav_shoulder_exercises_to_shoulderExerciseDetailFragment, bundle);
        });
        
        recyclerView.setAdapter(adapter);
    }

    private List<ShoulderExerciseItem> getShoulderExercises() {
        List<ShoulderExerciseItem> list = new ArrayList<>();
        
        list.add(new ShoulderExerciseItem(
                "Армейский жим",
                "Базовое упражнение для развития плеч",
                R.drawable.shoulders_military_press));
        
        list.add(new ShoulderExerciseItem(
                "Армейский жим из-за головы",
                "Вариация армейского жима с акцентом на задние дельты",
                R.drawable.shoulders_behind_neck_press));
        
        list.add(new ShoulderExerciseItem(
                "Боковой поворот плеча в сторону стоя с резинкой",
                "Изолирующее упражнение для внешней ротации плеча",
                R.drawable.shoulders_band_rotation));
        
        list.add(new ShoulderExerciseItem(
                "Жим Арнольда",
                "Комплексное упражнение для всех головок дельтовидной мышцы",
                R.drawable.shoulders_arnold_press));
        
        list.add(new ShoulderExerciseItem(
                "Жим вверх перед собой в тренажере",
                "Безопасное упражнение для передних дельт",
                R.drawable.shoulders_machine_press));
        
        list.add(new ShoulderExerciseItem(
                "Жим гантелей сидя",
                "Классическое упражнение для развития плеч",
                R.drawable.shoulders_dumbbell_press));
        
        list.add(new ShoulderExerciseItem(
                "Жим одной рукой Т-грифа стоя",
                "Одностороннее упражнение для баланса и силы",
                R.drawable.shoulders_tbar_press));
        
        list.add(new ShoulderExerciseItem(
                "Жим штанги из-за головы сидя",
                "Продвинутое упражнение для всех дельт",
                R.drawable.shoulders_behind_neck_barbell));
        
        list.add(new ShoulderExerciseItem(
                "Жим штанги перед собой сидя",
                "Базовое упражнение для развития силы плеч",
                R.drawable.shoulders_front_barbell));
        
        list.add(new ShoulderExerciseItem(
                "Махи в стороны в тренажере сидя",
                "Изолированное упражнение для средних дельт",
                R.drawable.shoulders_machine_lateral));
        
        list.add(new ShoulderExerciseItem(
                "Махи гантелей в стороны параллельным хватом в наклоне",
                "Упражнение для задних дельт",
                R.drawable.shoulders_bent_lateral));
        
        list.add(new ShoulderExerciseItem(
                "Махи гантелями в стороны лёжа грудью на наклонной скамье",
                "Изолированное упражнение для задних дельт",
                R.drawable.shoulders_incline_lateral));
        
        list.add(new ShoulderExerciseItem(
                "Махи гантелями в стороны сидя",
                "Классическое упражнение для средних дельт",
                R.drawable.shoulders_seated_lateral));
        
        list.add(new ShoulderExerciseItem(
                "Махи гантелями в стороны стоя",
                "Базовое упражнение для средних дельт",
                R.drawable.shoulders_standing_lateral));
        
        list.add(new ShoulderExerciseItem(
                "Отведение руки в сторону стоя в блоке",
                "Изолированное упражнение с постоянным сопротивлением",
                R.drawable.shoulders_cable_lateral));
        
        list.add(new ShoulderExerciseItem(
                "Подъём и жим гантелей сидя",
                "Комбинированное упражнение для плеч",
                R.drawable.shoulders_clean_press));
        
        list.add(new ShoulderExerciseItem(
                "Тяга гантелей к подбородку",
                "Упражнение для верха плеч и трапеций",
                R.drawable.shoulders_dumbbell_upright));
        
        list.add(new ShoulderExerciseItem(
                "Тяга штанги к груди в наклоне",
                "Упражнение для задних дельт и верха спины",
                R.drawable.shoulders_bent_row));
        
        list.add(new ShoulderExerciseItem(
                "Тяга штанги к подбородку",
                "Комплексное упражнение для плеч и трапеций",
                R.drawable.shoulders_barbell_upright));
        
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
} 