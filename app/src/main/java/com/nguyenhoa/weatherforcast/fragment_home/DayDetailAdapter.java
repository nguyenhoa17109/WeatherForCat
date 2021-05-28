package com.nguyenhoa.weatherforcast.fragment_home;

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
import com.nguyenhoa.weatherforcast.model.Daily;
import com.nguyenhoa.weatherforcast.model.HourWeatherForecastResult;
import com.squareup.picasso.Picasso;

public class DayDetailAdapter extends
        RecyclerView.Adapter<DayDetailAdapter.DayViewHolder> {
    private Context context;
    private HourWeatherForecastResult hourWeatherForecastResult;
    private ItemClickListener listener;



    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public DayDetailAdapter(Context context, HourWeatherForecastResult hourWeatherForecastResult) {
        this.context = context;
        this.hourWeatherForecastResult = hourWeatherForecastResult;
    }

    @NonNull
    @Override
    public DayDetailAdapter.DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_day2, parent, false);
        return new DayDetailAdapter.DayViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DayDetailAdapter.DayViewHolder holder, int position) {
//        WeatherOfDay x = list.get(position);
        Daily x = hourWeatherForecastResult.getDaily().get(position);
        if(x == null)   return;
        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                .append(x.getWeather().get(0).getIcon())
                .append(".png").toString()).into(holder.img);
        holder.tv2.setText(Common.converUnixToDay(x.dt));
        String s = String.valueOf(Math.round(x.getTemp().day));
        holder.tv1.setText(new StringBuilder(s.substring(0, 2)).append("°C"));
//        holder.img.setImageResource(x.getImg());
//        holder.tv1.setText(x.getDay());
//        holder.tv2.setText(x.getTemp()+"°C");
    }

    @Override
    public int getItemCount() {
        if(hourWeatherForecastResult != null)    return 7;
        return 0;
    }

    public class DayViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{
        private TextView tv1, tv2;
        private ImageView img;

        public DayViewHolder(@NonNull View view) {
            super(view);
            tv1 = view.findViewById(R.id.txt_temperature);
            tv2 = view.findViewById(R.id.txt_day);
            img = view.findViewById(R.id.img);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener != null){
//                listener.();
                listener.onItemClick(v, getAdapterPosition());
            }

        }
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
