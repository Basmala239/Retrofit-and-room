package com.example.retrofit.presentation.favorite.view;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit.R;
import com.example.retrofit.presentation.favorite.presenter.FavPresenter;
import com.example.retrofit.presentation.favorite.presenter.FavPresenterImp;
import com.example.retrofit.data.product.model.Product;

public class FavoriteActivity extends AppCompatActivity implements OnFavoriteClick, FavView {
    private RecyclerView rvFavorites;
    private FavoriteAdaptor adapter;

    FavPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        rvFavorites = findViewById(R.id.rv_favorites);
        rvFavorites.setLayoutManager(new LinearLayoutManager(this));
        presenter = new FavPresenterImp(getApplicationContext(),this);

        presenter.getAllFavProducts().observe(this, favorites -> {
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
        presenter.deleteFavProduct(product);
    }

    @Override
    public void onDeleteSuccess(String title) {
        Toast.makeText(this, "Removed: " + title, Toast.LENGTH_SHORT).show();

    }
}