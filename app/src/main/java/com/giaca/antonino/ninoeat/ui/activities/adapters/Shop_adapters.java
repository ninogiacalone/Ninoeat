package com.giaca.antonino.ninoeat.ui.activities.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.giaca.antonino.ninoeat.R;
import com.giaca.antonino.ninoeat.ui.activities.Shop;

import java.util.ArrayList;

/**
 * Created by anton on 07/02/2019.
 */

public class Shop_adapters extends RecyclerView.Adapter  {
  //  private Context context;
    private LayoutInflater inflater;
    private ArrayList<Shop> date;

    public interface OnQuantityChangedListener{


        void  onChange( float prezzo);
    }

    public OnQuantityChangedListener getOnQuanityChangedListener() {
        return onQuantityChangedListener;
    }

    public void setOnQuanityChangedListener(OnQuantityChangedListener onQuanityChangedListener) {
        this.onQuantityChangedListener = onQuanityChangedListener;
    }

    private OnQuantityChangedListener onQuantityChangedListener;
    public Shop_adapters(Context context, ArrayList<Shop> date) {
        this.inflater = inflater.from(context);
        this.date = date;
    //    this.context = context;


    }

    @NonNull

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {  //quando c'è una singola riga
        View view = inflater.inflate(R.layout.item_shop, viewGroup, false);
        return new ShopViewHolder(view);
    }


    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ShopViewHolder vh = (ShopViewHolder) viewHolder;
        vh.hamburgher.setText(date.get(i).getCibo());
        vh.prezzo.setText(""+date.get(i).getPrezzo());
        vh.modinum.setText(String.valueOf(date.get(i).getQuantity()));


    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    public void setData(ArrayList<Shop> data) {
        this.date = data;
        notifyDataSetChanged();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView hamburgher;
        public TextView prezzo;
        public Button meno;
        public TextView modinum;
        public Button piu;




        public ShopViewHolder(View itemView) {

            super(itemView);
            hamburgher = itemView.findViewById(R.id.hamburgher);
            prezzo = itemView.findViewById(R.id.prezzo2);
            meno = itemView.findViewById(R.id.meno);
            modinum = itemView.findViewById(R.id.num);

            piu = itemView.findViewById(R.id.piu);
            meno.setOnClickListener(this);
            piu.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.piu) {
                Shop shop = date.get(getAdapterPosition());
                shop.increaseQuantity();
                notifyItemChanged(getAdapterPosition());
                onQuantityChangedListener.onChange(shop.getPrezzo());
            }else if(view.getId()==R.id.meno){

                Shop shop=date.get(getAdapterPosition());
                if(shop.getQuantity()==0)return;
                shop.decreaseQuantity();
                notifyItemChanged(getAdapterPosition());

                onQuantityChangedListener.onChange(shop.getPrezzo()*-1);
            }


        }



    }
}