package com.nguyenhoa.weatherforcast.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.weatherforcast.R;
import com.nguyenhoa.weatherforcast.common.Common;
import com.nguyenhoa.weatherforcast.model.HourWeatherForecastResult;
import com.nguyenhoa.weatherforcast.model.Hourly;
import com.squareup.picasso.Picasso;

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.DayViewHolder> {
    private Context context;
    private HourWeatherForecastResult hourWeatherForecastResult;

    public HourAdapter(Context context, HourWeatherForecastResult hourWeatherForecastResult) {
        this.context = context;
        this.hourWeatherForecastResult = hourWeatherForecastResult;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(
                R.layout.item_hour, parent, false);
        return new DayViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
//        if(position > 10) return;
        Hourly x = hourWeatherForecastResult.getHourly().get(position);
        if(x == null)   return;

        //Load icon
        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                .append(x.getWeather().get(0).getIcon())
                .append(".png").toString()).into(holder.img);
        if(position == 0)
            holder.tv1.setText("Now");
        else
            holder.tv1.setText(new StringBuilder(Common.converUnixToHour(x.getDt())));
        String s = String.valueOf(x.getTemp());
        holder.tv2.setText(new StringBuilder(s.substring(0, s.length()-2)).append("°"));
//        holder.img.setImageResource(x.getImg());
//        holder.tv1.setText(x.getHour());
//        holder.tv2.setText(x.getTemp()+"°");
    }

    @Override
    public int getItemCount() {
        if(hourWeatherForecastResult != null)
            return 10;
        return 0;
    }

//    public void add(WeatherOfDay x){
//        list.add(x);
//        notifyDataSetChanged();
//    }

    public class DayViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1, tv2;
        private ImageView img;

        public DayViewHolder(View view) {
            super(view);
            tv1 = view.findViewById(R.id.txt_hour);
            tv2 = view.findViewById(R.id.txt_temperature);
            img = view.findViewById(R.id.img);
        }
    }
}
