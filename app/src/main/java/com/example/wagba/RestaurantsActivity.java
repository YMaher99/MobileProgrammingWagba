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

import com.example.wagba.databinding.ActivityRestaurantsBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class RestaurantsActivity extends AppCompatActivity implements RecyclerViewInterface {


    ActivityRestaurantsBinding binding;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://wagba-6d31f-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference restaurants = database.getReference("Restaurants");
    List<Restaurant_Item> items = new ArrayList<Restaurant_Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Log.i("CONTEXT",getApplicationContext().getClass().toString());

/*        binding.recyclerview.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                Log.i("TEST","touched");
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });*/

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.firstTab:
                        //intent = new Intent(getApplicationContext(),RestaurantsActivity.class);
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
                Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = binding.recyclerview;
        items.add(new Restaurant_Item("Mcdonald's","Restaurant Details Placeholder","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant_Item("kfc","Restaurant Details Placeholder","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant_Item("kfc","Restaurant Details Placeholder","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant_Item("kfc","Restaurant Details Placeholder","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant_Item("kfc","Restaurant Details Placeholder","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant_Item("kfc","Restaurant Details Placeholder","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant_Item("kfc","Restaurant Details Placeholder","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant_Item("kfc","Restaurant Details Placeholder","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant_Item("kfc","Restaurant Details Placeholder","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant_Item("kfc","Restaurant Details Placeholder","Fast Food",R.drawable.mcdonalds));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RestaurantAdapter(getApplicationContext(),items,this));

    }

    @Override
    public void onItemClick(int position) {
        Log.i("RESTAURANT",items.get(position).getName());
        Intent intent = new Intent(this, RestaurantActivity.class);
        startActivity(intent);
    }
}