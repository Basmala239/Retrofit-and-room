package com.example.retrofit.datasource.Remote;

import com.example.retrofit.model.Product;

import java.util.List;

public interface ProductNetworkResponse {
    public void onSuccess(List<Product> meal);
    public void onError(String error);
}
