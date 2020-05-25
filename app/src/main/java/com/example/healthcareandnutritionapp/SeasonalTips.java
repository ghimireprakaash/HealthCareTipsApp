package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SeasonalTips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasonal_tips);

        FloatingActionButton seasonalTips_fab = findViewById(R.id.seasonalHealthTips_fab);

        //OnClick fab button, the screen or activity switches to its respective data insert activity
        seasonalTips_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InsertDataForSeasonalHealthTips.class);
                startActivity(intent);
            }
        });
    }
}
