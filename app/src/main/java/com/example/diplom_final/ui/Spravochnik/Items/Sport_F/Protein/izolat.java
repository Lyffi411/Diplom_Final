package com.example.diplom_final.ui.Spravochnik.Items.Sport_F.Protein;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.diplom_final.R;

public class izolat extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_protein_detail, container, false);

        ImageView proteinImage = view.findViewById(R.id.proteinImage);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
        TextView benefitsTextView = view.findViewById(R.id.benefitsTextView);
        TextView recommendationsTextView = view.findViewById(R.id.recommendationsTextView);

        proteinImage.setImageResource(R.drawable.izolat);
        titleTextView.setText("Гидролизованный протеин");

        descriptionTextView.setText(
            "Гидролизованный протеин - это предварительно расщепленный белок, который подвергся процессу " +
            "гидролиза. В результате этого процесса длинные цепочки аминокислот разбиваются на более " +
            "короткие пептиды, что обеспечивает максимально быстрое усвоение и минимальную нагрузку " +
            "на пищеварительную систему."
        );

        benefitsTextView.setText(
            "• Сверхбыстрое усвоение\n" +
            "• Минимальная нагрузка на пищеварение\n" +
            "• Практически не вызывает аллергических реакций\n" +
            "• Высокая биодоступность\n" +
            "• Идеален для быстрого восстановления\n" +
            "• Минимальное содержание жиров и лактозы\n" +
            "• Подходит для периода сушки"
        );

        recommendationsTextView.setText(
            "• Принимать сразу после тренировки\n" +
            "• Можно использовать утром натощак\n" +
            "• Рекомендуемая дозировка: 25-35 грамм на порцию\n" +
            "• Смешивать с водой\n" +
            "• Не рекомендуется принимать перед сном\n" +
            "• Избегать смешивания с жирной пищей"
        );

        return view;
    }
}