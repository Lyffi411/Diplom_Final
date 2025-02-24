package com.example.diplom_final.ui.Stata;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.diplom_final.data.AppDatabase;
import com.example.diplom_final.ui.Stata.Stytis.ExerciseResult;
import java.util.List;

public class StatistikaViewModel extends AndroidViewModel {
    private final MutableLiveData<List<ExerciseResult>> filteredResults = new MutableLiveData<>();
    private final AppDatabase database;

    public StatistikaViewModel(Application application) {
        super(application);
        database = AppDatabase.getDatabase(application);
        loadExerciseResults();
    }

    public void loadExerciseResults() {
        loadFilteredResults(0, System.currentTimeMillis());
    }

    public void loadFilteredResults(String type, long startDate, long endDate) {
        new Thread(() -> {
            List<ExerciseResult> results = database.exerciseResultDao()
                    .getFilteredResults(type, startDate, endDate)
                    .getValue();
            filteredResults.postValue(results);
        }).start();
    }

    public void loadFilteredResults(long startDate, long endDate) {
        new Thread(() -> {
            List<ExerciseResult> results = database.exerciseResultDao()
                    .getResultsByDateRange(startDate, endDate)
                    .getValue();
            filteredResults.postValue(results);
        }).start();
    }

    public LiveData<List<ExerciseResult>> getFilteredResults() {
        return filteredResults;
    }
}