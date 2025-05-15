package com.example.diplom_final.ui.Spravochnik.Items.Sport_F;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.diplom_final.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link geiner#newInstance} factory method to
 * create an instance of this fragment.
 */
public class geiner extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView benefitsTextView;
    private TextView recommendationsTextView;

    public geiner() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment geiner.
     */
    // TODO: Rename and change types and number of parameters
    public static geiner newInstance(String param1, String param2) {
        geiner fragment = new geiner();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_geiner, container, false);

        benefitsTextView = view.findViewById(R.id.benefitsTextView);
        recommendationsTextView = view.findViewById(R.id.recommendationsTextView);

        benefitsTextView.setText(
            "• Быстрое восполнение энергии\n" +
            "• Ускорение набора мышечной массы\n" +
            "• Высокая калорийность\n" +
            "• Содержит комплекс витаминов и минералов\n" +
            "• Удобство приема\n" +
            "• Быстрое усвоение\n" +
            "• Предотвращение катаболизма"
        );

        recommendationsTextView.setText(
            "• Начинать с малых доз (30-50г)\n" +
            "• Разводить в молоке или воде\n" +
            "• Принимать через 30-40 минут после тренировки\n" +
            "• Следить за реакцией организма\n" +
            "• Не превышать рекомендуемую дозировку\n" +
            "• Сочетать прием с интенсивными тренировками"
        );

        return view;
    }
}