package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.wagba.databinding.ActivityOrderHistoryBinding;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {

    ActivityOrderHistoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderHistoryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        RecyclerView recyclerView = binding.ordersRv;
        List<Order_Item> items = new ArrayList<Order_Item>();
        ArrayList<Meal_Item> meal_list = new ArrayList<Meal_Item>();
        meal_list.add(new Meal_Item("Happy Meal",35.0,Boolean.TRUE));
        items.add(new Order_Item("A-130",meal_list,Boolean.TRUE));
        items.add(new Order_Item("B-130",meal_list,Boolean.FALSE));
        items.add(new Order_Item("A-130",meal_list,Boolean.TRUE));
        items.add(new Order_Item("A-130",meal_list,Boolean.TRUE));
        items.add(new Order_Item("A-130",meal_list,Boolean.TRUE));
        items.add(new Order_Item("A-130",meal_list,Boolean.TRUE));
        items.add(new Order_Item("A-130",meal_list,Boolean.TRUE));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new OrderAdapter(getApplicationContext(),items));
    }
}