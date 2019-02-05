package com.giaca.antonino.ninoeat.ui.activities;

import android.media.Image;

/**
 * Created by anton on 04/02/2019.
 */

public class Restaurant {
    String nome;
    String indirizzo;
    float prezzo;
    String urlimm;


    public Restaurant(String nome, String indirizzo, float prezzo,String urlimm) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.prezzo = prezzo;
        this.urlimm=urlimm;
    }

    public String getUrlimm() {
        return urlimm;
    }

    public void setUrlimm(String urlimm) {
        this.urlimm = urlimm;
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