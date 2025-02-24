package com.example.diplom_final.ui.Silomer.Rasshet;
import android.util.Log;

public class WeightNorms {
    public static int getMaxWeight(String exercise, int weight, int age, boolean isMale) {
        int category = getWeightCategory(weight, isMale);
        String ageGroup = getAgeGroup(age);
        
        Log.d("WeightNorms", String.format(
            "Параметры: вес=%d кг, возраст=%d, пол=%s\n" +
            "Определено: категория=%d, возрастная группа=%s",
            weight, age, isMale ? "муж" : "жен", category, ageGroup
        ));
        
        int result = isMale ? getMaleNorm(exercise, category, ageGroup) 
                           : getFemaleNorm(exercise, category, ageGroup);
                           
        Log.d("WeightNorms", String.format(
            "Результат для %s: максимум=%d кг",
            exercise, result
        ));
        
        return result;
    }

    private static int getWeightCategory(int weight, boolean isMale) {
        int category;
        if (isMale) {
            if (weight <= 60) category = 60;
            else if (weight <= 67.5) category = 67;
            else if (weight <= 75) category = 75;
            else if (weight <= 82.5) category = 82;
            else if (weight <= 90) category = 90;
            else if (weight <= 100) category = 100;
            else if (weight <= 110) category = 110;
            else if (weight <= 125) category = 125;
            else category = 126;
        } else {
            if (weight <= 44) category = 44;
            else if (weight <= 48) category = 48;
            else if (weight <= 52) category = 52;
            else if (weight <= 56) category = 56;
            else if (weight <= 60) category = 60;
            else if (weight <= 67.5) category = 67;
            else if (weight <= 75) category = 75;
            else if (weight <= 82.5) category = 82;
            else category = 83;
        }
        
        Log.d("WeightNorms", String.format(
            "Определение категории: вес=%d кг, пол=%s → категория=%d",
            weight, isMale ? "муж" : "жен", category
        ));
        
        return category;
    }

    private static String getAgeGroup(int age) {
        String group;
        if (age < 23) group = "До 23";
        else if (age < 40) group = "24-39";
        else if (age < 50) group = "40-49";
        else if (age < 60) group = "50-59";
        else group = "60+";
        
        Log.d("WeightNorms", String.format(
            "Определение возрастной группы: возраст=%d → группа=%s",
            age, group
        ));
        
        return group;
    }

