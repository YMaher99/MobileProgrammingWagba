package com.example.wagba;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView res_name,res_loc,res_cuisine;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview);
        res_name = itemView.findViewById(R.id.textviewrestaurantname);
        res_loc = itemView.findViewById(R.id.textviewrestaurantlocation);
        res_cuisine = itemView.findViewById(R.id.textviewcuisine);
    }
}
