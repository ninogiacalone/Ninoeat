package com.giaca.antonino.ninoeat.ui.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
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
Menu menu;
    LoginReceiver receiver;
    SharedPreferences.Editor editor;
    SharedPreferences share;
    public static final String LOGIN_ACTION="LOGIN_ACTION";



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
        receiver= new LoginReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,new IntentFilter());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        this.menu=menu;
        inflater.inflate(R.menu.menu_main, menu);
        setMode();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(receiver);
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
    public class LoginReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG,"LOGIN SUCCESS");
            menu.findItem(R.id.login_menu).setTitle("profile").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    startActivity(new Intent(MainActivity.this,ProfileActivity.class));
                    return true;
                }
            });
        }
    }
}

