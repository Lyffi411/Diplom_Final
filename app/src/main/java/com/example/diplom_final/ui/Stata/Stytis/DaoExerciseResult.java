package com.example.diplom_final.ui.Stata.Stytis;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoExerciseResult {
    @Insert
    void insert(ExerciseResult result);

    @Query("SELECT * FROM exercise_results WHERE exercise_type = :type " +
           "AND timestamp BETWEEN :startDate AND :endDate " +
           "ORDER BY timestamp DESC")
    LiveData<List<ExerciseResult>> getFilteredResults(String type, long startDate, long endDate);

    @Query("SELECT * FROM exercise_results WHERE timestamp BETWEEN :startDate AND :endDate " +
           "ORDER BY timestamp DESC")
    LiveData<List<ExerciseResult>> getResultsByDateRange(long startDate, long endDate);

    @Query("SELECT * FROM exercise_results WHERE exercise_type = :type ORDER BY timestamp DESC")
    LiveData<List<ExerciseResult>> getResultsByType(String type);

    @Query("SELECT * FROM exercise_results ORDER BY timestamp DESC")
    LiveData<List<ExerciseResult>> getAllResults();
    
    @Query("SELECT * FROM exercise_results WHERE exercise_type = :type ORDER BY timestamp DESC LIMIT 1")
    ExerciseResult getLastResult(String type);

    @Query("SELECT * FROM exercise_results WHERE exercise_type = :type ORDER BY timestamp DESC")
    List<ExerciseResult> getAllResults(String type);

    @Query("SELECT COUNT(*) FROM exercise_results WHERE exercise_type = :type")
    int getCount(String type);
    @Query("DELETE FROM exercise_results")
    void deleteAll();
} 