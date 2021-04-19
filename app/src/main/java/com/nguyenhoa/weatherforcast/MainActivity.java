package com.nguyenhoa.weatherforcast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.nguyenhoa.weatherforcast.fragment.FragmentBottomAdapter;
import com.nguyenhoa.weatherforcast.fragment.Horizon;
import com.nguyenhoa.weatherforcast.fragment.ViewPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigation;
    private ViewPager viewPager;
    private FragmentBottomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        navigation = findViewById(R.id.navigation);
        viewPager = findViewById(R.id.viewPager);
        adapter = new FragmentBottomAdapter(getSupportFragmentManager(),
                FragmentBottomAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mHome: viewPager.setCurrentItem(0);
                        break;
                    case R.id.mCare: viewPager.setCurrentItem(1);
                        break;
                    case R.id.mNoti: viewPager.setCurrentItem(2);
                        break;
                    case R.id.mUser: viewPager.setCurrentItem(3);
                }
                return true;
            }
        });
    }
}