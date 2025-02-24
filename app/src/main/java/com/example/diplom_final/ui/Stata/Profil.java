package com.example.diplom_final.ui.Stata;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.diplom_final.R;
import com.example.diplom_final.ui.Filter.MinMaxFilter;
import com.example.diplom_final.ui.Stata.Profile.UserProfile;
import com.example.diplom_final.data.AppDatabase;
import com.example.diplom_final.ui.Stata.Stytis.ExerciseResult;
import java.util.Locale;

public class Profil extends Fragment {
    private EditText nameInput, ageInput, heightInput, weightInput;
    private EditText benchPressInput, deadliftInput, squatInput;
    private Spinner genderSpinner;
    private Button saveButton, updateFromStatsButton;
    private ProfilViewModel viewModel;
    private MinMaxFilter ageFilter;
    private MinMaxFilter heightFilter;
    private MinMaxFilter weightFilter;
    private MinMaxFilter benchFilter;
    private MinMaxFilter deadliftFilter;
    private MinMaxFilter squatFilter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        
        // Инициализация полей
        nameInput = view.findViewById(R.id.nameInput);
        ageInput = view.findViewById(R.id.ageInput);
        heightInput = view.findViewById(R.id.heightInput);
        weightInput = view.findViewById(R.id.weightInput);
        benchPressInput = view.findViewById(R.id.benchPressInput);
        deadliftInput = view.findViewById(R.id.deadliftInput);
        squatInput = view.findViewById(R.id.squatInput);
        genderSpinner = view.findViewById(R.id.genderSpinner);
        saveButton = view.findViewById(R.id.saveButton);
        updateFromStatsButton = view.findViewById(R.id.updateFromStatsButton);

        // Настройка числового ввода и фильтров
        ageInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        heightInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        weightInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        benchPressInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        deadliftInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        squatInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        // Создаем фильтры с сохранением ссылок
        ageFilter = new MinMaxFilter(10, 100, ageInput);
        heightFilter = new MinMaxFilter(40, 250, heightInput);
        weightFilter = new MinMaxFilter(30, 250, weightInput);
        benchFilter = new MinMaxFilter(15, 400, benchPressInput);
        deadliftFilter = new MinMaxFilter(15, 500, deadliftInput);
        squatFilter = new MinMaxFilter(15, 500, squatInput);

        // Применяем фильтры
        ageInput.setFilters(new InputFilter[]{ageFilter});
        heightInput.setFilters(new InputFilter[]{heightFilter});
        weightInput.setFilters(new InputFilter[]{weightFilter});
        benchPressInput.setFilters(new InputFilter[]{benchFilter});
        deadliftInput.setFilters(new InputFilter[]{deadliftFilter});
        squatInput.setFilters(new InputFilter[]{squatFilter});

        // Настройка спиннера
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.genders, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        viewModel = new ViewModelProvider(this).get(ProfilViewModel.class);

        // Настройка числового ввода и фильтров
        ageInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        heightInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        weightInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        benchPressInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        deadliftInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        squatInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        // Применение фильтров с сохранением десятичных значений
        InputFilter[] decimalFilters = new InputFilter[] {
            new InputFilter.LengthFilter(7), // Максимальная длина числа
            (source, start, end, dest, dstart, dend) -> {
                String newVal = dest.toString().substring(0, dstart) + 
                              source.toString().substring(start, end) + 
                              dest.toString().substring(dend);
                
                if (newVal.equals(".")) return "0.";
                if (newVal.equals("")) return null;
                
                try {
                    double val = Double.parseDouble(newVal);
                    if (val >= 0 && val <= 999.99) return null;
                } catch (NumberFormatException e) {
                    if (newVal.matches("\\d*\\.?\\d*")) return null;
                }
                return "";
            }
        };

        heightInput.setFilters(decimalFilters);
        weightInput.setFilters(decimalFilters);
        benchPressInput.setFilters(decimalFilters);
        deadliftInput.setFilters(decimalFilters);
        squatInput.setFilters(decimalFilters);
        
        // Отдельный фильтр для возраста (только целые числа)
        ageInput.setFilters(new InputFilter[] {
            new InputFilter.LengthFilter(3),
            (source, start, end, dest, dstart, dend) -> {
                String newVal = dest.toString().substring(0, dstart) + 
                              source.toString().substring(start, end) + 
                              dest.toString().substring(dend);
                
                if (newVal.equals("")) return null;
                try {
                    int val = Integer.parseInt(newVal);
                    if (val >= 0 && val <= 150) return null;
                } catch (NumberFormatException e) {
                    if (newVal.matches("\\d+")) return null;
                }
                return "";
            }
        });

