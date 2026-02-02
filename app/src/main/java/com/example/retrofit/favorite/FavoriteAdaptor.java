package com.example.retrofit.favorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.retrofit.R;
import com.example.retrofit.datasource.local.ProductLocalDataSource;
import com.example.retrofit.model.Product;
import java.util.List;

public class FavoriteAdaptor extends RecyclerView.Adapter<FavoriteAdaptor.MyHolder> {
    private Context _context;
    private List<Product> _products;
    private onFavoriteClick listener;

    public FavoriteAdaptor(@NonNull Context context, List<Product> products, onFavoriteClick listener) {
        _context = context;
        _products = products;
        this.listener = listener;
    }

    public void updateData(List<Product> newList) {
        this._products = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(_context).inflate(R.layout.item_product, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Product currentProduct = _products.get(position);
        holder.tvTitle.setText(currentProduct.getTitle());
        holder.tvPrice.setText("$ " + currentProduct.getPrice());

        holder.btnFavorite.setImageResource(R.drawable.ic_favorite_filled);

        Glide.with(_context).load(currentProduct.getThumbnail()).into(holder.imgView);

        holder.btnFavorite.setOnClickListener(v -> {
            if (listener != null) {
                listener.onRemoveFavorite(currentProduct);
            }
        });
    }

    @Override
    public int getItemCount() {
        return _products != null ? _products.size() : 0;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvPrice;
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
}