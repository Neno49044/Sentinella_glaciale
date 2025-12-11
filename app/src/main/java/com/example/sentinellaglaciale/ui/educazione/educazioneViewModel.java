package com.example.sentinellaglaciale.ui.educazione;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class educazioneViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public educazioneViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Sezione dell'educazione, comprende informazioni + quiz");
    }

    public LiveData<String> getText() {
        return mText;
    }
}