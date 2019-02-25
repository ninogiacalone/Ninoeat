package com.giaca.antonino.ninoeat.ui.activities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anton on 04/02/2019.
 */
@Entity(tableName = "Restaurant")
public class Restaurant {

    @ColumnInfo(name = "name")
    String nome;

    @ColumnInfo(name = "address")
    String indirizzo;

    @ColumnInfo(name = "minimum_order")
    float prezzo;


    String urlimm;
    private String id;
    public static final String ENDPOINT="restaurants/";


    public Restaurant(String nome, String indirizzo, float prezzo,String urlimm) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.prezzo = prezzo;
        this.urlimm=urlimm;
    }

public Restaurant(JSONObject jsonRestaurant )throws JSONException{
        nome=jsonRestaurant.getString("name");
        indirizzo=jsonRestaurant.getString("address");
        prezzo=(float)(jsonRestaurant.getDouble("min_order"));
        urlimm=jsonRestaurant.getString("image_url");
        id=jsonRestaurant.getString("id");

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}