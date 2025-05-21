package com.example.diplom_final.ui.Stata.Stytis;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoExerciseResult {
    @Query("SELECT * FROM exercise_result ORDER BY timestamp DESC")
    LiveData<List<ExerciseResult>> getAllResultsLiveData();

    @Query("SELECT * FROM exercise_result ORDER BY timestamp DESC")
    List<ExerciseResult> getAllResults();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ExerciseResult exerciseResult);

    @Query("SELECT * FROM exercise_result WHERE exercise_type = :type ORDER BY timestamp DESC")
    LiveData<List<ExerciseResult>> getResultsByTypeLiveData(String type);

    @Query("SELECT * FROM exercise_result WHERE exercise_type = :type ORDER BY timestamp DESC")
    List<ExerciseResult> getResultsByType(String type);

    @Query("SELECT * FROM exercise_result WHERE timestamp BETWEEN :startDate AND :endDate ORDER BY timestamp DESC")
    LiveData<List<ExerciseResult>> getResultsByDateRangeLiveData(long startDate, long endDate);

    @Query("SELECT * FROM exercise_result WHERE timestamp BETWEEN :startDate AND :endDate ORDER BY timestamp DESC")
    List<ExerciseResult> getResultsByDateRange(long startDate, long endDate);

    @Query("SELECT * FROM exercise_result WHERE timestamp >= :startDate ORDER BY timestamp DESC")
    List<ExerciseResult> getResultsFromDate(long startDate);

    @Query("SELECT * FROM exercise_result WHERE timestamp <= :endDate ORDER BY timestamp DESC")
    List<ExerciseResult> getResultsToDate(long endDate);

    @Query("SELECT * FROM exercise_result WHERE exercise_type = :type AND timestamp BETWEEN :startDate AND :endDate ORDER BY timestamp DESC")
    LiveData<List<ExerciseResult>> getResultsByTypeAndDateRangeLiveData(String type, long startDate, long endDate);

    @Query("SELECT * FROM exercise_result WHERE exercise_type = :type AND timestamp BETWEEN :startDate AND :endDate ORDER BY timestamp DESC")
    List<ExerciseResult> getResultsByTypeAndDateRange(String type, long startDate, long endDate);

    @Query("SELECT * FROM exercise_result WHERE exercise_type = :type AND timestamp >= :startDate ORDER BY timestamp DESC")
    List<ExerciseResult> getResultsByTypeFromDate(String type, long startDate);

    @Query("SELECT * FROM exercise_result WHERE exercise_type = :type AND timestamp <= :endDate ORDER BY timestamp DESC")
    List<ExerciseResult> getResultsByTypeToDate(String type, long endDate);

    @Query("DELETE FROM exercise_result WHERE id = :id")
    void deleteById(long id);

    @Query("DELETE FROM exercise_result")
    void deleteAll();

    @Query("SELECT * FROM exercise_result WHERE exercise_type = :type ORDER BY timestamp DESC LIMIT 1")
    ExerciseResult getLastResult(String type);

    @Query("SELECT * FROM exercise_result WHERE weight > 0 OR reps > 0 ORDER BY timestamp DESC")
    LiveData<List<ExerciseResult>> getRecordedApproaches();
} 