package com.nguyenhoa.weatherforcast.fragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nguyenhoa.weatherforcast.R;
import com.nguyenhoa.weatherforcast.adpter.NotificationAdapter;
import com.nguyenhoa.weatherforcast.model.Notification;
import com.nguyenhoa.weatherforcast.sqlite.SQLiteNotification;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentNotification#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentNotification extends Fragment {
    private View v;
    private FloatingActionButton btsetting;
    private RecyclerView revNoti;
    private NotificationAdapter adapter;
    private NotificationManagerCompat notificationManagerCompat;
    final String CHANNEL_ID = "101";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentNotification() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentNotification.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentNotification newInstance(String param1, String param2) {
        FragmentNotification fragment = new FragmentNotification();
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
        SQLiteNotification sqLiteNotification = new SQLiteNotification(getContext());
        List<Notification> list = sqLiteNotification.getAll();
        createNotificationChannel(v);
        v = inflater.inflate(R.layout.fragment_notification, container, false);
        revNoti = v.findViewById(R.id.reView);
        btsetting = v.findViewById(R.id.bt_setting);

        btsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_showMessage(v, list.get(list.size()-1));
            }
        });

        adapter = new NotificationAdapter(v.getContext());
        LinearLayoutManager manager = new LinearLayoutManager(v.getContext());
        revNoti.setLayoutManager(manager);
        adapter.setData(creatData(list));
        revNoti.setAdapter(adapter);
        notificationManagerCompat = NotificationManagerCompat.from(v.getContext());
//        String s = getArguments().getString("abc");
//        Log.d("OK", s);
        return v;
    }
    private void createNotificationChannel(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("This is notification channel");
            NotificationManager manager = getContext().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

    }

    public void btn_showMessage(View view, Notification n){
        final AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
        alert.setTitle("Setting");
        View mView = getLayoutInflater().inflate(R.layout.activity_setting,null);
        TextView tvTime = mView.findViewById(R.id.tv_time);
        Button bt_date = mView.findViewById(R.id.bt_date);
        Button bt_save = mView.findViewById(R.id.bt_save);
        Switch sw1 = mView.findViewById(R.id.sw_noti);
        Switch sw2 = mView.findViewById(R.id.sw_feed);
        LinearLayout a = mView.findViewById(R.id.time);

        sw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sw1.isChecked()) a.setVisibility(View.VISIBLE);
                else a.setVisibility(View.GONE);
            }
        });
        if(sw1.isChecked()) a.setVisibility(View.VISIBLE);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tvTime.getText().toString().equals("")){
                    sendOnChannel(v, tvTime, n);
                }

                alertDialog.dismiss();
            }
        });
        bt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mHour, mMinute;
                Calendar calendar = Calendar.getInstance();
                mHour = calendar.get(Calendar.HOUR_OF_DAY);
                mMinute = calendar.get(Calendar.MINUTE);
                TimePickerDialog dialog = new TimePickerDialog(view.getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                tvTime.setText(hourOfDay+":"+minute);
                            }
                        }, mHour, mMinute, true);
                dialog.show();
            }
        });
        alertDialog.show();
    }
    private void sendOnChannel(View v, TextView editTime, Notification n) {
        final String time = editTime.getText().toString();
        String[] tmp = time.split(":");
        final int tmpHour, tmpMinute, tmpSecond;
        tmpHour = Integer.parseInt(tmp[0]);
        tmpMinute = Integer.parseInt(tmp[1]);
        tmpSecond = 0;
        int notificationId = 1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    Calendar c = Calendar.getInstance();
                    int hour, minute, second;
                    hour = c.get(Calendar.HOUR_OF_DAY);
                    minute = c.get(Calendar.MINUTE);
                    second = c.get(Calendar.SECOND);
                    if (hour == tmpHour && minute == tmpMinute &&
                            second >= tmpSecond) {
                        NotificationCompat.Builder builder =
                                new NotificationCompat.Builder(v.getContext(),CHANNEL_ID)
                                        .setSmallIcon(R.drawable.ic_baseline_cloud)
                                        .setContentTitle(n.getDay()+"\n"+n.getTitle())
                                        .setContentText(n.getContent())
                                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                                        .setAutoCancel(false);
                        NotificationManagerCompat notificationManagerCompat =
                                NotificationManagerCompat.from(v.getContext());
                        notificationManagerCompat.notify(notificationId, builder.build());
                        break;
                    }
                }
            }
        }).start();
    }

    private List<Notification> creatData(List<Notification> list) {
        List<Notification> lst = new ArrayList<>();
        for(int i=list.size()-1; i>=1; i--){
            lst.add(list.get(i));
        }
//        list.add(new Notification("04/01/2021", "Thoi tiet Ha Noi", "Hom nay mua to" +
//                " lam ban oi nho mang theo o nhe!"));
//        list.add(new Notification("06/01/2021", "Thoi tiet Ha Noi", "Hom nay mua to" +
//                " lam ban oi nho mang theo o nhe!"));
//        list.add(new Notification("24/01/2021", "Thoi tiet Ha Noi", "Hom nay mua to" +
//                " lam ban oi nho mang theo o nhe!"));
        return lst;
    }
}