package com.example.wagba;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Duration;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MyMealHolder> {

    Context context;
    List<Meal_Item> items;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://wagba-6d31f-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference databaseReference ;
    DatabaseReference allTheData = database.getReference();
    String restaurant_name;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    String user_token;

    public MealAdapter(Context context, List<Meal_Item> items,String restaurant_name){
        this.context = context;
        this.items = items;
        this.restaurant_name = restaurant_name;
        this.databaseReference= database.getReference(restaurant_name);
        this.user_token = user.getEmail().replace(".","");
    }


    @NonNull
    @Override
    public MyMealHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyMealHolder(LayoutInflater.from(context).inflate(R.layout.meal_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyMealHolder holder, int position) {
        holder.meal_name.setText((items.get(position).getName()));
        holder.meal_price.setText(Double.toString(items.get(position).getPrice()) + " EGP");
        if(items.get(position).getAvailable()){
            holder.availability_icon.setImageResource(R.drawable.tick);
        }
        else{
            holder.availability_icon.setImageResource(R.drawable.cross);
        }

        holder.add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                final Boolean[] outer_flag = new Boolean[1];


                Log.d("TEST NUM",Integer.toString(items.get(pos).getNumAvailable()));
                if(items.get(pos).getNumAvailable()>0){

                    allTheData.child(items.get(pos).restaurantName).child(Integer.toString(pos)).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if(task.isSuccessful()) {
                                allTheData.child("cart").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task_inner) {

                                        if(task_inner.isSuccessful()){

                                            Log.d("TESTING",task_inner.getResult().toString());

                                            if(!task_inner.getResult().child(user_token).child("current").exists() || task_inner.getResult().child(user_token).child("current").getValue().equals("0")){
                                                task_inner.getResult().child(user_token).child("current").getRef().setValue("1");
                                                task_inner.getResult().child(user_token).child("details").child("0").getRef().setValue(items.get(pos));
                                                task.getResult().child("numAvailable").getRef().setValue(Integer.toString(Integer.parseInt(task.getResult().child("numAvailable").getValue().toString()) - 1));
                                                Toast.makeText(context.getApplicationContext(), "Added to cart", Toast.LENGTH_SHORT).show();
                                            }
                                            else{
                                                // TODO IF I NEED TO MAKE IT SO ONLY SAME RESTAURANT ORDERS
                                                Log.d("CARTTEST",task_inner.getResult().child(user_token).toString());
                                                if(items.get(pos).restaurantName.equals(task_inner.getResult().child(user_token).child("details").child("0").child("restaurantName").getValue().toString())) {
                                                    task_inner.getResult().child(user_token).child("details").child(task_inner.getResult().child(user_token).child("current").getValue().toString()).getRef().setValue(items.get(pos));
                                                    task_inner.getResult().child(user_token).child("current").getRef().setValue(Integer.toString(Integer.parseInt(task_inner.getResult().child(user_token).child("current").getValue().toString()) + 1));
                                                    task.getResult().child("numAvailable").getRef().setValue(Integer.toString(Integer.parseInt(task.getResult().child("numAvailable").getValue().toString()) - 1));
                                                    Toast.makeText(context.getApplicationContext(), "Added to cart", Toast.LENGTH_SHORT).show();

                                                }
                                                else{
                                                    Toast.makeText(context.getApplicationContext(), "Can only order from one restaurant at a time", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }

                                    }

                                });


                            }
                        }
                    });


                    // TODO DECREASE NUMBER OF MEALS IN TEXTVIEW AND ADD TO CART IN DB (DONE I THINK NEEDS TESTING)
                }
                else{
                    Log.d("MEAL",Integer.toString(items.get(pos).getNumAvailable()));
                    Toast.makeText(context,"Sorry this meal is not available right now", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.remove_from_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO CHECK IF MEAL IN CART AND INCREASE NUMBER OF MEALS IN TEXTVIEW
                int pos = holder.getAdapterPosition();



                allTheData.child("cart").child(user_token).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        Boolean flag = Boolean.TRUE;
                        if (task.isSuccessful()) {
                            for (DataSnapshot meal : task.getResult().child("details").getChildren()){
                                if (items.get(pos).getName().equals(meal.child("name").getValue().toString())){
                                    flag = Boolean.FALSE;
                                    meal.getRef().removeValue();

                                    meal.getRef().getParent().getParent().child("current").setValue(Integer.toString(Integer.parseInt(task.getResult().child("current").getValue().toString())-1));
                                    //items.get(pos).setNumAvailable(items.get(pos).getNumAvailable()+1);
                                    allTheData.child(restaurant_name).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                                            if(task.isSuccessful()){
                                                for (DataSnapshot mealInRestaurant : task.getResult().getChildren()){
                                                    if (items.get(pos).getName().equals(mealInRestaurant.child("name").getValue().toString())){
                                                        mealInRestaurant.getRef().child("numAvailable").setValue(Integer.parseInt(mealInRestaurant.child("numAvailable").getValue().toString())+1);
                                                        Toast.makeText(context.getApplicationContext(), "Removed from Cart", Toast.LENGTH_SHORT).show();
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    });
                                    //TODO increase meals available in db and local object and decrease current in cart
                                    break;
                                }
                            }
                            if (flag){
                                Toast.makeText(context.getApplicationContext(), "You do not have this meal in your cart", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                });
            }
        });

        holder.remove_item_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO CHECK IF MEAL IN CART AND INCREASE NUMBER OF MEALS IN TEXTVIEW
                int pos = holder.getAdapterPosition();



                allTheData.child("cart").child(user_token).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        Boolean flag = Boolean.TRUE;
                        if (task.isSuccessful()) {
                            for (DataSnapshot meal : task.getResult().child("details").getChildren()){
                                if (items.get(pos).getName().equals(meal.child("name").getValue().toString())){
                                    flag = Boolean.FALSE;
                                    meal.getRef().removeValue();

                                    meal.getRef().getParent().getParent().child("current").setValue(Integer.toString(Integer.parseInt(task.getResult().child("current").getValue().toString())-1));
                                    //items.get(pos).setNumAvailable(items.get(pos).getNumAvailable()+1);
                                    allTheData.child(restaurant_name).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                                            if(task.isSuccessful()){
                                                for (DataSnapshot mealInRestaurant : task.getResult().getChildren()){
                                                    if (items.get(pos).getName().equals(mealInRestaurant.child("name").getValue().toString())){
                                                        mealInRestaurant.getRef().child("numAvailable").setValue(Integer.parseInt(mealInRestaurant.child("numAvailable").getValue().toString())+1);
                                                        Toast.makeText(context.getApplicationContext(), "Removed from Cart", Toast.LENGTH_SHORT).show();
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    });
                                    //TODO increase meals available in db and local object and decrease current in cart
                                    break;
                                }
                            }
                            if (flag){
                                Toast.makeText(context.getApplicationContext(), "You do not have this meal in your cart", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                });
            }
        });

        if(items.get(position).getNumAvailable()==-1){
            holder.meals_available.setText("");
            holder.add_to_cart.setVisibility(View.GONE);
            holder.remove_from_cart.setVisibility(View.GONE);
            holder.remove_item_btn.setVisibility(View.VISIBLE);
        }
        else {
            holder.meals_available.setText("Meals Available: " + Integer.toString(items.get(position).getNumAvailable()));
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
