package com.example.retrofit.view;

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
import com.example.retrofit.datasource.Remote.ProductNetworkResponse;
import com.example.retrofit.datasource.Remote.ProductRemoteDataSource;
import com.example.retrofit.datasource.local.ProductLocalDataSource;
import com.example.retrofit.favorite.FavoriteActivity;
import com.example.retrofit.model.Product;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnProductClicked{
    RecyclerView rvProduct;
    ProgressBar progressBar;
    Button favBtn;
    MyAdapter adapter;
    ProductLocalDataSource localDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localDataSource = new ProductLocalDataSource(this);
        favBtn = findViewById(R.id.go_to_fav);
        rvProduct = findViewById(R.id.rv_product);
        progressBar = findViewById(R.id.loading_progress);
        rvProduct.setLayoutManager(new LinearLayoutManager(this));

        fetchProducts();

        localDataSource.getAllFavorites().observe(this, favorites -> {
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        });

        favBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
            startActivity(intent);
        });
    }

    private void fetchProducts() {
        ProductRemoteDataSource productRemoteDataSource = new ProductRemoteDataSource();
        progressBar.setVisibility(View.VISIBLE);

        productRemoteDataSource.getProducts(new ProductNetworkResponse(){
            @Override
            public void onSuccess(List<Product> products) {
                progressBar.setVisibility(View.GONE);
                adapter = new MyAdapter(MainActivity.this, products, MainActivity.this);
                rvProduct.setAdapter(adapter);
            }
            @Override
            public void onError(String error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }
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
}