package com.example.diplom_final.ui.Silomer.Rasshet;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.diplom_final.databinding.FragmentGimBinding;
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

public class GimFragment extends Fragment {
    private FragmentGimBinding binding;
    private ProgressCircleView progressCircle1;
    private TextView textRes;
    private Button btnShowPercentages;
    private Button btnSaveResult;
    private double currentResult = 0;
    private ProfilViewModel profilViewModel;
    private double maxBenchPress = 0;
    private MinMaxFilter weightFilter;
    private MinMaxFilter repsFilter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profilViewModel = new ViewModelProvider(this).get(ProfilViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        binding = FragmentGimBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Инициализация элементов
        progressCircle1 = binding.progressCircle;
        textRes = binding.textRes;
        btnShowPercentages = binding.btnShowPercentages;
        EditText editTextWeight = binding.editWheit;
        EditText editTextReps = binding.editKolvo;
        Button buttonCalculate = binding.buttonRasshet;
        btnSaveResult = binding.btnSaveResult;

        // Скрываем элементы при запуске
        progressCircle1.setVisibility(View.GONE);
        textRes.setVisibility(View.GONE);
        btnShowPercentages.setVisibility(View.GONE);
        btnSaveResult.setVisibility(View.GONE);

        // Создаем фильтры с сохранением ссылок
        weightFilter = new MinMaxFilter(15, 400, editTextWeight, true);
        repsFilter = new MinMaxFilter(2, 20, editTextReps, true);
        
        // Устанавливаем фильтры
        editTextWeight.setFilters(new InputFilter[]{weightFilter});
        editTextReps.setFilters(new InputFilter[]{repsFilter});

        // Получаем максимальный жим из профиля
        profilViewModel.getProfile().observe(getViewLifecycleOwner(), profile -> {
            if (profile != null && profile.benchPress > 0) {
                maxBenchPress = profile.benchPress;
            }
        });

        // Обработчик кнопки "Рассчитать"
        buttonCalculate.setOnClickListener(v -> {
            try {
                // Принудительная валидация перед расчетом
                weightFilter.forceValidate();
                repsFilter.forceValidate();
                
                // Получаем значения после валидации
                String weightText = editTextWeight.getText().toString();
                String repsText = editTextReps.getText().toString();
                
                // Проверяем, что поля не пустые
                if (weightText.isEmpty() || repsText.isEmpty()) {
                    Toast.makeText(getContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Преобразуем в числа
                float weight = Float.parseFloat(weightText);
                float reps = Float.parseFloat(repsText);
                
                // Проверяем минимальные значения
                if (weight < weightFilter.getMinValue() || reps < repsFilter.getMinValue()) {
                    Toast.makeText(getContext(), "Значения меньше допустимых", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Продолжаем расчет
                currentResult = weight * (1 + (reps / 30));
                
                // Получаем данные из ViewModel
                UserProfile profile = profilViewModel.getProfile().getValue();
                
                if (profile != null) {
                    int userWeight = (int) profile.getWeight();
                    int age = profile.getAge();
                    boolean isMale = "Мужской".equals(profile.getGender());

                    
                    // Получаем максимум из таблицы нормативов
                    int maxNorm = WeightNorms.getMaxWeight("bench", userWeight, age, isMale);
                    Log.d("GimFragment", "Max norm from table: " + maxNorm);
                    
                    // Рассчитываем процент от максимума
                    float targetPercentage = Math.min(100f, (float) (currentResult / maxNorm * 100));
                    Log.d("GimFragment", String.format(
                        "Percentage calculation: (%.2f / %d) * 100 = %.2f%%",
                        currentResult, maxNorm, targetPercentage));
                    
                    binding.buttonRasshet.setVisibility(View.GONE); // Скрываем кнопку "Рассчитать"
                    progressCircle1.setVisibility(View.VISIBLE);
                    startProgressAnimation(targetPercentage);

                } else {

                    Toast.makeText(getContext(), "Заполните данные профиля", Toast.LENGTH_SHORT).show();
                }
                
            } catch (NumberFormatException e) {

                Toast.makeText(getContext(), "Пожалуйста, введите корректные значения", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {

                e.printStackTrace();
            }
        });

        // Обработчик кнопки "Показать варианты"
        btnShowPercentages.setOnClickListener(v -> showPercentageList());

        // Обработчик кнопки сохранения
        btnSaveResult.setOnClickListener(v -> {
            // Убедимся, что currentResult был рассчитан и он больше нуля
            if (currentResult > 0) {
                try {
                    // Используем конструктор, который принимает тип, результат (1ПМ - currentResult) и timestamp
                    ExerciseResult result = new ExerciseResult("bench_press", currentResult, System.currentTimeMillis());
                    saveResult(result); // Метод saveResult уже ожидает ExerciseResult
                    // Сообщение о сохранении уже есть внутри saveResult -> runOnUiThread
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Ошибка при подготовке данных для сохранения", Toast.LENGTH_SHORT).show();
                    Log.e("GimFragment", "Error preparing data for save: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getContext(), "Сначала рассчитайте результат (1ПМ)", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    // Расчет результата
    private double calculateResult(double weight, double reps) {
        double res1 = (weight * reps) / 30 + weight;
        double res2 = weight * (36 / (37 - reps));
        double res3 = (100 * weight) / (101.3 - 2.67123 * reps);
        double res4 = weight * (1 + 0.025 * reps);
        return (res1 + res2 + res3 + res4) / 4;
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
            textRes.setText(String.format("Результат: %.1f кг", currentResult));
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
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
            percentages.add(String.format("%d%% - %.1f kg (%d повт.)", percent, value, reps));
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
        // Убедимся, что currentResult - это рассчитанный 1ПМ
        // Предполагаем, что введенные вес и повторения доступны как локальные переменные
        // или поля класса, например, this.inputWeight и this.inputReps,
        // которые были использованы для расчета currentResult.

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
            // Обработка ошибки, если значения не могут быть преобразованы
            Toast.makeText(getContext(), "Ошибка в значениях веса или повторений", Toast.LENGTH_SHORT).show();
            return; // Не сохраняем, если данные некорректны
        }


        ExerciseResult newResult = new ExerciseResult();
        newResult.setExerciseType("bench_press"); // Убедитесь, что тип правильный
        newResult.setResult(currentResult); // currentResult должен быть уже рассчитанным 1ПМ
        newResult.setTimestamp(System.currentTimeMillis());
        newResult.setWeight(actualWeight); // Сохраняем введенный вес
        newResult.setReps(actualReps);   // Сохраняем введенные повторения

        AppDatabase db = AppDatabase.getDatabase(getContext());
        ProfilViewModel profilViewModel = new ViewModelProvider(this).get(ProfilViewModel.class);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            db.exerciseResultDao().insert(newResult);
            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getContext(), "Результат жима сохранен", Toast.LENGTH_SHORT).show();
                    // updateLastSavedResult(); // Обновляем отображение последнего сохраненного результата
                    // Если есть логика для обновления профиля, вызываем её
                     profilViewModel.getProfile().observe(getViewLifecycleOwner(), userProfile -> {
                        if (userProfile != null) {
                            if (newResult.getResult() > userProfile.getBenchPress()) {
                                userProfile.setBenchPress(newResult.getResult());
                                profilViewModel.saveProfile(userProfile); // Сохраняем обновленный профиль
                            }
                        }
                    });

                    // Сброс UI для нового расчета
                    binding.btnSaveResult.setVisibility(View.GONE);
                    binding.btnShowPercentages.setVisibility(View.GONE);
                    binding.textRes.setVisibility(View.GONE);
                    binding.textRes.setText("Результат: 0 кг"); // Сброс текста
                    binding.progressCircle.setVisibility(View.GONE);
                    binding.progressCircle.setProgress(0);
                    binding.progressCircle.setProgressText("0%");
                    binding.editWheit.setText("");
                    binding.editKolvo.setText("");
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