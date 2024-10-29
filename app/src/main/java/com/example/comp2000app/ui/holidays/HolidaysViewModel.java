package com.example.comp2000app.ui.holidays;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HolidaysViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HolidaysViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}