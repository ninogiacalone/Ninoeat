package com.giaca.antonino.ninoeat.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.giaca.antonino.ninoeat.R;
import com.giaca.antonino.ninoeat.services.RestController;
import com.giaca.antonino.ninoeat.ui.activities.adapters.Restaurant_adapters;
import com.giaca.antonino.ninoeat.ui.activities.adapters.Restaurant_adaptersgroup;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


/**
 * Created by anton on 31/01/2019.
 */

public class MainActivity  extends AppCompatActivity implements Response.Listener<String>,Response.ErrorListener {
    private static final String TAG=MainActivity.class.getName();
    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.LayoutManager layoutManagerGrid;
    Restaurant_adaptersgroup adaptersgroup;
    Restaurant_adapters adapter;
    ArrayList<Restaurant> arrayList= new ArrayList<>();
    boolean view=false;
    private RestController restController;

    SharedPreferences.Editor editor;
    SharedPreferences share;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editor=getSharedPreferences("ninoeat",MODE_PRIVATE).edit();
        share=getSharedPreferences("ninoeat",MODE_PRIVATE);
        layoutManagerGrid=new GridLayoutManager(this,2);
        restaurantRV = findViewById(R.id.places_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new Restaurant_adapters(this);
        adaptersgroup=new Restaurant_adaptersgroup(this);
        view=share.getBoolean("gridmode",false );

    restController=new RestController(this );
        restController.getRequest(Restaurant.ENDPOINT,this,this);

    }

    private ArrayList<Restaurant> getData(){
        arrayList = new ArrayList<>();
        arrayList.add(new Restaurant("Mc Donalds","Via Tiburtina",12.40f,"https://notizie.tiscali.it/export/sites/notizie/.galleries/16/fast_food.jpg_1267619266.jpg"));
        arrayList.add(new Restaurant("Burger king","Via Roma",10.50f,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKeVeLnrjypUr31YKAwMzBpOmzRk_IO5utBlVhXyA5MWJwIZwt"));
        arrayList.add(new Restaurant("Pizzeria 'ciccio' ","Via Marsala",10.0f,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvQU4vpPd_eOSu7b0PsKZBrZlinWAkqCze318Isq6pCYhErTY0"));
        arrayList.add(new Restaurant("Da Massimo","Via bergamo",15.00f,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmwhkvt74amWk3BNqLpuE-UHggyLLsM-kIXrgsHOARuK8k5NWt"));


        return arrayList;
    }


 @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
     setMode();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.login_menu) {

            startActivity(new Intent(this, LoginActivity.class));
            return true;
        } else if (item.getItemId() == R.id.checkout_menu) {

            startActivity(new Intent(this, CheckoutActivity.class));
        }else if(item.getItemId() == R.id.grid_menu)
            setMode();

            return super.onOptionsItemSelected(item);
        }

    private void setMode()
    {
        if(!view){
            restaurantRV.setLayoutManager(layoutManagerGrid);
            restaurantRV.setAdapter(adaptersgroup);
            editor.putBoolean("gridmode",false).commit();
            editor.apply();

            view=true;
        }else{
            restaurantRV.setLayoutManager(layoutManager);
            restaurantRV.setAdapter(adapter);
            editor.putBoolean("gridmode",true).commit();
            editor.apply();
            view=false;
        }



    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG,error.getMessage());
        Toast.makeText(this, error.getMessage(),Toast.LENGTH_LONG).show();

    }

    @Override
    public void onResponse(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for(int i=0;i<jsonArray.length();i++){
                arrayList.add(new Restaurant(jsonArray.getJSONObject(i)));
            }
            adapter.setData(arrayList);
            adaptersgroup.setData(arrayList);

        }catch (JSONException e){
            Log.e(TAG,e.getMessage());
        }

    }
}

