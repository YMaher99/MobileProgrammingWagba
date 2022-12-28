package com.example.wagba;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<MyOrderHolder> {
    Context context;
    List<Order_Item> items;
    private  final RecyclerViewInterface recyclerViewInterface;

    public OrderAdapter(Context context, List<Order_Item> items,RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.items = items;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyOrderHolder(LayoutInflater.from(context).inflate(R.layout.order_view,parent,false),recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderHolder holder, int position) {
        holder.orderID.setText(items.get(position).getOrderID());
        holder.meal_price.setText(Double.toString(items.get(position).getTotal_price())+ " EGP");
        Log.d("ORDERTEST",Integer.toString(items.get(position).delivery_status));
        switch (items.get(position).delivery_status){
            case 0:
                holder.order_status.setText("Order Status: Ordered");
                break;
            case 1:
                holder.order_status.setText("Order Status: Accepting");
                break;
            case 2:
                holder.order_status.setText("Order Status: Preparing");
                break;
            case 3:
                holder.order_status.setText("Order Status: Delivery");
                break;

        }



    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
