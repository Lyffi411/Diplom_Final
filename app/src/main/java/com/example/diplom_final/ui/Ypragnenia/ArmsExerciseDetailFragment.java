package com.example.diplom_final.ui.Ypragnenia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.diplom_final.R;
import com.example.diplom_final.databinding.FragmentArmsExerciseDetailBinding;

public class ArmsExerciseDetailFragment extends Fragment {
    private FragmentArmsExerciseDetailBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentArmsExerciseDetailBinding.inflate(inflater, container, false);
        
        if (getArguments() != null) {
            String title = getArguments().getString("title");
            setExerciseDetails(title);
        }
        
        return binding.getRoot();
    }

    private void setExerciseDetails(String title) {
        switch (title) {
            case "Поочередное сгибание рук с гантелями":
                setupAlternateDumbbellCurl();
                break;
            case "Сгибание рук со штангой":
                setupBarbellCurl();
                break;
            case "Молотки с гантелями":
                setupHammerCurls();
                break;
            case "Французский жим лежа":
                setupLyingTricepsExtension();
                break;
            case "Разгибание рук в блоке":
                setupTricepsPushdown();
                break;
            case "Поочередное сгибание рук с разворотом сидя":
                setupSeatedAlternateTwistCurl();
                break;
            case "Сгибание на скамье Скотта на блоке с канатной рукоятью":
                setupScottCableRopeCurl();
                break;
            case "Сгибание одной руки на скамье":
                setupSingleArmBenchCurl();
                break;
            case "Сгибание одной руки на скамье Скотта":
                setupSingleArmScottCurl();
                break;
            case "Сгибание одной руки от колена":
                setupConcentrationCurl();
                break;
            case "Сгибание одной рукой в блоке с тросом":
                setupSingleArmCableCurl();
                break;
            case "Сгибание одной рукой на скамье Скотта в блоке":
                setupScottSingleCableCurl();
                break;
            case "Сгибание рук в блоке":
                setupCableCurl();
                break;
            case "Сгибание рук в блоке сидя на корточках":
                setupSquatCableCurl();
                break;
            case "Сгибание рук в тренажере":
                setupMachineCurl();
                break;
            case "Сгибание рук с гантелями сидя":
                setupSeatedDumbbellCurl();
                break;
            case "Сгибание рук со штангой прямым хватом":
                setupReverseBarbellCurl();
                break;
            case "Сгибание рук со штангой с кривым грифом параллельным хватом":
                setupEZBarParallelCurl();
                break;
            case "Сгибание рук со штангой сидя":
                setupSeatedBarbellCurl();
                break;
            case "Жим лежа узким хватом":
                setupCloseGripBench();
                break;
            case "Обратные отжимания от скамьи":
                setupBenchDips();
                break;
            case "Отжимания на брусьях":
                setupDips();
                break;
            case "Разгибание в трицепс-машине":
                setupTricepsMachine();
                break;
            case "Разгибание из-за головы кривой штанги параллельным хватом":
                setupEZBarOverhead();
                break;
            case "Разгибание одной руки из-за головы с гантелью":
                setupSingleOverhead();
                break;
            case "Разгибание одной руки из-за головы с гантелью сидя":
                setupSeatedOverhead();
                break;
            case "Разгибание одной руки с гантелью в наклоне":
                setupBentExtension();
                break;
            case "Разгибание рук в блоке обратным хватом":
                setupReversePushdown();
                break;
            case "Разгибание рук в блоке с V-рукоятью":
                setupVBarPushdown();
                break;
            case "Разгибание рук в блоке с канатом":
                setupRopePushdown();
                break;
            case "Французский жим с гантелями лежа":
                setupLyingDBExtension();
                break;
            case "Французский жим со штангой лежа":
                setupLyingBarExtension();
                break;
            case "Разгибание рук в блоке с веревками":
                setupRopeOverheadExtension();
                break;
            case "Разгибание рук в тренажере":
                setupMachineExtension();
                break;
            case "Разгибание рук из-за головы в верхнем блоке":
                setupOverheadCableExtension();
                break;
            case "Разгибание рук из-за головы в верхнем блоке с канатом":
                setupOverheadRopeExtension();
                break;
            case "Разгибание рук из-за головы с гантелью сидя":
                setupSeatedDBOverhead();
                break;
            case "Разгибание штанги из-за головы обратным хватом стоя":
                setupStandingReverseExtension();
                break;
            case "Разгибание штанги из-за головы стоя":
                setupStandingOverhead();
                break;
            case "Французский жим в блоке":
                setupCableFrenchPress();
                break;
            case "Французский жим на наклонной скамье":
                setupInclineFrenchPress();
                break;
        }
    }

    private void setupAlternateDumbbellCurl() {
        binding.textViewExerciseTitle.setText("Поочередное сгибание рук с гантелями");
        binding.imageViewExercise.setImageResource(R.drawable.arms_alternate_dumbbell_curl);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для развития бицепсов с поочередной работой рук");

        binding.textViewTargetMuscles.setText(
            "• Бицепс\n" +
            "• Плечевая мышца\n" +
            "• Брахиалис\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Встаньте прямо, гантели в руках\n\n" +
            "2. Поочередно сгибайте руки к плечам\n\n" +
            "3. В верхней точке разверните кисть\n\n" +
            "4. Медленно опускайте гантель\n\n" +
            "5. Повторите другой рукой");

        binding.textViewCommonMistakes.setText(
            "• Раскачивание корпуса\n" +
            "• Неполная амплитуда\n" +
            "• Отведение локтей от корпуса\n" +
            "• Слишком быстрое выполнение");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Контролируйте движение\n" +
            "• Чередуйте руки равномерно");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с запястьями\n" +
            "• Тендинит бицепса\n" +
            "• Боли в плечах");

        binding.textViewAlternatives.setText(
            "• Одновременные сгибания с гантелями\n" +
            "• Сгибания на скамье Скотта\n" +
            "• Сгибания с канатом\n" +
            "• Сгибания на блоке");
    }

    private void setupBarbellCurl() {
        binding.textViewExerciseTitle.setText("Сгибание рук со штангой");
        binding.imageViewExercise.setImageResource(R.drawable.arms_barbell_curl);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для набора массы бицепсов");

        binding.textViewTargetMuscles.setText(
            "• Бицепс\n" +
            "• Брахиалис\n" +
            "• Плечевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Возьмите штангу нижним хватом\n\n" +
            "2. Встаньте прямо, локти прижаты к корпусу\n\n" +
            "3. Поднимите штангу к плечам\n\n" +
            "4. Задержитесь на секунду\n\n" +
            "5. Медленно опустите штангу");

        binding.textViewCommonMistakes.setText(
            "• Раскачивание корпуса\n" +
            "• Отведение локтей\n" +
            "• Неполная амплитуда\n" +
            "• Слишком большой вес");

        binding.textViewRecommendations.setText(
            "• Начинайте с умеренного веса\n" +
            "• 3-4 подхода по 8-12 повторений\n" +
            "• Следите за техникой\n" +
            "• Контролируйте движение");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с запястьями\n" +
            "• Боли в пояснице\n" +
            "• Тендинит бицепса");

        binding.textViewAlternatives.setText(
            "• Сгибания с гантелями\n" +
            "• Сгибания на скамье Скотта\n" +
            "• Сгибания с EZ-грифом\n" +
            "• Сгибания в блоке");
    }

    private void setupHammerCurls() {
        binding.textViewExerciseTitle.setText("Молотки с гантелями");
        binding.imageViewExercise.setImageResource(R.drawable.arms_seated_dumbbell_curl);
        
        binding.textViewExerciseDescription.setText(
            "Упражнение для развития брахиалиса и бицепса");

        binding.textViewTargetMuscles.setText(
            "• Брахиалис\n" +
            "• Бицепс\n" +
            "• Плечевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Возьмите гантели нейтральным хватом\n\n" +
            "2. Встаньте прямо, руки вдоль тела\n\n" +
            "3. Поднимите гантели к плечам\n\n" +
            "4. Сохраняйте локти неподвижными\n\n" +
            "5. Медленно опустите гантели");

        binding.textViewCommonMistakes.setText(
            "• Раскачивание корпуса\n" +
            "• Сгибание в запястьях\n" +
            "• Отведение локтей\n" +
            "• Неполная амплитуда");

        binding.textViewRecommendations.setText(
            "• Используйте средний вес\n" +
            "• 3 подхода по 12-15 повторений\n" +
            "• Контролируйте движение\n" +
            "• Чередуйте руки");

        binding.textViewContraindications.setText(
            "• Травмы локтей\n" +
            "• Проблемы с запястьями\n" +
            "• Тендинит\n" +
            "• Туннельный синдром");

        binding.textViewAlternatives.setText(
            "• Классические сгибания\n" +
            "• Сгибания на скамье\n" +
            "• Сгибания с канатом\n" +
            "• Изолированные сгибания");
    }

    private void setupLyingTricepsExtension() {
        binding.textViewExerciseTitle.setText("Французский жим лежа");
        binding.imageViewExercise.setImageResource(R.drawable.arms_lying_db_extension);
        
        binding.textViewExerciseDescription.setText(
            "Изолирующее упражнение для трицепса");

        binding.textViewTargetMuscles.setText(
            "• Трицепс\n" +
            "• Анконеус\n" +
            "• Дельтовидная мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Лягте на скамью\n\n" +
            "2. Возьмите штангу/гантель\n\n" +
            "3. Опустите вес за голову\n\n" +
            "4. Выпрямите руки\n\n" +
            "5. Вернитесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Разведение локтей\n" +
            "• Движение в плечах\n" +
            "• Слишком большой вес\n" +
            "• Прогиб в спине");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Держите локти стабильными\n" +
            "• Выполняйте плавно");

        binding.textViewContraindications.setText(
            "• Травмы локтей\n" +
            "• Проблемы с плечами\n" +
            "• Боли в шее\n" +
            "• Артрит суставов");

        binding.textViewAlternatives.setText(
            "• Разгибания на блоке\n" +
            "• Отжимания на брусьях\n" +
            "• Жим узким хватом\n" +
            "• Разгибания с гантелью");
    }

    private void setupTricepsPushdown() {
        binding.textViewExerciseTitle.setText("Разгибание рук в блоке");
        binding.imageViewExercise.setImageResource(R.drawable.arms_triceps_pushdown);
        
        binding.textViewExerciseDescription.setText(
            "Базовое изолирующее упражнение для трицепсов");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (все головки)\n" +
            "• Локтевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Встаньте перед верхним блоком\n\n" +
            "2. Возьмите рукоять прямым хватом\n\n" +
            "3. Прижмите локти к корпусу\n\n" +
            "4. Разогните руки вниз\n\n" +
            "5. Медленно вернитесь вверх");

        binding.textViewCommonMistakes.setText(
            "• Отведение локтей\n" +
            "• Наклон корпуса\n" +
            "• Неполная амплитуда\n" +
            "• Использование инерции");

        binding.textViewRecommendations.setText(
            "• Подберите рабочий вес\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Держите локти неподвижно\n" +
            "• Контролируйте движение");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с запястьями\n" +
            "• Тендинит трицепса\n" +
            "• Нестабильность плеч");

        binding.textViewAlternatives.setText(
            "• Разгибания с канатом\n" +
            "• Разгибания обратным хватом\n" +
            "• Разгибания с V-образной рукоятью\n" +
            "• Французский жим в блоке");
    }

    private void setupSeatedAlternateTwistCurl() {
        binding.textViewExerciseTitle.setText("Поочередное сгибание рук с разворотом сидя");
        binding.imageViewExercise.setImageResource(R.drawable.arms_seated_alternate_curl);
        
        binding.textViewExerciseDescription.setText(
            "Изолирующее упражнение для бицепсов с акцентом на супинацию");

        binding.textViewTargetMuscles.setText(
            "• Бицепс\n" +
            "• Плечелучевая мышца\n" +
            "• Брахиалис\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Сядьте на скамью, спина прямая\n\n" +
            "2. Возьмите гантели нейтральным хватом\n\n" +
            "3. Сгибая руку, разворачивайте кисть наружу\n\n" +
            "4. В верхней точке максимально разверните кисть\n\n" +
            "5. Медленно опускайте с обратным разворотом");

        binding.textViewCommonMistakes.setText(
            "• Отклонение корпуса назад\n" +
            "• Неполный разворот кисти\n" +
            "• Слишком тяжелый вес\n" +
            "• Рывковые движения");

        binding.textViewRecommendations.setText(
            "• Используйте средний вес\n" +
            "• 3 подхода по 12-15 повторений\n" +
            "• Фокусируйтесь на супинации\n" +
            "• Делайте паузу в верхней точке");

        binding.textViewContraindications.setText(
            "• Проблемы с лучезапястным суставом\n" +
            "• Травмы локтей\n" +
            "• Тендинит\n" +
            "• Синдром карпального канала");

        binding.textViewAlternatives.setText(
            "• Сгибания с гантелями стоя\n" +
            "• Молотковые сгибания\n" +
            "• Сгибания на скамье Скотта\n" +
            "• Концентрированные сгибания");
    }

    private void setupScottCableRopeCurl() {
        binding.textViewExerciseTitle.setText("Сгибание на скамье Скотта на блоке с канатной рукоятью");
        binding.imageViewExercise.setImageResource(R.drawable.arms_scott_cable_rope);
        
        binding.textViewExerciseDescription.setText(
            "Изолированное упражнение для бицепсов с постоянным напряжением");

        binding.textViewTargetMuscles.setText(
            "• Бицепс\n" +
            "• Брахиалис\n" +
            "• Плечевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Установите скамью Скотта у нижнего блока\n\n" +
            "2. Возьмите канатную рукоять\n\n" +
            "3. Прижмите локти к подушке\n\n" +
            "4. Плавно сгибайте руки\n\n" +
            "5. Медленно возвращайтесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Отрыв локтей от подушки\n" +
            "• Использование инерции\n" +
            "• Неполная амплитуда\n" +
            "• Слишком большой вес");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Контролируйте движение\n" +
            "• Держите постоянное напряжение");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Тендинит бицепса\n" +
            "• Проблемы с плечами\n" +
            "• Боли в предплечьях");

        binding.textViewAlternatives.setText(
            "• Сгибания со штангой на скамье Скотта\n" +
            "• Сгибания с гантелями на скамье Скотта\n" +
            "• Сгибания в блоке стоя\n" +
            "• Изолированные сгибания на блоке");
    }

    private void setupSingleArmBenchCurl() {
        binding.textViewExerciseTitle.setText("Сгибание одной руки на скамье");
        binding.imageViewExercise.setImageResource(R.drawable.arms_single_arm_bench_curl);
        
        binding.textViewExerciseDescription.setText(
            "Изолирующее упражнение для проработки каждого бицепса отдельно");

        binding.textViewTargetMuscles.setText(
            "• Бицепс\n" +
            "• Брахиалис\n" +
            "• Плечевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Сядьте на скамью, рука с гантелью опущена\n\n" +
            "2. Прижмите локоть к внутренней части бедра\n\n" +
            "3. Медленно поднимите гантель к плечу\n\n" +
            "4. Задержитесь на секунду в верхней точке\n\n" +
            "5. Плавно опустите гантель");

        binding.textViewCommonMistakes.setText(
            "• Раскачивание корпуса\n" +
            "• Отрыв локтя от бедра\n" +
            "• Неполная амплитуда\n" +
            "• Слишком быстрое выполнение");

        binding.textViewRecommendations.setText(
            "• Выбирайте вес, позволяющий сохранять технику\n" +
            "• 3 подхода по 12-15 повторений на каждую руку\n" +
            "• Фокусируйтесь на сокращении бицепса\n" +
            "• Делайте паузу в верхней точке");

        binding.textViewContraindications.setText(
            "• Травмы локтевого сустава\n" +
            "• Тендинит бицепса\n" +
            "• Проблемы с запястьем\n" +
            "• Боли в плече");

        binding.textViewAlternatives.setText(
            "• Концентрированные сгибания\n" +
            "• Сгибания на скамье Скотта\n" +
            "• Изолированные сгибания в блоке\n" +
            "• Сгибания с опорой о колено");
    }

    private void setupSingleArmScottCurl() {
        binding.textViewExerciseTitle.setText("Сгибание одной руки на скамье Скотта");
        binding.imageViewExercise.setImageResource(R.drawable.arms_single_scott_curl);
        
        binding.textViewExerciseDescription.setText(
            "Строгое изолирующее упражнение для максимальной концентрации на бицепсе");

        binding.textViewTargetMuscles.setText(
            "• Бицепс\n" +
            "• Брахиалис\n" +
            "• Плечевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Установите локоть на подушку скамьи Скотта\n\n" +
            "2. Возьмите гантель нижним хватом\n\n" +
            "3. Сгибайте руку, поднимая гантель\n\n" +
            "4. Сожмите бицепс в верхней точке\n\n" +
            "5. Медленно опустите гантель");

        binding.textViewCommonMistakes.setText(
            "• Отрыв локтя от подушки\n" +
            "• Использование инерции\n" +
            "• Неполное разгибание руки\n" +
            "• Слишком тяжелый вес");

        binding.textViewRecommendations.setText(
            "• Начинайте с умеренного веса\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Выполняйте движение плавно\n" +
            "• Равномерно прорабатывайте обе руки");

        binding.textViewContraindications.setText(
            "• Травмы локтевого сустава\n" +
            "• Тендинит бицепса\n" +
            "• Проблемы с запястьем\n" +
            "• Воспаление суставной сумки");

        binding.textViewAlternatives.setText(
            "• Сгибания со штангой на скамье Скотта\n" +
            "• Концентрированные сгибания\n" +
            "• Сгибания в блоке на скамье Скотта\n" +
            "• Изолированные сгибания стоя");
    }

    private void setupConcentrationCurl() {
        binding.textViewExerciseTitle.setText("Сгибание одной руки от колена");
        binding.imageViewExercise.setImageResource(R.drawable.arms_concentration_curl);
        
        binding.textViewExerciseDescription.setText(
            "Изолирующее упражнение для максимальной концентрации на бицепсе");

        binding.textViewTargetMuscles.setText(
            "• Бицепс\n" +
            "• Брахиалис\n" +
            "• Плечевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Сядьте на скамью, наклонитесь вперед\n\n" +
            "2. Упритесь локтем во внутреннюю часть бедра\n\n" +
            "3. Возьмите гантель нижним хватом\n\n" +
            "4. Медленно поднимите гантель к плечу\n\n" +
            "5. Плавно опустите в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Отрыв локтя от бедра\n" +
            "• Раскачивание корпуса\n" +
            "• Неполная амплитуда\n" +
            "• Использование инерции");

        binding.textViewRecommendations.setText(
            "• Используйте умеренный вес\n" +
            "• 3 подхода по 12-15 повторений\n" +
            "• Фокусируйтесь на сокращении бицепса\n" +
            "• Делайте паузу в верхней точке");

        binding.textViewContraindications.setText(
            "• Травмы локтевого сустава\n" +
            "• Тендинит бицепса\n" +
            "• Проблемы с поясницей\n" +
            "• Боли в коленях");

        binding.textViewAlternatives.setText(
            "• Сгибания на скамье Скотта\n" +
            "• Изолированные сгибания в блоке\n" +
            "• Сгибания с опорой о наклонную скамью\n" +
            "• Сгибания одной руки стоя");
    }

    private void setupSingleArmCableCurl() {
        binding.textViewExerciseTitle.setText("Сгибание одной рукой в блоке с тросом");
        binding.imageViewExercise.setImageResource(R.drawable.arms_single_cable_curl);
        
        binding.textViewExerciseDescription.setText(
            "Изолированное упражнение с постоянным напряжением на бицепс");

        binding.textViewTargetMuscles.setText(
            "• Бицепс\n" +
            "• Брахиалис\n" +
            "• Плечевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Встаньте боком к нижнему блоку\n\n" +
            "2. Возьмите рукоять нижним хватом\n\n" +
            "3. Прижмите локоть к корпусу\n\n" +
            "4. Плавно согните руку\n\n" +
            "5. Медленно вернитесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Отведение локтя от корпуса\n" +
            "• Раскачивание тела\n" +
            "• Слишком быстрое выполнение\n" +
            "• Неполная амплитуда");

        binding.textViewRecommendations.setText(
            "• Выбирайте средний вес\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Сохраняйте постоянное напряжение\n" +
            "• Контролируйте движение");

        binding.textViewContraindications.setText(
            "• Травмы локтевого сустава\n" +
            "• Тендинит бицепса\n" +
            "• Проблемы с плечом\n" +
            "• Нестабильность запястья");

        binding.textViewAlternatives.setText(
            "• Сгибания с гантелью\n" +
            "• Сгибания на скамье Скотта\n" +
            "• Концентрированные сгибания\n" +
            "• Сгибания с канатной рукоятью");
    }

    private void setupScottSingleCableCurl() {
        binding.textViewExerciseTitle.setText("Сгибание одной рукой на скамье Скотта в блоке");
        binding.imageViewExercise.setImageResource(R.drawable.arms_scott_single_cable);
        
        binding.textViewExerciseDescription.setText(
            "Комбинированное изолирующее упражнение с постоянным напряжением");

        binding.textViewTargetMuscles.setText(
            "• Бицепс\n" +
            "• Брахиалис\n" +
            "• Плечевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Установите скамью Скотта у нижнего блока\n\n" +
            "2. Положите локоть на подушку\n\n" +
            "3. Возьмите рукоять нижним хватом\n\n" +
            "4. Плавно согните руку\n\n" +
            "5. Медленно опустите с контролем");

        binding.textViewCommonMistakes.setText(
            "• Отрыв локтя от подушки\n" +
            "• Использование инерции\n" +
            "• Неполная амплитуда\n" +
            "• Слишком большой вес");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Держите постоянное напряжение\n" +
            "• Выполняйте движение плавно");

        binding.textViewContraindications.setText(
            "• Травмы локтевого сустава\n" +
            "• Тендинит бицепса\n" +
            "• Проблемы с запястьем\n" +
            "• Воспаление суставной сумки");

        binding.textViewAlternatives.setText(
            "• Сгибания с гантелями на скамье Скотта\n" +
            "• Сгибания со штангой на скамье Скотта\n" +
            "• Концентрированные сгибания\n" +
            "• Сгибания в блоке стоя");
    }

    private void setupCableCurl() {
        binding.textViewExerciseTitle.setText("Сгибание рук в блоке");
        binding.imageViewExercise.setImageResource(R.drawable.arms_cable_curl);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для бицепсов с постоянным сопротивлением");

        binding.textViewTargetMuscles.setText(
            "• Бицепс\n" +
            "• Брахиалис\n" +
            "• Плечевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Встаньте перед нижним блоком\n\n" +
            "2. Возьмите рукоять нижним хватом\n\n" +
            "3. Прижмите локти к корпусу\n\n" +
            "4. Согните руки, подняв рукоять\n\n" +
            "5. Плавно опустите вниз");

        binding.textViewCommonMistakes.setText(
            "• Раскачивание корпуса\n" +
            "• Отведение локтей\n" +
            "• Неполная амплитуда\n" +
            "• Рывковые движения");

        binding.textViewRecommendations.setText(
            "• Выберите комфортный вес\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Сохраняйте постоянное напряжение\n" +
            "• Контролируйте движение");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Тендинит бицепса\n" +
            "• Проблемы с плечами\n" +
            "• Нестабильность запястий");

        binding.textViewAlternatives.setText(
            "• Сгибания со штангой\n" +
            "• Сгибания с гантелями\n" +
            "• Сгибания на скамье Скотта\n" +
            "• Сгибания с канатной рукоятью");
    }

    private void setupSquatCableCurl() {
        binding.textViewExerciseTitle.setText("Сгибание рук в блоке сидя на корточках");
        binding.imageViewExercise.setImageResource(R.drawable.arms_squat_cable_curl);
        
        binding.textViewExerciseDescription.setText(
            "Вариация сгибаний в блоке для максимального сокращения бицепса");
    
        binding.textViewTargetMuscles.setText(
            "• Бицепс\n" +
            "• Брахиалис\n" +
            "• Плечевая мышца\n" +
            "• Мышцы предплечья");
    
        binding.textViewTechnique.setText(
            "1. Сядьте на корточки перед блоком\n\n" +
            "2. Возьмите рукоять нижним хватом\n\n" +
            "3. Держите локти прижатыми к бедрам\n\n" +
            "4. Выполните сгибание рук\n\n" +
            "5. Медленно опустите вес");
    
        binding.textViewCommonMistakes.setText(
            "• Потеря равновесия\n" +
            "• Отведение локтей\n" +
            "• Использование инерции\n" +
            "• Неполная амплитуда");
    
        binding.textViewRecommendations.setText(
            "• Используйте умеренный вес\n" +
            "• 3 подхода по 12-15 повторений\n" +
            "• Сохраняйте стабильное положение\n" +
            "• Фокусируйтесь на сокращении бицепса");
    
        binding.textViewContraindications.setText(
            "• Проблемы с коленями\n" +
            "• Травмы локтей\n" +
            "• Проблемы с равновесием\n" +
            "• Боли в пояснице");
    
        binding.textViewAlternatives.setText(
            "• Стандартные сгибания в блоке\n" +
            "• Сгибания на скамье Скотта\n" +
            "• Сгибания с канатом\n" +
            "• Изолированные сгибания");
    }
    
    private void setupMachineCurl() {
        binding.textViewExerciseTitle.setText("Сгибание рук в тренажере");
        binding.imageViewExercise.setImageResource(R.drawable.arms_machine_curl);
        
        binding.textViewExerciseDescription.setText(
            "Изолированное упражнение на бицепс с фиксированной траекторией");
    
        binding.textViewTargetMuscles.setText(
            "• Бицепс\n" +
            "• Брахиалис\n" +
            "• Плечевая мышца\n" +
            "• Мышцы предплечья");
    
        binding.textViewTechnique.setText(
            "1. Отрегулируйте сиденье тренажера\n\n" +
            "2. Установите локти на подушку\n\n" +
            "3. Возьмитесь за рукояти\n\n" +
            "4. Выполните сгибание рук\n\n" +
            "5. Плавно вернитесь в исходное положение");
    
        binding.textViewCommonMistakes.setText(
            "• Отрыв локтей от подушки\n" +
            "• Слишком большой вес\n" +
            "• Неполная амплитуда\n" +
            "• Рывковые движения");
    
        binding.textViewRecommendations.setText(
            "• Правильно настройте тренажер\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Контролируйте движение\n" +
            "• Фокусируйтесь на сокращении мышц");
    
        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Тендинит бицепса\n" +
            "• Проблемы с плечами\n" +
            "• Воспаление суставной сумки");
    
        binding.textViewAlternatives.setText(
            "• Сгибания со штангой\n" +
            "• Сгибания с гантелями\n" +
            "• Сгибания в блоке\n" +
            "• Сгибания на скамье Скотта");
    }

    private void setupSeatedDumbbellCurl() {
        binding.textViewExerciseTitle.setText("Сгибание рук с гантелями сидя");
        binding.imageViewExercise.setImageResource(R.drawable.arms_seated_dumbbell_curl);
        
        binding.textViewExerciseDescription.setText(
            "Классическое упражнение для бицепсов с исключением читинга");

        binding.textViewTargetMuscles.setText(
            "• Бицепс\n" +
            "• Брахиалис\n" +
            "• Плечевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Сядьте на скамью, спина прямая\n\n" +
            "2. Возьмите гантели нижним хватом\n\n" +
            "3. Прижмите локти к корпусу\n\n" +
            "4. Поднимите гантели к плечам\n\n" +
            "5. Медленно опустите вниз");

        binding.textViewCommonMistakes.setText(
            "• Раскачивание корпуса\n" +
            "• Отведение локтей\n" +
            "• Неполная амплитуда\n" +
            "• Использование инерции");

        binding.textViewRecommendations.setText(
            "• Выберите подходящий вес\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Контролируйте движение\n" +
            "• Следите за положением спины");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с запястьями\n" +
            "• Тендинит бицепса\n" +
            "• Боли в пояснице");

        binding.textViewAlternatives.setText(
            "• Сгибания стоя\n" +
            "• Сгибания на скамье Скотта\n" +
            "• Концентрированные сгибания\n" +
            "• Сгибания в блоке");
    }

    private void setupReverseBarbellCurl() {
        binding.textViewExerciseTitle.setText("Сгибание рук со штангой прямым хватом");
        binding.imageViewExercise.setImageResource(R.drawable.arms_reverse_curl);
        
        binding.textViewExerciseDescription.setText(
            "Упражнение для развития брахиалиса и мышц предплечья");

        binding.textViewTargetMuscles.setText(
            "• Брахиалис\n" +
            "• Мышцы предплечья\n" +
            "• Бицепс\n" +
            "• Плечевая мышца");

        binding.textViewTechnique.setText(
            "1. Возьмите штангу прямым хватом\n\n" +
            "2. Встаньте прямо, локти у корпуса\n\n" +
            "3. Поднимите штангу к плечам\n\n" +
            "4. Зафиксируйте на секунду\n\n" +
            "5. Медленно опустите вниз");

        binding.textViewCommonMistakes.setText(
            "• Раскачивание корпуса\n" +
            "• Слишком большой вес\n" +
            "• Отведение локтей\n" +
            "• Неполная амплитуда");

        binding.textViewRecommendations.setText(
            "• Используйте легкий вес\n" +
            "• 3 подхода по 12-15 повторений\n" +
            "• Выполняйте движение плавно\n" +
            "• Концентрируйтесь на мышцах предплечья");

        binding.textViewContraindications.setText(
            "• Травмы запястий\n" +
            "• Проблемы с локтями\n" +
            "• Тендинит\n" +
            "• Синдром карпального канала");

        binding.textViewAlternatives.setText(
            "• Обратные сгибания с гантелями\n" +
            "• Обратные сгибания в блоке\n" +
            "• Молотковые сгибания\n" +
            "• Сгибания на скамье Скотта прямым хватом");
    }

    private void setupEZBarParallelCurl() {
        binding.textViewExerciseTitle.setText("Сгибание рук со штангой с кривым грифом параллельным хватом");
        binding.imageViewExercise.setImageResource(R.drawable.arms_ez_bar_curl);
        
        binding.textViewExerciseDescription.setText(
            "Упражнение для бицепсов с комфортным положением запястий");

        binding.textViewTargetMuscles.setText(
            "• Бицепс\n" +
            "• Брахиалис\n" +
            "• Плечевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Возьмитесь за изогнутый гриф параллельным хватом\n\n" +
            "2. Встаньте прямо, локти прижаты\n\n" +
            "3. Поднимите штангу к плечам\n\n" +
            "4. Сожмите бицепсы в верхней точке\n\n" +
            "5. Плавно опустите вниз");

        binding.textViewCommonMistakes.setText(
            "• Раскачивание корпуса\n" +
            "• Отведение локтей\n" +
            "• Неполная амплитуда\n" +
            "• Рывковые движения");

        binding.textViewRecommendations.setText(
            "• Подберите комфортный вес\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Следите за положением запястий\n" +
            "• Контролируйте движение");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с запястьями\n" +
            "• Тендинит бицепса\n" +
            "• Боли в предплечьях");

        binding.textViewAlternatives.setText(
            "• Сгибания со штангой\n" +
            "• Сгибания с гантелями\n" +
            "• Сгибания на скамье Скотта\n" +
            "• Сгибания в блоке");
    }

    private void setupSeatedBarbellCurl() {
        binding.textViewExerciseTitle.setText("Сгибание рук со штангой сидя");
        binding.imageViewExercise.setImageResource(R.drawable.arms_seated_barbell_curl);
        
        binding.textViewExerciseDescription.setText(
            "Строгое упражнение для бицепсов с исключением читинга");

        binding.textViewTargetMuscles.setText(
            "• Бицепс\n" +
            "• Брахиалис\n" +
            "• Плечевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Сядьте на скамью, спина прямая\n\n" +
            "2. Возьмите штангу нижним хватом\n\n" +
            "3. Прижмите локти к корпусу\n\n" +
            "4. Поднимите штангу к плечам\n\n" +
            "5. Медленно опустите вниз");

        binding.textViewCommonMistakes.setText(
            "• Отклонение корпуса назад\n" +
            "• Отведение локтей\n" +
            "• Неполная амплитуда\n" +
            "• Использование инерции");

        binding.textViewRecommendations.setText(
            "• Используйте меньший вес, чем стоя\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Следите за положением спины\n" +
            "• Выполняйте движение плавно");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с запястьями\n" +
            "• Боли в пояснице\n" +
            "• Тендинит бицепса");

        binding.textViewAlternatives.setText(
            "• Сгибания с гантелями сидя\n" +
            "• Сгибания на скамье Скотта\n" +
            "• Сгибания с EZ-грифом сидя\n" +
            "• Изолированные сгибания");
    }

    private void setupCloseGripBench() {
        binding.textViewExerciseTitle.setText("Жим лежа узким хватом");
        binding.imageViewExercise.setImageResource(R.drawable.arms_close_grip_bench);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для развития трицепсов и груди");

        binding.textViewTargetMuscles.setText(
            "• Трицепс\n" +
            "• Грудные мышцы\n" +
            "• Передний пучок дельты\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Лягте на скамью, хват уже ширины плеч\n\n" +
            "2. Опустите штангу к нижней части груди\n\n" +
            "3. Локти движутся вдоль корпуса\n\n" +
            "4. Выжмите штангу вверх\n\n" +
            "5. Вернитесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Слишком узкий хват\n" +
            "• Разведение локтей\n" +
            "• Отрыв таза от скамьи\n" +
            "• Неполная амплитуда");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 8-12 повторений\n" +
            "• Держите локти близко к корпусу\n" +
            "• Используйте страховку");

        binding.textViewContraindications.setText(
            "• Травмы плечевых суставов\n" +
            "• Проблемы с локтями\n" +
            "• Боли в запястьях\n" +
            "• Травмы грудных мышц");

        binding.textViewAlternatives.setText(
            "• Отжимания узким хватом\n" +
            "• Французский жим\n" +
            "• Разгибания в блоке\n" +
            "• Жим гантелей узким хватом");
    }

    private void setupBenchDips() {
        binding.textViewExerciseTitle.setText("Обратные отжимания от скамьи");
        binding.imageViewExercise.setImageResource(R.drawable.arms_bench_dips);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для трицепсов с собственным весом");

        binding.textViewTargetMuscles.setText(
            "• Трицепс\n" +
            "• Передняя дельта\n" +
            "• Грудные мышцы\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Упритесь руками в скамью за спиной\n\n" +
            "2. Ноги вытяните вперед\n\n" +
            "3. Опустите таз, сгибая руки\n\n" +
            "4. Разогните руки, поднимая тело\n\n" +
            "5. Не допускайте полного локтевого замка");

        binding.textViewCommonMistakes.setText(
            "• Слишком широкая постановка рук\n" +
            "• Недостаточная глубина\n" +
            "• Сутулость плеч\n" +
            "• Рывковые движения");

        binding.textViewRecommendations.setText(
            "• Начинайте с высокой скамьи\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Держите локти близко к телу\n" +
            "• Контролируйте движение");

        binding.textViewContraindications.setText(
            "• Травмы плечевых суставов\n" +
            "• Проблемы с локтями\n" +
            "• Боли в запястьях\n" +
            "• Нестабильность плечевого пояса");

        binding.textViewAlternatives.setText(
            "• Отжимания на брусьях\n" +
            "• Отжимания узким хватом\n" +
            "• Разгибания в блоке\n" +
            "• Французский жим");
    }

    private void setupDips() {
        binding.textViewExerciseTitle.setText("Отжимания на брусьях");
        binding.imageViewExercise.setImageResource(R.drawable.arms_dips);
        
        binding.textViewExerciseDescription.setText(
            "Эффективное упражнение для развития трицепсов и груди");

        binding.textViewTargetMuscles.setText(
            "• Трицепс\n" +
            "• Грудные мышцы\n" +
            "• Передняя дельта\n" +
            "• Зубчатые мышцы");

        binding.textViewTechnique.setText(
            "1. Примите упор на брусьях\n\n" +
            "2. Слегка наклоните корпус вперед\n\n" +
            "3. Опуститесь, сгибая руки\n\n" +
            "4. Разогните руки, поднимаясь вверх\n\n" +
            "5. Контролируйте движение");

        binding.textViewCommonMistakes.setText(
            "• Недостаточная глубина\n" +
            "• Раскачивание тела\n" +
            "• Слишком сильный наклон\n" +
            "• Полный локтевой замок");

        binding.textViewRecommendations.setText(
            "• Начинайте с собственного веса\n" +
            "• 3-4 подхода по 8-12 повторений\n" +
            "• Следите за техникой\n" +
            "• Используйте помощь при необходимости");

        binding.textViewContraindications.setText(
            "• Травмы плечевых суставов\n" +
            "• Проблемы с локтями\n" +
            "• Нестабильность плеч\n" +
            "• Боли в груди");

        binding.textViewAlternatives.setText(
            "• Отжимания от скамьи\n" +
            "• Жим узким хватом\n" +
            "• Разгибания в блоке\n" +
            "• Французский жим");
    }

    private void setupTricepsMachine() {
        binding.textViewExerciseTitle.setText("Разгибание в трицепс-машине");
        binding.imageViewExercise.setImageResource(R.drawable.arms_triceps_machine);
        
        binding.textViewExerciseDescription.setText(
            "Изолированное упражнение для трицепсов с фиксированной траекторией");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (все головки)\n" +
            "• Локтевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Отрегулируйте сиденье и вес\n\n" +
            "2. Возьмитесь за рукояти\n\n" +
            "3. Полностью разогните руки\n\n" +
            "4. Медленно вернитесь в исходное положение\n\n" +
            "5. Не используйте инерцию");

        binding.textViewCommonMistakes.setText(
            "• Отрыв локтей от подушки\n" +
            "• Слишком большой вес\n" +
            "• Неполная амплитуда\n" +
            "• Рывковые движения");

        binding.textViewRecommendations.setText(
            "• Правильно настройте тренажер\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Контролируйте движение\n" +
            "• Фокусируйтесь на сокращении мышц");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Тендинит бицепса\n" +
            "• Проблемы с плечами\n" +
            "• Воспаление суставной сумки");

        binding.textViewAlternatives.setText(
            "• Сгибания со штангой\n" +
            "• Сгибания с гантелями\n" +
            "• Сгибания в блоке\n" +
            "• Сгибания на скамье Скотта");
    }

    private void setupEZBarOverhead() {
        binding.textViewExerciseTitle.setText("Разгибание из-за головы кривой штанги параллельным хватом");
        binding.imageViewExercise.setImageResource(R.drawable.arms_ez_bar_overhead);
        
        binding.textViewExerciseDescription.setText(
            "Эффективное упражнение для всех головок трицепса");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (все головки)\n" +
            "• Локтевая мышца\n" +
            "• Задняя дельта\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Возьмите EZ-гриф параллельным хватом\n\n" +
            "2. Поднимите штангу за голову\n\n" +
            "3. Удерживая локти неподвижно, разогните руки\n\n" +
            "4. Полностью выпрямите руки вверх\n\n" +
            "5. Медленно опустите штангу за голову");

        binding.textViewCommonMistakes.setText(
            "• Движение локтей\n" +
            "• Слишком большой вес\n" +
            "• Неполная амплитуда\n" +
            "• Прогиб в пояснице");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Держите локти близко к голове\n" +
            "• Контролируйте движение");

        binding.textViewContraindications.setText(
            "• Травмы плечевых суставов\n" +
            "• Проблемы с локтями\n" +
            "• Нестабильность плеч\n" +
            "• Боли в шее");

        binding.textViewAlternatives.setText(
            "• Французский жим лежа\n" +
            "• Разгибания одной рукой\n" +
            "• Разгибания в блоке\n" +
            "• Жим узким хватом");
    }

    private void setupSingleOverhead() {
        binding.textViewExerciseTitle.setText("Разгибание одной руки из-за головы с гантелью");
        binding.imageViewExercise.setImageResource(R.drawable.arms_single_overhead);
        
        binding.textViewExerciseDescription.setText(
            "Изолированное упражнение для проработки трицепса каждой руки");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (все головки)\n" +
            "• Локтевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Поднимите гантель за голову\n\n" +
            "2. Зафиксируйте локоть вертикально\n\n" +
            "3. Разогните руку вверх\n\n" +
            "4. Задержитесь в верхней точке\n\n" +
            "5. Медленно опустите гантель");

        binding.textViewCommonMistakes.setText(
            "• Движение локтя\n" +
            "• Наклон корпуса\n" +
            "• Неполная амплитуда\n" +
            "• Рывковые движения");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3 подхода по 12-15 повторений\n" +
            "• Контролируйте движение\n" +
            "• Равномерно прорабатывайте обе руки");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с плечами\n" +
            "• Нестабильность плечевого пояса\n" +
            "• Боли в шее");

        binding.textViewAlternatives.setText(
            "• Разгибания с двумя руками\n" +
            "• Разгибания в блоке\n" +
            "• Французский жим\n" +
            "• Разгибания с канатом");
    }

    private void setupSeatedOverhead() {
        binding.textViewExerciseTitle.setText("Разгибание одной руки из-за головы с гантелью сидя");
        binding.imageViewExercise.setImageResource(R.drawable.arms_seated_overhead);
        
        binding.textViewExerciseDescription.setText(
            "Строгое изолирующее упражнение для трицепса в положении сидя");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (все головки)\n" +
            "• Локтевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Сядьте на скамью со спинкой\n\n" +
            "2. Поднимите гантель за голову\n\n" +
            "3. Зафиксируйте локоть вертикально\n\n" +
            "4. Разогните руку вверх\n\n" +
            "5. Плавно вернитесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Отклонение локтя\n" +
            "• Наклон корпуса\n" +
            "• Неполная амплитуда\n" +
            "• Использование инерции");

        binding.textViewRecommendations.setText(
            "• Используйте спинку для стабильности\n" +
            "• 3 подхода по 12-15 повторений\n" +
            "• Выполняйте движение плавно\n" +
            "• Следите за положением локтя");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с плечами\n" +
            "• Боли в шее\n" +
            "• Нестабильность плечевого пояса");

        binding.textViewAlternatives.setText(
            "• Разгибания стоя\n" +
            "• Разгибания в блоке\n" +
            "• Французский жим сидя\n" +
            "• Разгибания с канатом");
    }

    private void setupBentExtension() {
        binding.textViewExerciseTitle.setText("Разгибание одной руки с гантелью в наклоне");
        binding.imageViewExercise.setImageResource(R.drawable.arms_bent_extension);
        
        binding.textViewExerciseDescription.setText(
            "Изолирующее упражнение для трицепса в другом угле нагрузки");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (все головки)\n" +
            "• Локтевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Наклонитесь вперед, упритесь рукой в скамью\n\n" +
            "2. Поднимите гантель к плечу\n\n" +
            "3. Разогните руку назад\n\n" +
            "4. Зафиксируйте на секунду\n\n" +
            "5. Медленно вернитесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Движение плеча\n" +
            "• Сутулость спины\n" +
            "• Неполная амплитуда\n" +
            "• Рывковые движения");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3 подхода по 12-15 повторений\n" +
            "• Держите спину прямой\n" +
            "• Контролируйте движение");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с поясницей\n" +
            "• Боли в плечах\n" +
            "• Нестабильность плечевого пояса");

        binding.textViewAlternatives.setText(
            "• Разгибания в блоке\n" +
            "• Разгибания из-за головы\n" +
            "• Французский жим\n" +
            "• Отжимания узким хватом");
    }

    private void setupReversePushdown() {
        binding.textViewExerciseTitle.setText("Разгибание рук в блоке обратным хватом");
        binding.imageViewExercise.setImageResource(R.drawable.arms_reverse_pushdown);
        
        binding.textViewExerciseDescription.setText(
            "Вариация разгибаний для акцента на внутреннюю головку трицепса");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (внутренняя головка)\n" +
            "• Локтевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Встаньте перед верхним блоком\n\n" +
            "2. Возьмите рукоять обратным хватом\n\n" +
            "3. Прижмите локти к корпусу\n\n" +
            "4. Разогните руки вниз\n\n" +
            "5. Плавно вернитесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Отведение локтей\n" +
            "• Раскачивание корпуса\n" +
            "• Неполная амплитуда\n" +
            "• Слишком большой вес");

        binding.textViewRecommendations.setText(
            "• Используйте меньший вес\n" +
            "• 3 подхода по 12-15 повторений\n" +
            "• Фокусируйтесь на сокращении трицепса\n" +
            "• Выполняйте движение плавно");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с запястьями\n" +
            "• Тендинит трицепса\n" +
            "• Синдром карпального канала");

        binding.textViewAlternatives.setText(
            "• Классические разгибания в блоке\n" +
            "• Разгибания с канатом\n" +
            "• Разгибания с V-образной рукоятью\n" +
            "• Французский жим обратным хватом");
    }

    private void setupVBarPushdown() {
        binding.textViewExerciseTitle.setText("Разгибание рук в блоке с V-рукоятью");
        binding.imageViewExercise.setImageResource(R.drawable.arms_v_bar_pushdown);
        
        binding.textViewExerciseDescription.setText(
            "Комфортная вариация разгибаний для запястий");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (все головки)\n" +
            "• Локтевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Встаньте перед верхним блоком\n\n" +
            "2. Возьмите V-образную рукоять\n\n" +
            "3. Прижмите локти к корпусу\n\n" +
            "4. Разогните руки вниз\n\n" +
            "5. Медленно поднимите рукоять");

        binding.textViewCommonMistakes.setText(
            "• Отведение локтей\n" +
            "• Наклон корпуса\n" +
            "• Неполная амплитуда\n" +
            "• Рывковые движения");

        binding.textViewRecommendations.setText(
            "• Подберите комфортный вес\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Следите за положением локтей\n" +
            "• Контролируйте движение");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с запястьями\n" +
            "• Тендинит трицепса\n" +
            "• Нестабильность плеч");

        binding.textViewAlternatives.setText(
            "• Разгибания с прямой рукоятью\n" +
            "• Разгибания с канатом\n" +
            "• Разгибания обратным хватом\n" +
            "• Французский жим в блоке");
    }

    private void setupRopePushdown() {
        binding.textViewExerciseTitle.setText("Разгибание рук в блоке с канатом");
        binding.imageViewExercise.setImageResource(R.drawable.arms_rope_pushdown);
        
        binding.textViewExerciseDescription.setText(
            "Эффективное упражнение для проработки всех головок трицепса");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (все головки)\n" +
            "• Локтевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Встаньте перед верхним блоком\n\n" +
            "2. Возьмите концы каната\n\n" +
            "3. Прижмите локти к корпусу\n\n" +
            "4. Разведите концы каната при разгибании\n\n" +
            "5. Плавно вернитесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Отведение локтей\n" +
            "• Раскачивание корпуса\n" +
            "• Неполное разведение концов\n" +
            "• Использование инерции");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Акцентируйте внимание на разведении\n" +
            "• Контролируйте движение");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с запястьями\n" +
            "• Тендинит трицепса\n" +
            "• Нестабильность плеч");

        binding.textViewAlternatives.setText(
            "• Разгибания с прямой рукоятью\n" +
            "• Разгибания с V-образной рукоятью\n" +
            "• Разгибания обратным хватом\n" +
            "• Французский жим с канатом");
    }

    private void setupLyingDBExtension() {
        binding.textViewExerciseTitle.setText("Французский жим с гантелями лежа");
        binding.imageViewExercise.setImageResource(R.drawable.arms_lying_db_extension);
        
        binding.textViewExerciseDescription.setText(
            "Раздельная работа для каждой руки в положении лежа");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (все головки)\n" +
            "• Локтевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Лягте на скамью, гантели над грудью\n\n" +
            "2. Согните руки, опуская гантели ко лбу\n\n" +
            "3. Удерживайте локти вертикально\n\n" +
            "4. Разогните руки\n\n" +
            "5. Медленно вернитесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Движение локтей\n" +
            "• Разведение рук\n" +
            "• Неполная амплитуда\n" +
            "• Слишком тяжелый вес");

        binding.textViewRecommendations.setText(
            "• Используйте умеренный вес\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Держите локти стабильно\n" +
            "• Выполняйте движение синхронно");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с плечами\n" +
            "• Боли в запястьях\n" +
            "• Нестабильность плечевого пояса");

        binding.textViewAlternatives.setText(
            "• Французский жим со штангой\n" +
            "• Разгибания в блоке\n" +
            "• Разгибания из-за головы\n" +
            "• Жим узким хватом");
    }

    private void setupLyingBarExtension() {
        binding.textViewExerciseTitle.setText("Французский жим со штангой лежа");
        binding.imageViewExercise.setImageResource(R.drawable.arms_lying_french_press);
        
        binding.textViewExerciseDescription.setText(
            "Классическое упражнение для массы трицепсов");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (все головки)\n" +
            "• Локтевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Лягте на скамью, штанга над грудью\n\n" +
            "2. Возьмите гриф средним хватом\n\n" +
            "3. Согните руки, опуская штангу ко лбу\n\n" +
            "4. Разогните руки, не двигая локтями\n\n" +
            "5. Плавно вернитесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Движение локтей\n" +
            "• Слишком широкий хват\n" +
            "• Неполная амплитуда\n" +
            "• Использование инерции");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 8-12 повторений\n" +
            "• Используйте страховку\n" +
            "• Контролируйте движение");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с плечами\n" +
            "• Боли в запястьях\n" +
            "• Нестабильность плечевого пояса");

        binding.textViewAlternatives.setText(
            "• Французский жим с гантелями\n" +
            "• Французский жим с EZ-грифом\n" +
            "• Разгибания в блоке\n" +
            "• Жим узким хватом");
    }

    private void setupRopeOverheadExtension() {
        binding.textViewExerciseTitle.setText("Разгибание рук в блоке с веревками");
        binding.imageViewExercise.setImageResource(R.drawable.arms_rope_pushdown);
        
        binding.textViewExerciseDescription.setText(
            "Эффективное упражнение для трицепса с дополнительной ротацией в конце движения");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (все головки)\n" +
            "• Локтевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Встаньте перед верхним блоком\n\n" +
            "2. Возьмите веревочные рукояти\n\n" +
            "3. Прижмите локти к корпусу\n\n" +
            "4. Разогните руки и разведите веревки\n\n" +
            "5. Медленно вернитесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Отведение локтей от тела\n" +
            "• Использование слишком большого веса\n" +
            "• Неполное разведение веревок\n" +
            "• Рывковые движения");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Концентрируйтесь на разведении в конце\n" +
            "• Держите постоянное напряжение");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с плечами\n" +
            "• Тендинит трицепса\n" +
            "• Нестабильность плечевого пояса");

        binding.textViewAlternatives.setText(
            "• Разгибания с прямой рукоятью\n" +
            "• Разгибания с V-образной рукоятью\n" +
            "• Французский жим в блоке\n" +
            "• Разгибания обратным хватом");
    }

    private void setupMachineExtension() {
        binding.textViewExerciseTitle.setText("Разгибание рук в тренажере");
        binding.imageViewExercise.setImageResource(R.drawable.arms_machine_curl);
        
        binding.textViewExerciseDescription.setText(
            "Изолированное упражнение на бицепс с фиксированной траекторией");
    
        binding.textViewTargetMuscles.setText(
            "• Бицепс\n" +
            "• Брахиалис\n" +
            "• Плечевая мышца\n" +
            "• Мышцы предплечья");
    
        binding.textViewTechnique.setText(
            "1. Отрегулируйте сиденье тренажера\n\n" +
            "2. Установите локти на подушку\n\n" +
            "3. Возьмитесь за рукояти\n\n" +
            "4. Выполните сгибание рук\n\n" +
            "5. Плавно вернитесь в исходное положение");
    
        binding.textViewCommonMistakes.setText(
            "• Отрыв локтей от подушки\n" +
            "• Слишком большой вес\n" +
            "• Неполная амплитуда\n" +
            "• Рывковые движения");
    
        binding.textViewRecommendations.setText(
            "• Правильно настройте тренажер\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Контролируйте движение\n" +
            "• Фокусируйтесь на сокращении мышц");
    
        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Тендинит бицепса\n" +
            "• Проблемы с плечами\n" +
            "• Воспаление суставной сумки");
    
        binding.textViewAlternatives.setText(
            "• Сгибания со штангой\n" +
            "• Сгибания с гантелями\n" +
            "• Сгибания в блоке\n" +
            "• Сгибания на скамье Скотта");
    }

    private void setupOverheadCableExtension() {
        binding.textViewExerciseTitle.setText("Разгибание рук из-за головы в верхнем блоке");
        binding.imageViewExercise.setImageResource(R.drawable.arms_overhead_extension);
        
        binding.textViewExerciseDescription.setText(
            "Эффективное упражнение для трицепса с дополнительной ротацией в конце движения");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (все головки)\n" +
            "• Локтевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Встаньте перед верхним блоком\n\n" +
            "2. Возьмите рукоять прямым хватом\n\n" +
            "3. Прижмите локти к корпусу\n\n" +
            "4. Разогните руки вниз\n\n" +
            "5. Медленно вернитесь вверх");

        binding.textViewCommonMistakes.setText(
            "• Отведение локтей\n" +
            "• Наклон корпуса\n" +
            "• Неполная амплитуда\n" +
            "• Использование инерции");

        binding.textViewRecommendations.setText(
            "• Подберите рабочий вес\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Держите локти неподвижно\n" +
            "• Контролируйте движение");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с запястьями\n" +
            "• Тендинит трицепса\n" +
            "• Нестабильность плеч");

        binding.textViewAlternatives.setText(
            "• Разгибания с канатом\n" +
            "• Разгибания обратным хватом\n" +
            "• Разгибания с V-образной рукоятью\n" +
            "• Французский жим в блоке");
    }

    private void setupOverheadRopeExtension() {
        binding.textViewExerciseTitle.setText("Разгибание рук из-за головы в верхнем блоке с канатом");
        binding.imageViewExercise.setImageResource(R.drawable.arms_overhead_rope);
        
        binding.textViewExerciseDescription.setText(
            "Эффективное упражнение для трицепса с дополнительной ротацией в конце движения");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (все головки)\n" +
            "• Локтевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Встаньте перед верхним блоком\n\n" +
            "2. Возьмите веревочные рукояти\n\n" +
            "3. Прижмите локти к корпусу\n\n" +
            "4. Разогните руки и разведите веревки\n\n" +
            "5. Медленно вернитесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Отведение локтей от тела\n" +
            "• Использование слишком большого веса\n" +
            "• Неполное разведение веревок\n" +
            "• Рывковые движения");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Концентрируйтесь на разведении в конце\n" +
            "• Держите постоянное напряжение");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с плечами\n" +
            "• Тендинит трицепса\n" +
            "• Нестабильность плечевого пояса");

        binding.textViewAlternatives.setText(
            "• Разгибания с прямой рукоятью\n" +
            "• Разгибания с V-образной рукоятью\n" +
            "• Французский жим в блоке\n" +
            "• Разгибания обратным хватом");
    }

    private void setupSeatedDBOverhead() {
        binding.textViewExerciseTitle.setText("Разгибание рук из-за головы с гантелью сидя");
        binding.imageViewExercise.setImageResource(R.drawable.arms_seated_db_overhead);
        
        binding.textViewExerciseDescription.setText(
            "Строгое изолирующее упражнение для трицепса в положении сидя");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (все головки)\n" +
            "• Локтевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Сядьте на скамью со спинкой\n\n" +
            "2. Поднимите гантель за голову\n\n" +
            "3. Зафиксируйте локоть вертикально\n\n" +
            "4. Разогните руку вверх\n\n" +
            "5. Плавно вернитесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Отклонение локтя\n" +
            "• Наклон корпуса\n" +
            "• Неполная амплитуда\n" +
            "• Использование инерции");

        binding.textViewRecommendations.setText(
            "• Используйте спинку для стабильности\n" +
            "• 3 подхода по 12-15 повторений\n" +
            "• Выполняйте движение плавно\n" +
            "• Следите за положением локтя");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с плечами\n" +
            "• Боли в шее\n" +
            "• Нестабильность плечевого пояса");

        binding.textViewAlternatives.setText(
            "• Разгибания стоя\n" +
            "• Разгибания в блоке\n" +
            "• Французский жим сидя\n" +
            "• Разгибания с канатом");
    }

    private void setupStandingReverseExtension() {
        binding.textViewExerciseTitle.setText("Разгибание штанги из-за головы обратным хватом стоя");
        binding.imageViewExercise.setImageResource(R.drawable.arms_standing_reverse_extension);
        
        binding.textViewExerciseDescription.setText(
            "Сложное упражнение для опытных атлетов с акцентом на внешнюю головку трицепса");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (внешняя головка)\n" +
            "• Локтевая мышца\n" +
            "• Задняя дельта\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Возьмите штангу обратным хватом\n\n" +
            "2. Поднимите штангу за голову\n\n" +
            "3. Зафиксируйте локти\n\n" +
            "4. Разогните руки вверх\n\n" +
            "5. Медленно вернитесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Движение локтей\n" +
            "• Прогиб в пояснице\n" +
            "• Использование слишком большого веса\n" +
            "• Неконтролируемые движения");

        binding.textViewRecommendations.setText(
            "• Начинайте с очень легкого веса\n" +
            "• 3 подхода по 10-12 повторений\n" +
            "• Обязательно разомнитесь\n" +
            "• Выполняйте движение плавно");

        binding.textViewContraindications.setText(
            "• Травмы плечевых суставов\n" +
            "• Проблемы с локтями\n" +
            "• Боли в шее\n" +
            "• Ограниченная подвижность плеч");

        binding.textViewAlternatives.setText(
            "• Французский жим стоя\n" +
            "• Разгибания в блоке\n" +
            "• Разгибания с гантелями\n" +
            "• Жим узким хватом");
    }

    private void setupStandingOverhead() {
        binding.textViewExerciseTitle.setText("Разгибание штанги из-за головы стоя");
        binding.imageViewExercise.setImageResource(R.drawable.arms_standing_overhead);
        
        binding.textViewExerciseDescription.setText(
            "Классическое упражнение для развития массы трицепсов");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (все головки)\n" +
            "• Локтевая мышца\n" +
            "• Задняя дельта\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Возьмите штангу прямым хватом\n\n" +
            "2. Поднимите штангу за голову\n\n" +
            "3. Зафиксируйте локти вертикально\n\n" +
            "4. Разогните руки вверх\n\n" +
            "5. Плавно опустите штангу");

        binding.textViewCommonMistakes.setText(
            "• Движение локтей\n" +
            "• Прогиб в спине\n" +
            "• Слишком тяжелый вес\n" +
            "• Рывковые движения");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Следите за техникой\n" +
            "• Держите корпус прямо");

        binding.textViewContraindications.setText(
            "• Травмы плечевых суставов\n" +
            "• Проблемы с локтями\n" +
            "• Боли в шее\n" +
            "• Ограниченная подвижность плеч");

        binding.textViewAlternatives.setText(
            "• Французский жим лежа\n" +
            "• Разгибания в блоке\n" +
            "• Разгибания с гантелями\n" +
            "• Жим узким хватом");
    }

    private void setupCableFrenchPress() {
        binding.textViewExerciseTitle.setText("Французский жим в блоке");
        binding.imageViewExercise.setImageResource(R.drawable.arms_cable_french_press);
        
        binding.textViewExerciseDescription.setText(
            "Вариация французского жима с постоянным сопротивлением");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (все головки)\n" +
            "• Локтевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Встаньте спиной к верхнему блоку\n\n" +
            "2. Возьмите рукоять прямым хватом\n\n" +
            "3. Согните руки за голову\n\n" +
            "4. Разогните руки вверх\n\n" +
            "5. Медленно вернитесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Движение локтей\n" +
            "• Наклон корпуса\n" +
            "• Неполная амплитуда\n" +
            "• Использование инерции");

        binding.textViewRecommendations.setText(
            "• Подберите подходящий вес\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Держите постоянное напряжение\n" +
            "• Выполняйте движение плавно");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с плечами\n" +
            "• Боли в шее\n" +
            "• Нестабильность плечевого пояса");
