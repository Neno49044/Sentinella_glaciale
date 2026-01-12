package com.example.sentinellaglaciale.ui.mappa;
import java.io.Serializable;

public class Ghiacciaio implements Serializable {
    private final String nome;
    private final double latitudine;
    private final double longitudine;

    public Ghiacciaio(String nome, double latitudine, double longitudine) {
        this.nome = nome;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
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
}
