package com.example.retrofit.data.network;

import com.example.retrofit.data.product.model.Product;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductServer {
    @GET("products")
    Call<Product.ProductResponse> getProducts();
}
