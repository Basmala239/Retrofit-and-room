package com.example.retrofit.presentation.favorite.presenter;

import androidx.lifecycle.LiveData;

import com.example.retrofit.data.product.model.Product;

import java.util.List;

public interface FavPresenter {
    LiveData<List<Product>> getAllFavProducts();
    void deleteFavProduct(Product product);


}
