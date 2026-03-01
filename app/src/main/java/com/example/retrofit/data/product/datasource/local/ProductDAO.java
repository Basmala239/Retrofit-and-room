package com.example.retrofit.data.product.datasource.local;

import com.example.retrofit.data.product.model.Product;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);

    @Delete
    void deleteProduct(Product product);

    @Query("SELECT * FROM products")
    LiveData<List<Product>> getAllProducts();

    @Query("SELECT COUNT(*) FROM products WHERE id = :productId")
    int isProductFavorite(int productId);
}
