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
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding binding;


    FirebaseDatabase database = FirebaseDatabase.getInstance("https://wagba-6d31f-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference cartRef = database.getReference("cart");
    List<Meal_Item> items = new ArrayList<Meal_Item>();


    public Cart_Item cart = new Cart_Item("0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        RecyclerView recyclerView = binding.recyclerview;

        setContentView(view);

        ValueEventListener postListener = new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                items = new ArrayList<Meal_Item>();
                for(DataSnapshot cart_item: dataSnapshot.child("details").getChildren()){
                    String name = cart_item.child("name").getValue().toString();
                    int numAvailable = Integer.parseInt(cart_item.child("numAvailable").getValue().toString());
                    double price = Double.parseDouble(cart_item.child("price").getValue().toString());
                    boolean isAvailable = Boolean.parseBoolean(cart_item.child("isAvailable").getValue().toString());
                    Log.d("CARTTEST", "onDataChange: " + name);
                    items.add(new Meal_Item(name,price,isAvailable,numAvailable));
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                //TODO RESTAURANT NAME STUFF
                recyclerView.setAdapter(new MealAdapter(getApplicationContext(),items,"Bakery"));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        };
        cartRef.addValueEventListener(postListener);

        //List<Meal_Item> items = cart.getMeals();


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //TODO RESTAURANT NAME STUFF
        recyclerView.setAdapter(new MealAdapter(getApplicationContext(),items,"Bakery"));

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