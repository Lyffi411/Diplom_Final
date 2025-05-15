package com.example.diplom_final.ui.Normativi.VseNormativi;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.diplom_final.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class GimLegaOdnoslFragment extends Fragment {

    private TableLayout tableMen, tableWomen; // tableWomen - это tableWeMen в XML
    private float scaleFactor = 1.0f;
    private float initialDistance = -1f;
    private float focusX, focusY;

    private TextInputEditText weightInput;
    private Button calculateButton;
    private TextView resultText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gimlegaodnosl, container, false);

        tableMen = view.findViewById(R.id.tableMen);
        tableWomen = view.findViewById(R.id.tableWeMen); // Исправлено на tableWeMen

        weightInput = view.findViewById(R.id.weightInput);
        calculateButton = view.findViewById(R.id.calculateButton);
        resultText = view.findViewById(R.id.resultText);

        tableMen.setOnTouchListener((v, event) -> handleTouch(event, tableMen));
        tableWomen.setOnTouchListener((v, event) -> handleTouch(event, tableWomen));

        setupCalculator();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupTables();
    }

    private void setupTables() {
        if (getContext() != null) {
            styleTable(tableMen);
            styleTable(tableWomen);
        }
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
        calculateButton.setOnClickListener(v -> {
            String weightStr = weightInput.getText().toString();
            if (TextUtils.isEmpty(weightStr)) {
                Toast.makeText(requireContext(), "Введите ваш вес", Toast.LENGTH_SHORT).show();
                resultText.setText("");
                return;
            }
            try {
                double weight = Double.parseDouble(weightStr);
                calculateNormative(weight);
            } catch (NumberFormatException e) {
                Toast.makeText(requireContext(), "Некорректный формат веса", Toast.LENGTH_SHORT).show();
                resultText.setText("");
            }
        });
    }

    private void calculateNormative(double weight) {
        if (weight <= 0) {
            Toast.makeText(requireContext(), "Введите корректный вес", Toast.LENGTH_SHORT).show();
            resultText.setText("");
            return;
        }

        // Весовые категории для ЖИМА ЛЕЖА В ОДНОСЛОЙНОЙ ЭКИПИРОВКЕ (из fragment_gimlegaodnosl.xml)
        // Мужчины: 60, 67.5, 75, 82.5, 90, 100, 110, 125, св.125
        double menCategory;
        if (weight <= 60) menCategory = 60;
        else if (weight <= 67.5) menCategory = 67.5;
        else if (weight <= 75) menCategory = 75;
        else if (weight <= 82.5) menCategory = 82.5;
        else if (weight <= 90) menCategory = 90;
        else if (weight <= 100) menCategory = 100;
        else if (weight <= 110) menCategory = 110;
        else if (weight <= 125) menCategory = 125;
        else menCategory = Double.POSITIVE_INFINITY; // "св.125"

        // Женщины: 44, 48, 52, 56, 60, 67.5, 75, 82.5, св.82.5
        double womenCategory;
        if (weight <= 44) womenCategory = 44;
        else if (weight <= 48) womenCategory = 48;
        else if (weight <= 52) womenCategory = 52;
        else if (weight <= 56) womenCategory = 56;
        else if (weight <= 60) womenCategory = 60;
        else if (weight <= 67.5) womenCategory = 67.5;
        else if (weight <= 75) womenCategory = 75;
        else if (weight <= 82.5) womenCategory = 82.5;
        else womenCategory = Double.POSITIVE_INFINITY; // "св.82.5"

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
        if (row == null) {
            builder.append("Нормативы для данной категории не найдены.\n");
            return;
        }
        // Индексы ячеек: 0-Весовая, 1-МСМК(ДК), 2-МС(ДК), 3-МСМК, 4-МС, 5-КМС, 6-I, 7-II, 8-III
        String[] titles = {"МСМК (ДК)", "МС (ДК)", "МСМК", "МС", "КМС", "I разряд", "II разряд", "III разряд"};
        for (int i = 1; i < row.getChildCount() && (i-1) < titles.length; i++) {
            builder.append(titles[i-1]).append(": ").append(getCellText(row, i)).append(" кг\n");
        }
    }

    private TableRow findRowByWeight(TableLayout table, double categoryWeight) {
        if (table == null) return null;
        for (int i = 1; i < table.getChildCount(); i++) { // Начинаем с 1, пропуская заголовок
            View child = table.getChildAt(i);
            if (child instanceof TableRow) {
                TableRow row = (TableRow) child;
                String weightText = getCellText(row, 0); // Весовая категория в первой ячейке

                if (categoryWeight == Double.POSITIVE_INFINITY) {
                    if (weightText.toLowerCase(Locale.ROOT).startsWith("св") || weightText.toLowerCase(Locale.ROOT).startsWith("sv")) {
                        return row;
                    }
                } else {
                    try {
                        // Убираем 'св.' или '>' для парсинга, если ищем НЕ POSITIVE_INFINITY
                        String cleanWeightText = weightText.replaceAll("(св\\.|sv\\.|св|>)", "").trim();
                        double rowWeight = parseDoubleSafe(cleanWeightText);
                        if (Math.abs(rowWeight - categoryWeight) < 0.01) { // Сравнение с небольшой погрешностью
                            return row;
                        }
                    } catch (NumberFormatException e) {
                        // Игнорируем строки, которые не можем распарсить как число (например, "св.XXX")
                        // если только не ищем категорию "свыше"
                    }
                }
            }
        }
        return null; // Если точная категория не найдена
    }

    private String getCellText(TableRow row, int cellIndex) {
        if (row != null && cellIndex < row.getChildCount()) {
            View cellView = row.getChildAt(cellIndex);
            if (cellView instanceof TextView) {
                return ((TextView) cellView).getText().toString();
            }
        }
        return "";
    }

    private double parseDoubleSafe(String text) {
        if (text == null || text.isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(text.replace(',', '.'));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    // Существующие методы handleTouch и setScale остаются без изменений
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
