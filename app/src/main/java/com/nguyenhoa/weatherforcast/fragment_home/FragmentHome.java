package com.nguyenhoa.weatherforcast.fragment_home;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenhoa.weatherforcast.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment {
    private View v;
    private Button btToday, btTomorrow, btAfter;
    private EditText etSearch;
    private TextView tvAddr, tvTemp;
    private ImageView iv, ivSang, ivChieu, ivToi;
    private TextView tvSang, tvChieu, tvToi;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
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
        v = inflater.inflate(R.layout.fragment_home, container, false);
        init(v);
        btToday.setBackgroundColor(Color.parseColor("#E36E79"));

        btToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btToday.setBackgroundColor(Color.parseColor("#E36E79"));
                btAfter.setBackgroundColor(Color.WHITE);
                btTomorrow.setBackgroundColor(Color.WHITE);
                tvSang.setText("30°C");
                tvChieu.setText("10°C");
                tvToi.setText("30°C");

                ivSang.setImageResource(R.drawable.ic_sun40);
                ivChieu.setImageResource(R.drawable.ic_sun40);
                ivToi.setImageResource(R.drawable.ic_sun40);
            }
        });

        btTomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btTomorrow.setBackgroundColor(Color.parseColor("#E36E79"));
                btToday.setBackgroundColor(Color.WHITE);
                btAfter.setBackgroundColor(Color.WHITE);
                tvSang.setText("30°C");
                tvChieu.setText("10°C");
                tvToi.setText("30°C");

                ivSang.setImageResource(R.drawable.ic_sun40);
                ivChieu.setImageResource(R.drawable.ic_sun40);
                ivToi.setImageResource(R.drawable.ic_sun40);
            }
        });
        btAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btAfter.setBackgroundColor(Color.parseColor("#E36E79"));
//                btAfter.setTextColor(Color.WHITE);
                btToday.setBackgroundColor(Color.WHITE);
                btTomorrow.setBackgroundColor(Color.WHITE);
                tvSang.setText("30°C");
                tvChieu.setText("20°C");
                tvToi.setText("30°C");

                ivSang.setImageResource(R.drawable.ic_sun40);
                ivChieu.setImageResource(R.drawable.ic_sun40);
                ivToi.setImageResource(R.drawable.ic_sun40);
            }

        });
        return v;
    }

    private void init(View v){
        etSearch = v.findViewById(R.id.etSearch);
        tvAddr = v.findViewById(R.id.tvAddr);
        tvTemp = v.findViewById(R.id.tvTemp);
        iv = v.findViewById(R.id.img);
        btToday = v.findViewById(R.id.bt1);
        btTomorrow = v.findViewById(R.id.bt2);
        btAfter = v.findViewById(R.id.bt3);
        tvSang = v.findViewById(R.id.tvTempSang);
        tvChieu = v.findViewById(R.id.tvTempChieu);
        tvToi = v.findViewById(R.id.tvTempToi);

        ivSang = v.findViewById(R.id.imgSang);
        ivChieu = v.findViewById(R.id.imgChieu);
        ivToi = v.findViewById(R.id.imgToi);
    }
//    onClick
}