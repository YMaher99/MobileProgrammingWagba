package com.example.wagba;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyMealHolder extends RecyclerView.ViewHolder {
    ImageView availability_icon;
    TextView meal_name, meal_price;


    public MyMealHolder(@NonNull View itemView) {
        super(itemView);
        availability_icon = itemView.findViewById(R.id.is_available);
        meal_name = itemView.findViewById(R.id.meal_name_tv);
        meal_price = itemView.findViewById(R.id.meal_price_tv);
    }
}
