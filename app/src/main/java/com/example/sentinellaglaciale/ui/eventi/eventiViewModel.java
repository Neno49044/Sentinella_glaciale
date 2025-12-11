package com.example.sentinellaglaciale.ui.eventi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class eventiViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public eventiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Sezione degli eventi, qui appariranno gli eventi relativi al ghiacciaio preferito");
    }

    public LiveData<String> getText() {
        return mText;
    }
}