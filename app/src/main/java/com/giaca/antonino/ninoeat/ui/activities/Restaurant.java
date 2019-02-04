package com.giaca.antonino.ninoeat.ui.activities;

/**
 * Created by anton on 04/02/2019.
 */

public class Restaurant {
    String nome;
    String indirizzo;
    float prezzo;

    public Restaurant(String nome, String indirizzo, float prezzo) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.prezzo = prezzo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public float getPrezzo() {
        return prezzo;
    }
}