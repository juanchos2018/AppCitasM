package com.example.appv4.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.appv4.DasboardActivity;
import com.example.appv4.R;

public class SplashActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar=findViewById(R.id.splashProgress);
        ObjectAnimator.ofInt(progressBar, "progress",100).setDuration(5000).start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, DasboardActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }
}