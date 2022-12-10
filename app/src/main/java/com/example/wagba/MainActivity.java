package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //ActivityMainBinding
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent intent = new Intent(this, SignUp.class);
        //Intent intent = new Intent(this, PaymentActivity.class);
        //Intent intent = new Intent(this, RestaurantActivity.class);
        //Intent intent = new Intent(this, RestaurantsActivity.class);
        //Intent intent = new Intent(this, OrderHistoryActivity.class);
        //Intent intent = new Intent(this, CartActivity.class);
        Intent intent = new Intent(this, OrderTrackingActivity.class);
        startActivity(intent);

    }
}