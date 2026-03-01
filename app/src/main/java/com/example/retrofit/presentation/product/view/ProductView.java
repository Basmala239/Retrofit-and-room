package com.example.retrofit.presentation.product.view;

import com.example.retrofit.data.product.model.Product;

import java.util.List;

public interface ProductView {
    void showLoading();
    void hideLoading();
    void onProductsLoaded(List<Product> products);
    void onProductsLoadError(String error);
    void onFavoriteAdded(String productTitle);
    void onFavoriteRemoved(String productTitle);
}
