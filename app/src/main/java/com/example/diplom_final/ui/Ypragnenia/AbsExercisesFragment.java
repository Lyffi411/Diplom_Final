package com.example.diplom_final.ui.Ypragnenia;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class AbsExercisesFragment extends Fragment {
    private RecyclerView recyclerView;
    private AbsExerciseAdapter adapter;
    private List<AbsExerciseItem> exerciseList;
    private List<AbsExerciseItem> exerciseListFull;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_abs_exercises, container, false);
        
        recyclerView = root.findViewById(R.id.recyclerViewAbsExercises);
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
        
        // Обратное скручивание в нижнем блоке
        addExercise(
            1,
            "Обратное скручивание в нижнем блоке",
            "Эффективное упражнение для проработки нижней части пресса с использованием дополнительного сопротивления.",
            "1. Встаньте на колени лицом к нижнему блоку\n" +
            "2. Возьмите рукоятку и прижмите её к плечам\n" +
            "3. На выдохе скрутите корпус вниз и вперед\n" +
            "4. Задержитесь на 1-2 секунды в нижней точке\n" +
            "5. На вдохе медленно вернитесь в исходное положение",
            "4 подхода по 15-20 повторений\n" +
            "Отдых между подходами 60-90 секунд\n" +
            "Выполнять в середине тренировки пресса",
            "• Грыжи позвоночника\n" +
            "• Острые боли в пояснице\n" +
            "• Недавние операции на брюшной полости",
            Arrays.asList(
                "Обратные скручивания на полу",
                "Подъемы ног в висе",
                "Скручивания на римском стуле"
            ),
            "https://www.youtube.com/watch?v=abs_example1",
            R.drawable.abs_exercise1
        );

        // Обратное скручивание на наклонной скамье
        addExercise(
            2,
            "Обратное скручивание на наклонной скамье",
            "Базовое упражнение для нижнего пресса с использованием веса собственного тела и гравитации.",
            "1. Лягте на наклонную скамью, возьмитесь за опоры за головой\n" +
            "2. Ноги прямые, направлены вверх\n" +
            "3. На выдохе поднимите таз, скручивая корпус\n" +
            "4. Задержитесь в верхней точке\n" +
            "5. На вдохе плавно опуститесь",
            "3-4 подхода по 12-15 повторений\n" +
            "Отдых 60 секунд\n" +
            "Выполнять в начале тренировки",
            "• Проблемы с поясницей\n" +
            "• Высокое кровяное давление\n" +
            "• Беременность",
            Arrays.asList(
                "Подъемы ног лежа",
                "Обратные скручивания на полу",
                "Подъемы коленей в упоре"
            ),
            "https://www.youtube.com/watch?v=abs_example2",
            R.drawable.abs_exercise2
        );

        // Обратное скручивание на наклонной скамье с отрывом таза
        addExercise(
            3,
            "Обратное скручивание на наклонной скамье с отрывом таза",
            "Продвинутый вариант обратного скручивания с максимальной амплитудой движения.",
            "1. Лягте на наклонную скамью, держитесь за опоры\n" +
            "2. Ноги согнуты в коленях\n" +
            "3. На выдохе поднимите таз максимально вверх\n" +
            "4. Полностью оторвите таз от скамьи\n" +
            "5. На вдохе медленно вернитесь в исходное положение",
            "3 подхода по 10-12 повторений\n" +
            "Отдых 90 секунд\n" +
            "Рекомендуется для опытных атлетов",
            "• Травмы поясничного отдела\n" +
            "• Грыжи межпозвоночных дисков\n" +
            "• Слабые мышцы кора",
            Arrays.asList(
                "Обратные скручивания без отрыва таза",
                "Подъемы ног в висе",
                "Скручивания на римской скамье"
            ),
            "https://www.youtube.com/watch?v=abs_example3",
            R.drawable.abs_exercise3
        );

        // Планка
        addExercise(
            4,
            "Планка",
            "Статическое упражнение для укрепления всего кора и развития выносливости мышц живота.",
            "1. Примите упор на локтях и носках\n" +
            "2. Тело прямое, как доска\n" +
            "3. Втяните живот\n" +
            "4. Держите позицию заданное время\n" +
            "5. Следите за дыханием",
            "3-4 подхода по 30-60 секунд\n" +
            "Отдых между подходами 45-60 секунд\n" +
            "Можно выполнять ежедневно",
            "• Травмы плечевых суставов\n" +
            "• Сильные боли в спине\n" +
            "• Грыжи живота",
            Arrays.asList(
                "Боковая планка",
                "Планка на прямых руках",
                "Динамическая планка"
            ),
            "https://www.youtube.com/watch?v=abs_example4",
            R.drawable.abs_exercise4
        );

        // Повороты корпуса в висе на турнике
        addExercise(
            5,
            "Повороты корпуса в висе на турнике",
            "Сложное упражнение для косых мышц живота и общего укрепления кора.",
            "1. Повисните на турнике прямым хватом\n" +
            "2. Поднимите ноги параллельно полу\n" +
            "3. Выполняйте повороты корпуса влево и вправо\n" +
            "4. Движения должны быть контролируемыми\n" +
            "5. Не используйте инерцию",
            "3 подхода по 10-12 повторений в каждую сторону\n" +
            "Отдых 90-120 секунд\n" +
            "Выполнять после разминки",
            "• Слабый хват\n" +
            "• Травмы плеч\n" +
            "• Проблемы с позвоночником",
            Arrays.asList(
                "Скручивания лежа",
                "Русские повороты",
                "Боковые наклоны с гантелей"
            ),
            "https://www.youtube.com/watch?v=abs_example5",
            R.drawable.abs_exercise5
        );

        // Складка в положении лежа
        addExercise(
            6,
            "Складка в положении лежа",
            "Эффективное упражнение для проработки всех мышц пресса, особенно верхней части.",
            "1. Лягте на спину, руки за головой\n" +
            "2. Ноги прямые, приподняты от пола\n" +
            "3. Одновременно поднимите корпус и ноги\n" +
            "4. Коснитесь пальцами ног\n" +
            "5. Медленно вернитесь в исходное положение",
            "3 подхода по 12-15 повторений\n" +
            "Отдых 60-90 секунд\n" +
            "Выполнять в середине тренировки",
            "• Травмы поясницы\n" +
            "• Грыжи позвоночника\n" +
            "• Слабые мышцы спины",
            Arrays.asList(
                "Подъемы корпуса лежа",
                "Подъемы ног лежа",
                "V-складки"
            ),
            "https://www.youtube.com/watch?v=abs_example6",
            R.drawable.abs_exercise6
        );

        // Скручивание на брусьях на косые мышцы живота
        addExercise(
            7,
            "Скручивание на брусьях на косые мышцы живота",
            "Продвинутое упражнение для развития косых мышц живота и общей силы кора.",
            "1. Примите упор на брусьях\n" +
            "2. Поднимите ноги параллельно полу\n" +
            "3. На выдохе выполните скручивание в сторону\n" +
            "4. Задержитесь на секунду\n" +
            "5. Вернитесь и повторите в другую сторону",
            "3 подхода по 8-12 повторений на каждую сторону\n" +
            "Отдых 90 секунд\n" +
            "Выполнять после основных упражнений",
            "• Слабые мышцы плечевого пояса\n" +
            "• Травмы запястий\n" +
            "• Проблемы с позвоночником",
            Arrays.asList(
                "Боковые скручивания на полу",
                "Наклоны с гантелей",
                "Скручивания на римском стуле"
            ),
            "https://www.youtube.com/watch?v=abs_example7",
            R.drawable.abs_exercise7
        );

        // Скручивание сидя на наклонной скамье
        addExercise(
            8,
            "Скручивание сидя на наклонной скамье",
            "Классическое упражнение для верхней части пресса с использованием наклонной скамьи.",
            "1. Сядьте на наклонную скамью, зафиксируйте ноги\n" +
            "2. Руки за головой или на груди\n" +
            "3. На выдохе скрутите корпус вперед\n" +
            "4. Максимально сократите мышцы пресса\n" +
            "5. На вдохе вернитесь в исходное положение",
            "4 подхода по 15-20 повторений\n" +
            "Отдых 60 секунд\n" +
            "Выполнять в начале или середине тренировки",
            "• Грыжи позвоночника\n" +
            "• Проблемы с шейным отделом\n" +
            "• Гипертония",
            Arrays.asList(
                "Скручивания на полу",
                "Скручивания на фитболе",
                "Подъемы корпуса на римской скамье"
            ),
            "https://www.youtube.com/watch?v=abs_example8",
            R.drawable.abs_exercise8
        );

        // Упражнение «Велосипед»
        addExercise(
            9,
            "Упражнение «Велосипед»",
            "Комплексное упражнение для проработки всех мышц пресса, особенно косых мышц живота.",
            "1. Лягте на спину, руки за головой\n" +
            "2. Поднимите ноги, согнутые в коленях\n" +
            "3. Поочередно выпрямляйте ноги\n" +
            "4. Одновременно выполняйте скручивания корпуса\n" +
            "5. Касайтесь локтем противоположного колена",
            "3-4 подхода по 20-30 секунд\n" +
            "Отдых 45-60 секунд\n" +
            "Выполнять в конце тренировки пресса",
            "• Травмы поясницы\n" +
            "• Проблемы с шеей\n" +
            "• Острые боли в спине",
            Arrays.asList(
                "Скручивания лежа",
                "Подъемы ног лежа",
                "Русские скручивания"
            ),
            "https://www.youtube.com/watch?v=abs_example9",
            R.drawable.abs_exercise9
        );

        exerciseListFull = new ArrayList<>(exerciseList);
        adapter = new AbsExerciseAdapter(exerciseList, this::navigateToExerciseDetail);
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

        AbsExerciseItem exercise = new AbsExerciseItem(title, description, imageResourceId);
        exercise.setId(id);
        exercise.setExtraData(exerciseData);
        exerciseList.add(exercise);
    }

    private void navigateToExerciseDetail(AbsExerciseItem exercise) {
        Navigation.findNavController(requireView())
            .navigate(getNavigationActionId(exercise.getId()), exercise.getExtraData());
    }

    private int getNavigationActionId(int exerciseId) {
        switch (exerciseId) {
            case 1:
                return R.id.action_nav_abs_exercises_to_absExerciseDetailFragment1;
            case 2:
                return R.id.action_nav_abs_exercises_to_absExerciseDetailFragment2;
            case 3:
                return R.id.action_nav_abs_exercises_to_absExerciseDetailFragment3;
            case 4:
                return R.id.action_nav_abs_exercises_to_absExerciseDetailFragment4;
            case 5:
                return R.id.action_nav_abs_exercises_to_absExerciseDetailFragment5;
            case 6:
                return R.id.action_nav_abs_exercises_to_absExerciseDetailFragment6;
            case 7:
                return R.id.action_nav_abs_exercises_to_absExerciseDetailFragment7;
            case 8:
                return R.id.action_nav_abs_exercises_to_absExerciseDetailFragment8;
            case 9:
                return R.id.action_nav_abs_exercises_to_absExerciseDetailFragment9;
            default:
                return R.id.action_nav_abs_exercises_to_absExerciseDetailFragment1;
        }
    }

    private void filter(String text) {
        List<AbsExerciseItem> filteredList = new ArrayList<>();
        
        for (AbsExerciseItem item : exerciseListFull) {
            if (item.getTitle().toLowerCase(Locale.getDefault())
                    .contains(text.toLowerCase(Locale.getDefault()))) {
                filteredList.add(item);
            }
        }
        
        adapter.filterList(filteredList);
    }
} 