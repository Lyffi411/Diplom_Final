package com.example.diplom_final.ui.Filter;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.EditText;
import android.os.Handler;
import android.os.Looper;
import java.util.Locale;

public class MinMaxFilter implements InputFilter {
    private double min;
    private double max;
    private EditText editText;
    private Handler handler;
    private boolean isValidating = false;
    private boolean isStrict; // флаг для определения режима работы

    public MinMaxFilter(double minValue, double maxValue, EditText editText, boolean strict) {
        this.min = minValue;
        this.max = maxValue;
        this.editText = editText;
        this.handler = new Handler(Looper.getMainLooper());
        this.isStrict = strict;
        setupValidation();
    }

    private void setupValidation() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (!isValidating && s.length() > 0) {
                    handler.removeCallbacksAndMessages(null);
                    handler.postDelayed(() -> validateValue(), 1000);
                }
            }
        });

        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                handler.removeCallbacksAndMessages(null);
                validateValue();
            }
        });
    }

    private void validateValue() {
        if (isValidating) return;
        
        isValidating = true;
        String text = editText.getText().toString();
        
        if (!text.isEmpty()) {
            try {
                double value = Double.parseDouble(text);
                if (isStrict) {
                    // Строгий режим - устанавливаем минимальные значения
                    if (value < min) {
                        editText.setText(String.format(Locale.US, "%.1f", min));
                        editText.setSelection(editText.length());
                    } else if (value > max) {
                        editText.setText(String.format(Locale.US, "%.1f", max));
                        editText.setSelection(editText.length());
                    }
                } else {
                    // Мягкий режим - только проверяем максимальные значения
                    if (value > max) {
                        editText.setText(String.format(Locale.US, "%.1f", max));
                        editText.setSelection(editText.length());
                    }
                }
            } catch (NumberFormatException e) {
                if (isStrict) {
                    // В строгом режиме устанавливаем минимальное значение
                    editText.setText(String.format(Locale.US, "%.1f", min));
                    editText.setSelection(editText.length());
                }
            }
        }
        
        isValidating = false;
    }

    public void forceValidate() {
        handler.removeCallbacksAndMessages(null);
        validateValue();
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dStart, int dEnd) {
        String newVal = dest.toString().substring(0, dStart) + 
                       source.toString().substring(start, end) + 
                       dest.toString().substring(dEnd);
        
        if (newVal.isEmpty() || newVal.equals(".")) {
            return null;
        }

        try {
            double input = Double.parseDouble(newVal);
            if (input <= max) {
                return null;
            }
        } catch (NumberFormatException e) {
            if (newVal.matches("\\d*\\.?\\d*")) {
                return null;
            }
        }
        return "";
    }

    public double getMinValue() {
        return min;
    }
}