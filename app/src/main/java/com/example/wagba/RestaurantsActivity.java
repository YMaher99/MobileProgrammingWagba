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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RestaurantsActivity extends AppCompatActivity implements RecyclerViewInterface {


    ActivityRestaurantsBinding binding;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://wagba-6d31f-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference restaurants = database.getReference("restaurants");
    List<Restaurant_Item> items = new ArrayList<Restaurant_Item>();
    FirebaseAuth mAuth;
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for (DataSnapshot restaurant: dataSnapshot.getChildren()){
                    String name = restaurant.child("name").getValue().toString();
                    String restaurant_details = restaurant.child("restaurant details").getValue().toString();
                    String cuisine = restaurant.child("cuisine").getValue().toString();
                    StorageReference image_ref = storageReference.child(restaurant.child("name").getValue().toString()+".png");
                    Log.d("IMAGE",image_ref.toString());
                    items.add(new Restaurant_Item(name,restaurant_details,cuisine,image_ref));

                }
                RecyclerView recyclerView = binding.recyclerview;

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new RestaurantAdapter(getApplicationContext(),items,RestaurantsActivity.this));
                //Log.d("restaurants",dataSnapshot.getChildren());
                // ..
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("FAILED", "loadPost:onCancelled", databaseError.toException());
            }
        };
        restaurants.addValueEventListener(postListener);


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
                    case R.id.fourthTab:
                        startActivity(new Intent(getApplicationContext(),ProfilePageActivity.class));
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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RestaurantAdapter(getApplicationContext(),items,this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){startActivity(new Intent(RestaurantsActivity.this,MainActivity.class));}
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.bottomNav.setSelectedItemId(R.id.firstTab);
    }

    @Override
    public void onItemClick(int position) {
        Log.i("RESTAURANT",items.get(position).getName());
        Intent intent = new Intent(this, RestaurantActivity.class);
        intent.putExtra("res_name",items.get(position).getName());
        startActivity(intent);
    }
}