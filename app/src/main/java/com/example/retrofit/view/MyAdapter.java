package com.example.retrofit.view;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.retrofit.R;
import com.example.retrofit.datasource.local.ProductLocalDataSource;
import com.example.retrofit.model.Product;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private Context _context;
    private List<Product> _products;
    private ProductLocalDataSource localDataSource;
    private OnProductClicked listener;

    public MyAdapter(@NonNull Context context, List<Product> products, OnProductClicked listener) {
        _context = context;
        _products = products;
        localDataSource = new ProductLocalDataSource(context);
        this.listener = listener;
    }


    @NonNull
    @Override
    public MyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(_context);
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new MyAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyHolder holder, int position) {
        Product currentProduct = _products.get(position);
        holder.tvTitle.setText(currentProduct.getTitle());
        holder.tvPrice.setText("$ " + currentProduct.getPrice());

        boolean isFavorite = localDataSource.isFavorite(currentProduct.getId());
        holder.btnFavorite.setImageResource(
                isFavorite ? R.drawable.ic_favorite_filled : R.drawable.ic_favorite_border
        );

        Glide.with(_context)
                .load(currentProduct.getThumbnail())
                .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                .into(holder.imgView);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onProductClick(currentProduct);
            }
        });

        holder.btnFavorite.setOnClickListener(v -> {
            if (localDataSource.isFavorite(currentProduct.getId())) {
                localDataSource.removeFromFavorites(currentProduct);
                holder.btnFavorite.setImageResource(R.drawable.ic_favorite_border);
                Toast.makeText(_context, "Removed from favorites", Toast.LENGTH_SHORT).show();
            } else {
                localDataSource.addToFavorites(currentProduct);
                holder.btnFavorite.setImageResource(R.drawable.ic_favorite_filled);
                Toast.makeText(_context, "Added to favorites", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return _products != null ? _products.size() : 0;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvPrice;
        de.hdodenhof.circleimageview.CircleImageView imgView;
        ImageButton btnFavorite;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_product_title);
            tvPrice = itemView.findViewById(R.id.tv_product_price);
            imgView = itemView.findViewById(R.id.in_product_image);
            btnFavorite = itemView.findViewById(R.id.btn_favorite);
        }
    }
    public void updateList(List<Product> newList) {
        this._products = newList;
        notifyDataSetChanged();
    }
}
