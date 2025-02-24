package com.example.diplom_final.ui.Spravochnik.Items.Sport_F.Protein;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.diplom_final.R;

public class komplecs extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_protein_detail, container, false);

        ImageView proteinImage = view.findViewById(R.id.proteinImage);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
        TextView benefitsTextView = view.findViewById(R.id.benefitsTextView);
        TextView recommendationsTextView = view.findViewById(R.id.recommendationsTextView);

        proteinImage.setImageResource(R.drawable.komplecs);
        titleTextView.setText("Комплексный протеин");

        descriptionTextView.setText(
            "Комплексный протеин - это специально разработанная смесь различных видов белка с разной " +
            "скоростью усвоения. Обычно включает сывороточный, казеиновый и яичный протеины. Такая " +
            "комбинация обеспечивает как быстрое поступление аминокислот, так и их длительное " +
            "присутствие в крови."
        );

        benefitsTextView.setText(
            "• Сбалансированный аминокислотный профиль\n" +
            "• Разная скорость усвоения компонентов\n" +
            "• Длительное анаболическое действие\n" +
            "• Подходит для любого времени приема\n" +
            "• Комплексная поддержка мышц\n" +
            "• Оптимален для набора массы\n" +
            "• Хорошая усвояемость"
        );

        recommendationsTextView.setText(
            "• Можно принимать в любое время дня\n" +
            "• Идеален для замены приема пищи\n" +
            "• Рекомендуемая дозировка: 30-40 грамм на порцию\n" +
            "• Смешивать с водой или молоком\n" +
            "• Подходит для приема до и после тренировки\n" +
            "• Можно использовать перед сном"
        );

        return view;
    }
}