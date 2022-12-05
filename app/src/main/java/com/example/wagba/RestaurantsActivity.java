package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.wagba.databinding.ActivityRestaurantsBinding;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsActivity extends AppCompatActivity {

    ActivityRestaurantsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantsBinding.inflate(getLayoutInflater());
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

        RecyclerView recyclerView = binding.recyclerview;
        List<Restaurant_Item> items = new ArrayList<Restaurant_Item>();
        items.add(new Restaurant_Item("Mcdonald's","ta7t beety","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant_Item("kfc","ta7t beety","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant_Item("kfc","ta7t beety","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant_Item("kfc","ta7t beety","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant_Item("kfc","ta7t beety","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant_Item("kfc","ta7t beety","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant_Item("kfc","ta7t beety","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant_Item("kfc","ta7t beety","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant_Item("kfc","ta7t beety","Fast Food",R.drawable.mcdonalds));
        items.add(new Restaurant_Item("kfc","ta7t beety","Fast Food",R.drawable.mcdonalds));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RestaurantAdapter(getApplicationContext(),items));

    }
}