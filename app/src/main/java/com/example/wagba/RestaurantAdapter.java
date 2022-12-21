package com.example.wagba;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        //holder.imageView.setImageResource(items.get(position).getImage());
        //holder.imageView.setImageBitmap(getImageBitmap(items.get(position).getImage_url()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("TAG", "Error getting bitmap", e);
        }
        return bm;
    }
}
