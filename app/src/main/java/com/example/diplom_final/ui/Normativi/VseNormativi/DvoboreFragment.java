package com.example.diplom_final.ui.Normativi.VseNormativi;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.diplom_final.R;
import com.example.diplom_final.ui.Normativi.VseNormativi.adapters.AgeStatsAdapter;
import com.example.diplom_final.ui.Normativi.VseNormativi.adapters.TrainingTipsAdapter;
import com.example.diplom_final.ui.Normativi.VseNormativi.models.AgeStatItem;
import com.example.diplom_final.ui.Normativi.VseNormativi.models.TrainingTip;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DvoboreFragment extends Fragment {

    private TableLayout tableMen, tableWomen;
    private float scaleFactor = 1.0f;
    private float initialDistance = -1f; // Начальное расстояние между пальцами
    private float focusX, focusY; // Точка фокуса для масштабирования

    // --- Добавленные поля из TroboreFragment --- //
    private TextInputEditText weightInput;
    private Button calculateButton;
    private TextView resultText;
    private LineChart progressTrackingChart;
    private RecyclerView ageStatsRecyclerView;
    private RecyclerView trainingTipsRecyclerView;
    private SharedPreferences progressPrefs;
    private TextView targetInfoText;
    private Button addProgressButton;
    private double currentWeight = 0;

    // Нормативы для разных разрядов (вес : результат) - ЗАГЛУШКА, ИСПОЛЬЗУЮТСЯ ДАННЫЕ ТРОЕБОРЬЯ!
    private Map<String, Map<Integer, Double>> menNorms;
    private Map<String, Map<Integer, Double>> womenNorms;
    // --- Конец добавленных полей --- //

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dvobore, container, false);

        // Инициализация нормативов (Используются данные Троеборья!)
        initializeNorms();

        // Инициализация views
        initializeViews(view);

        // Настройка функционала
        setupCalculator();
        setupTables();
        setupAgeStatistics();
        setupTrainingTips();
        setupProgressTracking();

        // Обработка касаний для таблиц (осталась из оригинального DvoboreFragment)
        if (tableMen != null) {
            tableMen.setOnTouchListener((v, event) -> handleTouch(event, tableMen));
        }
        if (tableWomen != null) {
            tableWomen.setOnTouchListener((v, event) -> handleTouch(event, tableWomen));
        }

        return view;
    }

    // --- Добавленные методы из TroboreFragment --- //

    private void initializeViews(View view) {
        // Инициализация таблиц
        tableMen = view.findViewById(R.id.tableMen);
        tableWomen = view.findViewById(R.id.tableWeMen);

        // Инициализация других views
        weightInput = view.findViewById(R.id.weightInput);
        calculateButton = view.findViewById(R.id.calculateButton);
        resultText = view.findViewById(R.id.resultText);
        progressTrackingChart = view.findViewById(R.id.progressTrackingChart);
        ageStatsRecyclerView = view.findViewById(R.id.ageStatsRecyclerView);
        trainingTipsRecyclerView = view.findViewById(R.id.trainingTipsRecyclerView);
        targetInfoText = view.findViewById(R.id.targetInfoText);
        addProgressButton = view.findViewById(R.id.addProgressButton);

        if (addProgressButton != null) {
            addProgressButton.setOnClickListener(v -> showAddProgressDialog());
        }
    }

    // !!! ВНИМАНИЕ: Используются нормативы Троеборья как заглушка. Заменить на нормативы Двоеборья! !!!
    private void initializeNorms() {
        // Инициализация норм для мужчин (данные троеборья)
        menNorms = new HashMap<>();
        Map<Integer, Double> msNorms = new HashMap<>();
        msNorms.put(60, 495.0); // Троеборье
        msNorms.put(67, 550.0);
        msNorms.put(75, 597.5);
        msNorms.put(82, 635.0);
        msNorms.put(90, 667.5);
        msNorms.put(100, 702.5);
        msNorms.put(110, 730.0);
        msNorms.put(125, 772.5);
        menNorms.put("MS", msNorms); // Пример: МС

        Map<Integer, Double> cmsNorms = new HashMap<>();
        cmsNorms.put(60, 432.5); // Троеборье
        cmsNorms.put(67, 482.5);
        cmsNorms.put(75, 532.5);
        cmsNorms.put(82, 567.5);
        cmsNorms.put(90, 597.5);
        cmsNorms.put(100, 630.0);
        cmsNorms.put(110, 665.0);
        cmsNorms.put(125, 692.5);
        menNorms.put("CMS", cmsNorms); // Пример: КМС

        // Инициализация норм для женщин (данные троеборья)
        womenNorms = new HashMap<>();
        Map<Integer, Double> wmsNorms = new HashMap<>();
        wmsNorms.put(44, 210.0); // Троеборье
        wmsNorms.put(48, 235.0);
        wmsNorms.put(52, 255.0);
        wmsNorms.put(56, 275.0);
        wmsNorms.put(60, 295.0);
        wmsNorms.put(67, 315.0);
        wmsNorms.put(75, 335.0);
        wmsNorms.put(82, 355.0);
        womenNorms.put("MS", wmsNorms); // Пример: МС

        Map<Integer, Double> wcmsNorms = new HashMap<>();
        wcmsNorms.put(44, 190.0); // Троеборье
        wcmsNorms.put(48, 205.0);
        wcmsNorms.put(52, 225.0);
        wcmsNorms.put(56, 245.0);
        wcmsNorms.put(60, 265.0);
        wcmsNorms.put(67, 285.0);
        wcmsNorms.put(75, 305.0);
        wcmsNorms.put(82, 325.0);
        womenNorms.put("CMS", wcmsNorms); // Пример: КМС
    }

    private void setupTables() {
        styleTable(tableMen);
        styleTable(tableWomen);
    }

    // Вспомогательный метод для стилизации таблиц
    private void styleTable(TableLayout table) {
        if (table == null) return;

        table.setBackgroundColor(getResources().getColor(R.color.background));
        table.setPadding(8, 8, 8, 8);

        for (int i = 0; i < table.getChildCount(); i++) {
            View view = table.getChildAt(i);
            if (view instanceof TableRow) {
                TableRow row = (TableRow) view;

                // Чередование цветов фона для строк
                if (i % 2 == 0) {
                    row.setBackgroundColor(getResources().getColor(R.color.surface));
                } else {
                    row.setBackgroundColor(getResources().getColor(R.color.gray_400));
                }

                for (int j = 0; j < row.getChildCount(); j++) {
                    View cell = row.getChildAt(j);
                    if (cell instanceof TextView) {
                        TextView textView = (TextView) cell;
                        TableRow.LayoutParams cellParams = (TableRow.LayoutParams) textView.getLayoutParams();
                        cellParams.setMargins(2, 2, 2, 2);
                        textView.setLayoutParams(cellParams);

                        textView.setTextSize(12);
                        textView.setPadding(8, 4, 8, 4);
                        textView.setGravity(Gravity.CENTER);

                        if (i == 0) {
                            // Стиль заголовка
                            textView.setTypeface(null, Typeface.BOLD);
                            textView.setTextColor(getResources().getColor(R.color.on_background));
                            textView.setBackgroundColor(getResources().getColor(R.color.primary));
                        } else {
                            // Стиль обычных ячеек
                            textView.setTextColor(getResources().getColor(R.color.on_surface));
                        }
                    }
                }
            }
        }
    }

    private void setupCalculator() {
        if (calculateButton == null || weightInput == null || resultText == null) return;

        calculateButton.setOnClickListener(v -> {
            String weightStr = weightInput.getText().toString();
            if (!weightStr.isEmpty()) {
                try {
                    currentWeight = Double.parseDouble(weightStr); // Сохраняем вес
                    calculateNormative(currentWeight);
                    updateTargetInfo(); // Обновляем информацию о целях после расчета
                } catch (NumberFormatException e) {
                    Toast.makeText(requireContext(),
                        "Пожалуйста, введите корректное значение веса",
                        Toast.LENGTH_SHORT).show();
                }
            } else {
                 Toast.makeText(requireContext(), "Введите ваш вес", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Адаптированный метод для двоеборья (использует данные из TableLayout)
    private void calculateNormative(double weight) {
        if (weight <= 0) {
            resultText.setText("Введите корректный вес.");
            return;
        }

        // Определяем весовую категорию для мужчин по таблице tableMen
        double menCategory = findWeightCategory(tableMen, weight);
        TableRow menRow = findRowByWeight(tableMen, menCategory);

        // Определяем весовую категорию для женщин по таблице tableWomen
        double womenCategory = findWeightCategory(tableWomen, weight);
        TableRow womenRow = findRowByWeight(tableWomen, womenCategory);

        StringBuilder result = new StringBuilder();
        result.append(String.format("Для веса %.1f кг:\n\n", weight));
        result.append("МУЖЧИНЫ (до ").append(menCategory).append(" кг):\n");
        if (menRow != null) {
            result.append("  МСМК: ").append(getCellText(menRow, 3)).append(" кг\n");
            result.append("  МС:   ").append(getCellText(menRow, 4)).append(" кг\n");
            result.append("  КМС:  ").append(getCellText(menRow, 5)).append(" кг\n");
            result.append("  I:    ").append(getCellText(menRow, 6)).append(" кг\n");
            result.append("  II:   ").append(getCellText(menRow, 7)).append(" кг\n");
            result.append("  III:  ").append(getCellText(menRow, 8)).append(" кг\n");
        } else {
             result.append("  Нет данных для этой весовой категории.\n");
        }

        result.append("\nЖЕНЩИНЫ (до ").append(womenCategory).append(" кг):\n");
        if (womenRow != null) {
            result.append("  МСМК: ").append(getCellText(womenRow, 3)).append(" кг\n");
            result.append("  МС:   ").append(getCellText(womenRow, 4)).append(" кг\n");
            result.append("  КМС:  ").append(getCellText(womenRow, 5)).append(" кг\n");
            result.append("  I:    ").append(getCellText(womenRow, 6)).append(" кг\n");
            result.append("  II:   ").append(getCellText(womenRow, 7)).append(" кг\n");
            result.append("  III:  ").append(getCellText(womenRow, 8)).append(" кг");
        } else {
             result.append("  Нет данных для этой весовой категории.\n");
        }

        resultText.setText(result.toString());
    }

    // Вспомогательный метод для получения текста ячейки
    private String getCellText(TableRow row, int index) {
        if (row != null && index < row.getChildCount()) {
            View cell = row.getChildAt(index);
            if (cell instanceof TextView) {
                return ((TextView) cell).getText().toString();
            }
        }
        return "-";
    }

    // Ищет подходящую весовую категорию (верхнюю границу) в таблице
    private double findWeightCategory(TableLayout table, double currentWeight) {
        if (table == null) return Double.MAX_VALUE;
        double category = Double.MAX_VALUE;
        double lastCategory = Double.MAX_VALUE;

        for (int i = 1; i < table.getChildCount(); i++) {
            View view = table.getChildAt(i);
            if (view instanceof TableRow) {
                TableRow row = (TableRow) view;
                TextView weightCell = (TextView) row.getChildAt(0);
                String weightStrRaw = weightCell.getText().toString();
                boolean isMaxCategory = weightStrRaw.startsWith("св.");
                String weightStr = weightStrRaw.replace("св.", "").trim();

                try {
                    double rowWeight = Double.parseDouble(weightStr);
                    lastCategory = rowWeight; // Запоминаем последнюю числовую категорию
                    if (isMaxCategory && currentWeight > rowWeight) {
                         return rowWeight; // Если 'свыше' и вес больше, то это та категория
                    }
                    if (currentWeight <= rowWeight) {
                        category = rowWeight;
                        break; // Нашли первую подходящую категорию
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
         // Если вес больше последней числовой категории, используем ее
        if (category == Double.MAX_VALUE && currentWeight > lastCategory) {
            return lastCategory;
        }

        return category;
    }

    // Ищет строку по точному значению весовой категории
    private TableRow findRowByWeight(TableLayout table, double categoryWeight) {
        if (table == null) return null;
        boolean lookingForMax = categoryWeight == Double.MAX_VALUE; // Флаг для поиска "св."

        for (int i = 1; i < table.getChildCount(); i++) {
            View view = table.getChildAt(i);
            if (view instanceof TableRow) {
                TableRow row = (TableRow) view;
                TextView weightCell = (TextView) row.getChildAt(0);
                String weightStrRaw = weightCell.getText().toString();
                boolean isMaxRow = weightStrRaw.startsWith("св.");
                String weightStr = weightStrRaw.replace("св.", "").trim();

                try {
                    double rowWeight = Double.parseDouble(weightStr);
                    if (lookingForMax && isMaxRow) {
                        return row; // Нашли строку "св."
                    } else if (!lookingForMax && !isMaxRow && rowWeight == categoryWeight) {
                        return row; // Нашли строку с точным весом
                    } else if (isMaxRow && categoryWeight == rowWeight) {
                        // Если ищем категорию, которая совпадает с числом в "св.", возвращаем ее
                         return row;
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
        // Если искали категорию 'свыше' и не нашли, возвращаем последнюю строку
        if (lookingForMax && table.getChildCount() > 1) {
            View lastDataRow = table.getChildAt(table.getChildCount() - 1);
             if (lastDataRow instanceof TableRow) {
                 return (TableRow) lastDataRow;
             }
        }

        return null;
    }


    private void setupAgeStatistics() {
        if (ageStatsRecyclerView == null) return;
        // ЗАГЛУШКА: Используются те же данные, что и для Троеборья
        List<AgeStatItem> ageStats = new ArrayList<>();
        ageStats.add(new AgeStatItem("18-23 (М)", 377.5, 327.5, 282.5)); // Взяты из таблицы Двоеборья (60 кг)
        ageStats.add(new AgeStatItem("24-39 (М)", 410.0, 355.0, 315.0)); // (82.5 кг)
        ageStats.add(new AgeStatItem("40+ (М)", 380.0, 330.0, 282.5)); // (125 кг)
        ageStats.add(new AgeStatItem("18-23 (Ж)", 190.0, 172.5, 150.0)); // (44 кг)
        ageStats.add(new AgeStatItem("24-39 (Ж)", 232.5, 210.0, 182.5)); // (56 кг)
        ageStats.add(new AgeStatItem("40+ (Ж)", 267.5, 240.0, 210.0)); // (67.5 кг)

        AgeStatsAdapter adapter = new AgeStatsAdapter(ageStats);
        ageStatsRecyclerView.setAdapter(adapter);
        ageStatsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void setupTrainingTips() {
         if (trainingTipsRecyclerView == null) return;
        // ЗАГЛУШКА: Используются те же данные, что и для Троеборья
        List<TrainingTip> tips = new ArrayList<>();
        tips.add(new TrainingTip(
            "Программа на силу (Двоеборье)",
            "Пн: Жим лежа (тяжелый) 5x5, Тяга (легкая) 3x8\n" +
            "Ср: Жим лежа (легкий) 3x8, Тяга (тяжелая) 5x5\n" +
            "Пт: Жим лежа (средний) 4x6, Тяга (средняя) 4x6",
            "Фокус на базовых движениях"
        ));
        tips.add(new TrainingTip(
            "Программа на массу (Двоеборье)",
            "Пн: Жим лежа 4x8-12, Жим под углом 3x10\n" +
            "Ср: Становая тяга 4x6-8, Тяга в наклоне 3x10\n" +
            "Пт: Жим лежа 3x10-15, Тяга блока 3x12",
            "Для увеличения мышечной массы"
        ));

        TrainingTipsAdapter adapter = new TrainingTipsAdapter(tips);
        trainingTipsRecyclerView.setAdapter(adapter);
        trainingTipsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void setupProgressTracking() {
        // Используем уникальный ключ для двоеборья
        progressPrefs = requireContext().getSharedPreferences("dvobore_progress_prefs", Context.MODE_PRIVATE);

        if (progressTrackingChart != null) {
            // Базовая настройка графика
            progressTrackingChart.setDrawGridBackground(false);
            progressTrackingChart.getDescription().setEnabled(false);
            progressTrackingChart.setTouchEnabled(true);
            progressTrackingChart.setDragEnabled(true);
            progressTrackingChart.setScaleEnabled(true);
            progressTrackingChart.setPinchZoom(true);
            progressTrackingChart.setNoDataText("Добавьте результаты для отслеживания прогресса");
            progressTrackingChart.setNoDataTextColor(getResources().getColor(R.color.on_background));

            // Настройка осей
            XAxis xAxis = progressTrackingChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            xAxis.setTextColor(getResources().getColor(R.color.on_background));
            xAxis.setGranularity(1f); // Шаг оси X
            xAxis.setValueFormatter(new IndexAxisValueFormatter()); // Показывать номер точки как метку

            YAxis leftAxis = progressTrackingChart.getAxisLeft();
            leftAxis.setDrawGridLines(true);
            leftAxis.setGridColor(getResources().getColor(R.color.gray_300));
            leftAxis.setAxisMinimum(0f);
            leftAxis.setTextColor(getResources().getColor(R.color.on_background));

            progressTrackingChart.getAxisRight().setEnabled(false);
            progressTrackingChart.getLegend().setEnabled(false);

            loadProgressData(); // Загрузка и отображение данных
        }
        updateTargetInfo(); // Обновляем инфо о целях при инициализации
    }

    private void loadProgressData() {
        if (progressTrackingChart == null || progressPrefs == null) return;

        ArrayList<Entry> entries = new ArrayList<>();
        Map<String, ?> allProgress = progressPrefs.getAll();
        List<Long> dates = new ArrayList<>();

        for (String dateStr : allProgress.keySet()) {
            try {
                dates.add(Long.parseLong(dateStr));
            } catch (NumberFormatException e) { /* ignore */ }
        }
        Collections.sort(dates);

        for (int i = 0; i < dates.size(); i++) {
            float value = progressPrefs.getFloat(String.valueOf(dates.get(i)), 0f);
            entries.add(new Entry(i, value)); // Используем индекс как X
        }

        LineDataSet dataSet;
        if (progressTrackingChart.getData() != null &&
            progressTrackingChart.getData().getDataSetCount() > 0) {
            dataSet = (LineDataSet) progressTrackingChart.getData().getDataSetByIndex(0);
            dataSet.setValues(entries);
            progressTrackingChart.getData().notifyDataChanged();
            progressTrackingChart.notifyDataSetChanged();
        } else {
            dataSet = createSet(); // Создаем новый набор данных
             dataSet.setValues(entries);
            LineData lineData = new LineData(dataSet);
            progressTrackingChart.setData(lineData);
        }

        // Настройка оси X для отображения дат
        XAxis xAxis = progressTrackingChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            private final SimpleDateFormat mFormat = new SimpleDateFormat("dd.MM", Locale.getDefault());
            @Override
            public String getFormattedValue(float value) {
                int index = (int) value;
                if (index >= 0 && index < dates.size()) {
                    return mFormat.format(new Date(dates.get(index)));
                }
                return "";
            }
        });
        xAxis.setLabelCount(Math.min(6, entries.size()), true); // Ограничиваем кол-во меток
        xAxis.setLabelRotationAngle(-45);

        // Настройка подсказок (MarkerView) - Опционально, но полезно
        // CustomMarkerView mv = new CustomMarkerView(requireContext(), R.layout.custom_marker_view_layout, dateLabels);

        progressTrackingChart.invalidate(); // Обновляем график
    }

    private LineDataSet createSet() {
        LineDataSet set = new LineDataSet(null, "Progress");
        set.setColor(getResources().getColor(R.color.primary));
        set.setLineWidth(2f);
        set.setCircleColor(getResources().getColor(R.color.primary));
        set.setCircleRadius(4f);
        set.setDrawCircleHole(false);
        set.setValueTextSize(10f);
        set.setValueTextColor(getResources().getColor(R.color.on_background));
        set.setDrawValues(true);
        set.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.format(Locale.US, "%.1f", value);
            }
        });
        return set;
    }

    private void showAddProgressDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_progress, null);

        DatePicker datePicker = dialogView.findViewById(R.id.datePicker);
        EditText weightResultInput = dialogView.findViewById(R.id.weightInput); // Используем то же ID для ввода результата
        weightResultInput.setHint("Результат (кг)"); // Меняем подсказку

        builder.setView(dialogView)
               .setTitle("Добавить результат (Двоеборье)")
               .setPositiveButton("Сохранить", (dialog, id) -> {
                   try {
                       Calendar calendar = Calendar.getInstance();
                       calendar.set(datePicker.getYear(),
                                  datePicker.getMonth(),
                                  datePicker.getDayOfMonth()); // УБИРАЕМ ОБНУЛЕНИЕ ВРЕМЕНИ
                       long date = calendar.getTimeInMillis(); // Используем полный timestamp

                       float weight = Float.parseFloat(weightResultInput.getText().toString());

                       saveProgress(date, weight);
                   } catch (NumberFormatException e) {
                       Toast.makeText(requireContext(),
                                    "Пожалуйста, введите корректное значение результата",
                                    Toast.LENGTH_SHORT).show();
                   }
               })
               .setNegativeButton("Отмена", (dialog, id) -> dialog.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void saveProgress(long date, float weight) {
        if (progressPrefs != null) {
            progressPrefs.edit()
                    .putFloat(String.valueOf(date), weight) // Используем дату как ключ
                    .apply();

            loadProgressData(); // Перезагружаем данные для обновления графика
            updateTargetInfo(); // Обновляем информацию о целях
            Toast.makeText(requireContext(), "Результат сохранен", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateTargetInfo() {
        if (targetInfoText == null || progressPrefs == null || menNorms == null || currentWeight <= 0) {
            if (targetInfoText != null) {
                targetInfoText.setText("Введите свой вес и рассчитайте нормативы, чтобы увидеть цели.");
            }
             return;
        }

        float lastResult = getLastResult();
        double menCategory = findWeightCategory(tableMen, currentWeight);
        TableRow menRow = findRowByWeight(tableMen, menCategory);

        String targetRank = "-";
        double targetWeight = 0.0;
        String nextRank = "-";
        double nextTargetWeight = 0.0;

        if (menRow != null) {
            // Определяем текущий и следующий норматив
            double[] norms = new double[] {
                 parseDoubleSafe(getCellText(menRow, 8)), // III
                 parseDoubleSafe(getCellText(menRow, 7)), // II
                 parseDoubleSafe(getCellText(menRow, 6)), // I
                 parseDoubleSafe(getCellText(menRow, 5)), // КМС
                 parseDoubleSafe(getCellText(menRow, 4)), // МС
                 parseDoubleSafe(getCellText(menRow, 3))  // МСМК
            };
            String[] ranks = new String[] {"III", "II", "I", "КМС", "МС", "МСМК"};

             for (int i = 0; i < norms.length; i++) {
                if (lastResult < norms[i]) {
                    nextRank = ranks[i];
                    nextTargetWeight = norms[i];
                    if (i > 0) {
                        targetRank = ranks[i-1];
                        targetWeight = norms[i-1];
                    } else {
                         targetRank = "Нет разряда";
                         targetWeight = 0;
                    }
                    break;
                }
                 // Если результат равен или больше МСМК
                if (i == norms.length - 1 && lastResult >= norms[i]) {
                    targetRank = ranks[i];
                    targetWeight = norms[i];
                    nextRank = "Элита"; // Условно
                    nextTargetWeight = norms[i] * 1.1; // Условно
                }
            }
        }

        StringBuilder info = new StringBuilder();
        info.append(String.format("Ваш вес: %.1f кг (Категория до %.1f кг)\n", currentWeight, menCategory));
        if (lastResult > 0) {
            info.append(String.format("Последний результат: %.1f кг\n", lastResult));
            info.append(String.format("Текущий разряд (приблизительно): %s\n", targetRank));
            if (!nextRank.equals("-")) {
                info.append(String.format("До следующего разряда (%s - %.1f кг) осталось %.1f кг",
                                        nextRank, nextTargetWeight, Math.max(0, nextTargetWeight - lastResult)));
            }
        } else {
            info.append("Добавьте свой первый результат, чтобы увидеть прогресс и цели.\n");
             if (!nextRank.equals("-")) {
                  info.append(String.format("Цель для III разряда: %.1f кг", nextTargetWeight));
             }
        }

        targetInfoText.setText(info.toString());
    }

    private double parseDoubleSafe(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException | NullPointerException e) {
            return 0.0; // Возвращаем 0, если парсинг не удался
        }
    }

    private float getLastResult() {
        if (progressPrefs == null) return 0;

        Map<String, ?> allProgress = progressPrefs.getAll();
        if (allProgress.isEmpty()) return 0;

        List<Long> dates = new ArrayList<>();
        for (String dateStr : allProgress.keySet()) {
             try {
                dates.add(Long.parseLong(dateStr));
            } catch (NumberFormatException e) { /* ignore */ }
        }
        if (dates.isEmpty()) return 0;

        Collections.sort(dates);
        // Возвращаем значение для самой последней даты
        return progressPrefs.getFloat(String.valueOf(dates.get(dates.size() - 1)), 0f);
    }

    // --- Конец добавленных методов --- //

    // --- Существующие методы масштабирования --- //
    private boolean handleTouch(MotionEvent event, TableLayout table) {
        // Определяем количество пальцев на экране
        int pointerCount = event.getPointerCount();

        if (pointerCount == 2) {
            // Получаем координаты двух пальцев
            float x1 = event.getX(0);
            float y1 = event.getY(0);
            float x2 = event.getX(1);
            float y2 = event.getY(1);

            // Вычисляем расстояние между пальцами
            float currentDistance = (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

            // Определяем точку фокуса (центр между пальцами)
            focusX = (x1 + x2) / 2;
            focusY = (y1 + y2) / 2;

            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_POINTER_DOWN:
                    // Начало жеста: сохраняем начальное расстояние
                    initialDistance = currentDistance;
                    // Важно сохранить текущий масштаб перед началом нового жеста
                    scaleFactor = table.getScaleX();
                    break;

                case MotionEvent.ACTION_MOVE:
                    if (initialDistance > 0) {
                        // Вычисляем коэффициент масштабирования относительно начального расстояния
                        float scale = currentDistance / initialDistance;
                        // Применяем масштабирование к сохраненному scaleFactor
                        setScale(table, scaleFactor * scale, focusX, focusY);
                    }
                    break;

                case MotionEvent.ACTION_POINTER_UP:
                    // Конец жеста: обновляем scaleFactor и сбрасываем initialDistance
                    scaleFactor = table.getScaleX();
                    initialDistance = -1f;
                    break;
            }
        } else {
             // Если пальцев не два, сбрасываем состояние масштабирования
             initialDistance = -1f;
        }

        // Возвращаем true, чтобы событие не передавалось дальше
        // (например, родительскому ScrollView, если он есть)
        return true;
    }

    private void setScale(TableLayout table, float scale, float focusX, float focusY) {
        // Ограничение масштаба
        scale = Math.max(0.5f, Math.min(scale, 3.0f));

        // Применение масштаба относительно точки фокуса
        table.setPivotX(focusX);
        table.setPivotY(focusY);
        table.setScaleX(scale);
        table.setScaleY(scale);
    }
    // --- Конец существующих методов масштабирования --- //
}
