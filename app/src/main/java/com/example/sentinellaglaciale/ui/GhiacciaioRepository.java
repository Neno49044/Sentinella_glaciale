package com.example.sentinellaglaciale.ui;

import java.util.ArrayList;
import java.util.List;

public class GhiacciaioRepository {

    private final List<Ghiacciaio> ghiacciai = new ArrayList<>();
    private static GhiacciaioRepository instance;

    private GhiacciaioRepository() {}

    public static GhiacciaioRepository getInstance() {
        if (instance == null) {
            instance = new GhiacciaioRepository();
        }
        return instance;
    }

    public List<Ghiacciaio> getGhiacciai() {
        return ghiacciai;
    }

    public void setGhiacciai(List<Ghiacciaio> lista) {
        ghiacciai.clear();
        ghiacciai.addAll(lista);
    }

    public Ghiacciaio getPreferito() {
        for (Ghiacciaio g : ghiacciai) {
            if (g.getPreferito()) {
                return g;
            }
        }
        return null;
    }

    public void impostaPreferito(Ghiacciaio nuovoPreferito) {
        for (Ghiacciaio g : ghiacciai) {
            g.setPreferito(false);
        }
        if (nuovoPreferito != null) {
            nuovoPreferito.setPreferito(true);
        }
    }

    public void rimuoviPreferito() {
        for (Ghiacciaio g : ghiacciai) {
            g.setPreferito(false);
        }
    }
}
