package com.example.diplom_final.ui.Stata.Stytis;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.Ignore;

@Entity(tableName = "exercise_result")
public class ExerciseResult {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "exercise_type")
    public String exerciseType; // Тип упражнения, например, "bench_press"

    @ColumnInfo(name = "result")
    public double result;       // Результат (1ПМ, рассчитанный или введенный)

    @ColumnInfo(name = "timestamp")
    public long timestamp;      // Временная метка сохранения результата

    @ColumnInfo(name = "weight")
    public double weight;       // Вес в подходе (для функции "Лучший подход")

    @ColumnInfo(name = "reps")
    public int reps;            // Количество повторений в подходе (для функции "Лучший подход")

    // Конструктор по умолчанию для Room
    public ExerciseResult() {
    }

    // Конструктор для Room, чтобы загружать все поля из БД
    // Room выберет его, если он соответствует всем полям
    public ExerciseResult(int id, String exerciseType, double result, long timestamp, double weight, int reps) {
        this.id = id;
        this.exerciseType = exerciseType;
        this.result = result;
        this.timestamp = timestamp;
        this.weight = weight;
        this.reps = reps;
    }

    // Конструктор для "Силомера" (сохраняет 1ПМ напрямую)
    @Ignore
    public ExerciseResult(String exerciseType, double result, long timestamp) {
        this.exerciseType = exerciseType;
        this.result = result; // 1ПМ из Силомера
        this.timestamp = timestamp;
        this.weight = 0; // Вес и повторения для записей Силомера по умолчанию 0
        this.reps = 0;
    }

    // Новый конструктор для функции "Лучший подход" (сохраняет подход и рассчитывает 1ПМ)
    @Ignore
    public ExerciseResult(String exerciseType, double weight, int reps, long timestamp) {
        this.exerciseType = exerciseType;
        this.weight = weight;
        this.reps = reps;
        this.timestamp = timestamp;
        // Расчет 1ПМ по формуле Эпли и сохранение в поле result
        if (reps == 0) { // Избегаем деления на ноль и бессмысленного расчета
            this.result = (weight > 0 && reps == 0) ? weight: 0; // Если вес есть, а повторов 0, то это и есть 1ПМ (или ошибка ввода, но лучше так)
        } else if (reps == 1) {
            this.result = weight; // Если 1 повторение, то это и есть 1ПМ
        } else {
            this.result = weight * (1 + (double)reps / 30.0);
        }
    }

    // --- Геттеры и сеттеры ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public double getResult() { // Изменил getValue на getResult для консистентности с полем
        return result;
    }

    public void setResult(double result) { // Изменил setValue на setResult
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
} 