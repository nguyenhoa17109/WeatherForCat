package com.nguyenhoa.weatherforcast.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.weatherforcast.R;
import com.nguyenhoa.weatherforcast.model.Notification;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private Context context;
    private List<Notification> list;

    public NotificationAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Notification> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_notification, parent, false);

        return new NotificationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification n = list.get(position);
        if(n == null) return;
        holder.tvTime.setText(n.getDay());
        holder.tvTitle.setText(n.getTitle());
        holder.tvContent.setText(n.getContent());
    }

    @Override
    public int getItemCount() {
        if(list != null)    return list.size();
        return 0;
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTime, tvTitle, tvContent;

        public NotificationViewHolder(@NonNull View view) {
            super(view);
            tvTime = view.findViewById(R.id.tvTime);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvContent = view.findViewById(R.id.tvContent);
        }
    }
}
