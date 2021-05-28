package com.nguyenhoa.weatherforcast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nguyenhoa.weatherforcast.adpter.FragmentBottomAdapter;

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


//        Bundle bundle = new Bundle();
//        bundle.putString("abc", "xx");
//        FragmentNotification a = new FragmentNotification();
//        a.setArguments(bundle);

        //Request permission
//        Dexter.withActivity(this)
//                .withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.ACCESS_FINE_LOCATION)
//                .withListener(new MultiplePermissionsListener() {
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport report) {
//                        if (report.areAllPermissionsGranted()) {
//                            buildLocationRequest();
//                            buildLocationCallBack();
//
//                            if (ActivityCompat.checkSelfPermission(MainActivity.this,
//                                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                                    && ActivityCompat.checkSelfPermission(MainActivity.this,
//                                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//                                return;
//                            }
//                            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
//                            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
//                        }
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//                        Snackbar.make(coordinatorLayout, "Pessmision Denied", Snackbar.LENGTH_LONG).show();
//                    }
//                }).check();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.clear();
    }

    //    private void buildLocationCallBack() {
//        locationCallback = new LocationCallback(){
//            @Override
//            public void onLocationResult(LocationResult locationResult) {
//                super.onLocationResult(locationResult);
//
//                Common.curren_location = locationResult.getLastLocation();
//                viewPager = findViewById(R.id.viewPager);
//                setupViewPager (viewPager);
//                tabLayout = findViewById(R.id.tabs);
//                tabLayout.setupWithViewPager(viewPager);
//                //log
//                Log.d("Location", locationResult.getLastLocation().getLatitude()
//                        + "/"+locationResult.getLastLocation().getLongitude());
//
//            }
//
//            private void setupViewPager(ViewPager viewPager) {
//                ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//                adapter.add(TodayWeatherFragment.getInstance(), "Today");
//                adapter.add(ForecastFragment.getInstance(), "5 Days");
//                adapter.add(CityFragment.getInstance(), "Cities");
//                viewPager.setAdapter(adapter);
//            }
//        };
//    }
//
//    private void buildLocationRequest() {
//        locationRequest = new LocationRequest();
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setInterval(5000);
//        locationRequest.setSmallestDisplacement(10.0f);
//        locationRequest.setFastestInterval(3000);
//    }
}