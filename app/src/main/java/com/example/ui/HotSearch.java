package com.example.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import entity.hotModel;
import utiliy.HotListAdapter;
import utiliy.SmartHttp;

public class HotSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_search);

        sh = new SmartHttp();

        Log.d("address", getResources().getString(R.string.address));
        String address = getResources().getString(R.string.address);

        try {
            sh.SyncGet(address, handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1000:
                    Gson gson = new Gson();
                    hotModel hm = gson.fromJson((String) msg.obj, hotModel.class);
                    Log.d("code", hm.getCode());
                    int count = 0;
                    ArrayList<String> list = new ArrayList<String>();
                    for (hotModel.hotData data : hm.getData()) {
                        count += 1;
                        String resultMessage = count + "." + data.getHot_word() + "(" + data.getHot_word_num() + ")";
                        list.add(resultMessage);
                    }
                    recyclerView = findViewById(R.id.recycler);
                    hotListAdapter = new HotListAdapter(list);
                    recyclerView.setLayoutManager(mLinearLayoutManager);
                    recyclerView.setAdapter(hotListAdapter);

                    break;
            }
        }
    };
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView recyclerView;
    private HotListAdapter hotListAdapter;
    private TextView view;
    protected SmartHttp sh;
}