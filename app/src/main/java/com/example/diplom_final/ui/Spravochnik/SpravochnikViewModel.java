package com.example.diplom_final.ui.Spravochnik;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SpravochnikViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SpravochnikViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Spravochnik fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}