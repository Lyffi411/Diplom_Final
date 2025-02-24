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
import com.example.diplom_final.databinding.FragmentSportNutritionBinding;

import java.util.ArrayList;
import java.util.List;

public class SportNutritionFragment extends Fragment {

    private FragmentSportNutritionBinding binding;
    private RecyclerView recyclerView;
    private EditText searchEditText;
    private List<SportNutritionItem> items;
    private SportNutritionAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSportNutritionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.recyclerViewSportNutrition;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        searchEditText = binding.searchEditText;
        setupSearch();

        initializeItems();

        adapter = new SportNutritionAdapter(items, position -> {
            SportNutritionItem item = adapter.getFilteredItems().get(position);
            Navigation.findNavController(requireView()).navigate(item.getNavigationId());
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
                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        });
    }

    private void initializeItems() {
        items = new ArrayList<>();
        items.add(new SportNutritionItem(
            "Протеин",
            "Белковая добавка для роста мышечной массы и восстановления. Основной строительный материал для мышц.",
            R.drawable.protein,
            R.id.action_sportNutritionFragment_to_proteinFragment
        ));
        
        items.add(new SportNutritionItem(
            "Гейнер",
            "Углеводно-белковая смесь для набора массы. Идеально подходит для людей с быстрым метаболизмом.",
            R.drawable.geiner,
            R.id.action_sportNutritionFragment_to_geinerFragment
        ));
        
        items.add(new SportNutritionItem(
            "Креатин",
            "Добавка для увеличения силы и выносливости. Помогает восстанавливать АТФ в мышцах.",
            R.drawable.kreatin,
            R.id.action_sportNutritionFragment_to_kreatinFragment
        ));
        
        items.add(new SportNutritionItem(
            "Аминокислоты",
            "Строительные блоки белка. Помогают в восстановлении и защите мышц от разрушения.",
            R.drawable.amino,
            R.id.action_sportNutritionFragment_to_aminoFragment
        ));
        
        items.add(new SportNutritionItem(
            "Жиросжигатели",
            "Добавки, ускоряющие метаболизм и помогающие в снижении веса.",
            R.drawable.girosgigat,
            R.id.action_sportNutritionFragment_to_girosgigatFragment
        ));
        
        items.add(new SportNutritionItem(
            "Л-карнитин",
            "Аминокислота, помогающая транспортировать жирные кислоты в митохондрии для их сжигания.",
            R.drawable.lkarnit,
            R.id.action_sportNutritionFragment_to_lKarnitFragment
        ));
        
        items.add(new SportNutritionItem(
            "Витамины",
            "Комплексы витаминов и минералов для поддержания общего здоровья и иммунитета.",
            R.drawable.vitamini,
            R.id.action_sportNutritionFragment_to_vitaminiFragment
        ));
        
        items.add(new SportNutritionItem(
            "Специальные препараты",
            "Добавки для специфических целей: суставы, связки, восстановление.",
            R.drawable.specprep,
            R.id.action_sportNutritionFragment_to_specprepFragment
        ));
        
        items.add(new SportNutritionItem(
            "Препараты для суставов",
            "Добавки для поддержания здоровья суставов и связок. Содержат хондроитин, глюкозамин и др.",
            R.drawable.systavi,
            R.id.action_sportNutritionFragment_to_systaviFragment
        ));
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
