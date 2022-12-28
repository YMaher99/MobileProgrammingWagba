package com.example.wagba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.wagba.databinding.ActivityPaymentBinding;
import com.example.wagba.databinding.ActivityRestaurantsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class PaymentActivity extends AppCompatActivity {

    ActivityPaymentBinding binding;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://wagba-6d31f-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference cartRef = database.getReference("cart");
    DatabaseReference ordersRef = database.getReference("orders");
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    String user_token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.mealPriceTv.setText("0");
        user_token = user.getEmail().replace(".","");
        cartRef.child(user_token).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (Integer.parseInt(task.getResult().child("current").getValue().toString()) > 0) {
                        for (DataSnapshot meal : task.getResult().child("details").getChildren()) {
                            binding.mealPriceTv.setText(Double.toString(Double.parseDouble(binding.mealPriceTv.getText().toString()) + Double.parseDouble(meal.child("price").getValue().toString())));
                        }
                    }
                }
            }
        });

        binding.orderNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(binding.paymentTotalTv.getText().toString().equals(0))){
                    Toast.makeText(PaymentActivity.this, "Can't order an empty order", Toast.LENGTH_SHORT).show();
                }
                if(binding.paymentRadioGroup.getCheckedRadioButtonId() != -1 && binding.deliveryRadioGroup.getCheckedRadioButtonId() != -1 && binding.deliveryGateRadioGroup.getCheckedRadioButtonId() != -1){
                    ordersRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if(task.isSuccessful()){
                                if(!task.getResult().child(user_token).child("current").exists()){
                                    task.getResult().child(user_token).child("current").getRef().setValue("1");
                                    task.getResult().child(user_token).child("Order0").getRef().setValue(new Order_Item("Order0",Double.parseDouble(binding.mealPriceTv.getText().toString()),1));
                                    cartRef.child(user_token).child("current").setValue("0");
                                    cartRef.child(user_token).child("details").removeValue();
                                }
                                else{
                                    task.getResult().child(user_token).child("Order"+task.getResult().child(user_token).child("current").getValue().toString()).getRef().setValue(new Order_Item("Order"+task.getResult().child(user_token).child("current").getValue().toString(),Double.parseDouble(binding.mealPriceTv.getText().toString()),1));
                                    task.getResult().child(user_token).child("current").getRef().setValue(Integer.toString(Integer.parseInt(task.getResult().child(user_token).child("current").getValue().toString()) + 1));
                                    cartRef.child(user_token).child("current").setValue("0");
                                    cartRef.child(user_token).child("details").removeValue();
                                }
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(PaymentActivity.this, "Please select choices for all radiogroups", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(PaymentActivity.this,OrderTrackingActivity.class));
            }
        });
    }
}