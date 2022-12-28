package com.example.wagba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.wagba.databinding.ActivityCartBinding;
import com.example.wagba.databinding.ActivityRestaurantBinding;
import com.example.wagba.databinding.ActivityRestaurantsBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {

    ActivityRestaurantBinding binding;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://wagba-6d31f-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference restaurant;
    List<Meal_Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        String res_name = getIntent().getStringExtra("res_name");
        restaurant = database.getReference(res_name);
        items = new ArrayList<Meal_Item>();

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                items = new ArrayList<Meal_Item>();
                for (DataSnapshot meal: dataSnapshot.getChildren()){
                    Log.d("TESTREMOVE",meal.child("name").toString());
                    String name = meal.child("name").getValue().toString();
                    double price = Double.parseDouble(meal.child("price").getValue().toString());
                    Boolean isAvailable = Boolean.parseBoolean(meal.child("isAvailable").getValue().toString());
                    int numAvailable = Integer.parseInt(meal.child("numAvailable").getValue().toString());
                    items.add(new Meal_Item(name,price,isAvailable,numAvailable, restaurant.getKey()));


                }
                RecyclerView recyclerView = binding.rv;

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new MealAdapter(getApplicationContext(),items,res_name));
                //Log.d("restaurants",dataSnapshot.getChildren());
                // ..
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("FAILED", "loadPost:onCancelled", databaseError.toException());
            }
        };
        restaurant.addValueEventListener(postListener);



        RecyclerView recyclerView = binding.rv;


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MealAdapter(getApplicationContext(),items,res_name));

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
                        intent = new Intent(getApplicationContext(),OrderHistoryActivity.class);
                        startActivity(intent);

                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        binding.cartFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });
    }


}