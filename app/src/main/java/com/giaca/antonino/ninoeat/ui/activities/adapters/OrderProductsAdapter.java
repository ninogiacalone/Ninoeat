package com.giaca.antonino.ninoeat.ui.activities.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.giaca.antonino.ninoeat.R;
import com.giaca.antonino.ninoeat.ui.activities.Shop;

import java.util.ArrayList;

/**
 * Created by anton on 11/02/2019.
 */

public class OrderProductsAdapter extends RecyclerView.Adapter<OrderProductsAdapter.OrderProductViewHolder> {
private ArrayList<Shop> date;
private Context context;
private LayoutInflater inflater;


    public interface onItemRemovedListener{
        void onItemRemoved(float subtotal, int quantity);

    }


    private onItemRemovedListener onItemRemovedListener;


    public OrderProductsAdapter.onItemRemovedListener getOnItemRemovedListener() {
        return onItemRemovedListener;
    }

    public void setOnItemRemovedListener(OrderProductsAdapter.onItemRemovedListener onItemRemovedListener) {
        this.onItemRemovedListener = onItemRemovedListener;
    }





    public OrderProductsAdapter(ArrayList<Shop> date, Context context) {
        this.date = date;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }


    @Override
    public OrderProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderProductViewHolder(inflater.inflate(R.layout.item_order_shop,parent,false));
    }

    @Override
    public void onBindViewHolder(OrderProductViewHolder holder, int position) {
        Shop shop=date.get(position);
        holder.productNameTv.setText(shop.getCibo());
        holder.quantityTv.setText(String.valueOf(shop.getQuantity()));
        holder.subtotalTv.setText(String.valueOf(shop.getPrezzo()));

    }

    private void removeItem(int index){
        date.remove(index);
        notifyItemRemoved(index);

    }
    @Override
    public int getItemCount() {
        return date.size();
    }

    public class OrderProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView quantityTv,productNameTv,subtotalTv;
        public ImageButton removeBtn;

        public OrderProductViewHolder(View itemView) {
            super(itemView);
            quantityTv = itemView.findViewById(R.id.quantita_tv);
            productNameTv = itemView.findViewById(R.id.prodd_nametv);
            subtotalTv = itemView.findViewById(R.id.subtt);
            removeBtn = itemView.findViewById(R.id.imageButton);
            removeBtn.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {


            AlertDialog.Builder removeAlert = new AlertDialog.Builder(context);
            removeAlert.setTitle(R.string.be_careful).setMessage(R.string.remove_title).setPositiveButton(R.string.conferma, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            onItemRemovedListener.onItemRemoved(date.get(getAdapterPosition()).getPrezzo(), date.get(getAdapterPosition()).getQuantity());
                            removeItem(getAdapterPosition());


                        }
                    })
                    .setNegativeButton(R.string.cancella, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .create().show();
        }
    }
}





