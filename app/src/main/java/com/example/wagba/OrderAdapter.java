package com.example.wagba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<MyOrderHolder> {
    Context context;
    List<Order_Item> items;

    public OrderAdapter(Context context, List<Order_Item> items){
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyOrderHolder(LayoutInflater.from(context).inflate(R.layout.order_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderHolder holder, int position) {
        holder.orderID.setText(items.get(position).getOrderID());
        holder.meal_price.setText(Double.toString(items.get(position).getTotal_price())+ "EGP");

        if(items.get(position).getIs_delivered()){
            holder.order_status.setText("Order Status: Delivered");
        }

        else{
            holder.order_status.setText("Order Status: Pending Delivery");
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
