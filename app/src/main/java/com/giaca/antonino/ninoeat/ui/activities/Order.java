package com.giaca.antonino.ninoeat.ui.activities;

import java.util.ArrayList;

/**
 * Created by anton on 11/02/2019.
 */

public class Order {
    private Restaurant restaurant;
    private ArrayList<Shop> prodotti;
    private  float total;

    public Restaurant getRestaurant(){
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {

        this.restaurant = restaurant;
    }
    public ArrayList<Shop> getProdotti(){
            return prodotti;

    }
    public void setProdotti(ArrayList<Shop>prodotti){
        this.prodotti=prodotti;
    }

    public float getTotal()
    {
        return total;
    }
    public void setTotal(float total) {

        this.total = total;
    }
}
