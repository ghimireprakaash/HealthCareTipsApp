package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HealthTips extends AppCompatActivity {
    private Toolbar toolbar;

    TextView healthTips, menHealthTips, womenHealthTips, healthyHeartTips, relieveStressTips, seasonalTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthTips.this, MainActivity.class));
                finish();
            }
        });



        healthTips = findViewById(R.id.healthTips);
        menHealthTips = findViewById(R.id.menHealthTips);
        womenHealthTips = findViewById(R.id.womenHealthTips);
        healthyHeartTips = findViewById(R.id.healthyHeartTips);
        relieveStressTips = findViewById(R.id.stressRelieveTips);
        seasonalTips = findViewById(R.id.seasonalTips);


        healthTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTips.this, SubClassHealthTips.class));
                finish();
            }
        });


        menHealthTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTips.this, HealthTipsForMen.class));
                finish();
            }
        });


        womenHealthTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTips.this, HealthTipsForWomen.class));
                finish();
            }
        });


        healthyHeartTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTips.this, HealthyHeart.class));
                finish();
            }
        });


        relieveStressTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTips.this, RelieveStress.class));
                finish();
            }
        });


        seasonalTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTips.this, SeasonalTips.class));
                finish();
            }
        });
    }
}
