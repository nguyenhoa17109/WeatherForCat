package com.nguyenhoa.weatherforcast.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenhoa.weatherforcast.R;
import com.nguyenhoa.weatherforcast.SettingActivity;
import com.nguyenhoa.weatherforcast.ShoppingActivity;
import com.nguyenhoa.weatherforcast.StorageActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentUser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUser extends Fragment {
    private Button bt_edit, bt_shop, bt_change, bt_setting;
    private TextView tv_name, tv_description, txt_like, txt_feed, txt_numcat;
    private View v;
    private ImageView img_notification;
    private SharedPreferences preferences;
    public static final String MyPREFERENCES = "SETTING";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentUser() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentUser.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentUser newInstance(String param1, String param2) {
        FragmentUser fragment = new FragmentUser();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        savedInstanceState.clear();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_user, container, false);

        init(v);
        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                alert.setTitle("Change profile");
                View mView = getLayoutInflater().inflate(R.layout.dialog_edit,null);
                EditText et_name = mView.findViewById(R.id.et_name);
                EditText et_description = mView.findViewById(R.id.et_description);
                Button bt_save = mView.findViewById(R.id.bt_save);
                alert.setView(mView);
                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);
                bt_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = et_name.getText().toString();
                        String description = et_description.getText().toString();
                        tv_name.setText(name);
                        tv_description.setText(description);
                        updateStatus1(name, description);
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
        bt_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
//                savedInstanceState.clear();
                startActivity(intent);
            }
        });
        bt_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShoppingActivity.class);
                startActivity(intent);
            }
        });
        bt_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StorageActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    private void init(View v) {
        bt_edit = v.findViewById(R.id.bt_edit);
        bt_change = v.findViewById(R.id.bt_change);
        bt_setting = v.findViewById(R.id.bt_setting);
        bt_shop = v.findViewById(R.id.bt_shop);

        tv_name = v.findViewById(R.id.tv_name);
        tv_description = v.findViewById(R.id.tv_description);

        txt_like =v.findViewById(R.id.txt_like);
        txt_feed = v.findViewById(R.id.txt_feed);
        txt_numcat = v.findViewById(R.id.txt_numcat);
        img_notification = v.findViewById(R.id.img_notification);

        preferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        reset();
        int img = preferences.getInt("img", 0);
        String like = preferences.getString("like", "500");
        String food = preferences.getString("feed", "0");
        String numcat = preferences.getString("numcat", "0");
        String name = preferences.getString("name", "Meo");
        String description = preferences.getString("description", "This is description");
//        if(name == null) name="Meo";
//        if(description == null)  description="none";
//        if(like==null) like="500";
//        if(food==null) food="500";
//        if(numcat==null)   numcat="1";
        tv_name.setText(name);
        tv_description.setText(description);
        txt_like.setText(like);
        txt_feed.setText(food);
        txt_numcat.setText(numcat);
        img_notification.setImageResource(img);
    }

    private void updateStatus1(String name, String description){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.putString("description", description);
        editor.apply();
    }

    private void updateStatus2(String like, String feed, String numcat){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("like", like);
        editor.putString("feed", feed);
        editor.putString("numcat", numcat);
        editor.apply();
    }

    private void reset(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("like", "500");
        editor.putString("feed", "0");
        editor.putString("numcat", "0");
        editor.putString("number0", "0");
        editor.putString("key"+0, "");
//        for(int i=1; i<6; i++){
//            editor.putString("key"+i, "Change");
//            editor.putString("number"+i, i+"00");
//        }
//        for(int i=0; i<3; i++){
////            editor.putInt("imgfood"+i, 0);
////            editor.putString("titlefood"+i, "");
////            editor.putString("contentfood"+i, "");
//            editor.putString("stored"+i, "0");
//        }
        editor.apply();
    }
}