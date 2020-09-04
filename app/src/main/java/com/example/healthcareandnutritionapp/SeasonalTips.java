package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class SeasonalTips extends AppCompatActivity {
    private Toolbar toolbar;

    RecyclerView seasonalTipsRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;

    ProgressBar seasonalTips_ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasonal_tips);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SeasonalTips.this, HealthTips.class));
                finish();
            }
        });



        seasonalTips_ProgressBar = findViewById(R.id.seasonalTips_ProgressBar);

        seasonalTipsRecyclerView = findViewById(R.id.seasonalNutritionRecyclerView);
        seasonalTipsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Health Tips").child("Seasonal Health Tips"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);


        seasonalTips_ProgressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                seasonalTips_ProgressBar.setVisibility(View.GONE);
                seasonalTipsRecyclerView.setAdapter(adapterRecyclerView);
            }
        },1000);

    }

    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}
