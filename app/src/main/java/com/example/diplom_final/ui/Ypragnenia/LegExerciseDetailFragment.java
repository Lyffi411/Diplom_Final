package com.example.diplom_final.ui.Ypragnenia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.diplom_final.R;
import com.example.diplom_final.databinding.FragmentLegExerciseDetailBinding;

public class LegExerciseDetailFragment extends Fragment {
    private FragmentLegExerciseDetailBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLegExerciseDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        String title = requireArguments().getString("title");
        String description = requireArguments().getString("description");
        int imageResourceId = requireArguments().getInt("imageResourceId");

        binding.textViewExerciseTitle.setText(title);
        binding.textViewExerciseDescription.setText(description);
        binding.imageViewExercise.setImageResource(imageResourceId);

        setExerciseDetails(title);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setExerciseDetails(String title) {
        switch (title) {
            case "Выпады вперед с отягощением":
                setupForwardLungesWithWeight();
                break;
            case "Выпады вперед с отягощением и приставлением ноги":
                setupForwardLungesWithWeightAndReturn();
                break;
            case "Жим ногами (узкая постановка ног)":
                setupLegPressNarrow();
                break;
            case "Жим ногами (широкая постановка ног)":
                setupLegPressWide();
                break;
            case "Зашагивания на тумбу с гантелями":
                setupStepUpsWithDumbbells();
                break;
            case "Обратные гакк-приседания":
                setupReverseHackSquats();
                break;
            case "Подъем на носки в тренажере сидя":
                setupSeatedCalfRaise();
                break;
            case "Подъем на носки в тренажере стоя":
                setupStandingCalfRaiseMachine();
                break;
            case "Подъем на носки стоя со штангой":
                setupStandingCalfRaiseBarbell();
                break;
            case "Приседания в Гакк-машине":
                setupHackSquat();
                break;
            case "Приседания в Гакк-машине с узкой постановкой ног":
                setupHackSquatNarrow();
                break;
            case "Приседания в Гакк-машине с широкой постановкой ног":
                setupHackSquatWide();
                break;
            case "Приседания плие с гантелью на груди":
                setupSumoSquatDumbbell();
                break;
            case "Приседания с гантелями":
                setupDumbbellSquats();
                break;
            case "Приседания с резинкой":
                setupBandSquats();
                break;
            case "Приседания со штангой":
                setupBarbellSquats();
                break;
            case "Приседания со штангой на груди":
                setupFrontSquats();
                break;
            case "Приседания со штангой на скамью":
                setupBoxSquats();
                break;
            case "Разгибание ног сидя":
                setupLegExtension();
                break;
            case "Сведение ног сидя":
                setupSeatedLegAdduction();
                break;
            case "Сгибание лежа по одной ноге":
                setupSingleLegCurl();
                break;
            case "Сгибание ног лежа":
                setupLyingLegCurl();
                break;
            case "Сгибание ног сидя":
                setupSeatedLegCurl();
                break;
            case "Сгибание ноги стоя в блоке":
                setupStandingLegCurl();
                break;
            case "Становая тяга":
                setupDeadlift();
                break;
            case "Становая тяга 'сумо'":
                setupSumoDeadlift();
                break;
        }
    }

    private void setupForwardLungesWithWeight() {
        binding.textViewExerciseTitle.setText("Выпады вперед с отягощением");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для развития квадрицепсов, ягодиц и улучшения баланса");

        binding.textViewTargetMuscles.setText(
            "• Квадрицепсы\n" +
            "• Ягодичные мышцы\n" +
            "• Бицепс бедра\n" +
            "• Икроножные мышцы");

        binding.textViewTechnique.setText(
            "1. Возьмите гантели в руки\n\n" +
            "2. Встаньте прямо, ноги на ширине плеч\n\n" +
            "3. Сделайте шаг вперед одной ногой\n\n" +
            "4. Опуститесь, сгибая оба колена\n\n" +
            "5. Вернитесь в исходное положение\n\n" +
            "6. Повторите другой ногой");

        binding.textViewCommonMistakes.setText(
            "• Наклон корпуса вперед\n" +
            "• Недостаточная глубина выпада\n" +
            "• Колено передней ноги заходит за носок\n" +
            "• Потеря баланса");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Держите спину прямо\n" +
            "• Следите за техникой");

        binding.textViewContraindications.setText(
            "• Травмы коленных суставов\n" +
            "• Проблемы с равновесием\n" +
            "• Травмы голеностопа\n" +
            "• Боли в спине");

