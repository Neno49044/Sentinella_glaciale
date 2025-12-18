package com.example.sentinellaglaciale.ui.educazione;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuizViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private final MutableLiveData<String> mText;

    public QuizViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Benvenuto al quiz!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}