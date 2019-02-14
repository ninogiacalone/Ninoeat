package com.giaca.antonino.ninoeat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.giaca.antonino.ninoeat.R;
import com.giaca.antonino.ninoeat.services.RestController;
import com.giaca.antonino.ninoeat.ui.activities.adapters.Shop_adapters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.view.View.OnClickListener;

/**
 * Created by anton on 31/01/2019.
 */

public class ShopActivity  extends AppCompatActivity implements Shop_adapters.OnQuantityChangedListener, Response.Listener<String>,Response.ErrorListener   {
    RecyclerView shoprv;
    private static final String TAG=ShopActivity.class.getName();
    public  Shop_adapters adapters;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Shop> date = new ArrayList<>();
    TextView totale,nome,indirizzo;
    Button checkout;
    private float total=0;
    ProgressBar progressBar;
    Restaurant restaurant;
    ImageView im;
    float price;


    private RestController restController;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        im=findViewById(R.id.imm);
        shoprv=findViewById(R.id.product_rv);
        layoutManager = new LinearLayoutManager(this);
        totale=findViewById(R.id.total3);
         progressBar=findViewById(R.id.progress_bar);
        nome=findViewById(R.id.nom);
        indirizzo=findViewById(R.id.ind);
        checkout=findViewById(R.id.check_out);

        Intent intent = getIntent();

        String name = intent.getExtras().getString("name");
        String address = intent.getExtras().getString("address");
        price = intent.getExtras().getFloat("minprice");
        String id = intent.getExtras().getString("id");
        String im=intent.getExtras().getString("urlimm");

        Log.i("concatenazione", Restaurant.ENDPOINT.concat("/").concat(id));

        restController=new RestController(this );
        restController.getRequest(Restaurant.ENDPOINT.concat("/").concat(id),this,this);



        adapters=new Shop_adapters(this,date);
        adapters.setOnQuanityChangedListener(this);
        shoprv.setLayoutManager(layoutManager);
        shoprv.setAdapter(adapters);
        checkout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShopActivity.this,CheckoutActivity.class));
                finish();
            }
        });


        nome.setText(name);
        indirizzo.setText(address);
        progressBar.setMax(( int )price *  100 );
    }




    public void updateTotal(float item){
        total= total + item;
       totale.setText("Total: ".concat( String.valueOf(total)));
        btnEna(checkout);

    }

    public void updateProgress(int progress){
        progressBar.setProgress(progress);
    }

    @Override
    public void onChange(float prezzo) {
        updateTotal(prezzo);
        updateProgress((int)total*100);
    }

    private void btnEna(Button checkout){
        if(total>= price){
             checkout.setEnabled(true);
        }else
            checkout.setEnabled(false);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG,error.getMessage());
        Toast.makeText(this, error.getMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(String response) {
        date = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONObject(response).getJSONArray("products");
            for(int i=0;i<jsonArray.length();i++){
                date.add(new Shop(jsonArray.getJSONObject(i)));
            }
            adapters.setData(date);
        }catch (JSONException e){
            Log.e(TAG,e.getMessage());
        }
    }
}