        binding.textViewAlternatives.setText(
            "• Выпады на месте\n" +
            "• Приседания\n" +
            "• Выпады назад\n" +
            "• Зашагивания на платформу");
    }

    private void setupForwardLungesWithWeightAndReturn() {
        binding.textViewExerciseTitle.setText("Выпады вперед с отягощением и приставлением ноги");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Вариация выпадов для лучшей проработки мышц ног");

        binding.textViewTargetMuscles.setText(
            "• Квадрицепсы\n" +
            "• Ягодичные мышцы\n" +
            "• Бицепс бедра\n" +
            "• Икроножные мышцы\n" +
            "• Мышцы-стабилизаторы");

        binding.textViewTechnique.setText(
            "1. Возьмите гантели в руки\n\n" +
            "2. Встаньте прямо, ноги вместе\n\n" +
            "3. Сделайте шаг вперед одной ногой\n\n" +
            "4. Опуститесь в выпад\n\n" +
            "5. Вернитесь в исходное положение, приставляя ногу\n\n" +
            "6. Повторите другой ногой");

        binding.textViewCommonMistakes.setText(
            "• Наклон корпуса вперед\n" +
            "• Неполное приставление ноги\n" +
            "• Потеря равновесия\n" +
            "• Недостаточная глубина выпада");

        binding.textViewRecommendations.setText(
            "• Начинайте с малого веса\n" +
            "• 3 подхода по 12-15 повторений\n" +
            "• Контролируйте движение\n" +
            "• Держите корпус прямо");

        binding.textViewContraindications.setText(
            "• Травмы коленей\n" +
            "• Проблемы с равновесием\n" +
            "• Травмы голеностопа\n" +
            "• Боли в пояснице");

        binding.textViewAlternatives.setText(
            "• Обычные выпады\n" +
            "• Выпады назад\n" +
            "• Приседания\n" +
            "• Зашагивания на платформу");
    }

    private void setupLegPressNarrow() {
        binding.textViewExerciseTitle.setText("Жим ногами (узкая постановка ног)");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Акцент на внешнюю часть квадрицепса");

        binding.textViewTargetMuscles.setText(
            "• Внешняя часть квадрицепса\n" +
            "• Прямая мышца бедра\n" +
            "• Икроножные мышцы\n" +
            "• Ягодичные мышцы");

        binding.textViewTechnique.setText(
            "1. Сядьте в тренажер\n\n" +
            "2. Поставьте ноги узко на платформе\n\n" +
            "3. Снимите платформу с фиксаторов\n\n" +
            "4. Опустите вес, сгибая колени\n\n" +
            "5. Выжмите вес обратно\n\n" +
            "6. Не выпрямляйте колени полностью");

        binding.textViewCommonMistakes.setText(
            "• Полное выпрямление коленей\n" +
            "• Отрыв поясницы от сиденья\n" +
            "• Слишком быстрое выполнение\n" +
            "• Неправильная глубина движения");

        binding.textViewRecommendations.setText(
            "• Начинайте с умеренного веса\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Следите за положением спины\n" +
            "• Выполняйте движения плавно");

        binding.textViewContraindications.setText(
            "• Травмы коленей\n" +
            "• Проблемы с поясницей\n" +
            "• Варикоз\n" +
            "• Высокое давление");

        binding.textViewAlternatives.setText(
            "• Приседания\n" +
            "• Жим ногами широкой постановкой\n" +
            "• Гакк-приседания\n" +
            "• Разгибания ног в тренажере");
    }

    private void setupLegPressWide() {
        binding.textViewExerciseTitle.setText("Жим ногами (широкая постановка ног)");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Акцент на внутреннюю часть бедра и ягодицы");

        binding.textViewTargetMuscles.setText(
            "• Внутренняя часть бедра\n" +
            "• Ягодичные мышцы\n" +
            "• Квадрицепсы\n" +
            "• Приводящие мышцы");

        binding.textViewTechnique.setText(
            "1. Сядьте в тренажер\n\n" +
            "2. Поставьте ноги широко на платформе\n\n" +
            "3. Разверните носки наружу\n\n" +
            "4. Опустите вес, сгибая колени\n\n" +
            "5. Выжмите вес обратно\n\n" +
            "6. Контролируйте движение");

        binding.textViewCommonMistakes.setText(
            "• Сведение коленей внутрь\n" +
            "• Отрыв таза от сиденья\n" +
            "• Чрезмерная нагрузка на колени\n" +
            "• Неконтролируемые движения");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Следите за положением коленей\n" +
            "• Держите спину прижатой");

        binding.textViewContraindications.setText(
            "• Травмы коленных суставов\n" +
            "• Проблемы с тазобедренными суставами\n" +
            "• Варикоз\n" +
            "• Грыжи позвоночника");

        binding.textViewAlternatives.setText(
            "• Приседания плие\n" +
            "• Жим ногами узкой постановкой\n" +
            "• Приседания с широкой постановкой\n" +
            "• Приседания в гакк-машине");
    }

    private void setupStepUpsWithDumbbells() {
        binding.textViewExerciseTitle.setText("Зашагивания на тумбу с гантелями");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Упражнение для развития силы ног и координации");

        binding.textViewTargetMuscles.setText(
            "• Квадрицепсы\n" +
            "• Ягодичные мышцы\n" +
            "• Икроножные мышцы\n" +
            "• Мышцы-стабилизаторы");

        binding.textViewTechnique.setText(
            "1. Возьмите гантели в руки\n\n" +
            "2. Встаньте перед тумбой\n\n" +
            "3. Поставьте одну ногу на тумбу\n\n" +
            "4. Поднимитесь на тумбу\n\n" +
            "5. Опуститесь контролируемо\n\n" +
            "6. Чередуйте ноги");

        binding.textViewCommonMistakes.setText(
            "• Наклон корпуса вперед\n" +
            "• Толчок второй ногой\n" +
            "• Потеря равновесия\n" +
            "• Неконтролируемое опускание");

        binding.textViewRecommendations.setText(
            "• Начинайте с низкой тумбы\n" +
            "• 3 подхода по 12-15 повторений\n" +
            "• Держите спину прямо\n" +
            "• Увеличивайте вес постепенно");

        binding.textViewContraindications.setText(
            "• Проблемы с коленями\n" +
            "• Нарушения равновесия\n" +
            "• Травмы голеностопа\n" +
            "• Сильные боли в суставах");

        binding.textViewAlternatives.setText(
            "• Выпады\n" +
            "• Приседания\n" +
            "• Ходьба по ступенькам\n" +
            "• Выпады с подъемом колена");
    }

    private void setupReverseHackSquats() {
        binding.textViewExerciseTitle.setText("Обратные гакк-приседания");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Изолированная работа на квадрицепсы");

        binding.textViewTargetMuscles.setText(
            "• Квадрицепсы\n" +
            "• Ягодичные мышцы\n" +
            "• Приводящие мышцы\n" +
            "• Икроножные мышцы");

        binding.textViewTechnique.setText(
            "1. Встаньте в тренажер лицом к упору\n\n" +
            "2. Расположите плечи под подушками\n\n" +
            "3. Поставьте ноги на платформу\n\n" +
            "4. Опуститесь, сгибая колени\n\n" +
            "5. Поднимитесь, выпрямляя ноги\n\n" +
            "6. Не блокируйте колени");

        binding.textViewCommonMistakes.setText(
            "• Неполная амплитуда\n" +
            "• Сведение коленей\n" +
            "• Отрыв пяток\n" +
            "• Блокировка коленей");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Следите за положением спины\n" +
            "• Выполняйте движения плавно");

        binding.textViewContraindications.setText(
            "• Травмы коленей\n" +
            "• Проблемы с позвоночником\n" +
            "• Варикоз\n" +
            "• Высокое давление");

        binding.textViewAlternatives.setText(
            "• Приседания\n" +
            "• Жим ногами\n" +
            "• Разгибания ног\n" +
            "• Приседания в смит-машине");
    }

    private void setupSeatedCalfRaise() {
        binding.textViewExerciseTitle.setText("Подъем на носки в тренажере сидя");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Изолированная работа на камбаловидные мышцы");

        binding.textViewTargetMuscles.setText(
            "• Камбаловидные мышцы\n" +
            "• Икроножные мышцы\n" +
            "• Задняя большеберцовая мышца");

        binding.textViewTechnique.setText(
            "1. Сядьте в тренажер\n\n" +
            "2. Поставьте носки на платформу\n\n" +
            "3. Опустите пятки максимально вниз\n\n" +
            "4. Поднимитесь на носки\n\n" +
            "5. Задержитесь в верхней точке\n\n" +
            "6. Медленно опуститесь");

        binding.textViewCommonMistakes.setText(
            "• Неполная амплитуда\n" +
            "• Слишком быстрый темп\n" +
            "• Раскачивания\n" +
            "• Неправильная постановка стоп");

        binding.textViewRecommendations.setText(
            "• Начинайте с малого веса\n" +
            "• 3-4 подхода по 15-20 повторений\n" +
            "• Выполняйте в медленном темпе\n" +
            "• Делайте паузу вверху");

        binding.textViewContraindications.setText(
            "• Травмы голеностопа\n" +
            "• Варикоз\n" +
            "• Тендинит ахилла\n" +
            "• Острые боли в стопах");

        binding.textViewAlternatives.setText(
            "• Подъемы на носки стоя\n" +
            "• Подъемы на платформе\n" +
            "• Подъемы на одной ноге\n" +
            "• Ходьба на носках");
    }

    private void setupStandingCalfRaiseMachine() {
        binding.textViewExerciseTitle.setText("Подъем на носки в тренажере стоя");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Упражнение для развития икроножных мышц");

        binding.textViewTargetMuscles.setText(
            "• Икроножные мышцы\n" +
            "• Камбаловидные мышцы\n" +
            "• Мышцы-стабилизаторы голени");

        binding.textViewTechnique.setText(
            "1. Встаньте в тренажер\n\n" +
            "2. Расположите плечи под подушками\n\n" +
            "3. Поставьте носки на платформу\n\n" +
            "4. Опустите пятки ниже уровня платформы\n\n" +
            "5. Поднимитесь на носки\n\n" +
            "6. Медленно опуститесь");

        binding.textViewCommonMistakes.setText(
            "• Сгибание в коленях\n" +
            "• Неполное опускание пяток\n" +
            "• Быстрое выполнение\n" +
            "• Слишком большой вес");

        binding.textViewRecommendations.setText(
            "• Начинайте с умеренного веса\n" +
            "• 3-4 подхода по 15-20 повторений\n" +
            "• Держите колени прямыми\n" +
            "• Контролируйте движение");

        binding.textViewContraindications.setText(
            "• Травмы голеностопа\n" +
            "• Варикозное расширение вен\n" +
            "• Проблемы с ахилловым сухожилием\n" +
            "• Нестабильность суставов");

        binding.textViewAlternatives.setText(
            "• Подъемы на носки сидя\n" +
            "• Подъемы со штангой\n" +
            "• Подъемы на платформе\n" +
            "• Подъемы на одной ноге");
    }

    private void setupStandingCalfRaiseBarbell() {
        binding.textViewExerciseTitle.setText("Подъем на носки стоя со штангой");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для икроножных мышц");

        binding.textViewTargetMuscles.setText(
            "• Икроножные мышцы\n" +
            "• Камбаловидные мышцы\n" +
            "• Мышцы-стабилизаторы\n" +
            "• Мышцы стопы");

        binding.textViewTechnique.setText(
            "1. Встаньте на платформу\n\n" +
            "2. Расположите штангу на плечах\n\n" +
            "3. Опустите пятки ниже уровня платформы\n\n" +
            "4. Поднимитесь на носки\n\n" +
            "5. Задержитесь в верхней точке\n\n" +
            "6. Медленно опуститесь");

        binding.textViewCommonMistakes.setText(
            "• Сгибание коленей\n" +
            "• Неполная амплитуда\n" +
            "• Потеря равновесия\n" +
            "• Раскачивания со штангой");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 15-20 повторений\n" +
            "• Держите спину прямо\n" +
            "• Выполняйте плавно");

        binding.textViewContraindications.setText(
            "• Травмы голеностопа\n" +
            "• Проблемы с равновесием\n" +
            "• Варикоз\n" +
            "• Травмы спины");

        binding.textViewAlternatives.setText(
            "• Подъемы в тренажере\n" +
            "• Подъемы с гантелями\n" +
            "• Подъемы на одной ноге\n" +
            "• Подъемы в смит-машине");
    }

    private void setupHackSquat() {
        binding.textViewExerciseTitle.setText("Приседания в Гакк-машине");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Безопасная альтернатива приседаниям со штангой");

        binding.textViewTargetMuscles.setText(
            "• Квадрицепсы\n" +
            "• Ягодичные мышцы\n" +
            "• Бицепс бедра\n" +
            "• Приводящие мышцы");

        binding.textViewTechnique.setText(
            "1. Установите спинку под углом\n\n" +
            "2. Поставьте ноги на платформу\n\n" +
            "3. Снимите платформу с фиксаторов\n\n" +
            "4. Опуститесь до параллели с полом\n\n" +
            "5. Поднимитесь, выпрямляя ноги\n\n" +
            "6. Не блокируйте колени");

        binding.textViewCommonMistakes.setText(
            "• Отрыв пяток от платформы\n" +
            "• Сведение коленей\n" +
            "• Неполная амплитуда\n" +
            "• Слишком быстрый темп");

        binding.textViewRecommendations.setText(
            "• Начинайте с малого веса\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Следите за техникой\n" +
            "• Дышите равномерно");

        binding.textViewContraindications.setText(
            "• Травмы коленей\n" +
            "• Проблемы с позвоночником\n" +
            "• Варикоз\n" +
            "• Высокое давление");

        binding.textViewAlternatives.setText(
            "• Приседания со штангой\n" +
            "• Жим ногами\n" +
            "• Приседания в смит-машине\n" +
            "• Приседания с гантелями");
    }

    private void setupHackSquatNarrow() {
        binding.textViewExerciseTitle.setText("Приседания в Гакк-машине с узкой постановкой ног");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Акцент на внешнюю часть квадрицепса");

        binding.textViewTargetMuscles.setText(
            "• Внешняя часть квадрицепса\n" +
            "• Прямая мышца бедра\n" +
            "• Ягодичные мышцы\n" +
            "• Икроножные мышцы");

        binding.textViewTechnique.setText(
            "1. Установите спинку под углом\n\n" +
            "2. Поставьте ноги узко на платформе\n\n" +
            "3. Носки слегка наружу\n\n" +
            "4. Опуститесь до параллели\n\n" +
            "5. Поднимитесь, выпрямляя ноги\n\n" +
            "6. Контролируйте движение");

        binding.textViewCommonMistakes.setText(
            "• Сведение коленей\n" +
            "• Отрыв пяток\n" +
            "• Неполная амплитуда\n" +
            "• Слишком большой вес");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Следите за коленями\n" +
            "• Выполняйте плавно");

        binding.textViewContraindications.setText(
            "• Травмы коленей\n" +
            "• Проблемы с позвоночником\n" +
            "• Варикоз\n" +
            "• Высокое давление");

        binding.textViewAlternatives.setText(
            "• Обычные приседания в гакк-машине\n" +
            "• Жим ногами узкой постановкой\n" +
            "• Приседания со штангой\n" +
            "• Разгибания ног");
    }

    private void setupHackSquatWide() {
        binding.textViewExerciseTitle.setText("Приседания в Гакк-машине с широкой постановкой ног");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Акцент на внутреннюю часть бедра");

        binding.textViewTargetMuscles.setText(
            "• Внутренняя часть бедра\n" +
            "• Ягодичные мышцы\n" +
            "• Приводящие мышцы\n" +
            "• Квадрицепсы");

        binding.textViewTechnique.setText(
            "1. Установите спинку под углом\n\n" +
            "2. Поставьте ноги широко на платформе\n\n" +
            "3. Разверните носки наружу\n\n" +
            "4. Опуститесь до параллели\n\n" +
            "5. Поднимитесь, выпрямляя ноги\n\n" +
            "6. Следите за коленями");

        binding.textViewCommonMistakes.setText(
            "• Заваливание коленей внутрь\n" +
            "• Отрыв пяток\n" +
            "• Неполная амплитуда\n" +
            "• Потеря баланса");

        binding.textViewRecommendations.setText(
            "• Начинайте с малого веса\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Держите колени над носками\n" +
            "• Контролируйте движение");

        binding.textViewContraindications.setText(
            "• Травмы коленей\n" +
            "• Проблемы с тазобедренными суставами\n" +
            "• Варикоз\n" +
            "• Грыжи позвоночника");

        binding.textViewAlternatives.setText(
            "• Приседания плие\n" +
            "• Жим ногами широкой постановкой\n" +
            "• Приседания сумо\n" +
            "• Обычные приседания в гакк-машине");
    }

    private void setupSumoSquatDumbbell() {
        binding.textViewExerciseTitle.setText("Приседания плие с гантелью на груди");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Упражнение для внутренней поверхности бедра");

        binding.textViewTargetMuscles.setText(
            "• Приводящие мышцы бедра\n" +
            "• Ягодичные мышцы\n" +
            "• Квадрицепсы\n" +
            "• Мышцы-стабилизаторы");

        binding.textViewTechnique.setText(
            "1. Возьмите гантель к груди\n\n" +
            "2. Поставьте ноги широко\n\n" +
            "3. Разверните носки наружу\n\n" +
            "4. Опуститесь в присед\n\n" +
            "5. Держите спину прямо\n\n" +
            "6. Поднимитесь, выпрямляя ноги");

        binding.textViewCommonMistakes.setText(
            "• Сведение коленей внутрь\n" +
            "• Наклон корпуса вперед\n" +
            "• Отрыв пяток\n" +
            "• Недостаточная глубина");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкой гантели\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Следите за положением коленей\n" +
            "• Дышите равномерно");

        binding.textViewContraindications.setText(
            "• Травмы тазобедренных суставов\n" +
            "• Проблемы с коленями\n" +
            "• Боли в паху\n" +
            "• Ограниченная подвижность");

        binding.textViewAlternatives.setText(
            "• Приседания сумо со штангой\n" +
            "• Приседания с резинкой\n" +
            "• Приседания в гакк-машине широкой постановкой\n" +
            "• Приседания у стены");
    }

    private void setupDumbbellSquats() {
        binding.textViewExerciseTitle.setText("Приседания с гантелями");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для всех мышц ног");

        binding.textViewTargetMuscles.setText(
            "• Квадрицепсы\n" +
            "• Ягодичные мышцы\n" +
            "• Бицепс бедра\n" +
            "• Икроножные мышцы\n" +
            "• Мышцы кора");

        binding.textViewTechnique.setText(
            "1. Возьмите гантели в руки\n\n" +
            "2. Поставьте ноги на ширине плеч\n\n" +
            "3. Опуститесь в присед\n\n" +
            "4. Колени над носками\n\n" +
            "5. Держите спину прямо\n\n" +
            "6. Поднимитесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Наклон корпуса вперед\n" +
            "• Сведение коленей\n" +
            "• Отрыв пяток\n" +
            "• Неполная амплитуда");

        binding.textViewRecommendations.setText(
            "• Начинайте с легких гантелей\n" +
            "• 3-4 подхода по 10-12 повторений\n" +
            "• Следите за техникой\n" +
            "• Увеличивайте вес постепенно");

        binding.textViewContraindications.setText(
            "• Травмы коленей\n" +
            "• Проблемы с позвоночником\n" +
            "• Грыжи\n" +
            "• Высокое давление");

        binding.textViewAlternatives.setText(
            "• Приседания со штангой\n" +
            "• Приседания с собственным весом\n" +
            "• Приседания в смит-машине\n" +
            "• Приседания с гирей");
    }

    private void setupBandSquats() {
        binding.textViewExerciseTitle.setText("Приседания с резинкой");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Упражнение для начинающих и разминки");

        binding.textViewTargetMuscles.setText(
            "• Квадрицепсы\n" +
            "• Ягодичные мышцы\n" +
            "• Приводящие мышцы\n" +
            "• Мышцы-стабилизаторы");

        binding.textViewTechnique.setText(
            "1. Встаньте на резинку\n\n" +
            "2. Возьмите концы резинки в руки\n\n" +
            "3. Ноги на ширине плеч\n\n" +
            "4. Опуститесь в присед\n\n" +
            "5. Следите за осанкой\n\n" +
            "6. Вернитесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Наклон корпуса вперед\n" +
            "• Сведение коленей\n" +
            "• Подъем на носки\n" +
            "• Неравномерное натяжение резинки");

        binding.textViewRecommendations.setText(
            "• Выбирайте подходящее сопротивление\n" +
            "• 3 подхода по 15-20 повторений\n" +
            "• Выполняйте плавно\n" +
            "• Следите за дыханием");

        binding.textViewContraindications.setText(
            "• Травмы коленей\n" +
            "• Сильные боли в суставах\n" +
            "• Проблемы с равновесием\n" +
            "• Острые боли в спине");

        binding.textViewAlternatives.setText(
            "• Приседания без веса\n" +
            "• Приседания с гантелями\n" +
            "• Приседания у стены\n" +
            "• Приседания с поддержкой");
    }

    private void setupBarbellSquats() {
        binding.textViewExerciseTitle.setText("Приседания со штангой");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для развития силы ног");

        binding.textViewTargetMuscles.setText(
            "• Квадрицепсы\n" +
            "• Ягодичные мышцы\n" +
            "• Бицепс бедра\n" +
            "• Мышцы спины\n" +
            "• Мышцы кора");

        binding.textViewTechnique.setText(
            "1. Установите штангу на стойки\n\n" +
            "2. Встаньте под штангу\n\n" +
            "3. Снимите штангу со стоек\n\n" +
            "4. Опуститесь в присед\n\n" +
            "5. Следите за техникой\n\n" +
            "6. Поднимитесь, выпрямляя ноги");

        binding.textViewCommonMistakes.setText(
            "• Округление спины\n" +
            "• Подъем на носки\n" +
            "• Сведение коленей\n" +
            "• Недостаточная глубина");

        binding.textViewRecommendations.setText(
            "• Начинайте с пустого грифа\n" +
            "• 3-4 подхода по 8-12 повторений\n" +
            "• Используйте страховку\n" +
            "• Увеличивайте вес постепенно");

        binding.textViewContraindications.setText(
            "• Травмы позвоночника\n" +
            "• Проблемы с коленями\n" +
            "• Грыжи\n" +
            "• Высокое давление");

        binding.textViewAlternatives.setText(
            "• Приседания в смит-машине\n" +
            "• Приседания с гантелями\n" +
            "• Жим ногами\n" +
            "• Приседания в гакк-машине");
    }

    private void setupFrontSquats() {
        binding.textViewExerciseTitle.setText("Приседания со штангой на груди");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Вариация приседаний с акцентом на переднюю часть бедра");

        binding.textViewTargetMuscles.setText(
            "• Квадрицепсы\n" +
            "• Ягодичные мышцы\n" +
            "• Мышцы кора\n" +
            "• Мышцы спины\n" +
            "• Дельтовидные мышцы");

        binding.textViewTechnique.setText(
            "1. Установите штангу на груди\n\n" +
            "2. Локти подняты\n\n" +
            "3. Ноги на ширине плеч\n\n" +
            "4. Опуститесь в присед\n\n" +
            "5. Держите корпус прямо\n\n" +
            "6. Поднимитесь в исходное положение");

        binding.textViewCommonMistakes.setText(
            "• Опускание локтей\n" +
            "• Наклон вперед\n" +
            "• Потеря баланса\n" +
            "• Неправильное положение штанги");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 8-10 повторений\n" +
            "• Работайте над гибкостью\n" +
            "• Следите за положением локтей");

        binding.textViewContraindications.setText(
            "• Травмы плеч\n" +
            "• Проблемы с запястьями\n" +
            "• Травмы коленей\n" +
            "• Ограниченная подвижность");

        binding.textViewAlternatives.setText(
            "• Приседания со штангой на спине\n" +
            "• Приседания с гантелями\n" +
            "• Гоблет приседания\n" +
            "• Приседания в смит-машине");
    }

    private void setupBoxSquats() {
        binding.textViewExerciseTitle.setText("Приседания со штангой на скамью");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Контролируемые приседания для начинающих");

        binding.textViewTargetMuscles.setText(
            "• Квадрицепсы\n" +
            "• Ягодичные мышцы\n" +
            "• Бицепс бедра\n" +
            "• Мышцы кора\n" +
            "• Мышцы спины");

        binding.textViewTechnique.setText(
            "1. Установите скамью позади\n\n" +
            "2. Возьмите штангу на спину\n\n" +
            "3. Опуститесь до касания скамьи\n\n" +
            "4. Слегка коснитесь скамьи\n\n" +
            "5. Поднимитесь вверх\n\n" +
            "6. Сохраняйте контроль движения");

        binding.textViewCommonMistakes.setText(
            "• Падение на скамью\n" +
            "• Смещение коленей внутрь\n" +
            "• Потеря баланса\n" +
            "• Неправильная высота скамьи");

        binding.textViewRecommendations.setText(
            "• Начинайте с пустого грифа\n" +
            "• 3-4 подхода по 8-12 повторений\n" +
            "• Подберите правильную высоту\n" +
            "• Не расслабляйтесь на скамье");

        binding.textViewContraindications.setText(
            "• Травмы спины\n" +
            "• Проблемы с коленями\n" +
            "• Нарушения равновесия\n" +
            "• Грыжи позвоночника");

        binding.textViewAlternatives.setText(
            "• Обычные приседания\n" +
            "• Приседания с гантелями\n" +
            "• Приседания в смит-машине\n" +
            "• Приседания с поддержкой");
    }

    private void setupLegExtension() {
        binding.textViewExerciseTitle.setText("Разгибание ног сидя");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Изолированное упражнение для квадрицепсов");

        binding.textViewTargetMuscles.setText(
            "• Квадрицепсы\n" +
            "• Прямая мышца бедра\n" +
            "• Внутренняя мышца бедра\n" +
            "• Внешняя мышца бедра");

        binding.textViewTechnique.setText(
            "1. Сядьте в тренажер\n\n" +
            "2. Отрегулируйте валик\n\n" +
            "3. Возьмитесь за рукоятки\n\n" +
            "4. Выпрямите ноги\n\n" +
            "5. Задержитесь в верхней точке\n\n" +
            "6. Медленно опустите вес");

        binding.textViewCommonMistakes.setText(
            "• Рывковые движения\n" +
            "• Неполная амплитуда\n" +
            "• Отрыв таза от сиденья\n" +
            "• Слишком большой вес");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Выполняйте плавно\n" +
            "• Фокусируйтесь на сокращении");

        binding.textViewContraindications.setText(
            "• Травмы коленей\n" +
            "• Артрит коленного сустава\n" +
            "• Недавние операции\n" +
            "• Острые боли в суставах");

        binding.textViewAlternatives.setText(
            "• Приседания\n" +
            "• Выпады\n" +
            "• Жим ногами\n" +
            "• Приседания в гакк-машине");
    }

    private void setupSeatedLegAdduction() {
        binding.textViewExerciseTitle.setText("Сведение ног сидя");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Упражнение для приводящих мышц бедра");

        binding.textViewTargetMuscles.setText(
            "• Приводящие мышцы бедра\n" +
            "• Внутренняя поверхность бедра\n" +
            "• Тазовые мышцы\n" +
            "• Мышцы-стабилизаторы");

        binding.textViewTechnique.setText(
            "1. Сядьте в тренажер\n\n" +
            "2. Разведите ноги\n\n" +
            "3. Возьмитесь за рукоятки\n\n" +
            "4. Сведите ноги вместе\n\n" +
            "5. Задержитесь в конечной точке\n\n" +
            "6. Медленно вернитесь");

        binding.textViewCommonMistakes.setText(
            "• Рывковые движения\n" +
            "• Неполное сведение\n" +
            "• Отрыв таза\n" +
            "• Задержка дыхания");

        binding.textViewRecommendations.setText(
            "• Используйте умеренный вес\n" +
            "• 3 подхода по 15-20 повторений\n" +
            "• Выполняйте плавно\n" +
            "• Следите за положением таза");

        binding.textViewContraindications.setText(
            "• Травмы паховой области\n" +
            "• Воспаление суставов\n" +
            "• Растяжения приводящих мышц\n" +
            "• Недавние операции");

        binding.textViewAlternatives.setText(
            "• Приседания плие\n" +
            "• Приседания с резинкой\n" +
            "• Упражнения с мячом\n" +
            "• Боковые выпады");
    }

    private void setupSingleLegCurl() {
        binding.textViewExerciseTitle.setText("Сгибание лежа по одной ноге");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Изолированная работа на бицепс бедра");

        binding.textViewTargetMuscles.setText(
            "• Бицепс бедра\n" +
            "• Икроножные мышцы\n" +
            "• Подколенные сухожилия\n" +
            "• Ягодичные мышцы");

        binding.textViewTechnique.setText(
            "1. Лягте на тренажер\n\n" +
            "2. Зафиксируйте одну ногу\n\n" +
            "3. Согните ногу в колене\n\n" +
            "4. Поднимите вес\n\n" +
            "5. Задержитесь наверху\n\n" +
            "6. Медленно опустите");

        binding.textViewCommonMistakes.setText(
            "• Рывковые движения\n" +
            "• Подъем таза\n" +
            "• Неполная амплитуда\n" +
            "• Слишком большой вес");

        binding.textViewRecommendations.setText(
            "• Начинайте с малого веса\n" +
            "• 3 подхода по 12-15 повторений\n" +
            "• Выполняйте плавно\n" +
            "• Чередуйте ноги");

        binding.textViewContraindications.setText(
            "• Травмы колена\n" +
            "• Растяжение подколенных сухожилий\n" +
            "• Боли в пояснице\n" +
            "• Острые боли в суставах");

        binding.textViewAlternatives.setText(
            "• Сгибание ног лежа\n" +
            "• Сгибание ног стоя\n" +
            "• Румынская становая тяга\n" +
            "• Мостик");
    }

    private void setupLyingLegCurl() {
        binding.textViewExerciseTitle.setText("Сгибание ног лежа");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для бицепса бедра");

        binding.textViewTargetMuscles.setText(
            "• Бицепс бедра\n" +
            "• Икроножные мышцы\n" +
            "• Подколенные сухожилия\n" +
            "• Ягодичные мышцы");

        binding.textViewTechnique.setText(
            "1. Лягте на тренажер\n\n" +
            "2. Зафиксируйте ноги\n\n" +
            "3. Согните ноги в коленях\n\n" +
            "4. Поднимите вес\n\n" +
            "5. Задержитесь в верхней точке\n\n" +
            "6. Медленно опустите");

        binding.textViewCommonMistakes.setText(
            "• Рывковые движения\n" +
            "• Отрыв бедер от скамьи\n" +
            "• Неполная амплитуда\n" +
            "• Работа спиной");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Выполняйте плавно\n" +
            "• Контролируйте движение");

        binding.textViewContraindications.setText(
            "• Травмы коленей\n" +
            "• Проблемы с поясницей\n" +
            "• Растяжения мышц\n" +
            "• Острые боли в суставах");

        binding.textViewAlternatives.setText(
            "• Сгибание одной ногой\n" +
            "• Сгибание ног стоя\n" +
            "• Сгибание ног сидя\n" +
            "• Гиперэкстензия");
    }

    private void setupSeatedLegCurl() {
        binding.textViewExerciseTitle.setText("Сгибание ног сидя");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Альтернативное упражнение для бицепса бедра");

        binding.textViewTargetMuscles.setText(
            "• Бицепс бедра\n" +
            "• Подколенные сухожилия\n" +
            "• Икроножные мышцы\n" +
            "• Мышцы-стабилизаторы");

        binding.textViewTechnique.setText(
            "1. Сядьте в тренажер\n\n" +
            "2. Отрегулируйте валик\n\n" +
            "3. Зафиксируйте ноги\n\n" +
            "4. Согните ноги в коленях\n\n" +
            "5. Задержитесь в верхней точке\n\n" +
            "6. Медленно вернитесь");

        binding.textViewCommonMistakes.setText(
            "• Рывковые движения\n" +
            "• Отрыв от сиденья\n" +
            "• Неполная амплитуда\n" +
            "• Слишком большой вес");

        binding.textViewRecommendations.setText(
            "• Начинайте с малого веса\n" +
            "• 3-4 подхода по 12-15 повторений\n" +
            "• Выполняйте плавно\n" +
            "• Следите за техникой");

        binding.textViewContraindications.setText(
            "• Травмы коленей\n" +
            "• Проблемы с поясницей\n" +
            "• Растяжения мышц\n" +
            "• Варикоз");

        binding.textViewAlternatives.setText(
            "• Сгибание ног лежа\n" +
            "• Сгибание одной ногой\n" +
            "• Сгибание ног стоя\n" +
            "• Румынская становая тяга");
    }

    private void setupStandingLegCurl() {
        binding.textViewExerciseTitle.setText("Сгибание ноги стоя в блоке");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Изолированное упражнение для бицепса бедра");

        binding.textViewTargetMuscles.setText(
            "• Бицепс бедра\n" +
            "• Подколенные сухожилия\n" +
            "• Икроножные мышцы\n" +
            "• Мышцы-стабилизаторы");

        binding.textViewTechnique.setText(
            "1. Встаньте у тренажера\n\n" +
            "2. Прикрепите манжету\n\n" +
            "3. Держитесь за опору\n\n" +
            "4. Согните ногу\n\n" +
            "5. Задержитесь наверху\n\n" +
            "6. Медленно опустите");

        binding.textViewCommonMistakes.setText(
            "• Раскачивания корпуса\n" +
            "• Неполная амплитуда\n" +
            "• Рывковые движения\n" +
            "• Потеря равновесия");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3 подхода по 12-15 повторений\n" +
            "• Держитесь за опору\n" +
            "• Чередуйте ноги");

        binding.textViewContraindications.setText(
            "• Травмы колена\n" +
            "• Проблемы с равновесием\n" +
            "• Растяжения мышц\n" +
            "• Боли в суставах");

        binding.textViewAlternatives.setText(
            "• Сгибание ног лежа\n" +
            "• Сгибание ног сидя\n" +
            "• Сгибание одной ногой\n" +
            "• Румынская становая тяга");
    }

    private void setupDeadlift() {
        binding.textViewExerciseTitle.setText("Становая тяга");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для всего тела");

        binding.textViewTargetMuscles.setText(
            "• Бицепс бедра\n" +
            "• Ягодичные мышцы\n" +
            "• Мышцы спины\n" +
            "• Квадрицепсы\n" +
            "• Мышцы кора");

        binding.textViewTechnique.setText(
            "1. Подойдите к штанге\n\n" +
            "2. Ноги на ширине плеч\n\n" +
            "3. Возьмитесь за гриф\n\n" +
            "4. Выпрямите спину\n\n" +
            "5. Поднимите штангу\n\n" +
            "6. Опустите контролируемо");

        binding.textViewCommonMistakes.setText(
            "• Округление спины\n" +
            "• Подъем плечами\n" +
            "• Неправильная траектория\n" +
            "• Рывковые движения");

        binding.textViewRecommendations.setText(
            "• Начинайте с легкого веса\n" +
            "• 3-4 подхода по 8-12 повторений\n" +
            "• Разминайтесь перед подходами\n" +
            "• Следите за техникой");

        binding.textViewContraindications.setText(
            "• Травмы спины\n" +
            "• Грыжи позвоночника\n" +
            "• Высокое давление\n" +
            "• Острые боли в суставах");

        binding.textViewAlternatives.setText(
            "• Румынская становая тяга\n" +
            "• Становая на прямых ногах\n" +
            "• Гиперэкстензия\n" +
            "• Тяга гантелей");
    }

    private void setupSumoDeadlift() {
        binding.textViewExerciseTitle.setText("Становая тяга 'сумо'");
        binding.imageViewExercise.setImageResource(R.drawable.back_dumbbell_shrugs);
        
        binding.textViewExerciseDescription.setText(
            "Вариация становой тяги с широкой постановкой ног");

        binding.textViewTargetMuscles.setText(
            "• Приводящие мышцы\n" +
            "• Квадрицепсы\n" +
            "• Ягодичные мышцы\n" +
            "• Мышцы спины\n" +
            "• Бицепс бедра");

        binding.textViewTechnique.setText(
            "1. Встаньте широко\n\n" +
            "2. Носки развернуты наружу\n\n" +
            "3. Возьмитесь за гриф\n\n" +
            "4. Держите спину прямо\n\n" +
            "5. Поднимите штангу\n\n" +
            "6. Опустите контролируемо");

        binding.textViewCommonMistakes.setText(
            "• Слишком узкая стойка\n" +
            "• Округление спины\n" +
            "• Колени внутрь\n" +
            "• Подъем таза раньше времени");

        binding.textViewRecommendations.setText(
            "• Начинайте с техники\n" +
            "• 3-4 подхода по 8-12 повторений\n" +
            "• Следите за коленями\n" +
            "• Держите грудь поднятой");

        binding.textViewContraindications.setText(
            "• Травмы тазобедренных суставов\n" +
            "• Проблемы с позвоночником\n" +
            "• Грыжи\n" +
            "• Варикоз");

        binding.textViewAlternatives.setText(
            "• Классическая становая тяга\n" +
            "• Приседания сумо\n" +
            "• Тяга гантелей\n" +
            "• Гиперэкстензия");
    }
} 