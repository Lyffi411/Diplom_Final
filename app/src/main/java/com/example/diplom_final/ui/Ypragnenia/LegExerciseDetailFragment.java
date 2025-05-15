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
        binding.imageViewExercise.setImageResource(R.drawable.leg_lunges_weight);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для развития квадрицепсов, ягодиц и улучшения баланса");



        binding.textViewTechnique.setText(
            "1. Возьмите гантели в руки\n\n" +
            "2. Встаньте прямо, ноги на ширине плеч\n\n" +
            "3. Сделайте шаг вперед одной ногой\n\n" +
            "4. Опуститесь, сгибая оба колена\n\n" +
            "5. Вернитесь в исходное положение\n\n" +
            "6. Повторите другой ногой");




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
        binding.imageViewExercise.setImageResource(R.drawable.leg_lunges_weight_return);
        
        binding.textViewExerciseDescription.setText(
            "Вариация выпадов для лучшей проработки мышц ног");



        binding.textViewTechnique.setText(
            "1. Возьмите гантели в руки\n\n" +
            "2. Встаньте прямо, ноги вместе\n\n" +
            "3. Сделайте шаг вперед одной ногой\n\n" +
            "4. Опуститесь в выпад\n\n" +
            "5. Вернитесь в исходное положение, приставляя ногу\n\n" +
            "6. Повторите другой ногой");





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
        binding.imageViewExercise.setImageResource(R.drawable.leg_press_narrow);
        
        binding.textViewExerciseDescription.setText(
            "Акцент на внешнюю часть квадрицепса");



        binding.textViewTechnique.setText(
            "1. Сядьте в тренажер\n\n" +
            "2. Поставьте ноги узко на платформе\n\n" +
            "3. Снимите платформу с фиксаторов\n\n" +
            "4. Опустите вес, сгибая колени\n\n" +
            "5. Выжмите вес обратно\n\n" +
            "6. Не выпрямляйте колени полностью");



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
        binding.imageViewExercise.setImageResource(R.drawable.leg_press_wide);
        
        binding.textViewExerciseDescription.setText(
            "Акцент на внутреннюю часть бедра и ягодицы");



        binding.textViewTechnique.setText(
            "1. Сядьте в тренажер\n\n" +
            "2. Поставьте ноги широко на платформе\n\n" +
            "3. Разверните носки наружу\n\n" +
            "4. Опустите вес, сгибая колени\n\n" +
            "5. Выжмите вес обратно\n\n" +
            "6. Контролируйте движение");

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
        binding.imageViewExercise.setImageResource(R.drawable.step_ups_dumbbells);
        
        binding.textViewExerciseDescription.setText(
            "Упражнение для развития силы ног и координации");



        binding.textViewTechnique.setText(
            "1. Возьмите гантели в руки\n\n" +
            "2. Встаньте перед тумбой\n\n" +
            "3. Поставьте одну ногу на тумбу\n\n" +
            "4. Поднимитесь на тумбу\n\n" +
            "5. Опуститесь контролируемо\n\n" +
            "6. Чередуйте ноги");



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
        binding.imageViewExercise.setImageResource(R.drawable.reverse_hack_squat);
        
        binding.textViewExerciseDescription.setText(
            "Изолированная работа на квадрицепсы");



        binding.textViewTechnique.setText(
            "1. Встаньте в тренажер лицом к упору\n\n" +
            "2. Расположите плечи под подушками\n\n" +
            "3. Поставьте ноги на платформу\n\n" +
            "4. Опуститесь, сгибая колени\n\n" +
            "5. Поднимитесь, выпрямляя ноги\n\n" +
            "6. Не блокируйте колени");




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
        binding.imageViewExercise.setImageResource(R.drawable.seated_calf_raise);
        
        binding.textViewExerciseDescription.setText(
            "Изолированная работа на камбаловидные мышцы");



        binding.textViewTechnique.setText(
            "1. Сядьте в тренажер\n\n" +
            "2. Поставьте носки на платформу\n\n" +
            "3. Опустите пятки максимально вниз\n\n" +
            "4. Поднимитесь на носки\n\n" +
            "5. Задержитесь в верхней точке\n\n" +
            "6. Медленно опуститесь");



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
        binding.imageViewExercise.setImageResource(R.drawable.standing_calf_raise_machine);
        
        binding.textViewExerciseDescription.setText(
            "Упражнение для развития икроножных мышц");



        binding.textViewTechnique.setText(
            "1. Встаньте в тренажер\n\n" +
            "2. Расположите плечи под подушками\n\n" +
            "3. Поставьте носки на платформу\n\n" +
            "4. Опустите пятки ниже уровня платформы\n\n" +
            "5. Поднимитесь на носки\n\n" +
            "6. Медленно опуститесь");



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
        binding.imageViewExercise.setImageResource(R.drawable.standing_calf_raise_barbell);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для икроножных мышц");



        binding.textViewTechnique.setText(
            "1. Встаньте на платформу\n\n" +
            "2. Расположите штангу на плечах\n\n" +
            "3. Опустите пятки ниже уровня платформы\n\n" +
            "4. Поднимитесь на носки\n\n" +
            "5. Задержитесь в верхней точке\n\n" +
            "6. Медленно опуститесь");



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
        binding.imageViewExercise.setImageResource(R.drawable.hack_squat_narrow);
        
        binding.textViewExerciseDescription.setText(
            "Безопасная альтернатива приседаниям со штангой");



        binding.textViewTechnique.setText(
            "1. Установите спинку под углом\n\n" +
            "2. Поставьте ноги на платформу\n\n" +
            "3. Снимите платформу с фиксаторов\n\n" +
            "4. Опуститесь до параллели с полом\n\n" +
            "5. Поднимитесь, выпрямляя ноги\n\n" +
            "6. Не блокируйте колени");



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
        binding.imageViewExercise.setImageResource(R.drawable.hack_squat_narrow);
        
        binding.textViewExerciseDescription.setText(
            "Акцент на внешнюю часть квадрицепса");


        binding.textViewTechnique.setText(
            "1. Установите спинку под углом\n\n" +
            "2. Поставьте ноги узко на платформе\n\n" +
            "3. Носки слегка наружу\n\n" +
            "4. Опуститесь до параллели\n\n" +
            "5. Поднимитесь, выпрямляя ноги\n\n" +
            "6. Контролируйте движение");



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
        binding.imageViewExercise.setImageResource(R.drawable.hack_squat_wide);
        
        binding.textViewExerciseDescription.setText(
            "Акцент на внутреннюю часть бедра");


        binding.textViewTechnique.setText(
            "1. Установите спинку под углом\n\n" +
            "2. Поставьте ноги широко на платформе\n\n" +
            "3. Разверните носки наружу\n\n" +
            "4. Опуститесь до параллели\n\n" +
            "5. Поднимитесь, выпрямляя ноги\n\n" +
            "6. Следите за коленями");


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
        binding.imageViewExercise.setImageResource(R.drawable.sumo_squat_dumbbell);
        
        binding.textViewExerciseDescription.setText(
            "Упражнение для внутренней поверхности бедра");



        binding.textViewTechnique.setText(
            "1. Возьмите гантель к груди\n\n" +
            "2. Поставьте ноги широко\n\n" +
            "3. Разверните носки наружу\n\n" +
            "4. Опуститесь в присед\n\n" +
            "5. Держите спину прямо\n\n" +
            "6. Поднимитесь, выпрямляя ноги");



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
        binding.imageViewExercise.setImageResource(R.drawable.dumbbell_squats);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для всех мышц ног");


        binding.textViewTechnique.setText(
            "1. Возьмите гантели в руки\n\n" +
            "2. Поставьте ноги на ширине плеч\n\n" +
            "3. Опуститесь в присед\n\n" +
            "4. Колени над носками\n\n" +
            "5. Держите спину прямо\n\n" +
            "6. Поднимитесь в исходное положение");



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
        binding.imageViewExercise.setImageResource(R.drawable.band_squats);
        
        binding.textViewExerciseDescription.setText(
            "Упражнение для начинающих и разминки");



        binding.textViewTechnique.setText(
            "1. Встаньте на резинку\n\n" +
            "2. Возьмите концы резинки в руки\n\n" +
            "3. Ноги на ширине плеч\n\n" +
            "4. Опуститесь в присед\n\n" +
            "5. Следите за осанкой\n\n" +
            "6. Вернитесь в исходное положение");


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
        binding.imageViewExercise.setImageResource(R.drawable.barbell_squats);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для развития силы ног");


        binding.textViewTechnique.setText(
            "1. Установите штангу на стойки\n\n" +
            "2. Встаньте под штангу\n\n" +
            "3. Снимите штангу со стоек\n\n" +
            "4. Опуститесь в присед\n\n" +
            "5. Следите за техникой\n\n" +
            "6. Поднимитесь, выпрямляя ноги");



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
        binding.imageViewExercise.setImageResource(R.drawable.front_squats);
        
        binding.textViewExerciseDescription.setText(
            "Вариация приседаний с акцентом на переднюю часть бедра");

        binding.textViewTechnique.setText(
            "1. Установите штангу на груди\n\n" +
            "2. Локти подняты\n\n" +
            "3. Ноги на ширине плеч\n\n" +
            "4. Опуститесь в присед\n\n" +
            "5. Держите корпус прямо\n\n" +
            "6. Поднимитесь в исходное положение");



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
        binding.imageViewExercise.setImageResource(R.drawable.box_squats);
        
        binding.textViewExerciseDescription.setText(
            "Контролируемые приседания для начинающих");



        binding.textViewTechnique.setText(
            "1. Установите скамью позади\n\n" +
            "2. Возьмите штангу на спину\n\n" +
            "3. Опуститесь до касания скамьи\n\n" +
            "4. Слегка коснитесь скамьи\n\n" +
            "5. Поднимитесь вверх\n\n" +
            "6. Сохраняйте контроль движения");



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
        binding.imageViewExercise.setImageResource(R.drawable.leg_extension);
        
        binding.textViewExerciseDescription.setText(
            "Изолированное упражнение для квадрицепсов");



        binding.textViewTechnique.setText(
            "1. Сядьте в тренажер\n\n" +
            "2. Отрегулируйте валик\n\n" +
            "3. Возьмитесь за рукоятки\n\n" +
            "4. Выпрямите ноги\n\n" +
            "5. Задержитесь в верхней точке\n\n" +
            "6. Медленно опустите вес");


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
        binding.imageViewExercise.setImageResource(R.drawable.seated_leg_adduction);
        
        binding.textViewExerciseDescription.setText(
            "Упражнение для приводящих мышц бедра");



        binding.textViewTechnique.setText(
            "1. Сядьте в тренажер\n\n" +
            "2. Разведите ноги\n\n" +
            "3. Возьмитесь за рукоятки\n\n" +
            "4. Сведите ноги вместе\n\n" +
            "5. Задержитесь в конечной точке\n\n" +
            "6. Медленно вернитесь");



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
        binding.imageViewExercise.setImageResource(R.drawable.single_leg_curl);
        
        binding.textViewExerciseDescription.setText(
            "Изолированная работа на бицепс бедра");


        binding.textViewTechnique.setText(
            "1. Лягте на тренажер\n\n" +
            "2. Зафиксируйте одну ногу\n\n" +
            "3. Согните ногу в колене\n\n" +
            "4. Поднимите вес\n\n" +
            "5. Задержитесь наверху\n\n" +
            "6. Медленно опустите");



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
        binding.imageViewExercise.setImageResource(R.drawable.lying_leg_curl);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для бицепса бедра");



        binding.textViewTechnique.setText(
            "1. Лягте на тренажер\n\n" +
            "2. Зафиксируйте ноги\n\n" +
            "3. Согните ноги в коленях\n\n" +
            "4. Поднимите вес\n\n" +
            "5. Задержитесь в верхней точке\n\n" +
            "6. Медленно опустите");


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
        binding.imageViewExercise.setImageResource(R.drawable.seated_leg_curl);
        
        binding.textViewExerciseDescription.setText(
            "Альтернативное упражнение для бицепса бедра");


        binding.textViewTechnique.setText(
            "1. Сядьте в тренажер\n\n" +
            "2. Отрегулируйте валик\n\n" +
            "3. Зафиксируйте ноги\n\n" +
            "4. Согните ноги в коленях\n\n" +
            "5. Задержитесь в верхней точке\n\n" +
            "6. Медленно вернитесь");



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
        binding.imageViewExercise.setImageResource(R.drawable.standing_leg_curl);
        
        binding.textViewExerciseDescription.setText(
            "Изолированное упражнение для бицепса бедра");


        binding.textViewTechnique.setText(
            "1. Встаньте у тренажера\n\n" +
            "2. Прикрепите манжету\n\n" +
            "3. Держитесь за опору\n\n" +
            "4. Согните ногу\n\n" +
            "5. Задержитесь наверху\n\n" +
            "6. Медленно опустите");

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
        binding.imageViewExercise.setImageResource(R.drawable.deadlift_f);
        
        binding.textViewExerciseDescription.setText(
            "Базовое упражнение для всего тела");

        binding.textViewTechnique.setText(
            "1. Подойдите к штанге\n\n" +
            "2. Ноги на ширине плеч\n\n" +
            "3. Возьмитесь за гриф\n\n" +
            "4. Выпрямите спину\n\n" +
            "5. Поднимите штангу\n\n" +
            "6. Опустите контролируемо");



        binding.textViewAlternatives.setText(
            "• Румынская становая тяга\n" +
            "• Становая на прямых ногах\n" +
            "• Гиперэкстензия\n" +
            "• Тяга гантелей");
    }

    private void setupSumoDeadlift() {
        binding.textViewExerciseTitle.setText("Становая тяга 'сумо'");
        binding.imageViewExercise.setImageResource(R.drawable.sumo_deadlift);
        
        binding.textViewExerciseDescription.setText(
            "Вариация становой тяги с широкой постановкой ног");



        binding.textViewTechnique.setText(
            "1. Встаньте широко\n\n" +
            "2. Носки развернуты наружу\n\n" +
            "3. Возьмитесь за гриф\n\n" +
            "4. Держите спину прямо\n\n" +
            "5. Поднимите штангу\n\n" +
            "6. Опустите контролируемо");



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