package com.nguyenhoa.weatherforcast.adpter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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

public class FoodStoreAdapter extends ArrayAdapter<String> {
    private Activity context;
    private Integer[] img;
    private String[] title;
    private String[] content;
    private String[] change;
    private String[] stored;
    private int heart;
    private SharedPreferences preferences, preferences1;
    public static final String MyPREFERENCES = "SETTING";

    public FoodStoreAdapter(@NonNull Activity context, Integer[] img, String[] stored
            , String[] title, String[] change, String[] content, int heart) {
        super(context, R.layout.item_foodstore, title);
        this.context = context;
        this.title = title;
        this.change = change;
        this.content = content;
        this.img = img;
        this.stored = stored;
        this.heart = heart;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.item_foodstore, null, true);

        //gan tung doi tuong vao layout
        ImageView i=v.findViewById(R.id.img);
        TextView tvTitle = v.findViewById(R.id.tv_title);
        Button btChange = v.findViewById(R.id.bt_change);
        TextView tvContent = v.findViewById(R.id.tv_content);
        TextView tvStored = v.findViewById(R.id.tv_stored);

        preferences = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String like = preferences.getString("like", "500");
        String numfood = preferences.getString("feed", "0");

        //SL food loai i trong kho
        String storedi = preferences.getString("stored"+position, stored[position]);
        String heartfood = preferences.getString("heartfood"+position, change[position]);
        int imgi = preferences.getInt("imgfood"+position, img[position]);
        String titlei = preferences.getString("titlefood"+position, title[position]);
        String contenti = preferences.getString("contentfood"+position, content[position]);

        //position vtri gan doi tuong
        i.setImageResource(imgi);
        tvTitle.setText(titlei);
        btChange.setText(heartfood);
        tvContent.setText(contenti);
        tvStored.setText("Stored: "+storedi);
        int have = Integer.parseInt(like);

        int n = Integer.parseInt(numfood);
        int m = Integer.parseInt(storedi);
        String s = heartfood;
        int miss = Integer.parseInt(s);
        if(miss > have) {
            btChange.setEnabled(false);
            btChange.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.disable_setting));
        }
        btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (miss <= have) {
//                    btChange.setText("Owner");
//                    btChange.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.back_noti));
                    String like = String.valueOf(have - miss);
                    String numfood = String.valueOf(n + 1);
                    String s = String.valueOf(m+1);
//                    tvStored.setText(s);
//                    btChange.setCompoundDrawables(null, null, null, null);
//                    btChange.setTextColor(Color.BLACK);
                    updateStatus2(like, numfood);
//                    if(s)
                    updateStatus(img[position], title[position], content[position], s, position);
                    notifyDataSetChanged();
                }
            }

        });


        return v;
    }

    private void updateStatus(Integer img, String title, String content, String stored, int i){
        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("number"+i, st);
        editor.putInt("imgfood"+i, img);
        editor.putString("titlefood"+i, title);
        editor.putString("contentfood"+i, content);
        editor.putString("stored"+i, stored);
        editor.apply();
    }

    private void updateStatus2(String like, String numfood){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("like", like);
        editor.putString("feed", numfood);
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

    public String getStored(int p){
        return stored[p];
    }
}
