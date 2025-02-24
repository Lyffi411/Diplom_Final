package com.example.diplom_final.ui.Zdorove;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.diplom_final.R;
import com.example.diplom_final.ui.Filter.MinMaxFilter;

public class WeightCalculatorFragment extends Fragment {

    private EditText editTextWeight, editTextHeight;
    private TextView textViewResult, textViewBmiCategory;
    private Button buttonCalculate;
    private MinMaxFilter weightFilter;
    private MinMaxFilter heightFilter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weight_calculator, container, false);

        initViews(view);
        setupInputFilters();
        setupCalculateButton();

        return view;
    }

    private void initViews(View view) {
        editTextWeight = view.findViewById(R.id.editTextWeight);
        editTextHeight = view.findViewById(R.id.editTextHeight);
        textViewResult = view.findViewById(R.id.textViewResult);
        textViewBmiCategory = view.findViewById(R.id.textViewBmiCategory);
        buttonCalculate = view.findViewById(R.id.buttonCalculate);
    }

    private void setupInputFilters() {
        weightFilter = new MinMaxFilter(30, 250, editTextWeight);
        heightFilter = new MinMaxFilter(40, 250, editTextHeight);

        editTextWeight.setFilters(new InputFilter[]{weightFilter});
        editTextHeight.setFilters(new InputFilter[]{heightFilter});
    }

    private void setupCalculateButton() {
        buttonCalculate.setOnClickListener(v -> calculateBMI());
    }

    private void calculateBMI() {
        try {
            // Принудительная валидация
            weightFilter.forceValidate();
            heightFilter.forceValidate();

            String weightStr = editTextWeight.getText().toString();
            String heightStr = editTextHeight.getText().toString();

            if (weightStr.isEmpty() || heightStr.isEmpty()) {
                showError("Пожалуйста, заполните все поля");
                return;
            }

            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr) / 100; // переводим в метры

            if (height < 1 || height > 2.5) {
                showError("Проверьте правильность введенного роста");
                return;
            }

            float bmi = weight / (height * height);
            displayBMIResult(bmi);
            
        } catch (Exception e) {
            showError("Проверьте правильность введенных данных");
        }
    }

    private void displayBMIResult(float bmi) {
        textViewResult.setText(String.format("ИМТ: %.1f", bmi));
        
        String category;
        int colorResId;
        
        if (bmi < 16) {
            category = "Выраженный дефицит массы";
            colorResId = android.R.color.holo_red_dark;
        } else if (bmi < 18.5) {
            category = "Недостаточная масса";
            colorResId = android.R.color.holo_orange_dark;
        } else if (bmi < 25) {
            category = "Нормальная масса";
            colorResId = android.R.color.holo_green_dark;
        } else if (bmi < 30) {
            category = "Избыточная масса";
            colorResId = android.R.color.holo_orange_dark;
        } else if (bmi < 35) {
            category = "Ожирение I степени";
            colorResId = android.R.color.holo_red_light;
        } else if (bmi < 40) {
            category = "Ожирение II степени";
            colorResId = android.R.color.holo_red_dark;
        } else {
            category = "Ожирение III степени";
            colorResId = android.R.color.holo_red_dark;
        }

        textViewBmiCategory.setTextColor(requireContext().getColor(colorResId));
        textViewBmiCategory.setText(category);
        textViewBmiCategory.setVisibility(View.VISIBLE);
    }

    private void showError(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
