package com.example.diplom_final.ui.Spravochnik;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.R;
import com.example.diplom_final.databinding.FragmentSpravochnikBinding;

import java.util.ArrayList;
import java.util.List;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;

public class SpravochnikFragment extends Fragment {

    private FragmentSpravochnikBinding binding;
    private RecyclerView recyclerView;
    private EditText searchEditText;
    private List<SpravochnikItem> items;
    private SpravochnikAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSpravochnikBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Инициализация RecyclerView
        recyclerView = binding.recyclerViewSpravochnik;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true); // Оптимизация производительности

        // Инициализация EditText
        searchEditText = binding.searchEditText;
        setupSearch();

        // Инициализация данных
        initializeItems();

        // Создание и установка адаптера
        adapter = new SpravochnikAdapter(items, position -> {
            SpravochnikItem item = adapter.getFilteredItems().get(position);
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
                // Скрыть клавиатуру
                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        });
    }

    private void navigateToDetail(SpravochnikItem item) {
        // Определяем, куда навигировать на основе позиции или типа элемента
        switch (item.getTitle()) {
            case "Спортивное питание":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_spravochnikFragment_to_sportNutritionFragment);
                break;
            case "Состав и таблица калорийности продуктов":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_spravochnikFragment_to_foodTableFragment);
                break;
            case "Фармакология":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_spravochnikFragment_to_pharmacologyFragment);
                break;
            case "Энциклопедия":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_spravochnikFragment_to_encyclopediaFragment);
                break;
        }
    }

    private void initializeItems() {
        items = new ArrayList<>();
        
        // Добавляем все элементы справочника
        items.add(new SpravochnikItem(
                "Спортивное питание",
                "Информация о спортивном питании, добавках и их применении",
                R.drawable.sport_pit
        ));
        
        items.add(new SpravochnikItem(
                "Состав и таблица калорийности продуктов",
                "Подробная информация о составе и калорийности различных продуктов",
                R.drawable.calorie_table
        ));
        
        items.add(new SpravochnikItem(
                "Фармакология",
                "Информация о спортивной фармакологии и её применении",
                R.drawable.pharmacology
        ));
        
        items.add(new SpravochnikItem(
                "Энциклопедия",
                "Общая информация о тренировках, питании и здоровом образе жизни",
                R.drawable.encyclopedia
        ));
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

