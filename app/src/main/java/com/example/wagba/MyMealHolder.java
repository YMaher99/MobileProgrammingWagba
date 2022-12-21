package com.example.wagba;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyMealHolder extends RecyclerView.ViewHolder {
    ImageView availability_icon;
    TextView meal_name, meal_price, meals_available;
    Button add_to_cart, remove_from_cart;


    public MyMealHolder(@NonNull View itemView) {
        super(itemView);
        availability_icon = itemView.findViewById(R.id.is_available);
        meal_name = itemView.findViewById(R.id.meal_name_tv);
        meal_price = itemView.findViewById(R.id.meal_price_tv);
        add_to_cart = itemView.findViewById(R.id.add_to_cart_button);
        remove_from_cart = itemView.findViewById(R.id.remove_from_cart_button);
        meals_available = itemView.findViewById(R.id.meals_available_tv);
    }
}
