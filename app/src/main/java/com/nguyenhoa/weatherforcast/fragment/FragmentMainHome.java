package com.nguyenhoa.weatherforcast.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.nguyenhoa.weatherforcast.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMainHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMainHome extends Fragment {
    private TabLayout tab;
    private View v;
    private ViewPager pager;
    private ViewPagerAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentMainHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMainHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMainHome newInstance(String param1, String param2) {
        FragmentMainHome fragment = new FragmentMainHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_main_home, container, false);
        tab = v.findViewById(R.id.tab);
        pager = v.findViewById(R.id.viewPagerTab);

        adapter = new ViewPagerAdapter(getChildFragmentManager(),
                ViewPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pager.setAdapter(adapter);
//        pager.setPageTransformer(true, new Horizon());

        tab.setupWithViewPager(pager);
        tab.setSelectedTabIndicatorColor(Color.parseColor("#E36E79"));
        return v;
    }
}