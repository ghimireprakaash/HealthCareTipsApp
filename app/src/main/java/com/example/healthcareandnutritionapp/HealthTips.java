package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HealthTips extends AppCompatActivity {
    TextView healthTips, healthyHeartTips, relieveStressTips, seasonalTips;
    Button buttonBackHealthTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);

        healthTips = findViewById(R.id.healthTips);
        healthyHeartTips = findViewById(R.id.healthyHeartTips);
        relieveStressTips = findViewById(R.id.stressRelieveTips);
        seasonalTips = findViewById(R.id.seasonalTips);


        buttonBackHealthTips = findViewById(R.id.buttonBackHealthTips);
        buttonBackHealthTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTips.this, MainActivity.class));
            }
        });


        healthTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTips.this, SubClassHealthTips.class));
            }
        });

        healthyHeartTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTips.this, HealthyHeart.class));
            }
        });

        relieveStressTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTips.this, RelieveStress.class));
            }
        });

        seasonalTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTips.this, SeasonalTips.class));
            }
        });
    }
}
