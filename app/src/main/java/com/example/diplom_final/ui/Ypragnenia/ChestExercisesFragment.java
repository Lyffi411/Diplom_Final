package com.example.diplom_final.ui.Ypragnenia;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.R;
import com.example.diplom_final.databinding.FragmentChestExercisesBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChestExercisesFragment extends Fragment {
    private FragmentChestExercisesBinding binding;
    private RecyclerView recyclerView;
    private EditText searchEditText;
    private List<ChestExerciseItem> items;
    private ChestExerciseAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChestExercisesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.recyclerViewChestExercises;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        searchEditText = binding.searchEditText;
        setupSearch();

        initializeItems();

        adapter = new ChestExerciseAdapter(items, position -> {
            ChestExerciseItem item = adapter.getFilteredItems().get(position);
            navigateToDetail(item);
        });

        recyclerView.setAdapter(adapter);

        return root;
    }

    private void setupSearch() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                InputMethodManager imm = (InputMethodManager) requireContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        });
    }

    private void initializeItems() {
        items = new ArrayList<>();
        
        items.add(new ChestExerciseItem(
                "Жим Свенда",
                "Эффективное упражнение для развития внутренней части грудных мышц",
                R.drawable.chest_svend_press,
                "1. Возьмите блин от штанги или диск\n" +
                "2. Зажмите его между ладонями на уровне груди\n" +
                "3. Выжимайте блин вперед, сохраняя давление ладоней\n" +
                "4. Медленно верните в исходное положение",
                "https://youtu.be/example1",
                "Подходы: 3-4\nПовторения: 12-15\nОтдых между подходами: 60-90 секунд",
                "• Травмы плечевых суставов\n• Проблемы с запястьями",
                Arrays.asList("Сведение рук в тренажере", "Отжимания от пола")
        ));
        
        items.add(new ChestExerciseItem(
                "Жим Свенда с Т-грифом",
                "Вариация классического жима Свенда с использованием специального грифа",
                R.drawable.chest_svend_tbar,
                "1. Установите Т-гриф на нужной высоте\n" +
                "2. Возьмитесь за рукояти\n" +
                "3. Выполняйте жим от груди\n" +
                "4. Контролируйте движение на всей амплитуде",
                "https://youtu.be/example2",
                "Подходы: 3-4\nПовторения: 10-12\nОтдых между подходами: 90-120 секунд",
                "• Травмы плеч\n• Проблемы с позвоночником",
                Arrays.asList("Жим в тренажере", "Жим гантелей")
        ));
        
        items.add(new ChestExerciseItem(
                "Жим Свенда с гантелями лёжа",
                "Упражнение для глубокой проработки грудных мышц",
                R.drawable.chest_svend_dumbbells,
                "1. Лягте на скамью\n" +
                "2. Возьмите гантели и удерживайте их вместе на груди\n" +
                "3. Выжимайте гантели вверх, сохраняя давление между ними\n" +
                "4. Плавно опустите вниз",
                "https://youtu.be/example3",
                "Подходы: 3\nПовторения: 12-15\nОтдых между подходами: 60 секунд",
                "• Травмы плечевого пояса\n• Проблемы с локтевыми суставами",
                Arrays.asList("Жим гантелей лежа", "Разведение гантелей")
        ));
        
        items.add(new ChestExerciseItem(
                "Жим в кроссовере горизонтально",
                "Изолированное упражнение для проработки внешней части груди",
                R.drawable.chest_crossover_horizontal,
                "1. Установите блоки на среднюю высоту\n" +
                "2. Возьмитесь за рукояти\n" +
                "3. Выполняйте жим перед собой\n" +
                "4. Контролируйте обратное движение",
                "https://youtu.be/example4",
                "Подходы: 3-4\nПовторения: 12-15\nОтдых между подходами: 60 секунд",
                "• Нестабильность плечевых суставов\n• Травмы груди",
                Arrays.asList("Жим гантелей", "Сведение рук в тренажере")
        ));
        
        items.add(new ChestExerciseItem(
                "Жим в рычажном тренажере",
                "Безопасное упражнение для начинающих",
                R.drawable.chest_lever_press,
                "1. Отрегулируйте сиденье\n" +
                "2. Возьмитесь за рукояти\n" +
                "3. Выжимайте вес от груди\n" +
                "4. Медленно возвращайтесь в исходное положение",
                "https://youtu.be/example5",
                "Подходы: 3-4\nПовторения: 12-15\nОтдых между подходами: 60-90 секунд",
                "• Серьезные травмы плеч\n• Проблемы с грудным отделом позвоночника",
                Arrays.asList("Жим гантелей", "Отжимания от пола")
        ));
        
        items.add(new ChestExerciseItem(
                "Жим в тренажере одной рукой",
                "Упражнение для устранения мышечного дисбаланса",
                R.drawable.chest_machine_single,
                "1. Настройте сиденье тренажера\n" +
                "2. Возьмитесь одной рукой за рукоять\n" +
                "3. Выполняйте жим от груди\n" +
                "4. Повторите для другой руки",
                "https://youtu.be/example6",
                "Подходы: 3\nПовторения: 12-15 на каждую руку\nОтдых между подходами: 60 секунд",
                "• Серьезная асимметрия мышц\n• Травмы плечевого сустава",
                Arrays.asList("Жим гантели одной рукой", "Отжимания с упором на одну руку")
        ));
        
        items.add(new ChestExerciseItem(
                "Жим в тренажере от груди вверх",
                "Акцентированная работа верхней части грудных мышц",
                R.drawable.chest_machine_up,
                "1. Установите спинку под углом 30-45 градусов\n" +
                "2. Возьмитесь за рукояти\n" +
                "3. Выполняйте жим вверх\n" +
                "4. Контролируйте опускание веса",
                "https://youtu.be/example7",
                "Подходы: 4\nПовторения: 10-12\nОтдых между подходами: 90 секунд",
                "• Проблемы с шейным отделом\n• Травмы плеч",
                Arrays.asList("Жим гантелей на наклонной скамье", "Сведение рук в кроссовере вверх")
        ));
        
        items.add(new ChestExerciseItem(
                "Жим в тренажере от груди вперед",
                "Базовое упражнение для всей груди",
                R.drawable.chest_machine_forward,
                "1. Отрегулируйте сиденье\n" +
                "2. Возьмитесь за рукояти на уровне груди\n" +
                "3. Выжимайте вес прямо перед собой\n" +
                "4. Плавно возвращайтесь в исходное положение",
                "https://youtu.be/example8",
                "Подходы: 4\nПовторения: 12-15\nОтдых между подходами: 60-90 секунд",
                "• Травмы грудных мышц\n• Проблемы с плечевыми суставами",
                Arrays.asList("Жим штанги лежа", "Отжимания от пола")
        ));
        
        items.add(new ChestExerciseItem(
                "Жим гантелей на горизонтальной скамье",
                "Классическое упражнение для развития груди",
                R.drawable.chest_dumbbells_flat,
                "1. Лягте на горизонтальную скамью\n" +
                "2. Возьмите гантели на вытянутые руки\n" +
                "3. Опустите гантели к груди\n" +
                "4. Выжмите вверх",
                "https://youtu.be/example9",
                "Подходы: 4\nПовторения: 8-12\nОтдых между подходами: 90-120 секунд",
                "• Травмы плечевого пояса\n• Проблемы с запястьями",
                Arrays.asList("Жим штанги лежа", "Отжимания от пола с весом")
        ));
        
        items.add(new ChestExerciseItem(
                "Жим гантелей на наклонной (30°) скамье вверх",
                "Упражнение для верхней части грудных мышц",
                R.drawable.chest_dumbbells_incline30,
                "1. Установите скамью под углом 30 градусов\n" +
                "2. Лягте на скамью\n" +
                "3. Выполняйте жим гантелей\n" +
                "4. Контролируйте движение",
                "https://youtu.be/example10",
                "Подходы: 4\nПовторения: 10-12\nОтдых между подходами: 90 секунд",
                "• Проблемы с шейным отделом\n• Травмы плечевых суставов",
                Arrays.asList("Жим штанги на наклонной скамье", "Сведение рук в кроссовере вверх")
        ));
        
        items.add(new ChestExerciseItem(
                "Жим гантелей на наклонной скамье вверх с супинацией",
                "Упражнение с разворотом кистей",
                R.drawable.chest_supination_press,
                "1. Лягте на наклонную скамью\n" +
                "2. Возьмите гантели нейтральным хватом\n" +
                "3. При движении вверх разворачивайте кисти наружу\n" +
                "4. Возвращайтесь в исходное положение с нейтральным хватом",
                "https://youtu.be/example_supination",
                "Подходы: 3-4\nПовторения: 10-12\nОтдых между подходами: 90 секунд",
                "• Проблемы с запястьями\n• Травмы плечевых суставов\n• Нестабильность локтевых суставов",
                Arrays.asList("Жим гантелей на наклонной скамье", "Жим штанги на наклонной скамье")
        ));
        
        items.add(new ChestExerciseItem(
                "Жим гантелей на наклонной скамье вниз",
                "Акцент на нижнюю часть грудных мышц",
                R.drawable.chest_decline_dumbbell_press,
                "1. Установите скамью под отрицательным углом\n" +
                "2. Надежно зафиксируйте ноги\n" +
                "3. Опускайте гантели к нижней части груди\n" +
                "4. Выжимайте гантели вверх, сохраняя контроль",
                "https://youtu.be/example_decline",
                "Подходы: 3-4\nПовторения: 10-12\nОтдых между подходами: 90 секунд",
                "• Проблемы с шеей\n• Головокружения\n• Высокое давление",
                Arrays.asList("Жим штанги на наклонной вниз скамье", "Отжимания на брусьях")
        ));
        
        items.add(new ChestExerciseItem(
                "Жим лёжа в кроссовере",
                "Изолированное упражнение лежа",
                R.drawable.chest_cable_bench_press,
                "1. Установите скамью между тросами кроссовера\n" +
                "2. Возьмитесь за нижние рукояти\n" +
                "3. Выполняйте жим от груди вверх\n" +
                "4. Контролируйте движение на всей амплитуде",
                "https://youtu.be/example_cable_press",
                "Подходы: 3\nПовторения: 12-15\nОтдых между подходами: 60 секунд",
                "• Нестабильность плечевых суставов\n• Травмы груди\n• Проблемы с координацией",
                Arrays.asList("Жим гантелей лежа", "Сведение рук в кроссовере")
        ));
        
        items.add(new ChestExerciseItem(
                "Жим лёжа в рычажном тренажёре",
                "Безопасное упражнение на тренажере",
                R.drawable.chest_machine_press,
                "1. Отрегулируйте сиденье и спинку тренажера\n" +
                "2. Возьмитесь за рукояти на уровне груди\n" +
                "3. Выжимайте вес от себя\n" +
                "4. Плавно возвращайтесь в исходное положение",
                "https://youtu.be/example_machine_press",
                "Подходы: 3-4\nПовторения: 12-15\nОтдых между подходами: 60-90 секунд",
                "• Серьезные травмы плеч\n• Проблемы с локтевыми суставами\n• Ограничения в подвижности",
                Arrays.asList("Жим гантелей лежа", "Жим штанги лежа")
        ));
        
        items.add(new ChestExerciseItem(
                "Жим штанги лежа на горизонтальной скамье",
                "Базовое упражнение для развития силы и массы грудных мышц",
                R.drawable.chest_flat_bench_press,
                "1. Лягте на скамью, поставив ноги на пол\n" +
                "2. Возьмите штангу хватом чуть шире плеч\n" +
                "3. Опустите штангу к средней части груди\n" +
                "4. Выжмите штангу вверх",
                "https://youtu.be/example16",
                "Подходы: 4-5\nПовторения: 6-12\nОтдых между подходами: 120-180 секунд",
                "• Травмы плечевых суставов\n• Проблемы с позвоночником\n• Травмы локтей",
                Arrays.asList("Жим гантелей лежа", "Жим в тренажере")
        ));
        
        items.add(new ChestExerciseItem(
                "Жим штанги лежа на наклонной вниз скамье",
                "Упражнение для нижней части грудных мышц",
                R.drawable.chest_decline_barbell_press,
                "1. Установите скамью под отрицательным углом\n" +
                "2. Надежно зафиксируйте ноги\n" +
                "3. Опускайте штангу к нижней части груди\n" +
                "4. Выжимайте штангу вверх",
                "https://youtu.be/example17",
                "Подходы: 3-4\nПовторения: 8-12\nОтдых между подходами: 90-120 секунд",
                "• Проблемы с шеей\n• Высокое давление\n• Головокружения",
                Arrays.asList("Отжимания на брусьях", "Жим гантелей на наклонной вниз скамье")
        ));
        
        items.add(new ChestExerciseItem(
                "Жим штанги на наклонной скамье вверх",
                "Упражнение для верхней части грудных мышц",
                R.drawable.chest_incline_barbell_press,
                "1. Установите скамью под углом 30-45 градусов\n" +
                "2. Возьмите штангу хватом чуть шире плеч\n" +
                "3. Опускайте штангу к верхней части груди\n" +
                "4. Выжимайте вверх до полного выпрямления рук",
                "https://youtu.be/example18",
                "Подходы: 4\nПовторения: 8-12\nОтдых между подходами: 90-120 секунд",
                "• Травмы плеч\n• Проблемы с шейным отделом",
                Arrays.asList("Жим гантелей на наклонной скамье", "Жим в тренажере вверх")
        ));
        
        items.add(new ChestExerciseItem(
                "Пуловер с гантелью",
                "Упражнение для растяжки грудных мышц и развития зубчатых мышц",
                R.drawable.chest_dumbbell_pullover,
                "1. Лягте поперек скамьи\n" +
                "2. Возьмите гантель двумя руками\n" +
                "3. Опустите руки за голову\n" +
                "4. Поднимите гантель обратно по дуге",
                "https://youtu.be/example19",
                "Подходы: 3\nПовторения: 12-15\nОтдых между подходами: 60 секунд",
                "• Проблемы с плечевыми суставами\n• Травмы спины",
                Arrays.asList("Пуловер со штангой", "Пуловер в тренажере")
        ));
        
        items.add(new ChestExerciseItem(
                "Пуловер с гантелью лежа на горизонтальной скамье",
                "Изолирующее упражнение для растяжки грудных мышц",
                R.drawable.chest_bench_pullover,
                "1. Лягте на скамью\n" +
                "2. Возьмите гантель двумя руками над грудью\n" +
                "3. Опустите гантель за голову\n" +
                "4. Верните в исходное положение",
                "https://youtu.be/example20",
                "Подходы: 3\nПовторения: 12-15\nОтдых между подходами: 60 секунд",
                "• Травмы плечевого пояса\n• Проблемы с позвоночником",
                Arrays.asList("Пуловер с прямыми руками в кроссовере", "Пуловер в тренажере")
        ));
        
        items.add(new ChestExerciseItem(
                "Разведение гантелей на горизонтальной скамье",
                "Изолирующее упражнение для растяжки грудных мышц",
                R.drawable.chest_dumbbell_flyes,
                "1. Лягте на горизонтальную скамью\n" +
                "2. Держите гантели над грудью\n" +
                "3. Разведите руки в стороны\n" +
                "4. Сведите гантели обратно над грудью",
                "https://youtu.be/example21",
                "Подходы: 3-4\nПовторения: 12-15\nОтдых между подходами: 60 секунд",
                "• Травмы плечевых суставов\n• Растяжение грудных мышц",
                Arrays.asList("Сведение рук в тренажере", "Сведение рук в кроссовере")
        ));
        
        items.add(new ChestExerciseItem(
                "Сведение рук в кроссовере лежа на наклонной вниз скамье",
                "Упражнение для нижней части грудных мышц",
                R.drawable.chest_decline_cable_flyes,
                "1. Установите скамью под отрицательным углом между блоками\n" +
                "2. Возьмитесь за рукояти\n" +
                "3. Сведите руки перед грудью\n" +
                "4. Медленно разведите обратно",
                "https://youtu.be/example22",
                "Подходы: 3\nПовторения: 12-15\nОтдых между подходами: 60 секунд",
                "• Проблемы с шеей\n• Высокое давление",
                Arrays.asList("Разведение гантелей на наклонной скамье", "Жим на наклонной скамье вниз")
        ));
        
        items.add(new ChestExerciseItem(
                "Сведение рук в кроссовере лежа на наклонной скамье",
                "Упражнение для верхней части грудных мышц",
                R.drawable.chest_incline_cable_flyes,
                "1. Установите скамью под углом 30-45 градусов\n" +
                "2. Возьмитесь за нижние блоки\n" +
                "3. Сведите руки над грудью\n" +
                "4. Плавно вернитесь в исходное положение",
                "https://youtu.be/example23",
                "Подходы: 3\nПовторения: 12-15\nОтдых между подходами: 60 секунд",
                "• Травмы плеч\n• Проблемы с шейным отделом",
                Arrays.asList("Разведение гантелей на наклонной скамье", "Жим на наклонной скамье")
        ));
        
        items.add(new ChestExerciseItem(
                "Сведение рук в кроссовере сверху стоя",
                "Изолирующее упражнение для внутренней части груди",
                R.drawable.chest_standing_cable_flyes,
                "1. Встаньте между блоками\n" +
                "2. Возьмитесь за верхние рукояти\n" +
                "3. Сведите руки перед собой\n" +
                "4. Контролируйте обратное движение",
                "https://youtu.be/example24",
                "Подходы: 3-4\nПовторения: 12-15\nОтдых между подходами: 60 секунд",
                "• Травмы плечевых суставов\n• Проблемы с позвоночником",
                Arrays.asList("Сведение рук в тренажере", "Разведение гантелей лежа")
        ));
        
        items.add(new ChestExerciseItem(
                "Сведение рук в кроссовере сверху стоя в наклоне",
                "Упражнение для нижней части грудных мышц",
                R.drawable.chest_low_cable_flyes,
                "1. Встаньте между блоками в наклоне\n" +
                "2. Возьмитесь за верхние рукояти\n" +
                "3. Сведите руки внизу\n" +
                "4. Медленно разведите руки",
                "https://youtu.be/example25",
                "Подходы: 3\nПовторения: 12-15\nОтдых между подходами: 60 секунд",
                "• Проблемы с поясницей\n• Травмы плеч",
                Arrays.asList("Разведение гантелей в наклоне", "Сведение рук в тренажере")
        ));
        
        items.add(new ChestExerciseItem(
                "Сведение рук в кроссовере снизу",
                "Упражнение для верхней части грудных мышц",
                R.drawable.chest_low_cable_flyes,
                "1. Встаньте между блоками\n" +
                "2. Возьмитесь за нижние рукояти\n" +
                "3. Сведите руки вверх и перед собой\n" +
                "4. Плавно вернитесь в исходное положение",
                "https://youtu.be/example26",
                "Подходы: 3\nПовторения: 12-15\nОтдых между подходами: 60 секунд",
                "• Травмы плечевых суставов\n• Проблемы с шеей",
                Arrays.asList("Сведение рук в тренажере", "Жим гантелей на наклонной скамье")
        ));
        
        items.add(new ChestExerciseItem(
                "Сведение рук в тренажере",
                "Изолирующее упражнение для грудных мышц",
                R.drawable.chest_pec_deck,
                "1. Настройте сиденье\n" +
                "2. Возьмитесь за рукояти\n" +
                "3. Сведите руки перед грудью\n" +
                "4. Медленно разведите руки",
                "https://youtu.be/example27",
                "Подходы: 3-4\nПовторения: 12-15\nОтдых между подходами: 60 секунд",
                "• Травмы плечевых суставов\n• Проблемы с грудными мышцами",
                Arrays.asList("Разведение гантелей", "Сведение рук в кроссовере")
        ));
        
        items.add(new ChestExerciseItem(
                "Сведение рук в тренажёре по одной руке",
                "Упражнение для коррекции асимметрии грудных мышц",
                R.drawable.chest_pec_deck,
                "1. Сядьте в тренажер боком\n" +
                "2. Возьмитесь за рукоять одной рукой\n" +
                "3. Выполните сведение руки к центру\n" +
                "4. Повторите для другой руки",
                "https://youtu.be/example28",
                "Подходы: 3\nПовторения: 12-15 на каждую руку\nОтдых между подходами: 60 секунд",
                "• Серьезная асимметрия мышц\n• Травмы плечевого сустава",
                Arrays.asList("Разведение одной гантели лежа", "Сведение одной руки в кроссовере")
        ));

        items.add(new ChestExerciseItem(
                "Жим гантелей на наклонной вверх (45°) скамье",
                "Интенсивная проработка верхней части грудных мышц",
                R.drawable.chest_incline_dumbbell_press,
                "1. Установите скамью под углом 45 градусов\n" +
                "2. Лягте, прижав спину к скамье\n" +
                "3. Выжимайте гантели вверх\n" +
                "4. Опускайте до уровня груди",
                "https://youtu.be/example11",
                "Подходы: 4\nПовторения: 8-12\nОтдых между подходами: 90 секунд",
                "• Боли в шейном отделе\n• Травмы плечевого пояса",
                Arrays.asList("Жим штанги на наклонной скамье", "Жим в тренажере вверх")
        ));
    }

    private void navigateToDetail(ChestExerciseItem item) {
        Bundle bundle = new Bundle();
        bundle.putString("exerciseTitle", item.getTitle());
        bundle.putString("exerciseDescription", item.getDescription());
        bundle.putInt("exerciseImage", item.getImageResourceId());
        bundle.putString("technique", item.getTechnique());
        bundle.putString("videoUrl", item.getVideoUrl());
        bundle.putString("sets", item.getSets());
        bundle.putString("contraindications", item.getContraindications());
        bundle.putStringArrayList("alternatives", new ArrayList<>(item.getAlternatives()));

        Navigation.findNavController(requireView())
                .navigate(R.id.action_chest_exercises_to_detail, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        recyclerView = null;
        searchEditText = null;
        items = null;
        adapter = null;
    }
} 