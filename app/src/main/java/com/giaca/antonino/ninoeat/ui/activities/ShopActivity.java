package com.giaca.antonino.ninoeat.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.giaca.antonino.ninoeat.R;

/**
 * Created by anton on 31/01/2019.
 */

public class ShopActivity  extends AppCompatActivity{

    String cibo;
    Float prezzo;
    String Ingredienti;

    public ShopActivity(String cibo, String ingredienti) {
        this.cibo = cibo;
        this.Ingredienti = Ingredienti;
        this.prezzo=prezzo;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
    }
}
