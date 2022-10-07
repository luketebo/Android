package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity {

    private TextView Tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        Tv5 = (TextView) findViewById(R.id.tv_5);
        // 绘制中划线
        Tv5.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        Tv5.getPaint().setAntiAlias(true); // 抗锯齿

    }
}