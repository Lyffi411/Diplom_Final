package com.example.diplom_final.ui.Spravochnik.Items.Sport_F;

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
import com.example.diplom_final.databinding.FragmentProteinBinding;
import com.example.diplom_final.ui.Spravochnik.Items.Protein.ProteinAdapter;
import com.example.diplom_final.ui.Spravochnik.Items.Protein.ProteinItem;

import java.util.ArrayList;
import java.util.List;

public class protein extends Fragment {

    private FragmentProteinBinding binding;
    private RecyclerView recyclerView;
    private EditText searchEditText;
    private List<ProteinItem> items;
    private ProteinAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProteinBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.recyclerViewProtein;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        searchEditText = binding.searchEditText;
        setupSearch();

        initializeItems();

        adapter = new ProteinAdapter(items, navigationId -> 
            Navigation.findNavController(requireView()).navigate(navigationId)
        );
        
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
        items.add(new ProteinItem(
            "Сывороточный протеин",
            "Быстроусвояемый протеин, получаемый из молочной сыворотки. Идеален после тренировки и утром.",
            R.drawable.sivor,
            R.id.action_proteinFragment_to_sivorFragment
        ));
        
        items.add(new ProteinItem(
            "Казеиновый протеин",
            "Медленноусвояемый протеин, обеспечивающий длительное поступление аминокислот. Лучше всего принимать перед сном.",
            R.drawable.kazein,
            R.id.action_proteinFragment_to_kazeinFragment
        ));
        
        items.add(new ProteinItem(
            "Яичный протеин",
            "Протеин из яичного белка. Имеет высокую биологическую ценность и хорошо усваивается.",
            R.drawable.aihni,
            R.id.action_proteinFragment_to_aihniFragment
        ));
        
        items.add(new ProteinItem(
            "Говяжий протеин",
            "Протеин, получаемый из говядины. Богат креатином и другими анаболическими компонентами.",
            R.drawable.govag,
            R.id.action_proteinFragment_to_govagFragment
        ));
        
        items.add(new ProteinItem(
            "Соевый протеин",
            "Растительный протеин, подходящий для вегетарианцев. Содержит все необходимые аминокислоты.",
            R.drawable.soevi,
            R.id.action_proteinFragment_to_soeviFragment
        ));
        
        items.add(new ProteinItem(
            "Комплексный протеин",
            "Смесь разных видов протеина для оптимального и длительного усвоения.",
            R.drawable.komplecs,
            R.id.action_proteinFragment_to_komplecsFragment
        ));
        
        items.add(new ProteinItem(
            "Многокомпонентный протеин",
            "Смесь разных видов протеина для постепенного и длительного усвоения.",
            R.drawable.matrix,
            R.id.action_proteinFragment_to_matrixFragment
        ));
        
        items.add(new ProteinItem(
            "Гидролизованный протеин",
            "Предварительно расщепленный протеин для максимально быстрого усвоения.",
            R.drawable.izolat,
            R.id.action_proteinFragment_to_izolatFragment
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