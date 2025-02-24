package com.example.diplom_final.ui.Ypragnenia;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class YpragneniaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public YpragneniaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Ypragnenia fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}