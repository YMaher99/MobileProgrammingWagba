package com.example.wagba;

import java.util.ArrayList;

public class Order_Item {

    String orderID;
    ArrayList<Meal_Item> meals;
    double total_price;
    Boolean is_delivered;

    public Order_Item(String orderID, ArrayList<Meal_Item> meals, Boolean is_delivered) {
        this.orderID = orderID;
        this.meals = meals;
        for (Meal_Item meal: meals){
            total_price+=meal.getPrice();
        }
        this.is_delivered = is_delivered;
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

    public Boolean getIs_delivered() {
        return is_delivered;
    }

    public void setIs_delivered(Boolean is_delivered) {
        this.is_delivered = is_delivered;
    }
}
