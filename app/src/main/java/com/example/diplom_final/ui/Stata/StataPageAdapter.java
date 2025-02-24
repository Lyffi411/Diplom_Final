package com.example.diplom_final.ui.Stata;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class StataPageAdapter extends FragmentStateAdapter {

    public StataPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Profil(); // Калькулятор минимального потребления
            case 1:
                return new Statistika(); // Калькулятор ИМТ
            default:
                return new Profil();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
