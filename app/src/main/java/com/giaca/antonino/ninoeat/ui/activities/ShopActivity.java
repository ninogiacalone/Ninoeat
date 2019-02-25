package com.giaca.antonino.ninoeat.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.giaca.antonino.ninoeat.R;
import com.giaca.antonino.ninoeat.services.AppDatabase;
import com.giaca.antonino.ninoeat.services.RestController;
import com.giaca.antonino.ninoeat.ui.activities.adapters.Shop_adapters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.OnClickListener;

/**
 * Created by anton on 31/01/2019.
 */

public class ShopActivity  extends AppCompatActivity implements Shop_adapters.OnQuantityChangedListener, Response.Listener<String>,Response.ErrorListener,View.OnClickListener {
    RecyclerView shoprv;

    public Shop_adapters adapters;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Shop> date = new ArrayList<>();
    TextView totale, nome, indirizzo;
    Button checkout;
    private float total = 0;
    ProgressBar progressBar;
    Menu menu;
    Restaurant restaurant;
    ImageView im;
    float price;

    public static final String RESTAURANT_ID_KEY = "RESTAURANT_ID_KEY";
    private static final String TAG = ShopActivity.class.getSimpleName();
    private static final int LOGIN_REQUEST_CODE = 2001;
    private RestController restController;
    private String restaurantId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        im = findViewById(R.id.imm);
        shoprv = findViewById(R.id.product_rv);
        layoutManager = new LinearLayoutManager(this);
        totale = findViewById(R.id.total3);
        progressBar = findViewById(R.id.progress_bar);
        nome = findViewById(R.id.nom);
        indirizzo = findViewById(R.id.ind);
        checkout = findViewById(R.id.check_out);

        Intent intent = getIntent();

        String name = intent.getExtras().getString("name");
        String address = intent.getExtras().getString("address");
        price = intent.getExtras().getFloat("minprice");
        String id = intent.getExtras().getString("id");
        String im = intent.getExtras().getString("urlimm");

//        Log.i("concatenazione", Restaurant.ENDPOINT.concat("/").concat(id));




        adapters = new Shop_adapters(this, date);
        adapters.setOnQuanityChangedListener(this);
        shoprv.setLayoutManager(layoutManager);
        shoprv.setAdapter(adapters);

        checkout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShopActivity.this, CheckoutActivity.class));
                finish();
            }
        });
        restaurantId = getIntent().getStringExtra(ShopActivity.RESTAURANT_ID_KEY);
        restController = new RestController(this);
        restController.getRequest(
                Restaurant.ENDPOINT.concat(restaurantId),
                this,
                this);

    }


    public void updateTotal(float item) {
        total = total + item;
        totale.setText("Total: ".concat(String.valueOf(total)));
        btnEna(checkout);

    }

    public void updateProgress(int progress) {
        progressBar.setProgress(progress);
    }
    private void bindData() {
        nome.setText(restaurant.getNome());
        indirizzo.setText(restaurant.getIndirizzo());
        Glide.with(this).load(restaurant.getUrlimm()).into(im);
        progressBar.setMax((int) restaurant.getPrezzo() * 100);
    }


    @Override
    public void onChange(float prezzo) {
        updateTotal(prezzo);
        updateProgress((int) total * 100);
        btnEna(checkout);

    }

    public void btnEna(Button checkout) {
        if (total >= price) {
            checkout.setEnabled(true);
        } else
            checkout.setEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.login_menu) {
            Intent intent2 = new Intent(this, LoginActivity.class);
            startActivityForResult(intent2, LOGIN_REQUEST_CODE);

        } else if (item.getItemId() == R.id.checkout_menu) {

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.shop_menu, menu);
        this.menu = menu;
        return true;

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, error.getMessage());
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            restaurant= new Restaurant(jsonObject);
            JSONArray jsonArray=jsonObject.getJSONArray("products");
            date = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                date.add(new Shop(jsonArray.getJSONObject(i)));
            }
bindData();
            adapters.setData(date);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }


    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(TAG, "requestCode" + requestCode);
        Log.d(TAG, "resultCode" + resultCode);
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Log.d(TAG, data.getStringExtra("response"));
            menu.findItem(R.id.login_menu).setTitle("Profile").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    startActivity(new Intent(ShopActivity.this, ProfileActivity.class));
                    return true;
                }
            });

        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.checkout_menu){
          new   SaveOrder().execute();
        }
    }

    class SaveOrder extends AsyncTask<Void,Void,Void>{

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected Void doInBackground(Void... voids) {
            Order order=new Order();
            order.setTotal(total);
            order.setRestaurant(restaurant);
            List<Shop> selected=adapters.getData();
            selected.removeIf(Shop->Shop.getQuantity()<1);
                order.setProdotti((ArrayList<Shop>) selected);
            AppDatabase.getAppDatabase(ShopActivity.this).orderDao().insert(order);
                            return null;
        }
    }
}




