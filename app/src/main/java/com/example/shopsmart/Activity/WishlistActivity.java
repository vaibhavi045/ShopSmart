package com.example.shopsmart.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.shopsmart.Adapter.CartAdapter;
import com.example.shopsmart.Helper.ChangeNumberItemsListener;
import com.example.shopsmart.Helper.ManagmentCart;
import com.example.shopsmart.R;
import com.example.shopsmart.databinding.ActivityWishlistBinding;
import com.example.shopsmart.databinding.ActivityWishlistBinding;

public class WishlistActivity extends BaseActivity {
    ActivityWishlistBinding binding;

    private ManagmentCart managmentCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityWishlistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart= new ManagmentCart(this);


        setVariable();
        initCartList();
        explorer();

    }

    private void initCartList() {
        if (managmentCart.getListCart().isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollViewWish.setVisibility(View.GONE);

        }else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollViewWish.setVisibility(View.VISIBLE);
        }
        binding.wishView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    private void explorer() {
        binding.explorerBtn.setOnClickListener(v -> {
            startActivity(new Intent(WishlistActivity.this, MainActivity.class));
        });
    }


    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());
    }


}
