package com.example.diplom_final.ui.Silomer.Rasshet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ProgressCircleView extends View {
    private Paint backgroundPaint;
    private Paint foregroundPaint;
    private Paint textPaint;
    private RectF rectF;
    private float progress = 0;
    private String progressText = "";

    public ProgressCircleView(Context context) {
        super(context);
        init();
    }

    public ProgressCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.LTGRAY);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(20);
        backgroundPaint.setAntiAlias(true);

        foregroundPaint = new Paint();
        foregroundPaint.setColor(Color.rgb(33, 150, 243));
        foregroundPaint.setStyle(Paint.Style.STROKE);
        foregroundPaint.setStrokeWidth(20);
        foregroundPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(Color.rgb(255, 165, 0)); // Оранжевый цвет (#FFA500)
        textPaint.setTextSize(50);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
        textPaint.setFakeBoldText(true);

        rectF = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        
        // Оставляем отступ для толщины линии
        float padding = backgroundPaint.getStrokeWidth();
        rectF.set(padding, padding, w - padding, h - padding);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Рисуем фоновый круг
        canvas.drawArc(rectF, 0, 360, false, backgroundPaint);
        
        // Рисуем прогресс
        float sweepAngle = (progress * 360) / 100;
        canvas.drawArc(rectF, -90, sweepAngle, false, foregroundPaint);
        
        // Рисуем текст в центре
        if (!progressText.isEmpty()) {
            float xPos = getWidth() / 2f;
            float yPos = (getHeight() / 2f) - ((textPaint.descent() + textPaint.ascent()) / 2f);
            canvas.drawText(progressText, xPos, yPos, textPaint);
        }
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public void setProgressText(String text) {
        this.progressText = text;
        invalidate();
    }

    public void setProgressColor(int color) {
        foregroundPaint.setColor(color);
        invalidate();
    }

    public void setBackgroundColor(int color) {
        backgroundPaint.setColor(color);
        invalidate();
    }

    public void setTextColor(int color) {
        textPaint.setColor(color);
        invalidate();
    }

    public void setStrokeWidth(float width) {
        backgroundPaint.setStrokeWidth(width);
        foregroundPaint.setStrokeWidth(width);
        invalidate();
    }

    public void setTextSize(float size) {
        textPaint.setTextSize(size);
        invalidate();
    }
}