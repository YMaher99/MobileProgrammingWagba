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

import com.example.wagba.databinding.ActivityOrderHistoryBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity implements RecyclerViewInterface {

    ActivityOrderHistoryBinding binding;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://wagba-6d31f-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference orders;
    FirebaseAuth mAuth;
    String user_token;
    List<Order_Item> items = new ArrayList<Order_Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderHistoryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();
        user_token = mAuth.getCurrentUser().getEmail().replace(".","");
        orders = database.getReference("orders").child(user_token);


        RecyclerView recyclerView = binding.ordersRv;
        ArrayList<Meal_Item> meal_list = new ArrayList<Meal_Item>();


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new OrderAdapter(getApplicationContext(),items,OrderHistoryActivity.this));

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
                        //intent = new Intent(getApplicationContext(),OrderHistoryActivity.class);
                        //startActivity(intent);
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

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                items = new ArrayList<Order_Item>();
                for (DataSnapshot order: snapshot.getChildren()){
                    if(order.getKey().equals("current")){continue;}
                    Log.d("ORDERTEST",order.toString());
                    String orderID = order.getKey();
                    double total_price = Double.parseDouble(order.child("total_price").getValue().toString());
                    int delivery_status = Integer.parseInt(order.child("delivery_status").getValue().toString());
                    items.add(new Order_Item(orderID,total_price,delivery_status));
                }
                binding.ordersRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                binding.ordersRv.setAdapter(new OrderAdapter(getApplicationContext(),items,OrderHistoryActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        orders.addValueEventListener(postListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.bottomNav.setSelectedItemId(R.id.thirdTab);

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this,OrderTrackingActivity.class);
        intent.putExtra("order_name",items.get(position).orderID);
        startActivity(intent);
    }
}