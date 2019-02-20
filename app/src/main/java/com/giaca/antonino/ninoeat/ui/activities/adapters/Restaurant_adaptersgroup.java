package com.giaca.antonino.ninoeat.ui.activities.adapters;

/**
 * Created by anton on 04/02/2019.
 */

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.giaca.antonino.ninoeat.R;
import com.giaca.antonino.ninoeat.ui.activities.Restaurant;
import com.giaca.antonino.ninoeat.ui.activities.ShopActivity;

import java.util.ArrayList;

public class Restaurant_adaptersgroup extends RecyclerView.Adapter {

    private final Context context;
    private LayoutInflater inflater;
    private ArrayList<Restaurant> data;


    public Restaurant_adaptersgroup(Context context, ArrayList<Restaurant> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    public Restaurant_adaptersgroup(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = new ArrayList<>();
    }

    public boolean isGridMode() {
        return isGridMode;
    }

    private boolean isGridMode;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {  //quando c'è una singola riga
        View view = inflater.inflate(R.layout.item_restaurantgroup, viewGroup, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {      //quando sta per essere visualizzata una lista
        RestaurantViewHolder vh = (RestaurantViewHolder) viewHolder; //cast a restaurantviewholder
        vh.restaurantName.setText(data.get(i).getNome());
        vh.indirizzoName.setText(data.get(i).getIndirizzo());
        vh.prezzoMin.setText("Prezzo minino " + data.get(i).getPrezzo());
        Glide.with(context).load(data.get(i).getUrlimm()).into(vh.imm2);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<Restaurant> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView restaurantName;
        public TextView indirizzoName;
        public TextView prezzoMin;
        public Button menubtn;
        public ImageView imm2;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.nome);
            indirizzoName = itemView.findViewById(R.id.indirizzo);
            prezzoMin = itemView.findViewById(R.id.prezzomin);
            menubtn = itemView.findViewById(R.id.btnmenu);
            menubtn.setOnClickListener(this);
            imm2 = itemView.findViewById(R.id.im2);


        }


        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.btnmenu) {
                //context.startActivity(new Intent(context,ShopActivity.class));
                Restaurant rest = data.get(getAdapterPosition());
                //context.startActivity(new Intent(context,ShopActivity.class));
              Intent intent = new Intent(context, ShopActivity.class);
                intent.putExtra(ShopActivity.RESTAURANT_ID_KEY,data.get(getAdapterPosition()).getId());
                  /*intent.putExtra("name", rest.getNome());
                intent.putExtra("address", rest.getIndirizzo());
                intent.putExtra("minprice", rest.getPrezzo());
                intent.putExtra("id", rest.getId());
                intent.putExtra("urlimm",rest.getUrlimm());*/

                context.startActivity(intent);
            }
        }
    }
}

