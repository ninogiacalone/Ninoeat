package com.giaca.antonino.ninoeat.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.giaca.antonino.ninoeat.R;
import com.giaca.antonino.ninoeat.ui.activities.adapters.Shop_adapters;

import java.util.ArrayList;

/**
 * Created by anton on 31/01/2019.
 */

public class ShopActivity  extends AppCompatActivity implements Shop_adapters.OnQuantityChangedListener {
    RecyclerView shoprv;

Shop_adapters adapters;
RecyclerView.LayoutManager layoutManager;
ArrayList<Shop> date;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        shoprv=findViewById(R.id.product_rv);
        layoutManager = new LinearLayoutManager(this);

        adapters=new Shop_adapters(this,getDate());
        shoprv.setLayoutManager(layoutManager);
        shoprv.setAdapter(adapters);
    }


    private ArrayList<Shop> getDate(){

        date= new ArrayList<>();
        date.add(new Shop("HAMBURGER",10));
        date.add(new Shop("HAMBURGER",15));
        date.add(new Shop("HAMBURGER",19));
        date.add(new Shop("HAMBURGER",140));
        date.add(new Shop("HAMBURGER",170));
        date.add(new Shop("PANINO",220));
        date.add(new Shop("KEBAB",121));

        return date;

    }
private float total=0;
    ProgressBar progressBar;
    private void updateTotal(float item){
            total+=item;


        }
        private  void updateProgress(int progress){
            progressBar.setProgress(progress);
        }

    @Override
    public void onChange(float prezzo) {
        updateTotal(prezzo);
        updateProgress((int)(total*100));
    }



}