    private static int getMaleNorm(String exercise, int category, String ageGroup) {
        switch (exercise) {
            case "bench":
                if (category == 60) {
                    switch (ageGroup) {
                        case "До 23": return 160;
                        case "24-39": return 165;
                        case "40-49": return 162;
                        case "50-59": return 155;
                        default: return 147;
                    }
                }
                if (category == 67) {
                    switch (ageGroup) {
                        case "До 23": return 175;
                        case "24-39": return 180;
                        case "40-49": return 176;
                        case "50-59": return 170;
                        default: return 162;
                    }
                }
                if (category == 75) {
                    switch (ageGroup) {
                        case "До 23": return 192;
                        case "24-39": return 197;
                        case "40-49": return 195;
                        case "50-59": return 187;
                        default: return 180;
                    }
                }
                if (category == 82) {
                    switch (ageGroup) {
                        case "До 23": return 205;
                        case "24-39": return 210;
                        case "40-49": return 207;
                        case "50-59": return 200;
                        default: return 192;
                    }
                }
                if (category == 90) {
                    switch (ageGroup) {
                        case "До 23": return 215;
                        case "24-39": return 220;
                        case "40-49": return 217;
                        case "50-59": return 210;
                        default: return 202;
                    }
                }
                if (category == 100) {
                    switch (ageGroup) {
                        case "До 23": return 225;
                        case "24-39": return 230;
                        case "40-49": return 227;
                        case "50-59": return 220;
                        default: return 212;
                    }
                }
                if (category == 110) {
                    switch (ageGroup) {
                        case "До 23": return 235;
                        case "24-39": return 240;
                        case "40-49": return 237;
                        case "50-59": return 230;
                        default: return 222;
                    }
                }
                if (category == 125) {
                    switch (ageGroup) {
                        case "До 23": return 245;
                        case "24-39": return 250;
                        case "40-49": return 247;
                        case "50-59": return 240;
                        default: return 232;
                    }
                }
                if (category >= 126) {
                    switch (ageGroup) {
                        case "До 23": return 255;
                        case "24-39": return 260;
                        case "40-49": return 257;
                        case "50-59": return 250;
                        default: return 242;
                    }
                }
                break;

            case "squat":
                if (category == 60) {
                    switch (ageGroup) {
                        case "До 23": return 200;
                        case "24-39": return 205;
                        case "40-49": return 200;
                        case "50-59": return 190;
                        default: return 180;
                    }
                }
                if (category == 67) {
                    switch (ageGroup) {
                        case "До 23": return 220;
                        case "24-39": return 225;
                        case "40-49": return 220;
                        case "50-59": return 210;
                        default: return 200;
                    }
                }
                if (category == 75) {
                    switch (ageGroup) {
                        case "До 23": return 240;
                        case "24-39": return 245;
                        case "40-49": return 240;
                        case "50-59": return 230;
                        default: return 220;
                    }
                }
                if (category == 82) {
                    switch (ageGroup) {
                        case "До 23": return 260;
                        case "24-39": return 265;
                        case "40-49": return 260;
                        case "50-59": return 250;
                        default: return 240;
                    }
                }
                if (category == 90) {
                    switch (ageGroup) {
                        case "До 23": return 280;
                        case "24-39": return 285;
                        case "40-49": return 280;
                        case "50-59": return 270;
                        default: return 260;
                    }
                }
                if (category == 100) {
                    switch (ageGroup) {
                        case "До 23": return 300;
                        case "24-39": return 305;
                        case "40-49": return 300;
                        case "50-59": return 290;
                        default: return 280;
                    }
                }
                if (category == 110) {
                    switch (ageGroup) {
                        case "До 23": return 320;
                        case "24-39": return 325;
                        case "40-49": return 320;
                        case "50-59": return 310;
                        default: return 300;
                    }
                }
                if (category == 125) {
                    switch (ageGroup) {
                        case "До 23": return 340;
                        case "24-39": return 345;
                        case "40-49": return 340;
                        case "50-59": return 330;
                        default: return 320;
                    }
                }
                if (category >= 126) {
                    switch (ageGroup) {
                        case "До 23": return 360;
                        case "24-39": return 365;
                        case "40-49": return 360;
                        case "50-59": return 350;
                        default: return 340;
                    }
                }
                break;

            case "deadlift":
                if (category == 60) {
                    switch (ageGroup) {
                        case "До 23": return 220;
                        case "24-39": return 225;
                        case "40-49": return 220;
                        case "50-59": return 210;
                        default: return 200;
                    }
                }
                if (category == 67) {
                    switch (ageGroup) {
                        case "До 23": return 240;
                        case "24-39": return 245;
                        case "40-49": return 240;
                        case "50-59": return 230;
                        default: return 220;
                    }
                }
                if (category == 75) {
                    switch (ageGroup) {
                        case "До 23": return 260;
                        case "24-39": return 265;
                        case "40-49": return 260;
                        case "50-59": return 250;
                        default: return 240;
                    }
                }
                if (category == 82) {
                    switch (ageGroup) {
                        case "До 23": return 280;
                        case "24-39": return 285;
                        case "40-49": return 280;
                        case "50-59": return 270;
                        default: return 260;
                    }
                }
                if (category == 90) {
                    switch (ageGroup) {
                        case "До 23": return 300;
                        case "24-39": return 305;
                        case "40-49": return 300;
                        case "50-59": return 290;
                        default: return 280;
                    }
                }
                if (category == 100) {
                    switch (ageGroup) {
                        case "До 23": return 320;
                        case "24-39": return 325;
                        case "40-49": return 320;
                        case "50-59": return 310;
                        default: return 300;
                    }
                }
                if (category == 110) {
                    switch (ageGroup) {
                        case "До 23": return 340;
                        case "24-39": return 345;
                        case "40-49": return 340;
                        case "50-59": return 330;
                        default: return 320;
                    }
                }
                if (category == 125) {
                    switch (ageGroup) {
                        case "До 23": return 360;
                        case "24-39": return 365;
                        case "40-49": return 360;
                        case "50-59": return 350;
                        default: return 340;
                    }
                }
                if (category >= 126) {
                    switch (ageGroup) {
                        case "До 23": return 380;
                        case "24-39": return 385;
                        case "40-49": return 380;
                        case "50-59": return 370;
                        default: return 360;
                    }
                }
                break;
        }
        return 200; // Значение по умолчанию
    }

