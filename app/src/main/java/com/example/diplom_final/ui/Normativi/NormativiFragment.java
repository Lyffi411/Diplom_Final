package com.example.diplom_final.ui.Normativi;

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
import com.example.diplom_final.databinding.FragmentNormativiBinding;

import java.util.ArrayList;
import java.util.List;

public class NormativiFragment extends Fragment {

    private FragmentNormativiBinding binding;
    private RecyclerView recyclerView;
    private EditText searchEditText;
    private List<NormativItem> items;
    private NormativAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNormativiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.recyclerViewNormativi;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        searchEditText = binding.searchEditText;
        setupSearch();

        initializeItems();

        adapter = new NormativAdapter(items, navigationId -> {
            NormativItem item = adapter.getFilteredItems().get(navigationId);
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
        items.add(new NormativItem(
            "Троеборье",
            "Силовое троеборье включает в себя три упражнения: приседания со штангой, жим лежа и становую тягу",
            R.drawable.troee,
            R.id.nav_trobore
        ));
        
        items.add(new NormativItem(
            "Двоеборье",
            "Силовое двоеборье состоит из жима лежа и становой тяги",
            R.drawable.dvoee,
            R.id.nav_dvobore
        ));
        
        items.add(new NormativItem(
            "Жим лёжа",
            "Базовое упражнение для развития грудных мышц, трицепсов и передних дельт",
            R.drawable.gim_g,
            R.id.nav_gimlega
        ));
        
        items.add(new NormativItem(
            "Становая тяга",
            "Базовое упражнение для развития мышц спины, ног и укрепления всего тела",
            R.drawable.deadlift,
            R.id.nav_tagast
        ));
        
        items.add(new NormativItem(
            "Народный жим",
            "Жим штанги лежа на максимальное количество повторений с весом, равным собственному весу",
            R.drawable.gim_g,
            R.id.nav_nargim
        ));
        
        items.add(new NormativItem(
            "Жим лёжа в однослойной экипировке",
            "Жим лежа с использованием специальной майки для жима, состоящей из одного слоя материала",
            R.drawable.gim_g,
            R.id.nav_gimlegaodnosl
        ));
        
        items.add(new NormativItem(
            "Жим лёжа в многослойной экипировке",
            "Жим лежа с использованием специальной майки для жима, состоящей из нескольких слоев материала",
            R.drawable.gim_g,
            R.id.nav_gimlegamnogsl
        ));
        
        items.add(new NormativItem(
            "Жим лёжа в многопетельной экипировке",
            "Жим лежа с использованием специальной майки для жима с множественными поддерживающими петлями",
            R.drawable.gim_g,
            R.id.nav_gimlegamnogopet
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