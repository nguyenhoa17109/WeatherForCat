package com.nguyenhoa.weatherforcast.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyenhoa.weatherforcast.R;
import com.nguyenhoa.weatherforcast.model.Notification;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentNotification#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentNotification extends Fragment {
    private View v;
    private RecyclerView revNoti;
    private NotificationAdapter adapter;

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
        v = inflater.inflate(R.layout.fragment_notification, container, false);
        revNoti = v.findViewById(R.id.reView);

        adapter = new NotificationAdapter(v.getContext());
        LinearLayoutManager manager = new LinearLayoutManager(v.getContext());
        revNoti.setLayoutManager(manager);
        adapter.setData(creatData());
        revNoti.setAdapter(adapter);
        return v;
    }

    private List<Notification> creatData() {
        List<Notification> list = new ArrayList<>();
        list.add(new Notification("04/01/2021", "Thoi tiet Ha Noi", "Hom nay mua to" +
                " lam ban oi nho mang theo o nhe!"));
        list.add(new Notification("06/01/2021", "Thoi tiet Ha Noi", "Hom nay mua to" +
                " lam ban oi nho mang theo o nhe!"));
        list.add(new Notification("24/01/2021", "Thoi tiet Ha Noi", "Hom nay mua to" +
                " lam ban oi nho mang theo o nhe!"));
        return list;
    }
}