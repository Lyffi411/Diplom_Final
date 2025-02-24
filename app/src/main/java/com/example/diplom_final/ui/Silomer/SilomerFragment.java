package com.example.diplom_final.ui.Silomer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.diplom_final.R;
import com.example.diplom_final.databinding.FragmentSilomerBinding;

public class SilomerFragment extends Fragment {

    private FragmentSilomerBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SilomerViewModel silomerViewModel =
                new ViewModelProvider(this).get(SilomerViewModel.class);

        binding = FragmentSilomerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Инициализация кнопки перехода
        ImageButton goToGimButton = binding.getRoot().findViewById(R.id.imageButton_gim);
        goToGimButton.setOnClickListener(v -> {
            // Переход к фрагменту Gim
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_gim);
        });
        ImageButton goToTagaButton = binding.getRoot().findViewById(R.id.imageButton_taga);
        goToTagaButton.setOnClickListener(v -> {
            // Переход к фрагменту Taga
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_taga);
        });
        ImageButton goToPrisadButton = binding.getRoot().findViewById(R.id.imageButton_prisad);
        goToPrisadButton.setOnClickListener(v -> {
            // Переход к фрагменту Taga
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_prisad);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
