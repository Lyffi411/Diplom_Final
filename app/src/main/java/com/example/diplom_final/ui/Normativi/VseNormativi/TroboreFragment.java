package com.example.diplom_final.ui.Normativi.VseNormativi;

import androidx.appcompat.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TableRow;
import android.os.Handler;
import android.view.Gravity;
import android.graphics.Typeface;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.R;
import com.example.diplom_final.ui.Normativi.VseNormativi.models.AgeStatItem;
import com.example.diplom_final.ui.Normativi.VseNormativi.models.TrainingTip;
import com.example.diplom_final.ui.Normativi.VseNormativi.adapters.AgeStatsAdapter;
import com.example.diplom_final.ui.Normativi.VseNormativi.adapters.TrainingTipsAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TroboreFragment extends Fragment {

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

    // Нормативы для разных разрядов (вес : результат)
    private Map<String, Map<Integer, Double>> menNorms;
    private Map<String, Map<Integer, Double>> womenNorms;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                           @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trobore, container, false);

        // Инициализируем нормативы
        initializeNorms();

        // Инициализация views
        initializeViews(view);

        // Настройка функционала
        setupCalculator();
        setupTables();
        setupAgeStatistics();
        setupTrainingTips();
        setupProgressTracking();

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

    private void initializeNorms() {
        // Инициализация норм для мужчин
        menNorms = new HashMap<>();
        Map<Integer, Double> msNorms = new HashMap<>();
        msNorms.put(56, 450.0);
        msNorms.put(62, 480.0);
        msNorms.put(69, 510.0);
        msNorms.put(77, 535.0);
        msNorms.put(85, 555.0);
        msNorms.put(94, 575.0);
        msNorms.put(105, 590.0);
        msNorms.put(120, 605.0);
        menNorms.put("MS", msNorms);

        Map<Integer, Double> cmsNorms = new HashMap<>();
        cmsNorms.put(56, 380.0);
        cmsNorms.put(62, 405.0);
        cmsNorms.put(69, 430.0);
        cmsNorms.put(77, 450.0);
        cmsNorms.put(85, 470.0);
        cmsNorms.put(94, 485.0);
        cmsNorms.put(105, 500.0);
        cmsNorms.put(120, 510.0);
        menNorms.put("CMS", cmsNorms);

        // Инициализация норм для женщин
        womenNorms = new HashMap<>();
        Map<Integer, Double> wmsNorms = new HashMap<>();
        wmsNorms.put(48, 320.0);
        wmsNorms.put(53, 340.0);
        wmsNorms.put(58, 360.0);
        wmsNorms.put(63, 380.0);
        wmsNorms.put(69, 400.0);
        wmsNorms.put(76, 415.0);
        wmsNorms.put(84, 430.0);
        womenNorms.put("MS", wmsNorms);

        Map<Integer, Double> wcmsNorms = new HashMap<>();
        wcmsNorms.put(48, 270.0);
        wcmsNorms.put(53, 285.0);
        wcmsNorms.put(58, 300.0);
        wcmsNorms.put(63, 315.0);
        wcmsNorms.put(69, 330.0);
        wcmsNorms.put(76, 345.0);
        wcmsNorms.put(84, 360.0);
        womenNorms.put("CMS", wcmsNorms);
    }

    private void setupTables() {
        // Удаляем старое содержимое и вызываем styleTable
        styleTable(tableMen);
        styleTable(tableWomen);
    }

    // Модифицированный метод styleTable
    private void styleTable(TableLayout table) {
        if (table == null) return;

        // Используем цвет из resources вместо жестко закодированного
        table.setBackgroundColor(getResources().getColor(R.color.background));
        table.setPadding(8, 8, 8, 8);

        for (int i = 0; i < table.getChildCount(); i++) {
            View view = table.getChildAt(i);
            if (view instanceof TableRow) {
                TableRow row = (TableRow) view;

                // Чередование цветов фона для строк, используя surface и gray_400
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
                        // Убедимся, что cellParams не null, если TextView был добавлен динамически без layoutParams
                        if (cellParams == null) {
                           cellParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
                        }
                        cellParams.setMargins(2, 2, 2, 2);
                        textView.setLayoutParams(cellParams);

                        textView.setTextSize(12); // Уменьшаем размер текста
                        textView.setPadding(8, 4, 8, 4);
                        textView.setGravity(Gravity.CENTER);

                        if (i == 0) {
                            // Стиль заголовка - используем primary вместо розового
                            textView.setTypeface(null, Typeface.BOLD);
                            textView.setTextColor(getResources().getColor(R.color.on_background));
                            textView.setBackgroundColor(getResources().getColor(R.color.primary));
                        } else {
                            // Стиль обычных ячеек - используем on_surface для текста
                            textView.setTextColor(getResources().getColor(R.color.on_surface));
                        }
                    }
                }
            }
        }
    }

    private boolean handleTouch(MotionEvent event, TableLayout table) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                scaleFactor = table.getScaleX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() == 2) {
                    float newDistance = getFingerSpacing(event);
                    if (initialDistance > 0) {
                        float scale = scaleFactor * (newDistance / initialDistance);
                        setScale(table, scale, focusX, focusY);
                    }
                    initialDistance = newDistance;
                    focusX = event.getX(0);
                    focusY = event.getY(0);
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (event.getPointerCount() == 2) {
                    initialDistance = getFingerSpacing(event);
                    focusX = event.getX(0);
                    focusY = event.getY(0);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                initialDistance = -1;
                break;
        }
        return true;
    }

    private float getFingerSpacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private void setScale(TableLayout table, float scale, float focusX, float focusY) {
        scale = Math.max(0.5f, Math.min(scale, 3.0f));
        table.setPivotX(focusX);
        table.setPivotY(focusY);
        table.setScaleX(scale);
        table.setScaleY(scale);
    }

    private void setupCalculator() {
        if (calculateButton == null || weightInput == null || resultText == null) return;

        calculateButton.setOnClickListener(v -> {
            String weightStr = weightInput.getText().toString();
            if (!weightStr.isEmpty()) {
                try {
                    double weight = Double.parseDouble(weightStr);
                    calculateNormative(weight);
                } catch (NumberFormatException e) {
                    Toast.makeText(requireContext(),
                        "Пожалуйста, введите корректное значение веса",
                        Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void calculateNormative(double weight) {
        if (weight <= 0) {
            Toast.makeText(requireContext(), "Введите корректный вес", Toast.LENGTH_SHORT).show();
            return;
        }

        // Определяем весовую категорию для мужчин
        double menCategory;
        if (weight <= 60) menCategory = 60;
        else if (weight <= 67.5) menCategory = 67.5;
        else if (weight <= 75) menCategory = 75;
        else if (weight <= 82.5) menCategory = 82.5;
        else if (weight <= 90) menCategory = 90;
        else if (weight <= 100) menCategory = 100;
        else if (weight <= 110) menCategory = 110;
        else if (weight <= 125) menCategory = 125;
        else menCategory = 125;

        // Определяем весовую категорию для женщин
        double womenCategory;
        if (weight <= 44) womenCategory = 44;
        else if (weight <= 48) womenCategory = 48;
        else if (weight <= 52) womenCategory = 52;
        else if (weight <= 56) womenCategory = 56;
        else if (weight <= 60) womenCategory = 60;
        else if (weight <= 67.5) womenCategory = 67.5;
        else if (weight <= 75) womenCategory = 75;
        else if (weight <= 82.5) womenCategory = 82.5;
        else womenCategory = 82.5;

        // Находим соответствующие строки в таблицах
        TableRow menRow = findRowByWeight(tableMen, menCategory);
        TableRow womenRow = findRowByWeight(tableWomen, womenCategory);

        StringBuilder result = new StringBuilder();
        result.append("Мужские нормативы (до ").append(menCategory).append(" кг):\n");
        if (menRow != null) {
            result.append("МСМК: ").append(((TextView)menRow.getChildAt(3)).getText()).append(" кг\n");
            result.append("МС: ").append(((TextView)menRow.getChildAt(4)).getText()).append(" кг\n");
            result.append("КМС: ").append(((TextView)menRow.getChildAt(5)).getText()).append(" кг\n");
            result.append("I: ").append(((TextView)menRow.getChildAt(6)).getText()).append(" кг\n");
            result.append("II: ").append(((TextView)menRow.getChildAt(7)).getText()).append(" кг\n");
            result.append("III: ").append(((TextView)menRow.getChildAt(8)).getText()).append(" кг\n");
        }

        result.append("\nЖенские нормативы (до ").append(womenCategory).append(" кг):\n");
        if (womenRow != null) {
            result.append("МСМК: ").append(((TextView)womenRow.getChildAt(3)).getText()).append(" кг\n");
            result.append("МС: ").append(((TextView)womenRow.getChildAt(4)).getText()).append(" кг\n");
            result.append("КМС: ").append(((TextView)womenRow.getChildAt(5)).getText()).append(" кг\n");
            result.append("I: ").append(((TextView)womenRow.getChildAt(6)).getText()).append(" кг\n");
            result.append("II: ").append(((TextView)womenRow.getChildAt(7)).getText()).append(" кг\n");
            result.append("III: ").append(((TextView)womenRow.getChildAt(8)).getText()).append(" кг");
        }

        resultText.setText(result.toString());
    }

    private TableRow findRowByWeight(TableLayout table, double weight) {
        if (table == null) return null;

        for (int i = 1; i < table.getChildCount(); i++) {
            View view = table.getChildAt(i);
            if (view instanceof TableRow) {
                TableRow row = (TableRow) view;
                TextView weightCell = (TextView) row.getChildAt(0);
                String weightStr = weightCell.getText().toString().replace("св.", "");
                try {
                    double rowWeight = Double.parseDouble(weightStr);
                    if (rowWeight == weight) {
                        return row;
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
        return null;
    }

    private void setupAgeStatistics() {
        if (ageStatsRecyclerView == null) return; 
        List<AgeStatItem> ageStats = new ArrayList<>();
        
        // Данные для Мужчин (Троеборье - используем данные из Dvobore как пример)
        ageStats.add(new AgeStatItem("18-23 (М)", 377.5, 327.5, 282.5)); // Продвинутый, Средний, Начинающий
        ageStats.add(new AgeStatItem("24-39 (М)", 410.0, 355.0, 315.0));
        ageStats.add(new AgeStatItem("40+ (М)", 380.0, 330.0, 282.5));

        // Данные для Женщин (Троеборье - используем данные из Dvobore как пример)
        ageStats.add(new AgeStatItem("18-23 (Ж)", 190.0, 172.5, 150.0)); // Продвинутый, Средний, Начинающий
        ageStats.add(new AgeStatItem("24-39 (Ж)", 232.5, 210.0, 182.5));
        ageStats.add(new AgeStatItem("40+ (Ж)", 267.5, 240.0, 210.0));

        AgeStatsAdapter adapter = new AgeStatsAdapter(ageStats);
        ageStatsRecyclerView.setAdapter(adapter);
        ageStatsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void setupTrainingTips() {
        List<TrainingTip> tips = new ArrayList<>();
        tips.add(new TrainingTip(
            "Базовая программа",
            "1. Приседания 5x5\n2. Жим лежа 5x5\n3. Становая тяга 3x5",
            "Для начинающих атлетов"
        ));
        tips.add(new TrainingTip(
            "Продвинутая программа",
            "1. Приседания 8x3\n2. Жим лежа 6x4\n3. Становая тяга 5x3",
            "Для опытных атлетов"
        ));

        TrainingTipsAdapter adapter = new TrainingTipsAdapter(tips);
        trainingTipsRecyclerView.setAdapter(adapter);
        trainingTipsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void setupProgressTracking() {
        progressPrefs = requireContext().getSharedPreferences("progress_prefs", Context.MODE_PRIVATE);
        
        if (progressTrackingChart != null) {
            // Базовая настройка графика
            progressTrackingChart.setDrawGridBackground(false);
            progressTrackingChart.getDescription().setEnabled(false);
            progressTrackingChart.setTouchEnabled(true);
            progressTrackingChart.setDragEnabled(true);
            progressTrackingChart.setScaleEnabled(true);
            progressTrackingChart.setPinchZoom(true);
            
            // Настройка осей
            XAxis xAxis = progressTrackingChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            xAxis.setTextColor(getResources().getColor(R.color.on_background));
            
            YAxis leftAxis = progressTrackingChart.getAxisLeft();
            leftAxis.setDrawGridLines(true);
            leftAxis.setAxisMinimum(0f);
            leftAxis.setTextColor(getResources().getColor(R.color.on_background));
            
            progressTrackingChart.getAxisRight().setEnabled(false);
            progressTrackingChart.getLegend().setEnabled(false);
            
            // Загружаем сохраненные данные
            ArrayList<Entry> entries = new ArrayList<>();
            Map<String, ?> allProgress = progressPrefs.getAll();
            List<Long> dates = new ArrayList<>();
            
            // Собираем все даты
            for (String dateStr : allProgress.keySet()) {
                dates.add(Long.parseLong(dateStr));
            }
            
            // Сортируем даты
            Collections.sort(dates);
            
            // Создаем точки для графика
            for (int i = 0; i < dates.size(); i++) {
                float value = progressPrefs.getFloat(String.valueOf(dates.get(i)), 0f);
                entries.add(new Entry(i, value));
            }
            
            // Если нет сохраненных данных, добавляем пустой набор
            if (entries.isEmpty()) {
                entries.add(new Entry(0, 0));
            }
            
            LineDataSet dataSet = new LineDataSet(entries, "Progress");
            dataSet.setColor(getResources().getColor(R.color.primary));
            dataSet.setLineWidth(2f);
            dataSet.setCircleColor(getResources().getColor(R.color.primary));
            dataSet.setCircleRadius(4f);
            dataSet.setDrawCircleHole(false);
            dataSet.setValueTextSize(0f);
            dataSet.setDrawValues(false);
            
            LineData lineData = new LineData(dataSet);
            progressTrackingChart.setData(lineData);
            progressTrackingChart.setMinimumHeight(400);
            progressTrackingChart.setViewPortOffsets(60f, 0f, 30f, 60f);
            
            // Обновляем график
            progressTrackingChart.invalidate();
        }
    }

    private void updateProgressChart() {
        if (progressTrackingChart == null || progressTrackingChart.getData() == null) return;
        
        LineData data = progressTrackingChart.getData();
        if (data.getDataSetCount() > 0) {
            LineDataSet set = (LineDataSet) data.getDataSetByIndex(0);
            if (set == null) {
                set = createSet();
                data.addDataSet(set);
            }
            
            // Обновляем данные
            data.notifyDataChanged();
            progressTrackingChart.notifyDataSetChanged();
            progressTrackingChart.invalidate();
        }
    }

    private LineDataSet createSet() {
        LineDataSet set = new LineDataSet(null, "Progress");
        set.setColor(getResources().getColor(R.color.primary));
        set.setLineWidth(2f);
        set.setCircleColor(getResources().getColor(R.color.primary));
        set.setCircleRadius(4f);
        set.setDrawCircleHole(false);
        set.setValueTextSize(0f);
        set.setDrawValues(false);
        return set;
    }

    private void showAddProgressDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_progress, null);

        DatePicker datePicker = dialogView.findViewById(R.id.datePicker);
        EditText weightInput = dialogView.findViewById(R.id.weightInput);

        builder.setView(dialogView)
               .setTitle("Добавить результат")
               .setPositiveButton("Сохранить", (dialog, id) -> {
                   try {
                       Calendar calendar = Calendar.getInstance();
                       calendar.set(datePicker.getYear(), 
                                  datePicker.getMonth(), 
                                  datePicker.getDayOfMonth());
                       long date = calendar.getTimeInMillis();
                       
                       float weight = Float.parseFloat(weightInput.getText().toString());
                       
                       saveProgress(date, weight);
                       updateProgressChart();
                       updateTargetInfo();
                   } catch (NumberFormatException e) {
                       Toast.makeText(requireContext(), 
                                    "Пожалуйста, введите корректное значение веса", 
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
            
            // Добавляем новую точку на график
            if (progressTrackingChart != null && progressTrackingChart.getData() != null) {
                LineData data = progressTrackingChart.getData();
                ILineDataSet set = data.getDataSetByIndex(0);
                
                if (set == null) {
                    set = createSet();
                    data.addDataSet((LineDataSet)set);
                }
                
                // Добавляем новую точку, используя количество существующих точек как X координату
                data.addEntry(new Entry(set.getEntryCount(), weight), 0);
                
                // Обновляем график
                data.notifyDataChanged();
                progressTrackingChart.notifyDataSetChanged();
                progressTrackingChart.setVisibleXRangeMaximum(5); // Показываем максимум 5 точек
                progressTrackingChart.moveViewToX(data.getEntryCount()); // Прокручиваем к последней точке
                progressTrackingChart.invalidate();
            }
        }
    }

    private void updateTargetInfo() {
        if (targetInfoText == null) return;

        float lastResult = getLastResult();
        if (lastResult == 0) {
            targetInfoText.setText("Добавьте свой первый результат!");
            return;
        }

        String info = String.format("Ваш последний результат: %.1f кг\n", lastResult);
        targetInfoText.setText(info);
    }

    private float getLastResult() {
        if (progressPrefs == null) return 0;

        Map<String, ?> allProgress = progressPrefs.getAll();
        if (allProgress.isEmpty()) return 0;

        List<Long> dates = new ArrayList<>();
        for (String dateStr : allProgress.keySet()) {
            dates.add(Long.parseLong(dateStr));
        }
        Collections.sort(dates);
        
        return progressPrefs.getFloat(String.valueOf(dates.get(dates.size() - 1)), 0f);
    }
}