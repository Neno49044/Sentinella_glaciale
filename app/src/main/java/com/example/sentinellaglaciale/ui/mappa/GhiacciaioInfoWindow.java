package com.example.sentinellaglaciale.ui.mappa;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.sentinellaglaciale.R;
import com.example.sentinellaglaciale.ui.Ghiacciaio;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow;

public class GhiacciaioInfoWindow extends MarkerInfoWindow{
    private final FragmentActivity activity;

    public GhiacciaioInfoWindow(
            MapView mapView,
            FragmentActivity activity
    ) {
        super(R.layout.marker_info_window, mapView);
        this.activity = activity;
    }

    @Override
    public void onOpen(Object item) {
        Marker marker = (Marker) item;
        Ghiacciaio g = (Ghiacciaio) marker.getRelatedObject();

        TextView title = mView.findViewById(R.id.marker_title);
        Button btn = mView.findViewById(R.id.btnVediPiu);

        title.setText(g.getNome());

        btn.setOnClickListener(v -> {
            DettagliGhiacciaioFragment fragment =
                    new DettagliGhiacciaioFragment();

            Bundle bundle = new Bundle();
            bundle.putSerializable("ghiacciaio", g);

            //per chiudere bene scheda ghiacciaio anche senza x
            androidx.navigation.Navigation.findNavController(activity, R.id.nav_host_fragment_activity_main)
                    .navigate(R.id.navigation_dettagli, bundle);

            close();
        });
    }
}
