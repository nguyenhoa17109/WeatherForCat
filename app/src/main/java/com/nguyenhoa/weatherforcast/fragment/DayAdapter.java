package com.nguyenhoa.weatherforcast.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.weatherforcast.R;
import com.nguyenhoa.weatherforcast.model.WeatherOfDay;

import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder> {
    private Context context;
    private List<WeatherOfDay> list;

    public DayAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<WeatherOfDay> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_day, parent, false);
        return new DayViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        WeatherOfDay x = list.get(position);
        if(x == null)   return;
        holder.img.setImageResource(x.getImg());
        holder.tv1.setText(x.getDay());
        holder.tv2.setText(x.getTemp()+"°C");
    }

    @Override
    public int getItemCount() {
        if(list != null)    return list.size();
        return 0;
    }

    public void add(WeatherOfDay x){
        list.add(x);
        notifyDataSetChanged();
    }

    public class DayViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1, tv2;
        private ImageView img;

        public DayViewHolder(@NonNull View view) {
            super(view);
            tv1 = view.findViewById(R.id.tvDay);
            tv2 = view.findViewById(R.id.tvWeather);
            img = view.findViewById(R.id.img);
        }
    }
}
