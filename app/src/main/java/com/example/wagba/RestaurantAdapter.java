package com.example.wagba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<MyRestaurantHolder> {

    Context context;
    List<Restaurant_Item> items;
    private final RecyclerViewInterface recyclerViewInterface;


    public RestaurantAdapter(Context context, List<Restaurant_Item> items, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.items = items;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyRestaurantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyRestaurantHolder(LayoutInflater.from(context).inflate(R.layout.restaurant_view,parent,false),recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRestaurantHolder holder, int position) {
        holder.res_name.setText((items.get(position).getName()));
        holder.res_loc.setText((items.get(position).getAddress()));
        holder.res_cuisine.setText((items.get(position).getCuisineType()));
        holder.imageView.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
