package com.example.retrofit.favorite;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit.R;
import com.example.retrofit.datasource.local.ProductLocalDataSource;
import com.example.retrofit.model.Product;

public class FavoriteActivity extends AppCompatActivity implements onFavoriteClick{
    private RecyclerView rvFavorites;
    private ProductLocalDataSource localDataSource;
    private FavoriteAdaptor adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        rvFavorites = findViewById(R.id.rv_favorites);
        rvFavorites.setLayoutManager(new LinearLayoutManager(this));
        localDataSource = new ProductLocalDataSource(this);

        localDataSource.getAllFavorites().observe(this, favorites -> {
            if (favorites == null || favorites.isEmpty()) {
                Toast.makeText(this, "No favorites yet", Toast.LENGTH_SHORT).show();
            }

            if (adapter == null) {
                adapter = new FavoriteAdaptor(this, favorites, this);
                rvFavorites.setAdapter(adapter);
            } else {
                adapter.updateData(favorites);
            }
        });
    }


    @Override
    public void onRemoveFavorite(Product product) {
        localDataSource.removeFromFavorites(product);
        Toast.makeText(this, "Removed: " + product.getTitle(), Toast.LENGTH_SHORT).show();
    }
}