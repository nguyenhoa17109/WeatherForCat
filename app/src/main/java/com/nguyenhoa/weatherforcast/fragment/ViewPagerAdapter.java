package com.nguyenhoa.weatherforcast.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nguyenhoa.weatherforcast.fragment_home.FragmentDetail;
import com.nguyenhoa.weatherforcast.fragment_home.FragmentHome;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int numPager = 2;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentHome();
            case 1: return new FragmentDetail();
            default: return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return numPager;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Home";
            case 1: return "Detail";
            default: return "Home";
        }
    }
}
