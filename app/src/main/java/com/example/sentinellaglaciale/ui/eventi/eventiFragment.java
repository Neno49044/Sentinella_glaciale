package com.example.sentinellaglaciale.ui.eventi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sentinellaglaciale.R;
import com.example.sentinellaglaciale.databinding.EventItemBinding;
import com.example.sentinellaglaciale.databinding.FragmentEventiBinding;

public class eventiFragment extends Fragment {

    private FragmentEventiBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEventiBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Event 1
        EventItemBinding event1Binding = EventItemBinding.bind(binding.getRoot().findViewById(R.id.event_1));
        event1Binding.eventImage.setImageResource(R.color.purple_500); // Placeholder color
        event1Binding.eventTitle.setText("Pulizia Ghiacciaio");
        event1Binding.eventDate.setText("15 Luglio 2026");
        event1Binding.eventLocation.setText("xxx, xx");
        event1Binding.eventDescription.setText("...descrizione evento...");
        event1Binding.participateButton.setOnClickListener(v -> event1Binding.participateButton.setText("ISCRITTO!"));

        // Event 2
        EventItemBinding event2Binding = EventItemBinding.bind(binding.getRoot().findViewById(R.id.event_2));
        event2Binding.eventImage.setImageResource(R.color.teal_200); // Placeholder color
        event2Binding.eventTitle.setText("Conferenza: Il Futuro dei Ghiacciai");
        event2Binding.eventDate.setText("22 Luglio 2026");
        event2Binding.eventLocation.setText("xxx, xx");
        event2Binding.eventDescription.setText("...descrizione evento...");
        event2Binding.participateButton.setOnClickListener(v -> event2Binding.participateButton.setText("ISCRITTO!"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}