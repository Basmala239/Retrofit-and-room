package com.example.retrofit.data.product.datasource.Remote;

import com.example.retrofit.data.product.model.Product;

import java.util.List;

public interface ProductNetworkResponse {
    public void onSuccess(List<Product> meal);
    public void onError(String error);
}
