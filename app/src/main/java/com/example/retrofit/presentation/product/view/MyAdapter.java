package com.example.retrofit.presentation.product.view;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.retrofit.R;
import com.example.retrofit.data.product.datasource.local.ProductLocalDataSource;
import com.example.retrofit.data.product.model.Product;
import com.example.retrofit.presentation.product.presenter.ProductPresenter;
import com.example.retrofit.presentation.product.presenter.ProductPresenterImp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private Context _context;
    private List<Product> _products;
    private OnProductClicked listener;
    private ProductPresenter presenter;

    public MyAdapter(@NonNull Context context, List<Product> products,
                     OnProductClicked listener, ProductPresenter presenter) {
        _context = context;
        _products = products;
        this.listener = listener;
        this.presenter = presenter;
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

        boolean isFavorite = presenter.isFavorite(currentProduct.getId());
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
            if (presenter.isFavorite(currentProduct.getId())) {
                presenter.removeFromFavorite(currentProduct);
                holder.btnFavorite.setImageResource(R.drawable.ic_favorite_border);
            } else {
                presenter.addToFavorite(currentProduct);
                holder.btnFavorite.setImageResource(R.drawable.ic_favorite_filled);
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
