package com.example.sentinellaglaciale.ui.mappa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sentinellaglaciale.R;
import com.example.sentinellaglaciale.databinding.FragmentDettagliGhiacciaioBinding;
import com.example.sentinellaglaciale.ui.Ghiacciaio;
import com.example.sentinellaglaciale.ui.GhiacciaioRepository;

public class DettagliGhiacciaioFragment extends Fragment {

    private FragmentDettagliGhiacciaioBinding binding;
    private Ghiacciaio ghiacciaioCorrente;

    public DettagliGhiacciaioFragment() {
        // Costruttore vuoto obbligatorio
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentDettagliGhiacciaioBinding.inflate(inflater, container, false);

        // Bottone chiusura
        binding.btnClose.setOnClickListener(v ->
                androidx.navigation.Navigation.findNavController(v).popBackStack()
        );

        // Recupero il ghiacciaio reale dalla lista Repository tramite l'oggetto passato
        if (getArguments() != null) {
            Ghiacciaio gPassato = (Ghiacciaio) getArguments().getSerializable("ghiacciaio");

            if (gPassato != null) {
                // Ripristino del ciclo per trovare l'oggetto corretto nel repository
                GhiacciaioRepository repo = GhiacciaioRepository.getInstance();
                for (Ghiacciaio g : repo.getGhiacciai()) {
                    if (g.getNome().equals(gPassato.getNome())) {
                        ghiacciaioCorrente = g;
                        break;
                    }
                }
            }

            if (ghiacciaioCorrente != null) {
                binding.txtTitolo.setText(ghiacciaioCorrente.getNome());
                aggiornaStella();
                popolaCampi(ghiacciaioCorrente);
            }
        }

        // Gestione stellina preferito
        binding.btnPreferito.setOnClickListener(v -> {
            if (ghiacciaioCorrente == null) return;
            GhiacciaioRepository repo = GhiacciaioRepository.getInstance();

            if (ghiacciaioCorrente.getPreferito()) {
                // Deseleziono
                repo.rimuoviPreferito(requireContext());
                aggiornaStella();
            } else {
                // Imposto come preferito
                impostaComePreferito();
            }
        });

        return binding.getRoot();
    }

    // Aggiorna icona stellina
    private void aggiornaStella() {
        if (ghiacciaioCorrente != null && ghiacciaioCorrente.getPreferito()) {
            binding.btnPreferito.setImageResource(R.drawable.ic_star_filled);
        } else {
            binding.btnPreferito.setImageResource(R.drawable.ic_star_outline);
        }
    }

    // Imposta il ghiacciaio corrente come preferito
    private void impostaComePreferito() {
        GhiacciaioRepository repo = GhiacciaioRepository.getInstance();
        repo.impostaPreferito(requireContext(), ghiacciaioCorrente);
        aggiornaStella();
    }

    // Mostra dialog di conferma (rimosso perché ora sovrascriviamo sempre)
    // private void mostraDialogConferma(Ghiacciaio vecchioPreferito) { ... }

    // Popola tutti i campi della scheda in base al ghiacciaio
    private void popolaCampi(Ghiacciaio g) {
        String nome = g.getNome();
        String raggruppamento, ghiacciai, bacino, descrizione;
        int immagine=0;

        switch (nome) {
            case "Cristallo":
                immagine = R.drawable.foto_cristallo;
                raggruppamento = getString(R.string.raggruppamento_cristallo);
                ghiacciai = getString(R.string.ghiacciai_cristallo);
                bacino = getString(R.string.bacino_cristallo);
                descrizione = getString(R.string.descrizione_cristallo);
                break;
            case "Pale di San Martino":
                immagine = R.drawable.foto_paledisanmartino;
                raggruppamento = getString(R.string.raggruppamento_paledisanmartino);
                ghiacciai = getString(R.string.ghiacciai_paledisanmartino);
                bacino = getString(R.string.bacino_paledisanmartino);
                descrizione = getString(R.string.descrizione_paledisanmartino);
                break;
            case "Marmolada":
                immagine = R.drawable.foto_marmolada;
                raggruppamento = getString(R.string.raggruppamento_marmolada);
                ghiacciai = getString(R.string.ghiacciai_marmolada);
                bacino = getString(R.string.bacino_marmolada);
                descrizione = getString(R.string.descrizione_marmolada);
                break;
            case "Civetta":
                immagine = R.drawable.foto_civetta;
                raggruppamento = getString(R.string.raggruppamento_civetta);
                ghiacciai = getString(R.string.ghiacciai_civetta);
                bacino = getString(R.string.bacino_civetta);
                descrizione = getString(R.string.descrizione_civetta);
                break;
            case "Pelmo":
                immagine = R.drawable.foto_pelmo;
                raggruppamento = getString(R.string.raggruppamento_pelmo);
                ghiacciai = getString(R.string.ghiacciai_pelmo);
                bacino = getString(R.string.bacino_pelmo);
                descrizione = getString(R.string.descrizione_pelmo);
                break;
            case "Tofane":
                immagine = R.drawable.foto_tofane;
                raggruppamento = getString(R.string.raggruppamento_tofane);
                ghiacciai = getString(R.string.ghiacciai_tofane);
                bacino = getString(R.string.bacino_tofane);
                descrizione = getString(R.string.descrizione_tofane);
                break;
            case "Antelao-Marmarole":
                immagine = R.drawable.foto_antelaomarmarole;
                raggruppamento = getString(R.string.raggruppamento_antelaomarmarole);
                ghiacciai = getString(R.string.ghiacciai_antelaomarmarole);
                bacino = getString(R.string.bacino_antelaomarmarole);
                descrizione = getString(R.string.descrizione_antelaomarmarole);
                break;
            case "Sorapis":
                immagine = R.drawable.foto_sorapis;
                raggruppamento = getString(R.string.raggruppamento_sorapis);
                ghiacciai = getString(R.string.ghiacciai_sorapis);
                bacino = getString(R.string.bacino_sorapis);
                descrizione = getString(R.string.descrizione_sorapis);
                break;
            case "Cadini-Popera":
                immagine = R.drawable.foto_cadinipopera;
                raggruppamento = getString(R.string.raggruppamento_cadinipopera);
                ghiacciai = getString(R.string.ghiacciai_cadinipopera);
                bacino = getString(R.string.bacino_cadinipopera);
                descrizione = getString(R.string.descrizione_cadinipopera);
                break;
            default:
                raggruppamento = getString(R.string.info_non_disponibili);
                ghiacciai = getString(R.string.info_non_disponibili);
                bacino = getString(R.string.info_non_disponibili);
                descrizione = getString(R.string.info_non_disponibili);
        }

        if (immagine != 0) {
            binding.imgGhiacciaio.setImageResource(immagine);
            binding.imgGhiacciaio.setVisibility(View.VISIBLE);
        } else {
            binding.imgGhiacciaio.setVisibility(View.GONE);
        }
        binding.txtRaggruppamento.setText(raggruppamento);
        binding.txtGhiacciai.setText(ghiacciai);
        binding.txtBacino.setText(bacino);
        binding.txtDescrizione.setText(descrizione);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}