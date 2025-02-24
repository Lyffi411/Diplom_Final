package com.example.diplom_final.ui.Filter;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.EditText;
import android.os.Handler;
import android.os.Looper;

public class MinMaxFilter implements InputFilter {
    private int intMin;
    private int intMax;
    private EditText editText;
    private Handler handler;
    private boolean isValidating = false;

    public MinMaxFilter(int minValue, int maxValue, EditText editText) {
        this.intMin = minValue;
        this.intMax = maxValue;
        this.editText = editText;
        this.handler = new Handler(Looper.getMainLooper());
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

        editText.setOnEditorActionListener((v, actionId, event) -> {
            handler.removeCallbacksAndMessages(null);
            validateValue();
            return false;
        });
    }

    private void validateValue() {
        if (isValidating) return;
        
        isValidating = true;
        String text = editText.getText().toString();
        
        if (!text.isEmpty()) {
            try {
                int value = Integer.parseInt(text);
                if (value < intMin) {
                    editText.setText(String.valueOf(intMin));
                    editText.setSelection(editText.length());
                } else if (value > intMax) {
                    editText.setText(String.valueOf(intMax));
                    editText.setSelection(editText.length());
                }
            } catch (NumberFormatException e) {
                editText.setText(String.valueOf(intMin));
                editText.setSelection(editText.length());
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
        try {
            String newVal = dest.toString().substring(0, dStart) + 
                          source.toString().substring(start, end) + 
                          dest.toString().substring(dEnd);
            
            if (newVal.equals("")) {
                return null;
            }

            if (newVal.length() <= String.valueOf(intMax).length()) {
                int input = Integer.parseInt(newVal);
                if (input <= intMax) {
                    return null;
                }
            }
        } catch (NumberFormatException e) {
            // Игнорируем ошибку
        }
        return "";
    }

    public int getMinValue() {
        return intMin;
    }
}