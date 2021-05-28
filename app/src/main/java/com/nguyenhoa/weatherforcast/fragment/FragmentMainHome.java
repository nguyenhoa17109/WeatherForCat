package com.nguyenhoa.weatherforcast.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.nguyenhoa.weatherforcast.R;
import com.nguyenhoa.weatherforcast.adpter.ViewPagerAdapter;
import com.nguyenhoa.weatherforcast.common.Common;
import com.nguyenhoa.weatherforcast.fragment_home.FragmentDetail;
import com.nguyenhoa.weatherforcast.fragment_home.FragmentHome;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMainHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMainHome extends Fragment {
    private Toolbar toolbar;
    private TabLayout tab;
    private View v;
    private ViewPager pager;
    private ViewPagerAdapter adapter;

    private CoordinatorLayout coordinatorLayout;
//    private CoordinatorLayout linearLayout;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

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
        coordinatorLayout = v.findViewById(R.id.root_view);
//        toolbar = v.findViewById(R.id.toolbar);
//        getActivity().setSupportActionBar(toolbar);
//
//        getActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        tab = v.findViewById(R.id.tab);
//        getS
//        pager = v.findViewById(R.id.viewPagerTab);
//
//        adapter = new ViewPagerAdapter(getChildFragmentManager(), ViewPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        adapter.addFragment(FragmentHome.getInstance(), "Home");
//        adapter.addFragment(FragmentDetail.getInstance(), "Detail");;

//        pager.setAdapter(adapter);
////        pager.setPageTransformer(true, new Horizon());


//        Request permission
        Dexter.withActivity(getActivity())
                .withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            buildLocationRequest();
                            buildLocationCallBack();

                            if (ActivityCompat.checkSelfPermission(getContext(),
                                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                                    && ActivityCompat.checkSelfPermission(getContext(),
                                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                                return;
                            }
                            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
                            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        Snackbar.make(coordinatorLayout, "Pessmision Denied", Snackbar.LENGTH_LONG).show();
                    }
                }).check();
//        pager.setAdapter(adapter);
//        tab.setupWithViewPager(pager);
//        tab.setSelectedTabIndicatorColor(Color.parseColor("#E36E79"));
        return v;
    }

    private void buildLocationCallBack() {
        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                Common.curren_location = locationResult.getLastLocation();
                pager = v.findViewById(R.id.viewPagerTab);
                setupViewPager (pager);
                tab = v.findViewById(R.id.tab);
                tab.setupWithViewPager(pager);
                tab.setSelectedTabIndicatorColor(Color.parseColor("#E36E79"));

                //log
                Log.d("Location", locationResult.getLastLocation().getLatitude()
                        + "/"+locationResult.getLastLocation().getLongitude());

            }

            private void setupViewPager(ViewPager viewPager) {
                adapter = new ViewPagerAdapter(getChildFragmentManager()
                        , ViewPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
                adapter.addFragment(FragmentHome.getInstance(), "Home");
                adapter.addFragment(FragmentDetail.getInstance(), "Detail");
//                adapter.addFragment(CityFragment.getInstance(), "Cities");
                viewPager.setAdapter(adapter);
            }
        };
    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10.0f);
    }

}