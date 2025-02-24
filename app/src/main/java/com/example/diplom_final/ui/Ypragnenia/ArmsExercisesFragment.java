package com.example.diplom_final.ui.Ypragnenia;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.R;
import com.example.diplom_final.adapters.ArmsExerciseAdapter;
import com.example.diplom_final.databinding.FragmentArmsExercisesBinding;
import com.example.diplom_final.models.ArmsExerciseItem;

import java.util.ArrayList;
import java.util.List;

public class ArmsExercisesFragment extends Fragment {
    private FragmentArmsExercisesBinding binding;
    private RecyclerView recyclerView;
    private ArmsExerciseAdapter adapter;
    private List<ArmsExerciseItem> allExercises;
    private EditText searchEditText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentArmsExercisesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        allExercises = getArmsExercises();
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
        List<ArmsExerciseItem> filteredList = new ArrayList<>();
        for (ArmsExerciseItem exercise : allExercises) {
            if (exercise.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(exercise);
            }
        }
        adapter.updateList(filteredList);
    }

    private void setupRecyclerView() {
        recyclerView = binding.recyclerViewArmsExercises;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        adapter = new ArmsExerciseAdapter(allExercises, item -> {
            Bundle bundle = new Bundle();
            bundle.putString("title", item.getTitle());
            Navigation.findNavController(requireView())
                     .navigate(R.id.action_nav_arms_exercises_to_armsExerciseDetailFragment, bundle);
        });
        
        recyclerView.setAdapter(adapter);
    }

    private List<ArmsExerciseItem> getArmsExercises() {
        List<ArmsExerciseItem> list = new ArrayList<>();
        
        // Упражнения на бицепс
        list.add(new ArmsExerciseItem(
                "Поочередное сгибание рук с гантелями",
                "Базовое упражнение для бицепсов",
                R.drawable.arms_alternate_dumbbell_curl));
                
        list.add(new ArmsExerciseItem(
                "Поочередное сгибание рук с разворотом сидя",
                "Изолирующее упражнение для бицепсов с супинацией",
                R.drawable.arms_seated_alternate_curl));
                
        list.add(new ArmsExerciseItem(
                "Сгибание на скамье Скотта на блоке с канатной рукоятью",
                "Изолированное упражнение для бицепсов",
                R.drawable.arms_scott_cable_rope));
                
        list.add(new ArmsExerciseItem(
                "Сгибание одной руки на скамье",
                "Изолирующее упражнение для бицепса одной руки",
                R.drawable.arms_single_arm_bench_curl));
                
        list.add(new ArmsExerciseItem(
                "Сгибание одной руки на скамье Скотта",
                "Строгое изолирующее упражнение для бицепса",
                R.drawable.arms_single_scott_curl));
                
        list.add(new ArmsExerciseItem(
                "Сгибание одной руки от колена",
                "Концентрированное сгибание для пикового сокращения",
                R.drawable.arms_concentration_curl));
                
        list.add(new ArmsExerciseItem(
                "Сгибание одной рукой в блоке с тросом",
                "Изолированное упражнение с постоянным напряжением",
                R.drawable.arms_single_cable_curl));
                
        list.add(new ArmsExerciseItem(
                "Сгибание одной рукой на скамье Скотта в блоке",
                "Комбинированное изолирующее упражнение",
                R.drawable.arms_scott_single_cable));
                
        list.add(new ArmsExerciseItem(
                "Сгибание рук в блоке",
                "Базовое упражнение с постоянным сопротивлением",
                R.drawable.arms_cable_curl));
                
        list.add(new ArmsExerciseItem(
                "Сгибание рук в блоке сидя на корточках",
                "Вариация для максимального сокращения бицепса",
                R.drawable.arms_squat_cable_curl));

        // Упражнения на бицепс (продолжение)
        list.add(new ArmsExerciseItem(
                "Сгибание рук в тренажере",
                "Изолированное упражнение с фиксированной траекторией",
                R.drawable.arms_machine_curl));
                
        list.add(new ArmsExerciseItem(
                "Сгибание рук с гантелями сидя",
                "Классическое упражнение для бицепсов",
                R.drawable.arms_seated_dumbbell_curl));
                
        list.add(new ArmsExerciseItem(
                "Сгибание рук с разворотом сидя на наклонной скамье",
                "Упражнение для бицепсов с акцентом на супинацию",
                R.drawable.arms_incline_twist_curl));
                
        list.add(new ArmsExerciseItem(
                "Сгибание рук со штангой",
                "Базовое упражнение для массы бицепсов",
                R.drawable.arms_barbell_curl));
                
        list.add(new ArmsExerciseItem(
                "Сгибание рук со штангой на скамье Скотта",
                "Строгое упражнение для бицепсов",
                R.drawable.arms_scott_barbell));
                
        list.add(new ArmsExerciseItem(
                "Сгибание рук со штангой прямым хватом",
                "Вариация для акцента на брахиалис",
                R.drawable.arms_reverse_curl));
                
        list.add(new ArmsExerciseItem(
                "Сгибание рук со штангой с кривым грифом параллельным хватом",
                "Упражнение для комфортной работы запястий",
                R.drawable.arms_ez_bar_curl));
                
        list.add(new ArmsExerciseItem(
                "Сгибание рук со штангой сидя",
                "Строгое упражнение с исключением читинга",
                R.drawable.arms_seated_barbell_curl));

        // Упражнения на трицепс
        list.add(new ArmsExerciseItem(
                "Жим лежа узким хватом",
                "Базовое упражнение для массы трицепсов",
                R.drawable.arms_close_grip_bench));
                
        list.add(new ArmsExerciseItem(
                "Обратные отжимания от скамьи",
                "Упражнение с собственным весом для трицепсов",
                R.drawable.arms_bench_dips));
                
        list.add(new ArmsExerciseItem(
                "Отжимания на брусьях",
                "Эффективное упражнение для трицепсов",
                R.drawable.arms_dips));
                
        list.add(new ArmsExerciseItem(
                "Разгибание в трицепс-машине",
                "Изолированное упражнение с фиксированной траекторией",
                R.drawable.arms_triceps_machine));
                
        list.add(new ArmsExerciseItem(
                "Разгибание из-за головы кривой штанги параллельным хватом",
                "Упражнение для всех головок трицепса",
                R.drawable.arms_ez_bar_overhead));
                
        list.add(new ArmsExerciseItem(
                "Разгибание одной руки из-за головы с гантелью",
                "Изолированное упражнение для одной руки",
                R.drawable.arms_single_overhead));
                
        list.add(new ArmsExerciseItem(
                "Разгибание одной руки из-за головы с гантелью сидя",
                "Строгое изолирующее упражнение",
                R.drawable.arms_seated_overhead));
                
        list.add(new ArmsExerciseItem(
                "Разгибание одной руки с гантелью в наклоне",
                "Упражнение для трицепса в другом угле",
                R.drawable.arms_bent_extension));

        // Продолжить со списком?
        
        // Продолжение упражнений на трицепс
        list.add(new ArmsExerciseItem(
                "Разгибание рук в блоке",
                "Базовое изолирующее упражнение для трицепсов",
                R.drawable.arms_triceps_pushdown));
                
        list.add(new ArmsExerciseItem(
                "Разгибание рук в блоке обратным хватом",
                "Вариация для акцента на внутреннюю головку",
                R.drawable.arms_reverse_pushdown));
                
        list.add(new ArmsExerciseItem(
                "Разгибание рук в блоке с V-рукоятью",
                "Комфортная вариация для запястий",
                R.drawable.arms_v_bar_pushdown));
                
        list.add(new ArmsExerciseItem(
                "Разгибание рук в блоке с веревками",
                "Упражнение с дополнительной ротацией",
                R.drawable.arms_rope_pushdown));
                
        list.add(new ArmsExerciseItem(
                "Разгибание рук в тренажере",
                "Изолированное упражнение с фиксированной траекторией",
                R.drawable.arms_machine_extension));
                
        list.add(new ArmsExerciseItem(
                "Разгибание рук из-за головы в верхнем блоке",
                "Упражнение для растяжения трицепса",
                R.drawable.arms_overhead_extension));
                
        list.add(new ArmsExerciseItem(
                "Разгибание рук из-за головы в верхнем блоке с канатом",
                "Вариация с комфортным хватом",
                R.drawable.arms_overhead_rope));
                
        list.add(new ArmsExerciseItem(
                "Разгибание рук из-за головы с гантелью сидя",
                "Изолирующее упражнение для трицепсов",
                R.drawable.arms_seated_db_overhead));
                
        list.add(new ArmsExerciseItem(
                "Разгибание штанги из-за головы обратным хватом стоя",
                "Сложное упражнение для опытных",
                R.drawable.arms_standing_reverse_extension));
                
        list.add(new ArmsExerciseItem(
                "Разгибание штанги из-за головы стоя",
                "Классическое упражнение для трицепсов",
                R.drawable.arms_standing_overhead));
                
        list.add(new ArmsExerciseItem(
                "Французский жим в блоке",
                "Вариация с постоянным сопротивлением",
                R.drawable.arms_cable_french_press));
                
        list.add(new ArmsExerciseItem(
                "Французский жим на наклонной скамье",
                "Упражнение под другим углом",
                R.drawable.arms_incline_french_press));
                
        list.add(new ArmsExerciseItem(
                "Французский жим с гантелями лежа",
                "Раздельная работа для каждой руки",
                R.drawable.arms_lying_db_extension));
                
        list.add(new ArmsExerciseItem(
                "Французский жим со штангой лежа",
                "Классическое упражнение для трицепсов",
                R.drawable.arms_lying_french_press));
        
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
} 