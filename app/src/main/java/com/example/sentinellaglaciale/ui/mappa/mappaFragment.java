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

//Classi della libreria OSMDroid
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import org.osmdroid.views.overlay.infowindow.InfoWindow;

import java.util.List;
import com.example.sentinellaglaciale.ui.mappa.Ghiacciaio;
import com.example.sentinellaglaciale.ui.mappa.DettagliGhiacciaioFragment;
import com.example.sentinellaglaciale.R;


public class mappaFragment extends Fragment {

    private FragmentMappaBinding binding;
    private MapView map;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mappaViewModel mappaViewModel = new ViewModelProvider(this).get(mappaViewModel.class);

        binding = FragmentMappaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Configuration.getInstance().setUserAgentValue(requireContext().getPackageName());

        map = binding.map;
        //map.setMultiTouchControls(true);
        map.setBuiltInZoomControls(true);// mostra i bottoni + e -
        map.setMultiTouchControls(true);// permette pinch zoom

        map.setOnTouchListener((v, event) -> {
            if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                InfoWindow.closeAllInfoWindowsOn(map);
            }
            return false; // IMPORTANTISSIMO
        });

        // Centro la mappa su Belluno
        GeoPoint startPoint = new GeoPoint(46.1391, 12.2172);
        map.getController().setZoom(10.5);
        map.getController().setCenter(startPoint);

        mappaViewModel.getGhiacciai().observe(getViewLifecycleOwner(), listaGhiacciai -> {
            map.getOverlays().clear();
            GhiacciaioInfoWindow infoWindow = new GhiacciaioInfoWindow(map, requireActivity());
            for (Ghiacciaio ghiacciaio : listaGhiacciai) {

                GeoPoint geoPoint = new GeoPoint(
                        ghiacciaio.getLatitudine(),
                        ghiacciaio.getLongitudine()
                );

                Marker ghiacciaioMarker = new Marker(map);
                ghiacciaioMarker.setPosition(geoPoint);
                ghiacciaioMarker.setAnchor(
                        Marker.ANCHOR_CENTER,
                        Marker.ANCHOR_BOTTOM
                );

                ghiacciaioMarker.setTitle(ghiacciaio.getNome());
                ghiacciaioMarker.setRelatedObject(ghiacciaio);
                ghiacciaioMarker.setInfoWindow(infoWindow);
                map.getOverlays().add(ghiacciaioMarker);
            }
            map.invalidate();
        });
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
