package com.example.katarzyna.playtune.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class DiscModel {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    private String name;
    private String author;
    private long year;
    private String genre;
    private double price;
    private String mainImage;
    private String country;
    private boolean inCart;
    private boolean inFavorites;
    private String secondImage;
    private String thirdImage;
    private String description;


    public DiscModel(int id, String author, String name, long year, String genre, double price, String mainImage, String country, boolean inCart, boolean inFavorites, String secondImage, String thirdImage, String description) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.price = price;
        this.mainImage = mainImage;
        this.country = country;
        this.inCart = inCart;
        this.inFavorites = inFavorites;
        this.secondImage = secondImage;
        this.thirdImage = thirdImage;
        this.description = description;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public Long getYear() { return year;}

    public double getPrice() {
        return price;
    }

    public String getMainImage() {
        return mainImage;
    }

    public boolean isInCart() {
        return inCart;
    }

    public void setInCart(boolean inCart) {
        this.inCart = inCart;
    }

    public boolean isInFavorites() {
        return inFavorites;
    }

    public void setInFavorites(boolean inFavorites) {
        this.inFavorites = inFavorites;
    }

    public String getSecondImage() {
        return secondImage;
    }

    public String getThirdImage() {
        return thirdImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
