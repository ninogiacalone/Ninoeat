package com.giaca.antonino.ninoeat.ui.activities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anton on 07/02/2019.
 */

public class Shop {
    String cibo;
    int quantity=0;
    float prezzo;
    String urlimm;
    String id;

    public String getUrlimm() {
        return urlimm;
    }

    public void setUrlimm(String urlimm) {
        this.urlimm = urlimm;
    }



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public Shop(String cibo, float prezzo, String urlimm) {
        this.cibo = cibo;
        this.prezzo = prezzo;
        this.urlimm=urlimm;
    }

    public Shop(JSONObject jsonShop )throws JSONException {
        cibo=jsonShop.getString("name");
        prezzo=(float)(jsonShop.getDouble("price"));
       id=jsonShop.getString("id");
        urlimm=jsonShop.getString("image_url");
    }



    public String getCibo() {
        return cibo;
    }

    public void setCibo(String cibo) {
        this.cibo = cibo;
    }

    public  float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }
    public void increaseQuantity(){
            this.quantity++;
    }

    public void decreaseQuantity(){
        if(quantity==0) return;;
            this.quantity--;
    }
}
