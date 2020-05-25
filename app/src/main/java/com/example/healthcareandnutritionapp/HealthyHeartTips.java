package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HealthyHeartTips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_heart_tips);

        FloatingActionButton heartTips_fab = findViewById(R.id.heartTips_fab);

        //OnClick fab button, the screen or activity switches to its respective data insert activity
        heartTips_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InsertDataForHeartTips.class);
                startActivity(intent);
            }
        });
    }
}
