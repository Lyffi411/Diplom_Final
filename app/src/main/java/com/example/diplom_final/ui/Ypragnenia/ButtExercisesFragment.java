package com.example.diplom_final.ui.Ypragnenia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.R;
import com.example.diplom_final.databinding.FragmentButtExercisesBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ButtExercisesFragment extends Fragment {
    private RecyclerView recyclerView;
    private ButtExerciseAdapter adapter;
    private List<ButtExerciseItem> exerciseList;
    private List<ButtExerciseItem> exerciseListFull;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_butt_exercises, container, false);

        recyclerView = root.findViewById(R.id.recyclerViewButtExercises);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        EditText searchEditText = root.findViewById(R.id.searchEditText);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        exerciseList = new ArrayList<>();

        // Болгарские приседания
        addExercise(
                1,
                "Болгарские приседания",
                "Эффективное односторонее упражнение для развития ягодичных мышц и квадрицепсов.",
                "1. Встаньте спиной к скамье на расстоянии большого шага\n" +
                        "2. Поставьте одну ногу на скамью позади себя\n" +
                        "3. Опускайтесь вниз, сгибая переднюю ногу в колене\n" +
                        "4. Заднее колено должно почти касаться пола\n" +
                        "5. Вернитесь в исходное положение",
                "3-4 подхода по 12-15 повторений на каждую ногу\n" +
                        "Отдых между подходами 60-90 секунд\n" +
                        "Выполнять после разминки",
                "• Травмы коленных суставов\n" +
                        "• Проблемы с равновесием\n" +
                        "• Острые боли в спине",
                Arrays.asList(
                        "Выпады вперед",
                        "Приседания на одной ноге",
                        "Приседания со штангой"
                ),
                "https://www.youtube.com/watch?v=butt_example1",
                R.drawable.butt_exercise1
        );

        // Выпады вперед
        addExercise(
                2,
                "Выпады вперед",
                "Базовое упражнение для развития ягодиц, квадрицепсов и улучшения баланса.",
                "1. Встаньте прямо, ноги на ширине плеч\n" +
                        "2. Сделайте большой шаг вперед одной ногой\n" +
                        "3. Опуститесь вниз, сгибая оба колена до 90 градусов\n" +
                        "4. Заднее колено почти касается пола\n" +
                        "5. Вернитесь в исходное положение",
                "4 подхода по 12-15 повторений на каждую ногу\n" +
                        "Отдых 60 секунд\n" +
                        "Можно использовать дополнительный вес",
                "• Травмы коленей\n" +
                        "• Проблемы с балансом\n" +
                        "• Сильные боли в суставах",
                Arrays.asList(
                        "Болгарские приседания",
                        "Обратные выпады",
                        "Приседания"
                ),
                "https://www.youtube.com/watch?v=butt_example2",
                R.drawable.butt_exercise2
        );

        // Гиперэкстензия на ягодицы
        addExercise(
                3,
                "Гиперэкстензия на ягодицы",
                "Изолирующее упражнение для проработки ягодичных мышц и нижней части спины.",
                "1. Установите скамью под углом 45 градусов\n" +
                        "2. Зафиксируйте ноги в упорах\n" +
                        "3. Опустите корпус вниз\n" +
                        "4. Поднимите корпус, напрягая ягодицы\n" +
                        "5. Задержитесь в верхней точке на 1-2 секунды",
                "3-4 подхода по 15-20 повторений\n" +
                        "Отдых 60-90 секунд\n" +
                        "Выполнять в середине тренировки",
                "• Грыжи позвоночника\n" +
                        "• Острые боли в пояснице\n" +
                        "• Травмы тазобедренных суставов",
                Arrays.asList(
                        "Обратная гиперэкстензия",
                        "Ягодичный мост",
                        "Становая тяга"
                ),
                "https://www.youtube.com/watch?v=butt_example3",
                R.drawable.butt_exercise3
        );

        // Доброе утро
        addExercise(
                4,
                "Доброе утро",
                "Эффективное упражнение для развития ягодиц и задней поверхности бедра.",
                "1. Встаньте прямо, штанга на плечах\n" +
                        "2. Ноги на ширине плеч, слегка согнуты в коленях\n" +
                        "3. Наклоняйтесь вперед, отводя таз назад\n" +
                        "4. Опускайтесь до параллели с полом\n" +
                        "5. Вернитесь в исходное положение",
                "3-4 подхода по 10-12 повторений\n" +
                        "Отдых 90 секунд\n" +
                        "Начинать с легкого веса",
                "• Проблемы с поясницей\n" +
                        "• Травмы позвоночника\n" +
                        "• Плохая гибкость",
                Arrays.asList(
                        "Румынская становая тяга",
                        "Гиперэкстензия",
                        "Наклоны со штангой"
                ),
                "https://www.youtube.com/watch?v=butt_example4",
                R.drawable.butt_exercise4
        );

        // Мертвая тяга
        addExercise(
                5,
                "Мертвая тяга",
                "Базовое упражнение для развития всех мышц задней поверхности тела.",
                "1. Встаньте перед штангой, ноги на ширине плеч\n" +
                        "2. Наклонитесь и возьмите штангу хватом сверху\n" +
                        "3. Выпрямитесь, держа спину прямой\n" +
                        "4. В верхней точке сведите лопатки\n" +
                        "5. Медленно опустите штангу",
                "4 подхода по 8-12 повторений\n" +
                        "Отдых 2-3 минуты\n" +
                        "Выполнять в начале тренировки",
                "• Грыжи межпозвоночных дисков\n" +
                        "• Травмы поясницы\n" +
                        "• Высокое давление",
                Arrays.asList(
                        "Румынская становая тяга",
                        "Гиперэкстензия",
                        "Становая тяга сумо"
                ),
                "https://www.youtube.com/watch?v=butt_example5",
                R.drawable.butt_exercise5
        );

        // Обратная гиперэкстензия
        addExercise(
                6,
                "Обратная гиперэкстензия",
                "Изолирующее упражнение для ягодиц и бицепса бедра.",
                "1. Лягте животом на скамью\n" +
                        "2. Зафиксируйте верхнюю часть тела\n" +
                        "3. Поднимите прямые ноги до горизонтали\n" +
                        "4. Задержитесь в верхней точке\n" +
                        "5. Медленно опустите ноги",
                "3 подхода по 15-20 повторений\n" +
                        "Отдых 60 секунд\n" +
                        "Выполнять в конце тренировки",
                "• Травмы поясницы\n" +
                        "• Проблемы с тазобедренными суставами\n" +
                        "• Беременность",
                Arrays.asList(
                        "Гиперэкстензия",
                        "Подъем ног в упоре",
                        "Ягодичный мост"
                ),
                "https://www.youtube.com/watch?v=butt_example6",
                R.drawable.butt_exercise6
        );

        // Отведение ноги вверх в блоке с упором в лавку
        addExercise(
                7,
                "Отведение ноги вверх в блоке",
                "Изолирующее упражнение для целенаправленной проработки ягодичных мышц.",
                "1. Встаньте перед блоком, упритесь в скамью\n" +
                        "2. Прикрепите манжету к рабочей ноге\n" +
                        "3. Отведите ногу назад и вверх\n" +
                        "4. Задержитесь в верхней точке\n" +
                        "5. Медленно верните ногу",
                "3-4 подхода по 15-20 повторений на каждую ногу\n" +
                        "Отдых 45-60 секунд\n" +
                        "Фокус на ягодичные мышцы",
                "• Травмы тазобедренных суставов\n" +
                        "• Проблемы с коленями\n" +
                        "• Боли в пояснице",
                Arrays.asList(
                        "Отведение ноги назад в блоке",
                        "Ягодичный мост",
                        "Обратная гиперэкстензия"
                ),
                "https://www.youtube.com/watch?v=butt_example7",
                R.drawable.butt_exercise7
        );

        // Отведение ноги назад ("ласточка")
        addExercise(
                8,
                "Отведение ноги назад (\"ласточка\")",
                "Упражнение для развития баланса и укрепления ягодичных мышц.",
                "1. Встаньте прямо, руки вдоль тела\n" +
                        "2. Наклонитесь вперед, поднимая одну ногу назад\n" +
                        "3. Руки разведите в стороны\n" +
                        "4. Удерживайте равновесие\n" +
                        "5. Вернитесь в исходное положение",
                "3 подхода по 12-15 повторений на каждую ногу\n" +
                        "Отдых 60 секунд\n" +
                        "Выполнять после разминки",
                "• Проблемы с вестибулярным аппаратом\n" +
                        "• Травмы спины\n" +
                        "• Слабые мышцы кора",
                Arrays.asList(
                        "Отведение ноги назад в блоке",
                        "Гиперэкстензия",
                        "Становая тяга на одной ноге"
                ),
                "https://www.youtube.com/watch?v=butt_example8",
                R.drawable.butt_exercise8
        );

        // Отведение ноги назад в блоке
        addExercise(
                9,
                "Отведение ноги назад в блоке",
                "Изолирующее упражнение для проработки ягодичных мышц с постоянным сопротивлением.",
                "1. Встаньте лицом к блоку\n" +
                        "2. Прикрепите манжету к лодыжке\n" +
                        "3. Отведите ногу назад до полного сокращения ягодиц\n" +
                        "4. Удержите пиковое сокращение\n" +
                        "5. Медленно верните ногу",
                "4 подхода по 15-20 повторений на каждую ногу\n" +
                        "Отдых 45 секунд\n" +
                        "Выполнять в середине тренировки",
                "• Травмы тазобедренных суставов\n" +
                        "• Проблемы с поясницей\n" +
                        "• Воспаление сухожилий",
                Arrays.asList(
                        "Отведение ноги с резинкой",
                        "Махи ногой назад",
                        "Ягодичный мост"
                ),
                "https://www.youtube.com/watch?v=butt_example9",
                R.drawable.butt_exercise9
        );

        // Приседания плие с гантелей
        addExercise(
                10,
                "Приседания плие с гантелей",
                "Вариация приседаний для акцента на внутренней поверхности бедра и ягодицах.",
                "1. Встаньте широко, носки врозь\n" +
                        "2. Держите гантелю перед собой\n" +
                        "3. Присядьте, разводя колени в стороны\n" +
                        "4. Опуститесь до параллели с полом\n" +
                        "5. Поднимитесь, выпрямляя ноги",
                "3-4 подхода по 12-15 повторений\n" +
                        "Отдых 60-90 секунд\n" +
                        "Следите за положением коленей",
                "• Травмы коленных суставов\n" +
                        "• Проблемы с тазобедренными суставами\n" +
                        "• Ограниченная подвижность",
                Arrays.asList(
                        "Приседания сумо",
                        "Приседания со штангой",
                        "Выпады в сторону"
                ),
                "https://www.youtube.com/watch?v=butt_example10",
                R.drawable.butt_exercise10
        );

        // Разведение ног сидя
        addExercise(
                11,
                "Разведение ног сидя",
                "Изолирующее упражнение для внутренней и внешней поверхности бедра.",
                "1. Сядьте в тренажер\n" +
                        "2. Установите комфортный вес\n" +
                        "3. Разведите ноги в стороны\n" +
                        "4. Задержитесь в крайней точке\n" +
                        "5. Медленно сведите ноги",
                "3 подхода по 15-20 повторений\n" +
                        "Отдых 60 секунд\n" +
                        "Контролируйте движение",
                "• Травмы паховой области\n" +
                        "• Проблемы с тазобедренными суставами\n" +
                        "• Воспаление связок",
                Arrays.asList(
                        "Приседания плие",
                        "Выпады в сторону",
                        "Приседания сумо"
                ),
                "https://www.youtube.com/watch?v=butt_example11",
                R.drawable.butt_exercise11
        );

        // Разгибание бедра в блоке
        addExercise(
                12,
                "Разгибание бедра в блоке",
                "Изолирующее упражнение для проработки ягодичных мышц.",
                "1. Встаньте перед блоком\n" +
                        "2. Прикрепите манжету к лодыжке\n" +
                        "3. Отведите ногу назад\n" +
                        "4. Удержите напряжение в ягодицах\n" +
                        "5. Медленно верните ногу",
                "4 подхода по 15-20 повторений на каждую ногу\n" +
                        "Отдых 45-60 секунд\n" +
                        "Фокус на ягодичные мышцы",
                "• Травмы поясницы\n" +
                        "• Проблемы с тазобедренными суставами\n" +
                        "• Воспаление сухожилий",
                Arrays.asList(
                        "Отведение ноги назад в блоке",
                        "Гиперэкстензия",
                        "Ягодичный мост"
                ),
                "https://www.youtube.com/watch?v=butt_example12",
                R.drawable.butt_exercise12
        );

        // Становая тяга на одной ноге с гантелями
        addExercise(
                13,
                "Становая тяга на одной ноге с гантелями",
                "Одностороннее упражнение для развития баланса и силы ягодиц.",
                "1. Встаньте на одну ногу, гантели в руках\n" +
                        "2. Наклонитесь вперед, поднимая вторую ногу назад\n" +
                        "3. Опустите гантели к полу\n" +
                        "4. Вернитесь в исходное положение\n" +
                        "5. Сохраняйте равновесие",
                "3 подхода по 10-12 повторений на каждую ногу\n" +
                        "Отдых 90 секунд\n" +
                        "Начинать с легкого веса",
                "• Проблемы с равновесием\n" +
                        "• Травмы коленей\n" +
                        "• Боли в пояснице",
                Arrays.asList(
                        "Румынская становая тяга",
                        "Болгарские приседания",
                        "Выпады"
                ),
                "https://www.youtube.com/watch?v=butt_example13",
                R.drawable.butt_exercise13
        );

        // Становая тяга на прямых ногах с гантелями
        addExercise(
                14,
                "Становая тяга на прямых ногах с гантелями",
                "Упражнение для развития задней поверхности бедра и ягодиц.",
                "1. Встаньте прямо, гантели в руках\n" +
                        "2. Ноги слегка согнуты в коленях\n" +
                        "3. Наклонитесь вперед, отводя таз назад\n" +
                        "4. Опустите гантели вдоль ног\n" +
                        "5. Вернитесь в исходное положение",
                "4 подхода по 12-15 повторений\n" +
                        "Отдых 60-90 секунд\n" +
                        "Держите спину прямой",
                "• Грыжи позвоночника\n" +
                        "• Травмы поясницы\n" +
                        "• Плохая гибкость",
                Arrays.asList(
                        "Румынская становая тяга",
                        "Мертвая тяга",
                        "Гиперэкстензия"
                ),
                "https://www.youtube.com/watch?v=butt_example14",
                R.drawable.butt_exercise14
        );

        // Ягодичный мост со штангой на скамье
        addExercise(
                15,
                "Ягодичный мост со штангой на скамье",
                "Эффективное упражнение для изолированной проработки ягодичных мышц.",
                "1. Сядьте на пол, спиной к скамье\n" +
                        "2. Положите штангу на бедра\n" +
                        "3. Поднимите таз, напрягая ягодицы\n" +
                        "4. Задержитесь в верхней точке\n" +
                        "5. Медленно опуститесь",
                "4 подхода по 12-15 повторений\n" +
                        "Отдых 60 секунд\n" +
                        "Фокус на сжатии ягодиц",
                "• Травмы поясницы\n" +
                        "• Проблемы с шеей\n" +
                        "• Грыжи позвоночника",
                Arrays.asList(
                        "Ягодичный мост без веса",
                        "Гиперэкстензия",
                        "Обратная гиперэкстензия"
                ),
                "https://www.youtube.com/watch?v=butt_example15",
                R.drawable.butt_exercise15
        );

        // Обратные гакк-приседания на одной ноге
        addExercise(
                16,
                "Обратные гакк-приседания на одной ноге",
                "Эффективное одностороннее упражнение для развития силы и баланса ягодичных мышц.",
                "1. Встаньте в гакк-машину спиной к упору\n" +
                        "2. Поставьте одну ногу на платформу, вторую поднимите\n" +
                        "3. Медленно опуститесь вниз на одной ноге\n" +
                        "4. В нижней точке колено рабочей ноги согнуто под 90 градусов\n" +
                        "5. Поднимитесь, выпрямляя ногу и напрягая ягодицы",
                "3-4 подхода по 10-12 повторений на каждую ногу\n" +
                        "Отдых 90 секунд\n" +
                        "Начинать с небольшого веса для освоения техники",
                "• Травмы коленных суставов\n" +
                        "• Проблемы с равновесием\n" +
                        "• Боли в пояснице\n" +
                        "• Травмы голеностопа",
                Arrays.asList(
                        "Болгарские приседания",
                        "Приседания на одной ноге",
                        "Выпады с гантелями"
                ),
                "https://www.youtube.com/watch?v=butt_example16",
                R.drawable.butt_exercise16
        );

        // Сумо присед со штангой
        addExercise(
                17,
                "Сумо присед",
                "Вариация приседаний с широкой постановкой ног для акцента на внутренней поверхности бедра и ягодицах.",
                "1. Встаньте широко, носки развернуты\n" +
                        "2. Штанга на плечах\n" +
                        "3. Присядьте, разводя колени\n" +
                        "4. Опуститесь ниже параллели\n" +
                        "5. Поднимитесь, выпрямляя ноги",
                "4 подхода по 8-12 повторений\n" +
                        "Отдых 2 минуты\n" +
                        "Следите за техникой",
                "• Травмы коленей\n" +
                        "• Проблемы с тазобедренными суставами\n" +
                        "• Травмы спины",
                Arrays.asList(
                        "Приседания плие",
                        "Классические приседания",
                        "Болгарские приседания"
                ),
                "https://www.youtube.com/watch?v=butt_example17",
                R.drawable.butt_exercise17
        );

        exerciseListFull = new ArrayList<>(exerciseList);
        adapter = new ButtExerciseAdapter(exerciseList, this::navigateToExerciseDetail);
        recyclerView.setAdapter(adapter);

        return root;
    }

    private void addExercise(int id, String title, String description, String technique,
                             String sets, String contraindications, List<String> alternatives,
                             String videoUrl, int imageResourceId) {
        Bundle exerciseData = new Bundle();
        exerciseData.putString("exerciseTitle", title);
        exerciseData.putString("exerciseDescription", description);
        exerciseData.putString("technique", technique);
        exerciseData.putString("sets", sets);
        exerciseData.putString("contraindications", contraindications);
        exerciseData.putStringArrayList("alternatives", new ArrayList<>(alternatives));
        exerciseData.putString("videoUrl", videoUrl);
        exerciseData.putInt("exerciseImage", imageResourceId);

        ButtExerciseItem exercise = new ButtExerciseItem(title, description, imageResourceId);
        exercise.setId(id);
        exercise.setExtraData(exerciseData);
        exerciseList.add(exercise);
    }

    private void navigateToExerciseDetail(ButtExerciseItem exercise) {
        Navigation.findNavController(requireView())
                .navigate(getNavigationActionId(exercise.getId()), exercise.getExtraData());
    }

    private int getNavigationActionId(int exerciseId) {
        switch (exerciseId) {
            case 1:
                return R.id.action_nav_butt_exercises_to_buttExerciseDetailFragment1;
            case 2:
                return R.id.action_nav_butt_exercises_to_buttExerciseDetailFragment2;
            case 3:
                return R.id.action_nav_butt_exercises_to_buttExerciseDetailFragment3;
            case 4:
                return R.id.action_nav_butt_exercises_to_buttExerciseDetailFragment4;
            case 5:
                return R.id.action_nav_butt_exercises_to_buttExerciseDetailFragment5;
            case 6:
                return R.id.action_nav_butt_exercises_to_buttExerciseDetailFragment6;
            case 7:
                return R.id.action_nav_butt_exercises_to_buttExerciseDetailFragment7;
            case 8:
                return R.id.action_nav_butt_exercises_to_buttExerciseDetailFragment8;
            case 9:
                return R.id.action_nav_butt_exercises_to_buttExerciseDetailFragment9;
            case 10:
                return R.id.action_nav_butt_exercises_to_buttExerciseDetailFragment10;
            case 11:
                return R.id.action_nav_butt_exercises_to_buttExerciseDetailFragment11;
            case 12:
                return R.id.action_nav_butt_exercises_to_buttExerciseDetailFragment12;
            case 13:
                return R.id.action_nav_butt_exercises_to_buttExerciseDetailFragment13;
            case 14:
                return R.id.action_nav_butt_exercises_to_buttExerciseDetailFragment14;
            case 15:
                return R.id.action_nav_butt_exercises_to_buttExerciseDetailFragment15;
            case 16:
                return R.id.action_nav_butt_exercises_to_buttExerciseDetailFragment16;
            case 17:
                return R.id.action_nav_butt_exercises_to_buttExerciseDetailFragment17;
            default:
                return R.id.action_nav_butt_exercises_to_buttExerciseDetailFragment1;
        }
    }

    private void filter(String text) {
        List<ButtExerciseItem> filteredList = new ArrayList<>();

        for (ButtExerciseItem item : exerciseListFull) {
            if (item.getTitle().toLowerCase(Locale.getDefault())
                    .contains(text.toLowerCase(Locale.getDefault()))) {
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }
}