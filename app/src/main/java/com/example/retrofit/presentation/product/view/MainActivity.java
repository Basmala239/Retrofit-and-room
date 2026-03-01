package com.example.retrofit.presentation.product.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit.R;
import com.example.retrofit.data.product.datasource.Remote.ProductNetworkResponse;
import com.example.retrofit.data.product.datasource.Remote.ProductRemoteDataSource;
import com.example.retrofit.data.product.datasource.local.ProductLocalDataSource;
import com.example.retrofit.presentation.favorite.view.FavoriteActivity;
import com.example.retrofit.data.product.model.Product;
import com.example.retrofit.presentation.product.presenter.ProductPresenter;
import com.example.retrofit.presentation.product.presenter.ProductPresenterImp;
import com.example.retrofit.presentation.productdetails.view.ProductActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnProductClicked, ProductView {
    RecyclerView rvProduct;
    ProgressBar progressBar;
    Button favBtn;
    MyAdapter adapter;
    ProductPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new ProductPresenterImp(getApplicationContext(),this);
        favBtn = findViewById(R.id.go_to_fav);
        rvProduct = findViewById(R.id.rv_product);
        progressBar = findViewById(R.id.loading_progress);
        rvProduct.setLayoutManager(new LinearLayoutManager(this));

        presenter.getAllProducts();

        presenter.getAllFavorite().observe(this, favorites -> {
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        });

        favBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onProductClick(Product product) {
        Intent intent = new Intent(MainActivity.this, ProductActivity.class);
        intent.putExtra("title", product.getTitle());
        intent.putExtra("brand", product.getBrand());
        intent.putExtra("price", product.getPrice());
        intent.putExtra("rating", product.getRating());
        intent.putExtra("description", product.getDescription());
        intent.putExtra("imageUrl", product.getThumbnail());
        startActivity(intent);
    }

    @Override
    public void onFavoriteAdded(String productTitle) {
        Toast.makeText(this, "Added to favorites: " + productTitle, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFavoriteRemoved(String productTitle) {
        Toast.makeText(this, "Removed from favorites: " + productTitle, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onProductsLoaded(List<Product> products) {
        adapter = new MyAdapter(MainActivity.this, products, this,presenter );
        rvProduct.setAdapter(adapter);
    }

    @Override
    public void onProductsLoadError(String error) {
        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
    }
}