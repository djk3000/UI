package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.ui.databinding.ActivityDataBindingBinding;

import java.util.ArrayList;
import java.util.List;

import entity.DataModel;
import entity.RecycleDataModel;
import utiliy.databindingList;

public class DataBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding);

        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        user = new DataModel("chenfeng", "123456", 123456);
        binding.setUserInfo(user);
        binding.setUserHandler(new DataHandler());
        user.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (propertyId == BR.name) {
                    Log.i("changeusername", "BR.name");
                }
            }
        });

        initList();
    }

    public class DataHandler {
        public void changeUserName() {
            user.setName("wkw");
            user.setNumber(20000);
        }

        public void changePassword() {
            user.setPassword("654321");
            user.setNumber(40000);
        }
    }

    private void initList() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<RecycleDataModel> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            RecycleDataModel recycle = new RecycleDataModel();
            recycle.getTitle().set(String.valueOf(i));
            list.add(recycle);
        }
        databindingList adapter = new databindingList(list,this);
        recyclerView.setAdapter(adapter);
    }
    private DataModel user;
}