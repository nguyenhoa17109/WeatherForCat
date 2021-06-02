package com.nguyenhoa.weatherforcast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.nguyenhoa.weatherforcast.common.Alarm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SettingActivity extends AppCompatActivity {
    private Button btsave;
    private int mHour, mMinute;
    private TextView tv_time1, tv_time2, tv_time3;
    private Switch sw1, sw2, sw3;
    private RelativeLayout lay1, lay2, lay3;
    private SharedPreferences preferences;
    public static final String MyPREFERENCES = "SETTING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_setting);

        init();
    }

    private void init() {
        lay1 = findViewById(R.id.lay1);
        lay2 = findViewById(R.id.lay2);
        lay3 = findViewById(R.id.lay3);

        sw1 = findViewById(R.id.sw_breakfast);
        sw2 = findViewById(R.id.sw_lunch);
        sw3 = findViewById(R.id.sw_dinner);

        tv_time1 = findViewById(R.id.tv_time1);
        tv_time2 = findViewById(R.id.tv_time2);
        tv_time3 = findViewById(R.id.tv_time3);

        btsave = findViewById(R.id.bt_save);
        preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        boolean a1 = preferences.getBoolean("breakfast", false);
        boolean a2 = preferences.getBoolean("lunch", false);
        boolean a3 = preferences.getBoolean("dinner", false);
        String b1 = preferences.getString("time1", "");
        String b2 = preferences.getString("time2", "");
        String b3 = preferences.getString("time3", "");

        sw1.setChecked(a1);
        sw2.setChecked(a2);
        sw3.setChecked(a3);
        if(b1.equals(""))   b1="00:00 AM";
                        tv_time1.setText(b1);
        if(b2.equals(""))   b2="00:00 AM";
                        tv_time2.setText(b2);
        if(b3.equals(""))   b3="00:00 PM";
                        tv_time3.setText(b3);
        check(sw1, lay1, R.drawable.sang, "#E36E79");
        check(sw2, lay2, R.drawable.chieu, "#7C5E90");
        check(sw3, lay3, R.drawable.day, "#2E4859");
        setSwith();
        setTextView();
        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sw1.isChecked()){
                    setTime(tv_time1, "AM");
                }
                if(sw2.isChecked()){
                    setTime(tv_time2, "AM");
                }
                if(sw3.isChecked()){
                    setTime(tv_time3, "PM");
                }
                if(!sw1.isChecked() && !sw2.isChecked() && !sw3.isChecked())
                    Alarm_cancel();
                updateStatus(sw1.isChecked(), sw2.isChecked(), sw3.isChecked(),
                        tv_time1.getText().toString(), tv_time2.getText().toString()
                        , tv_time3.getText().toString());
            }
        });
    }

    private void setTextView() {
        tv_time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog(tv_time1, " AM");

            }
        });
        tv_time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog(tv_time2, " AM");

            }
        });
        tv_time3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog(tv_time3, " PM");

            }
        });
    }

    private void setDialog(TextView tv, String s) {
        Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR);
        mMinute = c.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(SettingActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String x = "";
                if(hourOfDay>9) x+=hourOfDay;
                else            x+="0"+hourOfDay;
                if(minute>9) x+=":"+minute;
                else            x+=":0"+minute;
                tv.setText(x+s);
            }
        }, mHour, mMinute, false);
        dialog.show();
    }
    private void setTime(TextView tv, String s2){
        String s = change(tv.getText().toString().trim(), s2);
        Log.d("aa", s);
        String[] s1 = s.split(":");
        int hour = Integer.parseInt(s1[0]);
        int minute = Integer.parseInt(s1[1]);

        Calendar cal= Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                hour,
                minute,
                0);
        Alarm_set(cal);
    }
    private String change(String input, String s1){
        input +=s1;
        DateFormat df = new SimpleDateFormat("hh:mm aa");
        DateFormat out = new SimpleDateFormat("HH:mm");
        String s="";
        try {
            s = out.format(df.parse(input));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return s;
    }
    private void Alarm_set(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        Toast.makeText(this,"Your Alarm is Set",Toast.LENGTH_LONG).show();
    }
    private void Alarm_cancel() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this,"Your Alarm is Cancel",Toast.LENGTH_LONG).show();

    }

    private void setSwith() {
        sw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(sw1, lay1, R.drawable.sang, "#E36E79");
            }
        });
        sw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(sw2, lay2, R.drawable.chieu, "#7C5E90");
            }
        });
        sw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(sw3, lay3, R.drawable.day, "#2E4859");
            }
        });
    }
//    Switch sw, RelativeLayout lay, int x, String s
    private void check(Switch sw, RelativeLayout lay, int x, String s){
        if(sw.isChecked()) {
            lay.setBackground(ContextCompat.getDrawable(
                    SettingActivity.this, x));
            sw.setBackgroundColor(Color.parseColor(s));
        } else {
            lay.setBackground(ContextCompat.getDrawable(
                    SettingActivity.this, R.drawable.disable_setting));
            sw.setBackgroundColor(Color.parseColor("#E0E0E0"));
        }
    }

    private void updateStatus(boolean status1,boolean status2,boolean status3,
                              String time1, String time2, String time3){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("breakfast", status1);
        editor.putBoolean("lunch", status2);
        editor.putBoolean("dinner", status3);
        editor.putString("time1", time1);
        editor.putString("time2", time2);
        editor.putString("time3", time3);
        editor.apply();
    }
}