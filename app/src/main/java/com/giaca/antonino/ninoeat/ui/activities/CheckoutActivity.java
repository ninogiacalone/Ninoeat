package com.giaca.antonino.ninoeat.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.giaca.antonino.ninoeat.R;
import com.giaca.antonino.ninoeat.ui.activities.adapters.OrderProductsAdapter;

import java.util.ArrayList;

/**
 * Created by anton on 31/01/2019.
 */

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener{
private TextView nomerest,indirizzorest,tot;
    private RecyclerView productRv;
    private Button payBtn;
    private LinearLayoutManager layoutManager;
    private OrderProductsAdapter adapter;
    private Order order;


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
       layoutManager=new LinearLayoutManager(this);
       productRv.setLayoutManager(layoutManager);
       adapter=new OrderProductsAdapter(order.getProdotti(),this);
       productRv.setAdapter(adapter);
       bindData();
    }

private void bindData(){
     nomerest.setText(order.getRestaurant().getNome());
     indirizzorest.setText(order.getRestaurant().getIndirizzo());
     tot.setText(String.valueOf(order.getTotal()));


}
    public Order getOrder() {
        Order order1= new Order();
        order1.setProdotti(getProdotti());
        order1.setRestaurant(getRestaurant());
        order1.setTotal(30.00f);
        return order1;

    }
    private Restaurant getRestaurant() {
        return new Restaurant("da ciccio","Roma",30.00f,"");
    }
    @Override
    public void onClick(View view) {

    }

    private ArrayList<Shop> getProdotti(){

        ArrayList<Shop> date = new ArrayList<>();
        date.add(new Shop("HAMBURGER",10));
        date.add(new Shop("HAMBURGER",15));
        date.add(new Shop("HAMBURGER",19));
        date.add(new Shop("HAMBURGER",140));
        date.add(new Shop("HAMBURGER",170));
        date.add(new Shop("PANINO",2));
        date.add(new Shop("KEBAB",11));

        return date;

    }
}
