package com.giaca.antonino.ninoeat.ui.activities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

/**
 * Created by anton on 11/02/2019.
 */




@Entity(tableName = "Order")
public class Order {
    @Embedded
    private Restaurant restaurant;
    @ColumnInfo(name = "products")
    private ArrayList<Shop> prodotti;
    @ColumnInfo(name = "products")
    private  float total;
    public static final String ORDER_ENDPOINT = "http://138.68.86.70/orders";

    public String getIdrestaurant() {
        return idrestaurant;
    }

    public void setIdrestaurant(String idrestaurant) {
        this.idrestaurant = idrestaurant;
    }
@PrimaryKey(autoGenerate = true)
    private String idrestaurant;




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
