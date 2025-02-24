package com.example.diplom_final.ui.Spravochnik.Items.Sport_F.Protein;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.diplom_final.R;

public class kazein extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_protein_detail, container, false);

        ImageView proteinImage = view.findViewById(R.id.proteinImage);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
        TextView benefitsTextView = view.findViewById(R.id.benefitsTextView);
        TextView recommendationsTextView = view.findViewById(R.id.recommendationsTextView);

        proteinImage.setImageResource(R.drawable.kazein);
        titleTextView.setText("Казеиновый протеин");

        descriptionTextView.setText(
            "Казеиновый протеин - это медленно усваиваемый белок, получаемый из молока. Он образует " +
            "гель в желудке, что обеспечивает постепенное высвобождение аминокислот в течение " +
            "длительного времени (до 7 часов). Это делает его идеальным выбором для длительного " +
            "поддержания анаболического состояния, особенно в ночное время."
        );

        benefitsTextView.setText(
            "• Длительное высвобождение аминокислот\n" +
            "• Предотвращает катаболизм мышц\n" +
            "• Высокое содержание кальция\n" +
            "• Способствует чувству сытости\n" +
            "• Отлично подходит для ночного приема\n" +
            "• Содержит биоактивные пептиды\n" +
            "• Поддерживает здоровье костей"
        );

        recommendationsTextView.setText(
            "• Идеален для приема перед сном\n" +
            "• Можно принимать между приемами пищи\n" +
            "• Рекомендуемая дозировка: 30-40 грамм на порцию\n" +
            "• Смешивать с молоком или водой\n" +
            "• Не рекомендуется принимать до и после тренировки\n" +
            "• Можно использовать в приготовлении творожных десертов"
        );

        return view;
    }
}