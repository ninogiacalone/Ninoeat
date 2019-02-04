package com.giaca.antonino.ninoeat.ui.activities.adapters;

/**
 * Created by anton on 04/02/2019.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.giaca.antonino.ninoeat.R;
import com.giaca.antonino.ninoeat.ui.activities.Restaurant;

import java.util.ArrayList;

public class Restaurant_adapters extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private ArrayList<Restaurant> data;



    public Restaurant_adapters(Context context,ArrayList<Restaurant> data){
        inflater = LayoutInflater.from(context);
        this.data = data;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {  //quando c'è una singola riga
        View view = inflater.inflate(R.layout.item_restaurant, viewGroup, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {      //quando sta per essere visualizzata una lista
        RestaurantViewHolder vh = (RestaurantViewHolder) viewHolder; //cast a restaurantviewholder
        vh.restaurantName.setText(data.get(i).getNome());
        vh.indirizzoName.setText(data.get(i).getIndirizzo());
        vh.prezzoMin.setText( "Prezzo minino "+data.get(i).getPrezzo());



    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class RestaurantViewHolder extends RecyclerView.ViewHolder{

        public TextView restaurantName;
        public TextView indirizzoName;
        public TextView prezzoMin;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.nome);
            indirizzoName=itemView.findViewById(R.id.indirizzo);
            prezzoMin=itemView.findViewById(R.id.prezzomin);



        }


    }


}
