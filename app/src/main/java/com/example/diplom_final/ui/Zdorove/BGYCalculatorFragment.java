package com.example.diplom_final.ui.Zdorove;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.diplom_final.R;

public class BGYCalculatorFragment extends Fragment {

    private EditText editTextCalories;
    private Spinner spinnerDiet;
    private Button buttonCalculateMacros;
    private TextView textViewResultMacros;
    private TextView textViewProtein, textViewFat, textViewCarbs;
    private TextView textViewProteinGrams, textViewFatGrams, textViewCarbsGrams;
    private ProgressBar progressBarProtein, progressBarFat, progressBarCarbs;

    private double proteinPercent, fatPercent, carbPercent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bgy_calculator, container, false);
        initViews(view);
        setupSpinner();
        setupCalculateButton();
        return view;
    }

    private void initViews(View view) {
        editTextCalories = view.findViewById(R.id.editTextCalories);
        spinnerDiet = view.findViewById(R.id.spinnerDiet);
        buttonCalculateMacros = view.findViewById(R.id.buttonCalculateMacros);
        textViewResultMacros = view.findViewById(R.id.textViewResultMacros);
        
        textViewProtein = view.findViewById(R.id.textViewProtein);
        textViewFat = view.findViewById(R.id.textViewFat);
        textViewCarbs = view.findViewById(R.id.textViewCarbs);
        
        textViewProteinGrams = view.findViewById(R.id.textViewProteinGrams);
        textViewFatGrams = view.findViewById(R.id.textViewFatGrams);
        textViewCarbsGrams = view.findViewById(R.id.textViewCarbsGrams);
        
        progressBarProtein = view.findViewById(R.id.progressBarProtein);
        progressBarFat = view.findViewById(R.id.progressBarFat);
        progressBarCarbs = view.findViewById(R.id.progressBarCarbs);

        // Установка фильтров для ввода калорий
        editTextCalories.setFilters(new InputFilter[]{
            new InputFilter.LengthFilter(4),
            (source, start, end, dest, dstart, dend) -> {
                try {
                    String newVal = dest.toString().substring(0, dstart) + 
                                  source.toString().substring(start, end) + 
                                  dest.toString().substring(dend);
                    int input = Integer.parseInt(newVal);
                    if (input > 0 && input <= 9999) return null;
                } catch (NumberFormatException nfe) { }
                return "";
            }
        });
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireActivity(),
                R.array.diet_types, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerDiet.setAdapter(adapter);

        spinnerDiet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateMacroPercentages(position);
                updateMacroLabels();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void updateMacroPercentages(int position) {
        switch (position) {
            case 0: // Мало-жирная диета
                proteinPercent = 40;
                fatPercent = 10;
                carbPercent = 50;
                break;
            case 1: // Белковая диета
                proteinPercent = 50;
                fatPercent = 15;
                carbPercent = 35;
                break;
            case 2: // Сбалансированное питание
                proteinPercent = 20;
                fatPercent = 30;
                carbPercent = 50;
                break;
            case 3: // Белковая сушка
                proteinPercent = 70;
                fatPercent = 10;
                carbPercent = 20;
                break;
        }
    }

    private void updateMacroLabels() {
        textViewProtein.setText(String.format("Белки: %.0f%%", proteinPercent));
        textViewFat.setText(String.format("Жиры: %.0f%%", fatPercent));
        textViewCarbs.setText(String.format("Углеводы: %.0f%%", carbPercent));
    }

    private void setupCalculateButton() {
        buttonCalculateMacros.setOnClickListener(v -> calculateMacros());
    }

    private void calculateMacros() {
        String caloriesStr = editTextCalories.getText().toString();

        if (TextUtils.isEmpty(caloriesStr)) {
            showError("Введите суточную калорийность");
            return;
        }

        try {
            double calories = Double.parseDouble(caloriesStr);
            if (calories <= 0 || calories > 9999) {
                showError("Введите значение от 1 до 9999 ккал");
                return;
            }

            // Расчет граммов макронутриентов
            double protein = (calories * proteinPercent / 100) / 4; // 4 ккал на грамм белка
            double fat = (calories * fatPercent / 100) / 9;        // 9 ккал на грамм жира
            double carbs = (calories * carbPercent / 100) / 4;     // 4 ккал на грамм углеводов

            displayResults(protein, fat, carbs);

        } catch (NumberFormatException e) {
            showError("Проверьте правильность введенных данных");
        }
    }

    private void displayResults(double protein, double fat, double carbs) {
        // Обновляем текстовые поля
        textViewProteinGrams.setText(String.format("%.1f г", protein));
        textViewFatGrams.setText(String.format("%.1f г", fat));
        textViewCarbsGrams.setText(String.format("%.1f г", carbs));

        // Обновляем прогресс-бары
        progressBarProtein.setProgress((int) proteinPercent);
        progressBarFat.setProgress((int) fatPercent);
        progressBarCarbs.setProgress((int) carbPercent);

        // Показываем результаты
        textViewResultMacros.setVisibility(View.VISIBLE);
    }

    private void showError(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
