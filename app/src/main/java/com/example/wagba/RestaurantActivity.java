package com.example.wagba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.wagba.databinding.ActivityRestaurantBinding;

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

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        List<Restaurant> items = new ArrayList<Restaurant>();
        items.add(new Restaurant("Mcdonald's","ta7t beety","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant("kfc","ta7t beety","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant("kfc","ta7t beety","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant("kfc","ta7t beety","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant("kfc","ta7t beety","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant("kfc","ta7t beety","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant("kfc","ta7t beety","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant("kfc","ta7t beety","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant("kfc","ta7t beety","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant("kfc","ta7t beety","Fast Food",R.drawable.mcdonalds));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));

    }
}