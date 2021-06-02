package com.nguyenhoa.weatherforcast.adpter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.nguyenhoa.weatherforcast.R;

public class StorageAdapter extends ArrayAdapter<String> {
    private Activity context;
    private Integer[] img;
    private String[] title;
    private String[] content;
    private String[] change;
    private SharedPreferences preferences, preferences1;
//    public static final String MyPREFERENCES = "SETTING";
    public static final String MyPREFERENCES = "STORAGE";

    public StorageAdapter(@NonNull Activity context, Integer[] img
            , String[] title, String[] change, String[] content) {
        super(context, R.layout.item_petstore, title);
        this.context = context;
        this.title = title;
        this.change = change;
        this.content = content;
        this.img = img;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.item_storage, null, true);

        //gan tung doi tuong vao layout
        ImageView i=v.findViewById(R.id.img);
        TextView tvTitle = v.findViewById(R.id.tv_title);
        Button btChange = v.findViewById(R.id.bt_change);
        TextView tvContent = v.findViewById(R.id.tv_content);

        //position vtri gan doi tuong
//        Toast.makeText(getContext(), "LOLO1",Toast.LENGTH_SHORT);
        preferences = v.getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        preferences1 = getContext().getSharedPreferences(MyPREFERENCES1, Context.MODE_PRIVATE);
        String key = preferences.getString("key"+position, "");
        int img = preferences.getInt("img"+position, 0);
        String title = preferences.getString("title"+position, "");
        String content = preferences.getString("content"+position, "");
//        Log.d("LOLOa", key);
        i.setImageResource(img);
        tvTitle.setText(title);
        btChange.setText(key);
        tvContent.setText(content);
//        if(position==0) key=""
//        btChange.setEnabled(true);
//        String key="Change";
        if(key.equals("Using")){
            btChange.setEnabled(false);
            btChange.setText("Using");
            btChange.setTextColor(Color.WHITE);
            btChange.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.chieu));
        }else{

            btChange.setTextColor(Color.BLACK);
            btChange.setText("Change");
            btChange.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.back_noti));

        }


        btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(key.equals("Change")){
//                    Toast.makeText(v.getContext(), "LOLO",Toast.LENGTH_SHORT);
                    Log.d("LOLO", key);
                    btChange.setEnabled(true);
                    btChange.setText("Using");
                    btChange.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.chieu));
                    updateStatus(position);
                    notifyDataSetChanged();
                }
            }
        });
        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "LOLO",Toast.LENGTH_SHORT);
            }
        });
        return v;
    }

    private void updateStatus(int i){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("key"+i, "Using");
        for(int j=0; j<6; j++){
            if(j != i)
                editor.putString("key"+i, "Change");
        }
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
