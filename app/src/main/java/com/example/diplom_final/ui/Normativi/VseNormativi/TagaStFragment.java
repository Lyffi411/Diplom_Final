// Весь код из DvoboreFragment.java копируется сюда,
// затем применяются следующие изменения:

package com.example.diplom_final.ui.Normativi.VseNormativi;

// ... (все импорты из DvoboreFragment) ...
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
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


// 1. Переименовать класс
public class TagaStFragment extends Fragment {

    private TableLayout tableMen, tableWomen;
    private float scaleFactor = 1.0f;
    private float initialDistance = -1f;
    private float focusX, focusY;
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

    // Нормативы - не используются калькулятором, но оставим для единообразия
    private Map<String, Map<Integer, Double>> menNorms;
    private Map<String, Map<Integer, Double>> womenNorms;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // 2. Изменить используемый layout
        View view = inflater.inflate(R.layout.fragment_tagast, container, false);

        // Инициализация нормативов (Заглушка, калькулятор читает из XML)
        initializeNorms();

        // Инициализация views
        initializeViews(view);

        // Настройка функционала
        setupCalculator();
        setupTables();
        setupAgeStatistics(); // Использует заглушку
        setupTrainingTips();  // Использует заглушку
        setupProgressTracking(); // Изменит ключ SharedPreferences

        // Обработка касаний для таблиц
        if (tableMen != null) {
        tableMen.setOnTouchListener((v, event) -> handleTouch(event, tableMen));
        }
        if (tableWomen != null) {
        tableWomen.setOnTouchListener((v, event) -> handleTouch(event, tableWomen));
        }

