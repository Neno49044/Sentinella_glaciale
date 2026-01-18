package com.example.sentinellaglaciale.ui;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class GhiacciaioRepository {

    private static final String PREFS_NAME = "GlacierPrefs";
    private static final String PREF_FAVORITE_GLACIER = "favorite_glacier";

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

    public void setGhiacciai(Context context, List<Ghiacciaio> lista) {
        ghiacciai.clear();
        ghiacciai.addAll(lista);
        loadPreferito(context); // Carica il preferito dopo aver impostato la lista
    }

    public Ghiacciaio getPreferito() {
        for (Ghiacciaio g : ghiacciai) {
            if (g.getPreferito()) {
                return g;
            }
        }
        return null;
    }

    public void impostaPreferito(Context context, Ghiacciaio nuovoPreferito) {
        String nomePreferito = "";
        for (Ghiacciaio g : ghiacciai) {
            boolean isPreferito = (nuovoPreferito != null && g.getNome().equals(nuovoPreferito.getNome()));
            g.setPreferito(isPreferito);
            if (isPreferito) {
                nomePreferito = g.getNome();
            }
        }
        savePreferito(context, nomePreferito);
    }

    public void rimuoviPreferito(Context context) {
        for (Ghiacciaio g : ghiacciai) {
            g.setPreferito(false);
        }
        savePreferito(context, "");
    }

    private void savePreferito(Context context, String nomeGhiacciaio) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREF_FAVORITE_GLACIER, nomeGhiacciaio);
        // Usa commit() per un salvataggio immediato e sincrono.
        // Questo risolve il race condition.
        editor.commit();
    }

    public void loadPreferito(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String nomePreferito = prefs.getString(PREF_FAVORITE_GLACIER, null);
        if (nomePreferito != null) {
            for (Ghiacciaio g : ghiacciai) {
                g.setPreferito(g.getNome().equals(nomePreferito));
            }
        }
    }
}