package com.example.diplom_final.ui.Zdorove;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.diplom_final.R;
import com.example.diplom_final.ui.Filter.MinMaxFilter;

public class MinEatCalculatorFragment extends Fragment {

    private EditText editTextWeight, editTextHeight, editTextAge;
    private RadioGroup radioGroupGender, radioGroupActivity;
    private Button buttonCalculateCalories;
    private TextView textViewResultCalories, textViewDailyCalories;
    private MinMaxFilter weightFilter;
    private MinMaxFilter heightFilter;
    private MinMaxFilter ageFilter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mineatcalculator, container, false);
        initViews(view);
        setupInputFilters();
        setupCalculateButton();
        return view;
    }

    private void initViews(View view) {
        editTextWeight = view.findViewById(R.id.editTextWeight);
        editTextHeight = view.findViewById(R.id.editTextHeight);
        editTextAge = view.findViewById(R.id.editTextAge);
        radioGroupGender = view.findViewById(R.id.radioGroupGender);
        radioGroupActivity = view.findViewById(R.id.radioGroupActivity);
        buttonCalculateCalories = view.findViewById(R.id.buttonCalculateCalories);
        textViewResultCalories = view.findViewById(R.id.textViewResultCalories);
        textViewDailyCalories = view.findViewById(R.id.textViewDailyCalories);
    }

    private void setupInputFilters() {
        weightFilter = new MinMaxFilter(30, 250, editTextWeight, true);
        heightFilter = new MinMaxFilter(40, 250, editTextHeight, true);
        ageFilter = new MinMaxFilter(10, 120, editTextAge, true);

        editTextWeight.setFilters(new InputFilter[]{weightFilter});
        editTextHeight.setFilters(new InputFilter[]{heightFilter});
        editTextAge.setFilters(new InputFilter[]{ageFilter});
    }

    private void setupCalculateButton() {
        buttonCalculateCalories.setOnClickListener(v -> calculateCalories());
    }

    private void calculateCalories() {
        try {
            // Принудительная валидация
            weightFilter.forceValidate();
            heightFilter.forceValidate();
            ageFilter.forceValidate();

            if (!validateInput()) {
                return;
            }

            double weight = Double.parseDouble(editTextWeight.getText().toString());
            double height = Double.parseDouble(editTextHeight.getText().toString());
            int age = Integer.parseInt(editTextAge.getText().toString());

            // Расчет базового обмена веществ (BMR)
            double bmr = calculateBMR(weight, height, age);
            
            // Учет уровня активности
            double dailyCalories = calculateDailyCalories(bmr);

            displayResults(bmr, dailyCalories);

        } catch (Exception e) {
            showError("Проверьте правильность введенных данных");
        }
    }

    private boolean validateInput() {
        if (TextUtils.isEmpty(editTextWeight.getText()) || 
            TextUtils.isEmpty(editTextHeight.getText()) || 
            TextUtils.isEmpty(editTextAge.getText())) {
            showError("Пожалуйста, заполните все поля");
            return false;
        }

        if (radioGroupGender.getCheckedRadioButtonId() == -1) {
            showError("Выберите пол");
            return false;
        }

        if (radioGroupActivity.getCheckedRadioButtonId() == -1) {
            showError("Выберите уровень активности");
            return false;
        }

        return true;
    }

    private double calculateBMR(double weight, double height, int age) {
        boolean isMale = radioGroupGender.getCheckedRadioButtonId() == R.id.radioMale;
        
        if (isMale) {
            return 88.36 + (13.4 * weight) + (4.8 * height) - (5.7 * age);
        } else {
            return 447.6 + (9.2 * weight) + (3.1 * height) - (4.3 * age);
        }
    }

    private double calculateDailyCalories(double bmr) {
        int checkedId = radioGroupActivity.getCheckedRadioButtonId();
        double activityMultiplier;
        
        if (checkedId == R.id.radioSedentary) {
            activityMultiplier = 1.2; // Сидячий образ жизни
        } else if (checkedId == R.id.radioLightlyActive) {
            activityMultiplier = 1.375; // Легкая активность
        } else if (checkedId == R.id.radioModeratelyActive) {
            activityMultiplier = 1.55; // Умеренная активность
        } else if (checkedId == R.id.radioVeryActive) {
            activityMultiplier = 1.725; // Высокая активность
        } else if (checkedId == R.id.radioExtremelyActive) {
            activityMultiplier = 1.9; // Экстремальная активность
        } else {
            activityMultiplier = 1.2; // По умолчанию
        }
        
        return bmr * activityMultiplier;
    }

    private void displayResults(double bmr, double dailyCalories) {
        textViewResultCalories.setText(String.format(
            "Базовый обмен веществ (BMR):\n%.0f ккал", bmr));
        
        textViewDailyCalories.setText(String.format(
            "Суточная потребность:\n%.0f ккал", dailyCalories));
        
        textViewResultCalories.setVisibility(View.VISIBLE);
        textViewDailyCalories.setVisibility(View.VISIBLE);
    }

    private void showError(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
