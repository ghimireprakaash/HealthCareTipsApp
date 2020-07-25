package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class SeasonalTips extends AppCompatActivity {
    Button buttonBackSeasonalTips;

    RecyclerView seasonalTipsRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;


    ProgressBar progressBar_SeasonalTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasonal_tips);

        buttonBackSeasonalTips = findViewById(R.id.backArrow);
        buttonBackSeasonalTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SeasonalTips.this, HealthTips.class));
            }
        });


        progressBar_SeasonalTips = findViewById(R.id.progressBar_SeasonalNutritionTips);

        seasonalTipsRecyclerView = findViewById(R.id.seasonalNutritionRecyclerView);
        seasonalTipsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Health Tips").child("Seasonal Health Tips"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);
        seasonalTipsRecyclerView.setAdapter(adapterRecyclerView);


        progressBar_SeasonalTips.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar_SeasonalTips.setVisibility(View.GONE);
            }
        }, 2000);




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

    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();

    }
}
