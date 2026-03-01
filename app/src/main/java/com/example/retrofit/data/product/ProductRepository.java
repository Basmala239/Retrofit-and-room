package com.example.retrofit.data.product;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.retrofit.data.product.datasource.Remote.ProductNetworkResponse;
import com.example.retrofit.data.product.datasource.Remote.ProductRemoteDataSource;
import com.example.retrofit.data.product.datasource.local.ProductLocalDataSource;
import com.example.retrofit.data.product.model.Product;

import java.util.List;

public class ProductRepository {
    ProductLocalDataSource productLocalDataSource;
    ProductRemoteDataSource productRemoteDataSource;
    public ProductRepository(Context context){
        productLocalDataSource = new ProductLocalDataSource(context);
        productRemoteDataSource = new ProductRemoteDataSource();
    }

    public LiveData<List<Product>> getAllFavorites(){
        return productLocalDataSource.getAllFavorites();
    }
    public void removeFromFavorites(Product product) {
        productLocalDataSource.removeFromFavorites(product);
    }
    public void addToFavorite(Product currentProduct){
        productLocalDataSource.addToFavorites(currentProduct);
    }
    public void getProducts(ProductNetworkResponse response) {
        productRemoteDataSource.getProducts(response);
    }


    public boolean isFavorite(int id) {
        return productLocalDataSource.isFavorite(id);
    }

}
