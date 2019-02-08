package com.giaca.antonino.ninoeat.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.giaca.antonino.ninoeat.R;
import com.giaca.antonino.ninoeat.ui.activities.adapters.Shop_adapters;

import java.util.ArrayList;

/**
 * Created by anton on 31/01/2019.
 */

public class ShopActivity  extends AppCompatActivity implements Shop_adapters.OnQuantityChangedListener   {
    RecyclerView shoprv;

public  Shop_adapters adapters;
RecyclerView.LayoutManager layoutManager;
ArrayList<Shop> date;
    TextView totale,nome,indirizzo;
    Button checkout;
    private float total=0;
    ProgressBar progressBar;

      Restaurant ciccio;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        shoprv=findViewById(R.id.product_rv);
        layoutManager = new LinearLayoutManager(this);
totale=findViewById(R.id.total3);
progressBar=findViewById(R.id.progress_bar);
nome=findViewById(R.id.nom);
indirizzo=findViewById(R.id.ind);
checkout=findViewById(R.id.check_out);


        adapters=new Shop_adapters(this,getDate());
        adapters.setOnQuanityChangedListener(this);
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
        date.add(new Shop("PANINO",2));
        date.add(new Shop("KEBAB",11));

        return date;

    }







    public void updateTotal(float item){
        total= total + item;
       totale.setText("Total: ".concat( String.valueOf(total)));
    }

    public void updateProgress(int progress){
        progressBar.setProgress(progress);
    }

    @Override
    public void onChange(float prezzo) {
        updateTotal(prezzo);
        updateProgress((int)total*100);
    }
}




