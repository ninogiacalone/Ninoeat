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
import android.view.View;
import android.widget.Button;

import com.giaca.antonino.ninoeat.R;
import com.giaca.antonino.ninoeat.ui.activities.adapters.Restaurant_adapters;

import java.util.ArrayList;


/**
 * Created by anton on 31/01/2019.
 */

public class MainActivity  extends AppCompatActivity implements View.OnClickListener {
    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;
    Restaurant_adapters adapter;
    ArrayList<Restaurant> arrayList;

Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.btnmenu);
        button.setOnClickListener(this);
        restaurantRV = findViewById(R.id.places_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new Restaurant_adapters(this, getData());

        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);
    }

    private ArrayList<Restaurant> getData(){
        arrayList = new ArrayList<>();
        arrayList.add(new Restaurant("Mc Donalds","Via Tiburtina",12.40f));
        arrayList.add(new Restaurant("Burger king","Via Roma",10.50f));
        arrayList.add(new Restaurant("Pizzeria da ciccio","Via Marsala",10.0f));
        arrayList.add(new Restaurant("Mangi Pizza","Via Casal",15.50f));





        return arrayList;
    }


 @Override
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


    @Override
    public void onClick(View view) {
//TO DO INTET
    }
}

