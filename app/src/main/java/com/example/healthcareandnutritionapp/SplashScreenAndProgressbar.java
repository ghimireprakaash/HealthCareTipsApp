package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class SplashScreenAndProgressbar extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_and_progressbar);

        progressBar = findViewById(R.id.progressBar);

    }


    @Override
    protected void onStart() {
        super.onStart();

        progressBar.setVisibility(View.VISIBLE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(SplashScreenAndProgressbar.this, MainActivity.class));
                    finish();

                    progressBar.setVisibility(View.GONE);

                }
            }, 3000);
        }
}
