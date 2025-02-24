package com.example.diplom_final.ui.Spravochnik.Items.Sport_F.Protein;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.diplom_final.R;

public class soevi extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_protein_detail, container, false);

        ImageView proteinImage = view.findViewById(R.id.proteinImage);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
        TextView benefitsTextView = view.findViewById(R.id.benefitsTextView);
        TextView recommendationsTextView = view.findViewById(R.id.recommendationsTextView);

        proteinImage.setImageResource(R.drawable.soevi);
        titleTextView.setText("Соевый протеин");

        descriptionTextView.setText(
            "Соевый протеин - это растительный белок, получаемый из соевых бобов. Он содержит все " +
            "необходимые аминокислоты и является отличной альтернативой животным белкам для " +
            "вегетарианцев и веганов. Кроме того, он богат изофлавонами, которые обладают " +
            "антиоксидантными свойствами."
        );

        benefitsTextView.setText(
            "• Подходит для вегетарианцев и веганов\n" +
            "• Содержит все незаменимые аминокислоты\n" +
            "• Богат антиоксидантами\n" +
            "• Не содержит холестерина\n" +
            "• Способствует снижению уровня холестерина\n" +
            "• Легко усваивается\n" +
            "• Экономически выгоден"
        );

        recommendationsTextView.setText(
            "• Принимать 2-3 раза в день\n" +
            "• Подходит для любого времени приема\n" +
            "• Рекомендуемая дозировка: 25-35 грамм на порцию\n" +
            "• Смешивать с водой или растительным молоком\n" +
            "• Можно использовать в кулинарии\n" +
            "• Хорошо сочетается с другими растительными белками"
        );

        return view;
    }
}