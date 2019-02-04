package com.giaca.antonino.ninoeat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.giaca.antonino.ninoeat.R;
import com.giaca.antonino.ninoeat.ui.activities.adapters.Restaurant_adapters;

import java.util.ArrayList;


/**
 * Created by anton on 31/01/2019.
 */

public class MainActivity  extends AppCompatActivity {
    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;
    Restaurant_adapters adapter;
    ArrayList<String> arrayList;
    ArrayList<String> arrayList1;
   //ArrayList<Float> arrayList2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restaurantRV = findViewById(R.id.places_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new Restaurant_adapters(this, getData(),getData1());

        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);
    }

    private ArrayList<String> getData(){
        arrayList = new ArrayList<>();
        arrayList.add("McDonald's");
        arrayList.add("Burger king");
        arrayList.add("KFC");
        arrayList.add("Calabrese");



        return arrayList;
    }

private ArrayList<String> getData1(){
        arrayList1=new ArrayList<>();
        arrayList1.add("Via Sandro Sandri");
        arrayList1.add("Via Marsala");
        arrayList1.add("vai roma");
        arrayList1.add("via soci");
        return  arrayList1;
}
/*private ArrayList<Float>getData2(){

    arrayList2=new ArrayList<>();
    arrayList2.add(14,23f);
    arrayList2.add(11,45f);
    arrayList2.add(20,50f);
    return arrayList2;
}
   */ @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.login_menu) {

            startActivity(new Intent(this, LoginActivity.class));
            return true;
        } else if (item.getItemId() == R.id.checkout_menu) {

            startActivity(new Intent(this, CheckoutActivity.class));
        }

            return super.onOptionsItemSelected(item);
        }


    }

