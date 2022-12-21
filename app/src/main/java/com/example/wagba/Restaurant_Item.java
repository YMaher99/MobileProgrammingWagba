package com.example.wagba;

public class Restaurant_Item {
    private String name;
    private String address;
    private String cuisineType;

    public Restaurant_Item(String name, String address, String cuisineType, String image_url) {
        this.name = name;
        this.address = address;
        this.cuisineType = cuisineType;
        this.image_url = image_url;
    }

    private String image_url;



    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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
