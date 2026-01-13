package com.example.sentinellaglaciale.ui.mappa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.sentinellaglaciale.R;

import com.example.sentinellaglaciale.databinding.FragmentDettagliGhiacciaioBinding;

public class DettagliGhiacciaioFragment extends Fragment {

    private FragmentDettagliGhiacciaioBinding binding;

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
        binding.btnClose.setOnClickListener(v -> {
            requireActivity()
                    .getSupportFragmentManager()
                    .popBackStack();
        });

        //scheda informativa
        if (getArguments() != null) {
            Ghiacciaio g = (Ghiacciaio) getArguments().getSerializable("ghiacciaio");
            if (g != null) {
                String nome = g.getNome();
                binding.txtTitolo.setText(nome);

                String raggruppamento;
                String ghiacciai;
                String bacino;
                String descrizione;

                switch (nome) {

                    case "Cristallo":
                        raggruppamento = getString(R.string.raggruppamento_cristallo);
                        ghiacciai = getString(R.string.ghiacciai_cristallo);
                        bacino = getString(R.string.bacino_cristallo);
                        descrizione = getString(R.string.descrizione_cristallo);
                    break;

                    case "Pale di San Martino":
                        raggruppamento = getString(R.string.raggruppamento_paledisanmartino);
                        ghiacciai = getString(R.string.ghiacciai_paledisanmartino);
                        bacino = getString(R.string.bacino_paledisanmartino);
                        descrizione = getString(R.string.descrizione_paledisanmartino);
                    break;

                    case "Marmolada":
                        raggruppamento = getString(R.string.raggruppamento_marmolada);
                        ghiacciai = getString(R.string.ghiacciai_marmolada);
                        bacino = getString(R.string.bacino_marmolada);
                        descrizione = getString(R.string.descrizione_marmolada);
                    break;

                    case "Civetta":
                        raggruppamento = getString(R.string.raggruppamento_civetta);
                        ghiacciai = getString(R.string.ghiacciai_civetta);
                        bacino = getString(R.string.bacino_civetta);
                        descrizione = getString(R.string.descrizione_civetta);
                    break;

                    case "Pelmo":
                        raggruppamento = getString(R.string.raggruppamento_pelmo);
                        ghiacciai = getString(R.string.ghiacciai_pelmo);
                        bacino = getString(R.string.bacino_pelmo);
                        descrizione = getString(R.string.descrizione_pelmo);
                    break;

                    case "Tofane":
                        raggruppamento = getString(R.string.raggruppamento_tofane);
                        ghiacciai = getString(R.string.ghiacciai_tofane);
                        bacino = getString(R.string.bacino_tofane);
                        descrizione = getString(R.string.descrizione_tofane);
                    break;

                    case "Antelao-Marmarole":
                        raggruppamento = getString(R.string.raggruppamento_antelaomarmarole);
                        ghiacciai = getString(R.string.ghiacciai_antelaomarmarole);
                        bacino = getString(R.string.bacino_antelaomarmarole);
                        descrizione = getString(R.string.descrizione_antelaomarmarole);
                    break;

                    case "Sorapis":
                        raggruppamento = getString(R.string.raggruppamento_sorapis);
                        ghiacciai = getString(R.string.ghiacciai_sorapis);
                        bacino = getString(R.string.bacino_sorapis);
                        descrizione = getString(R.string.descrizione_sorapis);
                    break;

                    case "Cadini-Popera":
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
                binding.txtRaggruppamento.setText(raggruppamento);
                binding.txtGhiacciai.setText(ghiacciai);
                binding.txtBacino.setText(bacino);
                binding.txtDescrizione.setText(descrizione);
            }
        }
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
