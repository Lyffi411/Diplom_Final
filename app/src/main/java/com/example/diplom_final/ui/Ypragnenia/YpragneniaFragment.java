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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.R;
import com.example.diplom_final.databinding.FragmentYpragneniaBinding;

import java.util.ArrayList;
import java.util.List;

public class YpragneniaFragment extends Fragment {

    private FragmentYpragneniaBinding binding;
    private RecyclerView recyclerView;
    private EditText searchEditText;
    private List<YpragneniaItem> items;
    private YpragneniaAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        YpragneniaViewModel ypragneniaViewModel =
                new ViewModelProvider(this).get(YpragneniaViewModel.class);

        binding = FragmentYpragneniaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initViews();
        setupRecyclerView();
        setupSearch();
        initializeItems();
        setupAdapter();

        return root;
    }

    private void initViews() {
        recyclerView = binding.recyclerViewYpragnenia;
        searchEditText = binding.searchEditText;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
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
        
        // Добавляем категории упражнений
        items.add(new YpragneniaItem(
                "Грудные мышцы",
                "Упражнения для развития грудных мышц",
                R.drawable.chest_exercises
        ));
        
        items.add(new YpragneniaItem(
                "Спина",
                "Упражнения для мышц спины",
                R.drawable.back_exercises
        ));
        
        items.add(new YpragneniaItem(
                "Ноги",
                "Упражнения для мышц ног",
                R.drawable.leg_exercises
        ));
        
        items.add(new YpragneniaItem(
                "Плечи",
                "Упражнения для дельтовидных мышц",
                R.drawable.shoulder_exercises
        ));
        
        items.add(new YpragneniaItem(
                "Руки",
                "Упражнения для бицепса и трицепса",
                R.drawable.arm_exercises
        ));

        items.add(new YpragneniaItem(
                "Предплечье",
                "Упражнения для мышц предплечья",
                R.drawable.forearm_exercises
        ));
        
        items.add(new YpragneniaItem(
                "Пресс",
                "Упражнения для мышц пресса",
                R.drawable.abs_exercises
        ));

        items.add(new YpragneniaItem(
                "Ягодицы",
                "Упражнения для ягодичных мышц",
                R.drawable.glutes_exercises
        ));
    }

    private void setupAdapter() {
        adapter = new YpragneniaAdapter(items, position -> {
            YpragneniaItem item = adapter.getFilteredItems().get(position);
            navigateToDetail(item);
        });
        recyclerView.setAdapter(adapter);
    }

    private void navigateToDetail(YpragneniaItem item) {
        Bundle bundle = new Bundle();
        bundle.putString("title", item.getTitle());
        
        switch (item.getTitle()) {
            case "Грудные мышцы":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_nav_ypragnenia_to_chest_exercises, bundle);
                break;
            case "Спина":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_nav_ypragnenia_to_back_exercises, bundle);
                break;
            case "Ноги":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_nav_ypragnenia_to_leg_exercises, bundle);
                break;
            case "Плечи":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_nav_ypragnenia_to_shoulder_exercises, bundle);
                break;
            case "Руки":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_nav_ypragnenia_to_arm_exercises, bundle);
                break;
            case "Предплечье":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_nav_ypragnenia_to_forearm_exercises, bundle);
                break;
            case "Пресс":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_nav_ypragnenia_to_abs_exercises, bundle);
                break;
            case "Ягодицы":
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_nav_ypragnenia_to_glutes_exercises, bundle);
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