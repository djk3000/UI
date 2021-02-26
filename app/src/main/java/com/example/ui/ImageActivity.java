package com.example.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ImageView cat = findViewById(R.id.cat);
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myAlertBuilder = new
                        AlertDialog.Builder(ImageActivity.this);
                myAlertBuilder.setTitle("猫");
                myAlertBuilder.setMessage("想要买我吗");
                myAlertBuilder.setNegativeButton("不买", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User cancelled the dialog.
                                Toast.makeText(getApplicationContext(), "不买",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                myAlertBuilder.setPositiveButton("确定", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked OK button.
                                Toast.makeText(getApplicationContext(), "买",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                myAlertBuilder.show();
            }
        });
    }
}