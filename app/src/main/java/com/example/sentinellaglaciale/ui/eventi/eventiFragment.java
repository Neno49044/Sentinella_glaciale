package com.example.sentinellaglaciale.ui.eventi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
        setupEvent1();

        // Event 2
        setupEvent2();
    }

    private void setupEvent1() {
        EventItemBinding event1Binding = EventItemBinding.bind(binding.getRoot().findViewById(R.id.event_1));
        event1Binding.eventImage.setImageResource(R.drawable.ev1);
        event1Binding.eventTitle.setText("Giornata Mondiale dei Ghiacciai");
        event1Binding.eventDate.setText("21 Marzo 2026");
        event1Binding.eventLocation.setText("Eventi organizzati su tutto il territorio italiano");
        event1Binding.eventDescription.setText("Proteggere i ghiacciai è una responsabilità di tutti. Agisci ora!");
        event1Binding.participateButton.setOnClickListener(v -> {
            showEventDialog(
                    "Descrizione Evento:",
                    "In occasione della Giornata Mondiale dei Ghiacciai, sul territorio italiano saranno organizzate numerose attività, tra cui laboratori educativi, conferenze e soprattutto passeggiate sul territorio e sui ghiacciai alpini. " +
                            "Le iniziative saranno promosse da diversi enti, in particolare dal Club Alpino Italiano (CAI), da sempre fortemente presente e attivo sul territorio. " +
                            "Per rimanere aggiornati sulle attività in programma, è possibile consultare la pagina dedicata al seguente link.",
                    "https://caiscuola.cai.it/eventi/giornata-mondiale-dei-ghiacciai-sabato-21-marzo-2026/"
            );
        });
    }

    private void setupEvent2() {
        EventItemBinding event2Binding = EventItemBinding.bind(binding.getRoot().findViewById(R.id.event_2));
        event2Binding.eventImage.setImageResource(R.drawable.ev2);
        event2Binding.eventTitle.setText("29° Alpine Glaciological Meeting (AGM2026)");
        event2Binding.eventDate.setText("26-27 Febbraio 2026");
        event2Binding.eventLocation.setText("Milano, Italia");
        event2Binding.eventDescription.setText("Un incontro internazionale per comprendere le profonde trasformazioni dei ghiacciai alpini e dei territori di alta montagna.");
        event2Binding.participateButton.setOnClickListener(v -> {
            showEventDialog(
                    "Descrizione Evento:",
                    "Il 29ª Incontro di Glaciologia Alpina, in programma il 26 e 27 febbraio 2026, riunisce la comunità glaciologica alpina per promuovere uno scambio scientifico aperto e inclusivo. " +
                            "L’incontro si concentra sulle rapide trasformazioni della criosfera alpina e sulle loro implicazioni scientifiche e sociali, affrontando temi come il ritiro dei ghiacciai, i rischi glaciologici emergenti, i cambiamenti idrologici e l’impatto delle attività umane sugli ambienti montani. " +
                            "Particolare attenzione è dedicata agli approcci interdisciplinari e al coinvolgimento dei giovani ricercatori, con contributi orali e poster." + "Per iscriversi a questo evento andare alla pagina web attraverso il link.",
                    "https://sites.google.com/unimib.it/eurocold/agm-29th-2026"
            );
        });
    }

    private void showEventDialog(String title, String message, String url) {
        new AlertDialog.Builder(requireContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Apri link", (dialog, which) -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(browserIntent);
                })
                .setNegativeButton("Chiudi", null)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}