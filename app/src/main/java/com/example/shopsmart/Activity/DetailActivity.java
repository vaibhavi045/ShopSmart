package com.example.shopsmart.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopsmart.Adapter.SizeAdapter;
import com.example.shopsmart.Adapter.SliderAdapter;
import com.example.shopsmart.Domain.ItemsDomain;
import com.example.shopsmart.Domain.SliderItems;
import com.example.shopsmart.Fragment.DescriptionFragment;
import com.example.shopsmart.Fragment.ReviewFragment;
import com.example.shopsmart.Fragment.SoldFragment;
import com.example.shopsmart.Helper.ManagmentCart;
import com.example.shopsmart.databinding.ActivityDetailBinding;
import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends BaseActivity {
    ActivityDetailBinding binding;
    private ItemsDomain object;
    private int numberOrder=1;
    private ManagmentCart managmentCart;
    private Handler slidehandle = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(this);
        getBundles();
        initBanners();
        initSize();
        setupViewPager();

    }

    private void initSize() {
        ArrayList<String> list = new ArrayList<>();
        list.add("S");
        list.add("M");
        list.add("L");
        list.add("XL");
        list.add("XXL");

        binding.recyclerSize.setAdapter(new SizeAdapter(list));
        binding.recyclerSize.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void initBanners() {
        ArrayList<SliderItems> sliderItems= new ArrayList<>();
        for (int i = 0; i < object.getPicUrl().size(); i++) {
            sliderItems.add(new SliderItems(object.getPicUrl().get(i)));
        }
        binding.viewpageSlider.setAdapter(new SliderAdapter(sliderItems, binding.viewpageSlider));
        binding.viewpageSlider.setClipToPadding(false);
        binding.viewpageSlider.setClipChildren(false);
        binding.viewpageSlider.setOffscreenPageLimit(3);
        binding.viewpageSlider.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
    }

    private void getBundles() {
        object = (ItemsDomain) getIntent().getSerializableExtra("object");
        binding.titleTxt.setText(object.getTitle());
        binding.priceTxt.setText("Rs."+object.getPrice());
        binding.ratingBar.setRating(object.getRating());
        binding.ratingTxt.setText(object.getRating()+"Rating");

        binding.addTocartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberinCart(numberOrder);
                managmentCart.insertItem(object);
            }
        });
        binding.backBtn.setOnClickListener(v -> finish());
    }

    private void wishList() {
        object = (ItemsDomain) getIntent().getSerializableExtra("object");
        binding.titleTxt.setText(object.getTitle());
        binding.priceTxt.setText("Rs."+object.getPrice());
        binding.ratingBar.setRating(object.getRating());
        binding.ratingTxt.setText(object.getRating()+"Rating");

        binding.wishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managmentCart.insertItem(object);
            }
        });
        binding.backBtn.setOnClickListener(v -> finish());
    }
    private void setupViewPager(){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        DescriptionFragment tab1=new DescriptionFragment();
        ReviewFragment tab2=new ReviewFragment();
        SoldFragment tab3 = new SoldFragment();

        Bundle bundle1= new Bundle();
        Bundle bundle2= new Bundle();
        Bundle bundle3= new Bundle();

        bundle1.putString("description", object.getDescription());

        tab1.setArguments(bundle1);
        tab2.setArguments(bundle2);
        tab3.setArguments(bundle3);

        adapter.addFrag(tab1, "Description");
        adapter.addFrag(tab2, "Reviews");
        adapter.addFrag(tab3, "Sold");

        binding.viewpager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewpager);

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        private void addFrag(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position){
            return mFragmentTitleList.get(position);
        }
    }
}