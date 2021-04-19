package com.nguyenhoa.weatherforcast.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nguyenhoa.weatherforcast.fragment_home.FragmentHome;

public class FragmentBottomAdapter extends FragmentStatePagerAdapter {
    private int numPage = 4;
    public FragmentBottomAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentMainHome();
            case 1: return new FragmentCare();
            case 2: return new FragmentNotification();
            case 3: return new FragmentUser();
            default: return new FragmentMainHome();
        }
    }

    @Override
    public int getCount() {
        return numPage;
    }


}
