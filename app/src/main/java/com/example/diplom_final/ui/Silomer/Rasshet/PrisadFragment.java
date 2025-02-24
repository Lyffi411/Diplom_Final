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

import com.example.diplom_final.databinding.FragmentPrisadBinding;
import com.example.diplom_final.ui.Filter.MinMaxFilter;
import com.example.diplom_final.data.AppDatabase;
import com.example.diplom_final.ui.Stata.ProfilViewModel;
import com.example.diplom_final.ui.Stata.Stytis.ExerciseResult;
import com.example.diplom_final.ui.Stata.Profile.UserProfile;
import com.example.diplom_final.ui.Silomer.Rasshet.WeightNorms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrisadFragment extends Fragment {
    private FragmentPrisadBinding binding;
    private ProgressCircleView progressCircle1;
    private TextView textRes;
    private Button btnShowPercentages;
    private Button btnSaveResult;
    private double currentResult = 0;
    private ProfilViewModel profilViewModel;
    private double maxSquat = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profilViewModel = new ViewModelProvider(this).get(ProfilViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        binding = FragmentPrisadBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Инициализация элементов
        progressCircle1 = binding.progressCircle;
        textRes = binding.textRes;
        btnShowPercentages = binding.btnShowPercentages;
        EditText editTextWeight = binding.editWheit;
        EditText editTextReps = binding.editKolvo;
        Button buttonCalculate = binding.buttonR;
        btnSaveResult = binding.btnSaveResult;

        // Скрываем элементы при запуске
        progressCircle1.setVisibility(View.GONE);
        textRes.setVisibility(View.GONE);
        btnShowPercentages.setVisibility(View.GONE);
        btnSaveResult.setVisibility(View.GONE);

        // Фильтры ввода
           editTextWeight.setFilters(new InputFilter[]{new MinMaxFilter(15, 400, editTextWeight, true)});
          editTextReps.setFilters(new InputFilter[]{new MinMaxFilter(2, 20, editTextReps, true)});

        // Получаем максимальный присед из профиля
        profilViewModel.getProfile().observe(getViewLifecycleOwner(), profile -> {
            if (profile != null && profile.squat > 0) {
                maxSquat = profile.squat;
            }
        });

        // Обработчик кнопки "Рассчитать"
        buttonCalculate.setOnClickListener(v -> {
            try {
                // Сначала скрываем все элементы
                progressCircle1.setVisibility(View.VISIBLE);
                progressCircle1.setProgress(0);
                textRes.setVisibility(View.GONE);
                btnShowPercentages.setVisibility(View.GONE);
                btnSaveResult.setVisibility(View.GONE);

                // Получение данных
                float weight = Float.parseFloat(editTextWeight.getText().toString());
                float reps = Float.parseFloat(editTextReps.getText().toString());

                // Расчет результата
                currentResult = weight * (1 + (reps / 30));

                // Получаем данные из ViewModel
                UserProfile profile = profilViewModel.getProfile().getValue();
                
                if (profile != null) {
                    int userWeight = (int) profile.getWeight();
                    int age = profile.getAge();
                    boolean isMale = "Мужской".equals(profile.getGender());

                    
                    // Получаем максимум из таблицы нормативов
                    int maxNorm = WeightNorms.getMaxWeight("squat", userWeight, age, isMale);
                    
                    // Рассчитываем процент от максимума
                    float targetPercentage = Math.min(100f, (float) (currentResult / maxNorm * 100));
                    
                    // Запуск анимации
                    startProgressAnimation(targetPercentage);
                } else {
                    Toast.makeText(getContext(), "Заполните данные профиля", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Пожалуйста, введите корректные значения", Toast.LENGTH_SHORT).show();
                progressCircle1.setVisibility(View.GONE);
            }
        });

        // Обработчик кнопки "Показать варианты"
        btnShowPercentages.setOnClickListener(v -> showPercentageList());

        // Обработчик кнопки сохранения
        btnSaveResult.setOnClickListener(v -> {
            new Thread(() -> {
                saveResult(currentResult);
            }).start();
        });

        return root;
    }

    // Анимация прогресса
    private void startProgressAnimation(float targetPercentage) {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, targetPercentage);
        animator.setDuration(1500);
        animator.setInterpolator(new LinearInterpolator());

        animator.addUpdateListener(animation -> {
            float progress = (float) animation.getAnimatedValue();
            progressCircle1.setProgressText(String.format("%.1f%%", progress));
            progressCircle1.setProgress(progress);
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

    private void saveResult(double weight) {
        try {
            ExerciseResult result = new ExerciseResult("squat", weight);
            AppDatabase db = AppDatabase.getDatabase(requireContext());
            
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                try {
                    db.exerciseResultDao().insert(result);
                    Log.d("PrisadFragment", "SUCCESS: Сохранен результат в БД: тип=squat, вес=" + weight);
                    
                    // Очищаем поля и скрываем элементы в UI потоке
                    requireActivity().runOnUiThread(() -> {
                        // Скрываем все элементы
                        progressCircle1.setVisibility(View.GONE);
                        textRes.setVisibility(View.GONE);
                        btnShowPercentages.setVisibility(View.GONE);
                        btnSaveResult.setVisibility(View.GONE);
                        
                        // Очищаем поля ввода
                        EditText weightInput = binding.editWheit;
                        EditText repsInput = binding.editKolvo;
                        weightInput.setText("");
                        repsInput.setText("");
                        
                        Toast.makeText(requireContext(), "Результат сохранен", Toast.LENGTH_SHORT).show();
                        Log.d("PrisadFragment", "UI обновлен после сохранения");
                    });
                } catch (Exception e) {
                    Log.e("PrisadFragment", "ERROR при сохранении: " + e.getMessage());
                    e.printStackTrace();
                    requireActivity().runOnUiThread(() -> 
                        Toast.makeText(requireContext(), "Ошибка при сохранении", Toast.LENGTH_SHORT).show()
                    );
                }
            });
            executor.shutdown();
        } catch (Exception e) {
            Log.e("PrisadFragment", "ERROR в основном потоке: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}