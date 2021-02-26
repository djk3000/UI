package com.example.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

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

        //横屏
        if (ScreenOrient(this) == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            //竖向布局
            mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        }
        //竖屏
        if (ScreenOrient(this) == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            //网格布局
            mLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
            dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        }
        Drawable mDrawable = ContextCompat.getDrawable(this, R.drawable.divderitem);
        dividerItemDecoration.setDrawable(mDrawable);
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
                    //设置recycleer
                    hotListAdapter = new HotListAdapter(list);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setAdapter(hotListAdapter);
                    recyclerView.addItemDecoration(dividerItemDecoration);

                    hotListAdapter.setOnItemClickListener(new HotListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            String str = list.get(position);
                            Toast.makeText(HotSearch.this, "click " + str, Toast.LENGTH_SHORT).show();
                        }
                    });

                    hotListAdapter.setOnItemLongClickListener(new HotListAdapter.OnItemLongClickListener() {
                        @Override
                        public void onItemLongClick(View view, int position) {
                            String str = list.get(position);
                            Toast.makeText(HotSearch.this, "longclick " + str, Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
            }
        }
    };

    public int ScreenOrient(Activity activity) {
        int orient = activity.getRequestedOrientation();
        if (orient != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE && orient != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);

            int screenWidth = metrics.widthPixels;
            int screenHeight = metrics.heightPixels;
            orient = screenWidth < screenHeight ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }
        return orient;
    }

    private DividerItemDecoration dividerItemDecoration;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView recyclerView;
    private HotListAdapter hotListAdapter;
    private TextView view;
    protected SmartHttp sh;
}