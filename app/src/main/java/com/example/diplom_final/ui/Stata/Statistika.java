package com.example.diplom_final.ui.Stata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.diplom_final.R;
import com.example.diplom_final.data.AppDatabase;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialDatePicker.Builder;
import androidx.core.util.Pair;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import android.graphics.Color;
import java.util.ArrayList;
import java.util.List;
import com.example.diplom_final.ui.Stata.Stytis.ExerciseResult;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Collections;
import com.github.mikephil.charting.formatter.ValueFormatter;
import android.widget.DatePicker;
import java.util.Calendar;
import android.app.DatePickerDialog;

public class Statistika extends Fragment {
    private static final String TAG = "Statistika";
    private LineChart chart;
    private Spinner exerciseTypeSpinner;
    private Button startDateButton;
    private Button endDateButton;
    private AppDatabase db;
    private long startDate = -1;
    private long endDate = -1;
    private String selectedExerciseType = "all";
    private Button clearButton;
    private Button dateRangeButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_statistika, container, false);

        // Инициализация views
        startDateButton = root.findViewById(R.id.startDateButton);
        endDateButton = root.findViewById(R.id.endDateButton);
        exerciseTypeSpinner = root.findViewById(R.id.exerciseTypeSpinner);
        chart = root.findViewById(R.id.lineChart);
        clearButton = root.findViewById(R.id.clearButton);

        setupSpinner();
        setupDateButtons();
        setupChart();
        
        db = AppDatabase.getDatabase(requireContext());
        
        // Инициализируем кнопку очистки
        clearButton.setOnClickListener(v -> showClearConfirmationDialog());
        
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume вызван");
        // Загружаем данные для текущего выбранного упражнения
        if (exerciseTypeSpinner != null && exerciseTypeSpinner.getSelectedItem() != null) {
            String selectedExercise = exerciseTypeSpinner.getSelectedItem().toString();
            loadData(selectedExercise);
        } else {
            Log.d(TAG, "Спиннер не инициализирован или не выбрано значение");
        }
    }

    private void setupSpinner() {
        String[] exercises = new String[]{"deadlift", "bench_press", "squat"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            exercises
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseTypeSpinner.setAdapter(adapter);
        
        exerciseTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedExercise = exercises[position];
                Log.d(TAG, "Выбрано упражнение: " + selectedExercise);
                loadData(selectedExercise);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setupDateButtons() {
        View.OnClickListener startDateClickListener = v -> showDatePicker(true);
        View.OnClickListener endDateClickListener = v -> showDatePicker(false);
        
        startDateButton.setOnClickListener(startDateClickListener);
        endDateButton.setOnClickListener(endDateClickListener);
    }

    private void showDatePicker(final boolean isStart) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
            requireContext(),
            (view, year, month, dayOfMonth) -> {
                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.set(year, month, dayOfMonth);
                
                if (isStart) {
                    selectedCalendar.set(Calendar.HOUR_OF_DAY, 0);
                    selectedCalendar.set(Calendar.MINUTE, 0);
                    selectedCalendar.set(Calendar.SECOND, 0);
                    startDate = selectedCalendar.getTimeInMillis();
                    startDateButton.setText(new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                        .format(new Date(startDate)));
                } else {
                    selectedCalendar.set(Calendar.HOUR_OF_DAY, 23);
                    selectedCalendar.set(Calendar.MINUTE, 59);
                    selectedCalendar.set(Calendar.SECOND, 59);
                    endDate = selectedCalendar.getTimeInMillis();
                    endDateButton.setText(new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                        .format(new Date(endDate)));
                }
                
                if (startDate != -1 && endDate != -1) {
                    String selectedExercise = exerciseTypeSpinner.getSelectedItem() != null ? 
                        exerciseTypeSpinner.getSelectedItem().toString() : null;
                    if (selectedExercise != null) {
                        loadData(selectedExercise);
                    }
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void showClearConfirmationDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Очистка статистики")
                .setMessage("Вы уверены, что хотите удалить всю статистику? Это действие нельзя отменить.")
                .setPositiveButton("Да", (dialog, which) -> clearAllStats())
                .setNegativeButton("Отмена", null)
                .show();
    }

    private void clearAllStats() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Очищаем все результаты
                AppDatabase.getDatabase(requireContext())
                        .exerciseResultDao()
                        .deleteAll();
                
                requireActivity().runOnUiThread(() -> {
                    Toast.makeText(requireContext(), "Статистика очищена", Toast.LENGTH_SHORT).show();
                    // Очищаем график
                    chart.clear();
                    chart.setNoDataText("Нет данных");
                    chart.setNoDataTextColor(Color.WHITE);
                    chart.invalidate();
                });
            } catch (Exception e) {
                Log.e("Statistika", "Ошибка при очистке статистики: " + e.getMessage());
                requireActivity().runOnUiThread(() -> 
                    Toast.makeText(requireContext(), "Ошибка при очистке статистики", Toast.LENGTH_SHORT).show()
                );
            }
        });
        executor.shutdown();
    }

    private void setupChart() {
        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(true);
        chart.setDrawGridBackground(false);
        chart.setBackgroundColor(Color.TRANSPARENT);
        
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(true);
        xAxis.setGranularity(1f);
        
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setDrawGridLines(true);
        leftAxis.setAxisMinimum(0f);
        
        chart.getAxisRight().setEnabled(false);
        
        Legend legend = chart.getLegend();
        legend.setTextColor(Color.WHITE);
        legend.setForm(Legend.LegendForm.LINE);
    }

    private void loadData(String exerciseType) {
        Log.d(TAG, "Загрузка данных для типа: " + exerciseType);
        
        final long finalStartDate = startDate;
        final long finalEndDate = endDate;
        
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                List<ExerciseResult> results = db.exerciseResultDao().getAllResults(exerciseType);
                Log.d(TAG, "Всего найдено результатов: " + results.size());
                
                List<ExerciseResult> filteredResults;
                
                if (finalStartDate != -1 && finalEndDate != -1) {
                    filteredResults = new ArrayList<>();
                    for (ExerciseResult result : results) {
                        long timestamp = result.getTimestamp();
                        if (timestamp >= finalStartDate && 
                            timestamp <= finalEndDate) {
                            filteredResults.add(result);
                        }
                    }
                    Log.d(TAG, "Отфильтровано результатов: " + filteredResults.size());
                } else {
                    filteredResults = results;
                }

                requireActivity().runOnUiThread(() -> {
                    if (!filteredResults.isEmpty()) {
                        updateLineChart(filteredResults);
                    } else {
                        chart.clear();
                        chart.setNoDataText("Нет данных за выбранный период");
                        chart.setNoDataTextColor(Color.WHITE);
                        chart.invalidate();
                        Log.d(TAG, "Нет данных для отображения за выбранный период");
                    }
                });
            } catch (Exception e) {
                Log.e(TAG, "Ошибка загрузки данных: " + e.getMessage());
                e.printStackTrace();
            }
        });
        executor.shutdown();
    }

    private void updateLineChart(List<ExerciseResult> results) {
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> dates = new ArrayList<>();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM HH:mm", Locale.getDefault());
        
        for (int i = 0; i < results.size(); i++) {
            ExerciseResult result = results.get(i);
            entries.add(new Entry(i, (float) result.getValue()));
            dates.add(dateFormat.format(new Date(result.getTimestamp())));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Вес (кг)");
        dataSet.setColor(Color.BLUE);
        dataSet.setCircleColor(Color.BLUE);
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.format(Locale.getDefault(), "%.1f", value);
            }
        });

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);

        // Настройка оси X
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int index = (int) value;
                if (index >= 0 && index < dates.size()) {
                    return dates.get(index);
                }
                return "";
            }
        });
        xAxis.setLabelRotationAngle(45f);

        // Настройка оси Y
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTextColor(Color.WHITE);
        
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
        
        // Дополнительные настройки графика
        chart.getDescription().setEnabled(false);
        chart.setExtraBottomOffset(20f);
        chart.getLegend().setTextColor(Color.WHITE);
        chart.getLegend().setEnabled(true);
        chart.setGridBackgroundColor(Color.TRANSPARENT);
        chart.invalidate();
    }
}