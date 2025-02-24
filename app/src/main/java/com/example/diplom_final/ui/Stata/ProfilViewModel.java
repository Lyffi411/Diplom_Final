package com.example.diplom_final.ui.Stata;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.diplom_final.data.AppDatabase;
import com.example.diplom_final.ui.Stata.Profile.UserProfile;
import com.example.diplom_final.ui.Stata.Profile.DaoProfile;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProfilViewModel extends AndroidViewModel {
    private final MutableLiveData<UserProfile> _profile = new MutableLiveData<>();
    private final AppDatabase database;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ProfilViewModel(Application application) {
        super(application);
        database = AppDatabase.getDatabase(application);
        loadProfile();
    }

    private void loadProfile() {
        executorService.execute(() -> {
            UserProfile profile = database.userProfileDao().getProfile();
            _profile.postValue(profile);
        });
    }

    public void saveProfile(UserProfile profile) {
        executorService.execute(() -> {
            try {
                // Получаем текущий профиль для сохранения ID
                UserProfile currentProfile = database.userProfileDao().getProfile();
                if (currentProfile != null) {
                    profile.setId(currentProfile.getId());
                }
                
                database.userProfileDao().updateProfile(profile);
                _profile.postValue(profile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LiveData<UserProfile> getProfile() {
        return _profile;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        executorService.shutdown();
    }
}