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
import android.widget.AutoCompleteTextView;
import androidx.core.content.ContextCompat;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.github.mikephil.charting.utils.Utils;
import android.graphics.Paint;
import com.github.mikephil.charting.charts.Chart;
import java.util.Comparator;

public class Statistika extends Fragment {
    private static final String TAG = "Statistika";
    private LineChart chart;
    private AutoCompleteTextView exerciseTypeSpinner;
    private Button startDateButton;
    private Button endDateButton;
    private AppDatabase db;
    private long startDate = -1;
    private long endDate = -1;
    private Button clearButton;
    private Button advancedStatisticsButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_statistika, container, false);
        db = AppDatabase.getDatabase(requireContext()); // Инициализация DB
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Инициализация View
        chart = view.findViewById(R.id.lineChart);
        exerciseTypeSpinner = view.findViewById(R.id.exerciseTypeSpinner);
        startDateButton = view.findViewById(R.id.startDateButton);
        endDateButton = view.findViewById(R.id.endDateButton);
        advancedStatisticsButton = view.findViewById(R.id.advancedStatisticsButton);
        clearButton = view.findViewById(R.id.clearButton);

        // Первичная установка адаптера и слушателя
        ArrayAdapter<String> adapter = createSpinnerAdapter();
        exerciseTypeSpinner.setAdapter(adapter);
        exerciseTypeSpinner.setOnItemClickListener((parent, v, position, id) -> {
            String selectedExercise = (String) parent.getItemAtPosition(position);
            String exerciseType = convertToDbFormat(selectedExercise);
            Log.d(TAG, "Выбрано упражнение: " + exerciseType);
            // Загружаем данные только если даты выбраны
            if (startDate != -1 && endDate != -1) {
                loadData(exerciseType);
            } else {
                // Можно добавить Toast или просто не загружать
                Toast.makeText(requireContext(), "Сначала выберите даты", Toast.LENGTH_SHORT).show();
                // Очищаем выбор в спиннере, чтобы не было путаницы
                exerciseTypeSpinner.setText("", false);
            }
        });

        // Настройка остальных компонентов
        setupChart();
        setupDateButtons();
        setupButtons();

        // Устанавливаем начальное состояние (спиннер выключен, текст графика)
        checkDatesAndEnableSpinner();
        if (chart != null) {
            chart.setNoDataText("Выберите даты и упражнение для отображения статистики");
            chart.invalidate();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Принудительно сбрасываем и переустанавливаем адаптер спиннера
        // чтобы гарантировать полный список при возврате
        if (exerciseTypeSpinner != null) {
            ArrayAdapter<String> adapter = createSpinnerAdapter();
            exerciseTypeSpinner.setAdapter(adapter); // Переустановка адаптера
            exerciseTypeSpinner.setText("", false); // Очищаем выбор
            checkDatesAndEnableSpinner(); // Восстанавливаем enabled/hint
        }
        // Не загружаем данные здесь
    }

    private void setupButtons() {
        // Button loadDataButton = getView().findViewById(R.id.loadDataButton); // Если есть такая кнопка
        // if (loadDataButton != null) { // Проверка, если кнопка есть
        // loadDataButton.setOnClickListener(v -> loadInitialData());
        // }

        if (advancedStatisticsButton != null) {
            advancedStatisticsButton.setOnClickListener(v -> {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.nav_advanced_statistics);
            });
        }

        if (clearButton != null) {
            clearButton.setOnClickListener(v -> clearChartData());
        }
    }

    private void clearChartData() {
        chart.clear();
        chart.invalidate();
        Toast.makeText(getContext(), "Данные графика очищены", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        resetFilters(); // Очищаем состояние перед уходом
        chart = null;
        exerciseTypeSpinner = null;
        startDateButton = null;
        endDateButton = null;
        clearButton = null;
        advancedStatisticsButton = null;
        // workoutIntensityButton = null; // Удаляем обнуление
        // db = null; // Не надо, если это синглтон
    }

    // Метод для создания адаптера вынесен отдельно
    private ArrayAdapter<String> createSpinnerAdapter() {
        String[] exercises = new String[]{"Становая тяга", "Жим лежа", "Присед"};
        // Используйте свой layout dropdown_item
        return new ArrayAdapter<>(
                requireContext(),
                R.layout.dropdown_item, // Или android.R.layout.simple_dropdown_item_1line
                exercises
        );
    }

    private String convertToDbFormat(String displayName) {
        switch (displayName) {
            case "Становая тяга": return "deadlift";
            case "Жим лежа": return "bench_press";
            case "Присед": return "squat";
            default: return displayName;
        }
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
                
                // Проверяем, выбраны ли обе даты
                checkDatesAndEnableSpinner();
                
                // Если выбрано упражнение и обе даты, загружаем данные
                if (startDate != -1 && endDate != -1 && exerciseTypeSpinner.getText() != null 
                        && !exerciseTypeSpinner.getText().toString().isEmpty()) {
                    String selectedExercise = exerciseTypeSpinner.getText().toString();
                    String exerciseType = convertToDbFormat(selectedExercise);
                    loadData(exerciseType);
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    // Новый метод для проверки дат и включения спиннера
    private void checkDatesAndEnableSpinner() {
        if (startDate != -1 && endDate != -1) {
            // Обе даты выбраны, включаем спиннер
            exerciseTypeSpinner.setEnabled(true);
            exerciseTypeSpinner.setHint("Выберите упражнение");
            
            // Проверяем, что начальная дата не больше конечной
            if (startDate > endDate) {
                Toast.makeText(requireContext(), 
                    "Начальная дата должна быть раньше конечной", 
                    Toast.LENGTH_LONG).show();
                
                // Сбрасываем даты
                startDate = -1;
                endDate = -1;
                startDateButton.setText("Начальная дата");
                endDateButton.setText("Конечная дата");
                exerciseTypeSpinner.setEnabled(false);
                exerciseTypeSpinner.setHint("Сначала выберите даты");
            }
        }
    }

    // Дополнительный метод для сброса всех фильтров
    private void resetFilters() {
        if (chart != null) {
            chart.clear(); // Добавляем очистку графика
            chart.invalidate(); // Обновляем пустой график
        }
        if (exerciseTypeSpinner != null) {
            exerciseTypeSpinner.setText("", false); // Очищаем текст без вызова слушателя
            exerciseTypeSpinner.clearFocus();
            // Можно также деактивировать спиннер, если нужно
            // exerciseTypeSpinner.setEnabled(false); // Раскомментировать, если нужно
        }
        startDate = -1;
        endDate = -1;
        if (startDateButton != null) {
            startDateButton.setText("Начальная дата");
        }
        if (endDateButton != null) {
            endDateButton.setText("Конечная дата");
        }
        // Перезагрузка данных (возможно, с пустыми фильтрами или по умолчанию)
        // loadData(null); // Или loadData("default_type") - зависит от логики
        // Пока просто очистим график, а загрузка произойдет при выборе нового упражнения
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
        
        // Настройка осей
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(ContextCompat.getColor(requireContext(), R.color.on_surface));
        xAxis.setDrawGridLines(true);
        xAxis.setGridColor(ContextCompat.getColor(requireContext(), R.color.gray_100));
        xAxis.setGranularity(1f);
        
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTextColor(ContextCompat.getColor(requireContext(), R.color.on_surface));
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridColor(ContextCompat.getColor(requireContext(), R.color.gray_100));
        leftAxis.setAxisMinimum(0f);
        
        chart.getAxisRight().setEnabled(false);
        
        // Настройка легенды
        Legend legend = chart.getLegend();
        legend.setTextColor(ContextCompat.getColor(requireContext(), R.color.on_surface));
        legend.setForm(Legend.LegendForm.LINE);
        
        // Настройка отсутствия данных
        chart.setNoDataText("Нет данных");
        chart.setNoDataTextColor(ContextCompat.getColor(requireContext(), R.color.on_surface));
    }

    private void loadData(String exerciseType) {
        Log.d(TAG, "Загрузка данных для типа: " + exerciseType);
        
        String dbExerciseType = convertToDbFormat(exerciseType);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                List<ExerciseResult> results;
                final long finalStartDate = startDate;
                final long finalEndDate = endDate;

                if (!"Все упражнения".equals(dbExerciseType)) {
                    // Фильтрация по конкретному типу упражнения
                    if (finalStartDate != -1 && finalEndDate != -1) {
                        Log.d(TAG, "Фильтр: " + dbExerciseType + ", даты: " + finalStartDate + " - " + finalEndDate);
                        results = db.exerciseResultDao().getResultsByTypeAndDateRange(dbExerciseType, finalStartDate, finalEndDate);
                    } else if (finalStartDate != -1) {
                        Log.d(TAG, "Фильтр: " + dbExerciseType + ", с даты: " + finalStartDate);
                        results = db.exerciseResultDao().getResultsByTypeFromDate(dbExerciseType, finalStartDate);
                    } else if (finalEndDate != -1) {
                        Log.d(TAG, "Фильтр: " + dbExerciseType + ", до даты: " + finalEndDate);
                        results = db.exerciseResultDao().getResultsByTypeToDate(dbExerciseType, finalEndDate);
                    } else {
                        Log.d(TAG, "Фильтр: только " + dbExerciseType);
                        results = db.exerciseResultDao().getResultsByType(dbExerciseType);
                    }
                } else {
                    // Загрузка для "Все упражнения"
                    if (finalStartDate != -1 && finalEndDate != -1) {
                        Log.d(TAG, "Фильтр: Все, даты: " + finalStartDate + " - " + finalEndDate);
                        results = db.exerciseResultDao().getResultsByDateRange(finalStartDate, finalEndDate);
                    } else if (finalStartDate != -1) {
                        Log.d(TAG, "Фильтр: Все, с даты: " + finalStartDate);
                        results = db.exerciseResultDao().getResultsFromDate(finalStartDate);
                    } else if (finalEndDate != -1) {
                        Log.d(TAG, "Фильтр: Все, до даты: " + finalEndDate);
                        results = db.exerciseResultDao().getResultsToDate(finalEndDate);
                    } else {
                        Log.d(TAG, "Фильтр: Все, без дат");
                        results = db.exerciseResultDao().getAllResults();
                    }
                }

                // Сортируем результаты по времени (от старых к новым для графика)
                Collections.sort(results, (r1, r2) -> 
                    Long.compare(r1.getTimestamp(), r2.getTimestamp()));
                
                final List<ExerciseResult> finalResults = results;
                
                requireActivity().runOnUiThread(() -> {
                    if (!finalResults.isEmpty()) {
                        updateLineChart(finalResults);
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
        executorService.shutdown();
    }

    private void updateLineChart(List<ExerciseResult> results) {
        List<Entry> entries = new ArrayList<>();
        if (results != null && !results.isEmpty()) {
            // Сортируем результаты по timestamp, чтобы график был корректным
            results.sort(Comparator.comparingLong(ExerciseResult::getTimestamp));
            for (ExerciseResult result : results) {
                entries.add(new Entry(result.getTimestamp(), (float) result.getResult()));
            }
        }

        if (entries.isEmpty()) {
            chart.clear();
            chart.setNoDataText("Нет данных за выбранный период");
            chart.setNoDataTextColor(Color.WHITE);
            chart.invalidate();
            return;
        }

        LineDataSet dataSet = new LineDataSet(entries, "Вес (кг)");
        dataSet.setColor(ContextCompat.getColor(requireContext(), R.color.primary));
        dataSet.setCircleColor(ContextCompat.getColor(requireContext(), R.color.secondary));
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(4f);
        dataSet.setDrawCircleHole(true);
        dataSet.setCircleHoleColor(ContextCompat.getColor(requireContext(), R.color.surface));
        dataSet.setValueTextColor(ContextCompat.getColor(requireContext(), R.color.on_background));
        dataSet.setValueTextSize(12f);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);

        // Настройка оси X
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(ContextCompat.getColor(requireContext(), R.color.on_surface));
        xAxis.setValueFormatter(new ValueFormatter() {
            private final SimpleDateFormat mFormat = new SimpleDateFormat("dd.MM.yy", Locale.getDefault());
            @Override
            public String getFormattedValue(float value) {
                return mFormat.format(new Date((long) value));
            }
        });
        xAxis.setLabelRotationAngle(45f);

        // Настройка оси Y
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTextColor(ContextCompat.getColor(requireContext(), R.color.on_surface));
        
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
        
        // Дополнительные настройки графика
        chart.getDescription().setEnabled(false);
        chart.setExtraBottomOffset(20f);
        chart.getLegend().setTextColor(ContextCompat.getColor(requireContext(), R.color.on_surface));
        chart.getLegend().setEnabled(true);
        chart.setGridBackgroundColor(Color.TRANSPARENT);
        chart.invalidate();
    }
}