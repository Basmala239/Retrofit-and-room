package com.example.retrofit.presentation.favorite.presenter;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.retrofit.data.product.ProductRepository;
import com.example.retrofit.data.product.datasource.local.ProductLocalDataSource;
import com.example.retrofit.presentation.favorite.view.FavView;
import com.example.retrofit.data.product.model.Product;

import java.util.List;

public class FavPresenterImp implements FavPresenter {

    FavView favView;
    ProductRepository productRepository;

    public FavPresenterImp(Context context, FavView favView) {
        this.favView = favView;
        productRepository = new ProductRepository(context);
    }

    @Override
    public LiveData<List<Product>> getAllFavProducts() {
        return productRepository.getAllFavorites();
    }

    @Override
    public void deleteFavProduct(Product product) {
        productRepository.removeFromFavorites(product);
        favView.onDeleteSuccess(product.getTitle());
    }
}