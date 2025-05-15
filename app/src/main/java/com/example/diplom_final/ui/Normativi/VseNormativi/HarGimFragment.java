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

public class HarGimFragment extends Fragment {

    private TableLayout tableMen, tableWomen;
    private float scaleFactor = 1.0f;
    private float initialDistance = -1f; // Начальное расстояние между пальцами
    private float focusX, focusY; // Точка фокуса для масштабирования
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
    private Map<String, Map<Integer, Double>> menNorms;
    private Map<String, Map<Integer, Double>> womenNorms;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nargim, container, false);

        initializeNorms();
        initializeViews(view);
        setupCalculator();
        setupTables();
        setupAgeStatistics(); 
        setupTrainingTips();  
        setupProgressTracking(); 

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
        menNorms = new HashMap<>();
        Map<Integer, Double> msNorms = new HashMap<>();
        msNorms.put(60, 155.0); 
        msNorms.put(75, 185.0);
        msNorms.put(90, 205.0);
        menNorms.put("MS", msNorms);

        Map<Integer, Double> cmsNorms = new HashMap<>();
        cmsNorms.put(60, 135.0);
        cmsNorms.put(75, 162.5);
        cmsNorms.put(90, 180.0);
        menNorms.put("CMS", cmsNorms);

        womenNorms = new HashMap<>();
        Map<Integer, Double> wmsNorms = new HashMap<>();
        wmsNorms.put(52, 75.0);
        wmsNorms.put(60, 85.0);
        womenNorms.put("MS", wmsNorms);

        Map<Integer, Double> wcmsNorms = new HashMap<>();
        wcmsNorms.put(52, 67.5);
        wcmsNorms.put(60, 75.0);
        womenNorms.put("CMS", wcmsNorms);
    }

    private void setupTables() {
        styleTable(tableMen);
        styleTable(tableWomen);
    }

    private void styleTable(TableLayout table) {
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
        if (calculateButton == null || weightInput == null || resultText == null) return;

        calculateButton.setOnClickListener(v -> {
            String weightStr = weightInput.getText().toString();
            if (!weightStr.isEmpty()) {
                try {
                    currentWeight = Double.parseDouble(weightStr);
                    calculateNormative(currentWeight);
                    updateTargetInfo();
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

    private void calculateNormative(double weight) {
        if (weight <= 0) {
            Toast.makeText(requireContext(), "Введите корректный вес", Toast.LENGTH_SHORT).show();
            resultText.setText(""); 
            return;
        }

        double menCategory;
        if (weight <= 60) menCategory = 60;
        else if (weight <= 67.5) menCategory = 67.5;
        else if (weight <= 75) menCategory = 75;
        else if (weight <= 82.5) menCategory = 82.5;
        else if (weight <= 90) menCategory = 90;
        else if (weight <= 100) menCategory = 100;
        else if (weight <= 110) menCategory = 110;
        else if (weight <= 125) menCategory = 125;
        else menCategory = Double.POSITIVE_INFINITY; 

        double womenCategory;
        if (weight <= 44) womenCategory = 44;
        else if (weight <= 48) womenCategory = 48;
        else if (weight <= 52) womenCategory = 52;
        else if (weight <= 56) womenCategory = 56;
        else if (weight <= 60) womenCategory = 60;
        else if (weight <= 67.5) womenCategory = 67.5;
        else if (weight <= 75) womenCategory = 75;
        else if (weight <= 82.5) womenCategory = 82.5;
        else womenCategory = Double.POSITIVE_INFINITY; 

        TableRow menRow = findRowByWeight(tableMen, menCategory);
        TableRow womenRow = findRowByWeight(tableWomen, womenCategory);

        StringBuilder result = new StringBuilder();
        result.append("Для веса ").append(String.format(Locale.US, "%.1f", weight)).append(" кг:\n\n");

        String menCategoryLabel;
        if (menCategory == Double.POSITIVE_INFINITY) {
            menCategoryLabel = "свыше 125 кг"; 
        } else {
            menCategoryLabel = "до " + String.format(Locale.US, "%.1f", menCategory) + " кг";
        }
        result.append("МУЖЧИНЫ (").append(menCategoryLabel).append("):\n");
        appendNormatives(result, menRow);

        String womenCategoryLabel;
        if (womenCategory == Double.POSITIVE_INFINITY) {
            womenCategoryLabel = "свыше 82.5 кг"; 
        } else {
            womenCategoryLabel = "до " + String.format(Locale.US, "%.1f", womenCategory) + " кг";
        }
        result.append("\nЖЕНЩИНЫ (").append(womenCategoryLabel).append("):\n");
        appendNormatives(result, womenRow);

        resultText.setText(result.toString());
    }

     private void appendNormatives(StringBuilder builder, TableRow row) {
        if (row != null) {
            builder.append("  МСМК: ").append(getCellText(row, 3)).append(" кг\n");
            builder.append("  МС:   ").append(getCellText(row, 4)).append(" кг\n");
            builder.append("  КМС:  ").append(getCellText(row, 5)).append(" кг\n");
            builder.append("  I:    ").append(getCellText(row, 6)).append(" кг\n");
            builder.append("  II:   ").append(getCellText(row, 7)).append(" кг\n");
            builder.append("  III:  ").append(getCellText(row, 8)).append(" кг\n");
        } else {
             builder.append("  Нет данных для этой весовой категории.\n");
        }
    }

     private String formatCategoryLabel(double categoryWeight, TableLayout table, boolean isMen) {
        if (categoryWeight == Double.POSITIVE_INFINITY) {
            TableRow lastRow = (table != null && table.getChildCount() > 1)
                           ? (TableRow) table.getChildAt(table.getChildCount() - 1)
                           : null;
            String lastWeightStr = getCellText(lastRow, 0);
            if (lastWeightStr != null && lastWeightStr.toLowerCase().contains("св")) {
                String numPart = lastWeightStr.replaceAll("[^0-9.,]", "").replace(',', '.');
                return "свыше " + numPart + " кг";
            } else {
                return isMen ? "(свыше 125 кг)" : "(свыше 82.5 кг)";
            }
        } else if (categoryWeight == 0.0) {
            return "(вес ниже мин.)";
        } else {
            return "до " + String.format(Locale.US, "%.1f", categoryWeight) + " кг";
        }
    }

    private String getCellText(TableRow row, int index) {
         if (row != null && index >= 0 && index < row.getChildCount()) {
            View cell = row.getChildAt(index);
            if (cell instanceof TextView) {
                return ((TextView) cell).getText().toString();
            }
        }
        return "-";
    }

    private double findWeightCategory(TableLayout table, double currentWeight) {
         if (table == null) return Double.MAX_VALUE;
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
                                return Double.POSITIVE_INFINITY;
                            }
                            rowWeight = threshold; 
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

        if (categoryWeight == Double.MAX_VALUE && table.getChildCount() > 1) {
            TableRow firstRow = (TableRow) table.getChildAt(1);
            TextView firstWeightCell = (TextView) firstRow.getChildAt(0);
            try {
                double firstCategory = parseDoubleSafe(firstWeightCell.getText().toString());
                if (currentWeight < firstCategory) {
                    return 0.0; 
                } else {
                     return Double.POSITIVE_INFINITY; 
                }
            } catch (NumberFormatException e) {
                return 0.0; 
            }
        }

        return categoryWeight;
    }

    private TableRow findRowByWeight(TableLayout table, double categoryWeight) {
         if (table == null) return null;

        if (categoryWeight == 0.0) return null; 

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
                            String cleanWeightText = weightText.replaceAll("(св\\.|sv\\.|св|>)", "").trim();
                            double rowWeight = parseDoubleSafe(cleanWeightText);
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
        if (categoryWeight == Double.POSITIVE_INFINITY && table.getChildCount() > 1) {
             return (TableRow) table.getChildAt(table.getChildCount() - 1);
        }

        return null; 
    }

    private void setupAgeStatistics() {
        if (ageStatsRecyclerView == null) return;
        List<AgeStatItem> ageStats = new ArrayList<>();
        ageStats.add(new AgeStatItem("18-23 (М) - Жим лежа", 160.0, 140.0, 120.0)); 
        ageStats.add(new AgeStatItem("24-39 (М) - Жим лежа", 170.0, 150.0, 130.0)); 
        ageStats.add(new AgeStatItem("40+ (М) - Жим лежа", 165.0, 145.0, 125.0)); 
        ageStats.add(new AgeStatItem("18-23 (Ж) - Жим лежа", 75.0, 65.0, 55.0));  
        ageStats.add(new AgeStatItem("24-39 (Ж) - Жим лежа", 80.0, 70.0, 60.0));  
        ageStats.add(new AgeStatItem("40+ (Ж) - Жим лежа", 75.0, 65.0, 55.0));  
        AgeStatsAdapter adapter = new AgeStatsAdapter(ageStats);
        ageStatsRecyclerView.setAdapter(adapter);
        ageStatsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void setupTrainingTips() {
        if (trainingTipsRecyclerView == null) return;
        List<TrainingTip> tips = new ArrayList<>();
        tips.add(new TrainingTip("Программа на силу (Жим лежа)", "Пн: Жим лежа 5x5\nСр: Жим узким хватом 4x8\nПт: Жим лежа 3x8", "Фокус на увеличении силы в жиме"));
        tips.add(new TrainingTip("Программа на массу (Жим лежа)", "Пн: Жим лежа 4x10\nСр: Жим гантелей 3x12\nПт: Жим под углом 4x10", "Для увеличения объема грудных мышц"));
        TrainingTipsAdapter adapter = new TrainingTipsAdapter(tips);
        trainingTipsRecyclerView.setAdapter(adapter);
        trainingTipsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void setupProgressTracking() {
        progressPrefs = requireContext().getSharedPreferences("nargim_progress_prefs", Context.MODE_PRIVATE);

         if (progressTrackingChart != null) {
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
            xAxis.setValueFormatter(new IndexAxisValueFormatter());

            YAxis leftAxis = progressTrackingChart.getAxisLeft();
            leftAxis.setDrawGridLines(true);
            leftAxis.setGridColor(getResources().getColor(R.color.gray_300));
            leftAxis.setAxisMinimum(0f);
            leftAxis.setTextColor(getResources().getColor(R.color.on_background));

            progressTrackingChart.getAxisRight().setEnabled(false);
            progressTrackingChart.getLegend().setEnabled(false);

            loadProgressData();
        }
        updateTargetInfo();
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
            entries.add(new Entry(i, value));
        }

        LineDataSet dataSet;
        if (progressTrackingChart.getData() != null &&
            progressTrackingChart.getData().getDataSetCount() > 0) {
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
         LineDataSet set = new LineDataSet(null, "Прогресс");
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
        EditText weightResultInput = dialogView.findViewById(R.id.weightInput);
        weightResultInput.setHint("Результат (кг)");

        builder.setView(dialogView)
               .setTitle("Добавить результат (Народный жим)")
               .setPositiveButton("Сохранить", (dialog, id) -> {
                   try {
                       Calendar calendar = Calendar.getInstance();
                       calendar.set(datePicker.getYear(),
                                  datePicker.getMonth(),
                                  datePicker.getDayOfMonth());
                       long date = calendar.getTimeInMillis();

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
                    .putFloat(String.valueOf(date), weight)
                    .apply();

            loadProgressData();
            updateTargetInfo();
            Toast.makeText(requireContext(), "Результат сохранен", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateTargetInfo() {
        if (targetInfoText == null || progressPrefs == null || tableMen == null) {
             if (targetInfoText != null) {
                 targetInfoText.setText("Введите свой вес и рассчитайте нормативы, чтобы увидеть цели.");
             }
            return;
        }

        float lastResult = getLastResult();
        double menCategoryWeight = findWeightCategory(tableMen, currentWeight);
        TableRow menRow = findRowByWeight(tableMen, menCategoryWeight);

        String currentRank = "-";
        String nextRank = "-";
        double nextTargetWeight = 0.0;
        String menCategoryLabel = formatCategoryLabel(menCategoryWeight, tableMen, true);

        if (menRow != null && menCategoryWeight != 0.0) { 
            double[] norms = new double[] {
                 parseDoubleSafe(getCellText(menRow, 8)), 
                 parseDoubleSafe(getCellText(menRow, 7)), 
                 parseDoubleSafe(getCellText(menRow, 6)), 
                 parseDoubleSafe(getCellText(menRow, 5)), 
                 parseDoubleSafe(getCellText(menRow, 4)), 
                 parseDoubleSafe(getCellText(menRow, 3))  
            };
            String[] ranks = new String[] {"III", "II", "I", "КМС", "МС", "МСМК"};

            currentRank = "Нет разряда"; 
            nextRank = "III"; 
            nextTargetWeight = norms[0];

             for (int i = norms.length - 1; i >= 0; i--) {
                 if (norms[i] > 0 && lastResult >= norms[i]) {
                    currentRank = ranks[i];
                    if (i < norms.length - 1) {
                        nextRank = ranks[i+1];
                        nextTargetWeight = norms[i+1];
                    } else {
                        nextRank = "Элита";
                        nextTargetWeight = norms[i] * 1.1; 
                    }
                    break; 
                 }
             }
             if (currentRank.equals("Нет разряда") && norms.length > 0 && norms[0] > 0) {
                 nextRank = ranks[0];
                 nextTargetWeight = norms[0];
             }
        }

        StringBuilder info = new StringBuilder();
        info.append(String.format("Ваш вес: %.1f кг (%s)\n", currentWeight, menCategoryLabel));
        if (lastResult > 0) {
            info.append(String.format("Последний результат: %.1f кг\n", lastResult));
            info.append(String.format("Текущий разряд (приблизительно): %s\n", currentRank));
            if (!nextRank.equals("-") && nextTargetWeight > 0) {
                info.append(String.format("До следующего разряда (%s - %.1f кг) осталось %.1f кг",
                                        nextRank, nextTargetWeight, Math.max(0, nextTargetWeight - lastResult)));
            }
        } else {
            info.append("Добавьте свой первый результат, чтобы увидеть прогресс и цели.\n");
             if (!nextRank.equals("-") && nextTargetWeight > 0) {
                  info.append(String.format("Цель для %s: %.1f кг", nextRank, nextTargetWeight));
             }
        }

        targetInfoText.setText(info.toString());
    }

    private double parseDoubleSafe(String value) {
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
        return progressPrefs.getFloat(String.valueOf(dates.get(dates.size() - 1)), 0f);
    }

    private boolean handleTouch(MotionEvent event, TableLayout table) {
         int pointerCount = event.getPointerCount();

        if (pointerCount == 2) {
            float x1 = event.getX(0);
            float y1 = event.getY(0);
            float x2 = event.getX(1);
            float y2 = event.getY(1);
            float currentDistance = (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            focusX = (x1 + x2) / 2;
            focusY = (y1 + y2) / 2;

            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_POINTER_DOWN:
                    initialDistance = currentDistance;
                    scaleFactor = table.getScaleX();
                    break;

                case MotionEvent.ACTION_MOVE:
                    if (initialDistance > 0) {
                        float scale = currentDistance / initialDistance;
                        setScale(table, scaleFactor * scale, focusX, focusY);
                    }
                    break;

                case MotionEvent.ACTION_POINTER_UP:
                    scaleFactor = table.getScaleX();
                    initialDistance = -1f;
                    break;
            }
        } else {
             initialDistance = -1f;
        }
        return true;
    }

    private void setScale(TableLayout table, float scale, float focusX, float focusY) {
         scale = Math.max(0.5f, Math.min(scale, 3.0f));
        table.setPivotX(focusX);
        table.setPivotY(focusY);
        table.setScaleX(scale);
        table.setScaleY(scale);
    }
}
