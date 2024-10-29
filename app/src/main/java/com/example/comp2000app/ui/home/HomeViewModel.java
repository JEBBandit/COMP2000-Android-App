package com.example.comp2000app.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.lang.Thread;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> nText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
//        mText.setValue("This is home fragment");
        nText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}