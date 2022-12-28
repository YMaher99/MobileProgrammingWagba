package com.example.wagba;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyOrderHolder extends RecyclerView.ViewHolder {
    TextView orderID, meal_price, order_status;

    public MyOrderHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        orderID = itemView.findViewById(R.id.order_id_tv);
        meal_price = itemView.findViewById(R.id.order_total_tv);
        order_status = itemView.findViewById(R.id.order_status_tv);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recyclerViewInterface != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(position);
                    }
                }
            }
        });
    }
}