        // Обработчик кнопки сохранения
        saveButton.setOnClickListener(v -> {
            try {
                // Принудительная валидация
                ageFilter.forceValidate();
                heightFilter.forceValidate();
                weightFilter.forceValidate();
                benchFilter.forceValidate();
                deadliftFilter.forceValidate();
                squatFilter.forceValidate();

                UserProfile profile = new UserProfile();
                
                // Получаем значения из полей
                String name = nameInput.getText().toString().trim();
                String ageStr = ageInput.getText().toString().trim();
                String heightStr = heightInput.getText().toString().trim();
                String weightStr = weightInput.getText().toString().trim();
                String benchStr = benchPressInput.getText().toString().trim();
                String deadliftStr = deadliftInput.getText().toString().trim();
                String squatStr = squatInput.getText().toString().trim();
                
                // Устанавливаем значения в профиль
                profile.setName(name.isEmpty() ? "" : name);
                profile.setAge(ageStr.isEmpty() ? 0 : Integer.parseInt(ageStr));
                profile.setHeight(heightStr.isEmpty() ? 0.0 : Double.parseDouble(heightStr));
                profile.setWeight(weightStr.isEmpty() ? 0.0 : Double.parseDouble(weightStr));
                profile.setBenchPress(benchStr.isEmpty() ? 0.0 : Double.parseDouble(benchStr));
                profile.setDeadlift(deadliftStr.isEmpty() ? 0.0 : Double.parseDouble(deadliftStr));
                profile.setSquat(squatStr.isEmpty() ? 0.0 : Double.parseDouble(squatStr));
                profile.setGender(genderSpinner.getSelectedItem().toString());

                // Сохраняем профиль
                viewModel.saveProfile(profile);
                
                Toast.makeText(requireContext(), "Профиль сохранен", Toast.LENGTH_SHORT).show();

            } catch (NumberFormatException e) {
                Toast.makeText(requireContext(), "Проверьте правильность введенных данных", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (Exception e) {
                Toast.makeText(requireContext(), "Ошибка при сохранении профиля", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });

        // Наблюдаем за данными профиля
        viewModel.getProfile().observe(getViewLifecycleOwner(), profile -> {
            if (profile != null) {
                nameInput.setText(profile.getName() != null ? profile.getName() : "");
                ageInput.setText(profile.getAge() > 0 ? String.valueOf(profile.getAge()) : "");
                heightInput.setText(profile.getHeight() > 0 ? String.format(Locale.US, "%.1f", profile.getHeight()) : "");
                weightInput.setText(profile.getWeight() > 0 ? String.format(Locale.US, "%.1f", profile.getWeight()) : "");
                benchPressInput.setText(profile.getBenchPress() > 0 ? String.format(Locale.US, "%.1f", profile.getBenchPress()) : "");
                deadliftInput.setText(profile.getDeadlift() > 0 ? String.format(Locale.US, "%.1f", profile.getDeadlift()) : "");
                squatInput.setText(profile.getSquat() > 0 ? String.format(Locale.US, "%.1f", profile.getSquat()) : "");
                
                if (profile.getGender() != null) {
                    ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) genderSpinner.getAdapter();
                    for (int i = 0; i < adapter.getCount(); i++) {
                        if (profile.getGender().equals(adapter.getItem(i).toString())) {
                            genderSpinner.setSelection(i);
                            break;
                        }
                    }
                }
            }
        });

        // В методе onViewCreated исправляем обработчик кнопки
        updateFromStatsButton.setOnClickListener(v -> {
            try {
                // Получаем текущий профиль
                final UserProfile currentProfile = viewModel.getProfile().getValue();
                final UserProfile profileToUpdate = currentProfile != null ? currentProfile : new UserProfile();

                // Получаем последние результаты из базы данных
                new Thread(() -> {
                    try {
                        AppDatabase db = AppDatabase.getDatabase(requireContext());
                        ExerciseResult benchPress = db.exerciseResultDao().getLastResult("bench_press");
                        ExerciseResult deadlift = db.exerciseResultDao().getLastResult("deadlift");
                        ExerciseResult squat = db.exerciseResultDao().getLastResult("squat");

                        // Обновляем профиль новыми значениями
                        if (benchPress != null) {
                            profileToUpdate.setBenchPress(benchPress.getValue());
                        }
                        if (deadlift != null) {
                            profileToUpdate.setDeadlift(deadlift.getValue());
                        }
                        if (squat != null) {
                            profileToUpdate.setSquat(squat.getValue());
                        }

                        // Сохраняем обновленный профиль
                        viewModel.saveProfile(profileToUpdate);

                        requireActivity().runOnUiThread(() -> 
                            Toast.makeText(requireContext(), "Данные профиля обновлены", Toast.LENGTH_SHORT).show()
                        );
                    } catch (Exception e) {
                        e.printStackTrace();
                        requireActivity().runOnUiThread(() -> 
                            Toast.makeText(requireContext(), "Ошибка при обновлении данных", Toast.LENGTH_SHORT).show()
                        );
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(requireContext(), "Ошибка при обновлении данных", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        
        // Очищаем ссылки на View
        nameInput = null;
        ageInput = null;
        heightInput = null;
        weightInput = null;
        benchPressInput = null;
        deadliftInput = null;
        squatInput = null;
        genderSpinner = null;
        saveButton = null;
        updateFromStatsButton = null;
        
        // Очищаем ViewModel если необходимо
        viewModel = null;
    }
}