package com.example.diplom_final.ui.Spravochnik.Items.Sport_F.Protein;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.diplom_final.R;

public class govag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_protein_detail, container, false);

        ImageView proteinImage = view.findViewById(R.id.proteinImage);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
        TextView benefitsTextView = view.findViewById(R.id.benefitsTextView);
        TextView recommendationsTextView = view.findViewById(R.id.recommendationsTextView);

        proteinImage.setImageResource(R.drawable.govag);
        titleTextView.setText("Говяжий протеин");

        descriptionTextView.setText(
            "Говяжий протеин - это высококачественный белок, получаемый из говяжьего мяса путем " +
            "гидролиза. Он содержит большое количество креатина, железа и витаминов группы B. " +
            "Этот вид протеина отличается высокой биологической ценностью и хорошей усвояемостью."
        );

        benefitsTextView.setText(
            "• Высокое содержание креатина\n" +
            "• Богат железом и витаминами группы B\n" +
            "• Отличный аминокислотный профиль\n" +
            "• Хорошая усвояемость\n" +
            "• Подходит при непереносимости молочного белка\n" +
            "• Способствует быстрому набору мышечной массы\n" +
            "• Поддерживает здоровье суставов и связок"
        );

        recommendationsTextView.setText(
            "• Принимать 1-2 порции в день\n" +
            "• Оптимальное время приема: после тренировки и между приемами пищи\n" +
            "• Рекомендуемая дозировка: 30-40 грамм на порцию\n" +
            "• Смешивать с водой или соком\n" +
            "• Можно использовать в составе гейнера\n" +
            "• Избегать приема перед сном"
        );

        return view;
    }
}