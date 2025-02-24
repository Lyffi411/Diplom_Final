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
import com.example.diplom_final.databinding.FragmentBackBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackExercisesFragment extends Fragment {
    private FragmentBackBinding binding;
    private RecyclerView recyclerView;
    private EditText searchEditText;
    private List<BackExerciseItem> items;
    private BackExerciseAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBackBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.recyclerViewBackExercises;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        searchEditText = binding.searchEditText;
        setupSearch();

        initializeItems();

        adapter = new BackExerciseAdapter(items, position -> {
            BackExerciseItem item = adapter.getFilteredItems().get(position);
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

        items.add(new BackExerciseItem(
                "Вертикальная тяга блока за голову",
                "Базовое упражнение для развития широчайших мышц спины",
                R.drawable.back_lat_pulldown_behind,
                "1. Сядьте на тренажер, зафиксировав ноги\n" +
                "2. Возьмитесь за гриф широким хватом\n" +
                "3. Тяните гриф за голову до касания шеи\n" +
                "4. Плавно возвращайтесь в исходное положение",
                "https://youtu.be/example1",
                "Подходы: 3-4\nПовторения: 10-12\nОтдых между подходами: 90 секунд",
                "• Травмы плечевых суставов\n• Проблемы с шейным отделом",
                Arrays.asList("Тяга к груди", "Подтягивания")
        ));

        items.add(new BackExerciseItem(
                "Вертикальная тяга параллельным узким хватом к груди",
                "Упражнение для проработки центральной части спины",
                R.drawable.back_narrow_grip_pulldown,
                "1. Сядьте на тренажер\n" +
                "2. Возьмитесь за рукояти узким хватом\n" +
                "3. Тяните к верхней части груди\n" +
                "4. Медленно возвращайтесь в исходное положение",
                "https://youtu.be/example2",
                "Подходы: 3-4\nПовторения: 10-12\nОтдых между подходами: 90 секунд",
                "• Травмы плеч\n• Проблемы с локтями",
                Arrays.asList("Тяга широким хватом", "Тяга в тренажере")
        ));

        items.add(new BackExerciseItem(
                "Вертикальная тяга параллельным широким хватом к груди",
                "Эффективное упражнение для развития широчайших мышц",
                R.drawable.back_wide_grip_pulldown,
                "1. Сядьте на тренажер\n" +
                "2. Возьмитесь за широкие параллельные рукояти\n" +
                "3. Тяните вес к верхней части груди\n" +
                "4. Контролируйте движение при возврате",
                "https://youtu.be/example3",
                "Подходы: 3-4\nПовторения: 10-12\nОтдых между подходами: 90 секунд",
                "• Травмы плечевых суставов\n• Проблемы с позвоночником",
                Arrays.asList("Тяга узким хватом", "Подтягивания широким хватом")
        ));

        items.add(new BackExerciseItem(
                "Горизонтальная рычажная тяга в тренажере",
                "Базовое упражнение для середины спины",
                R.drawable.back_horizontal_row_machine,
                "1. Сядьте в тренажер\n" +
                "2. Возьмитесь за рукояти\n" +
                "3. Тяните вес к животу\n" +
                "4. Плавно возвращайте в исходное положение",
                "https://youtu.be/example4",
                "Подходы: 3-4\nПовторения: 12-15\nОтдых между подходами: 60-90 секунд",
                "• Травмы поясницы\n• Проблемы с плечами",
                Arrays.asList("Тяга в наклоне", "Тяга на нижнем блоке")
        ));

        items.add(new BackExerciseItem(
                "Горизонтальная рычажная тяга одной рукой",
                "Упражнение для проработки баланса мышц спины",
                R.drawable.back_single_arm_row_machine,
                "1. Встаньте боком к тренажеру\n" +
                "2. Возьмитесь за рукоять одной рукой\n" +
                "3. Выполните тягу к поясу\n" +
                "4. Медленно верните в исходное положение",
                "https://youtu.be/example5",
                "Подходы: 3\nПовторения: 12-15 на каждую руку\nОтдых: 60 секунд",
                "• Асимметрия мышц\n• Травмы плеча",
                Arrays.asList("Тяга гантели в наклоне", "Тяга на блоке одной рукой")
        ));

        items.add(new BackExerciseItem(
                "Горизонтальная тяга блока сидя широким параллельным хватом",
                "Упражнение для общего развития мышц спины",
                R.drawable.back_seated_wide_row,
                "1. Сядьте перед блоком, упритесь ногами\n" +
                "2. Возьмитесь за рукоять широким хватом\n" +
                "3. Тяните к нижней части груди\n" +
                "4. Медленно возвращайтесь в исходное положение",
                "https://youtu.be/example6",
                "Подходы: 3-4\nПовторения: 12-15\nОтдых: 90 секунд",
                "• Травмы поясницы\n• Проблемы с плечами",
                Arrays.asList("Тяга узким хватом", "Тяга в тренажере")
        ));

        items.add(new BackExerciseItem(
                "Горизонтальная тяга блока сидя узким хватом",
                "Упражнение для проработки середины спины",
                R.drawable.back_seated_narrow_row,
                "1. Сядьте перед блоком\n" +
                "2. Возьмитесь за рукоять узким хватом\n" +
                "3. Тяните к животу\n" +
                "4. Контролируйте возврат",
                "https://youtu.be/example7",
                "Подходы: 3-4\nПовторения: 12-15\nОтдых: 90 секунд",
                "• Травмы локтей\n• Проблемы с запястьями",
                Arrays.asList("Тяга широким хватом", "Тяга гантелей")
        ));

        items.add(new BackExerciseItem(
                "Подтягивания в Гравитроне широким прямым хватом",
                "Базовое упражнение для начинающих",
                R.drawable.back_assisted_pullups,
                "1. Встаньте на платформу гравитрона\n" +
                "2. Возьмитесь за перекладину широким хватом\n" +
                "3. Подтянитесь до касания перекладины грудью\n" +
                "4. Плавно опуститесь вниз",
                "https://youtu.be/example8",
                "Подходы: 3-4\nПовторения: 8-12\nОтдых: 120 секунд",
                "• Травмы плеч\n• Проблемы с локтями",
                Arrays.asList("Тяга верхнего блока", "Подтягивания с резиной")
        ));

        items.add(new BackExerciseItem(
                "Тяга верхнего блока к шее",
                "Изолирующее упражнение для верхней части спины",
                R.drawable.back_high_row_neck,
                "1. Сядьте на тренажер\n" +
                "2. Возьмитесь за рукоять хватом сверху\n" +
                "3. Тяните к основанию шеи\n" +
                "4. Медленно возвращайтесь в исходное положение",
                "https://youtu.be/example9",
                "Подходы: 3\nПовторения: 12-15\nОтдых: 60 секунд",
                "• Проблемы с шейным отделом\n• Травмы плеч",
                Arrays.asList("Тяга к груди", "Тяга в наклоне")
        ));

        items.add(new BackExerciseItem(
                "Тяга верхнего блока стоя прямыми руками",
                "Изолирующее упражнение для широчайших мышц",
                R.drawable.back_straight_arm_pulldown,
                "1. Встаньте перед верхним блоком\n" +
                "2. Возьмитесь за рукоять прямыми руками\n" +
                "3. Тяните блок вниз до бедер\n" +
                "4. Плавно поднимите обратно",
                "https://youtu.be/example10",
                "Подходы: 3\nПовторения: 12-15\nОтдых: 60 секунд",
                "• Травмы плеч\n• Проблемы с локтями",
                Arrays.asList("Пуловер с гантелями", "Тяга за голову")
        ));

        items.add(new BackExerciseItem(
                "Тяга гантелей двумя руками на наклонной скамье",
                "Упражнение для развития толщины спины",
                R.drawable.back_incline_dumbbell_row,
                "1. Лягте грудью на наклонную скамью\n" +
                "2. Возьмите гантели в обе руки\n" +
                "3. Тяните гантели к нижним ребрам\n" +
                "4. Медленно опускайте вниз",
                "https://youtu.be/example11",
                "Подходы: 3-4\nПовторения: 10-12\nОтдых: 90 секунд",
                "• Травмы поясницы\n• Проблемы с плечами",
                Arrays.asList("Тяга штанги в наклоне", "Тяга в тренажере")
        ));

        items.add(new BackExerciseItem(
                "Тяга гантелей к поясу в наклоне",
                "Базовое упражнение для общего развития спины",
                R.drawable.back_bent_over_dumbbell_row,
                "1. Наклонитесь вперед, спина прямая\n" +
                "2. Возьмите гантели в руки\n" +
                "3. Тяните гантели к поясу\n" +
                "4. Контролируйте опускание",
                "https://youtu.be/example12",
                "Подходы: 3-4\nПовторения: 10-12\nОтдых: 90 секунд",
                "• Проблемы с поясницей\n• Травмы плеч",
                Arrays.asList("Тяга штанги", "Тяга в тренажере")
        ));

        items.add(new BackExerciseItem(
                "Тяга гантели одной рукой на скамье",
                "Упражнение для баланса и силы спины",
                R.drawable.back_single_arm_dumbbell_row,
                "1. Поставьте колено и руку на скамью\n" +
                "2. Возьмите гантель свободной рукой\n" +
                "3. Тяните гантель к поясу\n" +
                "4. Плавно опускайте вниз",
                "https://youtu.be/example13",
                "Подходы: 3\nПовторения: 10-12 на каждую руку\nОтдых: 60 секунд",
                "• Асимметрия мышц\n• Проблемы с поясницей",
                Arrays.asList("Тяга в тренажере одной рукой", "Тяга гантелей двумя руками")
        ));

        items.add(new BackExerciseItem(
                "Тяга Т-образного грифа",
                "Эффективное упражнение для массы спины",
                R.drawable.back_t_bar_row,
                "1. Встаньте над Т-грифом\n" +
                "2. Возьмитесь за рукояти\n" +
                "3. Тяните вес к груди\n" +
                "4. Контролируйте опускание",
                "https://youtu.be/example14",
                "Подходы: 4\nПовторения: 8-12\nОтдых: 120 секунд",
                "• Травмы поясницы\n• Проблемы с плечами",
                Arrays.asList("Тяга штанги в наклоне", "Тяга гантелей")
        ));

        items.add(new BackExerciseItem(
                "Тяга Т-образного грифа с опорой узким хватом",
                "Упражнение для проработки середины спины",
                R.drawable.back_supported_t_bar_row_narrow,
                "1. Расположитесь на опоре грудью\n" +
                "2. Возьмитесь за рукояти узким хватом\n" +
                "3. Тяните вес к нижней части груди\n" +
                "4. Медленно опускайте вниз",
                "https://youtu.be/example15",
                "Подходы: 3-4\nПовторения: 10-12\nОтдых: 90 секунд",
                "• Травмы плеч\n• Проблемы с локтями",
                Arrays.asList("Тяга в тренажере", "Тяга гантелей узким хватом")
        ));

        items.add(new BackExerciseItem(
                "Тяга Т-образного грифа с опорой широким хватом",
                "Упражнение для развития широчайших мышц",
                R.drawable.back_supported_t_bar_row_wide,
                "1. Расположитесь на опоре грудью\n" +
                "2. Возьмитесь за рукояти широким хватом\n" +
                "3. Тяните вес к груди\n" +
                "4. Контролируйте возврат",
                "https://youtu.be/example16",
                "Подходы: 3-4\nПовторения: 10-12\nОтдых: 90 секунд",
                "• Травмы плечевых суставов\n• Проблемы с позвоночником",
                Arrays.asList("Тяга штанги широким хватом", "Тяга в тренажере")
        ));

        items.add(new BackExerciseItem(
                "Пуловер на блоке с веревкой стоя",
                "Изолирующее упражнение для широчайших мышц",
                R.drawable.back_rope_pullover,
                "1. Встаньте спиной к блоку\n" +
                "2. Возьмите веревочную рукоять над головой\n" +
                "3. Опустите руки вниз и назад\n" +
                "4. Медленно вернитесь в исходное положение",
                "https://youtu.be/example17",
                "Подходы: 3\nПовторения: 12-15\nОтдых: 60 секунд",
                "• Травмы плеч\n• Проблемы с локтями",
                Arrays.asList("Пуловер с гантелями", "Тяга прямыми руками")
        ));

        items.add(new BackExerciseItem(
                "Пуловер на блоке с прямой ручкой стоя",
                "Упражнение для растяжения и развития широчайших",
                R.drawable.back_straight_bar_pullover,
                "1. Встаньте спиной к блоку\n" +
                "2. Возьмите прямую ручку над головой\n" +
                "3. Опустите руки дугой вниз\n" +
                "4. Плавно вернитесь вверх",
                "https://youtu.be/example18",
                "Подходы: 3\nПовторения: 12-15\nОтдых: 60 секунд",
                "• Травмы плечевых суставов\n• Проблемы с грудным отделом",
                Arrays.asList("Пуловер с веревкой", "Тяга прямыми руками")
        ));

        items.add(new BackExerciseItem(
                "Шраги с гантелями",
                "Упражнение для развития трапециевидных мышц",
                R.drawable.back_dumbbell_shrugs,
                "1. Встаньте прямо, гантели в руках\n" +
                "2. Поднимите плечи максимально вверх\n" +
                "3. Задержитесь на секунду\n" +
                "4. Медленно опустите плечи",
                "https://youtu.be/example19",
                "Подходы: 3-4\nПовторения: 15-20\nОтдых: 60 секунд",
                "• Травмы шеи\n• Проблемы с плечами",
                Arrays.asList("Шраги со штангой", "Шраги в тренажере")
        ));

        items.add(new BackExerciseItem(
                "Шраги со штангой за спиной",
                "Вариация для развития трапеций",
                R.drawable.back_behind_shrugs,
                "1. Возьмите штангу за спиной\n" +
                "2. Поднимите плечи вверх\n" +
                "3. Удержите верхнюю позицию\n" +
                "4. Плавно опустите плечи",
                "https://youtu.be/example20",
                "Подходы: 3\nПовторения: 12-15\nОтдых: 90 секунд",
                "• Проблемы с плечами\n• Травмы шейного отдела",
                Arrays.asList("Шраги с гантелями", "Шраги перед собой")
        ));

        items.add(new BackExerciseItem(
                "Шраги со штангой перед собой",
                "Классическое упражнение для трапеций",
                R.drawable.back_front_shrugs,
                "1. Возьмите штангу перед собой\n" +
                "2. Поднимите плечи максимально вверх\n" +
                "3. Задержитесь в верхней точке\n" +
                "4. Медленно опустите плечи",
                "https://youtu.be/example21",
                "Подходы: 3-4\nПовторения: 12-15\nОтдых: 90 секунд",
                "• Травмы плеч\n• Проблемы с шеей",
                Arrays.asList("Шраги с гантелями", "Шраги за спиной")
        ));

        items.add(new BackExerciseItem(
                "Тяга рычажная прямым хватом снизу в тренажере",
                "Упражнение для нижней части спины",
                R.drawable.back_low_row_machine,
                "1. Сядьте в тренажер\n" +
                "2. Возьмитесь за рукояти прямым хватом\n" +
                "3. Тяните вес на себя\n" +
                "4. Плавно возвращайтесь в исходное положение",
                "https://youtu.be/example22",
                "Подходы: 3-4\nПовторения: 12-15\nОтдых: 90 секунд",
                "• Травмы поясницы\n• Проблемы с плечами",
                Arrays.asList("Тяга в наклоне", "Тяга на нижнем блоке")
        ));

        items.add(new BackExerciseItem(
                "Тяга штанги в наклоне обратным хватом",
                "Упражнение для верхней части спины",
                R.drawable.back_reverse_grip_row,
                "1. Наклонитесь вперед\n" +
                "2. Возьмите штангу обратным хватом\n" +
                "3. Тяните штангу к животу\n" +
                "4. Контролируйте опускание",
                "https://youtu.be/example23",
                "Подходы: 3-4\nПовторения: 10-12\nОтдых: 90 секунд",
                "• Травмы спины\n• Проблемы с запястьями",
                Arrays.asList("Тяга прямым хватом", "Тяга в тренажере")
        ));

        items.add(new BackExerciseItem(
                "Тяга штанги в наклоне прямым хватом",
                "Базовое упражнение для массы спины",
                R.drawable.back_barbell_row,
                "1. Наклонитесь вперед, колени слегка согнуты\n" +
                "2. Возьмите штангу прямым хватом\n" +
                "3. Тяните к нижней части груди\n" +
                "4. Медленно опускайте штангу",
                "https://youtu.be/example24",
                "Подходы: 4\nПовторения: 8-12\nОтдых: 120 секунд",
                "• Травмы поясницы\n• Проблемы с плечами",
                Arrays.asList("Тяга гантелей", "Тяга в тренажере")
        ));

        items.add(new BackExerciseItem(
                "Тяга штанги на наклонной скамье",
                "Упражнение для общего развития спины",
                R.drawable.back_incline_barbell_row,
                "1. Лягте грудью на наклонную скамью\n" +
                "2. Возьмите штангу прямым хватом\n" +
                "3. Тяните штангу к груди\n" +
                "4. Контролируйте опускание",
                "https://youtu.be/example25",
                "Подходы: 3-4\nПовторения: 10-12\nОтдых: 90 секунд",
                "• Травмы плеч\n• Проблемы с поясницей",
                Arrays.asList("Тяга гантелей на наклонной скамье", "Тяга в тренажере")
        ));
    }

    private void navigateToDetail(BackExerciseItem item) {
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
                .navigate(R.id.action_nav_back_exercises_to_nav_back_exercise_detail, bundle);
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