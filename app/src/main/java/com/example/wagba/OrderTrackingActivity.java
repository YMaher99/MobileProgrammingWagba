package com.example.wagba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.wagba.databinding.ActivityOrderTrackingBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import moe.feng.common.stepperview.VerticalStepperItemView;
import moe.feng.common.stepperview.VerticalStepperView;


public class OrderTrackingActivity extends AppCompatActivity {

    ActivityOrderTrackingBinding binding;

    private VerticalStepperItemView mSteppers[] = new VerticalStepperItemView[4];
    VerticalStepperItemView current_state;
    VerticalStepperItemView currentStep;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://wagba-6d31f-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference orders = database.getReference("orders");
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String user_token = mAuth.getCurrentUser().getEmail().replace(".","");
    String order_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderTrackingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        order_name = getIntent().getStringExtra("order_name");
        binding.topTv.setText(order_name);

        mSteppers[0] = binding.stepper0;
        mSteppers[1] = binding.stepper1;
        mSteppers[2] = binding.stepper2;
        mSteppers[3] = binding.stepper3;
        VerticalStepperItemView.bindSteppers(mSteppers);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int delivery_status = Integer.parseInt(snapshot.child("delivery_status").getValue().toString());
                if(delivery_status>=1) {
                    for (int i = 1; i <= delivery_status; i++) {
                        mSteppers[i - 1].nextStep();
                    }
                    if(delivery_status == 4){mSteppers[3].setState(VerticalStepperItemView.STATE_DONE);}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        orders.child(user_token).child(order_name).addValueEventListener(postListener);


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

    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.bottomNav.setSelected(false);
    }


}