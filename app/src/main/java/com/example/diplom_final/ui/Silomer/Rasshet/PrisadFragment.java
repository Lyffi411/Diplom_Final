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
    private MinMaxFilter weightFilterPrisad;
    private MinMaxFilter repsFilterPrisad;

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
        weightFilterPrisad = new MinMaxFilter(15, 400, editTextWeight, true);
        repsFilterPrisad = new MinMaxFilter(2, 20, editTextReps, true);
        editTextWeight.setFilters(new InputFilter[]{weightFilterPrisad});
        editTextReps.setFilters(new InputFilter[]{repsFilterPrisad});

        // Получаем максимальный присед из профиля
        profilViewModel.getProfile().observe(getViewLifecycleOwner(), profile -> {
            if (profile != null && profile.squat > 0) {
                maxSquat = profile.squat;
            }
        });

        // Обработчик кнопки "Рассчитать"
        buttonCalculate.setOnClickListener(v -> {
            try {
                weightFilterPrisad.forceValidate();
                repsFilterPrisad.forceValidate();

                String weightText = editTextWeight.getText().toString();
                String repsText = editTextReps.getText().toString();

                if (weightText.isEmpty() || repsText.isEmpty()) {
                    Toast.makeText(getContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                float weight = Float.parseFloat(weightText);
                float reps = Float.parseFloat(repsText);

                if (weight < weightFilterPrisad.getMinValue() || reps < repsFilterPrisad.getMinValue()) {
                    Toast.makeText(getContext(), "Значения меньше допустимых", Toast.LENGTH_SHORT).show();
                    return;
                }

                currentResult = weight * (1 + (reps / 30));

                UserProfile profile = profilViewModel.getProfile().getValue();
                
                if (profile != null) {
                    int userWeight = (int) profile.getWeight();
                    int age = profile.getAge();
                    boolean isMale = "Мужской".equals(profile.getGender());
                    
                    int maxNorm = WeightNorms.getMaxWeight("squat", userWeight, age, isMale);
                    float targetPercentage = Math.min(100f, (float) (currentResult / maxNorm * 100));
                    
                    binding.buttonR.setVisibility(View.GONE); // Скрываем кнопку "Рассчитать"
                    progressCircle1.setVisibility(View.VISIBLE);
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
            if (currentResult > 0) {
                try {
                    saveResult(new ExerciseResult("squat", currentResult, System.currentTimeMillis()));
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Ошибка при подготовке данных для сохранения", Toast.LENGTH_SHORT).show();
                    Log.e("PrisadFragment", "Error preparing data for save: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getContext(), "Сначала рассчитайте результат (1ПМ)", Toast.LENGTH_SHORT).show();
            }
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
        newResult.setExerciseType("squat"); // Убедитесь, что тип правильный - squat
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
                    Toast.makeText(getContext(), "Результат приседаний сохранен", Toast.LENGTH_SHORT).show();
                    profilViewModel.getProfile().observe(getViewLifecycleOwner(), userProfile -> {
                        if (userProfile != null) {
                            if (newResult.getResult() > userProfile.getSquat()) {
                                userProfile.setSquat(newResult.getResult());
                                profilViewModel.saveProfile(userProfile);
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
                    binding.buttonR.setVisibility(View.VISIBLE); // Показываем кнопку "Рассчитать"
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