//hfghjg
        binding.textViewAlternatives.setText(
            "• Французский жим со штангой\n" +
            "• Разгибания в блоке\n" +
            "• Разгибания с канатом\n" +
            "• Жим узким хватом");
    }

    private void setupInclineFrenchPress() {
        binding.textViewExerciseTitle.setText("Французский жим на наклонной скамье");
        binding.imageViewExercise.setImageResource(R.drawable.arms_incline_french_press);
        
        binding.textViewExerciseDescription.setText(
            "Вариация французского жима под другим углом для разнообразия нагрузки");

        binding.textViewTargetMuscles.setText(
            "• Трицепс (все головки)\n" +
            "• Локтевая мышца\n" +
            "• Мышцы предплечья");

        binding.textViewTechnique.setText(
            "1. Лягте на наклонную скамью\n\n" +
            "2. Возьмите штангу/гантели прямым хватом\n\n" +
            "3. Опустите вес за голову\n\n" +
            "4. Разогните руки\n\n" +
            "5. Плавно вернитесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Отведение локтей\n" +
            "• Подъем головы\n" +
            "• Неполная амплитуда\n" +
            "• Слишком большой вес");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Держите локти стабильно\n" +
            "• Контролируйте движение");

        binding.textViewContraindications.setText(
            "• Травмы локтевых суставов\n" +
            "• Проблемы с плечами\n" +
            "• Боли в шее\n" +
            "• Нестабильность плечевого пояса");

        binding.textViewAlternatives.setText(
            "• Французский жим лежа\n" +
            "• Разгибания в блоке\n" +
            "• Разгибания с гантелями\n" +
            "• Жим узким хватом");
    }
} 