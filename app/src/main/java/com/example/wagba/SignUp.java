package com.example.wagba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.wagba.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding binding;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://wagba-6d31f-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference users = database.getReference();

    FirebaseAuth mAuth;

    public class User {

        public String email;
        public String password;

        public User() {

        }

        public User(String email, String password) {
            this.email = email;
            this.password = password;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.emailText.getText().toString();
                String password = binding.passwordText.getText().toString();
                if(!password.equals(binding.confirmPasswordText.getText().toString())){return;}

                if(TextUtils.isEmpty(email)){
                    binding.emailText.setError("Email Cannot be empty");
                    binding.emailText.requestFocus();
                }

                else if(TextUtils.isEmpty(password)){
                    binding.passwordText.setError("Password Cannot be empty");
                    binding.passwordText.requestFocus();
                }

                else if(!password.equals(binding.confirmPasswordText.getText().toString())){
                    binding.confirmPasswordText.setError("Does not match the password");
                    binding.confirmPasswordText.requestFocus();}

                else{
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this,MainActivity.class));
                            }
                            else{
                                Toast.makeText(SignUp.this, "Registration Failed " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }
}