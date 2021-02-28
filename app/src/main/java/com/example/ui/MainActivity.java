package com.example.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_display:
                look(null);
                break;
            case R.id.item_hotSearch:
                other(null);
                break;
            case R.id.item_picture:
                picture(null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void look(View view) {
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com/")));
        Intent it = new Intent(this, DisplayActivity.class);
        it.putExtra("text", "chenfeng");
        startActivity(it);
    }

    public void other(View view) {
        Intent it = new Intent(this, HotSearch.class);
        startActivity(it);
    }

    public void login(View view) {
        Intent it = new Intent(this, LoginContentActivity.class);
        TextView textView = findViewById(R.id.userName);
        String name = textView.getText().toString();
        Log.d("name", name);
        it.putExtra(EXTRA_MESSAGE, name);
        startActivityForResult(it, TEXT_REQUEST);
    }

    public void picture(View view) {
        Intent it = new Intent(this, ImageActivity.class);
        startActivity(it);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("reply");
                Log.d("result----------", result);
                TextView textView = findViewById(R.id.userName);
                textView.setText(result);
            }
        }
    }

    public static final int TEXT_REQUEST = 1;
    public static final String EXTRA_MESSAGE = "username";

    public void dataBinding(View view) {
        Intent it = new Intent(this, DataBindingActivity.class);
        startActivity(it);
    }
}