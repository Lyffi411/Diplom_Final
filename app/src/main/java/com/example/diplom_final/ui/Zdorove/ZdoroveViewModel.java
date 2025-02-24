package com.example.diplom_final.ui.Zdorove;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ZdoroveViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ZdoroveViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Zdorove fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}