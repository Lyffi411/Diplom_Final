package com.example.diplom_final.ui.Stata;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.diplom_final.data.AppDatabase;
import com.example.diplom_final.ui.Stata.Stytis.ExerciseResult;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatistikaViewModel extends AndroidViewModel {
    private final MutableLiveData<List<ExerciseResult>> _filteredResults = new MutableLiveData<>();
    public LiveData<List<ExerciseResult>> filteredResults = _filteredResults;

    private final AppDatabase database;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public StatistikaViewModel(Application application) {
        super(application);
        database = AppDatabase.getDatabase(application);
    }

    public void loadExerciseResults() {
        executorService.execute(() -> {
            List<ExerciseResult> resultsList = database.exerciseResultDao().getAllResults();
            _filteredResults.postValue(resultsList);
        });
    }

    public void loadFilteredResults(String type, long startDate, long endDate) {
        executorService.execute(() -> {
            List<ExerciseResult> resultsList;
            if (type == null || type.isEmpty() || "Все упражнения".equals(type)) {
                resultsList = database.exerciseResultDao().getResultsByDateRange(startDate, endDate);
            } else {
                resultsList = database.exerciseResultDao().getResultsByTypeAndDateRange(type, startDate, endDate);
            }
            _filteredResults.postValue(resultsList);
        });
    }

    public void loadFilteredResults(long startDate, long endDate) {
        executorService.execute(() -> {
            List<ExerciseResult> resultsList = database.exerciseResultDao().getResultsByDateRange(startDate, endDate);
            _filteredResults.postValue(resultsList);
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        executorService.shutdown();
    }
}