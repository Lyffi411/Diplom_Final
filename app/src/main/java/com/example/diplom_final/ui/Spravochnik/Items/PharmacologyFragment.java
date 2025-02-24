package com.example.diplom_final.ui.Spravochnik.Items;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.R;
import com.example.diplom_final.databinding.FragmentPharmacologyBinding;
import com.example.diplom_final.databinding.FragmentSpravochnikBinding;
import com.example.diplom_final.ui.Spravochnik.ItemAdapterSpravo;
import com.example.diplom_final.ui.Spravochnik.ItemSpravo;
import com.example.diplom_final.ui.Spravochnik.SpravochnikViewModel;

import java.util.ArrayList;
import java.util.List;

public class PharmacologyFragment extends Fragment {

    private FragmentPharmacologyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SpravochnikViewModel spravochnikViewModel =
                new ViewModelProvider(this).get(SpravochnikViewModel.class);

        binding = FragmentPharmacologyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Инициализируем RecyclerView
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Создаем список элементов
        List<ItemSpravo> items = new ArrayList<>();
        items.add(new ItemSpravo(R.drawable.gormon, "Протеин"));
        items.add(new ItemSpravo(R.drawable.peptid, "Гейнер"));
        items.add(new ItemSpravo(R.drawable.ineksia, "Кретин"));
        items.add(new ItemSpravo(R.drawable.oralpreparat, "Аминокислоты"));
        ItemAdapterSpravo adapter = new ItemAdapterSpravo(items, position -> {
            switch (position) {
                case 0:
                    Navigation.findNavController(root).navigate(R.id.action_pharmacologyFragment_to_gormonFragment);
                    break;
                case 1:
                    Navigation.findNavController(root).navigate(R.id.action_pharmacologyFragment_to_peptidFragment);
                    break;
                case 2:
                    Navigation.findNavController(root).navigate(R.id.action_pharmacologyFragment_to_ineksiaFragment);
                    break;
                case 3:
                    Navigation.findNavController(root).navigate(R.id.action_pharmacologyFragment_to_oralpreparatFragment);
                    break;
            }
        });
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
