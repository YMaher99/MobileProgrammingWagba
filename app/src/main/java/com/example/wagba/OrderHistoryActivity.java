package com.example.wagba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.wagba.databinding.ActivityOrderHistoryBinding;
import com.google.android.material.navigation.NavigationBarView;

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

        binding.bottomNav.setSelectedItemId(R.id.thirdTab);

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

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.firstTab:
                        intent = new Intent(getApplicationContext(),RestaurantsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.secondTab:
                        intent = new Intent(getApplicationContext(),CartActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.thirdTab:
                        //intent = new Intent(getApplicationContext(),OrderHistoryActivity.class);
                        //startActivity(intent);
                        break;
                    case R.id.fourthTab:
                        startActivity(new Intent(getApplicationContext(),ProfilePageActivity.class));
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }
}