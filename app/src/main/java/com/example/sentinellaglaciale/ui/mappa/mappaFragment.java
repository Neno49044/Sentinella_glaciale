package com.example.sentinellaglaciale.ui.mappa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sentinellaglaciale.databinding.FragmentMappaBinding;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class mappaFragment extends Fragment {

    private FragmentMappaBinding binding;
    private MapView map;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inizializzazione ViewModel (puoi usarlo più avanti per dati della mappa)
        mappaViewModel mappaViewModel =
                new ViewModelProvider(this).get(mappaViewModel.class);

        // Inizializzazione ViewBinding
        binding = FragmentMappaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inizializzazione osmdroid
        Configuration.getInstance().setUserAgentValue(requireContext().getPackageName());

        // Recupero MapView dal binding
        map = binding.map; // qui "map" è l'ID che hai dato nel layout fragment_mappa.xml
        //map.setMultiTouchControls(true);
        map.setBuiltInZoomControls(true);   // mostra i bottoni + e -
        map.setMultiTouchControls(true);    // permette pinch zoom


        // Centro la mappa su Roma
        GeoPoint startPoint = new GeoPoint(46.1391, 12.2172);
        map.getController().setZoom(10.0);
        map.getController().setCenter(startPoint);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (map != null) map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (map != null) map.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
