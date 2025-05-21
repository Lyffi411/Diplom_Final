package com.example.diplom_final.ui.Silomer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.diplom_final.R;
import com.example.diplom_final.databinding.FragmentSilomerBinding;

public class SilomerFragment extends Fragment {

    private FragmentSilomerBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSilomerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (binding.cardGim != null) {
            binding.cardGim.setOnClickListener(view -> {
                NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.nav_gim);
        });
        }

        if (binding.cardTaga != null) {
            binding.cardTaga.setOnClickListener(view -> {
                NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.nav_taga);
        });
        }

        if (binding.cardPrisad != null) {
            binding.cardPrisad.setOnClickListener(view -> {
                NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.nav_prisad);
        });
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
