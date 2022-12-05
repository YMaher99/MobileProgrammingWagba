package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.wagba.databinding.ActivityRestaurantBinding;
import com.example.wagba.databinding.ActivityRestaurantsBinding;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {

    ActivityRestaurantBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        RecyclerView recyclerView = binding.rv;
        List<Meal_Item> items = new ArrayList<Meal_Item>();
        items.add(new Meal_Item("Happy Meal",35.0,Boolean.TRUE));
        items.add(new Meal_Item("Big Mac",50.0,Boolean.FALSE));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MealAdapter(getApplicationContext(),items));
    }
}