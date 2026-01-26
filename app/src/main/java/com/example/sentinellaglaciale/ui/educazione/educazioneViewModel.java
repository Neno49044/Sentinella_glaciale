package com.example.sentinellaglaciale.ui.educazione;


import com.example.sentinellaglaciale.R;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class educazioneViewModel extends ViewModel {

    private final MutableLiveData<Integer> mText;

    public educazioneViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(R.string.intro_edu);
    }

    public LiveData<Integer> getText() {
        return mText;
    }
}