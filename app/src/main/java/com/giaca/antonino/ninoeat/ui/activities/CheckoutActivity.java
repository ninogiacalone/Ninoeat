package com.giaca.antonino.ninoeat.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.giaca.antonino.ninoeat.R;
import com.giaca.antonino.ninoeat.ui.activities.adapters.OrderProductsAdapter;

import java.util.ArrayList;

/**
 * Created by anton on 31/01/2019.
 */

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener,OrderProductsAdapter.onItemRemovedListener{
private TextView nomerest,indirizzorest,tot;
    private RecyclerView productRv;
    private Button payBtn;
    private LinearLayoutManager layoutManager;
    private OrderProductsAdapter adapter;
    private Order order;
    private float total;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        nomerest=findViewById(R.id.riepnome);
        indirizzorest=findViewById(R.id.indirizzoriep);
        tot=findViewById(R.id.proddd);
        productRv=findViewById(R.id.prodd);
        payBtn=findViewById(R.id.paga);
        payBtn.setOnClickListener(this);
       order=getOrder();
       total=order.getTotal();
       layoutManager=new LinearLayoutManager(this);
       productRv.setLayoutManager(layoutManager);
       adapter=new OrderProductsAdapter(order.getProdotti(),this);
       adapter.setOnItemRemovedListener(this);
       productRv.setAdapter(adapter);
       bindData();
    }

private void bindData(){
     nomerest.setText(order.getRestaurant().getNome());
     indirizzorest.setText(order.getRestaurant().getIndirizzo());
     tot.setText(String.valueOf("Totale: " + order.getTotal()));


}
    public Order getOrder() {
        Order order2= new Order();
        order2.setProdotti(getProdotti());
        order2.setRestaurant(getRestaurant());
        float prezzo=0.0f;
        for(Shop i : order2.getProdotti()){
            prezzo+=(i.getQuantity()*i.getPrezzo());
        }
        order2.setTotal(prezzo);
        return order2;

    }
    private Restaurant getRestaurant() {
        return new Restaurant("da ciccio","Roma",30.00f,"");
    }
    @Override
    public void onClick(View view) {

    }

    private ArrayList<Shop> getProdotti(){

        ArrayList<Shop> date = new ArrayList<>();
        date.add(new Shop("HAMBURGER",10f));
        date.add(new Shop("HAMBURGER",15f));
        date.add(new Shop("HAMBURGER",19f));
        date.add(new Shop("HAMBURGER",14f));
        date.add(new Shop("HAMBURGER",17f));
        date.add(new Shop("PANINO",2f));
        date.add(new Shop("KEBAB",11f));
        int cont =1;
        for(Shop i : date){
            i.setQuantity(cont);
            cont++;
        }

        return date;

    }

    public void onItemRemoved(float subtotale, int quantity){
        updateTotal(subtotale, quantity);
    }

    private void updateTotal(float subtotal, int quantity) {
        if(total == 0) return;
        total -=(subtotal*quantity);
        Log.i("ciao",String.valueOf(subtotal));
        tot.setText(String.valueOf(total));
    }
}
