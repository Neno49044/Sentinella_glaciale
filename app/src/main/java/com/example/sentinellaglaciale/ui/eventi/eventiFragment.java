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
        event1Binding.eventTitle.setText(R.string.giornata_mondiale_dei_ghiacciai);
        event1Binding.eventDate.setText(R.string._21_marzo_2026);
        event1Binding.eventLocation.setText(R.string.eventi_organizzati_su_tutto_il_territorio_italiano);
        event1Binding.eventDescription.setText(R.string.proteggere_i_ghiacciai_una_responsabilit_di_tutti_agisci_ora);
        event1Binding.participateButton.setOnClickListener(v -> {
            showEventDialog(
                    getString(R.string.descrizione_evento),
                    getString(R.string.in_occasione_della_giornata_mondiale_dei_ghiacciai_sul_territorio_italiano_saranno_organizzate_numerose_attivit_tra_cui_laboratori_educativi_conferenze_e_soprattutto_passeggiate_sul_territorio_e_sui_ghiacciai_alpini) +
                            getString(R.string.le_iniziative_saranno_promosse_da_diversi_enti_in_particolare_dal_club_alpino_italiano_cai_da_sempre_fortemente_presente_e_attivo_sul_territorio) +
                            getString(R.string.per_rimanere_aggiornati_sulle_attivit_in_programma_possibile_consultare_la_pagina_dedicata_al_seguente_link),
                    "https://caiscuola.cai.it/eventi/giornata-mondiale-dei-ghiacciai-sabato-21-marzo-2026/"
            );
        });
    }

    private void setupEvent2() {
        EventItemBinding event2Binding = EventItemBinding.bind(binding.getRoot().findViewById(R.id.event_2));
        event2Binding.eventImage.setImageResource(R.drawable.ev2);
        event2Binding.eventTitle.setText(R.string._29_alpine_glaciological_meeting_agm2026);
        event2Binding.eventDate.setText(R.string._26_27_febbraio_2026);
        event2Binding.eventLocation.setText(R.string.milano_italia);
        event2Binding.eventDescription.setText(R.string.un_incontro_internazionale_per_comprendere_le_profonde_trasformazioni_dei_ghiacciai_alpini_e_dei_territori_di_alta_montagna);
        event2Binding.participateButton.setOnClickListener(v -> {
            showEventDialog(
                    getString(R.string.descrizione_evento2),
                    getString(R.string.il_29_incontro_di_glaciologia_alpina_in_programma_il_26_e_27_febbraio_2026_riunisce_la_comunit_glaciologica_alpina_per_promuovere_uno_scambio_scientifico_aperto_e_inclusivo) +
                            getString(R.string.l_incontro_si_concentra_sulle_rapide_trasformazioni_della_criosfera_alpina_e_sulle_loro_implicazioni_scientifiche_e_sociali_affrontando_temi_come_il_ritiro_dei_ghiacciai_i_rischi_glaciologici_emergenti_i_cambiamenti_idrologici_e_l_impatto_delle_attivit_umane_sugli_ambienti_montani) +
                            getString(R.string.particolare_attenzione_dedicata_agli_approcci_interdisciplinari_e_al_coinvolgimento_dei_giovani_ricercatori_con_contributi_orali_e_poster) + "Per iscriversi a questo evento andare alla pagina web attraverso il link.",
                    "https://sites.google.com/unimib.it/eurocold/agm-29th-2026"
            );
        });
    }

    private void showEventDialog(String title, String message, String url) {
        new AlertDialog.Builder(requireContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.apri_link, (dialog, which) -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(browserIntent);
                })
                .setNegativeButton(R.string.chiudi2, null)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}