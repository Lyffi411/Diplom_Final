package com.example.diplom_final.ui.Normativi.VseNormativi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.diplom_final.R;

public class GimLegaMnogopetFragment extends Fragment {

    private TableLayout tableMen, tableWomen;
    private float scaleFactor = 1.0f;
    private float initialDistance = -1f; // Начальное расстояние между пальцами
    private float focusX, focusY; // Точка фокуса для масштабирования

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gimlegamnogsl, container, false);

        // Инициализация таблиц
        tableMen = view.findViewById(R.id.tableMen);
        tableWomen = view.findViewById(R.id.tableWeMen);

        // Обработка касаний для таблиц
        tableMen.setOnTouchListener((v, event) -> handleTouch(event, tableMen));
        tableWomen.setOnTouchListener((v, event) -> handleTouch(event, tableWomen));

        return view;
    }

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
                    break;

                case MotionEvent.ACTION_MOVE:
                    if (initialDistance > 0) {
                        // Вычисляем коэффициент масштабирования
                        float scale = currentDistance / initialDistance;
                        setScale(table, scaleFactor * scale, focusX, focusY);
                    }
                    break;

                case MotionEvent.ACTION_POINTER_UP:
                    // Конец жеста: сохраняем текущий масштаб
                    scaleFactor = table.getScaleX();
                    initialDistance = -1f;
                    break;
            }
        }

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
}
