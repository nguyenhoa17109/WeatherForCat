package com.nguyenhoa.weatherforcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.nguyenhoa.weatherforcast.adpter.ViewPagerAdapter;
import com.nguyenhoa.weatherforcast.fragment_shopping.FragmentFoodStore;
import com.nguyenhoa.weatherforcast.fragment_shopping.FragmentPetStore;

public class ShoppingActivity extends AppCompatActivity {
    private TextView tvHeart;
    private TabLayout tab;
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SharedPreferences preferences;
    public static final String MyPREFERENCES = "SETTING";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        init();
    }

    private void init() {
        tvHeart = findViewById(R.id.tv_heart);
        tab = findViewById(R.id.tab);
        pager = findViewById(R.id.viewPagerTab);
        tab.setupWithViewPager(pager);

        tab.setSelectedTabIndicatorColor(Color.parseColor("#EE4A567B"));
        tab.setSelectedTabIndicator(R.drawable.bg_shopping_tab);

        preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String like = preferences.getString("like", "500");
        tvHeart.setText(like);
        setupViewPager(pager, like);
    }

    private void setupViewPager(ViewPager pager, String s) {
        int heart = Integer.parseInt(s);
        adapter = new ViewPagerAdapter(getSupportFragmentManager()
                , ViewPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new FragmentPetStore(heart), "Pet Store");
        adapter.addFragment(new FragmentFoodStore(heart), "Food Store");
        pager.setAdapter(adapter);
    }
}