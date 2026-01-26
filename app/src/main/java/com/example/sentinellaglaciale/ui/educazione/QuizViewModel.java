package com.example.sentinellaglaciale.ui.educazione;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.sentinellaglaciale.R;

public class QuizViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private final MutableLiveData<Integer> mText;

    public QuizViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(R.string.intro_quiz);
    }

    public LiveData<Integer> getText() {
        return mText;
    }
}