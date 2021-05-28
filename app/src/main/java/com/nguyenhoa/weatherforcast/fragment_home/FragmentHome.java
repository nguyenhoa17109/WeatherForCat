package com.nguyenhoa.weatherforcast.fragment_home;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.label305.asynctask.SimpleAsyncTask;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.nguyenhoa.weatherforcast.R;
import com.nguyenhoa.weatherforcast.common.Common;
import com.nguyenhoa.weatherforcast.model.WeatherForecastResult;
import com.nguyenhoa.weatherforcast.model.WeatherResult;
import com.nguyenhoa.weatherforcast.retrofit.IOpenWeatherMap;
import com.nguyenhoa.weatherforcast.retrofit.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment {
    private View v;
    private Button btToday, btTomorrow, btAfter;
    private TextView txt_city, txt_temperature, txt_notification;
    private ImageView img_weather, ivSang, ivChieu, ivToi;
    private TextView tvSang, tvChieu, tvToi;

    private List<String> lstCities;
    private MaterialSearchBar searchBar;
    private WeatherForecastResult result;

    LinearLayout weather_panel;
    ProgressBar loading;

    CompositeDisposable compositeDisposable;
    IOpenWeatherMap mService;

    static FragmentHome instance;

    public static FragmentHome getInstance(){
        if(instance==null){
            instance = new FragmentHome();
        }
        return instance;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHome() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance();
        mService = retrofit.create(IOpenWeatherMap.class);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
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
        v = inflater.inflate(R.layout.fragment_home, container, false);
        init(v);
        btToday.setBackgroundColor(Color.parseColor("#E36E79"));
        btAfter.setBackgroundColor(Color.WHITE);
        btTomorrow.setBackgroundColor(Color.WHITE);
        getInformation();
        setButton();

        return v;
    }

    private void init(View v){
        txt_city = v.findViewById(R.id.txt_city);
        txt_temperature = v.findViewById(R.id.txt_temperature);
        txt_notification = v.findViewById(R.id.tvNoti);
        img_weather = v.findViewById(R.id.img_weather);

        weather_panel = v.findViewById(R.id.weather_panel);
        loading = v.findViewById(R.id.loading);

        btToday = v.findViewById(R.id.bt1);
        btTomorrow = v.findViewById(R.id.bt2);
        btAfter = v.findViewById(R.id.bt3);
        tvSang = v.findViewById(R.id.tvTempSang);
        tvChieu = v.findViewById(R.id.tvTempChieu);
        tvToi = v.findViewById(R.id.tvTempToi);

        ivSang = v.findViewById(R.id.imgSang);
        ivChieu = v.findViewById(R.id.imgChieu);
        ivToi = v.findViewById(R.id.imgToi);

        searchBar = v.findViewById(R.id.searchBar);
        searchBar.setEnabled(false);
        new LoadCites().execute();
    }

    private void setButton(){
        btToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btToday.setBackgroundColor(Color.parseColor("#E36E79"));
                btAfter.setBackgroundColor(Color.WHITE);
                btTomorrow.setBackgroundColor(Color.WHITE);
                display(result, 0, tvSang, ivSang);
                display(result, 1, tvChieu, ivChieu);
                display(result, 2, tvToi, ivToi);
//                Toast.makeText(this, result.list.get(0).)
            }
        });
        btTomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btTomorrow.setBackgroundColor(Color.parseColor("#E36E79"));
                btToday.setBackgroundColor(Color.WHITE);
                btAfter.setBackgroundColor(Color.WHITE);
                display(result, 3, tvSang, ivSang);
                display(result, 4, tvChieu, ivChieu);
                display(result, 5, tvToi, ivToi);
            }
        });
        btAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btAfter.setBackgroundColor(Color.parseColor("#E36E79"));
                btToday.setBackgroundColor(Color.WHITE);
                btTomorrow.setBackgroundColor(Color.WHITE);
                display(result, 6, tvSang, ivSang);
                display(result, 7, tvChieu, ivChieu);
                display(result, 8, tvToi, ivToi);
            }

        });
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.bt1:{
//                btToday.setBackgroundColor(Color.parseColor("#E36E79"));
//                btAfter.setBackgroundColor(Color.WHITE);
//                btTomorrow.setBackgroundColor(Color.WHITE);
//                display(result, 0, tvSang, ivSang);
//                display(result, 1, tvChieu, ivChieu);
//                display(result, 2, tvToi, ivToi);
//            }
//            case R.id.bt2:{
//                btTomorrow.setBackgroundColor(Color.parseColor("#E36E79"));
//                btToday.setBackgroundColor(Color.WHITE);
//                btAfter.setBackgroundColor(Color.WHITE);
//                display(result, 3, tvSang, ivSang);
//                display(result, 4, tvChieu, ivChieu);
//                display(result, 5, tvToi, ivToi);
//            }
//            case R.id.bt3:{
//                btAfter.setBackgroundColor(Color.parseColor("#E36E79"));
//                btToday.setBackgroundColor(Color.WHITE);
//                btTomorrow.setBackgroundColor(Color.WHITE);
//                display(result, 6, tvSang, ivSang);
//                display(result, 7, tvChieu, ivChieu);
//                display(result, 8, tvToi, ivToi);
//            }
//        }
//
//    }

    private class LoadCites extends SimpleAsyncTask<List<String>> {
        @Override
        protected List<String> doInBackgroundSimple() {
            lstCities = new ArrayList<>();
            try {
                StringBuilder builder = new StringBuilder();
                InputStream is = getResources().openRawResource(R.raw.city_list);
                GZIPInputStream gzipInputStream = new GZIPInputStream(is);

                InputStreamReader reader = new InputStreamReader(gzipInputStream);
                BufferedReader in = new BufferedReader(reader);

                String readed;
                while ((readed = in.readLine()) != null){
                    builder.append(readed);
                    lstCities = new Gson().fromJson(builder.toString(), new TypeToken<List<String>>(){}.getType());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return lstCities;
        }

        @Override
        protected void onSuccess(List<String> listCity) {
            super.onSuccess(listCity);

            searchBar.setEnabled(true);
            searchBar.addTextChangeListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    List<String> suggest = new ArrayList<>();
                    for(String search:listCity)
                        if(search.toLowerCase().contains(searchBar.getText().toLowerCase()))
                            suggest.add(search);
                    searchBar.setLastSuggestions(suggest);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
                @Override
                public void onSearchStateChanged(boolean enabled) {

                }

                @Override
                public void onSearchConfirmed(CharSequence text) {
                    getWeatherInformation(text.toString());
                    searchBar.setLastSuggestions(listCity);
                }

                @Override
                public void onButtonClicked(int buttonCode) {

                }
            });

            searchBar.setLastSuggestions(listCity);

            loading.setVisibility(View.GONE);
            weather_panel.setVisibility(View.VISIBLE);
        }
    }

    private void getWeatherInformation(String cityName) {
        compositeDisposable.add(mService.getWeatherByCityName(cityName,
                Common.APP_ID,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherResult>() {
                    @Override
                    public void accept(WeatherResult weatherResult) throws Exception {
                        double lon = weatherResult.getCoord().getLon();
                        double lat = weatherResult.getCoord().getLat();
                        Log.d("ALO", weatherResult.toString());
                        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                                .append(weatherResult.getWeather().get(0).getIcon())
                                .append(".png").toString()).into(img_weather);

                        txt_city.setText(weatherResult.getName());
                        String s = String.valueOf(weatherResult.getMain().getTemp());
                        txt_temperature.setText(new StringBuilder(s.substring(0, s.length()-2)).append("°C").toString());
                        String str = "Weather Detail:" +
                                "\nDate: " + Common.converUnixToDate(weatherResult.getDt()) +
                                "\nLocation: '" + weatherResult.getName() + '\''+
                                "\nCoord: " + weatherResult.getCoord() +
                                "\nMain: " + weatherResult.getMain().toString() +
                                "\nWind: " + weatherResult.getWind().toString();
                        txt_notification.setText(str);
                        Common.curren_location.setLatitude(lat);
                        Common.curren_location.setLongitude(lon);
                        getInformation();
                        setButton();

                        //Display panel
                        weather_panel.setVisibility(View.VISIBLE);
                        loading.setVisibility(View.GONE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity(), ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    //get weather by location
    private void getInformation() {
        compositeDisposable.add(mService.getWeatherForecastByLatLng(
                String.valueOf(Common.curren_location.getLatitude()),
                String.valueOf(Common.curren_location.getLongitude()),
                Common.APP_ID,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherForecastResult>() {
                    @Override
                    public void accept(WeatherForecastResult weatherForecastResult) throws Exception {
                        result = weatherForecastResult;
                        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                                .append(weatherForecastResult.list.get(0).weather.get(0).getIcon())
                                .append(".png").toString()).into(img_weather);
                        txt_city.setText(weatherForecastResult.city.toString());
                        String s = String.valueOf(weatherForecastResult.list.get(0).main.getTemp());
                        txt_temperature.setText(new StringBuilder(s.substring(0, s.length()-2)).append("°C"));
                        String str = "Weather Detail:\n" +
                                "Location: "+weatherForecastResult.city.toString()
                                +"\n"+weatherForecastResult.list.get(0).main.toString();
                        txt_notification.setText(str);
                        display(result, 0, tvSang, ivSang);
                        display(result, 1, tvChieu, ivChieu);
                        display(result, 2, tvToi, ivToi);
                        setButton();
//                        Log.d("ALO", weatherForecastResult.toString());
                        Log.d("Locationii", Common.curren_location.getLatitude()+"/"+
                                Common.curren_location.getLongitude());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("ERROR", ""+throwable.getMessage());
                    }
                })
        );
    }

    private void display(WeatherForecastResult weatherForecastResult, int i, TextView tv, ImageView iv) {
        String s = String.valueOf(weatherForecastResult.list.get(i).main.getTemp());
        tv.setText(new StringBuilder(s.substring(0, s.length()-2)).append("°C"));
        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                .append(weatherForecastResult.list.get(i).weather.get(0).getIcon())
                .append(".png").toString()).into(iv);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}