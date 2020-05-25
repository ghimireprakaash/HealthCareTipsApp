package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class SplashScreenAndProgressBar extends AppCompatActivity {
    ProgressBar progressBar_SPLASHSCREEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_and_progress_bar);
        
//        init();

        //Initializing ProgressBar in java class with respect to its xml layout.
        progressBar_SPLASHSCREEN = findViewById(R.id.progressBar_SPLASH);


        //Setting VISIBILITY of ProgressBar to VISIBLE so it can appear when called.
        progressBar_SPLASHSCREEN.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        int SPLASH_SCREEN = 5000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar_SPLASHSCREEN.setVisibility(View.GONE);
                Intent intent = new Intent(SplashScreenAndProgressBar.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }

//    private void init() {
//        this.progressBar_SPLASHSCREEN = findViewById(R.id.progressBar_SPLASH);
//    }
}