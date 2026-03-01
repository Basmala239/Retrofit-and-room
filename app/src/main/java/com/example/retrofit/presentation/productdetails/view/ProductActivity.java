package com.example.retrofit.presentation.productdetails.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.retrofit.R;

public class ProductActivity extends AppCompatActivity {

    de.hdodenhof.circleimageview.CircleImageView imgViewURL;
    TextView tvTitle, tvPrice, tvBrand, tvDescription;
    RatingBar ratingBar;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product);

        initView();
        displayProduct();

        btnBack.setOnClickListener(v -> finish());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initView() {
        tvTitle = findViewById(R.id.tv_title);
        tvPrice = findViewById(R.id.tv_price);
        tvBrand = findViewById(R.id.tv_brand);
        tvDescription = findViewById(R.id.tv_description);
        imgViewURL = findViewById(R.id.img_url);
        ratingBar = findViewById(R.id.rating_bar);
        btnBack = findViewById(R.id.btn_back);
    }

    @SuppressLint("SetTextI18n")
    private void displayProduct() {
        Intent intent = getIntent();
        if (intent != null) {
            tvTitle.setText(intent.getStringExtra("title"));
            tvBrand.setText(intent.getStringExtra("brand"));
            tvDescription.setText(intent.getStringExtra("description"));
            tvPrice.setText("$" + intent.getDoubleExtra("price", 0.0));
            ratingBar.setRating((float) intent.getDoubleExtra("rating", 0.0));

            String imageUrl = intent.getStringExtra("imageUrl");

            Glide.with(this)
                    .load(imageUrl)
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .error(android.R.drawable.stat_notify_error)
                    .into(imgViewURL);
        }
    }
}