package com.example.diplom_final.ui.Spravochnik.Items.Sport_F.Protein;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.diplom_final.R;

public class matrix extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_protein_detail, container, false);

        ImageView proteinImage = view.findViewById(R.id.proteinImage);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
        TextView benefitsTextView = view.findViewById(R.id.benefitsTextView);
        TextView recommendationsTextView = view.findViewById(R.id.recommendationsTextView);

        proteinImage.setImageResource(R.drawable.matrix);
        titleTextView.setText("Многокомпонентный протеин");

        descriptionTextView.setText(
            "Многокомпонентный протеин - это продвинутая формула, содержащая несколько видов белка " +
            "с добавлением ферментов, витаминов и минералов. Такой состав обеспечивает не только " +
            "поступление белка, но и его максимально эффективное усвоение, а также дополнительную " +
            "поддержку организма."
        );

        benefitsTextView.setText(
            "• Комплексное воздействие на организм\n" +
            "• Улучшенное усвоение благодаря ферментам\n" +
            "• Дополнительные витамины и минералы\n" +
            "• Разнообразный аминокислотный профиль\n" +
            "• Поддержка иммунной системы\n" +
            "• Антикатаболическое действие\n" +
            "• Подходит для длительного применения"
        );

        recommendationsTextView.setText(
            "• Принимать 2-3 раза в день\n" +
            "• Подходит для любого времени приема\n" +
            "• Рекомендуемая дозировка: 30-45 грамм на порцию\n" +
            "• Смешивать с водой или молоком\n" +
            "• Можно использовать как основной источник протеина\n" +
            "• Эффективен в периоды интенсивных тренировок"
        );

        return view;
    }
}