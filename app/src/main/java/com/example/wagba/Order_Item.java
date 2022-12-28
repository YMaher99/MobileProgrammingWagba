package com.example.wagba;

import java.util.ArrayList;

public class Order_Item {

    public String orderID;
    public ArrayList<Meal_Item> meals;
    public double total_price;
    public int delivery_status;

    public Order_Item(String orderID, double total_price, int delivery_status) {
        this.orderID = orderID;
        this.meals = meals;
        this.total_price = total_price;
        this.delivery_status = delivery_status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public ArrayList<Meal_Item> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal_Item> meals) {
        this.meals = meals;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }


}
