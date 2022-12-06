package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.wagba.databinding.ActivityCartBinding;
import com.example.wagba.databinding.ActivityRestaurantBinding;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding binding;



    public Cart_Item cart = new Cart_Item(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        RecyclerView recyclerView = binding.recyclerview;
        List<Meal_Item> items = cart.getMeals();
        items.add(new Meal_Item("Happy Meal",35.0,Boolean.TRUE));
        items.add(new Meal_Item("Big Mac",50.0,Boolean.FALSE));
        items.add(new Meal_Item("Happy Meal",35.0,Boolean.TRUE));
        items.add(new Meal_Item("Big Mac",50.0,Boolean.FALSE));
        items.add(new Meal_Item("Happy Meal",35.0,Boolean.TRUE));
        items.add(new Meal_Item("Big Mac",50.0,Boolean.FALSE));
        items.add(new Meal_Item("Happy Meal",35.0,Boolean.TRUE));
        items.add(new Meal_Item("Big Mac",50.0,Boolean.FALSE));
        items.add(new Meal_Item("Happy Meal",35.0,Boolean.TRUE));
        items.add(new Meal_Item("Big Mac",50.0,Boolean.FALSE));
        items.add(new Meal_Item("Happy Meal",35.0,Boolean.TRUE));
        items.add(new Meal_Item("Big Mac",50.0,Boolean.FALSE));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MealAdapter(getApplicationContext(),items));
    }
}