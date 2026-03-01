package com.example.retrofit.presentation.product.presenter;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.retrofit.data.product.ProductRepository;
import com.example.retrofit.data.product.datasource.Remote.ProductNetworkResponse;
import com.example.retrofit.data.product.datasource.Remote.ProductRemoteDataSource;
import com.example.retrofit.data.product.datasource.local.ProductLocalDataSource;
import com.example.retrofit.data.product.model.Product;
import com.example.retrofit.presentation.product.view.ProductView;

import java.util.List;

public class ProductPresenterImp implements ProductPresenter{
    private ProductView productView;
    ProductRepository productRepository;

    public  ProductPresenterImp(Context context,ProductView productView){
        productRepository = new ProductRepository(context);
        this.productView = productView;
    }

    @Override
    public void getAllProducts() {
        productView.showLoading();
        productRepository.getProducts(new ProductNetworkResponse(){
            @Override
            public void onSuccess(List<Product> products) {
                productView.hideLoading();
                productView.onProductsLoaded(products);

            }
            @Override
            public void onError(String error) {
                productView.hideLoading();
                productView.onProductsLoadError(error);
            }
        });
    }
    @Override
    public void addToFavorite(Product currentProduct){
        productRepository.addToFavorite(currentProduct);
        productView.onFavoriteAdded(currentProduct.getTitle());
    }

    @Override
    public void removeFromFavorite(Product currentProduct) {
        productRepository.removeFromFavorites(currentProduct);
        productView.onFavoriteRemoved(currentProduct.getTitle());

    }

    @Override
    public boolean isFavorite(int id) {
        return productRepository.isFavorite(id);
    }

    @Override
    public LiveData<List<Product>> getAllFavorite() {
        return productRepository.getAllFavorites();
    }

}
