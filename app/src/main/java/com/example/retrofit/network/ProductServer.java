package com.example.retrofit.network;

import com.example.retrofit.model.Product;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductServer {
    @GET("products")
    Call<Product.ProductResponse> getProducts();
}
