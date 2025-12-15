package com.example.sentinellaglaciale.ui.educazione;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuizViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private final MutableLiveData<String> mText;

    public QuizViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Pagina del quiz, qui vanno inserite le domande");
    }

    public LiveData<String> getText() {
        return mText;
    }
}