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

                String descrizione;

                switch (nome) {

                    case "Cristallo":
                        descrizione =
                                "Raggruppamento montuoso: Dolomiti Nordorientali\n" +
                                "Ghiacciai: Popena, Cristallo, Cresta Bianca. (lista o linea?)\n" +
                                "Bacino idrografico: Adige\n" +
                                "Il monte Cristallo è situato tra il Passo dello Stelvio (a nord) e la Val Zebrù (a sud) e presenta due versanti completamente opposti uno rispetto all'altro: quello nord è ricoperto dalla Vedretta dei Vitelli (in provincia di Sondrio) e dalla Vedretta del Madaccio (in provincia di Bolzano), sulle quali sono presenti gli impianti estivi di risalita dello Stelvio.\n" +
                                "\tNel tempo questi ghiacciai, come molti altri nelle Alpi, si sono ridotti \n" +
                                "\tnotevolmente. Ad esempio, il ghiacciaio Cristallo, che una volta era uno dei\n" +
                                "\tpiù estesi delle Dolomiti, nel 1957 misurava 35 ettari, ma oggi è notevolmente ridotto e segnato da crepacci. \n" +
                                "Curiosità:  Qui, nell’agosto del 1888, perse la vita il celebre alpinista Michele Innerkolfer, cadendo in un crepaccio a seguito del crollo di un ponte di neve. ";
                        break;

                    case "Pale di San Martino":
                        descrizione =
                                "Raggruppamento montuoso: Dolomiti Centro-meridionali\n" +
                                "Ghiacciai: Val dei Cantoni, Valle delle Galline, Val Strutt, Ziroccole, Focobon, Fradusta, Travignolo\n" +
                                "Bacino idrografico: Piave\n" +
                                "\tI principali ghiacciai di questo gruppo montuoso sono il ghiacciaio della Fradusta e quello del Travignolo. Questi sono gli unici superstiti di un numero molto maggiore di apparati glaciali censiti all’inizio dello scorso secolo, e sono entrambi in forte regresso da molti anni.\n" +
                                "\n" +
                                "Il ghiacciaio della Fradusta, essendo alimentato esclusivamente da precipitazioni nevose dirette (divenute di anno in anno sempre più scarse) e subendo temperature estive sempre più elevate, ha finito col trovarsi a fine stagione costantemente privo di innevamento residuo e quindi di alimentazione, con un conseguente rapido ritiro.\n" +
                                "In passato il ghiacciaio era considerato il più importante delle Pale ed era il secondo delle Dolomiti, per estensione, dopo il Ghiacciaio della Marmolada. La calda estate del 2003 ha portato alla suddivisione del ghiacciaio in due parti e dal 2018 alla totale scomparsa della parte inferiore. Il decremento di superficie e la riduzione dello spessore permettono di presupporre che fra pochi anni anche la piccola porzione di ghiaccio rimasta si estinguerà completamente.\n" +
                                "A seguito della continua fusione, attualmente il ghiacciaio non è più il maggiore delle Pale, in quanto ha raggiunto una dimensione minore rispetto al Ghiacciaio del Travignolo.\n" +
                                "Per quanto riguarda quest’ultimo, si tratta di un ghiacciaio di vallone alimentato dalle valanghe. Dal 1947 il Ghiacciaio del Travignolo è arretrato di circa 200 metri in dislivello e oggi la fronte sfiora i 2300 metri di quota, con due lobi ricoperti di detrito e di spessore ridotto.\n";
                        break;

                    case "Marmolada":
                        descrizione =
                                "Raggruppamento montuoso: Dolomiti Nord-Occidentali\n" +
                                "Ghiacciai: Passo la Banca, Ombrettola\n" +
                                "Bacino idrografico: Piave\n" +
                                "Il ghiacciaio della Marmolada è un ghiacciaio vallivo che scende lungo il versante settentrionale della Marmolada, la montagna più alta delle Dolomiti. E’ collocato nella provincia di Trento e da esso prende forma il torrente Avisio.\n" +
                                "\tCome tutti i ghiacciai delle Alpi, anche il ghiacciaio della Marmolada si è ultimamente \n" +
                                "\tmolto ritirato. Nell’arco di un secolo le sue dimensioni si sono più che dimezzate: nel \n" +
                                "\t2013 misurava 190 ettari, mentre nel 1910 misurava 450 ettari. \n" +
                                "\tCuriosità: Nel 1916, durante la Grande Guerra, i genieri dell'esercito austro ungarico realizzarono dentro il ghiacciaio della Marmolada un complesso di gallerie, dormitori e depositi per collegare le diverse postazioni in quota bersagliate dal costante tiro delle truppe italiane arroccate sulla Cresta di Serauta (allora il confine di Stato passava proprio per il ghiacciaio). Il continuo movimento del ghiacciaio e la riduzione del suo spessore hanno tuttavia cancellato ogni traccia dei circa 10 km di gallerie scavate dai soldati nelle viscere del ghiacciaio, denominata in tedesco Eisstadt (\"Città di Ghiaccio\").\n";
                        break;

                    case "Civetta":
                        descrizione =
                                "Raggruppamento montuoso: Dolomiti di Zoldo\n" +
                                "Ghiacciai: Cristallo, Val Zuita, Cantoni, Moiazza, Civetta Est\n" +
                                "Bacino idrografico: Piave\n" +
                                "Cristallo\n" +
                                "Val Zuita\n" +
                                "Cantoni\n" +
                                "Moiazza\n" +
                                "Civetta Est\n" +
                                "(Sennò quando cerco viene fuori cristallo e glazier?)\n" +
                                "\t Il Monte Civetta, nelle Dolomiti, non ospita grandi ghiacciai vallivi come la Marmolada, ma presenta interessanti formazioni di ghiaccio e nevai perenni, soprattutto lungo la parete nord-est, dove si trovano cascate di ghiaccio stagionali e perenni (come la famosa Zuita Patavina) e ghiacciai pensili minori, tra cui il ghiacciaio del Cristallo. La maggior parte dei ghiacciai che erano una volta presenti all’interno di questo gruppo montuoso sono ormai scomparsi per via dell’aumento delle temperature estive e della quota relativamente bassa a cui si trovavano queste formazioni glaciali rispetto ad altri gruppi alpini. \n";
                        break;

                    case "Pelmo":
                        descrizione =
                                "Raggruppamento montuoso: Dolomiti di Zoldo\n" +
                                "Ghiacciai: Val d’Arcia\n" +
                                "Bacino idrografico: Piave\n" +
                                "Il Monte Pelmo in passato ospitava piccoli ghiacciai e glacionevati, oggi completamente scomparsi. Queste piccole formazioni glaciali sono state tra le prime a scomparire per via della bassa quota, che li ha resi particolarmente vulnerabili all’aumento delle temperature alpine e alla riduzione di nevicate. Questo rende i ghiacciai di questo gruppo montuoso un ottimo esempio di come il cambiamento climatico e il riscaldamento globale stanno velocemente cambiando il paesaggio alpino. \n" +
                                "Curiosità: Nonostante i ghiacciai di questa siano stati dichiarati completamente sciolti già da molto tempo, nel 2024 sono state rinvenute le tracce di un vecchio ghiacciaio sotto a strati di detriti, smossi da recenti frane. ";
                        break;

                    case "Tofane":
                        descrizione =
                                "Raggruppamento montuoso: Dolomiti Nordorientali\n" +
                                "Ghiacciai: Tofana Est, Tofana Ovest, Potofana, Fanes\n" +
                                "Bacino idrografico: Piave\n" +
                                "Sulle Tofane, massiccio dolomitico vicino a Cortina, un tempo erano presenti piccoli ghiacciai, ma a causa del riscaldamento climatico, sono quasi completamente scomparsi, ridotti a residui nevosi. se si parla di \"ghiacciai\" sulle Tofane oggi, ci si riferisce a residui glaciali e nevai piuttosto che a veri e propri ghiacciai in espansione, un fenomeno comune in tutte le Dolomiti.\n";
                        break;

                    case "Antelao-Marmarole":
                        descrizione =
                                "Raggruppamento montuoso: Dolomiti Nordorientali\n" +
                                "Ghiacciai: Val Salvella, Ciampestrini, Antelao Superiore, Antelao Inferiore, Selle, Froppa di Fuori, Froppa di Dentro, Meduce di Fuori, Meduce di Dentro\n" +
                                "Bacino idrografico: Piave\n" +
                                "I ghiacciai dell'Antelao (il \"Re delle Dolomiti\") e delle Marmarole sono in forte regressione, con il Ghiacciaio Superiore e Inferiore dell'Antelao ancora presenti ma ridotti, accessibili tramite ferrate impegnative e sentieri escursionistici, mentre i cinque ghiacciai storici delle Marmarole (Froppa, Meduce, Selle) sono già scomparsi, testimoniando il cambiamento climatico, con escursionisti che osservano rocce levigate dal ghiaccio e laghi glaciali al posto delle masse nevose un tempo estese, offrendo panorami spettacolari ma in un ambiente che cambia rapidamente\n";
                        break;

                    case "Sorapis":
                        descrizione =
                                "Raggruppamento montuoso: Dolomiti Nordorientali\n" +
                                "Ghiacciai: Sorapis Est, Sorapis Centrale, Zurlon, Sorapis Ovest, Foppa di Mattia\n" +
                                "Bacino idrografico: Piave\n" +
                                "I ghiacciai del Sorapis sono tre piccoli ma significativi ghiacciai nel massiccio del Sorapis, nelle Dolomiti Ampezzane, noti per alimentare il famoso Lago di Sorapis (Lago del Sorapis) con le loro acque di scioglimento, conferendogli l'iconico colore azzurro/turchese dovuto alle \"farine\" di roccia dolomitica. Questi ghiacciai (Orientale, Occidentale e uno centrale), pur essendo tra i più grandi delle Dolomiti, sono in forte e costante ritiro a causa dei cambiamenti climatici, rappresentando un ambiente fragile e un importante segnale del riscaldamento globale. \n";
                        break;

                    case "Cadini-Popera":
                        descrizione =
                                "Raggruppamento montuoso: Dolomiti Nordorientali\n" +
                                "Ghiacciai: San Lucano, Popera Basso, Popera Pensile, Popera Alto\n" +
                                "Bacino idrografico: Piave\n" +
                                "I ghiacciai dei Cadini e del Popera, nelle Dolomiti Orientali, sono in forte regressione, specialmente il Ghiacciaio Alto di Popera, che oggi è un piccolo \"debris covered glacier\" (ghiacciaio coperto di detriti) alla base della Cima Undici, con una marcata riduzione di superficie e massa, quasi interamente ricoperto da detrito ma con ancora debole dinamica glaciale e crepacci, testimone drammatico dei cambiamenti climatici, come documentato anche in filmati storici e attuali che mostrano la scomparsa del ghiacciaio pensile del Popera di 50 metri. \n";
                        break;

                    default:
                        descrizione = getString(R.string.info_non_disponibili);
                }
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
