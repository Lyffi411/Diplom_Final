package com.example.diplom_final.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.annotation.NonNull;

import com.example.diplom_final.ui.Stata.Profile.UserProfile;
import com.example.diplom_final.ui.Stata.Profile.DaoProfile;
import com.example.diplom_final.ui.Stata.Stytis.ExerciseResult;
import com.example.diplom_final.ui.Stata.Stytis.DaoExerciseResult;

@Database(entities = {UserProfile.class, ExerciseResult.class}, version = 4, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract DaoExerciseResult exerciseResultDao();
    public abstract DaoProfile userProfileDao();

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE exercise_result ADD COLUMN weight REAL NOT NULL DEFAULT 0.0");
            database.execSQL("ALTER TABLE exercise_result ADD COLUMN reps INTEGER NOT NULL DEFAULT 0");
        }
    };

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "app_database"
                    )
                    .addMigrations(MIGRATION_3_4)
                    .build();
                }
            }
        }
        return INSTANCE;
    }
} 