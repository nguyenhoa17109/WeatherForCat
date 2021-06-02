package com.nguyenhoa.weatherforcast.adpter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.nguyenhoa.weatherforcast.R;
import com.nguyenhoa.weatherforcast.ShoppingActivity;

public class PetStoreAdapter extends ArrayAdapter<String> {
    private Activity context;
    private Integer[] img;
    private String[] title;
    private String[] content;
    private String[] change;
    private int heart;
    private SharedPreferences preferences, preferences1;
    public static final String MyPREFERENCES = "SETTING";
//    public static final String MyPREFERENCES1 = "STORAGE";

    public PetStoreAdapter(@NonNull Activity context, Integer[] img
            , String[] title, String[] change, String[] content, int heart) {
        super(context, R.layout.item_petstore, title);
        this.context = context;
        this.title = title;
        this.change = change;
        this.content = content;
        this.img = img;
        this.heart = heart;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.item_petstore, null, true);

        //gan tung doi tuong vao layout
        ImageView i=v.findViewById(R.id.img);
        TextView tvTitle = v.findViewById(R.id.tv_title);
        Button btChange = v.findViewById(R.id.bt_change);
        TextView tvContent = v.findViewById(R.id.tv_content);

        //position vtri gan doi tuong


        preferences = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        preferences1 = getContext().getSharedPreferences(MyPREFERENCES1, Context.MODE_PRIVATE);
        String like = preferences.getString("like", "500");
        String numcat = preferences.getString("numcat", "0");
        String number = preferences.getString("number"+position, change[position]);
        String key = preferences.getString("key"+position, "");
        int imgi = preferences.getInt("img"+position, img[position]);
        String titlei = preferences.getString("title"+position, title[position]);
        String contenti = preferences.getString("content"+position, content[position]);

        i.setImageResource(imgi);
        tvTitle.setText(titlei);
        tvContent.setText(contenti);
        btChange.setText(number);
        int have = Integer.parseInt(like);

        int n = Integer.parseInt(numcat);
        String s = btChange.getText().toString();
        int miss = 1000;
        if(number.equals("Owner")){
            btChange.setEnabled(false);
            btChange.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.back_noti));
            btChange.setCompoundDrawables(null, null, null, null);
            btChange.setTextColor(Color.BLACK);
        }else{
            miss = Integer.parseInt(s);
            if(miss > have){
                btChange.setEnabled(false);
                btChange.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.disable_setting));
            }
        }
        int finalMiss = miss;
        btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalMiss <= have) {
                    btChange.setText("Owner");
                    btChange.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.back_noti));
                    String like = String.valueOf(have - finalMiss);
                    String numcat = String.valueOf(n + 1);
                    btChange.setCompoundDrawables(null, null, null, null);
                    btChange.setTextColor(Color.BLACK);
                    updateStatus2(like, numcat);
                    updateStatus("Owner", img[position], title[position], content[position], position);
                    notifyDataSetChanged();
                }
            }

        });
        return v;
    }

    private void updateStatus(String st, Integer img, String title, String content, int i){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("number"+i, st);
        editor.putInt("img"+i, img);
        editor.putString("title"+i, title);
        editor.putString("content"+i, content);
        editor.putString("key"+i, "Change");
        editor.apply();
    }

    private void updateStatus2(String like, String numcat){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("like", like);
        editor.putString("numcat", numcat);
        editor.apply();
    }

    public String getTitle(int p) {
        return title[p];
    }

    public String getContent(int p) {
        return content[p];
    }

    public String getChange(int p) {
        return change[p];
    }
}
