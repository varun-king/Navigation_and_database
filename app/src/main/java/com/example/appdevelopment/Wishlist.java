package com.example.appdevelopment;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "wishlistDB")

public class Wishlist {

    @PrimaryKey(autoGenerate = true)
    @NonNull

    private int wishlist_id;
    private String username;
    private String place_name;
    private String place_img;

    @Ignore
    public Wishlist(int wishlist_id, String username, String place_name) {
        this.wishlist_id = wishlist_id;
        this.username = username;
        this.place_name = place_name;
    }

    @Ignore
    public Wishlist(int wishlist_id, String username, String place_name, String place_img) {
        this.wishlist_id = wishlist_id;
        this.username = username;
        this.place_name = place_name;
        this.place_img = place_img;
    }

    public Wishlist(){

    }

    @Ignore
    public Wishlist(String username, String place_name, String place_img) {
        this.username = username;
        this.place_name = place_name;
        this.place_img = place_img;
    }

    @Ignore
    public Wishlist(@NonNull String username, String place_name) {
        this.username = username;
        this.place_name = place_name;
    }



    public String getPlace_img() {
        return place_img;
    }

    public void setPlace_img(String place_img) {
        this.place_img = place_img;
    }

    public int getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(int wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }
}
