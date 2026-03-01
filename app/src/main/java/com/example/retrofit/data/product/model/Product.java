package com.example.retrofit.data.product.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "products")
public class Product {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "brand")
    private String brand;

    @ColumnInfo(name = "price")
    private double price;

    @ColumnInfo(name = "rating")
    private double rating;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "thumbnail")
    private String thumbnail;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getThumbnail() { return thumbnail; }
    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }

    public static class ProductResponse {
        private ArrayList<Product> products;
        public ArrayList<Product> getProducts() { return products; }
    }
}