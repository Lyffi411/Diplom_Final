package com.example.diplom_final.ui.Spravochnik.Items.Sport_F.Protein;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.diplom_final.R;

public class sivor extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_protein_detail, container, false);

        ImageView proteinImage = view.findViewById(R.id.proteinImage);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
        TextView benefitsTextView = view.findViewById(R.id.benefitsTextView);
        TextView recommendationsTextView = view.findViewById(R.id.recommendationsTextView);

        proteinImage.setImageResource(R.drawable.sivor);
        titleTextView.setText("Сывороточный протеин");

        descriptionTextView.setText(
            "Сывороточный протеин - это быстроусваиваемый белок, получаемый из молочной сыворотки. " +
            "Он содержит высокую концентрацию BCAA и других незаменимых аминокислот. Благодаря " +
            "быстрому усвоению, этот вид протеина идеально подходит для приема сразу после тренировки " +
            "и утром."
        );

        benefitsTextView.setText(
            "• Быстрое усвоение\n" +
            "• Высокое содержание BCAA\n" +
            "• Отличная биодоступность\n" +
            "• Стимулирует синтез белка\n" +
            "• Укрепляет иммунную систему\n" +
            "• Способствует быстрому восстановлению\n" +
            "• Низкое содержание жиров и углеводов"
        );

        recommendationsTextView.setText(
            "• Принимать сразу после тренировки\n" +
            "• Можно использовать утром\n" +
            "• Рекомендуемая дозировка: 25-35 грамм на порцию\n" +
            "• Смешивать с водой или молоком\n" +
            "• Не рекомендуется принимать перед сном\n" +
            "• Можно комбинировать с креатином"
        );

        return view;
    }
}