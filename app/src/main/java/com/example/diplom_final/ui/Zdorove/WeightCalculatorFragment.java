package com.example.diplom_final.ui.Zdorove;

import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.diplom_final.R;
import com.example.diplom_final.ui.Filter.MinMaxFilter;

public class WeightCalculatorFragment extends Fragment {

    private EditText editTextWeight, editTextHeight;
    private TextView textViewResult, textViewBmiCategory;
    private Button buttonCalculate;
    private MinMaxFilter weightFilter;
    private MinMaxFilter heightFilter;
    private View bmiScaleContainer;
    private ImageView bmiArrow;

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
        bmiScaleContainer = view.findViewById(R.id.bmiScaleContainer);
        bmiArrow = view.findViewById(R.id.bmiArrow);
    }

    private void setupInputFilters() {
        weightFilter = new MinMaxFilter(30, 250, editTextWeight, true);
        heightFilter = new MinMaxFilter(40, 250, editTextHeight, true);

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
        textViewResult.setText(String.format("%.2f", bmi));
        bmiScaleContainer.setVisibility(View.VISIBLE);

        // Получаем размеры напрямую, так как view уже измерен
        View scaleBar = bmiScaleContainer.findViewById(R.id.scaleBar);
        
        if (scaleBar.getWidth() == 0) {
            // Первый расчет - используем ViewTreeObserver
            scaleBar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    scaleBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    updateArrowPosition(bmi, scaleBar.getWidth());
                }
            });
        } else {
            // Последующие расчеты - используем текущую ширину
            updateArrowPosition(bmi, scaleBar.getWidth());
        }

        // Определяем категорию и цвет
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

    private void updateArrowPosition(float bmi, float containerWidth) {
        float totalWeight = 8f; // сумма всех layout_weight в разметке
        float position;
        
        if (bmi < 16) {
            // Первая красная зона (вес 1)
            position = (bmi / 16f) * (containerWidth / totalWeight);
        } else if (bmi < 18.5) {
            // Оранжевая зона (вес 1)
            float basePosition = containerWidth / totalWeight;
            position = basePosition + ((bmi - 16f) / 2.5f) * (containerWidth / totalWeight);
        } else if (bmi < 25) {
            // Зеленая зона (вес 2)
            float basePosition = (2f * containerWidth) / totalWeight;
            position = basePosition + ((bmi - 18.5f) / 6.5f) * (2f * containerWidth / totalWeight);
        } else if (bmi < 30) {
            // Оранжевая зона (вес 1.5)
            float basePosition = (4f * containerWidth) / totalWeight;
            position = basePosition + ((bmi - 25f) / 5f) * (1.5f * containerWidth / totalWeight);
        } else if (bmi < 35) {
            // Красная зона (вес 1)
            float basePosition = (5.5f * containerWidth) / totalWeight;
            position = basePosition + ((bmi - 30f) / 5f) * (containerWidth / totalWeight);
        } else if (bmi < 40) {
            // Темно-красная зона (вес 1)
            float basePosition = (6.5f * containerWidth) / totalWeight;
            position = basePosition + ((bmi - 35f) / 5f) * (containerWidth / totalWeight);
        } else {
            // Последняя красная зона (вес 0.5)
            position = (7.5f * containerWidth) / totalWeight;
        }

        // Центрируем стрелку
        position -= bmiArrow.getWidth() / 2f;
        
        // Ограничиваем позицию
        position = Math.max(0, Math.min(position, containerWidth - bmiArrow.getWidth()));

        bmiArrow.animate()
                .translationX(position)
                .setDuration(300)
                .start();
    }

    private void showError(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
