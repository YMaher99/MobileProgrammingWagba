package com.example.wagba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.wagba.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://wagba-6d31f-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference users = database.getReference();

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });

        binding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.emailText.getText().toString();
                String password = binding.passwordText.getText().toString();

                if(TextUtils.isEmpty(email)){
                    binding.emailText.setError("Email Cannot be empty");
                    binding.emailText.requestFocus();
                }

                else if(TextUtils.isEmpty(password)){
                    binding.passwordText.setError("Password Cannot be empty");
                    binding.passwordText.requestFocus();
                }

                else{
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this,RestaurantsActivity.class));
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Logging in failed: "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
/*                users.child("users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()){
                            //TODO toast something maybe
                        }
                        else{
                            String email = binding.emailText.getText().toString();
                            String password = binding.passwordText.getText().toString();
                            Boolean flag = Boolean.TRUE;

                            for(DataSnapshot user: task.getResult().child("details").getChildren()){
                                if(user.child("email").getValue().toString().equals(email)){
                                    if(user.child("password").getValue().toString().equals(password)){
                                        Intent intent = new Intent(MainActivity.this,RestaurantsActivity.class);
                                        startActivity(intent);
                                        flag = Boolean.FALSE;
                                        break;
                                    }
                                    else{
                                        Toast.makeText(MainActivity.this,"Incorrect Password",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            if(flag){Toast.makeText(MainActivity.this,"Invalid Email",Toast.LENGTH_SHORT).show();}

                        }
                    }
                });*/
            }
        });


    }
}