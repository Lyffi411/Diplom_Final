package com.example.diplom_final.ui.Silomer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SilomerViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SilomerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Silomer fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}