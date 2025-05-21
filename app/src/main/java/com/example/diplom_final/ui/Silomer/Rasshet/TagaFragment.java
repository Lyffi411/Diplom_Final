package com.example.diplom_final.ui.Silomer.Rasshet;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.diplom_final.databinding.FragmentTagaBinding;
import com.example.diplom_final.ui.Filter.MinMaxFilter;
import com.example.diplom_final.data.AppDatabase;
import com.example.diplom_final.ui.Stata.ProfilViewModel;
import com.example.diplom_final.ui.Stata.Stytis.ExerciseResult;
import com.example.diplom_final.ui.Silomer.Rasshet.WeightNorms;
import com.example.diplom_final.ui.Stata.Profile.UserProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Map;

public class TagaFragment extends Fragment {
    private FragmentTagaBinding binding;
    private ProgressCircleView progressCircle1;
    private TextView textRes;
    private Button btnShowPercentages;
    private Button btnSaveResult;
    private double currentResult = 0;
    private ProfilViewModel profilViewModel;
    private double maxDeadlift = 0;
    private EditText weightInput;
    private EditText repsInput;
    private Button calculateButton;
    private MinMaxFilter weightFilterTaga;
    private MinMaxFilter repsFilterTaga;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profilViewModel = new ViewModelProvider(this).get(ProfilViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        binding = FragmentTagaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Инициализация элементов
        progressCircle1 = binding.progressCircle;
        textRes = binding.textRes;
        btnShowPercentages = binding.btnShowPercentages;
        weightInput = binding.editWheit;
        repsInput = binding.editKolvo;
        calculateButton = binding.buttonRasshet;
        btnSaveResult = binding.btnSaveResult;

        // Скрываем элементы при запуске
        progressCircle1.setVisibility(View.GONE);
        textRes.setVisibility(View.GONE);
        btnShowPercentages.setVisibility(View.GONE);
        btnSaveResult.setVisibility(View.GONE);

        // Фильтры ввода
        weightFilterTaga = new MinMaxFilter(15, 400, weightInput, true);
        repsFilterTaga = new MinMaxFilter(2, 20, repsInput, true);
        weightInput.setFilters(new InputFilter[]{weightFilterTaga});
        repsInput.setFilters(new InputFilter[]{repsFilterTaga});

        // Получаем максимальную тягу из профиля
        profilViewModel.getProfile().observe(getViewLifecycleOwner(), profile -> {
            if (profile != null && profile.deadlift > 0) {
                maxDeadlift = profile.deadlift;
            }
        });

        // Добавляем обработчик потери фокуса
        weightInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                InputMethodManager imm = (InputMethodManager) requireContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
        
        repsInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                InputMethodManager imm = (InputMethodManager) requireContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        // Обработчик кнопки "Рассчитать"
        calculateButton.setOnClickListener(v -> {
            // Скрываем клавиатуру при нажатии на кнопку
            InputMethodManager imm = (InputMethodManager) requireContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            
            // Принудительная валидация
            weightFilterTaga.forceValidate();
            repsFilterTaga.forceValidate();

            calculateAndShowResult(); // Изменил вызов на новый метод
        });

        // Обработчик кнопки "Показать варианты"
        btnShowPercentages.setOnClickListener(v -> showPercentageList());

        // Обработчик кнопки сохранения
        btnSaveResult.setOnClickListener(v -> {
            if (currentResult > 0) {
                try {
                    ExerciseResult resultToSave = new ExerciseResult("deadlift", currentResult, System.currentTimeMillis());
                    // Удаляем new Thread, так как saveResult уже использует ExecutorService
                    saveResult(resultToSave);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Ошибка при подготовке данных для сохранения", Toast.LENGTH_SHORT).show();
                    Log.e("TagaFragment", "Error preparing data for save: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getContext(), "Сначала рассчитайте результат (1ПМ)", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    // Объединенный метод для расчета и отображения результата
    private void calculateAndShowResult() {
        try {
            String weightText = weightInput.getText().toString();
            String repsText = repsInput.getText().toString();

            if (weightText.isEmpty() || repsText.isEmpty()) {
                Toast.makeText(getContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }

            float weight = Float.parseFloat(weightText);
            float reps = Float.parseFloat(repsText);

            if (weight < weightFilterTaga.getMinValue() || reps < repsFilterTaga.getMinValue()) {
                 Toast.makeText(getContext(), "Значения меньше допустимых", Toast.LENGTH_SHORT).show();
                 return;
            }
            
            currentResult = weight * (1 + (reps / 30));
            
            UserProfile profile = profilViewModel.getProfile().getValue();
            
            if (profile != null) {
                int userWeight = (int) profile.getWeight();
                int age = profile.getAge();
                boolean isMale = "Мужской".equals(profile.getGender());
                
                int maxNorm = WeightNorms.getMaxWeight("deadlift", userWeight, age, isMale);
                float targetPercentage = Math.min(100f, (float) (currentResult / maxNorm * 100));
                
                binding.buttonRasshet.setVisibility(View.GONE); // Скрываем кнопку "Рассчитать"
                progressCircle1.setVisibility(View.VISIBLE);
                startProgressAnimation(targetPercentage);
            } else {
                Toast.makeText(getContext(), "Заполните данные профиля", Toast.LENGTH_SHORT).show();
            }
            
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Пожалуйста, введите корректные значения", Toast.LENGTH_SHORT).show();
        }
    }

    // Анимация прогресса
    private void startProgressAnimation(float targetPercentage) {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, targetPercentage);
        animator.setDuration(1500);
        animator.setInterpolator(new LinearInterpolator());

        animator.addUpdateListener(animation -> {
            float progress = (float) animation.getAnimatedValue();
            progressCircle1.setProgress(progress);
            progressCircle1.setProgressText(String.format("%.0f%%", progress));
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                textRes.setText(String.format("Результат: %.1f кг", currentResult));
                textRes.setVisibility(View.VISIBLE);
                btnShowPercentages.setVisibility(View.VISIBLE);
                btnSaveResult.setVisibility(View.VISIBLE);
            }
        });

        animator.start();
    }

    // Отображение списка процентов
    private void showPercentageList() {
        List<String> percentages = new ArrayList<>();
        int reps = 2;

        for (int percent = 97; percent >= 61; percent -= 3) {
            double value = currentResult * (percent / 100.0);
            percentages.add(String.format("%d%% - %.1f кг (%d повт.)", percent, value, reps));
            reps++;
        }

        new AlertDialog.Builder(requireContext())
                .setTitle("Рассчитанные веса")
                .setAdapter(new ArrayAdapter<>(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        percentages), null)
                .setPositiveButton("OK", null)
                .show();
    }

    private void saveResult(ExerciseResult result) {
        String weightStr = "";
        if (binding.editWheit.getText() != null) {
            weightStr = binding.editWheit.getText().toString();
        }

        String repsStr = "";
        if (binding.editKolvo.getText() != null) {
            repsStr = binding.editKolvo.getText().toString();
        }

        double actualWeight = 0;
        int actualReps = 0;

        try {
            if (!weightStr.isEmpty()) {
                actualWeight = Double.parseDouble(weightStr);
            }
            if (!repsStr.isEmpty()) {
                actualReps = Integer.parseInt(repsStr);
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Ошибка в значениях веса или повторений", Toast.LENGTH_SHORT).show();
            return;
        }

        ExerciseResult newResult = new ExerciseResult();
        newResult.setExerciseType("deadlift"); // Убедитесь, что тип правильный - deadlift
        newResult.setResult(currentResult); // currentResult должен быть уже рассчитанным 1ПМ
        newResult.setTimestamp(System.currentTimeMillis());
        newResult.setWeight(actualWeight); // Сохраняем введенный вес
        newResult.setReps(actualReps);   // Сохраняем введенные повторения

        AppDatabase db = AppDatabase.getDatabase(getContext());
        ProfilViewModel profilViewModel = new ViewModelProvider(this).get(ProfilViewModel.class);
        ExecutorService executorService = Executors.newSingleThreadExecutor(); // Создаем новый ExecutorService

        executorService.execute(() -> {
            db.exerciseResultDao().insert(newResult);
            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getContext(), "Результат становой тяги сохранен", Toast.LENGTH_SHORT).show();
                    profilViewModel.getProfile().observe(getViewLifecycleOwner(), userProfile -> {
                        if (userProfile != null) {
                            if (newResult.getResult() > userProfile.getDeadlift()) {
                                userProfile.setDeadlift(newResult.getResult());
                                profilViewModel.saveProfile(userProfile);
                            }
                        }
                    });
                    // Сброс UI для нового расчета
                    binding.btnSaveResult.setVisibility(View.GONE);
                    binding.btnShowPercentages.setVisibility(View.GONE);
                    binding.textRes.setVisibility(View.GONE);
                    binding.textRes.setText("Результат: 0 кг");
                    binding.progressCircle.setVisibility(View.GONE);
                    binding.progressCircle.setProgress(0);
                    binding.progressCircle.setProgressText("0%");
                    weightInput.setText(""); // Используем weightInput, т.к. binding.editWheit уже есть
                    repsInput.setText("");   // Используем repsInput
                    binding.buttonRasshet.setVisibility(View.VISIBLE);
                    currentResult = 0;
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}