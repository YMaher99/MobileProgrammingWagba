package com.example.wagba;

import com.google.firebase.storage.StorageReference;

public class Restaurant_Item {
    private String name;
    private String address;
    private String cuisineType;
    public StorageReference image_ref;

    public Restaurant_Item(String name, String address, String cuisineType, StorageReference image_ref) {
        this.name = name;
        this.address = address;
        this.cuisineType = cuisineType;
        this.image_ref = image_ref;
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
