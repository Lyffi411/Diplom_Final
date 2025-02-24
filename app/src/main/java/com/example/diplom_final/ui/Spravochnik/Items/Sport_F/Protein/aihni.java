package com.example.diplom_final.ui.Spravochnik.Items.Sport_F.Protein;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.diplom_final.R;

public class aihni extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_protein_detail, container, false);

        ImageView proteinImage = view.findViewById(R.id.proteinImage);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
        TextView benefitsTextView = view.findViewById(R.id.benefitsTextView);
        TextView recommendationsTextView = view.findViewById(R.id.recommendationsTextView);

        // Установка изображения
        proteinImage.setImageResource(R.drawable.aihni);

        // Установка заголовка
        titleTextView.setText("Яичный протеин");

        // Установка описания
        descriptionTextView.setText(
            "Яичный протеин - это высококачественный белок, получаемый из яичного белка. " +
            "Он содержит все необходимые аминокислоты в оптимальном соотношении и имеет высокую " +
            "биологическую ценность. Этот вид протеина усваивается организмом со средней скоростью, " +
            "что делает его универсальным выбором для различных целей."
        );

        // Установка преимуществ
        benefitsTextView.setText(
            "• Высокая биологическая ценность\n" +
            "• Отличный аминокислотный профиль\n" +
            "• Средняя скорость усвоения\n" +
            "• Низкое содержание жиров и углеводов\n" +
            "• Подходит людям с непереносимостью лактозы\n" +
            "• Хорошо подходит для наращивания мышечной массы\n" +
            "• Способствует восстановлению мышц"
        );

        // Установка рекомендаций
        recommendationsTextView.setText(
            "• Принимать 1-2 порции в день\n" +
            "• Оптимальное время приема: утром и после тренировки\n" +
            "• Рекомендуемая дозировка: 25-35 грамм на порцию\n" +
            "• Смешивать с водой или молоком\n" +
            "• Можно комбинировать с другими видами протеина\n" +
            "• Подходит для приготовления протеиновых коктейлей и выпечки"
        );

        return view;
    }
}