package com.example.diplom_final.ui.Normativi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NormativiViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NormativiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Normativi fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}