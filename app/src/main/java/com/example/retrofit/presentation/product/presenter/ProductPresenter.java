package com.example.retrofit.presentation.product.presenter;

import androidx.lifecycle.LiveData;

import com.example.retrofit.data.product.model.Product;

import java.util.List;

public interface ProductPresenter {
    void getAllProducts();
    void addToFavorite(Product currentProduct);
    void removeFromFavorite(Product currentProduct);
    boolean isFavorite(int id);
    LiveData<List<Product>> getAllFavorite();

}
