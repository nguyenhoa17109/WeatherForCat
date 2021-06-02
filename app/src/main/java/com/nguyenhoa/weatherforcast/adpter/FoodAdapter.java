package com.nguyenhoa.weatherforcast.adpter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
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

public class FoodAdapter extends ArrayAdapter<String> {
    private Activity context;
    private Integer[] img;
    private String[] title;
    private String[] content;
    private String[] change;
    private String[] stored;
//    private int heart;
    private SharedPreferences preferences, preferences1;
//    public static final String MyPREFERENCES = "SETTING";
    public static final String MyPREFERENCES = "STORAGE";


    public FoodAdapter(@NonNull Activity context, Integer[] img, String[] stored
            , String[] title, String[] content) {
        super(context, R.layout.item_storage, title);
        this.context = context;
        this.title = title;
//        this.change = change;
        this.content = content;
        this.img = img;
        this.stored = stored;
//        this.heart = heart;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.item_storage, null, true);

        ImageView i=v.findViewById(R.id.img);
        TextView tvTitle = v.findViewById(R.id.tv_title);
        Button btChange = v.findViewById(R.id.bt_change);
        TextView tvContent = v.findViewById(R.id.tv_content);
//        TextView tvStored = v.findViewById(R.id.tv_stored);

        preferences = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        String like = preferences.getString("like", "500");
        String numfood = preferences.getString("feed", "0");

        //SL food loai i trong kho
        String storedi = preferences.getString("stored"+position, "0");
//        String heartfood = preferences.getString("heartfood"+position, "");
        int imgi = preferences.getInt("imgfood"+position, 0);
        String titlei = preferences.getString("titlefood"+position, "");
        String contenti = preferences.getString("contentfood"+position, "");

        i.setImageResource(imgi);
//        tvStored.setText("Stored: "+storedi);
        tvTitle.setText(titlei);
        tvContent.setText(contenti);
        btChange.setText(storedi);
//        int have = Integer.parseInt(like);

        final int[] n = {Integer.parseInt(numfood)};
        final int[] m = {Integer.parseInt(storedi)};
//        String s = heartfood;
//        int miss = Integer.parseInt(s);
        if(m[0] == 0) {
            btChange.setEnabled(false);
            btChange.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.disable_setting));
        }
        btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (m[0] > 0) {
//                    btChange.setText("Owner");
//                    btChange.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.back_noti));
//                    m--;
//                    String like = String.valueOf(m--);
                    String numfood = String.valueOf(n[0]--);
                    String s = String.valueOf(m[0]--);
//                    tvStored.setText(s);
//                    btChange.setCompoundDrawables(null, null, null, null);
//                    btChange.setTextColor(Color.BLACK);
                    updateStatus2(numfood);
                    updateStatus(s, position);
                    notifyDataSetChanged();
                }
            }

        });

        return v;
    }

    private void updateStatus(String stored, int i){
        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("number"+i, st);
//        editor.putInt("imgfood"+i, img);
//        editor.putString("titlefood"+i, title);
//        editor.putString("contentfood"+i, content);
        editor.putString("stored"+i, stored);
        editor.apply();
    }

    private void updateStatus2(String numfood){
        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("like", like);
        editor.putString("feed", numfood);
        editor.apply();
    }
}
