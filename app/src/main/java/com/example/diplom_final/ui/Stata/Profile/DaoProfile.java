package com.example.diplom_final.ui.Stata.Profile;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
@Dao
public interface DaoProfile {
    @Query("SELECT * FROM user_profile ORDER BY id DESC LIMIT 1")
    UserProfile getProfile();

    @Query("DELETE FROM user_profile")
    void deleteAll();

    @Transaction
    default void updateProfile(UserProfile profile) {
        deleteAll(); // Удаляем старый профиль
        insert(profile); // Вставляем новый
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserProfile profile);

    @Delete
    void delete(UserProfile profile);
}