    private static int getFemaleNorm(String exercise, int category, String ageGroup) {
        switch (exercise) {
            case "bench":
                if (category == 44) {
                    switch (ageGroup) {
                        case "До 23": return 45;
                        case "24-39": return 47;
                        case "40-49": return 45;
                        case "50-59": return 42;
                        default: return 40;
                    }
                }
                if (category == 48) {
                    switch (ageGroup) {
                        case "До 23": return 50;
                        case "24-39": return 52;
                        case "40-49": return 50;
                        case "50-59": return 47;
                        default: return 45;
                    }
                }
                if (category == 52) {
                    switch (ageGroup) {
                        case "До 23": return 55;
                        case "24-39": return 57;
                        case "40-49": return 55;
                        case "50-59": return 52;
                        default: return 50;
                    }
                }
                if (category == 56) {
                    switch (ageGroup) {
                        case "До 23": return 60;
                        case "24-39": return 62;
                        case "40-49": return 60;
                        case "50-59": return 57;
                        default: return 55;
                    }
                }
                if (category == 60) {
                    switch (ageGroup) {
                        case "До 23": return 65;
                        case "24-39": return 67;
                        case "40-49": return 65;
                        case "50-59": return 62;
                        default: return 60;
                    }
                }
                if (category == 67) {
                    switch (ageGroup) {
                        case "До 23": return 70;
                        case "24-39": return 72;
                        case "40-49": return 70;
                        case "50-59": return 67;
                        default: return 65;
                    }
                }
                if (category == 75) {
                    switch (ageGroup) {
                        case "До 23": return 75;
                        case "24-39": return 77;
                        case "40-49": return 75;
                        case "50-59": return 72;
                        default: return 70;
                    }
                }
                if (category == 82) {
                    switch (ageGroup) {
                        case "До 23": return 80;
                        case "24-39": return 82;
                        case "40-49": return 80;
                        case "50-59": return 77;
                        default: return 75;
                    }
                }
                if (category >= 83) {
                    switch (ageGroup) {
                        case "До 23": return 85;
                        case "24-39": return 87;
                        case "40-49": return 85;
                        case "50-59": return 82;
                        default: return 80;
                    }
                }
                break;

            case "squat":
                if (category == 44) {
                    switch (ageGroup) {
                        case "До 23": return 85;
                        case "24-39": return 87;
                        case "40-49": return 85;
                        case "50-59": return 82;
                        default: return 80;
                    }
                }
                if (category == 48) {
                    switch (ageGroup) {
                        case "До 23": return 92;
                        case "24-39": return 95;
                        case "40-49": return 92;
                        case "50-59": return 90;
                        default: return 87;
                    }
                }
                if (category == 52) {
                    switch (ageGroup) {
                        case "До 23": return 100;
                        case "24-39": return 102;
                        case "40-49": return 100;
                        case "50-59": return 97;
                        default: return 95;
                    }
                }
                if (category == 56) {
                    switch (ageGroup) {
                        case "До 23": return 107;
                        case "24-39": return 110;
                        case "40-49": return 107;
                        case "50-59": return 105;
                        default: return 102;
                    }
                }
                if (category == 60) {
                    switch (ageGroup) {
                        case "До 23": return 115;
                        case "24-39": return 117;
                        case "40-49": return 115;
                        case "50-59": return 112;
                        default: return 110;
                    }
                }
                if (category == 67) {
                    switch (ageGroup) {
                        case "До 23": return 122;
                        case "24-39": return 125;
                        case "40-49": return 122;
                        case "50-59": return 120;
                        default: return 117;
                    }
                }
                if (category == 75) {
                    switch (ageGroup) {
                        case "До 23": return 130;
                        case "24-39": return 132;
                        case "40-49": return 130;
                        case "50-59": return 127;
                        default: return 125;
                    }
                }
                if (category == 82) {
                    switch (ageGroup) {
                        case "До 23": return 137;
                        case "24-39": return 140;
                        case "40-49": return 137;
                        case "50-59": return 135;
                        default: return 132;
                    }
                }
                if (category >= 83) {
                    switch (ageGroup) {
                        case "До 23": return 145;
                        case "24-39": return 147;
                        case "40-49": return 145;
                        case "50-59": return 142;
                        default: return 140;
                    }
                }
                break;

            case "deadlift":
                if (category == 44) {
                    switch (ageGroup) {
                        case "До 23": return 95;
                        case "24-39": return 97;
                        case "40-49": return 95;
                        case "50-59": return 92;
                        default: return 90;
                    }
                }
                if (category == 48) {
                    switch (ageGroup) {
                        case "До 23": return 102;
                        case "24-39": return 105;
                        case "40-49": return 102;
                        case "50-59": return 100;
                        default: return 97;
                    }
                }
                if (category == 52) {
                    switch (ageGroup) {
                        case "До 23": return 110;
                        case "24-39": return 112;
                        case "40-49": return 110;
                        case "50-59": return 107;
                        default: return 105;
                    }
                }
                if (category == 56) {
                    switch (ageGroup) {
                        case "До 23": return 117;
                        case "24-39": return 120;
                        case "40-49": return 117;
                        case "50-59": return 115;
                        default: return 112;
                    }
                }
                if (category == 60) {
                    switch (ageGroup) {
                        case "До 23": return 125;
                        case "24-39": return 127;
                        case "40-49": return 125;
                        case "50-59": return 122;
                        default: return 120;
                    }
                }
                if (category == 67) {
                    switch (ageGroup) {
                        case "До 23": return 132;
                        case "24-39": return 135;
                        case "40-49": return 132;
                        case "50-59": return 130;
                        default: return 127;
                    }
                }
                if (category == 75) {
                    switch (ageGroup) {
                        case "До 23": return 140;
                        case "24-39": return 142;
                        case "40-49": return 140;
                        case "50-59": return 137;
                        default: return 135;
                    }
                }
                if (category == 82) {
                    switch (ageGroup) {
                        case "До 23": return 147;
                        case "24-39": return 150;
                        case "40-49": return 147;
                        case "50-59": return 145;
                        default: return 142;
                    }
                }
                if (category >= 83) {
                    switch (ageGroup) {
                        case "До 23": return 155;
                        case "24-39": return 157;
                        case "40-49": return 155;
                        case "50-59": return 152;
                        default: return 150;
                    }
                }
                break;
        }
        return 100; // Значение по умолчанию для женщин
    }
} 