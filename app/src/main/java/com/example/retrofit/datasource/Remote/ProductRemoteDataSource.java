package com.example.retrofit.datasource.Remote;

import com.example.retrofit.model.Product;
import com.example.retrofit.network.ProductServer;
import com.example.retrofit.network.Network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRemoteDataSource {
    ProductServer api;

    public ProductRemoteDataSource() {
        api = Network.getProductApi();
    }

    public void getProducts(ProductNetworkResponse callback) {
        api.getProducts().enqueue(new Callback<Product.ProductResponse>() {
            @Override
            public void onResponse(Call<Product.ProductResponse> call, Response<Product.ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null &&
                        response.body().getProducts() != null && !response.body().getProducts().isEmpty()) {
                    callback.onSuccess(response.body().getProducts());
                } else {
                    callback.onError("Failed to fetch products");
                }
            }

            @Override
            public void onFailure(Call<Product.ProductResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}