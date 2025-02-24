package com.example.diplom_final.ui.Ypragnenia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.diplom_final.R;
import com.example.diplom_final.databinding.FragmentShoulderExerciseDetailBinding;

public class ShoulderExerciseDetailFragment extends Fragment {

    private FragmentShoulderExerciseDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentShoulderExerciseDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        String title = getArguments() != null ? getArguments().getString("title") : "";
        if (title != null) {
            setExerciseDetails(title);
        }
    }

    private void setExerciseDetails(String title) {
        switch (title) {
            case "Армейский жим":
                setupMilitaryPress();
                break;
            case "Армейский жим из-за головы":
                setupBehindNeckMilitaryPress();
                break;
            case "Боковой поворот плеча в сторону стоя с резинкой":
                setupStandingBandExternalRotation();
                break;
            case "Жим Арнольда":
                setupArnoldPress();
                break;
            case "Жим вверх перед собой в тренажере":
                setupFrontShoulderPressMachine();
                break;
            case "Жим гантелей сидя":
                setupSeatedDumbbellPress();
                break;
            case "Жим одной рукой Т-грифа стоя":
                setupStandingOneArmTBarPress();
                break;
            case "Жим штанги из-за головы сидя":
                setupSeatedBehindNeckPress();
                break;
            case "Жим штанги перед собой сидя":
                setupSeatedFrontBarbellPress();
                break;
            case "Махи в стороны в тренажере сидя":
                setupSeatedLateralRaiseMachine();
                break;
            case "Махи гантелей в стороны параллельным хватом в наклоне":
                setupBentOverLateralRaise();
                break;
            case "Махи гантелями в стороны лёжа грудью на наклонной скамье":
                setupInclineBenchLateralRaise();
                break;
            case "Махи гантелями в стороны сидя":
                setupSeatedLateralRaise();
                break;
            case "Махи гантелями в стороны стоя":
                setupStandingLateralRaise();
                break;
            case "Отведение руки в сторону стоя в блоке":
                setupCableLateralRaise();
                break;
            case "Подъём и жим гантелей сидя":
                setupSeatedCleanAndPress();
                break;
            case "Тяга гантелей к подбородку":
                setupDumbbellUprightRow();
                break;
            case "Тяга штанги к груди в наклоне":
                setupBentOverBarbellRow();
                break;
            case "Тяга штанги к подбородку":
                setupBarbellUprightRow();
                break;
        }
    }

    private void setupSeatedDumbbellPress() {
        binding.textViewExerciseTitle.setText("Жим гантелей сидя");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_dumbbell_press);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для развития всех трех пучков дельтовидных мышц");

        binding.textViewTargetMuscles.setText(
            "• Передний пучок дельтовидной мышцы\n" +
            "• Средний пучок дельтовидной мышцы\n" +
            "• Задний пучок дельтовидной мышцы\n" +
            "• Трапециевидные мышцы\n" +
            "• Трицепсы");

        binding.textViewTechnique.setText(
            "1. Сядьте на скамью со спинкой\n\n" +
            "2. Возьмите гантели на уровне плеч\n\n" +
            "3. Выжмите гантели вверх\n\n" +
            "4. Полностью выпрямите руки\n\n" +
            "5. Медленно опустите гантели\n\n" +
            "6. Повторите движение");

        binding.textViewCommonMistakes.setText(
            "• Прогиб в пояснице\n" +
            "• Отбив гантелей от плеч\n" +
            "• Неполная амплитуда\n" +
            "• Раскачивания корпуса");

        binding.textViewRecommendations.setText(
            "• Начинайте с легких гантелей\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Держите спину прямо\n" +
            "• Дышите равномерно");

        binding.textViewContraindications.setText(
            "• Травмы плечевых суставов\n" +
            "• Проблемы с позвоночником\n" +
            "• Воспаление суставов\n" +
            "• Высокое давление");

        binding.textViewAlternatives.setText(
            "• Жим штанги сидя\n" +
            "• Жим в тренажере\n" +
            "• Жим гантелей стоя\n" +
            "• Армейский жим");
    }

    private void setupSeatedBarbellPress() {
        binding.textViewExerciseTitle.setText("Жим штанги сидя");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_dumbbell_press);
        
        binding.textViewExerciseDescription.setText(
            "Мощное базовое упражнение для развития плеч");

        binding.textViewTargetMuscles.setText(
            "• Дельтовидные мышцы\n" +
            "• Трапециевидные мышцы\n" +
            "• Трицепсы\n" +
            "• Передняя зубчатая мышца\n" +
            "• Мышцы-стабилизаторы");

        binding.textViewTechnique.setText(
            "1. Сядьте на скамью\n\n" +
            "2. Возьмите штангу шире плеч\n\n" +
            "3. Опустите штангу к верху груди\n\n" +
            "4. Выжмите штангу вверх\n\n" +
            "5. Зафиксируйте наверху\n\n" +
            "6. Медленно опустите");

        binding.textViewCommonMistakes.setText(
            "• Сильный прогиб спины\n" +
            "• Неправильный хват\n" +
            "• Движение по дуге\n" +
            "• Опускание локтей");

        binding.textViewRecommendations.setText(
            "• Начинайте с пустого грифа\n" +
            "• 3-4 подхода по 8-12 повторений\n" +
            "• Используйте страховку\n" +
            "• Следите за техникой");

        binding.textViewContraindications.setText(
            "• Травмы плеч\n" +
            "• Проблемы с позвоночником\n" +
            "• Нестабильность суставов\n" +
            "• Высокое давление");

        binding.textViewAlternatives.setText(
            "• Жим гантелей сидя\n" +
            "• Жим в тренажере Смита\n" +
            "• Армейский жим\n" +
            "• Жим гантелей стоя");
    }

    private void setupStandingLateralRaise() {
        binding.textViewExerciseTitle.setText("Разведение гантелей в стороны стоя");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_dumbbell_press);
        
        binding.textViewExerciseDescription.setText(
            "Изолированное упражнение для средней дельты");

        binding.textViewTargetMuscles.setText(
            "• Средний пучок дельтовидной мышцы\n" +
            "• Передний пучок дельтовидной мышцы\n" +
            "• Трапециевидные мышцы\n" +
            "• Мышцы-стабилизаторы");

        binding.textViewTechnique.setText(
            "1. Встаньте прямо\n\n" +
            "2. Возьмите гантели\n\n" +
            "3. Поднимите руки в стороны\n\n" +
            "4. Локти слегка согнуты\n\n" +
            "5. Опустите медленно\n\n" +
            "6. Контролируйте движение");

        binding.textViewCommonMistakes.setText(
            "• Раскачивания корпуса\n" +
            "• Слишком тяжелый вес\n" +
            "• Подъем выше плеч\n" +
            "• Полностью прямые руки");

        binding.textViewRecommendations.setText(
            "• Используйте легкие веса\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Выполняйте плавно\n" +
            "• Следите за техникой");

        binding.textViewContraindications.setText(
            "• Травмы плечевых суставов\n" +
            "• Воспаление суставов\n" +
            "• Проблемы с ротаторной манжетой\n" +
            "• Нестабильность плеч");

        binding.textViewAlternatives.setText(
            "• Разведение в тренажере\n" +
            "• Разведение с тросами\n" +
            "• Разведение одной рукой\n" +
            "• Подъемы перед собой");
    }

    private void setupMilitaryPress() {
        binding.textViewExerciseTitle.setText("Армейский жим");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_military_press);
        
        binding.textViewExerciseDescription.setText(
            "Классическое базовое упражнение для развития силы и массы плеч");

        binding.textViewTargetMuscles.setText(
            "• Передние дельты\n" +
            "• Средние дельты\n" +
            "• Задние дельты\n" +
            "• Трапециевидные мышцы\n" +
            "• Трицепсы");

        binding.textViewTechnique.setText(
            "1. Возьмите штангу со стоек на грудь\n\n" +
            "2. Ноги на ширине плеч\n\n" +
            "3. Локти под грифом\n\n" +
            "4. Выжмите штангу вверх\n\n" +
            "5. Зафиксируйте в верхней точке\n\n" +
            "6. Медленно опустите на грудь");

        binding.textViewCommonMistakes.setText(
            "• Прогиб в пояснице\n" +
            "• Отклонение корпуса назад\n" +
            "• Неполное выпрямление рук\n" +
            "• Опускание локтей");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 8-12 повторений\n" +
            "• Держите корпус прямо\n" +
            "• Следите за дыханием");

        binding.textViewContraindications.setText(
            "• Травмы плечевых суставов\n" +
            "• Проблемы с поясницей\n" +
            "• Нестабильность позвоночника\n" +
            "• Высокое давление");

        binding.textViewAlternatives.setText(
            "• Жим гантелей стоя\n" +
            "• Жим штанги сидя\n" +
            "• Жим в тренажере\n" +
            "• Жим Арнольда");
    }

    private void setupBehindNeckMilitaryPress() {
        binding.textViewExerciseTitle.setText("Армейский жим из-за головы");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_behind_neck_press);
        
        binding.textViewExerciseDescription.setText(
            "Продвинутый вариант армейского жима с акцентом на задние дельты");

        binding.textViewTargetMuscles.setText(
            "• Задние дельты\n" +
            "• Средние дельты\n" +
            "• Трапециевидные мышцы\n" +
            "• Трицепсы\n" +
            "• Мышцы-стабилизаторы");

        binding.textViewTechnique.setText(
            "1. Возьмите штангу со стоек за голову\n\n" +
            "2. Ноги на ширине плеч\n\n" +
            "3. Широкий хват штанги\n\n" +
            "4. Выжмите штангу вверх\n\n" +
            "5. Зафиксируйте наверху\n\n" +
            "6. Опустите за голову");

        binding.textViewCommonMistakes.setText(
            "• Слишком близкий хват\n" +
            "• Касание штангой шеи\n" +
            "• Наклон головы вперед\n" +
            "• Чрезмерный прогиб спины");

        binding.textViewRecommendations.setText(
            "• Используйте легкий вес\n" +
            "• 3 подхода по 10-12 повторений\n" +
            "• Разомните плечи перед выполнением\n" +
            "• Следите за положением головы");

        binding.textViewContraindications.setText(
            "• Травмы плеч\n" +
            "• Проблемы с шейным отделом\n" +
            "• Ограниченная подвижность плеч\n" +
            "• Синдром импинджмента");

        binding.textViewAlternatives.setText(
            "• Классический армейский жим\n" +
            "• Жим гантелей сидя\n" +
            "• Жим в тренажере\n" +
            "• Жим штанги перед собой");
    }

    private void setupStandingBandExternalRotation() {
        binding.textViewExerciseTitle.setText("Боковой поворот плеча в сторону стоя с резинкой");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_band_rotation);
        
        binding.textViewExerciseDescription.setText(
            "Реабилитационное упражнение для укрепления ротаторной манжеты плеча");

        binding.textViewTargetMuscles.setText(
            "• Внешние ротаторы плеча\n" +
            "• Задние дельты\n" +
            "• Подостная мышца\n" +
            "• Малая круглая мышца\n" +
            "• Мышцы-стабилизаторы");

        binding.textViewTechnique.setText(
            "1. Закрепите резинку на уровне локтя\n\n" +
            "2. Прижмите локоть к боку\n\n" +
            "3. Согните руку в локте 90°\n\n" +
            "4. Отведите предплечье наружу\n\n" +
            "5. Задержитесь на 1-2 секунды\n\n" +
            "6. Медленно вернитесь");

        binding.textViewCommonMistakes.setText(
            "• Отведение локтя от тела\n" +
            "• Слишком сильное сопротивление\n" +
            "• Рывковые движения\n" +
            "• Вращение корпусом");

        binding.textViewRecommendations.setText(
            "• Выбирайте легкое сопротивление\n" +
            "• 3 подхода по 15-20 повторений\n" +
            "• Выполняйте плавно\n" +
            "• Контролируйте движение");

        binding.textViewContraindications.setText(
            "• Острые боли в плече\n" +
            "• Воспаление суставов\n" +
            "• Недавние травмы\n" +
            "• Нестабильность сустава");

        binding.textViewAlternatives.setText(
            "• Внешняя ротация с гантелью\n" +
            "• Ротация в блоке\n" +
            "• Ротация лежа на боку\n" +
            "• Упражнения с эспандером");
    }

    private void setupArnoldPress() {
        binding.textViewExerciseTitle.setText("Жим Арнольда");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_arnold_press);
        
        binding.textViewExerciseDescription.setText(
            "Комплексное упражнение для всестороннего развития дельтовидных мышц");

        binding.textViewTargetMuscles.setText(
            "• Передние дельты\n" +
            "• Средние дельты\n" +
            "• Задние дельты\n" +
            "• Трицепсы\n" +
            "• Передняя зубчатая мышца");

        binding.textViewTechnique.setText(
            "1. Сядьте на скамью со спинкой\n\n" +
            "2. Возьмите гантели перед грудью\n\n" +
            "3. Разверните локти в стороны\n\n" +
            "4. Выжмите гантели с поворотом\n\n" +
            "5. Полностью выпрямите руки\n\n" +
            "6. Вернитесь с поворотом");

        binding.textViewCommonMistakes.setText(
            "• Неполный поворот рук\n" +
            "• Слишком тяжелый вес\n" +
            "• Прогиб в спине\n" +
            "• Асинхронные движения");

        binding.textViewRecommendations.setText(
            "• Начинайте с легких гантелей\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Выполняйте плавно\n" +
            "• Следите за техникой");

        binding.textViewContraindications.setText(
            "• Травмы плечевых суставов\n" +
            "• Проблемы с ротацией плеча\n" +
            "• Артрит плечевого сустава\n" +
            "• Тендинит");

        binding.textViewAlternatives.setText(
            "• Классический жим гантелей\n" +
            "• Жим штанги сидя\n" +
            "• Жим в тренажере\n" +
            "• Попеременный жим гантелей");
    }

    private void setupFrontShoulderPressMachine() {
        binding.textViewExerciseTitle.setText("Жим вверх перед собой в тренажере");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_machine_press);
        
        binding.textViewExerciseDescription.setText(
            "Безопасное упражнение для развития передних дельт с фиксированной траекторией");

        binding.textViewTargetMuscles.setText(
            "• Передние дельты\n" +
            "• Средние дельты\n" +
            "• Трицепсы\n" +
            "• Верхняя часть груди\n" +
            "• Передняя зубчатая мышца");

        binding.textViewTechnique.setText(
            "1. Отрегулируйте сиденье\n\n" +
            "2. Возьмитесь за рукояти\n\n" +
            "3. Прижмитесь к спинке\n\n" +
            "4. Выжмите вес вверх\n\n" +
            "5. Зафиксируйте наверху\n\n" +
            "6. Медленно опустите");

        binding.textViewCommonMistakes.setText(
            "• Отрыв спины от спинки\n" +
            "• Неполная амплитуда\n" +
            "• Рывковые движения\n" +
            "• Задержка дыхания");

        binding.textViewRecommendations.setText(
            "• Подберите правильный вес\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Контролируйте движение\n" +
            "• Соблюдайте технику");

        binding.textViewContraindications.setText(
            "• Травмы плеч\n" +
            "• Проблемы с позвоночником\n" +
            "• Острые боли в суставах\n" +
            "• Воспаление сухожилий");

        binding.textViewAlternatives.setText(
            "• Жим гантелей сидя\n" +
            "• Жим штанги сидя\n" +
            "• Армейский жим\n" +
            "• Жим Смита");
    }

    private void setupStandingOneArmTBarPress() {
        binding.textViewExerciseTitle.setText("Жим одной рукой Т-грифа стоя");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_tbar_press);
        
        binding.textViewExerciseDescription.setText(
            "Одностороннее упражнение для развития силы и баланса плеч");

        binding.textViewTargetMuscles.setText(
            "• Передние дельты\n" +
            "• Средние дельты\n" +
            "• Трицепс\n" +
            "• Мышцы кора\n" +
            "• Стабилизаторы плеча");

        binding.textViewTechnique.setText(
            "1. Встаньте у Т-грифа\n\n" +
            "2. Возьмитесь за рукоять\n\n" +
            "3. Держите корпус прямо\n\n" +
            "4. Выжмите вес вверх\n\n" +
            "5. Зафиксируйте положение\n\n" +
            "6. Медленно опустите");

        binding.textViewCommonMistakes.setText(
            "• Наклон в сторону\n" +
            "• Раскачивание корпуса\n" +
            "• Неполная амплитуда\n" +
            "• Потеря баланса");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3 подхода по 10-12 повторений\n" +
            "• Чередуйте руки\n" +
            "• Держите корпус стабильным");

        binding.textViewContraindications.setText(
            "• Травмы плеча\n" +
            "• Проблемы с балансом\n" +
            "• Боли в пояснице\n" +
            "• Нестабильность суставов");

        binding.textViewAlternatives.setText(
            "• Жим гантели одной рукой\n" +
            "• Жим в тренажере\n" +
            "• Жим штанги\n" +
            "• Жим гантелей двумя руками");
    }

    private void setupSeatedBehindNeckPress() {
        binding.textViewExerciseTitle.setText("Жим штанги из-за головы сидя");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_behind_neck_barbell);
        
        binding.textViewExerciseDescription.setText(
            "Продвинутое упражнение для развития всех головок дельтовидной мышцы");

        binding.textViewTargetMuscles.setText(
            "• Задние дельты\n" +
            "• Средние дельты\n" +
            "• Передние дельты\n" +
            "• Трапециевидные мышцы\n" +
            "• Трицепсы");

        binding.textViewTechnique.setText(
            "1. Сядьте на скамью со спинкой\n\n" +
            "2. Возьмите штангу за головой\n\n" +
            "3. Широкий хват штанги\n\n" +
            "4. Выжмите вверх\n\n" +
            "5. Зафиксируйте наверху\n\n" +
            "6. Опустите за голову");

        binding.textViewCommonMistakes.setText(
            "• Удар штангой по шее\n" +
            "• Слишком узкий хват\n" +
            "• Наклон головы вперед\n" +
            "• Отрыв от спинки");

        binding.textViewRecommendations.setText(
            "• Используйте умеренный вес\n" +
            "• 3-4 подхода по 8-12 повторений\n" +
            "• Хорошо разомнитесь\n" +
            "• Следите за положением головы");

        binding.textViewContraindications.setText(
            "• Травмы плеч\n" +
            "• Проблемы с шеей\n" +
            "• Ограниченная подвижность\n" +
            "• Остеохондроз");

        binding.textViewAlternatives.setText(
            "• Жим штанги перед собой\n" +
            "• Жим гантелей сидя\n" +
            "• Жим в тренажере\n" +
            "• Армейский жим");
    }

    private void setupSeatedFrontBarbellPress() {
        binding.textViewExerciseTitle.setText("Жим штанги перед собой сидя");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_front_barbell);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для развития силы и массы плеч");

        binding.textViewTargetMuscles.setText(
            "• Передние дельты\n" +
            "• Средние дельты\n" +
            "• Трицепсы\n" +
            "• Верхняя часть груди\n" +
            "• Передняя зубчатая мышца");

        binding.textViewTechnique.setText(
            "1. Сядьте на скамью со спинкой\n\n" +
            "2. Возьмите штангу на грудь\n\n" +
            "3. Хват чуть шире плеч\n\n" +
            "4. Выжмите штангу вверх\n\n" +
            "5. Зафиксируйте наверху\n\n" +
            "6. Плавно опустите");

        binding.textViewCommonMistakes.setText(
            "• Прогиб в пояснице\n" +
            "• Отбив от груди\n" +
            "• Неполное выпрямление рук\n" +
            "• Раскачивание корпуса");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 8-12 повторений\n" +
            "• Используйте страховку\n" +
            "• Держите спину прямо");

        binding.textViewContraindications.setText(
            "• Травмы плеч\n" +
            "• Проблемы с позвоночником\n" +
            "• Артрит плечевых суставов\n" +
            "• Высокое давление");

        binding.textViewAlternatives.setText(
            "• Жим гантелей сидя\n" +
            "• Жим в тренажере\n" +
            "• Армейский жим\n" +
            "• Жим Смита");
    }

    private void setupSeatedLateralRaiseMachine() {
        binding.textViewExerciseTitle.setText("Махи в стороны в тренажере сидя");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_machine_lateral);
        
        binding.textViewExerciseDescription.setText(
            "Изолированное упражнение для средних дельт с фиксированной траекторией");

        binding.textViewTargetMuscles.setText(
            "• Средние дельты\n" +
            "• Передние дельты\n" +
            "• Трапециевидные мышцы\n" +
            "• Надостная мышца\n" +
            "• Мышцы-стабилизаторы");

        binding.textViewTechnique.setText(
            "1. Отрегулируйте сиденье\n\n" +
            "2. Сядьте в тренажер\n\n" +
            "3. Возьмитесь за рукояти\n\n" +
            "4. Поднимите руки в стороны\n\n" +
            "5. Задержитесь наверху\n\n" +
            "6. Медленно опустите");

        binding.textViewCommonMistakes.setText(
            "• Использование инерции\n" +
            "• Слишком большой вес\n" +
            "• Неполная амплитуда\n" +
            "• Подъем плеч");

        binding.textViewRecommendations.setText(
            "• Выбирайте умеренный вес\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Выполняйте плавно\n" +
            "• Концентрируйтесь на средних дельтах");

        binding.textViewContraindications.setText(
            "• Травмы плечевых суставов\n" +
            "• Воспаление сухожилий\n" +
            "• Синдром импинджмента\n" +
            "• Артрит плеча");

        binding.textViewAlternatives.setText(
            "• Махи гантелями в стороны\n" +
            "• Махи с тросами\n" +
            "• Разведение рук с гантелями\n" +
            "• Подъемы рук через стороны");
    }

    private void setupBentOverLateralRaise() {
        binding.textViewExerciseTitle.setText("Махи гантелей в стороны параллельным хватом в наклоне");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_bent_lateral);
        
        binding.textViewExerciseDescription.setText(
            "Изолирующее упражнение для задних дельт и верхней части спины");

        binding.textViewTargetMuscles.setText(
            "• Задние дельты\n" +
            "• Средние дельты\n" +
            "• Трапециевидные мышцы\n" +
            "• Ромбовидные мышцы\n" +
            "• Мышцы-стабилизаторы");

        binding.textViewTechnique.setText(
            "1. Наклонитесь вперед\n\n" +
            "2. Возьмите гантели\n\n" +
            "3. Руки слегка согнуты\n\n" +
            "4. Поднимите руки в стороны\n\n" +
            "5. Задержитесь наверху\n\n" +
            "6. Медленно опустите");

        binding.textViewCommonMistakes.setText(
            "• Округление спины\n" +
            "• Слишком тяжелый вес\n" +
            "• Махи с инерцией\n" +
            "• Недостаточный наклон");

        binding.textViewRecommendations.setText(
            "• Используйте легкие веса\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Держите спину прямой\n" +
            "• Фокусируйтесь на задних дельтах");

        binding.textViewContraindications.setText(
            "• Травмы спины\n" +
            "• Проблемы с шеей\n" +
            "• Грыжи позвоночника\n" +
            "• Головокружения");

        binding.textViewAlternatives.setText(
            "• Разведение в тренажере\n" +
            "• Тяга к подбородку\n" +
            "• Махи на наклонной скамье\n" +
            "• Обратные разведения в кроссовере");
    }

    private void setupInclineBenchLateralRaise() {
        binding.textViewExerciseTitle.setText("Махи гантелями в стороны лёжа грудью на наклонной скамье");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_incline_lateral);
        
        binding.textViewExerciseDescription.setText(
            "Изолированное упражнение для задних дельт с опорой на скамью");

        binding.textViewTargetMuscles.setText(
            "• Задние дельты\n" +
            "• Средние дельты\n" +
            "• Трапециевидные мышцы\n" +
            "• Ромбовидные мышцы\n" +
            "• Мышцы-стабилизаторы");

        binding.textViewTechnique.setText(
            "1. Лягте на наклонную скамью\n\n" +
            "2. Возьмите гантели\n\n" +
            "3. Руки опущены вниз\n\n" +
            "4. Поднимите руки в стороны\n\n" +
            "5. Сведите лопатки\n\n" +
            "6. Медленно опустите");

        binding.textViewCommonMistakes.setText(
            "• Рывковые движения\n" +
            "• Слишком большой вес\n" +
            "• Неполная амплитуда\n" +
            "• Работа спиной");

        binding.textViewRecommendations.setText(
            "• Используйте легкие гантели\n" +
            "• 3 подхода по 12-15 повторений\n" +
            "• Выполняйте плавно\n" +
            "• Концентрируйтесь на задних дельтах");

        binding.textViewContraindications.setText(
            "• Травмы плеч\n" +
            "• Проблемы с шеей\n" +
            "• Боли в грудном отделе\n" +
            "• Головокружения");

        binding.textViewAlternatives.setText(
            "• Махи в наклоне стоя\n" +
            "• Разведение в тренажере\n" +
            "• Обратные разведения\n" +
            "• Тяга к подбородку");
    }

    private void setupSeatedLateralRaise() {
        binding.textViewExerciseTitle.setText("Махи гантелями в стороны сидя");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_seated_lateral);
        
        binding.textViewExerciseDescription.setText(
            "Изолированное упражнение для средних дельт с исключением читинга");

        binding.textViewTargetMuscles.setText(
            "• Средние дельты\n" +
            "• Передние дельты\n" +
            "• Трапециевидные мышцы\n" +
            "• Надостная мышца\n" +
            "• Мышцы-стабилизаторы");

        binding.textViewTechnique.setText(
            "1. Сядьте на скамью\n\n" +
            "2. Возьмите гантели\n\n" +
            "3. Слегка согните руки\n\n" +
            "4. Поднимите руки до уровня плеч\n\n" +
            "5. Зафиксируйте на секунду\n\n" +
            "6. Медленно опустите");

        binding.textViewCommonMistakes.setText(
            "• Использование инерции\n" +
            "• Подъём выше уровня плеч\n" +
            "• Полностью прямые руки\n" +
            "• Наклоны корпуса");

        binding.textViewRecommendations.setText(
            "• Используйте легкие веса\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Держите спину прямо\n" +
            "• Выполняйте плавно");

        binding.textViewContraindications.setText(
            "• Травмы плечевых суставов\n" +
            "• Воспаление сухожилий\n" +
            "• Синдром импинджмента\n" +
            "• Артрит плеча");

        binding.textViewAlternatives.setText(
            "• Махи в тренажере\n" +
            "• Махи с тросами\n" +
            "• Махи стоя\n" +
            "• Подъемы через стороны");
    }

    private void setupCableLateralRaise() {
        binding.textViewExerciseTitle.setText("Отведение руки в сторону стоя в блоке");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_cable_lateral);
        
        binding.textViewExerciseDescription.setText(
            "Изолированное упражнение для средних дельт с постоянным сопротивлением");

        binding.textViewTargetMuscles.setText(
            "• Средние дельты\n" +
            "• Передние дельты\n" +
            "• Трапециевидные мышцы\n" +
            "• Мышцы-стабилизаторы\n" +
            "• Ротаторная манжета");

        binding.textViewTechnique.setText(
            "1. Встаньте боком к блоку\n\n" +
            "2. Возьмите нижний трос\n\n" +
            "3. Отойдите на шаг\n\n" +
            "4. Поднимите руку в сторону\n\n" +
            "5. Зафиксируйте наверху\n\n" +
            "6. Медленно опустите");

        binding.textViewCommonMistakes.setText(
            "• Наклоны корпуса\n" +
            "• Рывковые движения\n" +
            "• Слишком большой вес\n" +
            "• Сгибание руки");

        binding.textViewRecommendations.setText(
            "• Начинайте с малого веса\n" +
            "• 3 подхода по 12-15 повторений\n" +
            "• Контролируйте движение\n" +
            "• Чередуйте руки");

        binding.textViewContraindications.setText(
            "• Травмы плеча\n" +
            "• Воспаление суставов\n" +
            "• Проблемы с балансом\n" +
            "• Нестабильность плеча");

        binding.textViewAlternatives.setText(
            "• Махи гантелями\n" +
            "• Махи в тренажере\n" +
            "• Подъемы через стороны\n" +
            "• Разведения лежа");
    }

    private void setupSeatedCleanAndPress() {
        binding.textViewExerciseTitle.setText("Подъём и жим гантелей сидя");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_clean_press);
        
        binding.textViewExerciseDescription.setText(
            "Комплексное упражнение для развития силы и мощности плеч");

        binding.textViewTargetMuscles.setText(
            "• Дельтовидные мышцы\n" +
            "• Трапециевидные мышцы\n" +
            "• Трицепсы\n" +
            "• Бицепсы\n" +
            "• Мышцы кора");

        binding.textViewTechnique.setText(
            "1. Сядьте на скамью\n\n" +
            "2. Гантели у бедер\n\n" +
            "3. Поднимите к плечам\n\n" +
            "4. Выжмите вверх\n\n" +
            "5. Зафиксируйте\n\n" +
            "6. Опустите к плечам и вниз");

        binding.textViewCommonMistakes.setText(
            "• Использование спины\n" +
            "• Неконтролируемые движения\n" +
            "• Слишком тяжелый вес\n" +
            "• Потеря баланса");

        binding.textViewRecommendations.setText(
            "• Начинайте с легких гантелей\n" +
            "• 3-4 подхода по 8-12 повторений\n" +
            "• Соблюдайте технику\n" +
            "• Держите корпус прямо");

        binding.textViewContraindications.setText(
            "• Травмы плеч\n" +
            "• Проблемы с запястьями\n" +
            "• Боли в спине\n" +
            "• Нестабильность суставов");

        binding.textViewAlternatives.setText(
            "• Жим гантелей сидя\n" +
            "• Армейский жим\n" +
            "• Жим штанги\n" +
            "• Жим в тренажере");
    }

    private void setupDumbbellUprightRow() {
        binding.textViewExerciseTitle.setText("Тяга гантелей к подбородку");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_dumbbell_upright);
        
        binding.textViewExerciseDescription.setText(
            "Упражнение для развития верха плеч и трапециевидных мышц");

        binding.textViewTargetMuscles.setText(
            "• Дельтовидные мышцы\n" +
            "• Трапециевидные мышцы\n" +
            "• Бицепсы\n" +
            "• Передние зубчатые мышцы\n" +
            "• Ротаторная манжета");

        binding.textViewTechnique.setText(
            "1. Встаньте прямо\n\n" +
            "2. Возьмите гантели\n\n" +
            "3. Локти в стороны\n\n" +
            "4. Поднимите к подбородку\n\n" +
            "5. Задержитесь наверху\n\n" +
            "6. Медленно опустите");

        binding.textViewCommonMistakes.setText(
            "• Раскачивание корпуса\n" +
            "• Опускание локтей\n" +
            "• Слишком тяжелый вес\n" +
            "• Неполная амплитуда");

        binding.textViewRecommendations.setText(
            "• Используйте средний вес\n" +
            "• 3 подхода по 12-15 повторений\n" +
            "• Держите локти выше кистей\n" +
            "• Контролируйте движение");

        binding.textViewContraindications.setText(
            "• Травмы плеч\n" +
            "• Синдром импинджмента\n" +
            "• Проблемы с запястьями\n" +
            "• Тендинит");

        binding.textViewAlternatives.setText(
            "• Тяга штанги к подбородку\n" +
            "• Тяга в тренажере\n" +
            "• Махи в стороны\n" +
            "• Шраги");
    }

    private void setupBentOverBarbellRow() {
        binding.textViewExerciseTitle.setText("Тяга штанги к груди в наклоне");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_bent_row);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для развития задних дельт и верха спины");

        binding.textViewTargetMuscles.setText(
            "• Задние дельты\n" +
            "• Трапециевидные мышцы\n" +
            "• Ромбовидные мышцы\n" +
            "• Широчайшие мышцы\n" +
            "• Бицепсы");

        binding.textViewTechnique.setText(
            "1. Наклонитесь вперед\n\n" +
            "2. Возьмите штангу\n\n" +
            "3. Спина прямая\n\n" +
            "4. Тяните к груди\n\n" +
            "5. Сведите лопатки\n\n" +
            "6. Плавно опустите");

        binding.textViewCommonMistakes.setText(
            "• Округление спины\n" +
            "• Недостаточный наклон\n" +
            "• Рывковые движения\n" +
            "• Работа только руками");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Держите спину прямой\n" +
            "• Фокус на задних дельтах");

        binding.textViewContraindications.setText(
            "• Травмы спины\n" +
            "• Грыжи позвоночника\n" +
            "• Проблемы с поясницей\n" +
            "• Головокружения");

        binding.textViewAlternatives.setText(
            "• Тяга гантелей\n" +
            "• Тяга в тренажере\n" +
            "• Махи в наклоне\n" +
            "• Обратные разведения");
    }

    private void setupBarbellUprightRow() {
        binding.textViewExerciseTitle.setText("Тяга штанги к подбородку");
        binding.imageViewExercise.setImageResource(R.drawable.shoulders_barbell_upright);
        
        binding.textViewExerciseDescription.setText(
            "Комплексное упражнение для развития плеч и трапеций");

        binding.textViewTargetMuscles.setText(
            "• Дельтовидные мышцы\n" +
            "• Трапециевидные мышцы\n" +
            "• Бицепсы\n" +
            "• Предплечья\n" +
            "• Верхняя часть спины");

        binding.textViewTechnique.setText(
            "1. Встаньте прямо\n\n" +
            "2. Возьмите штангу хватом сверху\n\n" +
            "3. Поднимите локти\n\n" +
            "4. Тяните к подбородку\n\n" +
            "5. Задержитесь наверху\n\n" +
            "6. Медленно опустите");

        binding.textViewCommonMistakes.setText(
            "• Слишком широкий хват\n" +
            "• Раскачивание корпуса\n" +
            "• Опускание локтей\n" +
            "• Неполная амплитуда");

        binding.textViewRecommendations.setText(
            "• Используйте умеренный вес\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Держите локти выше грифа\n" +
            "• Выполняйте плавно");

        binding.textViewContraindications.setText(
            "• Травмы плеч\n" +
            "• Синдром импинджмента\n" +
            "• Проблемы с запястьями\n" +
            "• Тендинит плеча");

        binding.textViewAlternatives.setText(
            "• Тяга гантелей к подбородку\n" +
            "• Тяга в тренажере\n" +
            "• Махи в стороны\n" +
            "• Шраги с гантелями");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
} 