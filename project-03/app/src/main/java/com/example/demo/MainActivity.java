package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 入口
        setContentView(R.layout.activity_main_);
        mButton = (Button) findViewById(R.id.button_1);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 跳转到TextView演示界面
                Intent intent = new Intent(MainActivity.this, TextActivity.class);
                startActivity(intent);
            }
        });
    }
}