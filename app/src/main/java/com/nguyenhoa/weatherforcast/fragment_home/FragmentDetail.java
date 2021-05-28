package com.nguyenhoa.weatherforcast.fragment_home;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.nguyenhoa.weatherforcast.R;
import com.nguyenhoa.weatherforcast.common.Common;
import com.nguyenhoa.weatherforcast.adpter.HourAdapter;
import com.nguyenhoa.weatherforcast.model.Alert;
import com.nguyenhoa.weatherforcast.model.HourWeatherForecastResult;
import com.nguyenhoa.weatherforcast.model.Hourly;
import com.nguyenhoa.weatherforcast.model.Notification;
import com.nguyenhoa.weatherforcast.model.WeatherResult;
import com.nguyenhoa.weatherforcast.retrofit.IOpenWeatherMap;
import com.nguyenhoa.weatherforcast.retrofit.RetrofitClient;
import com.nguyenhoa.weatherforcast.sqlite.SQLiteNotification;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDetail extends Fragment implements DayDetailAdapter.ItemClickListener{
    private View v;
    private TextView txt_date_time, txt_pressure, txt_temperature, txt_wind
            , txt_humidity, txt_feel_likes;
    private ImageView img_weather;
    private RecyclerView revHour, revDay;
//    private HourAdapter adapter;
//    private DayDetailAdapter adapter2;
    private CombinedChart chart;

    LinearLayout weather_panel;
    ProgressBar loading;
    CompositeDisposable compositeDisposable;
    IOpenWeatherMap mService;

    static FragmentDetail instance;

    public static FragmentDetail getInstance(){
        if(instance==null){
            instance = new FragmentDetail();
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

    public FragmentDetail() {
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
     * @return A new instance of fragment FragmentDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDetail newInstance(String param1, String param2) {
        FragmentDetail fragment = new FragmentDetail();
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
        v = inflater.inflate(R.layout.fragment_detail, container, false);
        init(v);
//        Intent intent = new Intent(FragmentHome.this, FragmentNotification.class);

        getWeatherInformation();
        getInformation();
        getInformation1();
        return v;
    }

    private void init(View v) {
        txt_date_time = v.findViewById(R.id.txt_date_time);
        txt_temperature = v.findViewById(R.id.txt_temperature);
        img_weather = v.findViewById(R.id.img_weather);
        txt_humidity = v.findViewById(R.id.txt_humidity);
        txt_pressure = v.findViewById(R.id.txt_pressure);
        txt_wind = v.findViewById(R.id.txt_wind);
        txt_feel_likes = v.findViewById(R.id.txt_feels_like);

        revHour = v.findViewById(R.id.reView);
        revDay = v.findViewById(R.id.reView2);
        chart = v.findViewById(R.id.chart);

        weather_panel = v.findViewById(R.id.weather_panel);
        loading = v.findViewById(R.id.loading);

        revHour.setHasFixedSize(true);
        revHour.setLayoutManager(new LinearLayoutManager(v.getContext()
            , LinearLayoutManager.HORIZONTAL, false));

        revDay.setHasFixedSize(true);
        revDay.setLayoutManager(new LinearLayoutManager(v.getContext()
                , LinearLayoutManager.HORIZONTAL, false));
    }


    private void setDataChart(List data1, List data2, List data3, List<String> xLabel){
        chart.getDescription().setEnabled(false);
        chart.setBackgroundColor(Color.WHITE);
        chart.setDrawGridBackground(true);
        chart.setDrawBarShadow(false);
        chart.setHighlightFullBarEnabled(false);
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Toast.makeText(v.getContext(), "Value: "
                        + e.getY()
                        + ", index: "
                        + h.getX()
                        + ", DataSet index: "
                        + h.getDataSetIndex(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f);
        rightAxis.setMinWidth(10f);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setMinWidth(10f);

//        final List<String> xLabel = new ArrayList<>();
//        xLabel.add("Day 1");
//        xLabel.add("Day 2");
//        xLabel.add("Day 3");

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(10f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xLabel.get((int) value % xLabel.size());
            }
        });

//        Arrays a = null;
        CombinedData data = new CombinedData();
        LineData lineDatas = new LineData();
        lineDatas.addDataSet((ILineDataSet) dataChart(data1, "#1F78B4", "Temperature"));
        lineDatas.addDataSet((ILineDataSet) dataChart(data2, "#B2DF8A", "Dew point"));
        lineDatas.addDataSet((ILineDataSet) dataChart(data3, "#A6CEE3", "Humidity"));
//        lineDatas.addDataSet(dataChart(a, "#1F78B4", "Temperature"));
        data.setData(lineDatas);

        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        chart.setData(data);
        chart.invalidate();
    }

    private static DataSet dataChart(List<Integer> list, String color, String name) {
        ArrayList<Entry> entries = new ArrayList<Entry>();
        LineData d = new LineData();
        int x[] = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            x[i] = list.get(i);
        }
//        x[0]=0;
        for (int index = 0; index < 5; index++) {
            entries.add(new Entry(index, x[index]));
        }
        LineDataSet set = new LineDataSet(entries, name);
        set.setColor(Color.parseColor(color));
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.parseColor(color));
        set.setCircleRadius(2f);
        set.setFillColor(Color.parseColor(color));
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.parseColor(color));
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return set;
    }

    @Override
    public void onItemClick(View view, int position) {
//        v.findViewById(R.id.)
//        view.setBackgroundColor();
//        view.
        view.setBackgroundResource(R.drawable.tabbar);
        TextView tv = view.findViewById(R.id.txt_day);
        tv.setTextColor(Color.WHITE);
//        view.refreshDrawableState();
    }
    private void getWeatherInformation() {
        compositeDisposable.add(mService.getWeatherByLatLng(
                String.valueOf(Common.curren_location.getLatitude()),
                String.valueOf(Common.curren_location.getLongitude()),
                Common.APP_ID,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherResult>() {
                    @Override
                    public void accept(WeatherResult weatherResult) throws Exception {
//                        SQLiteWeather sqLiteWeather = new SQLiteWeather(getContext());
//                        sqLiteWeather.addWeather(weatherResult);
                        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                                .append(weatherResult.getWeather().get(0).getIcon())
                                .append(".png").toString()).into(img_weather);

                        txt_temperature.setText(new StringBuilder(
                                String.valueOf(weatherResult.getMain().getTemp())).append("°C").toString());
                        txt_date_time.setText(Common.converUnixToDate(weatherResult.getDt()));
                        txt_pressure.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getPressure())).append(" mb").toString());
                        txt_humidity.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getHumidity())).append(" %").toString());
                        txt_feel_likes.setText(new StringBuilder(String.valueOf(Math.round(weatherResult.getMain().getFeels_like()))).append("°C").toString());
                        txt_wind.setText(new StringBuilder(String.valueOf(weatherResult.getWind().getSpeed())).append("km/h").toString());
