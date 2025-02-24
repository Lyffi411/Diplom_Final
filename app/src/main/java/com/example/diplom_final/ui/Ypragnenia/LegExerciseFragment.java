package com.example.diplom_final.ui.Ypragnenia;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.R;
import com.example.diplom_final.databinding.FragmentLegExerciseBinding;

import java.util.ArrayList;
import java.util.List;

public class LegExerciseFragment extends Fragment {
    private FragmentLegExerciseBinding binding;
    private RecyclerView recyclerView;
    private EditText searchEditText;
    private List<LegExerciseItem> items;
    private LegExerciseAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLegExerciseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        searchEditText = binding.searchEditText;
        setupSearch();

        initializeItems();

        adapter = new LegExerciseAdapter(items, position -> {
            LegExerciseItem item = adapter.getFilteredItems().get(position);
            navigateToDetail(item);
        });

        recyclerView.setAdapter(adapter);

        return root;
    }

    private void setupSearch() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                InputMethodManager imm = (InputMethodManager) requireContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        });
    }

    private void initializeItems() {
        items = new ArrayList<>();
        
        items.add(new LegExerciseItem(
            "Выпады вперед с отягощением",
            "Базовое упражнение для развития квадрицепсов, ягодиц и улучшения баланса",
            R.drawable.leg_lunges_weight));
        
        items.add(new LegExerciseItem(
            "Выпады вперед с отягощением и приставлением ноги",
            "Вариация выпадов для лучшей проработки мышц ног",
            R.drawable.leg_lunges_weight_return));
        
        items.add(new LegExerciseItem(
            "Жим ногами (узкая постановка ног)",
            "Акцент на внешнюю часть квадрицепса",
            R.drawable.leg_press_narrow));
        
        items.add(new LegExerciseItem(
            "Жим ногами (широкая постановка ног)",
            "Акцент на внутреннюю часть бедра и ягодицы",
            R.drawable.leg_press_wide));
        
        items.add(new LegExerciseItem(
            "Зашагивания на тумбу с гантелями",
            "Упражнение для развития силы ног и координации",
            R.drawable.step_ups_dumbbells));
        
        items.add(new LegExerciseItem(
            "Обратные гакк-приседания",
            "Изолированная работа на квадрицепсы",
            R.drawable.reverse_hack_squat));
        
        items.add(new LegExerciseItem(
            "Подъем на носки в тренажере сидя",
            "Изолированная работа на камбаловидные мышцы",
            R.drawable.seated_calf_raise));
        
        items.add(new LegExerciseItem(
            "Подъем на носки в тренажере стоя",
            "Упражнение для развития икроножных мышц",
            R.drawable.standing_calf_raise_machine));
        
        items.add(new LegExerciseItem(
            "Подъем на носки стоя со штангой",
            "Базовое упражнение для икроножных мышц",
            R.drawable.standing_calf_raise_barbell));
        
        items.add(new LegExerciseItem(
            "Приседания в Гакк-машине",
            "Безопасная альтернатива приседаниям со штангой",
            R.drawable.hack_squat_narrow));
        
        items.add(new LegExerciseItem(
            "Приседания в Гакк-машине с узкой постановкой ног",
            "Акцент на внешнюю часть квадрицепса",
            R.drawable.hack_squat_narrow));
        
        items.add(new LegExerciseItem(
            "Приседания в Гакк-машине с широкой постановкой ног",
            "Акцент на внутреннюю часть бедра",
            R.drawable.hack_squat_wide));
        
        items.add(new LegExerciseItem(
            "Приседания плие с гантелью на груди",
            "Упражнение для внутренней поверхности бедра",
            R.drawable.sumo_squat_dumbbell));
        
        items.add(new LegExerciseItem(
            "Приседания с гантелями",
            "Базовое упражнение для всех мышц ног",
            R.drawable.dumbbell_squats));
        
        items.add(new LegExerciseItem(
            "Приседания с резинкой",
            "Упражнение для начинающих и разминки",
            R.drawable.band_squats));
        
        items.add(new LegExerciseItem(
            "Приседания со штангой",
            "Базовое упражнение для развития силы ног",
            R.drawable.barbell_squats));
        
        items.add(new LegExerciseItem(
            "Приседания со штангой на груди",
            "Вариация приседаний с акцентом на переднюю часть бедра",
            R.drawable.front_squats));
        
        items.add(new LegExerciseItem(
            "Приседания со штангой на скамью",
            "Контролируемые приседания для начинающих",
            R.drawable.box_squats));
        
        items.add(new LegExerciseItem(
            "Разгибание ног сидя",
            "Изолированное упражнение для квадрицепсов",
            R.drawable.leg_extension));
        
        items.add(new LegExerciseItem(
            "Сведение ног сидя",
            "Упражнение для приводящих мышц бедра",
            R.drawable.seated_leg_adduction));
        
        items.add(new LegExerciseItem(
            "Сгибание лежа по одной ноге",
            "Изолированная работа на бицепс бедра",
            R.drawable.single_leg_curl));
        
        items.add(new LegExerciseItem(
            "Сгибание ног лежа",
            "Базовое упражнение для бицепса бедра",
            R.drawable.lying_leg_curl));
        
        items.add(new LegExerciseItem(
            "Сгибание ног сидя",
            "Альтернативное упражнение для бицепса бедра",
            R.drawable.seated_leg_curl));
        
        items.add(new LegExerciseItem(
            "Сгибание ноги стоя в блоке",
            "Изолированное упражнение для бицепса бедра",
            R.drawable.standing_leg_curl));
        
        items.add(new LegExerciseItem(
            "Становая тяга",
            "Комплексное упражнение для всего тела",
            R.drawable.deadlift_f));
        
        items.add(new LegExerciseItem(
            "Становая тяга 'сумо'",
            "Вариация становой тяги с широкой постановкой ног",
            R.drawable.sumo_deadlift));
    }

    private void navigateToDetail(LegExerciseItem item) {
        Bundle bundle = new Bundle();
        bundle.putString("title", item.getTitle());
        bundle.putString("description", item.getDescription());
        bundle.putInt("imageResourceId", item.getImageResourceId());
        
        Navigation.findNavController(requireView())
                .navigate(R.id.action_legExerciseFragment_to_legExerciseDetailFragment, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        recyclerView = null;
        searchEditText = null;
        items = null;
        adapter = null;
    }
} 