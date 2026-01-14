package com.example.sentinellaglaciale.ui;
import java.io.Serializable;

public class Ghiacciaio implements Serializable {
    private final String nome;
    private final double latitudine;
    private final double longitudine;
    private boolean preferito;

    public Ghiacciaio(String nome, double latitudine, double longitudine, boolean preferito) {
        this.nome = nome;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.preferito = preferito;
    }

    public String getNome() {
        return nome;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setPreferito(boolean preferito) {
        this.preferito = preferito;
    }

    public boolean getPreferito() {
        return preferito;
    }
}
