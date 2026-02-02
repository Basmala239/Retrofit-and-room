package com.example.retrofit.datasource.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.retrofit.DB.AppDatabase;
import com.example.retrofit.model.Product;

import java.util.List;

public class ProductLocalDataSource {
    private ProductDAO productDAO;

    public ProductLocalDataSource(Context context){
        AppDatabase database = AppDatabase.getInstance(context);
        this.productDAO = database.productDao();
    }

    public void addToFavorites(Product product) {
        new Thread(() -> productDAO.insertProduct(product)).start();
    }

    public void removeFromFavorites(Product product) {
        new Thread(() -> productDAO.deleteProduct(product)).start();
    }

    public LiveData<List<Product>> getAllFavorites() {
        return productDAO.getAllProducts();
    }

    public boolean isFavorite(int productId) {
        return productDAO.isProductFavorite(productId) > 0;
    }
}