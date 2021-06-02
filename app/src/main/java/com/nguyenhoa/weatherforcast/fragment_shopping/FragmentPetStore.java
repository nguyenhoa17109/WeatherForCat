package com.nguyenhoa.weatherforcast.fragment_shopping;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nguyenhoa.weatherforcast.R;
import com.nguyenhoa.weatherforcast.adpter.PetStoreAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPetStore#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPetStore extends Fragment {
    private int heart;
    private View v;
    private ListView listView;
    private String[] title, content, change;
    private Integer[] img;
    private PetStoreAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentPetStore() {
        // Required empty public constructor
    }

    public FragmentPetStore(int heart) {
        this.heart = heart;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPetStore.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPetStore newInstance(String param1, String param2) {
        FragmentPetStore fragment = new FragmentPetStore();
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
        v = inflater.inflate(R.layout.fragment_pet_store, container, false);
//        TextView tvHeart = v.find
        init();
        return v;
    }

    private void init() {
        listView = v.findViewById(R.id.lv_pet);
        createPetShop();
        adapter = new PetStoreAdapter(getActivity(), img, title, change, content, heart);
        listView.setAdapter(adapter);
    }

    private void createPetShop() {
        img = new Integer[]{R.drawable.ic_resource_default,
                R.drawable.ic_frame_743,
                R.drawable.ic_frame_769,
                R.drawable.ic_frame,
                R.drawable.ic_frame_768,
                R.drawable.ic_frame_744};
        title = new String[]{"MeoMeo", "Water Slime Cat", "Black Cat", "Birman Cat",
        "British ShortHair Cat", "Toyger Cat"};
        content = new String[]{"Tuitor cat, help you use app. Very naughty and sensitive.",
                "The cat made by pure water. Very friendly and easy going.",
                "They said im not lucky. Very careful and quiet.",
                "Has long fur and tail. Sweet and easy going.",
                "The cat made by pure water. Very friendly and easy going.",
                "Sparkling gold fur. Enthusiatic and jaunty."};
        change = new String[]{"0", "100", "200", "300", "600", "500"};

//        img = new Integer[]{
//                R.drawable.ic_frame_743,
//                R.drawable.ic_frame_769,
//                R.drawable.ic_frame,
//                R.drawable.ic_frame_768,
//                R.drawable.ic_frame_744};
//        title = new String[]{ "Water Slime Cat", "Black Cat", "Birman Cat",
//                "British ShortHair Cat", "Toyger Cat"};
//        content = new String[]{
//                "The cat made by pure water. Very friendly and easy going.",
//                "They said im not lucky. Very careful and quiet.",
//                "Has long fur and tail. Sweet and easy going.",
//                "The cat made by pure water. Very friendly and easy going.",
//                "Sparkling gold fur. Enthusiatic and jaunty."};
//        change = new String[]{ "100", "200", "300", "600", "500"};

    }
}