package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.wagba.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://wagba-6d31f-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference restaurants = database.getReference("Restaurants");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //Intent intent = new Intent(this, SignUp.class);
        //Intent intent = new Intent(this, PaymentActivity.class);
        //Intent intent = new Intent(this, RestaurantActivity.class);
        Intent intent = new Intent(this, RestaurantsActivity.class);
        //Intent intent = new Intent(this, OrderHistoryActivity.class);
        //Intent intent = new Intent(this, CartActivity.class);
        //Intent intent = new Intent(this, OrderTrackingActivity.class);
        startActivity(intent);

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });


    }
}