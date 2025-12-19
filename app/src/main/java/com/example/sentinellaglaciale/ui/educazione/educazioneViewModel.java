package com.example.sentinellaglaciale.ui.educazione;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class educazioneViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public educazioneViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Benvenuto in sentinella glaciale! In questa sezione potrai imparare alcune " +
                "informazioni sui principali ghiacciai dolomitici e metterti alla prova con un " +
                "divertente quiz!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}