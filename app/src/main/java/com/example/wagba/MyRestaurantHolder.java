package com.example.wagba;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRestaurantHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView res_name,res_loc,res_cuisine;


    public MyRestaurantHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview);
        res_name = itemView.findViewById(R.id.meal_name_tv);
        res_loc = itemView.findViewById(R.id.meal_price_tv);
        res_cuisine = itemView.findViewById(R.id.textviewcuisine);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recyclerViewInterface != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(res_name.getText().toString());
                    }
                }
            }
        });
    }
}
