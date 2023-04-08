package com.example.storyapp2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;



public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //bắt đầu màn hình sau 2s
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //start main screen
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        },2000);
    }
}