package com.example.wagba;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
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

        Glide.with(context.getApplicationContext()).load(items.get(position).image_ref).into(holder.imageView);
        //holder.imageView.setImageResource(items.get(position).getImage());
        //holder.imageView.setImageBitmap(getImageBitmap(items.get(position).getImage_url()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
