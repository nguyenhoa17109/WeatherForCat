package com.nguyenhoa.weatherforcast.fragment_shopping;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nguyenhoa.weatherforcast.R;
import com.nguyenhoa.weatherforcast.adpter.FoodStoreAdapter;
import com.nguyenhoa.weatherforcast.adpter.PetStoreAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentFoodStore#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFoodStore extends Fragment {
    private int heart;
    private View v;
    private ListView listView;
    private String[] title, content, change, stored;
    private Integer[] img;
    private FoodStoreAdapter adapter;
    private SharedPreferences preferences;
    public static final String MyPREFERENCES = "SETTING";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentFoodStore() {
        // Required empty public constructor
    }

    public FragmentFoodStore(int heart) {
        this.heart = heart;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentFoodStore.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentFoodStore newInstance(String param1, String param2) {
        FragmentFoodStore fragment = new FragmentFoodStore();
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
        v = inflater.inflate(R.layout.fragment_food_store, container, false);
        init();
        return v;
    }

    private void init() {
        listView = v.findViewById(R.id.lv_pet);
        createFoodShop();
        reset();
        adapter = new FoodStoreAdapter(getActivity(), img, stored, title, change, content,  heart);
        listView.setAdapter(adapter);
    }

    private void createFoodShop() {
        img = new Integer[]{R.drawable.ic_group_780, R.drawable.ic_group_781, R.drawable.ic_group_782};
        title = new String[]{"Mòe Ó", "Royal Catin", "CatAkit"};
        change = new String[]{"20", "50", "20"};
        stored = new String[]{"0", "0", "0"};
        content = new String[]{"Provide a full range of nutrients to help pet stay healthy and happy.",
        "Nutrion suitable for animal health, providing necessary energy and preventing diseases.",
        "Provide superior nutrion to help them easily digest and absorb food."};
    }

    private void reset(){
        preferences = v.getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("like", "500");
//        editor.putString("feed", "0");
//        editor.putString("numcat", "0");
//        editor.putString("number0", "0");
//        editor.putString("key"+0, "");
//        for(int i=1; i<6; i++){
//            editor.putString("key"+i, "Change");
//            editor.putString("number"+i, i+"00");
//        }
        for(int i=0; i<3; i++){
            editor.putInt("imgfood"+img[i], 0);
            editor.putString("titlefood"+title[i], "");
            editor.putString("contentfood"+content[i], "");
            editor.putString("stored"+stored[i], "0");
        }
        editor.apply();
    }
}