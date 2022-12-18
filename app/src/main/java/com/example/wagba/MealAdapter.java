package com.example.wagba;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.Duration;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MyMealHolder> {

    Context context;
    List<Meal_Item> items;

    public MealAdapter(Context context, List<Meal_Item> items){
        this.context = context;
        this.items = items;
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
                if(items.get(holder.getAdapterPosition()).getNumAvailable()>0){
                    // TODO DECREASE NUMBER OF MEALS IN TEXTVIEW AND ADD TO CART IN DB
                }
                else{
                    Toast.makeText(context,"Sorry this meal is not available right now", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.remove_from_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO CHECK IF MEAL IN CART AND INCREASE NUMBER OF MEALS IN TEXTVIEW
                // if ()
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
