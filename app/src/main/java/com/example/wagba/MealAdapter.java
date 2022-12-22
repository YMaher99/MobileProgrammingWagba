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

    public MealAdapter(Context context, List<Meal_Item> items,String restaurant_name){
        this.context = context;
        this.items = items;
        this.restaurant_name = restaurant_name;
        this.databaseReference= database.getReference(restaurant_name);
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

                Log.d("TEST NUM",Integer.toString(items.get(pos).getNumAvailable()));
                if(items.get(pos).getNumAvailable()>0){
                    String meal_name = items.get(pos).getName();

                    databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            Integer meal_num = null;
                            DataSnapshot needed_restaurant = null;
                            if (task.isSuccessful()){
                                for (DataSnapshot meal: task.getResult().getChildren()){
                                    if(meal.child("name").getValue().toString().equals(meal_name)){
                                        meal_num = Integer.parseInt(meal.getKey().toString());
                                        needed_restaurant = meal;
                                        break;
                                    }
                                }
                                if (meal_num != null) {
                                    databaseReference.child(Integer.toString(meal_num)).child("numAvailable").setValue(Integer.toString(Integer.parseInt(needed_restaurant.child("numAvailable").getValue().toString())-1));
                                    items.get(pos).setNumAvailable(items.get(pos).getNumAvailable()-1);
                                    allTheData.child("cart").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                                            if(task.isSuccessful()) {
                                                //TODO add to cart (DONE NEEDS TESTING)
                                                Toast.makeText(context.getApplicationContext(), "Added to cart", Toast.LENGTH_SHORT).show();
                                                allTheData.child("cart").child("details").child(task.getResult().child("current").getValue().toString()).setValue(items.get(pos));
                                                allTheData.child("cart").child("current").setValue(Integer.parseInt(task.getResult().child("current").getValue().toString())+1);
                                            }
                                        }
                                    });
                                }
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
                allTheData.child("cart").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        Boolean flag = Boolean.TRUE;
                        if (task.isSuccessful()) {
                            for (DataSnapshot meal : task.getResult().child("details").getChildren()){
                                if (items.get(pos).getName().equals(meal.child("name").getValue().toString())){
                                    flag = Boolean.FALSE;
                                    meal.getRef().removeValue();
                                    allTheData.child("cart").child("current").setValue(Integer.toString(Integer.parseInt(task.getResult().child("current").getValue().toString())-1));
                                    items.get(pos).setNumAvailable(items.get(pos).getNumAvailable()+1);
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
        holder.meals_available.setText("Meals Available: " + Integer.toString(items.get(position).getNumAvailable()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
