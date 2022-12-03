package com.example.wagba;

public class Restaurant {
    private String name;
    private String address;
    private String cuisineType;
    private int image;

    public Restaurant(String name, String address, String cuisineType, int image) {
        this.name = name;
        this.address = address;
        this.cuisineType = cuisineType;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }
}
