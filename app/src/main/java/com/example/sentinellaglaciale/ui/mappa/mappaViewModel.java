package com.example.sentinellaglaciale.ui.mappa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import org.osmdroid.util.GeoPoint;
import java.util.ArrayList;
import java.util.List;

public class mappaViewModel extends ViewModel {
    private final MutableLiveData<List<GeoPoint>> ghiacciai = new MutableLiveData<>();

    public mappaViewModel() {
        // Ghiacciai Veneti
        List<GeoPoint> lista = new ArrayList<>();
        lista.add(new GeoPoint(46.35, 12.12)); // Cristallo
        lista.add(new GeoPoint(46.17, 11.50)); // Pale di San Martino
        lista.add(new GeoPoint(46.24, 11.50)); // Marmolada
        lista.add(new GeoPoint(46.22, 12.03)); // Civetta
        lista.add(new GeoPoint(46.25, 12.08)); // Pelmo
        lista.add(new GeoPoint(46.33, 12.03)); // Tofane
        lista.add(new GeoPoint(46.30, 12.17)); // Antelao-Marmarole
        lista.add(new GeoPoint(46.30, 12.12)); // Sorapis
        lista.add(new GeoPoint(46.38, 12.23)); // Cadini-Popera

        ghiacciai.setValue(lista);
    }

    public LiveData<List<GeoPoint>> getGhiacciai() {
        return ghiacciai;
    }
}