//                        txt_sunrise.setText(Common.converUnixToHour(weatherResult.getSys().getSunrise()));
//                        txt_sunset.setText(Common.converUnixToHour(weatherResult.getSys().getSunset()));

                        //Display panel
                        weather_panel.setVisibility(View.VISIBLE);
                        loading.setVisibility(View.GONE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Error", ""+throwable.getMessage());
//                        Toast.makeText(getActivity(), ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    private void getInformation() {
        compositeDisposable.add(mService.getWeatherByHour(
                String.valueOf(Common.curren_location.getLatitude()),
                String.valueOf(Common.curren_location.getLongitude()),
                "daily",
                Common.APP_ID,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HourWeatherForecastResult>() {
                    @Override
                    public void accept(HourWeatherForecastResult hourWeatherForecast) throws Exception {
                        SQLiteNotification sqLiteNotification = new SQLiteNotification(v.getContext());
//                        List<Notification> list = sqLiteNotification.getAll();
                        if(hourWeatherForecast.getAlerts() == null){
                            Notification notification = new Notification(
                                    Common.converUnixToDate1(hourWeatherForecast.getCurrent().getDt()),
                                    hourWeatherForecast.getTimezone(),
                                    hourWeatherForecast.getCurrent().toString(),
                                    hourWeatherForecast.getCurrent().toString(),
                                    hourWeatherForecast.getCurrent().getWin(),
                                    hourWeatherForecast.getCurrent().getMain(),
                                    String.valueOf(hourWeatherForecast.getCurrent().clouds));
                            sqLiteNotification.addNotification(notification);
                        }else{
                            Alert alert = hourWeatherForecast.getAlerts().get(0);
                            Notification notification = new Notification(
                                    Common.converUnixToDate1(Integer.parseInt(String.valueOf(alert.getStart()))),
                                    alert.getSender_name()+" - "+alert.getEvent(),
                                    alert.getDescription(),
                                    hourWeatherForecast.getCurrent().toString(),
                                    hourWeatherForecast.getCurrent().getWin(),
                                    hourWeatherForecast.getCurrent().getMain(),
                                    String.valueOf(hourWeatherForecast.getCurrent().clouds));
                            sqLiteNotification.addNotification(notification);
                        }

                        display(hourWeatherForecast);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("ERROR", ""+throwable.getMessage());
                    }
                })
        );
    }

    private void display(HourWeatherForecastResult hourWeatherForecast) {
        HourAdapter adapter = new HourAdapter(getContext(), hourWeatherForecast);
        revHour.setAdapter(adapter);
        List data1 = new ArrayList<Integer>();
        List data2 = new ArrayList<Integer>();
        List data3 = new ArrayList<Integer>();
        List<String> label = new ArrayList<>();
        for(int i=0; i<5; i++){
            Hourly x = hourWeatherForecast.getHourly().get(i);
            data1.add((int)x.getTemp());
//                            data2.add(x.getVisibility());
            data3.add(x.getHumidity());
            data2.add((int)x.getDew_point());
            label.add(Common.converUnixToHour(x.getDt()));
        }
        label.set(0, "Now");
        Log.d("LM", data1.toString());

        setDataChart(data1, data2, data3, label);
    }

    private void getInformation1() {
        compositeDisposable.add(mService.getWeatherByHour(
//                at=33.44&lon=-94.04
                String.valueOf(Common.curren_location.getLatitude()),
                String.valueOf(Common.curren_location.getLongitude()),
                "hourly",
                Common.APP_ID,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HourWeatherForecastResult>() {
                    @Override
                    public void accept(HourWeatherForecastResult hourWeatherForecast) throws Exception {
//                        Log.d("ALO", hourWeatherForecast.getAlerts().toString());
                        display1(hourWeatherForecast);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("ERROR", ""+throwable.getMessage());
                    }
                })
        );
    }

    private void display1(HourWeatherForecastResult hourWeatherForecast) {
        DayDetailAdapter adapter = new DayDetailAdapter(getContext(), hourWeatherForecast);
        adapter.setListener(this::onItemClick);
        revDay.setAdapter(adapter);
        revDay.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
                holder.itemView.setBackgroundColor(Color.parseColor("#E36E79"));
            }
        });
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