        return view;
    }

    private void initializeViews(View view) {
        tableMen = view.findViewById(R.id.tableMen);
        tableWomen = view.findViewById(R.id.tableWeMen);
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

    private void initializeNorms() {
        // Заглушка - калькулятор берет данные из XML таблиц
        menNorms = new HashMap<>();
        womenNorms = new HashMap<>();
    }

    private void setupTables() {
        styleTable(tableMen);
        styleTable(tableWomen);
    }

    private void styleTable(TableLayout table) {
        // Код идентичен DvoboreFragment
         if (table == null) return;
        table.setBackgroundColor(getResources().getColor(R.color.background));
        table.setPadding(8, 8, 8, 8);
        for (int i = 0; i < table.getChildCount(); i++) {
            View view = table.getChildAt(i);
            if (view instanceof TableRow) {
                TableRow row = (TableRow) view;
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
                         if (cellParams == null) {
                           cellParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
                        }
                        cellParams.setMargins(2, 2, 2, 2);
                        textView.setLayoutParams(cellParams);
                        textView.setTextSize(12);
                        textView.setPadding(8, 4, 8, 4);
                        textView.setGravity(Gravity.CENTER);
                        if (i == 0) {
                            textView.setTypeface(null, Typeface.BOLD);
                            textView.setTextColor(getResources().getColor(R.color.on_background));
                            textView.setBackgroundColor(getResources().getColor(R.color.primary));
                        } else {
                            textView.setTextColor(getResources().getColor(R.color.on_surface));
                        }
                    }
                }
            }
        }
    }

    private void setupCalculator() {
         // Код идентичен DvoboreFragment
        if (calculateButton == null || weightInput == null || resultText == null) return;
        calculateButton.setOnClickListener(v -> {
            String weightStr = weightInput.getText().toString();
            if (!weightStr.isEmpty()) {
                try {
                    currentWeight = Double.parseDouble(weightStr);
                    calculateNormative(currentWeight);
                    updateTargetInfo();
                } catch (NumberFormatException e) {
                    Toast.makeText(requireContext(), "Пожалуйста, введите корректное значение веса", Toast.LENGTH_SHORT).show();
                }
            } else {
                 Toast.makeText(requireContext(), "Введите ваш вес", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void calculateNormative(double weight) {
        if (weight <= 0) {
            Toast.makeText(requireContext(), "Введите корректный вес", Toast.LENGTH_SHORT).show();
            resultText.setText(""); // Очищаем результат при некорректном весе
            return;
        }

        // Определяем весовую категорию для мужчин
        double menCategory;
        // !!! Используем категории из fragment_tagast.xml !!!
        if (weight <= 60) menCategory = 60;
        else if (weight <= 67.5) menCategory = 67.5;
        else if (weight <= 75) menCategory = 75;
        else if (weight <= 82.5) menCategory = 82.5;
        else if (weight <= 90) menCategory = 90;
        else if (weight <= 100) menCategory = 100;
        else if (weight <= 110) menCategory = 110;
        else if (weight <= 125) menCategory = 125;
        else menCategory = Double.POSITIVE_INFINITY; // Используем Infinity для "свыше"

        // Определяем весовую категорию для женщин
        double womenCategory;
        // !!! Используем категории из fragment_tagast.xml !!!
        if (weight <= 44) womenCategory = 44;
        else if (weight <= 48) womenCategory = 48;
        else if (weight <= 52) womenCategory = 52;
        else if (weight <= 56) womenCategory = 56;
        else if (weight <= 60) womenCategory = 60;
        else if (weight <= 67.5) womenCategory = 67.5;
        else if (weight <= 75) womenCategory = 75;
        else if (weight <= 82.5) womenCategory = 82.5;
        else womenCategory = Double.POSITIVE_INFINITY; // Используем Infinity для "свыше"

        // Находим соответствующие строки в таблицах
        TableRow menRow = findRowByWeight(tableMen, menCategory);
        TableRow womenRow = findRowByWeight(tableWomen, womenCategory);

        StringBuilder result = new StringBuilder();
        result.append("Для веса ").append(String.format(Locale.US, "%.1f", weight)).append(" кг:\n\n");

         // --- Обработка вывода категории для мужчин (адаптировано) ---
        String menCategoryLabel;
        if (menCategory == Double.POSITIVE_INFINITY) {
            menCategoryLabel = "свыше 125 кг"; // Жестко задано, т.к. нет чтения из XML
        } else {
            menCategoryLabel = "до " + String.format(Locale.US, "%.1f", menCategory) + " кг";
        }
        result.append("МУЖЧИНЫ (").append(menCategoryLabel).append("):\n");
        // --- Конец обработки ---

        if (menRow != null) {
            result.append("МСМК: ").append(getCellText(menRow, 3)).append(" кг\n");
            result.append("МС: ").append(getCellText(menRow, 4)).append(" кг\n");
            result.append("КМС: ").append(getCellText(menRow, 5)).append(" кг\n");
            result.append("I: ").append(getCellText(menRow, 6)).append(" кг\n");
            result.append("II: ").append(getCellText(menRow, 7)).append(" кг\n");
            result.append("III: ").append(getCellText(menRow, 8)).append(" кг\n");
        } else {
            result.append("Нормативы не найдены для данной весовой категории.\n");
        }

        // --- Обработка вывода категории для женщин (адаптировано) ---
        String womenCategoryLabel;
        if (womenCategory == Double.POSITIVE_INFINITY) {
            womenCategoryLabel = "свыше 82.5 кг"; // Жестко задано
        } else {
            womenCategoryLabel = "до " + String.format(Locale.US, "%.1f", womenCategory) + " кг";
        }
        result.append("\nЖЕНЩИНЫ (").append(womenCategoryLabel).append("):\n");
        // --- Конец обработки ---

        if (womenRow != null) {
            result.append("МСМК: ").append(getCellText(womenRow, 3)).append(" кг\n");
            result.append("МС: ").append(getCellText(womenRow, 4)).append(" кг\n");
            result.append("КМС: ").append(getCellText(womenRow, 5)).append(" кг\n");
            result.append("I: ").append(getCellText(womenRow, 6)).append(" кг\n");
            result.append("II: ").append(getCellText(womenRow, 7)).append(" кг\n");
            result.append("III: ").append(getCellText(womenRow, 8)).append(" кг");
        } else {
             result.append("Нормативы не найдены для данной весовой категории.");
        }

        resultText.setText(result.toString());
    }

     private String getCellText(TableRow row, int index) {
         // Код идентичен DvoboreFragment
        if (row != null && index >= 0 && index < row.getChildCount()) {
            View cell = row.getChildAt(index);
            if (cell instanceof TextView) {
                return ((TextView) cell).getText().toString();
            }
        }
        return "-"; // Возвращаем прочерк, если ячейка не найдена
    }

    private TableRow findRowByWeight(TableLayout table, double categoryWeight) {
        // Код ИЗ DvoboreFragment
        if (categoryWeight == 0) return null; // No row if weight is below min

        for (int i = 1; i < table.getChildCount(); i++) {
            View view = table.getChildAt(i);
            if (view instanceof TableRow) {
                TableRow row = (TableRow) view;
                TextView weightCell = (TextView) row.getChildAt(0);
                if (weightCell != null) {
                    String weightText = weightCell.getText().toString().toLowerCase();
                    if (categoryWeight == Double.POSITIVE_INFINITY) {
                         if (weightText.contains("св.") || weightText.contains("sv.") || weightText.contains("св") || weightText.contains(">")) {
                             return row;
                         }
                    } else {
                         try {
                            double rowWeight = parseDoubleSafe(weightText);
                            if (Math.abs(rowWeight - categoryWeight) < 0.01) {
                                return row;
                            }
                         } catch (NumberFormatException e) {
                             continue;
                         }
                    }
                }
            }
        }
        // Fallback for POSITIVE_INFINITY if text match failed (e.g., inconsistent 'sv.' text)
        if (categoryWeight == Double.POSITIVE_INFINITY && table.getChildCount() > 1) {
            return (TableRow) table.getChildAt(table.getChildCount() - 1);
        }
        return null;
    }

    private void setupAgeStatistics() {
        // Используем заглушку, как в DvoboreFragment, но с пометкой
         if (ageStatsRecyclerView == null) return;
        List<AgeStatItem> ageStats = new ArrayList<>();
        ageStats.add(new AgeStatItem("18-23 (М) - Становая", 200.0, 180.0, 160.0)); // Пример
        ageStats.add(new AgeStatItem("24-39 (М) - Становая", 220.0, 200.0, 170.0)); // Пример
        ageStats.add(new AgeStatItem("40+ (М) - Становая", 210.0, 190.0, 160.0));   // Пример
        ageStats.add(new AgeStatItem("18-23 (Ж) - Становая", 100.0, 90.0, 80.0));    // Пример
        ageStats.add(new AgeStatItem("24-39 (Ж) - Становая", 110.0, 100.0, 90.0));   // Пример
        ageStats.add(new AgeStatItem("40+ (Ж) - Становая", 100.0, 90.0, 80.0));     // Пример
        AgeStatsAdapter adapter = new AgeStatsAdapter(ageStats);
        ageStatsRecyclerView.setAdapter(adapter);
        ageStatsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void setupTrainingTips() {
         // Используем заглушку
        if (trainingTipsRecyclerView == null) return;
        List<TrainingTip> tips = new ArrayList<>();
        tips.add(new TrainingTip("Программа на силу (Становая)", "1. Становая тяга 3x5\n2. Приседания 3x8\n3. Жим ногами 3x10", "Фокус на увеличении силы в тяге"));
        tips.add(new TrainingTip("Программа на технику (Становая)", "1. Тяга до колен 5x3\n2. Тяга с плинтов 5x3\n3. Гиперэкстензия 3x12", "Улучшение отдельных фаз движения"));
        TrainingTipsAdapter adapter = new TrainingTipsAdapter(tips);
        trainingTipsRecyclerView.setAdapter(adapter);
        trainingTipsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void setupProgressTracking() {
        // 3. Изменить ключ SharedPreferences
        progressPrefs = requireContext().getSharedPreferences("progress_prefs_tagast", Context.MODE_PRIVATE);
        if (progressTrackingChart != null) {
            // ... (остальной код настройки графика идентичен DvoboreFragment) ...
             progressTrackingChart.setDrawGridBackground(false);
            progressTrackingChart.getDescription().setEnabled(false);
            progressTrackingChart.setTouchEnabled(true);
            progressTrackingChart.setDragEnabled(true);
            progressTrackingChart.setScaleEnabled(true);
            progressTrackingChart.setPinchZoom(true);
            progressTrackingChart.setNoDataText("Добавьте результаты для отслеживания прогресса");
            progressTrackingChart.setNoDataTextColor(getResources().getColor(R.color.on_background));

            XAxis xAxis = progressTrackingChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            xAxis.setTextColor(getResources().getColor(R.color.on_background));
            xAxis.setGranularity(1f);

            YAxis leftAxis = progressTrackingChart.getAxisLeft();
            leftAxis.setDrawGridLines(true);
            leftAxis.setGridColor(getResources().getColor(R.color.gray_300));
            leftAxis.setAxisMinimum(0f);
            leftAxis.setTextColor(getResources().getColor(R.color.on_background));

            progressTrackingChart.getAxisRight().setEnabled(false);
            progressTrackingChart.getLegend().setEnabled(false);

            loadProgressData(); // Загрузка данных
            updateTargetInfo(); // Обновление информации о цели
        }
    }

    private void loadProgressData() {
        // Код идентичен DvoboreFragment
         if (progressPrefs == null || progressTrackingChart == null) return;
        Map<String, ?> allEntries = progressPrefs.getAll();
        ArrayList<Entry> entries = new ArrayList<>();
        List<Long> dates = new ArrayList<>();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            try {
                long date = Long.parseLong(entry.getKey());
                float value = (Float) entry.getValue();
                dates.add(date);
            } catch (NumberFormatException | ClassCastException e) {
                // Ignore entries with invalid keys or values
            }
        }
        Collections.sort(dates);

        final List<String> dateLabels = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM", Locale.getDefault());
        for (int i = 0; i < dates.size(); i++) {
            long date = dates.get(i);
            float value = progressPrefs.getFloat(String.valueOf(date), 0f);
            entries.add(new Entry(i, value));
            dateLabels.add(sdf.format(new Date(date)));
        }

        LineDataSet dataSet;
        if (progressTrackingChart.getData() != null && progressTrackingChart.getData().getDataSetCount() > 0) {
            dataSet = (LineDataSet) progressTrackingChart.getData().getDataSetByIndex(0);
            dataSet.setValues(entries);
            progressTrackingChart.getData().notifyDataChanged();
            progressTrackingChart.notifyDataSetChanged();
        } else {
            dataSet = createSet();
             dataSet.setValues(entries);
            LineData lineData = new LineData(dataSet);
            progressTrackingChart.setData(lineData);
        }

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
        xAxis.setLabelCount(Math.min(6, entries.size()), true);
        xAxis.setLabelRotationAngle(-45);

        progressTrackingChart.invalidate();
    }

    private LineDataSet createSet() {
        // Код идентичен DvoboreFragment
        LineDataSet set = new LineDataSet(null, "Прогресс");
        set.setColor(getResources().getColor(R.color.primary));
        set.setLineWidth(2f);
        set.setCircleColor(getResources().getColor(R.color.primary));
        set.setCircleRadius(4f);
        set.setDrawCircleHole(false);
        set.setValueTextSize(0f);
        set.setDrawValues(false);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        return set;
    }

     private void showAddProgressDialog() {
         // Код идентичен DvoboreFragment
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_progress, null);

        DatePicker datePicker = dialogView.findViewById(R.id.datePicker);
        EditText weightResultInput = dialogView.findViewById(R.id.weightInput); // Переименовал для ясности

        builder.setView(dialogView)
               .setTitle("Добавить результат (Становая тяга)")
               .setPositiveButton("Сохранить", (dialog, id) -> {
                   try {
                       Calendar calendar = Calendar.getInstance();
                       calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                       long date = calendar.getTimeInMillis();

                       String resultStr = weightResultInput.getText().toString();
                       if (resultStr.isEmpty()) {
                            Toast.makeText(requireContext(), "Введите результат", Toast.LENGTH_SHORT).show();
                            return;
                       }
                       float resultValue = Float.parseFloat(resultStr);

                       saveProgress(date, resultValue);
                       loadProgressData(); // Перезагружаем данные после сохранения
                       updateTargetInfo(); // Обновляем инфо о цели
                   } catch (NumberFormatException e) {
                       Toast.makeText(requireContext(), "Пожалуйста, введите корректное значение результата", Toast.LENGTH_SHORT).show();
                   }
               })
               .setNegativeButton("Отмена", (dialog, id) -> dialog.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

     private void saveProgress(long date, float weight) {
         // Код идентичен DvoboreFragment
         if (progressPrefs != null) {
            SharedPreferences.Editor editor = progressPrefs.edit();
            editor.putFloat(String.valueOf(date), weight);
            editor.apply();
            Toast.makeText(requireContext(), "Результат сохранен", Toast.LENGTH_SHORT).show();
        }
    }

     private void updateTargetInfo() {
         // Код идентичен DvoboreFragment
        if (targetInfoText == null || tableMen == null) return;

        float lastResult = getLastResult();
        String targetText = "Введите свой вес и рассчитайте нормативы, чтобы увидеть цели.";

        if (currentWeight > 0) {
             double menCategoryWeight = findWeightCategory(tableMen, currentWeight);
             TableRow menRow = findRowByWeight(tableMen, menCategoryWeight);
             if (menRow != null) {
                 String nextRank = "-";
                 double nextRankValue = -1;

                // Определяем текущий разряд и цель
                double thirdRank = parseDoubleSafe(getCellText(menRow, 8));
                double secondRank = parseDoubleSafe(getCellText(menRow, 7));
                double firstRank = parseDoubleSafe(getCellText(menRow, 6));
                double cmsRank = parseDoubleSafe(getCellText(menRow, 5));
                double msRank = parseDoubleSafe(getCellText(menRow, 4));
                double msmkRank = parseDoubleSafe(getCellText(menRow, 3));

                if (lastResult < thirdRank) { nextRank = "III разряд"; nextRankValue = thirdRank; }
                else if (lastResult < secondRank) { nextRank = "II разряд"; nextRankValue = secondRank; }
                else if (lastResult < firstRank) { nextRank = "I разряд"; nextRankValue = firstRank; }
                else if (lastResult < cmsRank) { nextRank = "КМС"; nextRankValue = cmsRank; }
                else if (lastResult < msRank) { nextRank = "МС"; nextRankValue = msRank; }
                else if (lastResult < msmkRank) { nextRank = "МСМК"; nextRankValue = msmkRank; }
                else { nextRank = "Выше МСМК!"; }

                if (lastResult > 0) {
                     targetText = String.format(Locale.US, "Ваш последний результат: %.1f кг", lastResult);
                    if (nextRankValue > 0) {
                         targetText += String.format(Locale.US, "\nЦель: %s (%.1f кг). Осталось: %.1f кг",
                         nextRank, nextRankValue, Math.max(0, nextRankValue - lastResult));
                    } else if (nextRank.equals("Выше МСМК!")) {
                        targetText += "\nЦель: " + nextRank;
                    }
                } else {
                     targetText = "Добавьте свой первый результат!";
                     if (nextRankValue > 0) {
                         targetText += String.format(Locale.US, "\nБлижайшая цель: %s (%.1f кг).", nextRank, nextRankValue);
                     }
                 }
            } else {
                 if (lastResult > 0) {
                     targetText = String.format(Locale.US, "Ваш последний результат: %.1f кг", lastResult);
                 } else {
                      targetText = "Добавьте свой первый результат!";
                 }
            }
        }

        targetInfoText.setText(targetText);
    }

     private double parseDoubleSafe(String value) {
        // Код идентичен DvoboreFragment
         if (value == null || value.isEmpty() || value.equals("-")) {
            return 0.0;
        }
        try {
            return Double.parseDouble(value.replace(',', '.'));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

     private float getLastResult() {
        // Код идентичен DvoboreFragment
         if (progressPrefs == null) return 0;
        Map<String, ?> allProgress = progressPrefs.getAll();
        if (allProgress.isEmpty()) return 0;
        List<Long> dates = new ArrayList<>(allProgress.size());
         for (String dateStr : allProgress.keySet()) {
             try {
                dates.add(Long.parseLong(dateStr));
            } catch (NumberFormatException e) { /* ignore */ }
        }
        if (dates.isEmpty()) return 0;
        Collections.sort(dates);
        return progressPrefs.getFloat(String.valueOf(dates.get(dates.size() - 1)), 0f);
    }

    // --- Методы для обработки касаний и масштабирования (из DvoboreFragment) ---
    private boolean handleTouch(MotionEvent event, TableLayout table) {
         // Код идентичен DvoboreFragment
         int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                initialDistance = -1f;
                break;
                case MotionEvent.ACTION_POINTER_DOWN:
                if (event.getPointerCount() == 2) {
                    initialDistance = getFingerSpacing(event);
                    if (initialDistance > 10f) {
                        // Рассчитываем точку фокуса
                        focusX = (event.getX(0) + event.getX(1)) / 2;
                        focusY = (event.getY(0) + event.getY(1)) / 2;
                    }
                }
                    break;
                case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() == 2 && initialDistance > 10f) {
                    float newDistance = getFingerSpacing(event);
                    if (newDistance > 10f) {
                        scaleFactor = newDistance / initialDistance;
                        setScale(table, scaleFactor, focusX, focusY);
                    }
                    }
                    break;
            case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                    initialDistance = -1f;
                    break;
            }
        return true; // Важно вернуть true, чтобы обработать событие
    }

     private float getFingerSpacing(MotionEvent event) {
        // Код идентичен DvoboreFragment
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

     private void setScale(TableLayout table, float scale, float pivotX, float pivotY) {
        // Код идентичен DvoboreFragment
         // Ограничиваем масштабирование
        scale = Math.max(0.5f, Math.min(scale * table.getScaleX(), 3.0f)); // Пример ограничений 0.5x - 3x
        table.setScaleX(scale);
        table.setScaleY(scale);
        // Устанавливаем точку масштабирования
        table.setPivotX(pivotX);
        table.setPivotY(pivotY);
    }

    // --- ВОССТАНАВЛИВАЕМ МЕТОД findWeightCategory --- //
    private double findWeightCategory(TableLayout table, double currentWeight) {
        // Код ИЗ DvoboreFragment
        double categoryWeight = Double.MAX_VALUE;
        double minDiff = Double.MAX_VALUE;

        for (int i = 1; i < table.getChildCount(); i++) {
            View view = table.getChildAt(i);
            if (view instanceof TableRow) {
                TableRow row = (TableRow) view;
                TextView weightCell = (TextView) row.getChildAt(0);
                if (weightCell != null) {
                    String weightText = weightCell.getText().toString().toLowerCase();
                    double rowWeight;
                    if (weightText.contains("св.") || weightText.contains("sv.") || weightText.contains("св") || weightText.contains(">")) {
                        try {
                            String numPart = weightText.replaceAll("[^0-9.,]", "").replace(',', '.');
                            double threshold = Double.parseDouble(numPart);
                            if (currentWeight > threshold) {
                                categoryWeight = Double.POSITIVE_INFINITY;
                                break;
                            }
                            rowWeight = threshold + 0.1; // Treat 'over' as slightly above threshold for comparison
                        } catch (NumberFormatException e) {
                            continue;
                        }
                    } else {
                        try {
                            rowWeight = parseDoubleSafe(weightText);
                        } catch (NumberFormatException e) {
                            continue;
                        }
                    }
                    // Find the lowest category that is >= currentWeight
                    if (currentWeight <= rowWeight) {
                        double diff = rowWeight - currentWeight;
                        if (categoryWeight == Double.MAX_VALUE || diff < minDiff) {
                            minDiff = diff;
                            categoryWeight = rowWeight;
                         }
                    }
                }
            }
        }

        // Handle case where weight is above all defined categories (excluding 'sv.')
        if (categoryWeight == Double.MAX_VALUE && table.getChildCount() > 1) {
             TableRow lastRow = (TableRow) table.getChildAt(table.getChildCount() - 1);
             TextView lastWeightCell = (TextView) lastRow.getChildAt(0);
             String lastText = lastWeightCell.getText().toString().toLowerCase();
            if (lastText.contains("св.") || lastText.contains("sv.") || lastText.contains("св") || lastText.contains(">")) {
                try {
                     String numPart = lastText.replaceAll("[^0-9.,]", "").replace(',', '.');
                    double threshold = Double.parseDouble(numPart);
                    if (currentWeight > threshold) {
                         categoryWeight = Double.POSITIVE_INFINITY;
                    }
                } catch (NumberFormatException e) { /* Ignore parsing error here */ }
            }
         }

        // Handle case where weight is below the lowest category
        if (categoryWeight == Double.MAX_VALUE && table.getChildCount() > 1) {
            TableRow firstRow = (TableRow) table.getChildAt(1);
            TextView firstWeightCell = (TextView) firstRow.getChildAt(0);
            try {
                double firstCategory = parseDoubleSafe(firstWeightCell.getText().toString());
                 if (currentWeight < firstCategory) {
                     return 0.0; // Return 0 to indicate weight is below minimum
                 } else {
                     categoryWeight = firstCategory;
                 }
            } catch (NumberFormatException e) { return 0.0; /* Cannot determine, return 0 */}
        }

        return categoryWeight; // Return found category, 0, or POSITIVE_INFINITY
    }
    // --- КОНЕЦ ВОССТАНОВЛЕННОГО МЕТОДА --- //
}

