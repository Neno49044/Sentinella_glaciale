package com.example.sentinellaglaciale.ui.mappa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class mappaViewModel extends ViewModel {

    private final MutableLiveData<List<Ghiacciaio>> ghiacciai = new MutableLiveData<>();

    public mappaViewModel() {

        List<Ghiacciaio> lista = new ArrayList<>();

        // Ghiacciai Veneti - Dolomiti
        lista.add(new Ghiacciaio("Cristallo", 46.35, 12.12));
        lista.add(new Ghiacciaio("Pale di San Martino", 46.17, 11.50));
        lista.add(new Ghiacciaio("Marmolada", 46.24, 11.50));
        lista.add(new Ghiacciaio("Civetta", 46.22, 12.03));
        lista.add(new Ghiacciaio("Pelmo", 46.25, 12.08));
        lista.add(new Ghiacciaio("Tofane", 46.33, 12.03));
        lista.add(new Ghiacciaio("Antelao-Marmarole", 46.30, 12.17));
        lista.add(new Ghiacciaio("Sorapis", 46.30, 12.12));
        lista.add(new Ghiacciaio("Cadini-Popera", 46.38, 12.23));

        ghiacciai.setValue(lista);
    }

    public LiveData<List<Ghiacciaio>> getGhiacciai() {
        return ghiacciai;
    }
}