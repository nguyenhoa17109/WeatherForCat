package com.nguyenhoa.weatherforcast.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenhoa.weatherforcast.R;
import com.nguyenhoa.weatherforcast.adpter.MessageAdapter;
import com.nguyenhoa.weatherforcast.model.Notification;
import com.nguyenhoa.weatherforcast.model.ResponseMessage;
import com.nguyenhoa.weatherforcast.sqlite.SQLiteNotification;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCare#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCare extends Fragment {
    View v;
    EditText userInput;
    AnimationDrawable rocketAnimation;
    ImageView img;
    RecyclerView recyclerView;
    MessageAdapter messageAdapter;
    List<ResponseMessage> responseMessageList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentCare() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCare.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCare newInstance(String param1, String param2) {
        FragmentCare fragment = new FragmentCare();
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
        v = inflater.inflate(R.layout.fragment_care, container, false);
        init(v);
        SQLiteNotification sqLiteNotification = new SQLiteNotification(v.getContext());
        List<Notification> list = sqLiteNotification.getAll();
//        sqLiteNotification.delete(5);
//        sqLiteNotification.delete(6);
        Notification result = new Notification();
        if(list.size()>1)
            result = list.get(list.size()-1);
//        Notification finalResult = result;
        Notification finalResult = result;
        userInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEND) {
                    String s = userInput.getText().toString();
                    ResponseMessage responseMessage = new ResponseMessage(s, true);
                    responseMessageList.add(responseMessage);

                    ResponseMessage responseMessage2 = setMessageBot(s, finalResult);
                    responseMessageList.add(responseMessage2);
                    messageAdapter.notifyDataSetChanged();
                    userInput.setText("");
                    if (!isLastVisible())
                        recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                }
                return false;
            }
        });
        return v;
    }

    private ResponseMessage setMessageBot(String s, Notification result) {
        String s1="";

        switch (s){
            case "1":{
                s1 = "Choose one option:\na. Get main.\nb. Get win.\nc. Get clouds.\nd. Exit.";
                break;
            }
            case "2":{
                s1 = result.getGeneral();
                break;
            }
            case "a":{
                s1 = result.getMain();
                break;
            }
            case "b":{
                s1 = result.getWin();
                break;
            }
            case "c":{
                s1 = result.getClouds();
                break;
            }
            default:{
                s1 = "What do you want to know:" +
                        "\n1.Get weather detail.\n2.Get weather general.";
            }
        }

        return new ResponseMessage(s1, false);
    }

    private void init(View v) {
        img = v.findViewById(R.id.img);
        userInput = v.findViewById(R.id.userInput);
        recyclerView = v.findViewById(R.id.conversation);
        responseMessageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(responseMessageList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(messageAdapter);

        img.setBackgroundResource(R.drawable.cat);
        rocketAnimation = (AnimationDrawable) img.getBackground();
        rocketAnimation.start();
        createStart();
    }

    private void createStart() {
        ResponseMessage r = new ResponseMessage("What do you want to know:" +
                "\n1.Get weather detail.\n2.Get weather general."
                , false);
        responseMessageList.add(r);
        messageAdapter.notifyDataSetChanged();
    }
    boolean isLastVisible() {
        LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
        int pos = layoutManager.findLastCompletelyVisibleItemPosition();
        int numItems = recyclerView.getAdapter().getItemCount();
        return (pos >= numItems);
    }
}