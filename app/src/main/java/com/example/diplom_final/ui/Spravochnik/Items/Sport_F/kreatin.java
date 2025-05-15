package com.example.diplom_final.ui.Spravochnik.Items.Sport_F;

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
import com.example.diplom_final.ui.Spravochnik.Items.SportNutritionAdapter;
import com.example.diplom_final.ui.Spravochnik.Items.SportNutritionItem;

import java.util.ArrayList;
import java.util.List;

public class kreatin extends Fragment {

    private RecyclerView recyclerView;
    private List<SportNutritionItem> items;
    private SportNutritionAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_kreatin, container, false);
        
        recyclerView = root.findViewById(R.id.recyclerViewKreatin);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        
        initializeItems();
        
        adapter = new SportNutritionAdapter(items, new SportNutritionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                SportNutritionItem item = adapter.getFilteredItems().get(position);
                Navigation.findNavController(requireView()).navigate(item.getNavigationId());
            }
            
            @Override
            public void onItemClick(int actionId, View view) {
                Navigation.findNavController(view).navigate(actionId);
            }
        });
        
        recyclerView.setAdapter(adapter);
        
        return root;
    }
    
    private void initializeItems() {
        items = new ArrayList<>();
        items.add(new SportNutritionItem(
            "Креатин моногидрат", 
            "Самая изученная и эффективная форма креатина. Представляет собой молекулу креатина, связанную с молекулой воды.",
            R.drawable.creatine_mono,
            R.id.action_kreatinFragment_to_creatineMonohydrateFragment
        ));
        
        items.add(new SportNutritionItem(
            "Креатин с транспортной системой", 
            "Усовершенствованная форма креатина с дополнительными компонентами для улучшенного усвоения.",
            R.drawable.creatine_transport,
            R.id.action_kreatinFragment_to_creatineTransportFragment
        ));
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView = null;
        items = null;
        adapter = null;
    }
}