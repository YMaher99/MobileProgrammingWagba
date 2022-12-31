package com.example.wagba;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "email")
    private String email;

    @NonNull
    @ColumnInfo(name= "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "phone")
    private String phone;

    public User(@NonNull String email, @NonNull String name, @NonNull String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public User getUser(){return this;}

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
