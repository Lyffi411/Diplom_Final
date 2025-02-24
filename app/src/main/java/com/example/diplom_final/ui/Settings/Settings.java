package com.example.diplom_final.ui.Settings;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import com.example.diplom_final.R;

public class Settings extends Fragment {

    private static final String PREFS_NAME = "ThemePrefs";
    private static final String KEY_THEME = "isDarkTheme";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Загружаем макет для фрагмента
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Восстановление текущей темы
        SharedPreferences preferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean isDarkTheme = preferences.getBoolean(KEY_THEME, false);

        // Находим кнопку для переключения темы
        Button themeToggleButton = view.findViewById(R.id.themeToggleButton);
        themeToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переключение темы
                boolean newTheme = !isDarkTheme;
                setAppTheme(newTheme);
                saveThemePreference(newTheme);
                requireActivity().recreate(); // Перезапуск активности для применения новой темы
            }
        });

        return view;
    }

    private void setAppTheme(boolean isDarkTheme) {
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void saveThemePreference(boolean isDarkTheme) {
        SharedPreferences preferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_THEME, isDarkTheme);
        editor.apply();
    }
}