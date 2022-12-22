package com.example.wagba;

import java.util.ArrayList;

public class Cart_Item {

    ArrayList<Meal_Item> Meals = new ArrayList<Meal_Item>();
    String orderID;

    public Cart_Item(String orderID) {
        this.orderID = orderID;
    }

    public ArrayList<Meal_Item> getMeals() {
        return this.Meals;
    }

    public void addMeal(Meal_Item meal) {
        this.Meals.add(meal);
    }

    public void removeMeal(Meal_Item meal){
        this.Meals.remove(meal);
    }

}
