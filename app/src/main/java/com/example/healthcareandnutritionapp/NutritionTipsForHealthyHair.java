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

public class NutritionTipsForHealthyHair extends AppCompatActivity {
    private Toolbar toolbar;

    RecyclerView hairNutritionRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;

    ProgressBar healthyHairNutritionTips_ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_tips_for_healthy_hair);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionTipsForHealthyHair.this, NutritionTips.class));
                finish();
            }
        });



        healthyHairNutritionTips_ProgressBar = findViewById(R.id.healthyHairTips_ProgressBar);


        hairNutritionRecyclerView = findViewById(R.id.hairNutritionRecyclerView);
        hairNutritionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Nutrition Tips").child("Healthy Hair"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);


        healthyHairNutritionTips_ProgressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                healthyHairNutritionTips_ProgressBar.setVisibility(View.GONE);

                hairNutritionRecyclerView.setAdapter(adapterRecyclerView);

            }
        },1000);
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}
