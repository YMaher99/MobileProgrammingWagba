package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.wagba.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //ActivityMainBinding
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this,RestaurantActivity.class);
        startActivity(intent);


    }
}