package com.nguyenhoa.weatherforcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.nguyenhoa.weatherforcast.adpter.FoodAdapter;
import com.nguyenhoa.weatherforcast.adpter.StorageAdapter;

import java.util.ArrayList;
import java.util.List;

public class StorageActivity extends AppCompatActivity {
    private ListView listView_pet, listView_food;
    private TextView tvHeart;
    private String[] title, content, change, stored;
    private Integer[] img;
    private StorageAdapter adapter;
    private FoodAdapter adapter1;
    private SharedPreferences preferences, preferences1;
    public static final String MyPREFERENCES = "SETTING";
    public static final String MyPREFERENCES1 = "STORAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        init();

//        listView_food.setAdapter(adapter);
    }

    private void init() {
        tvHeart = findViewById(R.id.tv_heart);
        listView_pet = findViewById(R.id.lv_pet);
        listView_food = findViewById(R.id.lv_food);
        preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        preferences1 = getSharedPreferences(MyPREFERENCES1, Context.MODE_PRIVATE);
        String like = preferences.getString("like", "500");

        tvHeart.setText(like);
        createStorage();
        adapter = new StorageAdapter(this, img, title, change, content);
        listView_pet.setAdapter(adapter);

        createStorage1();
        adapter1 = new FoodAdapter(this, img, stored, title, content);
        listView_food.setAdapter(adapter1);
    }

    private void createStorage1() {
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        List<String> l3 = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        img = new Integer[3];
        title = new String[3];
        content = new String[3];
        stored = new String[3];

        for(int i=0; i<3; i++){
            String s = preferences.getString("stored"+i, "0");
            int k = Integer.parseInt(s);
            if(!s.equals("") || k > 0){
                l1.add(preferences.getString("titlefood"+i, ""));
                l2.add(preferences.getString("contentfood"+i, ""));
                l.add(preferences.getInt("imgfood"+i, 0));
                l3.add(s);
            }
//            Log.d("AKAK", String.valueOf(preferences.getInt("imgfood"+i, 0)));
        }
        for(int i=0; i<l1.size(); i++){
            stored[i] = l3.get(i);
            title[i] = l1.get(i);
            content[i] = l2.get(i);
            img[i] = l.get(i);
            updateStatus1(img[i], title[i], content[i], i, stored[i]);

        }
//        Log.d("AKAKA", String.valueOf(img));
    }

    private void updateStatus1(Integer img, String title, String content, int i, String store){
        SharedPreferences.Editor editor = preferences1.edit();
        editor.putInt("imgfood"+i, img);
        editor.putString("titlefood"+i, title);
        editor.putString("contentfood"+i, content);
        editor.putString("stored"+i, store);
        editor.apply();
        Log.d("AKA", String.valueOf(preferences1.getInt("imgfood"+i, 0)));
    }

    private void createStorage() {
//        int k=-1;
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        List<String> l3 = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        for (int i=0; i<6; i++){
            String s = preferences.getString("number"+i, "");
            if(s.equals("Owner")){
//                k++;
                l.add(preferences.getInt("img"+i, 0));
                l1.add(preferences.getString("title"+i, ""));
                l2.add(preferences.getString("content"+i, ""));
                l3.add(preferences.getString("key"+i, ""));

                Log.d("AAA", preferences.getString("title"+i, ""));
            }
        }
        title = new String[l1.size()];
        content=new String[l2.size()];
        change=new String[l3.size()];
        img=new Integer[100];
        for(int i=0; i<l1.size(); i++){
            title[i] = l1.get(i);
            content[i] = l2.get(i);
//            change[i] = l3.get(i);
            img[i] = l.get(i);
            updateStatus(img[i], title[i], content[i], i);
            Log.d("AAA", title[i]);
        }

    }

    private void updateStatus(Integer img, String title, String content, int i){
        SharedPreferences.Editor editor = preferences1.edit();
        editor.putInt("img"+i, img);
        editor.putString("title"+i, title);
        editor.putString("content"+i, content);
        editor.putString("key"+i, "Change");
        editor.apply();
    }


}