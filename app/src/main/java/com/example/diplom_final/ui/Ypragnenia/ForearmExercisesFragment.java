package com.example.diplom_final.ui.Ypragnenia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.diplom_final.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForearmExercisesFragment extends Fragment {
    private RecyclerView recyclerView;
    private ForearmExerciseAdapter adapter;
    private List<ForearmExerciseItem> exerciseList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_forearm_exercises, container, false);
        
        recyclerView = root.findViewById(R.id.recyclerViewForearmExercises);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        exerciseList = new ArrayList<>();
        
        // Подъём груза на вращающейся балке (разгибатели)
        addExercise(
            1,
            "Подъём груза на вращающейся балке (разгибатели)",
            "Эффективное упражнение для развития разгибателей предплечья, которое позволяет изолированно прорабатывать мышцы.",
            "1. Возьмитесь за вращающуюся балку хватом сверху\n" +
            "2. Исходное положение - руки опущены, балка с грузом внизу\n" +
            "3. На выдохе поднимите груз, разгибая кисти\n" +
            "4. На вдохе медленно опустите груз в исходное положение",
            "3-4 подхода по 12-15 повторений\n" +
            "Отдых между подходами 60-90 секунд\n" +
            "Выполнять в начале или середине тренировки",
            "• Травмы запястий и локтевых суставов\n" +
            "• Острые воспаления суставов\n" +
            "• Тендинит",
            Arrays.asList(
                "Разгибание кисти с гантелью",
                "Разгибание кисти на нижнем блоке",
                "Разгибания кисти со штангой"
            ),
            "https://www.youtube.com/watch?v=example1",
            R.drawable.forearm_exercise1
        );

        // Подъём груза на вращающейся балке (сгибатели)
        addExercise(
            2,
            "Подъём груза на вращающейся балке (сгибатели)",
            "Базовое упражнение для развития сгибателей предплечья, обеспечивающее изолированную нагрузку на целевые мышцы.",
            "1. Возьмитесь за вращающуюся балку хватом снизу\n" +
            "2. Исходное положение - руки опущены, балка с грузом внизу\n" +
            "3. На выдохе поднимите груз, сгибая кисти\n" +
            "4. На вдохе медленно опустите груз в исходное положение",
            "3-4 подхода по 12-15 повторений\n" +
            "Отдых между подходами 60-90 секунд\n" +
            "Выполнять в начале тренировки предплечий",
            "• Травмы запястий\n" +
            "• Туннельный синдром\n" +
            "• Воспаление сухожилий",
            Arrays.asList(
                "Сгибание кисти с гантелью",
                "Сгибание кисти на нижнем блоке",
                "Сгибания кисти со штангой"
            ),
            "https://www.youtube.com/watch?v=example2",
            R.drawable.forearm_exercise2
        );
        
        // Разгибание кисти в кроссовере стоя
        addExercise(
            3,
            "Разгибание кисти в кроссовере стоя",
            "Изолирующее упражнение для разгибателей запястья, которое позволяет точно контролировать нагрузку и амплитуду движения.",
            "1. Встаньте лицом к нижнему блоку\n" +
            "2. Возьмите рукоятку хватом снизу\n" +
            "3. Предплечье параллельно полу, локоть прижат к корпусу\n" +
            "4. На выдохе разогните кисть, поднимая рукоятку\n" +
            "5. На вдохе плавно вернитесь в исходное положение",
            "3-4 подхода по 12-15 повторений\n" +
            "Отдых между подходами 45-60 секунд\n" +
            "Темп выполнения средний, без рывков",
            "• Острые травмы запястья\n" +
            "• Воспаление сухожилий разгибателей\n" +
            "• Артрит лучезапястного сустава",
            Arrays.asList(
                "Разгибание кисти с гантелью",
                "Разгибание кисти на верхнем блоке",
                "Разгибание кисти со штангой"
            ),
            "https://www.youtube.com/watch?v=example3",
            R.drawable.forearm_exercise3
        );

        // Разгибание кисти в нижнем блоке
        addExercise(
            4,
            "Разгибание кисти в нижнем блоке (локоть под углом 90°)",
            "Эффективное упражнение для развития разгибателей запястья с фиксированным положением локтя для лучшей изоляции целевых мышц.",
            "1. Установите локоть на скамью под углом 90 градусов\n" +
            "2. Возьмите рукоятку нижнего блока хватом снизу\n" +
            "3. На выдохе разогните кисть максимально вверх\n" +
            "4. Задержитесь на секунду в верхней точке\n" +
            "5. На вдохе плавно опустите кисть вниз",
            "4 подхода по 12-15 повторений\n" +
            "Отдых между подходами 60 секунд\n" +
            "Выполнять после базовых упражнений",
            "• Травмы лучезапястного сустава\n" +
            "• Тендинит разгибателей\n" +
            "• Нестабильность локтевого сустава",
            Arrays.asList(
                "Разгибание кисти с эспандером",
                "Разгибание кисти с резиновой лентой",
                "Статические удержания в разгибании"
            ),
            "https://www.youtube.com/watch?v=example4",
            R.drawable.forearm_exercise4
        );
        
        // Разгибание кисти с гантелью в упоре
        addExercise(
            5,
            "Разгибание кисти с гантелью в упоре",
            "Классическое упражнение для изолированной проработки разгибателей запястья с использованием гантели.",
            "1. Положите предплечье на скамью, кисть свисает с края\n" +
            "2. Возьмите гантель хватом снизу\n" +
            "3. На выдохе разогните кисть вверх\n" +
            "4. На вдохе плавно опустите гантель\n" +
            "5. Следите за полной амплитудой движения",
            "3-4 подхода по 15-20 повторений\n" +
            "Отдых 45-60 секунд\n" +
            "Рекомендуется выполнять в конце тренировки",
            "• Острые боли в запястье\n" +
            "• Синдром запястного канала\n" +
            "• Воспаление сухожилий",
            Arrays.asList(
                "Разгибание кисти на блоке",
                "Разгибание кисти со штангой",
                "Разгибание кисти с эспандером"
            ),
            "https://www.youtube.com/watch?v=example5",
            R.drawable.forearm_exercise5
        );
        
        // Сгибание кистей со штангой в упоре
        addExercise(
            6,
            "Сгибание кистей со штангой в упоре",
            "Базовое упражнение для развития силы сгибателей запястья, позволяющее использовать значительные веса.",
            "1. Сядьте на скамью, предплечья на бедрах\n" +
            "2. Возьмите штангу хватом снизу\n" +
            "3. Кисти выступают за колени\n" +
            "4. На выдохе поднимите штангу, сгибая кисти\n" +
            "5. На вдохе медленно опустите штангу",
            "4 подхода по 12-15 повторений\n" +
            "Отдых между подходами 60-90 секунд\n" +
            "Выполнять в начале тренировки предплечий",
            "• Туннельный синдром\n" +
            "• Травмы связок запястья\n" +
            "• Артрит лучезапястного сустава",
            Arrays.asList(
                "Сгибание кисти с гантелями",
                "Сгибание на блоке",
                "Сгибание с гирей"
            ),
            "https://www.youtube.com/watch?v=example6",
            R.drawable.forearm_exercise6
        );
        
        // Сгибание кистей со штангой за спиной стоя
        addExercise(
            7,
            "Сгибание кистей со штангой за спиной стоя",
            "Продвинутый вариант упражнения для сгибателей запястья, требующий хорошей координации и баланса.",
            "1. Встаньте прямо, держа штангу за спиной хватом снизу\n" +
            "2. Локти прижаты к корпусу\n" +
            "3. На выдохе согните кисти, поднимая штангу\n" +
            "4. Задержитесь на секунду в верхней точке\n" +
            "5. На вдохе плавно опустите штангу",
            "3 подхода по 12-15 повторений\n" +
            "Отдых 60-90 секунд\n" +
            "Рекомендуется для опытных атлетов",
            "• Проблемы с плечевыми суставами\n" +
            "• Травмы запястий\n" +
            "• Нестабильность позвоночника",
            Arrays.asList(
                "Сгибание кистей со штангой в упоре",
                "Сгибание кистей с гантелями",
                "Сгибание на нижнем блоке"
            ),
            "https://www.youtube.com/watch?v=example7",
            R.drawable.forearm_exercise7
        );
        
        // Сгибание кисти с гантелью в упоре
        addExercise(
            8,
            "Сгибание кисти с гантелью в упоре",
            "Изолирующее упражнение для сгибателей запястья, позволяющее прорабатывать каждую руку отдельно.",
            "1. Положите предплечье на скамью, кисть свисает с края\n" +
            "2. Возьмите гантель хватом сверху\n" +
            "3. На выдохе согните кисть, поднимая гантель\n" +
            "4. В верхней точке сделайте паузу 1-2 секунды\n" +
            "5. На вдохе плавно опустите гантель",
            "3-4 подхода по 15-20 повторений\n" +
            "Отдых между подходами 45-60 секунд\n" +
            "Выполнять после базовых упражнений",
            "• Острые травмы запястья\n" +
            "• Воспаление сухожилий сгибателей\n" +
            "• Карпальный туннельный синдром",
            Arrays.asList(
                "Сгибание кисти на блоке",
                "Сгибание кисти со штангой",
                "Сгибание кисти с эспандером"
            ),
            "https://www.youtube.com/watch?v=example8",
            R.drawable.forearm_exercise8
        );

        adapter = new ForearmExerciseAdapter(exerciseList, this::navigateToExerciseDetail);
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

        ForearmExerciseItem exercise = new ForearmExerciseItem(title, description, imageResourceId);
        exercise.setId(id);
        exercise.setExtraData(exerciseData);
        exerciseList.add(exercise);
    }

    private void navigateToExerciseDetail(ForearmExerciseItem exercise) {
        Navigation.findNavController(requireView())
            .navigate(getNavigationActionId(exercise.getId()), exercise.getExtraData());
    }

    private int getNavigationActionId(int exerciseId) {
        switch (exerciseId) {
            case 1:
                return R.id.action_nav_forearm_exercises_to_forearmExerciseDetailFragment1;
            case 2:
                return R.id.action_nav_forearm_exercises_to_forearmExerciseDetailFragment2;
            case 3:
                return R.id.action_nav_forearm_exercises_to_forearmExerciseDetailFragment3;
            case 4:
                return R.id.action_nav_forearm_exercises_to_forearmExerciseDetailFragment4;
            case 5:
                return R.id.action_nav_forearm_exercises_to_forearmExerciseDetailFragment5;
            case 6:
                return R.id.action_nav_forearm_exercises_to_forearmExerciseDetailFragment6;
            case 7:
                return R.id.action_nav_forearm_exercises_to_forearmExerciseDetailFragment7;
            case 8:
                return R.id.action_nav_forearm_exercises_to_forearmExerciseDetailFragment8;
            default:
                return R.id.action_nav_forearm_exercises_to_forearmExerciseDetailFragment1;
        }
    }
} 