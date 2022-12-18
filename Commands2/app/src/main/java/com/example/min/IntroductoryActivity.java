package com.example.min;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class IntroductoryActivity extends AppCompatActivity {
    ImageView bg;
    TextView title;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        lottieAnimationView = findViewById(R.id.lottie);
        bg = findViewById(R.id.bg);
        title = findViewById(R.id.title);


        bg.animate().translationY(-2600).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1800).setDuration(1000).setStartDelay(4000);
        title.animate().translationY(1800).setDuration(1000).setStartDelay(4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent=new Intent(IntroductoryActivity.this,MainActivity.class);
//                startActivity(intent);
//                finish();
                Intent intent = new Intent( );
                intent.setClass(IntroductoryActivity.this, Login_signupActivity.class);
                startActivity(intent);
            }
        }, 5000);
    }
}