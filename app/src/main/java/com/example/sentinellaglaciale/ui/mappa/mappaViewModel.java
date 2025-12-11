package com.example.sentinellaglaciale.ui.mappa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class mappaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public mappaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Sezione della mappa, dove vengono segnalati visivamente i ghiacciai");
    }

    public LiveData<String> getText() {
        return mText;
    }
}