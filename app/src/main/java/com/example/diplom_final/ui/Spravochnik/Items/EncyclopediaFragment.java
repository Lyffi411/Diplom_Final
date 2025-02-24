package com.example.diplom_final.ui.Spravochnik.Items;

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
import com.example.diplom_final.databinding.FragmentEncyclopediaBinding;
import com.example.diplom_final.ui.Spravochnik.Items.Encyc.ItemAdapterEncyc;
import com.example.diplom_final.ui.Spravochnik.Items.Encyc.ItemEncyc;

import java.util.ArrayList;
import java.util.List;

public class EncyclopediaFragment extends Fragment {

    private FragmentEncyclopediaBinding binding;
    private RecyclerView recyclerView;
    private EditText searchEditText;
    private List<ItemEncyc> items;
    private ItemAdapterEncyc adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEncyclopediaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Инициализация RecyclerView
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        // Инициализация EditText
        searchEditText = binding.searchEditText;
        setupSearch();

        // Инициализация данных
        initializeItems();

        // Создание и установка адаптера
        adapter = new ItemAdapterEncyc(items, position -> {
            ItemEncyc item = adapter.getFilteredItems().get(position);
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
        items.add(new ItemEncyc("Базовые упражнения", 
            "Информация о базовых упражнениях", R.drawable.baza));
        items.add(new ItemEncyc("В какое время дня лучше заниматься?", 
            "Информация о времени тренировок", R.drawable.vrema));
        items.add(new ItemEncyc("В чем польза растяжки?", 
            "Информация о пользе растяжки", R.drawable.rastag));
        items.add(new ItemEncyc("Важные составляющие набора мышечной массы", 
            "Информация о наборе массы", R.drawable.nabmish));
        items.add(new ItemEncyc("Ваш тип тела — Эктоморф, Мезоморф, Эндоморф?", 
            "Информация о типах телосложения", R.drawable.tiptela));
        items.add(new ItemEncyc("Застой в тренировках и от чего он возникает?", 
            "Информация о застое в тренировках", R.drawable.zastoi));
        items.add(new ItemEncyc("Изолирующие упражнения", 
            "Информация об изолирующих упражнениях", R.drawable.izoypr));
        items.add(new ItemEncyc("Интервал между подходами", 
            "Информация об интервалах отдыха", R.drawable.interval));
        items.add(new ItemEncyc("Как правильно делать замеры тела?", 
            "Информация о замерах тела", R.drawable.zameri));
        items.add(new ItemEncyc("Какие лучше делать упражнения: со свободными весами или в тренажерах?", 
            "Сравнение тренировок со свободными весами и в тренажерах", R.drawable.svvstren));
        items.add(new ItemEncyc("Мышцы кора", 
            "Информация о мышцах кора", R.drawable.mishkor));
        items.add(new ItemEncyc("Негативная фаза", 
            "Информация о негативной фазе упражнений", R.drawable.negfaz));
        items.add(new ItemEncyc("Нужно ли пить воду во время тренировки?", 
            "Информация о питьевом режиме", R.drawable.voda));
        items.add(new ItemEncyc("Понятие бодибилдинг", 
            "Информация о бодибилдинге", R.drawable.bodidilding));
        items.add(new ItemEncyc("Понятие пауэрлифтинг", 
            "Информация о пауэрлифтинге", R.drawable.powerlift));
        items.add(new ItemEncyc("Почему болят мышцы после тренировки и означает ли это, что тренировка была удачной?", 
            "Информация о мышечной боли", R.drawable.bol));
        items.add(new ItemEncyc("Почему от тренировки к тренировке играет вес при взвешивании?", 
            "Информация о колебаниях веса", R.drawable.raznves));
        items.add(new ItemEncyc("Правильное дыхание", 
            "Информация о технике дыхания", R.drawable.dishi));
        items.add(new ItemEncyc("Спортивный массаж", 
            "Информация о спортивном массаже", R.drawable.massag));
        items.add(new ItemEncyc("Стоит ли заниматься во время болезни?", 
            "Информация о тренировках во время болезни", R.drawable.bolezn));
        items.add(new ItemEncyc("Стоит ли ходить в сауну после тренировки?", 
            "Информация о посещении сауны", R.drawable.sayna));
        items.add(new ItemEncyc("Упражнения с осевой нагрузкой на позвоночник", 
            "Информация об осевой нагрузке", R.drawable.ocevaa));
        items.add(new ItemEncyc("Что выбрать - занятия дома или в тренажерном зале?", 
            "Сравнение домашних тренировок и занятий в зале", R.drawable.dovvszal));
        items.add(new ItemEncyc("Что лучше принимать: протеин или гейнер?", 
            "Сравнение протеина и гейнера", R.drawable.protvsgen));
        items.add(new ItemEncyc("Что такое ЧСС (частота сердечных сокращений) и как рассчитать свою целевую зону пульса?", 
            "Информация о пульсе и его расчете", R.drawable.hcc));
        items.add(new ItemEncyc("Что такое анаболизм, катаболизм, метаболизм?", 
            "Информация о метаболических процессах", R.drawable.anabol));
        items.add(new ItemEncyc("Что такое анаболики?", 
            "Информация об анаболиках", R.drawable.anaboliki));
        items.add(new ItemEncyc("Что такое андрогены?", 
            "Информация об андрогенах", R.drawable.androgen));
        items.add(new ItemEncyc("Что такое гликоген, где вырабатывается и на что влияет?", 
            "Информация о гликогене", R.drawable.glicogen));
        items.add(new ItemEncyc("Что такое дроп-сет?", 
            "Информация о дроп-сетах", R.drawable.dropset));
        items.add(new ItemEncyc("Что такое круговая тренировка?", 
            "Информация о круговых тренировках", R.drawable.kryg));
        items.add(new ItemEncyc("Что такое пампинг?", 
            "Информация о пампинге", R.drawable.pamping));
        items.add(new ItemEncyc("Что такое простые углеводы и сложные углеводы?", 
            "Информация об углеводах", R.drawable.ygli));
        items.add(new ItemEncyc("Что такое супер-тренировка?", 
            "Информация о супер-тренировках", R.drawable.syper));
        items.add(new ItemEncyc("Что такое суперсет (суперсерия)?", 
            "Информация о суперсетах", R.drawable.syperset));
        items.add(new ItemEncyc("Что такое супинация?", 
            "Информация о супинации", R.drawable.sypinasia));
        items.add(new ItemEncyc("Что такое читинг?", 
            "Информация о читинге", R.drawable.cihing));
    }

    private void navigateToDetail(ItemEncyc item) {
        switch (item.getTitle()) {
            case "Базовые упражнения":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_bazaFragment);
                break;
            case "В какое время дня лучше заниматься?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_vremaFragment);
                break;
            case "В чем польза растяжки?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_rastagFragment);
                break;
            case "Важные составляющие набора мышечной массы":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_nabmishFragment);
                break;
            case "Ваш тип тела — Эктоморф, Мезоморф, Эндоморф?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_tiptelaFragment);
                break;
            case "Застой в тренировках и от чего он возникает?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_zastoiFragment);
                break;
            case "Изолирующие упражнения":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_izoyprFragment);
                break;
            case "Интервал между подходами":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_intervalFragment);
                break;
            case "Как правильно делать замеры тела?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_zameriFragment);
                break;
            case "Какие лучше делать упражнения: со свободными весами или в тренажерах?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_svvstrenFragment);
                break;
            case "Мышцы кора":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_mishkorFragment);
                break;
            case "Негативная фаза":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_negfazFragment);
                break;
            case "Нужно ли пить воду во время тренировки?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_vodaFragment);
                break;
            case "Понятие бодибилдинг":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_bodidildingFragment);
                break;
            case "Понятие пауэрлифтинг":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_powerliftFragment);
                break;
            case "Почему болят мышцы после тренировки и означает ли это, что тренировка была удачной?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_bolFragment);
                break;
            case "Почему от тренировки к тренировке играет вес при взвешивании?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_raznvesFragment);
                break;
            case "Правильное дыхание":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_dishiFragment);
                break;
            case "Спортивный массаж":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_massagFragment);
                break;
            case "Стоит ли заниматься во время болезни?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_boleznFragment);
                break;
            case "Стоит ли ходить в сауну после тренировки?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_saynaFragment);
                break;
            case "Упражнения с осевой нагрузкой на позвоночник":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_ocevaaFragment);
                break;
            case "Что выбрать - занятия дома или в тренажерном зале?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_dovvszalFragment);
                break;
            case "Что лучше принимать: протеин или гейнер?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_protvsgenFragment);
                break;
            case "Что такое ЧСС (частота сердечных сокращений) и как рассчитать свою целевую зону пульса?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_hccFragment);
                break;
            case "Что такое анаболизм, катаболизм, метаболизм?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_anabolFragment);
                break;
            case "Что такое анаболики?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_anabolikiFragment);
                break;
            case "Что такое андрогены?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_androgenFragment);
                break;
            case "Что такое гликоген, где вырабатывается и на что влияет?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_glicogenFragment);
                break;
            case "Что такое дроп-сет?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_dropsetFragment);
                break;
            case "Что такое круговая тренировка?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_krygFragment);
                break;
            case "Что такое пампинг?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_pampingFragment);
                break;
            case "Что такое простые углеводы и сложные углеводы?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_ygliFragment);
                break;
            case "Что такое супер-тренировка?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_syperFragment);
                break;
            case "Что такое суперсет (суперсерия)?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_sypersetFragment);
                break;
            case "Что такое супинация?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_sypinasiaFragment);
                break;
            case "Что такое читинг?":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_encyclopediaFragment_to_cihingFragment);
                break;
        }
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
