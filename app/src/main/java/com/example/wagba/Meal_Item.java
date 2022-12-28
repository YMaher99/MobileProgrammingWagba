package com.example.wagba;

public class Meal_Item {
    public String name;
    public double price;
    public Boolean isAvailable;
    public int numAvailable;
    public String restaurantName;

    public Meal_Item(){

    }

    public Meal_Item(String name, double price, Boolean isAvailable){

        this(name,price,isAvailable,0,"");
    }

    public Meal_Item(String name, double price, Boolean isAvailable, int numAvailable, String restaurantName) {
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
        this.numAvailable = numAvailable;
        this.restaurantName = restaurantName;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public int getNumAvailable() {
        return numAvailable;
    }

    public void setNumAvailable(int numAvailable) {
        this.numAvailable = numAvailable;
    }
}
