package com.example.wagba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.wagba.databinding.ActivityCartBinding;
import com.example.wagba.databinding.ActivityRestaurantBinding;
import com.google.android.material.navigation.NavigationBarView;

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
        items.add(new Meal_Item("Big Mac",50.0,Boolean.TRUE));
        items.add(new Meal_Item("Happy Meal",35.0,Boolean.TRUE));
        items.add(new Meal_Item("Big Mac",50.0,Boolean.TRUE));
        items.add(new Meal_Item("Happy Meal",35.0,Boolean.TRUE));
        items.add(new Meal_Item("Big Mac",50.0,Boolean.TRUE));
        items.add(new Meal_Item("Happy Meal",35.0,Boolean.TRUE));
        items.add(new Meal_Item("Big Mac",50.0,Boolean.TRUE));
        items.add(new Meal_Item("Happy Meal",35.0,Boolean.TRUE));
        items.add(new Meal_Item("Big Mac",50.0,Boolean.TRUE));
        items.add(new Meal_Item("Happy Meal",35.0,Boolean.TRUE));
        items.add(new Meal_Item("Big Mac",50.0,Boolean.TRUE));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MealAdapter(getApplicationContext(),items));

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
                        //intent = new Intent(getApplicationContext(),CartActivity.class);
                        //startActivity(intent);
                        break;
                    case R.id.thirdTab:
                        intent = new Intent(getApplicationContext(),OrderHistoryActivity.class);
                        startActivity(intent);

                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        binding.checkoutFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PaymentActivity.class);
                startActivity(intent);
            }
        });
    }
}