package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void look(View view) {
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com/")));
        Intent it = new Intent(this, DisplayActivity.class);
        it.putExtra("text", "chenfeng");
        startActivity(it);
    }

    public void other(View view) {
        Intent it = new Intent(this,HotSearch.class);
        startActivity(it);

    }

}