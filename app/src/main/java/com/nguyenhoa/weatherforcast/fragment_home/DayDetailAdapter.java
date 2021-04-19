package com.nguyenhoa.weatherforcast.fragment_home;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.weatherforcast.R;
import com.nguyenhoa.weatherforcast.model.WeatherOfDay;

import java.util.List;

public class DayDetailAdapter extends
        RecyclerView.Adapter<DayDetailAdapter.DayViewHolder> {
    private Context context;
    private List<WeatherOfDay> list;
    private ItemClickListener listener;

    public DayDetailAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<WeatherOfDay> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_day2, parent, false);
        return new DayViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        WeatherOfDay x = list.get(position);
        if(x == null)   return;
        holder.img.setImageResource(x.getImg());
        holder.tv2.setText(x.getDay());
        holder.tv1.setText(x.getTemp()+" C");
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.itemView.setBackgroundColor(Color.parseColor("#4A567B"));
//            }
//        });
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

    public String getItem(int position){
        return list.get(position).toString();
//        list.get(position).
    }

    public class DayViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{
        private TextView tv1, tv2;
        private ImageView img;

        public DayViewHolder(@NonNull View view) {
            super(view);
            tv1 = view.findViewById(R.id.tvTemp);
            tv2 = view.findViewById(R.id.tvDay);
            img = view.findViewById(R.id.iv);

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
