package com.nguyenhoa.weatherforcast.fragment_home;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.nguyenhoa.weatherforcast.fragment.HourAdapter;
import com.nguyenhoa.weatherforcast.model.WeatherOfDay;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDetail extends Fragment
implements DayDetailAdapter.ItemClickListener{
    private View v;
    private TextView tvDate, tvTemp;
    private ImageView iv;
    private RecyclerView revHour, revDay;
    private HourAdapter adapter;
    private DayDetailAdapter adapter2;
    private CombinedChart chart;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentDetail() {
        // Required empty public constructor
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
        tvDate = v.findViewById(R.id.tvDate);
        tvTemp = v.findViewById(R.id.tvTemp);
        iv = v.findViewById(R.id.img);
        revHour = v.findViewById(R.id.reView);
        revDay = v.findViewById(R.id.reView2);
        chart = v.findViewById(R.id.chart);

        adapter = new HourAdapter(v.getContext());
        adapter2 = new DayDetailAdapter(v.getContext());
        LinearLayoutManager manager = new LinearLayoutManager(v.getContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        LinearLayoutManager manager2 = new LinearLayoutManager(v.getContext());
        manager2.setOrientation(RecyclerView.HORIZONTAL);

        revHour.setLayoutManager(manager);
        revDay.setLayoutManager(manager2);
        adapter.setData(creatData());
        adapter2.setData(creatData2());
        adapter2.setListener(this::onItemClick);

        revHour.setAdapter(adapter);
        revDay.setAdapter(adapter2);
        revDay.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
                holder.itemView.setBackgroundColor(Color.parseColor("#E36E79"));
            }
        });
        setDataChart();

        return v;
    }

    private List<WeatherOfDay> creatData2() {
        List<WeatherOfDay> list = new ArrayList<>();
        list.add(new WeatherOfDay("Sun", 30, R.drawable.ic_filled_rain));
        list.add(new WeatherOfDay("Mon", 27, R.drawable.ic_filled_rain));
        list.add(new WeatherOfDay("Tue", 25, R.drawable.ic_filled_rain));
        list.add(new WeatherOfDay("Wed", 30, R.drawable.ic_filled_rain));
        list.add(new WeatherOfDay("Thur", 27, R.drawable.ic_filled_rain));
//        list.add(new WeatherOfDay("Wed", 25, R.drawable.ic_sun40));
        return list;
    }

    private List<WeatherOfDay> creatData() {
        List<WeatherOfDay> list = new ArrayList<>();
        list.add(new WeatherOfDay("Mon","Now", 30, R.drawable.ic_cloud));
        list.add(new WeatherOfDay("Mon","14:00", 27, R.drawable.ic_cloud));
        list.add(new WeatherOfDay("Mon","15:00", 25, R.drawable.ic_cloud));
        list.add(new WeatherOfDay("Mon","16:00", 30, R.drawable.ic_cloud));
        list.add(new WeatherOfDay("Mon","17:00", 27, R.drawable.ic_cloud));
//        list.add(new WeatherOfDay("Wed", 25, R.drawable.ic_sun40));
        return list;
    }

    private void setDataChart(){
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

        final List<String> xLabel = new ArrayList<>();
        xLabel.add("Day 1");
        xLabel.add("Day 2");
        xLabel.add("Day 3");

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

        int[] data1 = new int[] { 20, 25, 20};
        int[] data2 = new int[] { 0, 15, 10};
        int[] data3 = new int[] { 10, 45, 20};
        CombinedData data = new CombinedData();
        LineData lineDatas = new LineData();
        lineDatas.addDataSet((ILineDataSet) dataChart(data1, "#1F78B4", "Temperature"));
        lineDatas.addDataSet((ILineDataSet) dataChart(data2, "#B2DF8A", "Visibility"));
        lineDatas.addDataSet((ILineDataSet) dataChart(data3, "#A6CEE3", "Humidity"));
        data.setData(lineDatas);

        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        chart.setData(data);
        chart.invalidate();
    }
    private static DataSet dataChart(int[] data, String color, String name) {
        ArrayList<Entry> entries = new ArrayList<Entry>();
        LineData d = new LineData();
        for (int index = 0; index < 3; index++) {
            entries.add(new Entry(index, data[index]));
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
        TextView tv = view.findViewById(R.id.tvDay);
        tv.setTextColor(Color.WHITE);
//        view.refreshDrawableState();
    }
}